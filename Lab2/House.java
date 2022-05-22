package com.example.lab2;

public class House {

    private String address;//street address (string)
    private String city;//city (string)
    private double assessedValue;//assessed value (double)
    private int year;//year built (int)
    private double tax;//property taxes (double)
    private int squareFootage;//square footage (int)

    public House (String address, String city,double assessedValue, int year, double tax, int squareFootage)
    {
        this.address = address;
        this.city = city;
        this.assessedValue = assessedValue;
        this.year = year;
        this.tax = tax;
        this.squareFootage = squareFootage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getAssessedValue() {
        return assessedValue;
    }

    public void setAssessedValue(double assessedValue) {
        this.assessedValue = assessedValue;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getSquareFootage() {
        return squareFootage;
    }
    public void setSquareFootage(int squareFootage){
        this.squareFootage = squareFootage;
    }

}
