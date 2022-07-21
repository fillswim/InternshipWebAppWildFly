package org.example.service;

import org.example.dto.ProductDTO;
import org.example.models.Product;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProductsDTOS();

    void saveProduct(Product product);

    Product getProductById(int productId);

    ProductDTO getProductDTOById(int productId);

    void deleteProduct(Product product);

    void addProductToBucket(int productId, String username);

    void deleteProductFromBucket(int productId, String username);

}
