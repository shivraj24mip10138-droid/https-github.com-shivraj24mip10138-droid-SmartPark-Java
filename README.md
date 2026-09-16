# Smart Parking System

Project Overview

The Smart Parking System is a Java-based command-line application designed to manage parking spaces efficiently.

The system allows users to register vehicle entry and exit, automatically assign parking slots, calculate parking fees, search for parked vehicles, view parking history, and display parking statistics.

The application uses Java, Maven, JDBC, and SQLite. It can be executed completely from the command line without requiring any GUI-based setup.



Features

* Vehicle entry registration
* Automatic parking slot allocation
* Separate parking support for cars and bikes
* Vehicle exit management
* Automatic parking fee calculation
* View available and occupied parking slots
* Search vehicle using registration number
* Parking history
* Parking statistics
* Revenue calculation
* SQLite database storage
* Command-line based interface
* Maven-based project structure


Technologies Used

* Java 17
* Apache Maven
* SQLite
* JDBC
* JUnit
* Git and GitHub

---

 Project Structure
smart-parking-system
│
├── pom.xml
├── README.md
├── .gitignore
├── PROJECT_REPORT_OUTLINE.md
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── smartparking/
│   │               ├── Main.java
│   │               ├── DatabaseManager.java
│   │               ├── ParkingService.java
│   │               ├── BillingService.java
│   │               ├── ReportService.java
│   │               │
│   │               └── model/
│   │                   ├── ParkingSlot.java
│   │                   └── Vehicle.java
│   │
│   └── test/
│       └── java/
│
└── target/



System Requirements

Before running the project, make sure the following software is installed.

Java

Java 17 or above is recommended.

Check Java installation:

java -version

Example:
java version "17"

Maven

Apache Maven is required to build the project.

Check Maven installation:
mvn -version

Example:
Apache Maven 3.9.x
Java version: 17

How to Download the Project

Clone the GitHub repository:
git clone https://github.com/shivraj24mip10138-droid/smart-parking-system.git

Move into the project directory:
cd smart-parking-system

How to Build the Project

Run the following command from the folder containing `pom.xml`:

mvn clean package

Maven will:

1. Download the required dependencies.
2. Compile the Java source code.
3. Run the tests.
4. Package the application into a JAR file.

If the build is successful, Maven will display:

BUILD SUCCESS

The generated JAR file will be available inside:
target/

How to Run the Project

After successfully building the application, run:

java -jar target/smart-parking-system.ja

The Smart Parking System menu will appear in the terminal.

Example:
====================================
       SMART PARKING SYSTEM
====================================

1. Vehicle Entry
2. Vehicle Exit
3. View Parking Slots
4. Search Vehicle
5. Parking History
6. Parking Statistics
7. Exit

Enter your choice

Running the Project Again

If the project has already been built and no source code has been changed, there is no need to run Maven again.

Simply open the project directory:


cd smart-parking-system

Then run:


java -jar target/smart-parking-system.jar

Application Workflow

The basic workflow of the Smart Parking System is:

Vehicle Arrives
      |
      v
Check Available Parking Slot
      |
      v
Assign Parking Slot
      |
      v
Store Vehicle Information
      |
      v
Store Entry Time
      |
      v
Vehicle Exit Request
      |
      v
Calculate Parking Duration
      |
      v
Calculate Parking Fee
      |
      v
Release Parking Slot
      |
      v
Store Parking History



Vehicle Entry

Select:

1. Vehicle Entry


The system asks for vehicle information such as:

Vehicle Number: MP04AB1234
Vehicle Type: CAR

The application searches for an available parking slot.

Example output:

Vehicle parked successfully.

Vehicle Number : MP04AB1234
Vehicle Type   : CAR
Assigned Slot  : C01


Vehicle Exit

Select:

2. Vehicle Exit

Enter the registration number:

MP04AB1234

The system:

* Finds the parked vehicle
* Calculates parking duration
* Calculates parking charges
* Stores the parking record
* Releases the parking slot

Example:

Vehicle Number : MP04AB1234
Parking Slot   : C01
Parking Fee    : Rs. 40

Vehicle exited successfully.


View Parking Slots

Select:

3. View Parking Slots

The application displays the status of parking slots.

Example:

