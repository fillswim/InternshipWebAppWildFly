package org.example.service;

import org.example.dao.OrderDAO;
import org.example.dto.OrderDTO;
import org.example.mappers.OrderMapper;
import org.example.models.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    private final BucketService bucketService;

    private final BucketDetailsService bucketDetailsService;

    private final UserService userService;

    private final OrderMapper orderMapper;

    private final OrderDAO orderDAO;

    public OrderServiceImpl(BucketService bucketService,
                            BucketDetailsService bucketDetailsService,
                            UserService userService,
                            OrderMapper orderMapper,
                            OrderDAO orderDAO) {
        this.bucketService = bucketService;
        this.bucketDetailsService = bucketDetailsService;
        this.userService = userService;
        this.orderMapper = orderMapper;
        this.orderDAO = orderDAO;
    }

    @Override
    public List<OrderDTO> getAllOrders(String username) {

        User user = userService.findUserByUsername(username);

        List<Order> orders = orderDAO.findAllOrdersByUser(user);

        return orders.stream()
                .map(orderMapper::mapToOrderDTO)
                .collect(Collectors.toList());

    }

    @Override
    public void createOrder(String username, OrderDTO orderDTO) {

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

                // Подсчет стоимости корзинки
                double sum = 0.0;
                for (BucketDetails bucketDetails : bucketDetailsList) {

                    double sumDetail = bucketDetails.getProduct().getPrice() * bucketDetails.getAmount();
                    sum = sum + sumDetail;
                }

                bucket.setSum(sum);
                bucket.setBucketStatus(BucketStatus.COMPLETED);

                // Создается заказ
                Order order = Order.builder()
                        .address(orderDTO.getAddress())
                        .description(orderDTO.getDescription())
                        .bucket(bucket)
                        .user(user)
                        .build();

                // Сохраняется заказ
                orderDAO.saveOrder(order);

                // Создание и сохранение новой корзинки для новых покупок
                Bucket newCurrentBucket = Bucket.builder()
                        .user(user)
                        .bucketStatus(BucketStatus.CURRENT)
                        .build();

                bucketService.saveBucket(newCurrentBucket);

            }

        }

    }

    @Override
    public void saveOrder(Order order) {

        orderDAO.saveOrder(order);
    }

    @Override
    public OrderDTO findOrderByUsernameAndId(int orderId) {

        OrderDTO orderDTO = null;

        Optional<Order> optionalOrder = orderDAO.findOrderByUserAndId(orderId);

        if (optionalOrder.isPresent()) {

            Order order = optionalOrder.get();

            orderDTO = orderMapper.mapToOrderDTO(order);
        }

        return orderDTO;
    }
}
