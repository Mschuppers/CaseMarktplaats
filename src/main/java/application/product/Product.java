package application.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@NamedQuery(name = "product.FindOneProduct", query = "SELECT p FROM Product p where p.id = :id")
@NamedQuery(name = "product.FindOneUserProduct", query = "SELECT p FROM Product p where p.id = :id and p.user = :user_id")

@NamedQuery(name = "product.FindAll", query = "SELECT p FROM Product p")




@Data @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 200, message = "Maximaal 200 karakters")
    private String name;


    @Pattern(regexp = "\\d*[.]\\d{2}?")
    private double price;
    @Size(min = 1, max = 200, message = "Maximaal 200 karakters")
    private String description;
    @PastOrPresent
    private LocalDate datePublished;

    @ManyToOne
    private User user;




}

