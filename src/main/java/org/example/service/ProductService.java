package org.example.service;

import org.example.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    void saveProduct(Product product);

    Product getProductById(int productId);

    void deleteProduct(int productId);

    void deleteProduct(Product product);

}
