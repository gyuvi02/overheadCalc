package org.gyula.overheadCalc.dao;

import org.gyula.overheadCalc.entity.A_tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TenantRepository extends JpaRepository<A_tenant, Integer> {
    // add a method to sort by last name
    public List<A_tenant> findAllByOrderByLastNameAsc();
}
