package ru.geekbrains.hibrnate.lesson3.entity;

import javax.persistence.*;

@Entity
@Table (name = "simple_items_tbl")
public class SimpleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="simple_item_id")
    private Long id;

    @Column(name = "title_fld")
    private String title;

    @Column(name = "price_fld")
    private int price;

    @Transient
    private String value;

    public SimpleItem() {
    }

    public SimpleItem(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SimpleItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", value='" + value + '\'' +
                '}';
    }
}
