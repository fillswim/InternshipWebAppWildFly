package org.example.dao;

import org.example.models.Order;
import org.example.models.User;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {

    void saveOrder(Order order);

    List<Order> findAllOrdersByUser(User user);

    Optional<Order> findOrderByUserAndId(int orderId);

}
