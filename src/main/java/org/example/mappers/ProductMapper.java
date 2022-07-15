package org.example.mappers;

import org.example.dto.ProductDTO;
import org.example.models.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public ProductDTO mapToProductDto(Product product) {

        return ProductDTO.builder()
                .id(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .manufacturerId(product.getManufacturer().getId())
                .manufacturerTitle(product.getManufacturer().getTitle())
                .build();
    }

    public Product mapToProduct(ProductDTO productDTO) {

        return Product.builder()
                .id(productDTO.getId())
                .title(productDTO.getTitle())
                .price(productDTO.getPrice())
                .build();
    }

}
