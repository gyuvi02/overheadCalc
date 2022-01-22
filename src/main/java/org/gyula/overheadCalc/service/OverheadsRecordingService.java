package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.entity.A_electricity_meter;
import org.gyula.overheadCalc.entity.A_gas_meter;
import org.gyula.overheadCalc.entity.A_water_meter;

import java.util.List;

public interface OverheadsRecordingService {
    List<A_gas_meter> findAllGasByFlat();
    List<A_electricity_meter> findAllElectricityByFlat();
    List<A_water_meter> findAllWaterByFlat();

    A_gas_meter findLatestGas();
    A_electricity_meter findLatestElectricity();
    A_water_meter findLatestWater();

    void saveActualGas(A_gas_meter gasMeter);
    void saveActualElectricity(A_electricity_meter electricityMeter);
    void saveActualWater(A_water_meter waterMeter);

}
