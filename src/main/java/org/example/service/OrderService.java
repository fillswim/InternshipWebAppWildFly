package org.example.service;

import org.example.dto.OrderDTO;
import org.example.models.Order;
import org.example.models.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderDTO> getAllOrders(String username);

    void createOrder(String username);

    void saveOrder(Order order);

    OrderDTO findOrderByUsernameAndId(int orderId);

}
