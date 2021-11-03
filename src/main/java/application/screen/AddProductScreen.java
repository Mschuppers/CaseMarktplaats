package application.screen;

import application.Exceptions.UserAbortedAction;
import application.products.Product;
import application.products.User;
import application.Exceptions.ZeroValue;
import application.dao.ProductDao;
import application.dao.UserDao;
import org.slf4j.Logger;
import application.sysFiles.Validator;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;


public class AddProductScreen {
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
            p.setPrice(Double.parseDouble(EnterPrice()));

            p.setDatePublished(LocalDate.now());

            User marco = userDao.findByName("Marco");
            p.setUser(marco);

            productDao.save(p);

        } catch (ZeroValue e){
            log.info(e.ValueEqualsZero());
        } catch (UserAbortedAction e) {
            log.info(e.ActionAbortedByUser());
        } catch (InputMismatchException e) {
            log.info(e.getMessage());
        }
    }

    private String EnterPrice() throws ZeroValue, UserAbortedAction, InputMismatchException {
        return vd.validatePrice(sc.nextLine().replace(",","."));

    }
}

