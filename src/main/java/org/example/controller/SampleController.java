package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/app01")
public class SampleController {

    @RequestMapping("/sample")
    public String showFirstView() {
        return "sample";
    }

}
