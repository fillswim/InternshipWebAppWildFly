package org.example.service;

import org.example.dto.BucketDetailsDTO;
import org.example.models.Bucket;
import org.example.models.BucketDetails;
import org.example.models.BucketStatus;
import org.example.models.Product;

import java.util.List;
import java.util.Optional;

public interface BucketDetailsService {

    List<BucketDetailsDTO> findAllBucketDetailsForCurrentBucketOfUser(String username);

    void saveBucketDetails(BucketDetails bucketDetails);

    void deleteBucketDetails(BucketDetails bucketDetails);

    List<BucketDetails> findBucketDetailsByBucket(Bucket bucket);

    List<BucketDetailsDTO> findBucketDetailsDTOByBucket(Bucket bucket);

    Optional<BucketDetails> findBucketDetailsByBucketAndProduct(Bucket bucket, Product product);

}
