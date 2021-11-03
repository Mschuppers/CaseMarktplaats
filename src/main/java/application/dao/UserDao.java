package application.dao;

import application.products.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class UserDao {

    @Inject
    private EntityManager em;

    public User findByName(String name) {
        return em.createQuery("SELECT u FROM User u WHERE u.name = :eennaam", User.class)
                .setParameter("eennaam", name)
                .getSingleResult();
    }

}
