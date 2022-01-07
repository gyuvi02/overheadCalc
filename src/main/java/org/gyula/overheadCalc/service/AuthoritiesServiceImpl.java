package org.gyula.overheadCalc.service;

import org.gyula.overheadCalc.entity.Authorities;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
@Transactional
public class AuthoritiesServiceImpl implements AuthoritiesService{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Authorities findByUserName(String userName) {
        try {
            Query q = em.createQuery("from Authorities where username = :tempUser ");
            q.setParameter("tempUser", userName);
            Authorities authorities = (Authorities) q.getSingleResult();
            return authorities;
        } catch (Exception e) {
            e.getCause();
            System.out.println("Did not find the user called - " + userName);
            return null;
        }
    }

    @Override
    public void save(Authorities theAuthority) {
        em.merge(theAuthority);
    }
}
