package org.example.service;

import org.example.dto.OrderDTO;
import org.example.models.Order;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrders(String username);

    void createOrder(String username, OrderDTO orderDTO);

    void saveOrder(Order order);

    OrderDTO findOrderByUsernameAndId(int orderId);

}
