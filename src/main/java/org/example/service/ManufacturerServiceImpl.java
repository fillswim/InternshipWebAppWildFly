package org.example.service;

import org.example.dao.ManufacturerDAO;
import org.example.dto.ManufacturerDTO;
import org.example.mappers.ManufacturerMapper;
import org.example.models.Manufacturer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerDAO manufacturerDAO;

    private final ManufacturerMapper manufacturerMapper;

    public ManufacturerServiceImpl(ManufacturerDAO manufacturerDAO,
                                   ManufacturerMapper manufacturerMapper) {
        this.manufacturerDAO = manufacturerDAO;
        this.manufacturerMapper = manufacturerMapper;
    }

    @Override
    public List<ManufacturerDTO> getAllManufacturerDTOS() {
        return manufacturerDAO.getAllManufacturer().stream()
                .map(manufacturerMapper::mapToManufacturerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Manufacturer getManufacturerById(int manufacturerId) {
        return manufacturerDAO.getManufacturerById(manufacturerId);
    }
}
