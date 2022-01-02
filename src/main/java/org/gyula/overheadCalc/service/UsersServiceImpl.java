package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.entity.Users;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class UsersServiceImpl implements UsersService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Users> findAll() {
        Query q = em.createQuery("from Users ");
        List<Users> result = q.getResultList();
        return result;
    }

    @Override
    public Users findByUserName(String userName) {
        try {
            Query q = em.createQuery("from Users where username = :tempUser ");
            q.setParameter("tempUser", userName);
            List<Users> users = q.getResultList();
            return users.get(0);
        } catch (Exception e) {
            e.getCause();
            System.out.println("Did not find the user called - " + userName);
            return null;
        }
    }

    @Override
    public void save(Users theUser) {
        em.merge(theUser);
    }

    @Override
    public void deleteByUserName(String userName) {
        em.remove(findByUserName(userName));
    }
}
