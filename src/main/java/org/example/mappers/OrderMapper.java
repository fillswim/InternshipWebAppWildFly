package org.example.mappers;

import org.example.dto.OrderDTO;
import org.example.models.Order;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class OrderMapper {

    private final BucketMapper bucketMapper;

    public OrderMapper(BucketMapper bucketMapper) {
        this.bucketMapper = bucketMapper;
    }

    public OrderDTO mapToOrderDTO(Order order) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy");

        return OrderDTO.builder()
                .id(order.getId())
                .date(order.getUpdated().format(formatter))
                .address(order.getAddress())
                .description(order.getDescription())
                .bucketDTO(bucketMapper.mapToBucketDTO(order.getBucket()))
                .build();

    }

}
