package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.dao.FlatRepository;
import org.gyula.overheadCalc.dao.TenantRepository;
import org.gyula.overheadCalc.entity.A_flat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlatServiceImpl implements FlatService{

    FlatRepository flatRepository;
    TenantRepository tenantRepository;


    @Autowired
    public FlatServiceImpl(FlatRepository flatRepository) {
        this.flatRepository = flatRepository;
    }

    @Override
    public List<A_flat> findAll() {
        return flatRepository.findAll();
    }

    @Override
    public A_flat findById(int id) {
        Optional<A_flat> result = flatRepository.findById(id);

        A_flat a_flat = null;

        if (result.isPresent()) {
            a_flat = result.get();
        }
        else {
            // we didn't find the tenant
            throw new RuntimeException("Did not find flat id - " + id);
        }
        return a_flat;
    }

    @Override
    public void save(A_flat theFlat) {
        flatRepository.save(theFlat);
    }

    @Override
    public void deleteById(int id) {
        flatRepository.deleteById(id);

    }
}
