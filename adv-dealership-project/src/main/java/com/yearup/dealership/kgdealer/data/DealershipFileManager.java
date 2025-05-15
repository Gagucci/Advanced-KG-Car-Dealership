package com.yearup.dealership.kgdealer.data;

import com.yearup.dealership.kgdealer.model.Vehicle;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {

    public static ArrayList<Vehicle> getDealership() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/inventory.csv"))) {
            String line;
            boolean isHeader = true;

            while ((line = bufferedReader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip the header
                    continue;
                }
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines
                }

                String[] column = line.split("\\|");
                if (column.length != 8) {
                    System.out.println("Invalid line format at line: " + line);
                    continue;
                }

                int vin = Integer.parseInt(column[0]);
                int year = Integer.parseInt(column[1]);
                String make = column[2];
                String model = column[3];
                String vehicleType = column[4];
                String color = column[5];
                int odometer = Integer.parseInt(column[6]);
                double price = Double.parseDouble(column[7]);

                Vehicle v = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                vehicles.add(v);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return vehicles;
    }

    public static void saveDealership(ArrayList<Vehicle> vehicles) {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/inventory.csv"));
            // Write the header
            bufferedWriter.write("vin|year|make|model|vehicleType|color|odometer|price");
            bufferedWriter.newLine();

            for (Vehicle vehicle : vehicles) {
                bufferedWriter.write(vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|" + vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer() + "|" + vehicle.getPrice());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}