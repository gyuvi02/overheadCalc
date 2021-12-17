package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.dao.TenantDAO;
import org.gyula.overheadCalc.entity.A_tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TenantServiceImpl implements TenantService{

    private TenantDAO tenantDAO;

    @Autowired
    public TenantServiceImpl(TenantDAO theTenantDAO) {
        tenantDAO = theTenantDAO;
    }

    @Override
    @Transactional
    public List<A_tenant> findAll() {
        return tenantDAO.findAll();
    }

    @Override
    @Transactional
    public A_tenant findById(int id) {
        return tenantDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(A_tenant theTenant) {
        tenantDAO.save(theTenant);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        tenantDAO.deleteById(id);
    }
}
