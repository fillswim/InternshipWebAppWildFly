package org.example.service;

import org.example.models.Bucket;
import org.example.models.BucketStatus;
import org.example.models.User;

import java.util.Optional;

public interface BucketService {

    Optional<Bucket> findBucketByUserAndStatus(User user, BucketStatus bucketStatus);

    void saveBucket(Bucket bucket);

}
