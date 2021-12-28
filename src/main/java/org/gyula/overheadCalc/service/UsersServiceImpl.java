package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.entity.Users;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class UsersServiceImpl implements UsersService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Users> findAll() {
        return null;
    }

    @Override
    public Users findByUserName(String userName) {
        return null;
    }

    @Override
    public void save(Users theUser) {

    }

    @Override
    public void deleteByUserName(String userName) {

    }

    @Override
    public void update(String userName) {

    }
}
