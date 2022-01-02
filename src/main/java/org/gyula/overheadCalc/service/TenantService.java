package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.entity.A_tenant;

import java.util.List;

public interface TenantService {

    List<A_tenant> findAll();

    A_tenant findById(int id);

    void save(A_tenant theTenant);

    void deleteById(int id);

    List<Integer> tenantIdList();

}
