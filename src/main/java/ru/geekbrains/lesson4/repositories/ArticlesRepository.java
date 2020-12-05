package ru.geekbrains.lesson4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson4.entity.Article;
import ru.geekbrains.lesson4.entity.User;

import java.util.List;

@Repository
public interface ArticlesRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitle(String title);
    Article findOneByTitle(String title);

    @Query("SELECT a FROM Article a WHERE a.user = :user")
    List<Article> findArticleByUser(@Param("user") User user);
}
