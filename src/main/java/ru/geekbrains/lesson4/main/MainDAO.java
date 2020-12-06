package ru.geekbrains.lesson4.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.lesson4.config.AppConfig;
import ru.geekbrains.lesson4.dao.ArticleDAO;
import ru.geekbrains.lesson4.entity.Article;
import ru.geekbrains.lesson4.entity.User;
import ru.geekbrains.lesson4.repositories.ArticlesRepository;

public class MainDAO {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        ArticlesRepository articlesRepository =
                applicationContext.getBean("articlesRepository",ArticlesRepository.class);

        articlesRepository.save(new Article("New Title 1", new User("Alex")));
        articlesRepository.save(new Article("New Title 2", new User("Alena")));
        articlesRepository.save(new Article("New Title 3", new User("Milena")));

        ArticleDAO articleDAO = applicationContext.getBean("articleDAO", ArticleDAO.class);

        articleDAO.findAll().stream().forEach(System.out::println);
    }
}
