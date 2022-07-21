package org.example.dao;

import org.example.models.Bucket;
import org.example.models.BucketDetails;
import org.example.models.Product;
import org.example.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Repository
public class BucketDetailsDAOImpl implements BucketDetailsDAO {

    private final SessionFactory sessionFactory;

    public BucketDetailsDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveBucketDetails(BucketDetails bucketDetails) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(bucketDetails);

    }

    @Override
    public void deleteBucketDetails(BucketDetails bucketDetails) {

        Session session = sessionFactory.getCurrentSession();
        session.delete(bucketDetails);

    }

    @Override
    public Optional<BucketDetails> findBucketDetailsByBucketAndProduct(Bucket bucket, Product product) {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<BucketDetails> criteriaQuery = criteriaBuilder.createQuery(BucketDetails.class);

        Root<BucketDetails> root = criteriaQuery.from(BucketDetails.class);
        Predicate predicateForBucket = criteriaBuilder.equal(root.get("bucket"), bucket);
        Predicate predicateForProduct = criteriaBuilder.equal(root.get("product"), product);

        Predicate finalPredicate = criteriaBuilder.and(predicateForBucket, predicateForProduct);

        criteriaQuery.where(finalPredicate);

        Query<BucketDetails> query = session.createQuery(criteriaQuery);

        BucketDetails bucketDetails;

        try {
            bucketDetails = query.getSingleResult();
        } catch (Exception e) {
            bucketDetails = null;
        }

        return Optional.ofNullable(bucketDetails);
    }

    @Override
    public List<BucketDetails> findBucketDetailsByBucket(Bucket bucket) {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<BucketDetails> criteriaQuery = criteriaBuilder.createQuery(BucketDetails.class);

        Root<BucketDetails> root = criteriaQuery.from(BucketDetails.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("bucket"), bucket));

        Query<BucketDetails> query = session.createQuery(criteriaQuery);

        return query.getResultList();

    }
}
