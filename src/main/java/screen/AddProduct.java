package screen;

import dao.ProductDao;
import dao.UserDao;
import org.slf4j.Logger;
import products.Product;
import products.User;
import sysFiles.Validator;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Scanner;


public class AddProduct {
    @Inject
    private Logger log;

    @Inject
    private Scanner sc;

    @Inject
    private Validator vd;

    @Inject
    private UserDao userDao;

    @Inject
    private ProductDao productDao;

    public void insertProduct() {
        Product p = new Product();
        try {
            System.out.println("Een nieuw product toevoegen");
            System.out.println("Titel: ");
            p.setName(sc.nextLine());
            System.out.println("Omschrijving: ");
            p.setDescription(sc.nextLine());
            System.out.println("De gewenste prijs: ");
            p.setPrice(Double.parseDouble(vd.validatePrice()));
            p.setDatePublished(LocalDate.now());
            User marco = userDao.findByName("Marco");
            p.setUser(marco);
            productDao.save(p);
        } catch (NumberFormatException e) {
            System.out.println("An Error has occurred, please try again");
        }
    }


}

