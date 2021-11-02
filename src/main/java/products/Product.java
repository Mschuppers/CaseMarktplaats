package products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@NamedQuery(name = "product.FindOneProduct", query = "SELECT p FROM Product p where p.id = :id")
@NamedQuery(name = "product.FindAll", query = "SELECT p FROM Product p")

@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 200, message = "Maximaal 200 karakters")
    private String name;

    @NotNull(message = "Invoer mag niet leeg zijn")
    @Pattern(regexp = "\\d*[.]\\d{2}?")
    private double price;
    //@Size(min = 1, max = 200, message = "Maximaal 200 karakters")
    private String description;
    @PastOrPresent
    private LocalDate datePublished;
    @NotNull
    private int addedById;
    @ManyToOne
    private User user;

    //ALLARG CONSTRUCTOR -> SPECIFICALLY NEEDED
   /* public Product(int id, String name, double price, String description, LocalDate datePublished, int addedById) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.datePublished = LocalDate.now();
        this.addedById = 1;  //TODO Entry point for userID FK
    }*/

    //NONARG CONSTRUCTOR -> SPECIFICALLY NEEDED
    /*public Product() {

    }*/


}

