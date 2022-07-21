package org.example.dao;

import org.example.models.Bucket;
import org.example.models.BucketDetails;
import org.example.models.Product;
import org.example.models.User;

import java.util.List;
import java.util.Optional;

public interface BucketDetailsDAO {

    void saveBucketDetails(BucketDetails bucketDetails);

    void deleteBucketDetails(BucketDetails bucketDetails);

    Optional<BucketDetails> findBucketDetailsByBucketAndProduct(Bucket bucket, Product product);

    List<BucketDetails> findBucketDetailsByBucket(Bucket bucket);

}
