package org.example.mappers;

import org.example.dto.OrderDTO;
import org.example.models.Bucket;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {

    public OrderDTO mapToOrderDTO(Bucket bucket) {

        return OrderDTO.builder()
                .id(bucket.getId())
                .date(bucket.getUpdated())
                .status(bucket.getBucketStatus().name())
                .sum(0.0)
                .build();

    }

}
