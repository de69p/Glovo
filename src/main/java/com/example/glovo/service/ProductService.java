package com.example.glovo.service;

import com.example.glovo.model.Product;
import com.example.glovo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public void createProduct(Product product) {
        productRepository.createProduct(product);
    }

    public void updateProduct(Long id, Product product) {
        productRepository.updateProduct(id,product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }
}
