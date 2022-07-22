package org.example.service;

import org.example.dao.ProductDAO;
import org.example.dto.ProductDTO;
import org.example.mappers.ProductMapper;
import org.example.models.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    private final ProductMapper productMapper;

    private final UserService userService;

    private final BucketService bucketService;

    private final BucketDetailsService bucketDetailsService;

    public ProductServiceImpl(ProductDAO productDAO,
                              ProductMapper productMapper,
                              UserService userService,
                              BucketService bucketService,
                              BucketDetailsService bucketDetailsService) {
        this.productDAO = productDAO;
        this.productMapper = productMapper;
        this.userService = userService;
        this.bucketService = bucketService;
        this.bucketDetailsService = bucketDetailsService;
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

    @Override
    public void addProductToBucket(int productId, String username) {

        Product product = productDAO.getProductById(productId);
        User user = userService.findUserByUsername(username);

        List<Bucket> buckets = bucketService.findBucketsByUserAndBucketStatus(user, BucketStatus.CURRENT);

        // Найти корзинку пользователя или создать новую
        if (!buckets.isEmpty()) {

            Bucket bucket = buckets.get(0);

            // ищется деталь заказа (по корзинке и продукту)
            Optional<BucketDetails> optionalBucketDetails =
                    bucketDetailsService.findBucketDetailsByBucketAndProduct(bucket, product);

            BucketDetails bucketDetails;

            if (optionalBucketDetails.isPresent()) {

                // Если деталь заказа найдена, то количество товара в ней увеличивается на 1
                bucketDetails = optionalBucketDetails.get();

                int amount = bucketDetails.getAmount();
                bucketDetails.setAmount(++amount);
            } else {

                // Если деталь заказа не найдена, то она создается
                bucketDetails = BucketDetails.builder()
                        .bucket(bucket)
                        .product(product)
                        .amount(1)
                        .build();
            }

            // сохранение детали заказа
            bucketDetailsService.saveBucketDetails(bucketDetails);

        } else {

            // Создание новой корзинки
            Bucket newBucket = Bucket.builder()
                    .user(user)
                    .bucketStatus(BucketStatus.CURRENT)
                    .build();

            // Создать BucketDetails с продуктом
            BucketDetails newBucketDetails = BucketDetails.builder()
                    .bucket(newBucket)
                    .product(product)
                    .amount(1)
                    .build();

            // Сохранение детали заказа
            bucketDetailsService.saveBucketDetails(newBucketDetails);
        }

    }

    @Override
    public void deleteProductFromBucket(int productId, String username) {

        Product product = productDAO.getProductById(productId);
        User user = userService.findUserByUsername(username);

        // Ищется корзинка (по пользователю и статусу корзинки)
        List<Bucket> buckets = bucketService.findBucketsByUserAndBucketStatus(user, BucketStatus.CURRENT);

        if (!buckets.isEmpty()) {

            // Если корзинка найдена, то
            Bucket bucket = buckets.get(0);

            // Ищется деталь заказа по продукту и корзинке
            Optional<BucketDetails> optionalBucketDetails =
                    bucketDetailsService.findBucketDetailsByBucketAndProduct(bucket, product);

            if (optionalBucketDetails.isPresent()) {

                // Если деталь заказа найдена, то количество товара в ней уменьшается на 1
                BucketDetails bucketDetails = optionalBucketDetails.get();

                int amount = bucketDetails.getAmount();
                int newAmount = --amount;

                if (newAmount > 0) {
                    // Если количество товара в детале заказа отстается больше 1, то деталь заказа сохраняется
                    bucketDetails.setAmount(newAmount);

                    bucketDetailsService.saveBucketDetails(bucketDetails);
                } else {

                    // Если количество товара становится меньше 1, то деталь заказа удаляется
                    bucketDetailsService.deleteBucketDetails(bucketDetails);
                }

            }

        }


    }
}
