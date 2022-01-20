package org.gyula.overheadCalc.service;

import lombok.extern.slf4j.Slf4j;
import org.gyula.overheadCalc.entity.A_gas_meter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class GasServiceImpl implements GasService{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public List<A_gas_meter> findAllFlat(int id) {
        Query q = em.createQuery("from A_gas_meter where a_flat.id = :tempFlatId order by dateId");
        q.setParameter("tempFlatId", id );
        List<A_gas_meter> result = q.getResultList();
        return result;
    }

    @Override
    public A_gas_meter findLatest(int id) {
        Query q = em.createQuery("from A_gas_meter where a_flat.id = :tempFlatId order by dateId desc ");
        q.setParameter("tempFlatId", id );
        ArrayList<A_gas_meter> resultList = (ArrayList<A_gas_meter>) q.getResultList();
        A_gas_meter result = resultList.get(0);
        return result;
    }

    @Override
    public A_gas_meter findBeforeLatest(int id) {
        return null;
    }

    @Override
    @Transactional
    public void save(A_gas_meter theGas) {
        em.merge(theGas);
    }
}
