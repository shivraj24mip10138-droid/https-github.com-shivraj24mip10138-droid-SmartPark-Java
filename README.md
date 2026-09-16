# https-github.com-shivraj24mip10138-droid-SmartPark-Java
SmartPark is a Java-based Smart Parking Management System that helps users find, reserve, and manage parking slots efficiently. It includes user and vehicle management, smart slot allocation, reservations, automatic fee calculation, payments, and parking analytics using PostgreSQL with a simple command-line interface.

SmartPark – Smart Parking Management System
1. Project Overview

SmartPark is a Java-based Smart Parking Management System designed to simplify and automate the management of parking spaces. The system provides a centralized platform through which users can register their vehicles, view available parking slots, reserve suitable spaces, manage reservations, calculate parking charges, record payments, and view parking-related reports.

Traditional parking systems often require users to search manually for available spaces, while parking administrators have to maintain records of vehicles, slots, reservations, and payments. These processes can become inefficient as the number of vehicles and parking spaces increases. SmartPark addresses these problems by providing a structured, computerized solution.

The project is developed using Java with PostgreSQL as the database and JDBC for database connectivity. It uses a command-line interface (CLI) so that the complete application can be executed through a terminal without requiring a graphical interface.

2. Problem Statement

Parking management becomes difficult when the availability and usage of parking spaces are maintained manually. Users may not know which slots are available, while administrators need to keep track of occupied spaces, reservations, vehicles, parking duration, and payments.

The absence of an organized system can result in:

Difficulty finding available parking slots.
Duplicate or incorrect reservations.
Poor tracking of vehicle information.
Manual calculation of parking fees.
Difficulty maintaining payment records.
Lack of useful parking statistics and reports.
Increased chances of human errors.

SmartPark aims to solve these problems by providing an organized Java-based system for managing the complete parking process.

3. Project Objectives

The main objectives of SmartPark are:

3.1 Parking Management
Maintain information about parking slots.
Display available and occupied slots.
Manage different types of parking spaces.
Update slot status when vehicles enter or leave.
3.2 User and Vehicle Management
Allow users to register.
Maintain user information.
Register vehicles associated with users.
Provide controlled access to the system.
3.3 Reservation Management
Allow users to reserve parking slots.
Prevent invalid or duplicate reservations.
Allow users to cancel reservations.
Maintain reservation history.
3.4 Payment Management
Automatically calculate parking charges.
Generate parking bills.
Store payment information.
Maintain payment history.
3.5 Reporting and Analytics
Display parking occupancy.
Show available and occupied spaces.
Calculate revenue.
Provide useful parking statistics.
4. Major Functional Modules
4.1 User Management Module

The User Management Module handles user registration and user information.

Features:
New user registration.
User login.
User information management.
Vehicle registration.
User-specific reservation history.
4.2 Parking Slot Management Module

This module manages all parking spaces in the system.

Features:
Add parking slots.
View all parking slots.
View available slots.
View occupied slots.
Update slot status.
Identify suitable slots based on vehicle requirements.
4.3 Reservation Management Module

The Reservation Module manages the process of booking parking spaces.

Features:
Search for available slots.
Reserve a parking slot.
View reservation details.
Cancel reservations.
Maintain reservation status.
Maintain reservation history.
4.4 Payment and Billing Module

This module handles parking fee calculation and payment records.

Features:
Calculate parking charges based on parking duration.
Generate bills.
Record payment details.
Store payment status.
View payment history.

For example, the system can calculate a basic parking charge according to the configured hourly rate:

First hour       → ₹30
Every extra hour → ₹20

The actual rates can be configured according to the project's requirements.

4.5 Reports and Analytics Module

This module provides useful information about parking operations.

Features:
Total number of parking slots.
Number of available slots.
Number of occupied slots.
Occupancy percentage.
Revenue information.
Parking usage statistics.

Example:

========== PARKING REPORT ==========

Total Slots       : 100
Occupied Slots    : 63
Available Slots   : 37
Occupancy         : 63%
Today's Revenue   : ₹4,850

====================================
5. Smart Slot Allocation

One of the important features of SmartPark is smart parking-slot selection.

Instead of simply assigning the first available slot, the system can consider the vehicle's requirements while selecting a suitable space.

For example:

Available Slots:

A01 → Regular
A02 → Regular
A03 → EV
B01 → Accessible

If an electric vehicle requires parking, the system can prioritize an available EV slot.

This makes the parking allocation process more organized and demonstrates the use of programming logic and data structures in the project.

6. Technology Stack

The project uses the following technologies:

Programming Language

Java

Java is used to implement the application's business logic, object-oriented structure, validation, parking operations, reservation processing, and payment calculations.

Database

PostgreSQL

PostgreSQL is used to permanently store users, vehicles, parking slots, reservations, and payment information.

Database Connectivity

JDBC

Java Database Connectivity (JDBC) connects the Java application with PostgreSQL and allows the application to execute database operations.

Build Tool

Apache Maven

Maven is used to manage project dependencies, build the project, and maintain a standard Java project structure.

Development Environment

Visual Studio Code

VS Code is used for writing, managing, testing, and executing the Java source code.

Version Control

Git and GitHub

Git and GitHub are used to manage project versions and maintain the source code repository.

7. System Architecture

