package org.gyula.overheadCalc.dao;

import org.gyula.overheadCalc.entity.A_flat;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FlatRepository extends JpaRepository<A_flat, Integer> {
}
