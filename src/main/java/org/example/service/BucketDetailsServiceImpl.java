package org.example.service;

import org.example.dao.BucketDetailsDAO;
import org.example.dto.BucketDetailsDTO;
import org.example.mappers.BucketDetailsMapper;
import org.example.models.BucketDetails;
import org.example.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BucketDetailsServiceImpl implements BucketDetailsService{

    private final BucketDetailsDAO bucketDetailsDAO;

    private final BucketDetailsMapper bucketDetailsMapper;

    public BucketDetailsServiceImpl(BucketDetailsDAO bucketDetailsDAO,
                                    BucketDetailsMapper bucketDetailsMapper) {
        this.bucketDetailsDAO = bucketDetailsDAO;
        this.bucketDetailsMapper = bucketDetailsMapper;
    }

    @Override
    public List<BucketDetailsDTO> findAllBucketDetailsDTOS() {
        return bucketDetailsDAO.findAllBucketDetails().stream()
                .map(bucketDetailsMapper::mapToBucketDetailsDTO)
                .collect(Collectors.toList());
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
