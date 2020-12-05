package ru.geekbrains.lesson4.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import ru.geekbrains.lesson4.config.AppConfig;
import ru.geekbrains.lesson4.entity.Cart;
import ru.geekbrains.lesson4.entity.Category;
import ru.geekbrains.lesson4.entity.Product;
import ru.geekbrains.lesson4.entity.User;
import ru.geekbrains.lesson4.services.*;

import java.util.List;

public class MainData {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CartService cartService = applicationContext.getBean("cartService", CartService.class);
        CartEntryService cartEntryService = applicationContext.getBean("cartEntryService", CartEntryService.class);
        ProductService productService = applicationContext.getBean("productService", ProductService.class);
        UserService userService = applicationContext.getBean("userService", UserService.class);
        CategoryService categoryService = applicationContext.getBean("categoryService", CategoryService.class);
       // CartDataRepository cartDataRepository = applicationContext.getBean("cartDataRepository", CartDataRepository.class);

        User user1 = new User("Alex");
        User user2 = new User("Felix");

        userService.save(user1);
        userService.save(user2);

        Category category1 = new Category("Category_1");
        Category category2 = new Category("Category_2");

        categoryService.save(category1);
        categoryService.save(category2);


        Product product1 = new Product("Product_1", 100l, 15.0, category1);
        Product product2 = new Product("Product_2", 200l, 20.0, category1);
        Product product3 = new Product("Product_3", 300l, 25.0, category2);
        Product product4 = new Product("Product_4", 400l, 75.0, category2);
        Product product5 = new Product("Product_5", 500l, 50.0, category2);
        Product product6 = new Product("Product_6", 100l, 15.0, category1);
        Product product7 = new Product("Product_7", 200l, 30.0, category1);
        Product product8 = new Product("Product_8", 300l, 45.0, category2);
        Product product9 = new Product("Product_9", 400l, 65.0, category2);
        Product product0 = new Product("Product_0", 500l, 55.0, category2);

        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        productService.save(product4);
        productService.save(product5);
        productService.save(product6);
        productService.save(product7);
        productService.save(product8);
        productService.save(product9);
        productService.save(product0);



        Cart cart1 = new Cart();
        cart1.setCode("0001");
        cart1.setUser(user1);
        cartService.save(cart1);

        Cart cart2 = new Cart();
        cart2.setCode("0002");
        cart2.setUser(user2);
        cartService.save(cart2);

        cartEntryService.addProduct(cart1, product1, 2);
        cartEntryService.addProduct(cart1, product2,5);
        cartEntryService.addProduct(cart1, product3, 10);
        cartEntryService.addProduct(cart1, product6, 2);
        cartEntryService.addProduct(cart1, product7,5);

        cartEntryService.addProduct(cart2, product4, 2);
        cartEntryService.addProduct(cart2, product5,5);
        cartEntryService.addProduct(cart1, product8, 2);
        cartEntryService.addProduct(cart1, product9,5);
        cartEntryService.addProduct(cart1, product0, 10);

        Cart cartAlex = cartService.getByCode("0001");
        System.out.println("CartAlex - " + cartAlex);

        categoryService.getAll().stream().forEach(System.out::println);

        productService.findAllByCategory(category1).stream().forEach(System.out::println);

        productService.findProductsWithMinAndMaxPriceInCategory(category2).stream().forEach(System.out::println);

        System.out.println(productService.findProductWithMaxPrice());
        System.out.println(productService.findProductWithMinPrice());


        Pageable pageable = PageRequest.of(0,2);
        Page<Product> productsOnPage = productService.findAllByCategory(category1, pageable);
        System.out.println(productsOnPage.getContent());

    }
}
