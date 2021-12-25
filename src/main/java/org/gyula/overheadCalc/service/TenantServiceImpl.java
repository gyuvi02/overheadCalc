package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.entity.A_flat;
import org.gyula.overheadCalc.entity.A_tenant;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Component
public class TenantServiceImpl implements TenantService{

//    private TenantDAO tenantDAO;
//
//    @Autowired
//    public TenantServiceImpl(TenantDAO theTenantDAO) {
//        tenantDAO = theTenantDAO;
//    }

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<A_tenant> findAll() {
        Query q = em.createQuery("from A_tenant");
        List<A_tenant> result = q.getResultList();
        return result;
    }

    @Override
    public A_tenant findById(int id) {

        try {
            A_tenant a_tenant = em.find(A_tenant.class, id);
            return a_tenant;
        } catch (Exception e) {
            e.getCause();
            System.out.println("Did not find tenant id - " + id);
            return null;
        }
    }

//
//    @Override
//    public A_tenant findById(int id) {
//        Optional<A_tenant> result = tenantRepository.findById(id);
//
//        A_tenant a_tenant = null;
//
//        if (result.isPresent()) {
//            a_tenant = result.get();
//        }
//        else {
//            // we didn't find the tenant
//            throw new RuntimeException("Did not find tenant id - " + id);
//        }
//        return a_tenant;
//    }

    @Override
    @Transactional
    public void save(A_tenant theTenant) {
        em.merge(theTenant);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        em.remove(findById(id));
    }

    // I need a list of the
    @Override
    public List<Integer> tenantIdList() {
        List<Integer> tenantIds = new ArrayList<>();
        for (A_tenant tenant : findAll()) {
            tenantIds.add(tenant.getId());
        }
        return tenantIds;
    }

    @Override
    public void update(int id) {
        A_tenant theTenant = findById(id);

    }
}
