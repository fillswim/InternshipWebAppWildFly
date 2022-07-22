package org.example.controller;

import org.example.dto.BucketDetailsDTO;
import org.example.dto.InfoDTO;
import org.example.mappers.BucketDetailsMapper;
import org.example.service.BucketDetailsService;
import org.example.service.InfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/buckets")
public class BucketController {

    private final InfoService infoService;
    private final BucketDetailsService bucketDetailsService;

    private final BucketDetailsMapper bucketDetailsMapper;

    public BucketController(InfoService infoService,
                            BucketDetailsService bucketDetailsService,
                            BucketDetailsMapper bucketDetailsMapper) {
        this.infoService = infoService;
        this.bucketDetailsService = bucketDetailsService;
        this.bucketDetailsMapper = bucketDetailsMapper;
    }

    @GetMapping
    public String showCurrentBucketOfUser(Model model, Principal principal) {

        if (principal != null) {

            String username = principal.getName();

            List<BucketDetailsDTO> bucketDetailsDTOS =
                    bucketDetailsService.findAllBucketDetailsForCurrentBucketOfUser(username);
            model.addAttribute("bucketDetails", bucketDetailsDTOS);

            InfoDTO infoDTO = infoService.getInfoDTOBuId(0);
            model.addAttribute("infoDTO", infoDTO);

            return "bucket";
        } else {

            return "login";
        }

    }


}