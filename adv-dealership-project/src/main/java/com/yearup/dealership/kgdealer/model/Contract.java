package com.yearup.dealership.kgdealer.model;

public abstract class Contract {
    private String Date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private double totalPrice;
    private double monthlyPayment;

    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double totalPrice, double monthlyPayment) {
        this.Date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    public Contract() {
    }

    public String getDate() { return Date; }
    public String getCustomerName() { return customerName; }
    public String getCustomerEmail() { return customerEmail; }
    public Vehicle getVehicleSold() { return vehicleSold; }

    public void setDate(String date) { this.Date = date; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    public void setVehicleSold(Vehicle vehicleSold) { this.vehicleSold = vehicleSold; }

    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

}

