package org.example.service;

import org.example.dao.BucketDAO;
import org.example.dto.BucketDTO;
import org.example.mappers.BucketMapper;
import org.example.models.Bucket;
import org.example.models.BucketStatus;
import org.example.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BucketServiceImpl implements BucketService {

    private final UserService userService;

    private final BucketMapper bucketMapper;

    private final BucketDAO bucketDAO;

    public BucketServiceImpl(UserService userService,
                             BucketMapper bucketMapper,
                             BucketDAO bucketDAO) {
        this.userService = userService;
        this.bucketMapper = bucketMapper;
        this.bucketDAO = bucketDAO;
    }

    @Override
    public BucketDTO findCurrentBucketOfUser(String username) {

        User user = userService.findUserByUsername(username);

        List<Bucket> buckets = findBucketsByUserAndBucketStatus(user, BucketStatus.CURRENT);

        // Проверим, есть ли текущая корзинка у пользователя
        if (!buckets.isEmpty()) {

            Bucket currentBucket = buckets.get(0);

            return bucketMapper.mapToBucketDTO(currentBucket);

        } else {

            return null;

        }

    }

    @Override
    public List<Bucket> findBucketsByUserAndBucketStatus(User user, BucketStatus bucketStatus) {

        return bucketDAO.findBucketsByUserAndStatus(user, bucketStatus);
    }

    @Override
    public Optional<Bucket> findBucketById(int bucketId) {
        return bucketDAO.findBucketById(bucketId);
    }

    @Override
    public void saveBucket(Bucket bucket) {

        bucketDAO.saveBucket(bucket);
    }

}
