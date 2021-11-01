package products;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@NamedQuery(name = "Product.FindOneProduct", query = "SELECT p FROM Product p where p.id = :id")
@NamedQuery(name = "Product.FindAll", query = "SELECT p FROM Product p")

@Data
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String description;
    private LocalDate datePublished;



    //NONARG CONSTRUCTOR -> SPECIFICALLY NEEDED
    public Product() {

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", datePublished=" + datePublished + '}';
    }
}

