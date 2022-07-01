package org.example.dao;

import org.example.models.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemDAOImpl implements OrderItemDAO{

    private final SessionFactory sessionFactory;

    public OrderItemDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<OrderItem> getAllOrderItems() {

        Session session = sessionFactory.getCurrentSession();

        List<OrderItem> orderItems = session.createQuery("from OrderItem", OrderItem.class).getResultList();

        return orderItems;
    }

    @Override
    public void saveOrderItem(OrderItem orderItem) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(orderItem);
    }

    @Override
    public OrderItem getOrderItem(int id) {

        Session session = sessionFactory.getCurrentSession();
        OrderItem orderItem = session.get(OrderItem.class, id);

        return orderItem;
    }

    @Override
    public void deleteOrderItem(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query<OrderItem> query = session.createQuery("delete from OrderItem where id =:orderItemId");
        query.setParameter("orderItemId", id);

        query.executeUpdate();
    }
}
