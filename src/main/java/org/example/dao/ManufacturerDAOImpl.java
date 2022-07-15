package org.example.dao;

import org.example.models.Manufacturer;
import org.example.models.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ManufacturerDAOImpl implements ManufacturerDAO {

    private final SessionFactory sessionFactory;

    public ManufacturerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Manufacturer> getAllManufacturer() {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Manufacturer> criteriaQuery = criteriaBuilder.createQuery(Manufacturer.class);

        Root<Manufacturer> root = criteriaQuery.from(Manufacturer.class);
        criteriaQuery.select(root);

        Query<Manufacturer> query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public Manufacturer getManufacturerById(int manufacturerId) {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Manufacturer> criteriaQuery = criteriaBuilder.createQuery(Manufacturer.class);

        Root<Manufacturer> root = criteriaQuery.from(Manufacturer.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), manufacturerId));

        Query<Manufacturer> query = session.createQuery(criteriaQuery);

        return query.getSingleResult();
    }


}
