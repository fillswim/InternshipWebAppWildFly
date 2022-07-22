package org.example.controller;

import org.example.dto.BucketDetailsDTO;
import org.example.dto.InfoDTO;
import org.example.mappers.BucketDetailsMapper;
import org.example.models.BucketStatus;
import org.example.service.BucketDetailsService;
import org.example.service.InfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

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

}
