package ru.geekbrains.lesson4.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.lesson4.entity.Cart;
import ru.geekbrains.lesson4.entity.Product;
import ru.geekbrains.lesson4.entity.User;
import ru.geekbrains.lesson4.repositories.CartRepository;
import ru.geekbrains.lesson4.services.CartService;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    @Autowired
    public void setCartRepository(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Cart get(Long id) {
        return cartRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getByCode(String code) {
        return cartRepository.findByCode(code);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cart> getAll() {
        return cartRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void remove(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    public Cart findCartByUser(User user) {
        return cartRepository.findCartByUser(user);
    }

    @Override
    public List<Cart> findAllCartByProduct(Product product) {
        return cartRepository.findAllCartsByProduct(product);
    }
}
