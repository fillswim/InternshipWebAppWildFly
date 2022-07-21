package org.example.dao;

import org.example.models.Bucket;
import org.example.models.BucketStatus;
import org.example.models.User;

import java.util.Optional;

public interface BucketDAO {

    Optional<Bucket> findBucketByUserAndStatus(User user, BucketStatus bucketStatus);

    void saveBucket(Bucket bucket);

}
