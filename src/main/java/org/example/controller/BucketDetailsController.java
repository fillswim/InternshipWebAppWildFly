package org.example.controller;

import org.example.dto.BucketDetailsDTO;
import org.example.mappers.BucketDetailsMapper;
import org.example.models.BucketDetails;
import org.example.models.Info;
import org.example.service.BucketDetailsService;
import org.example.service.InfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/bucketdetails")
public class BucketDetailsController {

    private final InfoService infoService;
    private final BucketDetailsService bucketDetailsService;

    private final BucketDetailsMapper bucketDetailsMapper;

    public BucketDetailsController(InfoService infoService,
                                   BucketDetailsService bucketDetailsService,
                                   BucketDetailsMapper bucketDetailsMapper) {
        this.infoService = infoService;
        this.bucketDetailsService = bucketDetailsService;
        this.bucketDetailsMapper = bucketDetailsMapper;
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
    public String showAllBucketDetails(Model model) {

        List<BucketDetails> allBucketDetails = bucketDetailsService.findAllBucketDetails();

        List<BucketDetailsDTO> bucketDetailsDTOS = allBucketDetails.stream()
                .map(bucketDetailsMapper::mapToBucketDetailsDTO)
                .collect(Collectors.toList());

        model.addAttribute("bucketDetails", bucketDetailsDTOS);

        String header = getHeader();
        String footer = getFooter();
        model.addAttribute("mytitle", header);
        model.addAttribute("myfooter", footer);

        return "bucketdetails";
    }

}
