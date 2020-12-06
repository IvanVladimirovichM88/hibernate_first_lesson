package ru.geekbrains.lesson4.dao;

import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import ru.geekbrains.lesson4.entity.Article;

import java.util.List;

public interface ArticleDAO {
    List<Article> findAll();
    void save(Article article);
    Article findById(Long id);
    void update(Article article);
    void delete(Article article);
}
