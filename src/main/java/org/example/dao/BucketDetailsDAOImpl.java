package org.example.dao;

import org.example.models.BucketDetails;
import org.example.models.Product;
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
public class BucketDetailsDAOImpl implements BucketDetailsDAO {

    private final SessionFactory sessionFactory;

    public BucketDetailsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<BucketDetails> findAllBucketDetails() {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<BucketDetails> criteriaQuery = criteriaBuilder.createQuery(BucketDetails.class);

        Root<BucketDetails> root = criteriaQuery.from(BucketDetails.class);
        criteriaQuery.select(root);

        Query<BucketDetails> query = session.createQuery(criteriaQuery);

        return query.getResultList();
    }

    @Override
    public BucketDetails getBucketDetailsByProduct(int productId) {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<BucketDetails> criteriaQuery = criteriaBuilder.createQuery(BucketDetails.class);

        Root<BucketDetails> root = criteriaQuery.from(BucketDetails.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("product").get("id"), productId));

        Query<BucketDetails> query = session.createQuery(criteriaQuery);

        BucketDetails bucketDetails;

        try {
            bucketDetails = query.getSingleResult();
        } catch (Exception e) {
            bucketDetails = null;
        }

        return bucketDetails;
    }

    @Override
    public BucketDetails getBucketDetailsByProduct(Product product) {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<BucketDetails> criteriaQuery = criteriaBuilder.createQuery(BucketDetails.class);

        Root<BucketDetails> root = criteriaQuery.from(BucketDetails.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("product"), product));

        Query<BucketDetails> query = session.createQuery(criteriaQuery);

        BucketDetails bucketDetails;

        try {
            bucketDetails = query.getSingleResult();
        } catch (Exception e) {
            bucketDetails = null;
        }

        return bucketDetails;
    }

    @Override
    public void saveBucketDetails(BucketDetails bucketDetails) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(bucketDetails);

    }

    @Override
    public void deleteBucketDetailsById(int bucketDetailsId) {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<BucketDetails> criteriaDelete = criteriaBuilder.createCriteriaDelete(BucketDetails.class);

        Root<BucketDetails> root = criteriaDelete.from(BucketDetails.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), bucketDetailsId));

        session.createQuery(criteriaDelete).executeUpdate();

    }
}
