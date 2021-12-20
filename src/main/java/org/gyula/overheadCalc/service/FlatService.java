package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.entity.A_flat;

import java.util.List;

public interface FlatService {

    List<A_flat> findAll();

    A_flat findById(int id);

    void save(A_flat theFlat);

    void deleteById(int id);
}
