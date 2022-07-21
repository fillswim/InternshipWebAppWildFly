package org.example.service;

import org.example.dao.BucketDetailsDAO;
import org.example.dto.BucketDetailsDTO;
import org.example.mappers.BucketDetailsMapper;
import org.example.models.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BucketDetailsServiceImpl implements BucketDetailsService{

    private final BucketDetailsDAO bucketDetailsDAO;

    private final UserService userService;

    private final BucketService bucketService;

    private final BucketDetailsMapper bucketDetailsMapper;

    public BucketDetailsServiceImpl(BucketDetailsDAO bucketDetailsDAO,
                                    UserService userService,
                                    BucketService bucketService,
                                    BucketDetailsMapper bucketDetailsMapper) {
        this.bucketDetailsDAO = bucketDetailsDAO;
        this.userService = userService;
        this.bucketService = bucketService;
        this.bucketDetailsMapper = bucketDetailsMapper;
    }

    @Override
    public List<BucketDetailsDTO> findAllBucketDetailsForUserDTOS(String username, BucketStatus bucketStatus) {

        User user = userService.findUserByUsername(username);

        Optional<Bucket> bucketOptional = bucketService.findBucketByUserAndStatus(user, bucketStatus);

        if (bucketOptional.isPresent()) {
            return bucketDetailsDAO.findBucketDetailsByBucket(bucketOptional.get()).stream()
                    .map(bucketDetailsMapper::mapToBucketDetailsDTO)
                    .collect(Collectors.toList());
        } else {

            return null;
        }

    }

    @Override
    public void saveBucketDetails(BucketDetails bucketDetails) {
        bucketDetailsDAO.saveBucketDetails(bucketDetails);
    }

    @Override
    public void deleteBucketDetails(BucketDetails bucketDetails) {
        bucketDetailsDAO.deleteBucketDetails(bucketDetails);
    }

    @Override
    public Optional<BucketDetails> findBucketDetailsByBucketAndProduct(Bucket bucket, Product product) {
        return bucketDetailsDAO.findBucketDetailsByBucketAndProduct(bucket, product);
    }
}
