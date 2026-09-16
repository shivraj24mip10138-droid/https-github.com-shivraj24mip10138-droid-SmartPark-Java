# Smart Parking Management and Analytics System

A command-line Java project that manages parking slots, vehicle entry and exit, automatic slot allocation, billing, parking history, and parking analytics.

## Features

- Automatic parking slot assignment
- Separate car and bike slots
- Vehicle entry and exit management
- Parking fee calculation
- Search currently parked vehicles
- Parking history
- Parking occupancy statistics
- Revenue statistics
- SQLite database persistence
- Maven build and test support

## Technology Stack

- Java 17 or later
- Maven
- JDBC
- SQLite
- JUnit 5

## Project Structure

```text
smart-parking-system/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/java/com/smartparking/
    │   ├── Main.java
    │   ├── DatabaseManager.java
    │   ├── ParkingService.java
    │   ├── BillingService.java
    │   ├── ReportService.java
    │   └── model/
    │       ├── ParkingSlot.java
    │       └── Vehicle.java
    └── test/java/com/smartparking/
        └── BillingServiceTest.java
```

## Prerequisites

Check Java:

```bash
java -version
```

Check Maven:

```bash
mvn -version
```

Java 17 or newer is recommended.

## Build

Open a terminal in the project root and run:

```bash
mvn clean package
```

Maven downloads the required SQLite JDBC dependency automatically and creates an executable JAR.

## Run

```bash
java -jar target/smart-parking-system.jar
```

The program automatically creates `parking.db` in the project directory on first run.

## Menu

```text
1. Vehicle Entry
2. Vehicle Exit
3. View Parking Slots
4. Search Vehicle
5. Parking History
6. Parking Statistics
7. Exit
```

## Default Parking Slots

The application initially creates:

- 5 car slots: C-01 to C-05
- 5 bike slots: B-01 to B-05

## Parking Rates

- Car: Rs. 40 per hour
- Bike: Rs. 20 per hour
- Minimum charge: one hour
- Partial hours are rounded up to the next hour

## Run Tests

```bash
mvn test
```

## Example

```text
Enter your choice: 1

Enter vehicle number: MP04AB1234
Enter vehicle type (CAR/BIKE): CAR
Vehicle parked successfully.
Assigned Slot : C-01
```

## Database Tables

The SQLite database contains:

- `parking_slots`
- `active_parking`
- `parking_history`

## Reset Application Data

Delete `parking.db` and run the application again. A fresh database and default parking slots will be created automatically.

## Future Enhancements

- Number plate recognition
- QR-based parking tickets
- Online slot reservation
- Dynamic pricing
- Admin dashboard
- IoT sensor integration
- Peak-hour prediction

## Course Project Notes

This project is designed to run completely from the command line. An evaluator can clone the repository, build it with Maven, and run the generated JAR without any GUI-based setup.
