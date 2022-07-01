package org.example.controller;

import org.example.models.OrderItem;
import org.example.service.OrderItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
//@RequestMapping("/test/app01")
//@RequestMapping("/")
public class MyController {
    private final OrderItemService orderItemService;

    public MyController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @RequestMapping("/")
    public String showAllOrderItems(Model model) {

        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        model.addAttribute("allOrderItems", orderItems);

        return "orderItems-All";
    }

    @RequestMapping("/addNewOrderItem")
    public String addNewOrderItem(Model model) {

        OrderItem orderItem = new OrderItem();
        model.addAttribute("orderItem", orderItem);

        return "orderItem-Details";
    }

    @RequestMapping("/saveOrderItem")
    public String saveOrderItem(@ModelAttribute("orderItem") OrderItem orderItem) {

        orderItemService.saveOrderItem(orderItem);

        return "redirect:/";
    }

    @RequestMapping("/updateOrderItem")
    public String updateOrderItem(@RequestParam("itemId") int id, Model model) {

        OrderItem orderItem = orderItemService.getOrderItem(id);
        model.addAttribute("orderItem", orderItem);

        return "orderItem-Details";
    }

    @RequestMapping("/deleteOrderItem")
    public String deleteOrderItem(@RequestParam("itemId") int id) {

        orderItemService.deleteOrderItem(id);

        return "redirect:/";
    }

}
