package org.example.controller;

import org.example.dto.InfoDTO;
import org.example.dto.UserDTO;
import org.example.service.InfoService;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final InfoService infoService;

    private final UserService userService;

    public AuthController(InfoService infoService,
                          UserService userService) {
        this.infoService = infoService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                Model model) {

        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }

        model.addAttribute("loginUser", new UserDTO());

        InfoDTO infoDTO = infoService.getInfoDTOBuId(0);
        model.addAttribute("infoDTO", infoDTO);

        return "login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {

        model.addAttribute("registerUser", new UserDTO());

        InfoDTO infoDTO = infoService.getInfoDTOBuId(0);
        model.addAttribute("infoDTO", infoDTO);

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") UserDTO userDTO, Model model) {

        if (userService.saveUser(userDTO)) {

            String message = "User " + userDTO.getUsername() + " is successfully registered. " +
                    "\nPlease login as " + userDTO.getUsername();

            model.addAttribute("msg", message);
        } else {

            model.addAttribute("error", "The passwords don't match");
            model.addAttribute("registerUser", userDTO);

        }

        return "register";
    }

}
