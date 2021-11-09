package application.screen;

import application.dao.ProductDao;
import application.product.Product;

import javax.inject.Inject;
import java.util.List;
import java.util.Scanner;

public class SearchProductScreen {

    @Inject
    private ProductDao productDao;

    @Inject
    private Scanner sc;

    public Product byId(int id) {
        try {
            Product p = productDao.findProduct(id);
            if (p != null) {
                return p;
            }
            throw new Exception();
        } catch (
                Exception e) {
            System.out.println("De invoer ging niet goed, probeer het nog een keer met een geldig ID");
            return byId(Integer.parseInt(sc.nextLine()));
        }
    }

    public void byAll() {
        productDao.findAllProducts();
        List<Product> all = productDao.findAllProducts();
        all.forEach(System.out::println);
    }
}

