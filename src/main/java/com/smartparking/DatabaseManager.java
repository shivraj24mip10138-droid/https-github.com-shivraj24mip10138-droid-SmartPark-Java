package com.smartparking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:parking.db";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public void initializeDatabase() {
        String createSlots = """
                CREATE TABLE IF NOT EXISTS parking_slots (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    slot_number TEXT UNIQUE NOT NULL,
                    vehicle_type TEXT NOT NULL,
                    status TEXT NOT NULL DEFAULT 'AVAILABLE'
                )
                """;

        String createActiveParking = """
                CREATE TABLE IF NOT EXISTS active_parking (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    vehicle_number TEXT UNIQUE NOT NULL,
                    vehicle_type TEXT NOT NULL,
                    slot_id INTEGER UNIQUE NOT NULL,
                    entry_time TEXT NOT NULL,
                    FOREIGN KEY(slot_id) REFERENCES parking_slots(id)
                )
                """;

        String createHistory = """
                CREATE TABLE IF NOT EXISTS parking_history (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    vehicle_number TEXT NOT NULL,
                    vehicle_type TEXT NOT NULL,
                    slot_number TEXT NOT NULL,
                    entry_time TEXT NOT NULL,
                    exit_time TEXT NOT NULL,
                    duration_minutes INTEGER NOT NULL,
                    amount REAL NOT NULL
                )
                """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            statement.execute("PRAGMA foreign_keys = ON");
            statement.execute(createSlots);
            statement.execute(createActiveParking);
            statement.execute(createHistory);
            seedSlots(connection);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to initialize database: " + e.getMessage(), e);
        }
    }

    private void seedSlots(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    INSERT OR IGNORE INTO parking_slots(slot_number, vehicle_type, status) VALUES
                    ('C-01', 'CAR', 'AVAILABLE'),
                    ('C-02', 'CAR', 'AVAILABLE'),
                    ('C-03', 'CAR', 'AVAILABLE'),
                    ('C-04', 'CAR', 'AVAILABLE'),
                    ('C-05', 'CAR', 'AVAILABLE'),
                    ('B-01', 'BIKE', 'AVAILABLE'),
                    ('B-02', 'BIKE', 'AVAILABLE'),
                    ('B-03', 'BIKE', 'AVAILABLE'),
                    ('B-04', 'BIKE', 'AVAILABLE'),
                    ('B-05', 'BIKE', 'AVAILABLE')
                    """);
        }
    }
}
