package org.example.service;

import org.example.dto.BucketDetailsDTO;
import org.example.models.Bucket;
import org.example.models.BucketDetails;
import org.example.models.BucketStatus;
import org.example.models.Product;

import java.util.List;
import java.util.Optional;

public interface BucketDetailsService {

    List<BucketDetailsDTO> findAllBucketDetailsForUserDTOS(String username, BucketStatus bucketStatus);

    void saveBucketDetails(BucketDetails bucketDetails);

    void deleteBucketDetails(BucketDetails bucketDetails);

    Optional<BucketDetails> findBucketDetailsByBucketAndProduct(Bucket bucket, Product product);

}
