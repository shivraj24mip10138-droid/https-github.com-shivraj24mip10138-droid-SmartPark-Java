package com.smartparking;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final DatabaseManager databaseManager;
    private final BillingService billingService;

    public ParkingService(DatabaseManager databaseManager, BillingService billingService) {
        this.databaseManager = databaseManager;
        this.billingService = billingService;
    }

    public void parkVehicle(String vehicleNumber, String vehicleType) {
        vehicleNumber = normalizeVehicleNumber(vehicleNumber);
        vehicleType = normalizeVehicleType(vehicleType);

        if (vehicleNumber.isBlank()) {
            System.out.println("Vehicle number cannot be empty.");
            return;
        }

        String duplicateSql = "SELECT 1 FROM active_parking WHERE vehicle_number = ?";
        String findSlotSql = "SELECT id, slot_number FROM parking_slots WHERE status = 'AVAILABLE' AND vehicle_type = ? ORDER BY id LIMIT 1";
        String insertParkingSql = "INSERT INTO active_parking(vehicle_number, vehicle_type, slot_id, entry_time) VALUES (?, ?, ?, ?)";
        String updateSlotSql = "UPDATE parking_slots SET status = 'OCCUPIED' WHERE id = ?";

        try (Connection connection = databaseManager.getConnection()) {
            connection.setAutoCommit(false);
            try {
                try (PreparedStatement duplicate = connection.prepareStatement(duplicateSql)) {
                    duplicate.setString(1, vehicleNumber);
                    try (ResultSet resultSet = duplicate.executeQuery()) {
                        if (resultSet.next()) {
                            System.out.println("This vehicle is already parked.");
                            connection.rollback();
                            return;
                        }
                    }
                }

                int slotId;
                String slotNumber;
                try (PreparedStatement findSlot = connection.prepareStatement(findSlotSql)) {
                    findSlot.setString(1, vehicleType);
                    try (ResultSet resultSet = findSlot.executeQuery()) {
                        if (!resultSet.next()) {
                            System.out.println("No available " + vehicleType.toLowerCase() + " parking slot.");
                            connection.rollback();
                            return;
                        }
                        slotId = resultSet.getInt("id");
                        slotNumber = resultSet.getString("slot_number");
                    }
                }

                String entryTime = LocalDateTime.now().format(FORMATTER);
                try (PreparedStatement insertParking = connection.prepareStatement(insertParkingSql);
                     PreparedStatement updateSlot = connection.prepareStatement(updateSlotSql)) {
                    insertParking.setString(1, vehicleNumber);
                    insertParking.setString(2, vehicleType);
                    insertParking.setInt(3, slotId);
                    insertParking.setString(4, entryTime);
                    insertParking.executeUpdate();

                    updateSlot.setInt(1, slotId);
                    updateSlot.executeUpdate();
                }

                connection.commit();
                System.out.println("Vehicle parked successfully.");
                System.out.println("Assigned Slot : " + slotNumber);
                System.out.println("Entry Time    : " + entryTime);
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println("Parking operation failed: " + e.getMessage());
        }
    }

    public void exitVehicle(String vehicleNumber) {
        vehicleNumber = normalizeVehicleNumber(vehicleNumber);

        String findSql = """
                SELECT a.vehicle_number, a.vehicle_type, a.entry_time, a.slot_id, s.slot_number
                FROM active_parking a
                JOIN parking_slots s ON s.id = a.slot_id
                WHERE a.vehicle_number = ?
                """;
        String historySql = """
                INSERT INTO parking_history(vehicle_number, vehicle_type, slot_number, entry_time, exit_time, duration_minutes, amount)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        String deleteSql = "DELETE FROM active_parking WHERE vehicle_number = ?";
        String releaseSql = "UPDATE parking_slots SET status = 'AVAILABLE' WHERE id = ?";

        try (Connection connection = databaseManager.getConnection()) {
            connection.setAutoCommit(false);
            try {
                String vehicleType;
                String entryTimeText;
                String slotNumber;
                int slotId;

                try (PreparedStatement find = connection.prepareStatement(findSql)) {
                    find.setString(1, vehicleNumber);
                    try (ResultSet resultSet = find.executeQuery()) {
                        if (!resultSet.next()) {
                            System.out.println("Vehicle not found in active parking.");
                            connection.rollback();
                            return;
                        }
                        vehicleType = resultSet.getString("vehicle_type");
                        entryTimeText = resultSet.getString("entry_time");
                        slotId = resultSet.getInt("slot_id");
                        slotNumber = resultSet.getString("slot_number");
                    }
                }

                LocalDateTime entryTime = LocalDateTime.parse(entryTimeText, FORMATTER);
                LocalDateTime exitTime = LocalDateTime.now();
                long durationMinutes = Math.max(1, Duration.between(entryTime, exitTime).toMinutes());
                double amount = billingService.calculateFee(vehicleType, durationMinutes);
                String exitTimeText = exitTime.format(FORMATTER);

                try (PreparedStatement history = connection.prepareStatement(historySql);
                     PreparedStatement delete = connection.prepareStatement(deleteSql);
                     PreparedStatement release = connection.prepareStatement(releaseSql)) {

                    history.setString(1, vehicleNumber);
                    history.setString(2, vehicleType);
                    history.setString(3, slotNumber);
                    history.setString(4, entryTimeText);
                    history.setString(5, exitTimeText);
                    history.setLong(6, durationMinutes);
                    history.setDouble(7, amount);
                    history.executeUpdate();

                    delete.setString(1, vehicleNumber);
                    delete.executeUpdate();

                    release.setInt(1, slotId);
                    release.executeUpdate();
                }

                connection.commit();
                System.out.println("Vehicle exited successfully.");
                System.out.println("Slot            : " + slotNumber);
                System.out.println("Duration        : " + durationMinutes + " minute(s)");
                System.out.printf("Parking Fee     : Rs. %.2f%n", amount);
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println("Exit operation failed: " + e.getMessage());
        }
    }

    public void viewSlots() {
        String sql = "SELECT slot_number, vehicle_type, status FROM parking_slots ORDER BY id";
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            System.out.printf("%-10s %-10s %-12s%n", "SLOT", "TYPE", "STATUS");
            System.out.println("--------------------------------");
            while (resultSet.next()) {
                System.out.printf("%-10s %-10s %-12s%n",
                        resultSet.getString("slot_number"),
                        resultSet.getString("vehicle_type"),
                        resultSet.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println("Unable to display parking slots: " + e.getMessage());
        }
    }

    public void searchVehicle(String vehicleNumber) {
        vehicleNumber = normalizeVehicleNumber(vehicleNumber);
        String sql = """
                SELECT a.vehicle_number, a.vehicle_type, a.entry_time, s.slot_number
                FROM active_parking a
                JOIN parking_slots s ON s.id = a.slot_id
                WHERE a.vehicle_number = ?
                """;

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vehicleNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    System.out.println("Vehicle is not currently parked.");
                    return;
                }
                System.out.println("Vehicle Number : " + resultSet.getString("vehicle_number"));
                System.out.println("Vehicle Type   : " + resultSet.getString("vehicle_type"));
                System.out.println("Parking Slot   : " + resultSet.getString("slot_number"));
                System.out.println("Entry Time     : " + resultSet.getString("entry_time"));
            }
        } catch (SQLException e) {
            System.out.println("Search failed: " + e.getMessage());
        }
    }

    private String normalizeVehicleNumber(String vehicleNumber) {
        return vehicleNumber == null ? "" : vehicleNumber.trim().toUpperCase();
    }

    private String normalizeVehicleType(String vehicleType) {
        String normalized = vehicleType == null ? "" : vehicleType.trim().toUpperCase();
        return "BIKE".equals(normalized) ? "BIKE" : "CAR";
    }
}
