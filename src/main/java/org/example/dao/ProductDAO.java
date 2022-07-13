package org.example.dao;

import org.example.models.Product;

import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts();

    void saveProduct(Product product);

    Product getProductById(int productId);

    void deleteProductById(int productId);

}
