package com.moskalev.service.impl;

import com.moskalev.domain.dto.impl.driverDto.DriverDto;
import com.moskalev.domain.dto.impl.driverDto.DriverToCreateDto;
import com.moskalev.domain.dto.impl.orderDto.OrderDto;
import com.moskalev.domain.dto.impl.orderDto.OrderToCreateDto;
import com.moskalev.domain.entity.Driver;
import com.moskalev.domain.entity.Order;
import com.moskalev.domain.mapper.DriverMapper;
import com.moskalev.domain.mapper.mupstruct.MergeMapperForDriver;
import com.moskalev.exception.CustomException;
import com.moskalev.repositories.DriverRepository;
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
@Transactional
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final MergeMapperForDriver mergeMapperForDriver;

    public void create(DriverToCreateDto driverToCreateDto) {
        Optional<Driver> cityOptional = driverRepository.findByPersonalNumber(driverToCreateDto.getPersonalNumber());
        if(!cityOptional.isPresent()){
            driverRepository.save(driverMapper.createFromDto(driverToCreateDto));
        }else {
            throw new CustomException(String.format("Driver with personal number : %s already exist ", driverToCreateDto.getPersonalNumber()));
        }
    }

    public void delete(String number) {
        Optional<Driver> driverOptional = driverRepository.findByPersonalNumber( number);
        if (driverOptional.isPresent()) {
            driverRepository.deleteById(driverOptional.get().getId());
        } else {
            throw new CustomException(String.format("Driver with id : %s not found ",  number));
        }
    }

    public DriverDto read(String number) {
        Optional<Driver> driverOptional = driverRepository.findByPersonalNumber(number);
        if (driverOptional.isPresent()) {
           Driver driver = driverOptional.get();
            //  Hibernate.initialize(cargo.getWayPointList());
            DriverDto driverDto = driverMapper.convertToDto(driver);
            return driverDto;
        } else {
            throw new CustomException(String.format("Driver with id : %s not found ", number));
        }
    }

    public List<DriverDto> readAll() {
        List<DriverDto> driverDtoList = driverMapper.convertListToDto(driverRepository.findAll());
        return driverDtoList;
    }

    public void update(String number, DriverDto driverDto) {
        Optional<Driver> driverOptional = driverRepository.findByPersonalNumber(number);
        if (driverOptional.isPresent()) {
            Driver target = driverOptional.get();
            Driver source = driverMapper.convertFromDto(driverDto);
            driverRepository.save(mergeMapperForDriver.merge(target, source));
        } else {
            throw new CustomException(String.format("Driver with id : %s not found ", number));
        }
    }
}
