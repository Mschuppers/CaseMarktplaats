package application;

import application.dao.ProductDao;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.Logger;
import application.screen.AddProductScreen;

import javax.inject.Inject;
import java.util.Scanner;


public class App {
    @Inject
    private Logger log;

    @Inject
    private AddProductScreen addProduct;

    @Inject
    ProductDao PDao;

    @Inject
    private Scanner sc;


    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize();
        App app = weldContainer.select(App.class).get();

        app.start();
    }


    boolean start() {

        while (true) {
            log.info("Welcome to the Product menu");
            System.out.println("Choose option");
            System.out.println("1) Choose find 1 product by ID");
            System.out.println("2) Choose find all available products");
            System.out.println("3) Add a new product");
            System.out.println("4) Update an excisting product");
            System.out.println("5) Remove a product");


            int input = Integer.parseInt(sc.nextLine());
            switch (input) {
                case 1:
                System.out.println("Find which id?");
                PDao.findProduct(Integer.parseInt(sc.nextLine()));
                break;
                case 2:
//                dao.findAllProducts();
//                List<Product> all = dao.findAllProducts();
//                all.forEach(System.out::println);
                    break;
                case 3:
                    addProduct.insertProduct();
                    break;
                case 4:
//                int pId = sc.nextInt();
//                System.out.println("Update which id?");
//                dao.updateProduct(dao.findProduct(pId));
//                break;
                case 5:
//                System.out.println("Delete which id?");
//                id = sc.nextInt();
//                Product x = dao.findProduct(id);
//                dao.deleteProduct(x);
//                break;
                case 0:
                    System.out.println("Exiting application");
                    return false;
                default:
                    System.out.println("default");
            }

        }
    }
}

