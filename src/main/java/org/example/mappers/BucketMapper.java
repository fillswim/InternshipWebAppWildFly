package org.example.mappers;

import org.example.dto.BucketDTO;
import org.example.dto.BucketDetailsDTO;
import org.example.models.Bucket;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BucketMapper {

    private final BucketDetailsMapper bucketDetailsMapper;

    public BucketMapper(BucketDetailsMapper bucketDetailsMapper) {
        this.bucketDetailsMapper = bucketDetailsMapper;
    }

    public BucketDTO mapToBucketDTO(Bucket bucket) {

        List<BucketDetailsDTO> bucketDetailsDTOS = bucket.getBucketDetailsList().stream()
                .map(bucketDetailsMapper::mapToBucketDetailsDTO)
                .collect(Collectors.toList());

        double sum = bucketDetailsDTOS.stream().mapToDouble(BucketDetailsDTO::getSum).sum();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");

        return BucketDTO.builder()
                .created(bucket.getCreated().format(formatter))
                .updated(bucket.getUpdated())
                .bucketDetailsDTOS(bucketDetailsDTOS)
                .sum(sum)
                .build();
    }

}
