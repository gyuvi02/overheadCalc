package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.entity.A_flat;
import org.gyula.overheadCalc.entity.A_tenant;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class FlatServiceImpl implements FlatService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<A_flat> findAll() {
        Query q = em.createQuery("from A_flat ");
        List<A_flat> result = q.getResultList();
        return result;
    }

    @Override
    public A_flat findById(int id) {

        try {
            A_flat a_flat = em.find(A_flat.class, id);
            return a_flat;
        } catch (Exception e) {
            e.getCause();
            System.out.println("Did not find flat id - " + id);
            return null;
        }
    }

    @Override
    @Transactional
    public void save(A_flat theFlat) {
        em.merge(theFlat);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Query q = em.createQuery("delete from A_flat where id = :tempFlatId  ");
        q.setParameter("tempFlatId", id );
        q.executeUpdate();
//        em.remove(findById(id));
    }
}
