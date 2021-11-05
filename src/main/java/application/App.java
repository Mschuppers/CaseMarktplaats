package application;

import application.dao.ProductDao;
import application.product.Product;
import application.screen.ManageProductScreen;
import application.screen.SearchProductScreen;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import javax.inject.Inject;
import java.util.Scanner;


public class App {

    @Inject
    private ManageProductScreen manageProduct;
    @Inject
    private SearchProductScreen searchProduct;
    @Inject
    private ProductDao pDao;
    @Inject
    private Scanner sc;
    @Inject
    private Logger logger;


    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize();
        App app = weldContainer.select(App.class).get();

        app.start();
    }


    boolean start() {

        while (true) {
            System.out.println("Welcome to the Product menu");
            System.out.println("Choose option");
            System.out.println("1) Choose find 1 product by ID");
            System.out.println("2) Choose find all available products");
            System.out.println("3) Add a new product");
            System.out.println("4) Update an excisting product");
            System.out.println("5) Remove a product");


            int input = Integer.parseInt(sc.nextLine());
            switch (input) {
                case 1:
                    try {
                        System.out.println("Find which id?");
                        System.out.println(searchProduct.byId(Integer.parseInt(sc.nextLine())));
                        break;
                    } catch (Exception e) {
                        System.out.println("Er ging iets mis, mogelijk is het ID niet correct");
                        logger.debug(Level.DEBUG + "ID was not found");
                    }
                case 2:
                    System.out.println("Listing all available products:");
                    searchProduct.byAll();
                    break;

                case 3:
                    manageProduct.insertProduct();
                    break;

                case 4:
                    System.out.println("Update which id?");
                    manageProduct.updateProduct(pDao.findProduct(Integer.parseInt(sc.nextLine())));
                    break;

                case 5:
                    System.out.println("Delete which id?");
                    try {
                        pDao.deleteProduct((Product) pDao.findUserProduct(Integer.parseInt(sc.nextLine()), 1));
                    } catch (Exception e) {
                        System.out.println("Er ging iets mis, mogelijk is het ID niet correct");
                        logger.debug(Level.DEBUG + "ID was not found");
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    System.out.println("Applicatie wordt afgesloten");
                    return false;
                default:
                    System.out.println("default");
            }

        }
    }
}

