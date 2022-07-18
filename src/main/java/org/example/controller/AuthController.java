package org.example.controller;

import org.example.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginPage(Model model) {

        model.addAttribute("loginUser", new UserDTO());

        return "login";
    }

}
