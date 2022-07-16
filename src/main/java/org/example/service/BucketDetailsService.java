package org.example.service;

import org.example.dto.BucketDetailsDTO;
import org.example.models.BucketDetails;
import org.example.models.Product;

import java.util.List;

public interface BucketDetailsService {

    List<BucketDetailsDTO> findAllBucketDetailsDTOS();

    BucketDetails getBucketDetailsByProduct(Product product);

    void saveBucketDetails(BucketDetails bucketDetails);

    void deleteBucketDetailsById(int bucketDetailsId);

}
