package ru.geekbrains.lesson4.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.lesson4.entity.Product;
import ru.geekbrains.lesson4.repositories.ProductRepository;
import ru.geekbrains.lesson4.services.ProductService;

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
    public List<Product> findAllByPriceGreaterThan(Double price) {
        return productRepository.findAllByPriceGreaterThanEqual(price);
    }

    @Override
    @Transactional
    public List<Product> findAllByPriceGreaterThanOrderByPriceDesc(Double price) {
        return productRepository.findAllByPriceGreaterThanOrderByPriceDesc(price);
    }
}
