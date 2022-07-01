package org.example.service;

import org.example.dao.OrderItemDAO;
import org.example.models.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService{

    private final OrderItemDAO orderItemDAO;

    public OrderItemServiceImpl(OrderItemDAO orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return orderItemDAO.getAllOrderItems();
    }

    @Override
    public void saveOrderItem(OrderItem orderItem) {
        orderItemDAO.saveOrderItem(orderItem);
    }

    @Override
    public OrderItem getOrderItem(int id) {
        return orderItemDAO.getOrderItem(id);
    }

    @Override
    public void deleteOrderItem(int id) {
        orderItemDAO.deleteOrderItem(id);
    }
}
