package ru.geekbrains.lesson4.services;

import ru.geekbrains.lesson4.entity.Cart;
import ru.geekbrains.lesson4.entity.Product;
import ru.geekbrains.lesson4.entity.User;

import java.util.List;

public interface CartService {
    Cart get(Long id);
    Cart getByCode(String code);
    List<Cart> getAll();
    void save(Cart cart);
    void remove (Cart cart);

    Cart findCartByUser(User user);
    List<Cart> findAllCartByProduct(Product product);
}
