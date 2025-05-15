package com.yearup.dealership.kgdealer.ui;

import com.yearup.dealership.kgdealer.data.DealershipFileManager;
import com.yearup.dealership.kgdealer.model.Dealership;
import com.yearup.dealership.kgdealer.model.Vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class UserInterface {

    private Dealership dealership;
    static Scanner read = new Scanner(System.in);

    public void init() {
        this.dealership = new Dealership("KG Dealership", "123 Main St", "555-1234");
    }


    public void displayVehicles(ArrayList<Vehicle> vehicles) {

        Collections.sort(vehicles, Comparator.comparingInt(Vehicle::getVin));
        System.out.println("Displaying vehicles:");
        loadingBar();

        System.out.printf("%-10s %-6s %-10s %-10s %-15s %-10s %-10s %-10s%n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");
        System.out.println("----------------------------------------------------------------------------------------");
        for (Vehicle vehicle : vehicles) {
            System.out.printf("%-10s %-6d %-10s %-10s %-15s %-10s %-10d %-10.2f%n",
                    vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(),
                    vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
        }
        System.out.println("----------------------------------------------------------------------------------------");
    }


    public void display() {
        init();
        boolean running = false;
        System.out.println("Press enter to Start the program");
        System.out.println("Press any other key to exit");
        String start = read.nextLine();

        if (start.isEmpty()) {
            System.out.println("Starting the program...");
            loadingBar();
            running = true;
        } else {
            System.out.println("Exiting the program...");
            System.exit(0);
        }

        // Start menu implementation
        while (running) {
            System.out.println("Welcome to the Dealership Management System");
            System.out.println("1 | Find vehicles within a price range");
            System.out.println("2 | Find vehicles by make / model");
            System.out.println("3 | Find vehicles by year range");
            System.out.println("4 | Find vehicles by color");
            System.out.println("5 | Find vehicles by mileage range");
            System.out.println("6 | Find vehicles by vehicle type");
            System.out.println("7 | View all vehicles");
            System.out.println("8 | Add a vehicle");
            System.out.println("9 | Remove a vehicle");
            System.out.println("0 | Exit");
            System.out.println("Please select an option: ");
            System.out.print("> ");

            String choiceStr = read.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(choiceStr);
                if (choice < 0 || choice > 9) {
                    System.out.println("Invalid choice. Please try again.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetHyColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 0:
                    System.out.println("Exiting the program...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public void processGetByPriceRequest() {
        System.out.print("Enter minimum price: ");
        while (!read.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.print("Enter minimum price: ");
            read.next();
        }
        double minPrice = read.nextDouble();
        read.nextLine();

        System.out.print("Enter maximum price: ");
        while (!read.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.print("Enter maximum price: ");
            read.next();
        }
        double maxPrice = read.nextDouble();
        read.nextLine();

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(vehicles);
    }


    public void processGetByMakeModelRequest() {
        System.out.println("Enter make (leave blank to skip): ");
        String make = read.nextLine();
        while (!make.isEmpty() && !make.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. Please enter only letters or leave blank.");
            System.out.println("Enter make (leave blank to skip): ");
            make = read.nextLine();
        }

        System.out.println("Enter model (leave blank to skip): ");
        String model = read.nextLine();
        while (!model.isEmpty() && !model.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. Please enter only letters or leave blank.");
            System.out.println("Enter model (leave blank to skip): ");
            model = read.nextLine();
        }
        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }


    public void processGetByYearRequest() {

        System.out.print("Enter minimum year: ");
        while (!read.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.print("Enter minimum year: ");
            read.next();
        }
        int minYear = read.nextInt();
        read.nextLine();

        System.out.print("Enter maximum year: ");
        while (!read.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.print("Enter maximum year: ");
            read.next();
        }
        int maxYear = read.nextInt();
        read.nextLine();

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(vehicles);
    }


    public void processGetHyColorRequest() {
        System.out.print("Enter color: ");
        String color = read.nextLine();
        while (!color.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. Please enter only letters.");
            System.out.print("Enter color: ");
            color = read.nextLine();
        }

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found with the specified color.");
        } else {
            displayVehicles(vehicles);
        }
    }


    public void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        while (!read.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.print("Enter minimum mileage: ");
            read.next();
        }
        int minMileage = read.nextInt();
        read.nextLine();

        System.out.print("Enter maximum mileage: ");
        while (!read.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            System.out.print("Enter maximum mileage: ");
            read.next();
        }
        int maxMileage = read.nextInt();
        read.nextLine();

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found with the specified mileage.");
        } else {
            displayVehicles(vehicles);
        }
    }


    public void processGetByVehicleTypeRequest() {

        System.out.print("Enter vehicle type: ");
        String vehicleType = read.nextLine();
        while (!vehicleType.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input. Please enter only letters.");
            System.out.print("Enter vehicle type: ");
            vehicleType = read.nextLine();
        }

        ArrayList<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found with the specified type.");
        } else {
            displayVehicles(vehicles);
        }
    }


    public void processGetAllVehiclesRequest() {
        ArrayList<Vehicle> vehicles = dealership.getVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            displayVehicles(vehicles);
        }
    }


    public void processAddVehicleRequest() {
        System.out.print("Enter VIN: ");
        while (!read.hasNextInt()) {
            System.out.println("Invalid input. VIN must be a number.");
            System.out.print("Enter VIN: ");
            read.next();
        }
        int vin = read.nextInt();

        System.out.print("Enter year: ");
        while (!read.hasNextInt()) {
            System.out.println("Invalid input. Year must be a number.");
            System.out.print("Enter year: ");
            read.next();
        }
        int year = read.nextInt();
        read.nextLine();

        System.out.print("Enter make: ");
        String make = read.nextLine();

        System.out.print("Enter model: ");
        String model = read.nextLine();

        System.out.print("Enter vehicle type: ");
        String vehicleType = read.nextLine();

        System.out.print("Enter color: ");
        String color = read.nextLine();

        System.out.print("Enter odometer: ");
        while (!read.hasNextInt()) {
            System.out.println("Invalid input. Odometer must be a number.");
            System.out.print("Enter odometer: ");
            read.next();
        }
        int odometer = read.nextInt();

        System.out.print("Enter price: ");
        while (!read.hasNextDouble()) {
            System.out.println("Invalid input. Price must be a number.");
            System.out.print("Enter price: ");
            read.next();
        }
        double price = read.nextDouble();

        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(newVehicle);
        System.out.println("Vehicle added successfully.");
    }


    public void processRemoveVehicleRequest() {
        processGetAllVehiclesRequest();
        System.out.print("Enter the VIN of the vehicle to remove: ");
        while (!read.hasNextInt()) {
            System.out.println("Invalid input. VIN must be a number.");
            System.out.print("Enter the VIN of the vehicle to remove: ");
            read.next();
        }
        int vin = read.nextInt();
        read.nextLine();

        boolean removed = dealership.removeVehicle(vin);
        if (removed) {
            System.out.println("Vehicle with VIN " + vin + " has been removed successfully.");
        } else {
            System.out.println("Vehicle with VIN " + vin + " not found.");
        }

        System.out.println("Vehicle list has been updated.");
        ArrayList<Vehicle> vehicles = dealership.getVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            displayVehicles(vehicles);
        }

        DealershipFileManager.saveDealership(dealership.getVehicles());
    }

    public static void loadingBar() {
        try {
            for (int i = 0; i <= 100; i += 20) {
                System.out.print("\rLoading: [" + "=".repeat(i / 5) + "] " + i + "%");
                TimeUnit.MILLISECONDS.sleep(100);
            }
            System.out.println("\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Loading interrupted.");
        }
    }
}

