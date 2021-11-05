package application.screen;

import application.dao.ProductDao;
import application.product.Product;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;

class SearchProductScreenTest {

    ProductDao pD = new ProductDao();
    SearchProductScreen sPS = new SearchProductScreen();

    @Test
    void byId() {
        int searchThisID = 5;

        Product p = sPS.byId((searchThisID));

        assert(p != null);
    }

    @Test
    void byAll() {

        assert(pD.findAllProducts() != null);

    }

}