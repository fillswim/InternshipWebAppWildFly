package org.example.service;

import org.example.dao.BucketDAO;
import org.example.models.Bucket;
import org.example.models.BucketStatus;
import org.example.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BucketServiceImpl implements BucketService {

    private final BucketDAO bucketDAO;

    public BucketServiceImpl(BucketDAO bucketDAO) {
        this.bucketDAO = bucketDAO;
    }

    @Override
    public List<Bucket> findBucketsByUserAndBucketStatus(User user, BucketStatus bucketStatus) {

        return bucketDAO.findBucketsByUserAndStatus(user, bucketStatus);
    }

    @Override
    public Optional<Bucket> findBucketById(int bucketId) {
        return bucketDAO.findBucketById(bucketId);
    }

    @Override
    public void saveBucket(Bucket bucket) {

        bucketDAO.saveBucket(bucket);
    }

}
