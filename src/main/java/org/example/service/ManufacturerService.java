package org.example.service;

import org.example.models.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    List<Manufacturer> getAllManufacturer();

    Manufacturer getManufacturerById(int manufacturerId);

}
