package com.moskalev.domain.mapper;

import com.moskalev.domain.dto.impl.driverDto.DriverDto;
import com.moskalev.domain.dto.impl.driverDto.DriverToCreateDto;
import com.moskalev.domain.dto.impl.orderDto.OrderDto;
import com.moskalev.domain.entity.*;
import com.moskalev.domain.entity.Driver;
import com.moskalev.repositories.CityRepository;
import com.moskalev.repositories.OrderRepository;
import com.moskalev.repositories.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class DriverMapper {

private final CityRepository cityRepository;
private final TruckRepository truckRepository;
private final OrderRepository orderRepository;

    public Driver createFromDto(DriverToCreateDto driverToCreateDto) {
        Driver driver = new  Driver ();
        driver.setFirstName(driverToCreateDto.getFirstName());
        driver.setLastName(driverToCreateDto.getLastName());
        driver.setPersonalNumber(driverToCreateDto.getPersonalNumber());
        driver.setHoursWorkedPerMonth(driverToCreateDto.getHoursWorkedPerMonth());
        driver.setStatusOfDriver(driverToCreateDto.getStatusOfDriver());
        if(driverToCreateDto.getCityIdForDriver()!=null || driverToCreateDto.getCurrentTruckId()!=null
                || driverToCreateDto.getOrderId()!=null){
            Truck truck = truckRepository.findById(driverToCreateDto.getCurrentTruckId()).get();
            City city=cityRepository.findById(driverToCreateDto.getCityIdForDriver()).get();
            Order order=orderRepository.findById(driverToCreateDto.getOrderId()).get();
            driver.setOrderForDriver(order);
            driver.setCurrentTruck(truck);
            driver.setCurrentCityforDriver(city);
        }

        return driver;
    }

    public  Driver convertFromDto( DriverDto driverDto) {
        Driver driver = new  Driver ();
        driver.setFirstName(driverDto.getFirstName());
        driver.setLastName(driverDto.getLastName());
        driver.setPersonalNumber(driverDto.getPersonalNumber());
        driver.setHoursWorkedPerMonth(driverDto.getHoursWorkedPerMonth());
        driver.setStatusOfDriver(driverDto.getStatusOfDriver());
        if(driverDto.getCurrentCityForDriver()!=null || driverDto.getCurrentNumberOfTruckForDriver()!=null
                || driverDto.getCurrentOrderOfNumber()!=null){
            Truck truck = truckRepository.findByRegistrationNumber(driverDto.getCurrentNumberOfTruckForDriver()).get();
            City city=cityRepository.findByName(driverDto.getCurrentCityForDriver()).get();
            Order order=orderRepository.findOrderByUniqueNumber(driverDto.getCurrentOrderOfNumber()).get();
            driver.setOrderForDriver(order);
            driver.setCurrentTruck(truck);
            driver.setCurrentCityforDriver(city);
        }

        return driver;
    }

    public DriverDto convertToDto(Driver driver) {
        DriverDto driverDto = new DriverDto();
        driverDto.setFirstName(driver.getFirstName());
        driverDto.setLastName(driver.getLastName());
        driverDto.setPersonalNumber(driver.getPersonalNumber());
        driverDto.setHoursWorkedPerMonth(driver.getHoursWorkedPerMonth());
        driverDto.setStatusOfDriver(driver.getStatusOfDriver());

        if(driver.getCurrentCityforDriver()!=null || driver.getCurrentTruck()!=null
                || driver.getOrderForDriver()!=null){
            Truck truck = truckRepository.findByRegistrationNumber(driver.getCurrentTruck().getRegistrationNumber()).get();
            City city=cityRepository.findByName(driver.getCurrentCityforDriver().getName()).get();
            Order order=orderRepository.findOrderByUniqueNumber(driver.getOrderForDriver().getUniqueNumber()).get();
            driverDto.setCurrentOrderOfNumber(order.getUniqueNumber());
            driverDto.setCurrentNumberOfTruckForDriver(truck.getRegistrationNumber());
            driverDto.setCurrentCityForDriver(city.getName());
        }
        return driverDto;
    }

    public List<Driver> convertListFromDto(List<DriverDto> objList) {
        return objList.stream().map(this::convertFromDto).collect(Collectors.toList());
    }
    public List<DriverDto> convertListToDto(List<Driver> objList) {
        return objList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
