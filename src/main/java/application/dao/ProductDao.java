package application.dao;

import application.product.Product;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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
        logger.debug(Level.DEBUG + " Deleting " + p);
        System.out.println("Product id: " + p.getId() + " is nu verwijderd");
        runnableTransaction(() -> em.remove(p));
    }

    public Product findProduct(int id) {
        Product p = em.find(Product.class, id);
        logger.debug(Level.DEBUG + " Finding " + p.getId());
        return p;
    }

    public List<Product> findAllProducts() {
        var query = em.createNamedQuery("product.FindAll", Product.class);
        return query.getResultList();
    }
}



