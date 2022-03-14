package com.moskalev.domain.mapper;


import com.moskalev.domain.dto.impl.orderDto.OrderDto;
import com.moskalev.domain.dto.impl.orderDto.OrderToCreateDto;
import com.moskalev.domain.entity.City;

import com.moskalev.domain.entity.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {


    public Order createFromDto(OrderToCreateDto orderToCreateDto) {
        Order order = new Order ();
        order.setIsCompleted(orderToCreateDto.getIsCompleted());
        order.setUniqueNumber(orderToCreateDto.getUniqueNumber());
        return order;
    }

    public Order convertFromDto(OrderDto orderDto) {
        Order order = new Order ();
        order.setIsCompleted(orderDto.getIsCompleted());
        order.setUniqueNumber(orderDto.getUniqueNumber());
        return order;
    }

    public OrderDto convertToDto(Order order) {
        OrderDto  orderDto = new OrderDto();
        orderDto.setIsCompleted(order.getIsCompleted());
        orderDto.setUniqueNumber(order.getUniqueNumber());
        return orderDto;
    }

    public List<Order> convertListFromDto(List<OrderDto> objList) {
        return objList.stream().map(this::convertFromDto).collect(Collectors.toList());
    }
    public List<OrderDto> convertListToDto(List<Order> objList) {
        return objList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
