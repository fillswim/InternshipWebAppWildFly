package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDetailsDTO {

    private String productTitle;

    private double price;

    private int amount;

    private double sum;

}
