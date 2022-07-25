package org.example.controller;

import org.example.dto.InfoDTO;
import org.example.dto.OrderDTO;
import org.example.service.InfoService;
import org.example.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final InfoService infoService;

    public OrderController(OrderService orderService,
                           InfoService infoService) {
        this.orderService = orderService;
        this.infoService = infoService;
    }

    @GetMapping
    public String showAllOrders(Model model, Principal principal) {

        if (principal != null) {

            String username = principal.getName();

            List<OrderDTO> orderDTOS = orderService.getAllOrders(username);
            model.addAttribute("orders", orderDTOS);

            InfoDTO infoDTO = infoService.getInfoDTOBuId(0);
            model.addAttribute("infoDTO", infoDTO);

            return "orders";
        } else {

            return "login";
        }

    }


    @GetMapping("/createOrder")
    public String createOrder(Principal principal) {

        String username = principal.getName();

        orderService.createOrder(username);

        return "redirect:/orders";
    }

    @GetMapping("/showOrder")
    public String showOrder(@RequestParam("orderId") int orderId, Model model) {

        OrderDTO orderDTO = orderService.findOrderByUsernameAndId(orderId);

        model.addAttribute("order", orderDTO);

        InfoDTO infoDTO = infoService.getInfoDTOBuId(0);
        model.addAttribute("infoDTO", infoDTO);

        return "order";


    }

}
