package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BucketDTO {

    private String created;

    private LocalDateTime updated;

    private double sum;

    private List<BucketDetailsDTO> bucketDetailsDTOS;

}
