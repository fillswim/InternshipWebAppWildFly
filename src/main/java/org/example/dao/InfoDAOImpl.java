package org.example.dao;

import org.example.models.Info;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class InfoDAOImpl implements InfoDAO {

    private final SessionFactory sessionFactory;

    public InfoDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Info> getAllInfo() {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Info> criteriaQuery = criteriaBuilder.createQuery(Info.class);

        Root<Info> root = criteriaQuery.from(Info.class);
        criteriaQuery.select(root);

        Query<Info> query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }
}
