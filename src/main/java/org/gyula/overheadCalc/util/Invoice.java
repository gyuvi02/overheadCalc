package org.gyula.overheadCalc.util;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.math3.util.Precision;
import org.gyula.overheadCalc.entity.A_electricity_meter;
import org.gyula.overheadCalc.entity.A_flat;
import org.gyula.overheadCalc.entity.A_gas_meter;
import org.gyula.overheadCalc.entity.A_water_meter;
import org.gyula.overheadCalc.service.FlatService;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Invoice {

    FlatService flatService;

    private LocalDateTime localDateTime = LocalDateTime.now();

    private String tenantFirstName;
    private String tenantLastName;
    private String address;

    private double gasMeterLatest;
    private double gasMeterBeforeLatest;
    private double gasConsumption;
    private double gasUnitPrice;
    private double gasToPay;
    private double gasBasicPrice;

    private double electricityMeterLatest;
    private double electricityMeterBeforeLatest;
    private double electricityConsumption;
    private double electricityUnitPrice;
    private double electricityToPay;
    private double electricityBasicPrice;

    private double waterMeterLatest;
    private double waterMeterBeforeLatest;
    private double waterConsumption;
    private double waterUnitPrice;
    private double waterToPay;
    private double waterBasicPrice;

    private int rent;
    private int associateFee;
    private double totalSum;


    public Invoice() {
    }

    public Invoice(String tenantFirstName, String tenantLastName, String address, double gasMeterLatest,
                   double gasMeterBeforeLatest, double gasConsumption, double gasUnitPrice, double gasToPay,
                   double gasBasicPrice, double electricityMeterLatest, double electricityMeterBeforeLatest,
                   double electricityConsumption, double electricityUnitPrice, double electricityToPay,
                   double electricityBasicPrice, double waterMeterLatest, double waterMeterBeforeLatest,
                   double waterConsumption, double waterUnitPrice, double waterToPay, double waterBasicPrice,
                   int rent, int associateFee, double totalSum) {
        this.localDateTime = LocalDateTime.now();
        this.tenantFirstName = tenantFirstName;
        this.tenantLastName = tenantLastName;
        this.address = address;
        this.gasMeterLatest = gasMeterLatest;
        this.gasMeterBeforeLatest = gasMeterBeforeLatest;
        this.gasConsumption = Precision.round(gasMeterLatest - gasMeterBeforeLatest, 2);
        this.gasUnitPrice = gasUnitPrice;
        this.gasToPay = Precision.round((gasMeterLatest - gasMeterBeforeLatest) * gasUnitPrice + gasBasicPrice, 0);
        this.gasBasicPrice = gasBasicPrice;
        this.electricityMeterLatest = electricityMeterLatest;
        this.electricityMeterBeforeLatest = electricityMeterBeforeLatest;
        this.electricityConsumption = Precision.round(electricityMeterLatest - electricityMeterBeforeLatest, 2);
        this.electricityUnitPrice = electricityUnitPrice;
        this.electricityToPay = Precision.round((electricityMeterLatest - electricityMeterBeforeLatest) * electricityUnitPrice + electricityBasicPrice, 0);
        this.electricityBasicPrice = electricityBasicPrice;
        this.waterMeterLatest = waterMeterLatest;
        this.waterMeterBeforeLatest = waterMeterBeforeLatest;
        this.waterConsumption = Precision.round(waterMeterLatest - waterMeterBeforeLatest, 2);
        this.waterUnitPrice = waterUnitPrice;
        this.waterToPay = Precision.round ((waterMeterLatest - waterMeterBeforeLatest) * waterUnitPrice + waterBasicPrice, 0);
        this.waterBasicPrice = waterBasicPrice;
        this.rent = rent;
        this.associateFee = associateFee;
        this.totalSum = Precision.round(rent + associateFee + this.gasToPay + this.electricityToPay + this.waterToPay, 0);
    }

    public Invoice createInvoiceData(A_flat flat) {
        double gasMeterLast = 0;
        double gasMeterBeforeLast = 0;
        double electricityMeterLast = 0;
        double electricityMeterBeforeLast = 0;
        double waterMeterLast = 0;
        double waterMeterBeforeLast = 0;

        List<A_gas_meter> gasMeter = flat.getGas_meters();
        List<A_electricity_meter> electricityMeter = flat.getElectricity_meters();
        List<A_water_meter> waterMeter = flat.getWater_meters();

        tenantFirstName = flat.getTheTenant().getFirstName();
        tenantLastName = flat.getTheTenant().getLastName();
        address = flat.getAddress();
        // gas data

        if (gasMeter.size() < 1) {
            gasMeterLast = 0;
        } else {
            gasMeterLast = gasMeter.get(gasMeter.size() - 1).getGas_meter();
            if (gasMeter.size() < 2) {
                gasMeterBeforeLast = 0;
            }else{
                gasMeterBeforeLast = gasMeter.get(gasMeter.size() - 2).getGas_meter();
            }
        }
        int gasBasicPrice = flat.getGasBasicPrice();
        double gasUnitPrice = flat.getGasUnitPrice();

        // electricity data
        if (electricityMeter.size() < 1) {
            electricityMeterLast = 0;
        } else {
            electricityMeterLast = electricityMeter.get(electricityMeter.size() - 1).getElectricity_meter();
            if (electricityMeter.size() < 2) {
                electricityMeterBeforeLast = 0;
            }else{
                electricityMeterBeforeLast = electricityMeter.get(electricityMeter.size() - 2).getElectricity_meter();
            }
        }
        int electricityBasicPrice = flat.getElectricityBasicPrice();
        double electricityUnitPrice = flat.getElectricityUnitPrice();

        // water data
        if (waterMeter.size() < 1) {
            waterMeterLast = 0;
        } else {
            waterMeterLast = waterMeter.get(waterMeter.size() - 1).getWater_meter();
            if (waterMeter.size() < 2) {
                waterMeterBeforeLast = 0;
            }else{
                waterMeterBeforeLast = waterMeter.get(waterMeter.size() - 2).getWater_meter();
            }

        }
        int waterBasicPrice = flat.getWaterBasicPrice();
        double waterUnitPrice = flat.getWaterUnitPrice();

        int rent = flat.getRent();
        int associateFee = flat.getAssociateFee();

        return new Invoice(tenantFirstName, tenantLastName, address, gasMeterLast, gasMeterBeforeLast, gasConsumption,
                gasUnitPrice, gasToPay, gasBasicPrice, electricityMeterLast, electricityMeterBeforeLast,
                electricityConsumption, electricityUnitPrice, electricityToPay, electricityBasicPrice, waterMeterLast,
                waterMeterBeforeLast, waterConsumption, waterUnitPrice, waterToPay, waterBasicPrice, rent, associateFee,
                totalSum);
    }


}
