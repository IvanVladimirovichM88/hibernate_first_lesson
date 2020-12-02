package ru.geekbrains.hibrnate.lesson3.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts_tbl")
//@NamedQuery(name = "withProducts", query = "SELECT c FROM Category c JOIN FETCH c.products WHERE c.id = :id")
@NamedQuery(name = "allPersonsBuyProduct", query = "SELECT c.person FROM Cart c JOIN c.products p WHERE p.id  = :ProductId")
@NamedQuery(name = "allProductsInPersonCart", query = "SELECT p FROM Cart c JOIN c.products p WHERE c.person.id = :PersonId")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne(mappedBy = "personCart")
    private Person person;

    @ManyToMany (cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "products_carts_tbl",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

////////////////////////////////////////////////////////////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", person=" + person +
                ", products=" + products +
                '}';
    }
}
