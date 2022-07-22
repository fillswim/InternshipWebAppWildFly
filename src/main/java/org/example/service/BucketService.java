package org.example.service;

import org.example.models.Bucket;
import org.example.models.BucketStatus;
import org.example.models.User;

import java.util.List;
import java.util.Optional;

public interface BucketService {

    List<Bucket> findBucketsByUserAndBucketStatus(User user, BucketStatus bucketStatus);

    Optional<Bucket> findBucketById(int bucketId);

    void saveBucket(Bucket bucket);

}
