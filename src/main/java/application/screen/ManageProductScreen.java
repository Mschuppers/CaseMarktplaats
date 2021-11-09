package application.screen;

import application.dao.ProductDao;
import application.dao.UserDao;
import application.exception.UserAbortedAction;
import application.exception.ZeroValue;
import application.product.Product;
import application.product.User;
import application.validator.Validator;
import lombok.SneakyThrows;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManageProductScreen {
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


    @SneakyThrows
    public void insertProduct() throws ZeroValue, UserAbortedAction {
        Product p = new Product();

        System.out.println("Titel (max 40 karakters): ");
        p.setName(vd.validateText(sc.nextLine(), 41));
        System.out.println("Omschrijving (max 200 karakters): ");
        p.setDescription(vd.validateText(sc.nextLine(), 201));
        System.out.println("De gewenste prijs: ");
        p.setPrice(Double.parseDouble(enterPrice().replace(',', '.')));
        p.setDatePublished(LocalDate.now());
        User marco = userDao.findByName("Marco");
        p.setUser(marco);
        productDao.save(p);

    }

    public void updateProduct(Product p) throws ZeroValue, UserAbortedAction {

        System.out.println("Update welk onderdeel?");
        System.out.println("1) Naam ");
        System.out.println("2) Omschrijving");
        System.out.println("3) Prijs");
        int input = Integer.parseInt(sc.nextLine());
        switch (input) {
            case 1:
                System.out.println("Titel (max 40 karakters): ");
                p.setName(sc.nextLine());
                productDao.update(p);
                break;
            case 2:
                System.out.println("Omschrijving (max 200 karakters): ");
                p.setDescription(sc.nextLine());
                productDao.update(p);
                break;
            case 3:
                System.out.println("De gewenste prijs (max 2 cijfers achter de komma): ");
                p.setPrice(Double.parseDouble(enterPrice().replace(',', '.')));
                productDao.update(p);
                break;
        }



    }

    private String enterPrice() throws ZeroValue, UserAbortedAction, InputMismatchException {
        return vd.validatePrice(sc.nextLine().replace(",", "."));

    }
}

