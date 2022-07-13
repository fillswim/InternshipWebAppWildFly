package org.example.service;

import org.example.models.BucketDetails;

import java.util.List;

public interface BucketDetailsService {

    public List<BucketDetails> findAllBucketDetails();

    public BucketDetails getBucketDetailsByProductId(int productId);

    public void saveBucketDetails(BucketDetails bucketDetails);

    public void deleteBucketDetailsById(int bucketDetailsId);

}
