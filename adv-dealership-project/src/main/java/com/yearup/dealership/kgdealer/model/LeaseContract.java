package com.yearup.dealership.kgdealer.model;

public class LeaseContract extends Contract {
    private double endOfLeaseValue = getVehicleSold().getPrice() * 0.5;
    private double leaseFee = getVehicleSold().getPrice() * 0.07;

    public double getEndOfLeaseValue() { return endOfLeaseValue; }
    public double getLeaseFee() { return leaseFee; }

    public void setEndOfLeaseValue(double endOfLeaseValue) { this.endOfLeaseValue = endOfLeaseValue; }
    public void setLeaseFee(double leaseFee) { this.leaseFee = leaseFee; }

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double totalPrice, double monthlyPayment, double endOfLeaseValue, double leaseFee) {
        super(date, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.endOfLeaseValue = endOfLeaseValue;
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
        // Calculate the total price of the lease contract
        double price = getVehicleSold().getPrice();
        double tax = price * 0.05; // Assuming a sales tax of 5%
        return (price - endOfLeaseValue) + tax + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        // Calculate the monthly payment for the lease contract
        double price = getVehicleSold().getPrice();
        double tax = price * 0.05; // Assuming a sales tax of 5%
        double totalLeaseCost = (price - endOfLeaseValue) + tax + leaseFee;
        return totalLeaseCost / 36; // Assuming a 3-year lease
    }
}
