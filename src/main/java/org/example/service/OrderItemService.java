package org.example.service;

import org.example.models.OrderItem;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> getAllOrderItems();

    void saveOrderItem(OrderItem orderItem);

    OrderItem getOrderItem(int id);

    void deleteOrderItem(int id);

}
