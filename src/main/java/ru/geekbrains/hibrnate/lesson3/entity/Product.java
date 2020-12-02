package ru.geekbrains.hibrnate.lesson3.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products_tbl")
@NamedQuery(name = "deleteProduct", query = "DELETE FROM Product p WHERE p.id = :ProductId")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name_fld")
    private String name;

    @Column (name = "price_fld")
    private Float price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(
            name = "products_carts_tbl",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id")
    )
    private List<Cart> carts = new ArrayList<>();

   //////////////////////////////////////////////////////////////////

    public Product(){}

    public Product(String name, Float price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
