package com.smartparking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.initializeDatabase();

        BillingService billingService = new BillingService();
        ParkingService parkingService = new ParkingService(databaseManager, billingService);
        ReportService reportService = new ReportService(databaseManager);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                printMenu();
                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine().trim();
                System.out.println();

                switch (choice) {
                    case "1" -> {
                        System.out.print("Enter vehicle number: ");
                        String vehicleNumber = scanner.nextLine();
                        System.out.print("Enter vehicle type (CAR/BIKE): ");
                        String vehicleType = scanner.nextLine();
                        parkingService.parkVehicle(vehicleNumber, vehicleType);
                    }
                    case "2" -> {
                        System.out.print("Enter vehicle number: ");
                        parkingService.exitVehicle(scanner.nextLine());
                    }
                    case "3" -> parkingService.viewSlots();
                    case "4" -> {
                        System.out.print("Enter vehicle number: ");
                        parkingService.searchVehicle(scanner.nextLine());
                    }
                    case "5" -> reportService.showParkingHistory();
                    case "6" -> reportService.showStatistics();
                    case "7" -> {
                        running = false;
                        System.out.println("Thank you for using Smart Parking System.");
                    }
                    default -> System.out.println("Invalid choice. Please enter a number from 1 to 7.");
                }

                if (running) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("   SMART PARKING MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Vehicle Entry");
        System.out.println("2. Vehicle Exit");
        System.out.println("3. View Parking Slots");
        System.out.println("4. Search Vehicle");
        System.out.println("5. Parking History");
        System.out.println("6. Parking Statistics");
        System.out.println("7. Exit");
        System.out.println("========================================");
    }
}
