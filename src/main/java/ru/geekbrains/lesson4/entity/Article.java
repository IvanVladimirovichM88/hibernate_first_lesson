package ru.geekbrains.lesson4.entity;

import javax.persistence.*;

@Entity
@Table(name = "article_tbl")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "title_fld")
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "user_id")
    private User user;
////////////////////////////////////////////////////////
    public Article(){}

    public Article(String title, User user){
        this.title = title;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", user=" + user +
                '}';
    }
}
