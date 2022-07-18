package org.example.controller;

import org.example.dto.InfoDTO;
import org.example.dto.UserDTO;
import org.example.service.InfoService;
import org.example.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final InfoService infoService;

    public UserController(UserService userService,
                          InfoService infoService) {
        this.userService = userService;
        this.infoService = infoService;
    }

    @GetMapping
    public String showAllProducts(Model model) {

        List<UserDTO> userDTOS = userService.getAllUserDTOS();
        model.addAttribute("users", userDTOS);

        InfoDTO infoDTO = infoService.getInfoDTOBuId(0);
        model.addAttribute("infoDTO", infoDTO);

        return "users";
    }

}
