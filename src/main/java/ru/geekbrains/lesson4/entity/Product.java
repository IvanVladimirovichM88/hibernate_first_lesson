package ru.geekbrains.lesson4.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OptimisticLock;
import org.springframework.data.web.JsonPath;

import javax.persistence.*;

@Entity
@Table(name = "product_tbl")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name_fld")
    private String name;

    @Column(name = "quantity_fld")
    private Long quantity;

    @Column(name = "price_fld")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "something_fld")
    @OptimisticLock(excluded = true)
    private String something;

    @Version
    long version;
////////////////////////////////////////////
    public Product(){}

    public Product(String name, Long quantity, Double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(String name, Long quantity, Double price, Category category) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSomething() {
        return something;
    }

    public void setSomething(String something) {
        this.something = something;
    }

    public long getVersion() {
        return version;
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
                ", quantity=" + quantity +
                ", price=" + price +
                ", something='" + something + '\'' +
                ", version=" + version +'\''+
                ", category=" + category +
                '}';
    }
}
