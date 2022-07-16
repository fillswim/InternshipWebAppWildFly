package org.example.mappers;

import org.example.dto.InfoDTO;
import org.example.models.Info;
import org.springframework.stereotype.Service;

@Service
public class InfoMapper {

    public InfoDTO mapToInfoDTO(Info info) {

        return InfoDTO.builder()
                .header(info.getName())
                .footer(info.getCopyright() + " " + info.getPeriod() + " " + info.getName())
                .build();
    }

}
