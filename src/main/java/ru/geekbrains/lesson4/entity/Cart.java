package ru.geekbrains.lesson4.entity;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import javax.swing.event.AncestorEvent;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart_tbl")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @Column(name = "code_fld")
    private String code;

    @OneToMany(
            mappedBy = "cart",
            fetch = FetchType.EAGER
    )
    List<CartEntry> cartEntryList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
//////////////////////////////////////////////////////////////
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<CartEntry> getCartEntryList() {
        return cartEntryList;
    }

    public void setCartEntryList(List<CartEntry> cartEntryList) {
        this.cartEntryList = cartEntryList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", cartEntryList=" + cartEntryList +
                ", user=" + user +
                '}';
    }
}
