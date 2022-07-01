package org.example.dao;

import org.example.models.OrderItem;

import java.util.List;

public interface OrderItemDAO {

    List<OrderItem> getAllOrderItems();

    void saveOrderItem(OrderItem orderItem);

    OrderItem getOrderItem(int id);

    void deleteOrderItem(int id);

}
