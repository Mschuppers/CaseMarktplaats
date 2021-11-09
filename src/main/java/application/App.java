package application;

import application.dao.ProductDao;
import application.exception.UserAbortedAction;
import application.exception.ZeroValue;
import application.screen.ManageProductScreen;
import application.screen.SearchProductScreen;
import application.sysFiles.Header;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.InputMismatchException;
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
    @Inject
    private Header hd;

    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer weldContainer = weld.initialize();
        App app = weldContainer.select(App.class).get();

        app.start();
    }

    public boolean start() {
        hd.createHeader();
        while (true) {
            System.out.println("************Product Beheer************");
            System.out.println("*Kies uit en van de volgende opties  *");
            System.out.println("*1) Zoek product op ID               *");
            System.out.println("*2) Toon alle beschikbare producten  *");
            System.out.println("*3) Voeg een nieuw product toe       *");
            System.out.println("*4) Update een bestaand product      *");
            System.out.println("*5) Verwijder een product            *");
            System.out.println("**************************************");
            try {
                switch (Integer.parseInt(sc.nextLine())) {
                    case 1:
                        System.out.println("Welk Id zoek je?");
                        System.out.println(searchProduct.byId(Integer.parseInt(sc.nextLine())));
                        break;

                    case 2:
                        System.out.println("Alle beschikbare producten.");
                        searchProduct.byAll();
                        break;

                    case 3:
                        System.out.println("Een nieuw product toevoegen");
                        manageProduct.insertProduct();
                        break;

                    case 4:
                        System.out.println("Welk ID wordt geupdate?");
                        manageProduct.updateProduct(searchProduct.byId(Integer.parseInt(sc.nextLine())));
                        break;

                    case 5:
                        System.out.println("Welk ID moet worden verwijderd");
                        pDao.deleteProduct(searchProduct.byId(Integer.parseInt(sc.nextLine())));
                        break;
                    case 0:
                        System.out.println("Applicatie wordt afgesloten");
                        return false;
                    default:
                        System.out.println("Ongeldige invoer ontvangen");
                }
            } catch (ZeroValue e) {
                logger.info(e.ValueEqualsZero());
                System.out.println("Er is '0' opgegeven");
            } catch (UserAbortedAction e) {
                logger.info(e.ActionAbortedByUser());
                System.out.println("De actie wordt gestopt...");
            } catch (InputMismatchException e) {
                logger.info(e.getMessage());
                System.out.println("De opgegeven waarde klopt niet met het format wat gevraagd wordt");
            } catch (Exception e) {
                System.out.println("Het opgegeven ID bestaat niet, is het goede nummer ingevuld?");
                e.printStackTrace();
            }
        }
    }
}