SmartPark follows a three-layer architecture to keep the project modular and maintainable.

              USER / ADMIN
                   │
                   ▼
        ┌─────────────────────┐
        │ Presentation Layer  │
        │    CLI Interface    │
        └──────────┬──────────┘
                   │
                   ▼
        ┌─────────────────────┐
        │    Service Layer    │
        │                     │
        │ User Service        │
        │ Parking Service     │
        │ Reservation Service │
        │ Payment Service     │
        └──────────┬──────────┘
                   │
                   ▼
        ┌─────────────────────┐
        │   Data Access Layer │
        │      JDBC / DAO     │
        └──────────┬──────────┘
                   │
                   ▼
        ┌─────────────────────┐
        │    PostgreSQL DB    │
        └─────────────────────┘
7.1 Presentation Layer

The presentation layer provides the command-line interface through which users interact with SmartPark.

7.2 Service Layer

The service layer contains the main business logic, including parking allocation, reservations, fee calculation, and user operations.

7.3 Data Access Layer

The data access layer handles communication between Java and PostgreSQL using JDBC.

8. Database Design

SmartPark will use PostgreSQL to store the application's persistent data.

The major tables are:

USERS

Stores user information.

user_id
name
email
phone
password
VEHICLES

Stores vehicles associated with users.

vehicle_id
user_id
vehicle_number
vehicle_type
PARKING_SLOTS

Stores parking-space information.

slot_id
slot_number
slot_type
status
RESERVATIONS

Stores parking reservations.

reservation_id
user_id
vehicle_id
slot_id
start_time
end_time
status
PAYMENTS

Stores payment information.

payment_id
reservation_id
amount
payment_method
payment_status

9. Java Project Structure
The project follows a modular package structure:

SmartPark-Java/
│

├── README.md

├── statement.md

├── pom.xml

├── .gitignore

├── LICENSE

│

├── src/

│   ├── main/

│   │   └── java/

│   │       └── smartpark/

│   │           ├── Main.java

│   │           │

│   │           ├── model/

│   │           │   ├── User.java

│   │           │   ├── Vehicle.java

│   │           │   ├── ParkingSlot.java

│   │           │   ├── Reservation.java

│   │           │   └── Payment.java

│   │           │

│   │           ├── service/

│   │           │   ├── UserService.java

│   │           │   ├── ParkingService.java

│   │           │   ├── ReservationService.java

│   │           │   └── PaymentService.java

│   │           │

│   │           └── database/

│   │               └── DatabaseConnection.java

│   │

│   └── test/

│

├── sql/

│   └── schema.sql

│

└── docs/

    └── diagrams/



This structure separates data models, business logic, and database operations, making the project easier to understand and maintain.

10. Command-Line Interface
    
SmartPark will be designed to run completely from the terminal.

A sample main menu will be:

  SMARTPARK MANAGEMENT SYSTEM :

1. Register User
2. Login
3. Register Vehicle
4. View Available Slots
5. Reserve Parking
6. Cancel Reservation
7. View Reservations
8. Make Payment
9. Parking Reports
10. Exit

Enter your choice:
The user selects an option and provides the required input. The system validates the input, performs the requested operation, and displays the result.

11. Non-Functional Requirements
11.1 Performance:
The system should process common operations such as searching for available slots and retrieving reservations efficiently.

11.2 Security:
User information and database access should be protected using appropriate authentication and controlled database permissions.

11.3 Reliability:
The system should handle invalid operations and database errors without unexpectedly terminating the application.

11.4 Maintainability

The project should use separate packages and classes so that individual modules can be modified without affecting the entire system.

11.5 Error Handling

The application should validate user inputs and provide meaningful error messages for invalid data.

11.6 Scalability

The database and application structure should allow additional parking areas, slots, users, and vehicles to be added in the future.

12. Validation and Error Handling

SmartPark will include validation for common situations such as:

Invalid user input.
Invalid vehicle number.
Duplicate vehicle registration.
Attempting to reserve an occupied slot.
Invalid reservation ID.
Cancelling an already cancelled reservation.
Invalid payment information.
Database connection failures.

Instead of terminating unexpectedly, the system should display a useful message and allow the user to continue.

13. Testing

Testing will be performed for individual modules and complete workflows.

Example Test Cases
Test Case	Expected Result
Register valid user	User successfully registered
Register duplicate email	Error message displayed
View available slots	Available slots displayed
Reserve available slot	Reservation created
Reserve occupied slot	Reservation rejected
Cancel reservation	Reservation cancelled
Calculate parking fee	Correct amount displayed
Make valid payment	Payment recorded
View report	Correct statistics displayed
14. Future Enhancements

The current system can be extended in several ways:

14.1 Online Payment

Integration with a payment gateway could allow users to make real payments.

14.2 Mobile Application

A mobile application could allow users to reserve parking spaces remotely.

14.3 QR Code Parking

QR codes could be used for faster vehicle entry and exit.

14.4 IoT Integration

Sensors could automatically detect whether parking spaces are occupied.

14.5 Advanced Analytics

Historical parking data could be analyzed to predict peak parking hours and demand.

14.6 Electric Vehicle Support

Dedicated EV charging stations could be integrated into the parking management system.

15. Project Benefits

SmartPark provides several benefits:

Reduces manual parking management.
Makes parking-slot availability easier to track.
Simplifies reservations.
Automates parking-fee calculations.
Maintains organized vehicle and payment records.
Provides useful parking analytics.
Demonstrates practical Java programming.
Demonstrates database connectivity using JDBC.
Provides a modular and maintainable architecture.
16. Conclusion

SmartPark – Smart Parking Management System is a Java-based application designed to provide an organized and efficient approach to parking management. The system combines user management, vehicle management, parking-slot allocation, reservations, payment processing, and analytics into a single application.
