package ru.geekbrains.lesson4.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.lesson4.entity.Category;
import ru.geekbrains.lesson4.entity.Product;
import ru.geekbrains.lesson4.repositories.ProductRepository;
import ru.geekbrains.lesson4.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Product get(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void remove(Product product) {
        productRepository.delete(product);
    }

    @Override
    @Transactional
    public Product findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Product findProductWithMaxPriceInCategory(Category category) {
        return productRepository.findTopByCategoryOrderByPriceDesc(category);
    }

    @Override
    @Transactional
    public Product findProductWithMinPriceInCategory(Category category) {
        return productRepository.findTopByCategoryOrderByPriceAsc(category);
    }

    @Override
    public List<Product> findProductsWithMinAndMaxPriceInCategory(Category category) {
        List<Product> products = new ArrayList<>(2);
        products.add(this.findProductWithMinPriceInCategory(category));
        products.add(this.findProductWithMaxPriceInCategory(category));
        return products;
    }

    @Override
    @Transactional
    public Product findProductWithMaxPrice() {
        return productRepository.findTopByOrderByPriceDesc();
    }

    @Override
    @Transactional
    public Product findProductWithMinPrice() {
        return productRepository.findTopByOrderByPriceAsc();
    }

    @Override
    @Transactional
    public List<Product> findAllByPriceGreaterThanEqual(Double price) {
        return productRepository.findAllByPriceGreaterThanEqual(price);
    }

    @Override
    @Transactional
    public List<Product> findAllByPriceGreaterThanOrderByPriceDesc(Double price) {
        return productRepository.findAllByPriceGreaterThanOrderByPriceDesc(price);
    }

    @Override
    @Transactional
    public List<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Page<Product> findAllByCategory(Category category, Pageable pageable) {
        return productRepository.findAllByCategory(category, pageable);
    }
}
