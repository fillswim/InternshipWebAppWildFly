package org.example.dao;

import org.example.models.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<OrderItem> criteriaQuery = criteriaBuilder.createQuery(OrderItem.class);

        Root<OrderItem> root = criteriaQuery.from(OrderItem.class);
        criteriaQuery.select(root);

        Query<OrderItem> query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public void saveOrderItem(OrderItem orderItem) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(orderItem);
    }

    @Override
    public OrderItem getOrderItem(int id) {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<OrderItem> criteriaQuery = criteriaBuilder.createQuery(OrderItem.class);

        Root<OrderItem> root = criteriaQuery.from(OrderItem.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));

        Query<OrderItem> query = session.createQuery(criteriaQuery);

        return query.getSingleResult();
    }

    @Override
    public void deleteOrderItem(int id) {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<OrderItem> criteriaDelete = criteriaBuilder.createCriteriaDelete(OrderItem.class);

        Root<OrderItem> root = criteriaDelete.from(OrderItem.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));

        session.createQuery(criteriaDelete).executeUpdate();

    }
}
