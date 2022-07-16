package org.example.service;

import org.example.dao.InfoDAO;
import org.example.dto.InfoDTO;
import org.example.mappers.InfoMapper;
import org.example.models.Info;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class InfoServiceImpl implements InfoService{

    private final InfoDAO infoDAO;

    private final InfoMapper infoMapper;

    public InfoServiceImpl(InfoDAO infoDAO,
                           InfoMapper infoMapper) {
        this.infoDAO = infoDAO;
        this.infoMapper = infoMapper;
    }

    @Override
    public InfoDTO getInfoDTOBuId(int infoId) {

        Info info = infoDAO.getAllInfo().get(infoId);

        return infoMapper.mapToInfoDTO(info);
    }
}
