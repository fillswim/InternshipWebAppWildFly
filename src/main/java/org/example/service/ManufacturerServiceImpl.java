package org.example.service;

import org.example.dao.ManufacturerDAO;
import org.example.models.Manufacturer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerDAO manufacturerDAO;

    public ManufacturerServiceImpl(ManufacturerDAO manufacturerDAO) {
        this.manufacturerDAO = manufacturerDAO;
    }

    @Override
    public List<Manufacturer> getAllManufacturer() {
        return manufacturerDAO.getAllManufacturer();
    }

    @Override
    public Manufacturer getManufacturerById(int manufacturerId) {
        return manufacturerDAO.getManufacturerById(manufacturerId);
    }
}
