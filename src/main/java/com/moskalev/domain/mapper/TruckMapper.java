package com.moskalev.domain.mapper;


import com.moskalev.domain.dto.impl.personDto.PersonDto;
import com.moskalev.domain.dto.impl.personDto.PersonToCreateDto;
import com.moskalev.domain.dto.impl.truckDto.TruckDto;
import com.moskalev.domain.dto.impl.truckDto.TruckToCreateDto;
import com.moskalev.domain.entity.Person;
import com.moskalev.domain.entity.Truck;
import com.moskalev.repositories.CityRepository;
import com.moskalev.repositories.DriverRepository;
import com.moskalev.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TruckMapper {
    private final CityRepository cityRepository;
    private final DriverRepository driverRepository;
    private final OrderRepository orderRepository;


    /**
     * @param source -Person  object
     * @return PersonDto object
     */
    public TruckDto toDto(Truck source) {
        TruckDto truckDto = new TruckDto();
        truckDto.setRegistrationNumber(source.getRegistrationNumber());
        truckDto.setChangeTimeOfDriver(source.getChangeTimeOfDriver());
        truckDto.setTruckCapacity(source.getTruckCapacity());
        truckDto.setWorkStatus(source.getWorkStatus());
        if (source.getCurrentCityforTruck() != null) {
            truckDto.setCity(cityRepository.findByName(source.getCurrentCityforTruck().getName()).get().getName());
        }
        if (source.getDriver() != null) {
            truckDto.setDriverNumber(driverRepository.findByPersonalNumber(source.getDriver().getPersonalNumber()).get().getPersonalNumber());
        }
        if (source.getOrder() != null) {
            truckDto.setOrderNumber(orderRepository.findOrderByUniqueNumber(source.getOrder().getUniqueNumber()).get().getUniqueNumber());
        }
        return truckDto;
    }

    /**
     * @param source -PersonDto  object
     * @return Person object
     */
    public Truck fromDto(TruckDto source) {
        Truck truck = new Truck();
        truck.setRegistrationNumber(source.getRegistrationNumber());
        truck.setChangeTimeOfDriver(source.getChangeTimeOfDriver());
        truck.setTruckCapacity(source.getTruckCapacity());
        truck.setWorkStatus(source.getWorkStatus());
        if (!source.getCity().isEmpty()) {
            truck.setCurrentCityforTruck(cityRepository.findByName(source.getCity()).get());
        }
        if (!source.getDriverNumber().isEmpty()) {
            truck.setDriver(driverRepository.findByPersonalNumber(source.getDriverNumber()).get());
        }
        if (!source.getOrderNumber().isEmpty()) {
            truck.setOrder(orderRepository.findOrderByUniqueNumber(source.getOrderNumber()).get());
        }
        return truck;

    }

    /**
     * @param source -PersonToCreateDto object
     * @return Person object
     */
    public Truck fromCreateDto(TruckToCreateDto source) {
        Truck truck = new Truck();
        truck.setRegistrationNumber(source.getRegistrationNumber());
        truck.setChangeTimeOfDriver(source.getChangeTimeOfDriver());
        truck.setTruckCapacity(source.getTruckCapacity());
        truck.setWorkStatus(source.getWorkStatus());
        if (!source.getCity().isEmpty()) {
            truck.setCurrentCityforTruck(cityRepository.findByName(source.getCity()).get());
        }
        if (!source.getDriverNumber().isEmpty()) {
            truck.setDriver(driverRepository.findByPersonalNumber(source.getDriverNumber()).get());
        }
        if (!source.getOrderNumber().isEmpty()) {
            truck.setOrder(orderRepository.findOrderByUniqueNumber(source.getOrderNumber()).get());
        }
        return truck;
    }

    /**
     * @param objList - list of Person object
     * @return PersonDto list
     */
    public List<TruckDto> convertListToDto(List<Truck> objList) {
        List<TruckDto> truckDtoList = new ArrayList<>();
        for (Truck truck : objList) {
            truckDtoList.add(toDto(truck));
        }
        return truckDtoList;
    }
}
