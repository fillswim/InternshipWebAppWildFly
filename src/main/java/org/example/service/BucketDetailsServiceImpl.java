package org.example.service;

import org.example.dao.BucketDetailsDAO;
import org.example.models.Bucket;
import org.example.models.BucketDetails;
import org.example.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BucketDetailsServiceImpl implements BucketDetailsService{

    private final BucketDetailsDAO bucketDetailsDAO;

    public BucketDetailsServiceImpl(BucketDetailsDAO bucketDetailsDAO) {
        this.bucketDetailsDAO = bucketDetailsDAO;
    }

    @Override
    public void saveBucketDetails(BucketDetails bucketDetails) {
        bucketDetailsDAO.saveBucketDetails(bucketDetails);
    }

    @Override
    public void deleteBucketDetails(BucketDetails bucketDetails) {
        bucketDetailsDAO.deleteBucketDetails(bucketDetails);
    }

    @Override
    public List<BucketDetails> findBucketDetailsByBucket(Bucket bucket) {
        return bucketDetailsDAO.findBucketDetailsByBucket(bucket);
    }

    @Override
    public Optional<BucketDetails> findBucketDetailsByBucketAndProduct(Bucket bucket, Product product) {
        return bucketDetailsDAO.findBucketDetailsByBucketAndProduct(bucket, product);
    }
}
