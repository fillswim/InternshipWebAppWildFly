package org.example.dao;

import org.example.models.Manufacturer;

import java.util.List;

public interface ManufacturerDAO {

    List<Manufacturer> getAllManufacturer();

    Manufacturer getManufacturerById(int manufacturerId);

}
