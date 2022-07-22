package org.example.dao;

import org.example.models.Bucket;
import org.example.models.BucketStatus;
import org.example.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class BucketDAOImpl implements BucketDAO {

    private final SessionFactory sessionFactory;

    public BucketDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Bucket> findBucketsByUserAndStatus(User user, BucketStatus bucketStatus) {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Bucket> criteriaQuery = criteriaBuilder.createQuery(Bucket.class);

        Root<Bucket> root = criteriaQuery.from(Bucket.class);
        Predicate predicateForUser = criteriaBuilder.equal(root.get("user"), user);
        Predicate predicateForBucketStatus = criteriaBuilder.equal(root.get("bucketStatus"), bucketStatus);

        Predicate finalPredicate = criteriaBuilder.and(predicateForUser, predicateForBucketStatus);

        criteriaQuery.where(finalPredicate);

        Query<Bucket> query = session.createQuery(criteriaQuery);

        return query.getResultList();

    }

    @Override
    public void saveBucket(Bucket bucket) {

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(bucket);

    }

    @Override
    public Optional<Bucket> findBucketById(int bucketId) {

        Session session = sessionFactory.getCurrentSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Bucket> criteriaQuery = criteriaBuilder.createQuery(Bucket.class);

        Root<Bucket> root = criteriaQuery.from(Bucket.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), bucketId));

        Query<Bucket> query = session.createQuery(criteriaQuery);

        return Optional.ofNullable(query.getSingleResult());
    }

}
