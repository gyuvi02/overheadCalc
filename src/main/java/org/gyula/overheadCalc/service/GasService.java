package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.entity.A_flat;
import org.gyula.overheadCalc.entity.A_gas_meter;

import java.util.List;

public interface GasService {

    List<A_gas_meter> findAllFlat(int id);

    A_gas_meter findLatest(int id);

    A_gas_meter findBeforeLatest(int id);

    void save(A_gas_meter theGas);

}
