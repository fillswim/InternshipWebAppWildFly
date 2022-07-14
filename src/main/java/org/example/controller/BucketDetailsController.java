package org.example.controller;

import org.example.models.BucketDetails;
import org.example.models.Info;
import org.example.service.BucketDetailsService;
import org.example.service.InfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bucketdetails")
public class BucketDetailsController {

    private final InfoService infoService;
    private final BucketDetailsService bucketDetailsService;

    public BucketDetailsController(InfoService infoService,
                                   BucketDetailsService bucketDetailsService) {
        this.infoService = infoService;
        this.bucketDetailsService = bucketDetailsService;
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

        List<BucketDetails> bucketDetails = bucketDetailsService.findAllBucketDetails();
        model.addAttribute("bucketDetails", bucketDetails);

        String header = getHeader();
        String footer = getFooter();
        model.addAttribute("mytitle", header);
        model.addAttribute("myfooter", footer);

        return "bucketdetails";
    }

}
