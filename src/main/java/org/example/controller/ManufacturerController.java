package org.example.controller;

import org.example.dto.InfoDTO;
import org.example.dto.ManufacturerDTO;
import org.example.service.InfoService;
import org.example.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {

    private final InfoService infoService;
    private final ManufacturerService manufacturerService;

    public ManufacturerController(InfoService infoService,
                                  ManufacturerService manufacturerService) {
        this.infoService = infoService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String showAllManufacturers(Model model) {

        List<ManufacturerDTO> manufacturerDTOS = manufacturerService.getAllManufacturerDTOS();
        model.addAttribute("manufacturers", manufacturerDTOS);

        InfoDTO infoDTO = infoService.getInfoDTOBuId(0);
        model.addAttribute("infoDTO", infoDTO);

        return "manufacturers";
    }

}
