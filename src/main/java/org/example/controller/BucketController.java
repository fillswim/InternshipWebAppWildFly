package org.example.controller;

import org.example.dto.BucketDTO;
import org.example.dto.InfoDTO;
import org.example.dto.OrderDTO;
import org.example.service.BucketService;
import org.example.service.InfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/buckets")
public class BucketController {

    private final InfoService infoService;

    private final BucketService bucketService;

    public BucketController(InfoService infoService,
                            BucketService bucketService) {
        this.infoService = infoService;
        this.bucketService = bucketService;
    }

    @GetMapping
    public String showCurrentBucketOfUser(Model model, Principal principal) {

        if (principal != null) {

            String username = principal.getName();

            BucketDTO currentBucket = bucketService.findCurrentBucketOfUser(username);
            model.addAttribute("bucket", currentBucket);

            OrderDTO orderDTO = new OrderDTO();
            model.addAttribute("order", orderDTO);

            InfoDTO infoDTO = infoService.getInfoDTOBuId(0);
            model.addAttribute("infoDTO", infoDTO);

            return "bucket";
        } else {

            return "login";
        }

    }


}
