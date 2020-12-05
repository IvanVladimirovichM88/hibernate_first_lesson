package ru.geekbrains.lesson4.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.geekbrains.lesson4.entity.Category;
import ru.geekbrains.lesson4.entity.Product;

import java.util.List;

public interface ProductService {
    Product get(Long id);
    List<Product> getAll();
    void save(Product product);
    void remove(Product product);
    Product findByName(String name);
    Product findProductWithMaxPriceInCategory(Category category);
    Product findProductWithMinPriceInCategory(Category category);
    List<Product> findProductsWithMinAndMaxPriceInCategory(Category category);
    Product findProductWithMaxPrice();
    Product findProductWithMinPrice();
    List<Product> findAllByPriceGreaterThanEqual(Double price);
    List<Product> findAllByPriceGreaterThanOrderByPriceDesc(Double price);
    List<Product> findAllByCategory(Category category);
    Page<Product> findAllByCategory(Category category, Pageable pageable);
}