Slot     Type       Status
--------------------------------
C01      CAR        OCCUPIED
C02      CAR        AVAILABLE
C03      CAR        AVAILABLE
B01      BIKE       AVAILABLE
B02      BIKE       OCCUPIED


Search Vehicle

Select:

4. Search Vehicle

Enter the vehicle registration number.

Example:

Enter vehicle number: MP04AB1234
The system displays information about the vehicle if it is currently parked.


Parking History

Select:
5. Parking History

The system displays previous parking records including:

* Vehicle number
* Parking slot
* Entry time
* Exit time
* Parking duration
* Parking fee


Parking Statistics

Select:

6. Parking Statistics

The application displays useful parking information.

Example:

================================
       PARKING STATISTICS
================================

Total Slots       : 20
Occupied Slots    : 8
Available Slots   : 12
Total Vehicles    : 35
Total Revenue     : Rs. 1250


Database

The application uses SQLite as its database.

SQLite was selected because:

* It does not require a separate database server.
* It is lightweight.
* It is easy to configure.
* It works well with Java through JDBC.
* It makes the project easy to execute during evaluation.

The database is automatically initialized when the application runs.

The database stores information such as:

* Parking slots
* Vehicle information
* Entry time
* Exit time
* Parking history
* Parking fees



Main Modules

Main.java

Contains the main method and command-line menu.

It accepts user input and allows the user to access different Smart Parking System functions.



DatabaseManager.java

Responsible for:

Establishing SQLite database connection
Creating required database tables
Initializing parking slots
Managing database operations



 ParkingService.java

Responsible for the main parking operations.

Functions include:

 Vehicle entry
 Vehicle exit
 Slot assignment
 Slot availability
 Vehicle search



BillingService.java

Responsible for parking fee calculation.

The fee is calculated according to the duration for which the vehicle remained in the parking area.


 ReportService.java

Responsible for generating information such as:
Parking history
Number of occupied slots
Number of available slots
Total vehicles
Revenue statistics



Vehicle.java

Represents vehicle information such as:

text
Vehicle Number
Vehicle Type


ParkingSlot.java

Represents individual parking slot information such as:

text
Slot Number
Slot Type
Availability Status


Testing

The project includes JUnit tests for important application functionality such as parking fee calculation.

Run all tests using:

bash
mvn test

If the tests are successful:

text
BUILD SUCCESS

will be displayed.


Complete Setup Procedure for Windows PowerShell

Open PowerShell.

Navigate to the folder where the repository is downloaded:

powershell
cd C:\Users\desk\Downloads\smart-parking-system


Check project files:

powershell
dir


Make sure the following files are visible:

text
pom.xml
README.md
src


Build the application:

powershell
mvn clean package

Run the application:

powershell
java -jar target\smart-parking-system.jar


Example Complete Execution

powershell
git clone https://github.com/shivraj24mip10138-droid/smart-parking-system.git

cd smart-parking-system

mvn clean package

java -jar target\smart-parking-system.jar

Objectives

The main objectives of this project are:

1. To automate parking slot management.
2. To reduce manual parking management.
3. To automatically assign available parking slots.
4. To maintain vehicle entry and exit records.
5. To calculate parking charges automatically.
6. To provide parking availability information.
7. To maintain parking history.
8. To generate basic parking analytics.



Advantages

Simple command-line interface
Easy installation
Automatic slot management
Automatic fee calculation
Persistent database storage
Vehicle search functionality
Parking history
Parking analytics
No external database server required



Future Enhancements

The system can be further improved by adding:

Graphical user interface
Web-based dashboard
QR-code based parking tickets
Online payment integration
Number plate recognition
IoT parking sensors
Real-time parking availability
Mobile application
Admin login system
Parking reservation system
Machine learning based parking demand prediction


Conclusion

The "Smart Parking System" provides a simple and efficient solution for managing parking spaces.

The project demonstrates practical implementation of Java programming, object-oriented programming, Maven dependency management, JDBC database connectivity, SQLite database operations, testing, and command-line application development.

The system successfully handles vehicle entry, parking slot assignment, vehicle exit, parking fee calculation, parking history, vehicle search, and parking statistics.


 Author
Shivraj

GitHub Username:shivraj24mip10138-droid

Project:Smart Parking System

Repository :https://github.com/shivraj24mip10138-droid/smart-parking-system
