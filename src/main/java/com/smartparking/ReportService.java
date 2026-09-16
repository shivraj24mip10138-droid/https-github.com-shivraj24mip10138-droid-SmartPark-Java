package com.smartparking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportService {
    private final DatabaseManager databaseManager;

    public ReportService(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void showParkingHistory() {
        String sql = """
                SELECT vehicle_number, vehicle_type, slot_number, entry_time, exit_time, duration_minutes, amount
                FROM parking_history
                ORDER BY id DESC
                LIMIT 50
                """;

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            System.out.printf("%-14s %-7s %-7s %-20s %-20s %-10s %-10s%n",
                    "VEHICLE", "TYPE", "SLOT", "ENTRY", "EXIT", "MINUTES", "AMOUNT");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-14s %-7s %-7s %-20s %-20s %-10d Rs. %-7.2f%n",
                        rs.getString("vehicle_number"),
                        rs.getString("vehicle_type"),
                        rs.getString("slot_number"),
                        rs.getString("entry_time"),
                        rs.getString("exit_time"),
                        rs.getLong("duration_minutes"),
                        rs.getDouble("amount"));
            }
            if (!found) {
                System.out.println("No parking history available yet.");
            }
        } catch (SQLException e) {
            System.out.println("Unable to load parking history: " + e.getMessage());
        }
    }

    public void showStatistics() {
        String sql = """
                SELECT
                    (SELECT COUNT(*) FROM parking_slots) AS total_slots,
                    (SELECT COUNT(*) FROM parking_slots WHERE status = 'OCCUPIED') AS occupied_slots,
                    (SELECT COUNT(*) FROM parking_slots WHERE status = 'AVAILABLE') AS available_slots,
                    (SELECT COUNT(*) FROM active_parking) AS active_vehicles,
                    (SELECT COUNT(*) FROM parking_history) AS completed_visits,
                    (SELECT COALESCE(SUM(amount), 0) FROM parking_history) AS total_revenue
                """;

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            if (rs.next()) {
                int total = rs.getInt("total_slots");
                int occupied = rs.getInt("occupied_slots");
                int available = rs.getInt("available_slots");
                double occupancy = total == 0 ? 0.0 : (occupied * 100.0 / total);

                System.out.println("Parking Statistics");
                System.out.println("----------------------------");
                System.out.println("Total Slots       : " + total);
                System.out.println("Occupied Slots    : " + occupied);
                System.out.println("Available Slots   : " + available);
                System.out.println("Active Vehicles   : " + rs.getInt("active_vehicles"));
                System.out.println("Completed Visits  : " + rs.getInt("completed_visits"));
                System.out.printf("Occupancy Rate    : %.2f%%%n", occupancy);
                System.out.printf("Total Revenue     : Rs. %.2f%n", rs.getDouble("total_revenue"));
            }
        } catch (SQLException e) {
            System.out.println("Unable to load statistics: " + e.getMessage());
        }
    }
}
