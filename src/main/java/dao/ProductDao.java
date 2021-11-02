package dao;

import lombok.var;
import org.slf4j.Logger;
import products.Product;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class ProductDao {
    @Inject
    private Logger log;

    @Inject
    private Scanner sc;

    @Inject
    private EntityManager em;

    @Inject
    private Product p;

    public void runnableTransaction(Runnable subject) {
            em.getTransaction().begin();
            em.merge(subject);
            em.getTransaction().commit();
    }

    public Product findProduct(int id) {
        Product p = em.find(Product.class, id);
        return p;
    }

    public List<Product> findAllProducts() {
        var query = em.createNamedQuery("Product.FindAll", Product.class);
        return query.getResultList();
    }

    public void insertProduct() {
        System.out.println("Een nieuw product toevoegen");
        System.out.println("Titel: ");
        p.setName(sc.nextLine());
        System.out.println("Omschrijving: ");
        p.setDescription(sc.nextLine());
        System.out.println("De gewenste prijs: ");
        p.setPrice(Double.parseDouble(valueOf(sc.nextLine())));
        p.setDatePublished(LocalDate.now());
        runnableTransaction(() -> em.persist(p));
    }

    public void deleteProduct(Product p) {
        runnableTransaction(() -> em.remove(p));
    }

    public void updateProduct(Product p) {
        System.out.println("Een bestaand product bewerken");
        System.out.println("Oude titel: " + p.getName());
        System.out.println("Bewerkt titel Y/N");

        if (sc.next().equalsIgnoreCase("Y")) {
            System.out.println("Nieuwe titel: ");
            p.setName(sc.nextLine());
        }
        if (!sc.next().equalsIgnoreCase("N")) {
            log.error("Geen Titel geldige input ( Y of N ) ontvangen");
        }

        System.out.println("Oude omschrijving: " + p.getDescription());
        System.out.println("Bewerkt titel Y/N");

        if (sc.next().equalsIgnoreCase("Y")) {
            System.out.println("Omschrijving: ");
            p.setDescription(sc.nextLine());
        }
        if (!sc.next().equalsIgnoreCase("N")) {
            log.error("Geen geldige omschrijving input ( Y of N ) ontvangen");
        }

        System.out.println("Oude prijs: " + p.getPrice());
        System.out.println("Bewerkt prijs? Y/N");
        if (sc.next().equalsIgnoreCase("Y")) {
            System.out.println("De gewenste prijs: ");
            p.setPrice(Double.parseDouble(sc.nextLine()));
        }
        if (!sc.next().equalsIgnoreCase("N")) {
            log.error("Geen geldige prijs input ( Y of N ) ontvangen");
        }


        runnableTransaction(() -> em.merge(p));
    }


}

