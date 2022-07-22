package org.example.dao;

import org.example.models.Bucket;
import org.example.models.BucketStatus;
import org.example.models.User;

import java.util.List;
import java.util.Optional;

public interface BucketDAO {

    List<Bucket> findBucketsByUserAndStatus(User user, BucketStatus bucketStatus);

    void saveBucket(Bucket bucket);

    Optional<Bucket> findBucketById(int bucketId);

}
