package org.example.controller;

import org.example.models.Info;
import org.example.models.OrderItem;
import org.example.service.InfoService;
import org.example.service.OrderItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyController {
    private final OrderItemService orderItemService;
    private final InfoService infoService;

    public MyController(OrderItemService orderItemService,
                        InfoService infoService) {
        this.orderItemService = orderItemService;
        this.infoService = infoService;
    }

    private String getHeader() {
        return infoService.getAllInfo().get(0).getName();
    }

    private String getFooter() {

        List<Info> infos = infoService.getAllInfo();
        Info info = infos.get(0);

        return info.getCopyright() + " " + info.getPeriod() + " " + info.getName();
    }

    @RequestMapping()
    public String showAllOrderItems(Model model) {

        List<OrderItem> orderItems = orderItemService.getAllOrderItems();

        String header = getHeader();
        String footer = getFooter();

        model.addAttribute("allOrderItems", orderItems);
        model.addAttribute("mytitle", header);
        model.addAttribute("myfooter", footer);

        return "orderItems-All";
    }

    @RequestMapping("/addNewOrderItem")
    public String addNewOrderItem(Model model) {

        OrderItem orderItem = new OrderItem();
        model.addAttribute("orderItem", orderItem);

        String header = getHeader();
        String footer = getFooter();

        model.addAttribute("mytitle", header);
        model.addAttribute("myfooter", footer);

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
