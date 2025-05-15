package com.yearup.dealership.kgdealer.model;

public class SalesContract extends Contract{
    private double SalesTax = 0.05;
    private double recordingFee = 100;
    private double processingFeeUnderThousand = 295;
    private double processingFeeOverThousand = 495;
    private boolean isFinancing;
    private double monthlyPayment;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold, double totalPrice, double monthlyPayment, double salesTax, double recordingFee, double processingFeeUnderThousand, double processingFeeOverThousand, boolean isFinancing, double monthlyPayment1) {
        super(date, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        SalesTax = salesTax;
        this.recordingFee = recordingFee;
        this.processingFeeUnderThousand = processingFeeUnderThousand;
        this.processingFeeOverThousand = processingFeeOverThousand;
        this.isFinancing = isFinancing;
        this.monthlyPayment = monthlyPayment1;
    }

    public double getSalesTax() { return SalesTax; }
    public double getRecordingFee() { return recordingFee; }
    public double getProcessingFeeUnderThousand() { return processingFeeUnderThousand; }
    public double getProcessingFeeOverThousand() { return processingFeeOverThousand; }
    public boolean isFinancing() { return isFinancing; }
    public double getMonthlyPayment1() { return monthlyPayment; }

    public void setSalesTax(double salesTax) { SalesTax = salesTax; }
    public void setRecordingFee(double recordingFee) { this.recordingFee = recordingFee; }
    public void setProcessingFeeUnderThousand(double processingFeeUnderThousand) { this.processingFeeUnderThousand = processingFeeUnderThousand; }
    public void setProcessingFeeOverThousand(double processingFeeOverThousand) { this.processingFeeOverThousand = processingFeeOverThousand; }
    public void setFinancing(boolean financing) { isFinancing = financing; }
    public void setMonthlyPayment(double monthlyPayment) { this.monthlyPayment = monthlyPayment; }


    @Override
    public double getTotalPrice() {
        //Calculate the total price of the vehicle sold
        double price = getVehicleSold().getPrice();
        double tax = price * SalesTax;
        double processingFee = price < 10000 ? processingFeeUnderThousand : processingFeeOverThousand;

        return price + tax + processingFee + recordingFee;
    }

    @Override
    public double getMonthlyPayment() {
        //All loans are at 4.25% for 48 months if the price is $10,000 or more
        //Otherwise they are at 5.25% for 24 month
        //If the customer is not financing, the monthly payment is 0
        if (isFinancing) {
            if (getTotalPrice() >= 10000) {
                monthlyPayment = (getTotalPrice() * 0.0425) / 48;
                return monthlyPayment;
            } else {
                monthlyPayment = (getTotalPrice() * 0.0525) / 24;
                return monthlyPayment;
            }
        } else {
            monthlyPayment = 0;
            return monthlyPayment;
        }

    }

}
