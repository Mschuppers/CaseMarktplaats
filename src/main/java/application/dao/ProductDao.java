package application.dao;

import application.product.Product;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;


public class ProductDao {

    @Inject
    private EntityManager em;

    @Inject
    private Logger logger;


    public void runnableTransaction(Runnable subject) {
        em.getTransaction().begin();
        subject.run();
        em.getTransaction().commit();
    }


    public void save(Product p) {
        runnableTransaction(() -> em.persist(p));
    }

    public void update(Product p) {
        runnableTransaction(() -> em.merge(p));
    }


    public void deleteProduct(Product p) {
        logger.info(Level.DEBUG + " Deleting " + p);
        runnableTransaction(() -> em.remove(p));
    }

    public Product findProduct(int id) {
        Product p = em.find(Product.class, id);
        logger.info(Level.DEBUG + " Finding " + p);
        return p;
    }

    public Product findUserProduct(int id, int user_id) {
        Query q = em.createNamedQuery("product.FindOneUserProduct", Product.class);
        q.setParameter("id", id);
        q.setParameter("user_id", user_id);
        Product p = (Product) q.getSingleResult();
        return p;
    }


    public List<Product> findAllProducts() {
        var query = em.createNamedQuery("product.FindAll", Product.class);
        logger.info(Level.INFO + "Returning all products");
        return query.getResultList();
    }
}



