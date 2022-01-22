package org.gyula.overheadCalc.util;

import lombok.Getter;
import lombok.Setter;
import org.gyula.overheadCalc.entity.A_flat;
import org.gyula.overheadCalc.entity.A_gas_meter;
import org.gyula.overheadCalc.service.FlatService;

import java.util.List;

@Getter
@Setter
public class OverheadsRecording {

    FlatService flatService;

    private double gasMeterLatest;
    private double gasMeterNow;

    private double electricityMeterLatest;
    private double electricityMeterNow;

    private double waterMeterLatest;
    private double waterMeterNow;

    public OverheadsRecording(FlatService flatService, double gasMeterLatest, double gasMeterNow,
                              double electricityMeterLatest, double electricityMeterNow, double waterMeterLatest,
                              double waterMeterNow) {
        this.flatService = flatService;
        this.gasMeterLatest = gasMeterLatest;
        this.gasMeterNow = gasMeterNow;
        this.electricityMeterLatest = electricityMeterLatest;
        this.electricityMeterNow = electricityMeterNow;
        this.waterMeterLatest = waterMeterLatest;
        this.waterMeterNow = waterMeterNow;
    }

    public void recordGasData(A_flat flat) {
        double gasMeterLast = 0;
        List<A_gas_meter> gasMeter = flat.getGas_meters();

        if (gasMeter.size() < 1) {
            gasMeterLast = 0;
        } else {
            gasMeterLast = gasMeter.get(gasMeter.size() - 1).getGas_meter();
        }
        int gasBasicPrice = flat.getGasBasicPrice();
        double gasUnitPrice = flat.getGasUnitPrice();
        }


}
