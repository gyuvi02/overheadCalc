package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.dao.TenantRepository;
import org.gyula.overheadCalc.entity.A_tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TenantServiceImpl implements TenantService{

//    private TenantDAO tenantDAO;
//
//    @Autowired
//    public TenantServiceImpl(TenantDAO theTenantDAO) {
//        tenantDAO = theTenantDAO;
//    }
    TenantRepository tenantRepository;

    @Autowired
    public TenantServiceImpl(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public List<A_tenant> findAll() {
        return tenantRepository.findAll();
    }

    @Override
    public A_tenant findById(int id) {
        Optional<A_tenant> result = tenantRepository.findById(id);

        A_tenant a_tenant = null;

        if (result.isPresent()) {
            a_tenant = result.get();
        }
        else {
            // we didn't find the tenant
            throw new RuntimeException("Did not find tenant id - " + id);
        }
        return a_tenant;
    }

    @Override
    public void save(A_tenant theTenant) {
        tenantRepository.save(theTenant);
    }

    @Override
    public void deleteById(int id) {
        tenantRepository.deleteById(id);
    }

    // I need a list of the
    @Override
    public List<Integer> tenantIdList() {
        List<Integer> tenantIds = new ArrayList<>();
        for (A_tenant tenant : tenantRepository.findAll()) {
            tenantIds.add(tenant.getId());
        }
        return tenantIds;
    }
}
