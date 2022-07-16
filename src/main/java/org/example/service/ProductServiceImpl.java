package org.example.service;

import org.example.dao.ProductDAO;
import org.example.dto.ProductDTO;
import org.example.mappers.ProductMapper;
import org.example.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductDAO productDAO,
                              ProductMapper productMapper) {
        this.productDAO = productDAO;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDTO> getAllProductsDTOS() {
        return productDAO.getAllProducts().stream()
                .map(productMapper::mapToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveProduct(Product product) {
        productDAO.saveProduct(product);
    }

    @Override
    public Product getProductById(int productId) {
        return productDAO.getProductById(productId);
    }

    @Override
    public ProductDTO getProductDTOById(int productId) {

        Product product = productDAO.getProductById(productId);

        return productMapper.mapToProductDto(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productDAO.deleteProduct(product);
    }
}
