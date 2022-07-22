package org.example.service;

import org.example.dto.OrderDTO;
import org.example.mappers.OrderMapper;
import org.example.models.Bucket;
import org.example.models.BucketDetails;
import org.example.models.BucketStatus;
import org.example.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    private final BucketService bucketService;

    private final BucketDetailsService bucketDetailsService;

    private final UserService userService;

    private final OrderMapper orderMapper;

    public OrderServiceImpl(BucketService bucketService,
                            BucketDetailsService bucketDetailsService,
                            UserService userService, OrderMapper orderMapper) {
        this.bucketService = bucketService;
        this.bucketDetailsService = bucketDetailsService;
        this.userService = userService;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderDTO> getAllOrders(String username) {

        User user = userService.findUserByUsername(username);

        List<Bucket> buckets = bucketService.findBucketsByUserAndBucketStatus(user, BucketStatus.COMPLETED);

        return buckets.stream()
                .map(orderMapper::mapToOrderDTO)
                .collect(Collectors.toList());

    }

    @Override
    public void createOrder(String username) {

        User user = userService.findUserByUsername(username);

        // Найти текущую корзинку пользователя
        List<Bucket> buckets = bucketService.findBucketsByUserAndBucketStatus(user, BucketStatus.CURRENT);

        // Если корзинка существует
        if (!buckets.isEmpty()) {

            Bucket bucket = buckets.get(0);

            List<BucketDetails> bucketDetailsList =
                    bucketDetailsService.findBucketDetailsByBucket(bucket);

            // то смотрим, есть ли в ней детали заказов
            if (!bucketDetailsList.isEmpty()) {

                bucket.setBucketStatus(BucketStatus.COMPLETED);
                bucketService.saveBucket(bucket);

                // Создание и сохранение новой корзинки для текущих покупок
                Bucket newCurrentBucket = Bucket.builder()
                        .user(user)
                        .bucketStatus(BucketStatus.CURRENT)
                        .build();

                bucketService.saveBucket(newCurrentBucket);

            }

        }

    }
}
