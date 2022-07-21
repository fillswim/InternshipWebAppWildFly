package org.example.service;

import org.example.dao.BucketDAO;
import org.example.models.Bucket;
import org.example.models.BucketStatus;
import org.example.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BucketServiceImpl implements BucketService {

    private final BucketDAO bucketDAO;

    public BucketServiceImpl(BucketDAO bucketDAO) {
        this.bucketDAO = bucketDAO;
    }

    @Override
    public Optional<Bucket> findBucketByUserAndStatus(User user, BucketStatus bucketStatus) {

        return bucketDAO.findBucketByUserAndStatus(user, bucketStatus);
    }

    @Override
    public void saveBucket(Bucket bucket) {

        bucketDAO.saveBucket(bucket);
    }

}
