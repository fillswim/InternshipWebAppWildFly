package org.example.controller;

import org.example.dto.InfoDTO;
import org.example.dto.UserDTO;
import org.example.service.InfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    private final InfoService infoService;

    public AuthController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {

        model.addAttribute("loginUser", new UserDTO());

        InfoDTO infoDTO = infoService.getInfoDTOBuId(0);
        model.addAttribute("infoDTO", infoDTO);

        return "login";
    }

}
