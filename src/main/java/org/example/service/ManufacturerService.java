package org.example.service;

import org.example.dto.ManufacturerDTO;
import org.example.models.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    List<ManufacturerDTO> getAllManufacturerDTOS();

    Manufacturer getManufacturerById(int manufacturerId);

}
