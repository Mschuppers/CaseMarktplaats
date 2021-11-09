package application.screen;

import application.dao.ProductDao;
import application.dao.UserDao;
import application.exception.UserAbortedAction;
import application.exception.ZeroValue;
import application.product.Product;
import application.validator.Validator;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Scanner;

public class ManageProductScreen {
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
        System.out.println("Titel (max 40 karakters): ");
        enterName(p, sc.nextLine(), 40);
        System.out.println("Omschrijving (max 200 karakters): ");
        enterDescription(p, sc.nextLine(), 200);
        System.out.println("De gewenste prijs: ");
        enterPrice(p, sc.nextLine().replace(',', '.'));
        p.setDatePublished(LocalDate.now());
        p.setUser(userDao.findByName("Marco"));
        productDao.save(p);
    }

    public void updateProduct(Product p) throws ZeroValue, UserAbortedAction {
        System.out.println("Update welk onderdeel?");
        System.out.println("1) Naam ");
        System.out.println("2) Omschrijving");
        System.out.println("3) Prijs");
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                System.out.println("Titel (max 40 karakters): ");
                enterName(p, sc.nextLine(), 40);
                break;
            case 2:
                System.out.println("Omschrijving (max 200 karakters): ");
                enterDescription(p, sc.nextLine(), 200);
                break;
            case 3:
                System.out.println("De gewenste prijs (max 2 cijfers achter de komma): ");
                enterPrice(p, sc.nextLine().replace(',', '.'));
                break;
        }
        productDao.update(p);
    }

    private void enterPrice(Product p, String inputPrice) {
        if (vd.validatePrice(inputPrice)) {
            p.setPrice(Double.parseDouble(inputPrice.replace(',', '.')));
        } else {
            System.out.println("Prijs is niet akkoord, probeer het opnieuw");
            enterPrice(p, sc.nextLine().replace(',', '.'));
        }
    }

    private void enterDescription(Product p, String inputDescription, int max) {
        if (vd.validateText(inputDescription, max)) {
            p.setDescription(inputDescription);
        } else {
            System.out.println("Omschrijving is niet akkoord, probeer het opnieuw");
            enterDescription(p, sc.nextLine(), max);
        }
    }

    private void enterName(Product p, String nameInput, int max) {
        if (vd.validateText(nameInput, max)) {
            p.setName(nameInput);
        } else {
            System.out.println("Opgegeven titel is niet akkoord, probeer opnieuw");
            enterName(p, nameInput, max);
        }
    }
}

