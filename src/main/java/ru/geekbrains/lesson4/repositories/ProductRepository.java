package ru.geekbrains.lesson4.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson4.entity.Category;
import ru.geekbrains.lesson4.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual(Double price);
    List<Product> findAllByPriceGreaterThanOrderByPriceDesc(Double price);
    List<Product> findAllByCategory(Category category);
    Page<Product> findAllByCategory(Category category, Pageable pageable);
    Product findByName(String name);
    Product findTopByCategoryOrderByPriceDesc(Category category);
    Product findTopByCategoryOrderByPriceAsc(Category category);
    Product findTopByOrderByPriceDesc();
    Product findTopByOrderByPriceAsc();
    

    
}
