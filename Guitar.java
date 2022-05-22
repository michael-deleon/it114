package com.example.thisproject1;

public class Guitar{

    public static final int SIX_STRINGS = 50;
    public static final int SEVEN_STRINGS = 75;
    public static final int EIGHT_STRINGS = 100;
    public static final double PERCENT = 0.015;


    private String model;
    private String serialNumber;
    private int numberOfStrings;
    private int numberOfFrets;
    private double scaleLength;
    private String fingerboard;
    private double basePrice;
    private String img;



    public Guitar (String model, String serialNumber,int numberOfStrings, int numberOfFrets, double scaleLength, String fingerboard, double basePrice, String img)
    {
        this.model = model;
        this.serialNumber = serialNumber;
        this.numberOfStrings = numberOfStrings;
        this.numberOfFrets = numberOfFrets;
        this.scaleLength = scaleLength;
        this.fingerboard = fingerboard;
        this.basePrice = basePrice;
        this.img = img;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getNumberOfStrings() {
        return numberOfStrings;
    }

    public void setNumberOfStrings(int numberOfStrings) {
        this.numberOfStrings = numberOfStrings;
    }

    public int getNumberOfFrets() {
        return numberOfFrets;
    }

    public void setNumberOfFrets(int numberOfFrets) {
        this.numberOfFrets = numberOfFrets;
    }

    public double getScaleLength() {
        return scaleLength;
    }

    public void setScaleLength(double scaleLength) {
        this.scaleLength = scaleLength;
    }

    public String getFingerboard() {
        return fingerboard;
    }
    public void setFingerboard(String fingerboard){
        this.fingerboard = fingerboard;
    }

    public double getBasePrice() {
        return basePrice;
    }
    public void setBasePrice(double basePrice){
        this.basePrice = basePrice;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img){
        this.img = img;
    }

    public double getSetupCost(){
        if (this.getNumberOfStrings() == 6){
            return SIX_STRINGS + (this.basePrice * PERCENT);
        }
        if (this.getNumberOfStrings() == 7){
            return SEVEN_STRINGS + (this.basePrice * PERCENT);
        }
        if (this.getNumberOfStrings() == 8){
            return EIGHT_STRINGS + (this.basePrice * PERCENT);
        }
        else{
            return 0.0;
        }
    }
}
