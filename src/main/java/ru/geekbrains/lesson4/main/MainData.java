package ru.geekbrains.lesson4.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.lesson4.config.AppConfig;
import ru.geekbrains.lesson4.entity.Cart;
import ru.geekbrains.lesson4.entity.Product;
import ru.geekbrains.lesson4.entity.User;
import ru.geekbrains.lesson4.services.CartEntryService;
import ru.geekbrains.lesson4.services.CartService;
import ru.geekbrains.lesson4.services.ProductService;
import ru.geekbrains.lesson4.services.UserService;

public class MainData {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        CartService cartService = applicationContext.getBean("cartService", CartService.class);
        CartEntryService cartEntryService = applicationContext.getBean("cartEntryService", CartEntryService.class);
        ProductService productService = applicationContext.getBean("productService", ProductService.class);
        UserService userService = applicationContext.getBean("userService", UserService.class);
       // CartDataRepository cartDataRepository = applicationContext.getBean("cartDataRepository", CartDataRepository.class);

        User user1 = new User("Alex");
        User user2 = new User("Felix");

        userService.save(user1);
        userService.save(user2);


        Product product1 = new Product("Product_1", 100l, 15.0);
        Product product2 = new Product("Product_2", 200l, 20.0);
        Product product3 = new Product("Product_3", 300l, 25.0);
        Product product4 = new Product("Product_4", 400l, 30.0);
        Product product5 = new Product("Product_5", 500l, 50.0);


        productService.save(product1);
        productService.save(product2);
        productService.save(product3);
        productService.save(product4);
        productService.save(product5);

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

        cartEntryService.addProduct(cart2, product4, 2);
        cartEntryService.addProduct(cart2, product5,5);

        Cart cartAlex = cartService.getByCode("0001");
        System.out.println("CartAlex - " + cartAlex);
    }
}
