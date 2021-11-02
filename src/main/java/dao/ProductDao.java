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


    public void insertProduct() {
        Product p = new Product();
        p.setAddedById(1);
        System.out.println("Een nieuw product toevoegen");
        System.out.println("Titel: ");
        p.setName(sc.nextLine());
        System.out.println("Omschrijving: ");
        p.setDescription(sc.nextLine());
        System.out.println("De gewenste prijs: ");
        p.setPrice(Double.parseDouble(sc.nextLine().replace(',' , '.')));
        p.setDatePublished(LocalDate.now());
        runnableTransaction(() -> em.persist(p));
    }

    public void runnableTransaction(Runnable subject) {
        em.getTransaction().begin();
        subject.run();
        em.getTransaction().commit();
    }


}

