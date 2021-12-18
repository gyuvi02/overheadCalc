package org.gyula.overheadCalc.dao;

import org.gyula.overheadCalc.entity.A_tenant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TenantRepository extends JpaRepository<A_tenant, Integer> {

}
