package org.gyula.overheadCalc.util;

import lombok.Getter;
import lombok.Setter;
import org.gyula.overheadCalc.entity.A_electricity_meter;
import org.gyula.overheadCalc.entity.A_flat;
import org.gyula.overheadCalc.entity.A_gas_meter;
import org.gyula.overheadCalc.entity.A_water_meter;
import org.gyula.overheadCalc.service.FlatService;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CurrentData {

    FlatService flatService;

    private LocalDateTime date = LocalDateTime.now();

    private String tenantFirstName;
    private String tenantLastName;

    private double gasMeterLatest;
    private double gasMeterNow;
    private double gasUnitPrice;
    private double gasBasicPrice;

    private double electricityMeterLatest;
    private double electricityMeterNow;
    private double electricityUnitPrice;
    private double electricityBasicPrice;

    private double waterMeterLatest;
    private double waterMeterNow;
    private double waterUnitPrice;
    private double waterBasicPrice;

    private int rent;
    private int associateFee;

    private double finalSum;

    public CurrentData() {
    }

    public CurrentData(String tenantFirstName, String tenantLastName, LocalDateTime date, double gasMeterLatest, double gasMeterNow, double gasUnitPrice, double gasBasicPrice,
                       double electricityMeterLatest, double electricityMeterNow, double electricityUnitPrice,
                       double electricityBasicPrice, double waterMeterLatest, double waterMeterNow, double waterUnitPrice,
                       double waterBasicPrice, int rent, int associateFee) {
        this.tenantFirstName = tenantFirstName;
        this.tenantLastName = tenantLastName;
        this.date = date;
        this.gasMeterLatest = gasMeterLatest;
        this.gasMeterNow = gasMeterNow;
        this.gasUnitPrice = gasUnitPrice;
        this.gasBasicPrice = gasBasicPrice;
        this.electricityMeterLatest = electricityMeterLatest;
        this.electricityMeterNow = electricityMeterNow;
        this.electricityUnitPrice = electricityUnitPrice;
        this.electricityBasicPrice = electricityBasicPrice;
        this.waterMeterLatest = waterMeterLatest;
        this.waterMeterNow = waterMeterNow;
        this.waterUnitPrice = waterUnitPrice;
        this.waterBasicPrice = waterBasicPrice;
        this.rent = rent;
        this.associateFee = associateFee;
    }

    public CurrentData createData(A_flat flat) {
        double gasMeterLast = 0;
        double electricityMeterLast = 0;
        double waterMeterLast = 0;

        List<A_gas_meter> gasMeter = flat.getGas_meters();
        List<A_electricity_meter> electricityMeter = flat.getElectricity_meters();
        List<A_water_meter> waterMeter = flat.getWater_meters();

        tenantFirstName = flat.getTheTenant().getFirstName();
        tenantLastName = flat.getTheTenant().getLastName();
        // gas data
        if (gasMeter.size() < 1) {
            gasMeterLast = 0;
        } else {
            gasMeterLast = gasMeter.get(gasMeter.size() - 1).getGas_meter();
        }
        int gasBasicPrice = flat.getGasBasicPrice();
        double gasUnitPrice = flat.getGasUnitPrice();

        // electricity data
        if (electricityMeter.size() < 1) {
            electricityMeterLast = 0;
        } else {
            electricityMeterLast = electricityMeter.get(gasMeter.size() - 1).getElectricity_meter();
        }
        int electricityBasicPrice = flat.getElectricityBasicPrice();
        double electricityUnitPrice = flat.getElectricityUnitPrice();

        // water data
        if (waterMeter.size() < 1) {
            waterMeterLast = 0;
        } else {
            waterMeterLast = waterMeter.get(gasMeter.size() - 1).getWater_meter();
        }
        int waterBasicPrice = flat.getWaterBasicPrice();
        double waterUnitPrice = flat.getWaterUnitPrice();

        int rent = flat.getRent();
        int associateFee = flat.getAssociateFee();

//        for (A_water_meter emeter : flat.getWater_meters()) {
//            System.out.println(emeter.getWater_meter());
//        }


        return null;
    }


}
