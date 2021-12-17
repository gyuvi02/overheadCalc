package org.gyula.overheadCalc.dao;

import org.gyula.overheadCalc.entity.A_tenant;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TenantDAOImpl implements TenantDAO{

    // define field for EntityManager
    private EntityManager entityManager;

    // set up constructor injection
    @Autowired
    public TenantDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
//    @Transactional
    public List<A_tenant> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
//
//        // create a query
//        Query<A_tenant> theQuery = currentSession.createQuery("from A_tenant", A_tenant.class);
//
//        // get the result list
//        List<A_tenant> tenants = theQuery.getResultList();
//
//        return tenants;

        return currentSession.createQuery("from A_tenant", A_tenant.class).getResultList();
    }

    @Override
    public A_tenant findById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);

//        return currentSession.createQuery("from a_tenant where id = 1", A_tenant.class).getSingleResult();
        return currentSession.get(A_tenant.class, id);
    }

    @Override
    public void save(A_tenant theTenant) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(theTenant);
    }

    @Override
    public void deleteById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.delete(findById(id));
    }
}
