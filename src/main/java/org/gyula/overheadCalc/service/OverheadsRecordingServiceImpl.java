package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.entity.A_electricity_meter;
import org.gyula.overheadCalc.entity.A_gas_meter;
import org.gyula.overheadCalc.entity.A_water_meter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional
public class OverheadsRecordingServiceImpl implements OverheadsRecordingService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<A_gas_meter> findAllGasByFlat() {
        return null;
    }

    @Override
    public List<A_electricity_meter> findAllElectricityByFlat() {
        return null;
    }

    @Override
    public List<A_water_meter> findAllWaterByFlat() {
        return null;
    }

    @Override
    public A_gas_meter findLatestGas() {
        return null;
    }

    @Override
    public A_electricity_meter findLatestElectricity() {
        return null;
    }

    @Override
    public A_water_meter findLatestWater() {
        return null;
    }

    @Override
    @Transactional
    public void saveActualGas(A_gas_meter gasMeter) {
        em.merge(gasMeter);
    }

    @Override
    public void saveActualElectricity(A_electricity_meter electricityMeter) {
        em.merge(electricityMeter);
    }

    @Override
    public void saveActualWater(A_water_meter waterMeter) {
        em.merge(waterMeter);
    }
}
