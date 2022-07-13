package org.example.dao;

import org.example.models.BucketDetails;

import java.util.List;

public interface BucketDetailsDAO {

    List<BucketDetails> findAllBucketDetails();

    BucketDetails getBucketDetailsByProductId(int productId);

    void saveBucketDetails(BucketDetails bucketDetails);

    void deleteBucketDetailsById(int bucketDetailsId);

}
