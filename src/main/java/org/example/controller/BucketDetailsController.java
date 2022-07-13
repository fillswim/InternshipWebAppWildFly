package org.example.controller;

import org.example.models.BucketDetails;
import org.example.service.BucketDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bucketdetails")
public class BucketDetailsController {

    private final BucketDetailsService bucketDetailsService;

    public BucketDetailsController(BucketDetailsService bucketDetailsService) {
        this.bucketDetailsService = bucketDetailsService;
    }

    @GetMapping
    public String showAllBucketDetails(Model model) {

        List<BucketDetails> bucketDetails = bucketDetailsService.findAllBucketDetails();
        model.addAttribute("bucketDetails", bucketDetails);

        return "bucketdetails";
    }

}
