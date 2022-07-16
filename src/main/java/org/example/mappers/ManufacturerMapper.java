package org.example.mappers;

import org.example.dto.ManufacturerDTO;
import org.example.models.Manufacturer;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerMapper {

    public ManufacturerDTO mapToManufacturerDTO(Manufacturer manufacturer) {

        return ManufacturerDTO.builder()
                .id(manufacturer.getId())
                .title(manufacturer.getTitle())
                .address(manufacturer.getAddress())
                .build();
    }

}
