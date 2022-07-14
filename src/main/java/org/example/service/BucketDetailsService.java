package org.example.service;

import org.example.models.BucketDetails;

import java.util.List;

public interface BucketDetailsService {

    List<BucketDetails> findAllBucketDetails();

    BucketDetails getBucketDetailsByProductId(int productId);

    void saveBucketDetails(BucketDetails bucketDetails);

    void deleteBucketDetailsById(int bucketDetailsId);

}
