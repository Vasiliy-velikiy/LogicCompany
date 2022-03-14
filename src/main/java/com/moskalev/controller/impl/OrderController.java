package com.moskalev.controller.impl;

import com.moskalev.domain.dto.impl.cityDto.CityDto;
import com.moskalev.domain.dto.impl.cityDto.CityToCreateDto;
import com.moskalev.domain.dto.impl.orderDto.OrderToCreateDto;
import com.moskalev.service.impl.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public void create(@RequestBody OrderToCreateDto orderToCreateDto) {
        orderService.create(orderToCreateDto);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
//    //
//    @GetMapping(path = "/{id}")
//    public CityDto read(@PathVariable Long id) {
//        return orderService.read(id);
//    }
//
//    @GetMapping
//    public Page<CityDto> readAll() {
//        return orderService.readAll();
//    }
//
//    @PatchMapping(path = "/{id}")
//    public void update(@PathVariable Long id, @RequestBody CityDto cityDto) {
//        orderService.update(id, cityDto);
//    }

}
