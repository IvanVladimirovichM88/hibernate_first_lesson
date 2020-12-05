package ru.geekbrains.lesson4.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.lesson4.config.AppConfig;
import ru.geekbrains.lesson4.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.LockModeType;
import java.util.List;
import java.util.Properties;

public class MainLocks {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        EntityManagerFactory entityManagerFactory =
                applicationContext.getBean("entityManagerFactory", EntityManagerFactory.class);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        Product product = new Product();
        product.setName("product_1");
        product.setSomething("something_1");
        product.setQuantity(100l);
        entityManager.persist(product);

        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        Product product1 = entityManager.find(Product.class, 1L);

        product1.setName("orange");
        System.out.println("version 1  = " + product1.getVersion());
        System.out.println("product = " + product1);
        entityManager.flush();

        product1.setName("potato");
        System.out.println("version 2  = " + product1.getVersion());
        System.out.println("product = " + product1);
        entityManager.flush();

        System.out.println("version 3 = " + product1.getVersion());
        System.out.println("product = " + product1);
        entityManager.getTransaction().rollback();

        entityManager.getTransaction().begin();
        product1= entityManager.find(Product.class, 1L);
        System.out.println("Version 4  = " + product1.getVersion());
        System.out.println("product = " + product1);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        Product product2 = new Product();
        product2.setName("apple");
        product2.setSomething("something");
        product2.setQuantity(400l);
        entityManager.persist(product2);
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();

        int allQuantity = 0;
        List<Product> products = entityManager.createQuery("FROM Product", Product.class)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getResultList();

        for (Product p : products){
            allQuantity += p.getQuantity();
        }
        System.out.println("allQuantity = " + allQuantity);
        entityManager.getTransaction().commit();

        entityManager.close();

    }
}
