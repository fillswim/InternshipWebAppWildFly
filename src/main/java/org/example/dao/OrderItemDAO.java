package org.example.dao;

import org.example.models.OrderItem;

import java.util.List;

public interface OrderItemDAO {

    public List<OrderItem> getAllOrderItems();

    public void saveOrderItem(OrderItem orderItem);

    public OrderItem getOrderItem(int id);

    public void deleteOrderItem(int id);

}
