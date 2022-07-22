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
    public List<BucketDetailsDTO> findAllBucketDetailsForCurrentBucketOfUser(String username) {

        User user = userService.findUserByUsername(username);

        List<Bucket> buckets = bucketService.findBucketsByUserAndBucketStatus(user, BucketStatus.CURRENT);

        List<BucketDetailsDTO> bucketDetailsDTOS = null;

        // Если корзинка существует,
        if (!buckets.isEmpty()) {

            Bucket bucket = buckets.get(0);

            List<BucketDetails> bucketDetailsList = findBucketDetailsByBucket(bucket);

            // то смотрим, есть ли в ней детали заказов
            if (!bucketDetailsList.isEmpty()) {

                bucketDetailsDTOS = bucketDetailsList.stream()
                        .map(bucketDetailsMapper::mapToBucketDetailsDTO)
                        .collect(Collectors.toList());
            }

        }

        return bucketDetailsDTOS;
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
    public List<BucketDetails> findBucketDetailsByBucket(Bucket bucket) {
        return bucketDetailsDAO.findBucketDetailsByBucket(bucket);
    }

    @Override
    public List<BucketDetailsDTO> findBucketDetailsDTOByBucket(Bucket bucket) {

        return findBucketDetailsByBucket(bucket).stream()
                .map(bucketDetailsMapper::mapToBucketDetailsDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BucketDetails> findBucketDetailsByBucketAndProduct(Bucket bucket, Product product) {
        return bucketDetailsDAO.findBucketDetailsByBucketAndProduct(bucket, product);
    }
}
