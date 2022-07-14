package org.example.service;

import org.example.dao.BucketDetailsDAO;
import org.example.models.BucketDetails;
import org.example.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BucketDetailsServiceImpl implements BucketDetailsService{

    private final BucketDetailsDAO bucketDetailsDAO;

    public BucketDetailsServiceImpl(BucketDetailsDAO bucketDetailsDAO) {
        this.bucketDetailsDAO = bucketDetailsDAO;
    }

    @Override
    public List<BucketDetails> findAllBucketDetails() {
        return bucketDetailsDAO.findAllBucketDetails();
    }

    @Override
    public BucketDetails getBucketDetailsByProduct(int productId) {
        return bucketDetailsDAO.getBucketDetailsByProduct(productId);
    }

    @Override
    public BucketDetails getBucketDetailsByProduct(Product product) {
        return bucketDetailsDAO.getBucketDetailsByProduct(product);
    }

    @Override
    public void saveBucketDetails(BucketDetails bucketDetails) {
        bucketDetailsDAO.saveBucketDetails(bucketDetails);
    }

    @Override
    public void deleteBucketDetailsById(int bucketDetailsId) {
        bucketDetailsDAO.deleteBucketDetailsById(bucketDetailsId);

    }
}
