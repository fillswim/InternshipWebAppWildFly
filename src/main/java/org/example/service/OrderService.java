package org.example.service;

import org.example.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrders(String username);

    void createOrder(String username);

}
