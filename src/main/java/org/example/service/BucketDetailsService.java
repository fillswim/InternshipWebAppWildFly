package org.example.service;

import org.example.models.BucketDetails;
import org.example.models.Product;

import java.util.List;

public interface BucketDetailsService {

    List<BucketDetails> findAllBucketDetails();

    BucketDetails getBucketDetailsByProduct(int productId);

    BucketDetails getBucketDetailsByProduct(Product product);

    void saveBucketDetails(BucketDetails bucketDetails);

    void deleteBucketDetailsById(int bucketDetailsId);

}
