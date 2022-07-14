package org.example.mappers;

import org.example.dto.BucketDetailsDTO;
import org.example.models.BucketDetails;
import org.springframework.stereotype.Service;

@Service
public class BucketDetailsMapper {

    public BucketDetailsDTO mapToBucketDetailsDTO(BucketDetails bucketDetails) {

        return BucketDetailsDTO.builder()
                .productTitle(bucketDetails.getProduct().getTitle())
                .price(bucketDetails.getProduct().getPrice())
                .amount(bucketDetails.getAmount())
                .sum(bucketDetails.getProduct().getPrice() * bucketDetails.getAmount())
                .build();
    }



}
