package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.entity.Authorities;

public interface AuthoritiesService {

     Authorities findByUserName(String userName);

     void save(Authorities theAuthority);

}
