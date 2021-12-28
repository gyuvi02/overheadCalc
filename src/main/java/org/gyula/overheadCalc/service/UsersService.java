package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.entity.Users;
import org.springframework.stereotype.Component;

import java.util.List;

public interface UsersService {

    List<Users> findAll();

    Users findByUserName(String userName);

    void save(Users theUser);

    void deleteByUserName(String userName);

    void update(String userName);

}
