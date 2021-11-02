package dao;

import products.Product;

import javax.inject.Inject;
import javax.persistence.EntityManager;


public class ProductDao {

    @Inject
    private EntityManager em;


    public void save(Product p) {
        runnableTransaction(() -> em.persist(p));
    }

    public void runnableTransaction(Runnable subject) {
        em.getTransaction().begin();
        subject.run();
        em.getTransaction().commit();
    }


}

