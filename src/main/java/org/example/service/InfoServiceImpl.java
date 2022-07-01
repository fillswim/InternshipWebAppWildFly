package org.example.service;

import org.example.dao.InfoDAO;
import org.example.models.Info;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InfoServiceImpl implements InfoService{

    private final InfoDAO infoDAO;

    public InfoServiceImpl(InfoDAO infoDAO) {
        this.infoDAO = infoDAO;
    }

    @Override
    public List<Info> getAllInfo() {
        return infoDAO.getAllInfo();
    }
}
