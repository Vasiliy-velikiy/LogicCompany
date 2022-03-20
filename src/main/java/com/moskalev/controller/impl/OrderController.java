package com.moskalev.controller.impl;

import com.moskalev.domain.dto.impl.orderDto.OrderDto;
import com.moskalev.domain.dto.impl.orderDto.OrderToCreateDto;
import com.moskalev.domain.dto.impl.personDto.PersonToCreateDto;
import com.moskalev.service.impl.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Negative;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/new")
    public String newOrder(@ModelAttribute("order") OrderToCreateDto order){
        return "order/new";
    }

    @GetMapping("/{uniqueNumber}/edit")
    public String edit(Model model, @PathVariable("uniqueNumber") String uniqueNumber){
        model.addAttribute("order",orderService.read(uniqueNumber));
        return "order/edit";
    }


    @PostMapping
    public String create(@ModelAttribute("order") @Valid OrderToCreateDto orderToCreateDto,Model model) {
        orderService.create(orderToCreateDto);
        return "redirect:/api/orders";
    }

    @DeleteMapping(path = "/{uniqueNumber}")
    public String delete(@ModelAttribute @PathVariable String uniqueNumber) {
        orderService.delete(uniqueNumber);
        return "redirect:/api/orders";
    }

//    @GetMapping(path = "/{id}")
//    public OrderDto read(@PathVariable Long id) {
//        return orderService.read(id);
//    }

    @GetMapping
    public String readAll(Model model) {
       model.addAttribute("orders", orderService.readAll());
        return "order/orderList";
    }

    @PatchMapping(path = "/{uniqueNumber}")
    public String update(@PathVariable String uniqueNumber, @ModelAttribute("order") OrderDto orderDto) {
        orderService.update(uniqueNumber, orderDto);
        return "redirect:/api/orders";
    }

}
