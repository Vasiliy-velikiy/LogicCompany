package com.moskalev.service.impl;



import com.moskalev.domain.dto.impl.orderDto.OrderDto;
import com.moskalev.domain.dto.impl.orderDto.OrderToCreateDto;

import com.moskalev.domain.entity.Order;
import com.moskalev.domain.mapper.OrderMapper;
import com.moskalev.domain.mapper.mupstruct.MergeMapperForOrder;

import com.moskalev.exception.CustomException;
import com.moskalev.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final MergeMapperForOrder mergeMapperForOrder;

    public void create(OrderToCreateDto orderToCreateDto) {
        Optional<Order> cityOptional = orderRepository.findOrderByUniqueNumber(orderToCreateDto.getUniqueNumber());
        if(!cityOptional.isPresent()){
            orderRepository.save(orderMapper.createFromDto(orderToCreateDto));
        }else {
            throw new CustomException(String.format("Order with unique numbers : %s already exist ", orderToCreateDto.getUniqueNumber()));
        }
    }

    public void delete(Long id) {
        Optional<Order> cityOptional = orderRepository.findById(id);
        if (cityOptional.isPresent()) {
            orderRepository.deleteById(id);
        } else {
            throw new CustomException(String.format("City with id : %s not found ", id));
        }
    }

    public OrderDto read(Long id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            //  Hibernate.initialize(cargo.getWayPointList());
            OrderDto orderDto = orderMapper.convertToDto(order);
            return orderDto;
        } else {
            throw new CustomException(String.format("City with id : %s not found ", id));
        }
    }

    public Page<OrderDto> readAll() {
        List<OrderDto> orderDtoList = orderMapper.convertListToDto(orderRepository.findAll());
        Pageable firstPageWithTwoElements = PageRequest.of(0, orderDtoList.size());
        return new PageImpl<>(orderDtoList, firstPageWithTwoElements, orderDtoList.size());
    }

    public void update(Long id, OrderDto cityDto) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order target = orderOptional.get();
            Order source = orderMapper.convertFromDto(cityDto);
            orderRepository.save(mergeMapperForOrder.merge(target, source));
        } else {
            throw new CustomException(String.format("City with id : %s not found ", id));
        }
    }
}
