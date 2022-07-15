package org.example.controller;

import org.example.models.Info;
import org.example.models.Manufacturer;
import org.example.service.InfoService;
import org.example.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    private String getHeader() {
        return infoService.getAllInfo().get(0).getName();
    }

    private String getFooter() {

        List<Info> infos = infoService.getAllInfo();
        Info info = infos.get(0);

        return info.getCopyright() + " " + info.getPeriod() + " " + info.getName();
    }

    @GetMapping
    public String showAllManufacturers(Model model) {

        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturer();
        model.addAttribute("manufacturers", manufacturers);

        String header = getHeader();
        String footer = getFooter();
        model.addAttribute("mytitle", header);
        model.addAttribute("myfooter", footer);

        return "manufacturers";
    }

//    @PostMapping("/addManufacturer")
//    public String addManufacturer(@ModelAttribute("manufacturer") Manufacturer manufacturer) {
//
//        System.out.println(manufacturer);
//
//        return "redirect:/manufacturers";
//    }

    @GetMapping("/addManufacturer")
    public String addManufacturer(@RequestParam("manufacturer") int manufacturerId) {

        System.out.println(manufacturerId);

        return "redirect:/manufacturers";
    }

}
