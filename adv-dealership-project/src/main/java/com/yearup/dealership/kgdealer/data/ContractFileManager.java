package com.yearup.dealership.kgdealer.data;

import com.yearup.dealership.kgdealer.model.Contract;
import com.yearup.dealership.kgdealer.model.LeaseContract;
import com.yearup.dealership.kgdealer.model.SalesContract;
import com.yearup.dealership.kgdealer.model.Vehicle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class ContractFileManager {

    public static ArrayList<Contract> readFromContractsCsv() {
        ArrayList<Contract> contracts = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/contracts.csv"))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("SALE") || line.contains("LEASE")) {
                    String[] column = line.split("\\|");
                    if (column[0].equalsIgnoreCase("SALE")) {
                        String date = column[1];
                        String customerName = column[2];
                        String customerEmail = column[3];
                        Vehicle vehicleSold = new Vehicle(
                                Integer.parseInt(column[4]), // VIN
                                Integer.parseInt(column[5]), // year
                                column[6], // make
                                column[7], // model
                                column[8], // type
                                column[9], // color
                                Integer.parseInt(column[10]), // mileage
                                Double.parseDouble(column[11]) // price
                        );
                        double totalPrice = Double.parseDouble(column[12]);
                        double monthlyPayment = Double.parseDouble(column[13]);

                    } else if (column[0].equalsIgnoreCase("LEASE")) {
                        LeaseContract leaseContract = new LeaseContract(
                                column[1], // date
                                column[2], // customer name
                                column[3], // customer email
                                Integer.parseInt(column[4]), // VIN
                                Integer.parseInt(column[5]), // year
                                column[6], // make
                                column[7], // model
                                column[8], // type
                                column[9], // color
                                Integer.parseInt(column[10]), // mileage
                                Double.parseDouble(column[11]), // price
                                Double.parseDouble(column[12]), // lease fee
                                Double.parseDouble(column[13]), // monthly payment
                                Double.parseDouble(column[14]) // total lease cost
                        );
                        contracts.add(leaseContract);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return contracts;
    }

}
