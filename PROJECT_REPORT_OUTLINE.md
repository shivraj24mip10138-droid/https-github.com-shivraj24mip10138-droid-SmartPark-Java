# Project Report Outline

## 1. Title
Smart Parking Management and Analytics System

## 2. Problem Statement
Traditional parking management requires manual tracking of parking slots, vehicle entry and exit, and payment calculation. The project automates these operations through a command-line Java application.

## 3. Objectives
- Track available and occupied parking slots.
- Automatically allocate suitable slots.
- Record vehicle entry and exit time.
- Calculate parking charges.
- Maintain parking history.
- Generate occupancy and revenue statistics.

## 4. Technologies Used
Java, Maven, JDBC, SQLite, JUnit 5.

## 5. Modules
- Database Manager
- Parking Service
- Billing Service
- Reporting Service
- Command-Line User Interface

## 6. Database Design
Tables: parking_slots, active_parking, parking_history.

## 7. Algorithm
1. Read vehicle details.
2. Validate that the vehicle is not already parked.
3. Find the first available slot matching vehicle type.
4. Mark the slot occupied and record entry time.
5. On exit, calculate duration.
6. Calculate bill using hourly rate.
7. Move the record to parking history.
8. Mark the slot available.

## 8. Testing
Test billing rules, slot assignment, duplicate vehicle handling, invalid searches, and statistics.

## 9. Results
The application successfully automates basic parking operations and persists information in an SQLite database.

## 10. Limitations
The current version does not use physical sensors, online payments, or automatic number plate recognition.

## 11. Future Scope
IoT sensors, web/mobile interface, reservations, ANPR, dynamic pricing, and predictive analytics.

## 12. Conclusion
The system demonstrates how Java, JDBC, and database concepts can be combined to build a practical smart parking management application.
