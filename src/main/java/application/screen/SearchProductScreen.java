package application.screen;

import application.dao.ProductDao;
import application.exception.ZeroValue;
import application.product.Product;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import javax.inject.Inject;
import java.util.List;
import java.util.Scanner;

public class SearchProductScreen {

    @Inject
    private ProductDao productDao;

    @Inject
    private Logger logger;

    @Inject
    private Scanner sc;

    public Product byId(int id) {
        try {
            Product p = productDao.findProduct(id);
            if (p != null) {
                logger.debug(Level.DEBUG + " Found the id submitted: " + id);
                return p;
            } else {
                throw new ZeroValue();
            }

        } catch (ZeroValue zeroValue) {
            logger.debug(Level.DEBUG + "Id niet gvonden");
        }
        System.out.println("ID niet herkend, probeer het opnieuw");
        return byId(Integer.parseInt(sc.nextLine()));
    }

    public void byAll() {
        productDao.findAllProducts();
        List<Product> all = productDao.findAllProducts();
        all.forEach(System.out::println);
    }
}

