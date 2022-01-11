package org.gyula.overheadCalc.util;

import java.util.Date;

public class CurrentData {

    private Date date;

    private double gasMeterLatest;
    private double gasMeterNow;
    private double gasPrice;
    private double gasBasicPrice;

    private double electricityMeterLatest;
    private double electricityMeterNow;
    private double electricityPrice;
    private double electricityBasicPrice;

    private double waterMeterLatest;
    private double waterMeterNow;
    private double waterPrice;
    private double waterBasicPrice;

    private int rent;
    private int associate_fee;

    private double finalSum;

    public CurrentData(Date date, double gasMeterLatest, double gasMeterNow, double gasPrice, double gasBasicPrice,
                       double electricityMeterLatest, double electricityMeterNow, double electricityPrice,
                       double electricityBasicPrice, double waterMeterLatest, double waterMeterNow, double waterPrice,
                       double waterBasicPrice, int rent, int associate_fee) {
        this.date = date;
        this.gasMeterLatest = gasMeterLatest;
        this.gasMeterNow = gasMeterNow;
        this.gasPrice = gasPrice;
        this.gasBasicPrice = gasBasicPrice;
        this.electricityMeterLatest = electricityMeterLatest;
        this.electricityMeterNow = electricityMeterNow;
        this.electricityPrice = electricityPrice;
        this.electricityBasicPrice = electricityBasicPrice;
        this.waterMeterLatest = waterMeterLatest;
        this.waterMeterNow = waterMeterNow;
        this.waterPrice = waterPrice;
        this.waterBasicPrice = waterBasicPrice;
        this.rent = rent;
        this.associate_fee = associate_fee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getGasMeterLatest() {
        return gasMeterLatest;
    }

    public void setGasMeterLatest(double gasMeterLatest) {
        this.gasMeterLatest = gasMeterLatest;
    }

    public double getGasMeterNow() {
        return gasMeterNow;
    }

    public void setGasMeterNow(double gasMeterNow) {
        this.gasMeterNow = gasMeterNow;
    }

    public double getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(double gasPrice) {
        this.gasPrice = gasPrice;
    }

    public double getGasBasicPrice() {
        return gasBasicPrice;
    }

    public void setGasBasicPrice(double gasBasicPrice) {
        this.gasBasicPrice = gasBasicPrice;
    }

    public double getElectricityMeterLatest() {
        return electricityMeterLatest;
    }

    public void setElectricityMeterLatest(double electricityMeterLatest) {
        this.electricityMeterLatest = electricityMeterLatest;
    }

    public double getElectricityMeterNow() {
        return electricityMeterNow;
    }

    public void setElectricityMeterNow(double electricityMeterNow) {
        this.electricityMeterNow = electricityMeterNow;
    }

    public double getElectricityPrice() {
        return electricityPrice;
    }

    public void setElectricityPrice(double electricityPrice) {
        this.electricityPrice = electricityPrice;
    }

    public double getElectricityBasicPrice() {
        return electricityBasicPrice;
    }

    public void setElectricityBasicPrice(double electricityBasicPrice) {
        this.electricityBasicPrice = electricityBasicPrice;
    }

    public double getWaterMeterLatest() {
        return waterMeterLatest;
    }

    public void setWaterMeterLatest(double waterMeterLatest) {
        this.waterMeterLatest = waterMeterLatest;
    }

    public double getWaterMeterNow() {
        return waterMeterNow;
    }

    public void setWaterMeterNow(double waterMeterNow) {
        this.waterMeterNow = waterMeterNow;
    }

    public double getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(double waterPrice) {
        this.waterPrice = waterPrice;
    }

    public double getWaterBasicPrice() {
        return waterBasicPrice;
    }

    public void setWaterBasicPrice(double waterBasicPrice) {
        this.waterBasicPrice = waterBasicPrice;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getAssociate_fee() {
        return associate_fee;
    }

    public void setAssociate_fee(int associate_fee) {
        this.associate_fee = associate_fee;
    }

    public double getFinalSum() {
        return finalSum;
    }

    public void setFinalSum(double finalSum) {
        this.finalSum = finalSum;
    }
}
