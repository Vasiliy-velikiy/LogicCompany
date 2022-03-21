package com.moskalev.domain.mapper;


import com.moskalev.domain.dto.impl.truckDto.TruckDto;
import com.moskalev.domain.dto.impl.truckDto.TruckToCreateDto;
import com.moskalev.domain.dto.impl.waypointDto.WayPointDto;
import com.moskalev.domain.dto.impl.waypointDto.WayPointToCreateDto;
import com.moskalev.domain.entity.Truck;
import com.moskalev.domain.entity.WayPoint;
import com.moskalev.repositories.CargoRepository;
import com.moskalev.repositories.CityRepository;
import com.moskalev.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WayMapper {

    private final OrderRepository orderRepository;
    private final CityRepository cityRepository;
    private final CargoRepository cargoRepository;


    /**
     * @param source -Person  object
     * @return PersonDto object
     */
    public WayPointDto toDto(WayPoint source) {
        WayPointDto wayPointDto = new WayPointDto();
        wayPointDto.setIsLoading(source.getIsLoading());
        wayPointDto.setId(source.getId());
        if (source.getOrderForWayPoint() != null) {
            wayPointDto.setOrderForWayPoint(orderRepository.findOrderByUniqueNumber(source.getOrderForWayPoint().getUniqueNumber()).get().getUniqueNumber());
        }
        if (source.getCurrentCargoForWayPoint() != null) {
            wayPointDto.setCurrentCargoForWayPoint(cargoRepository.findCargoByNumberOfCargo(source.getCurrentCargoForWayPoint().getNumberOfCargo()).get().getNumberOfCargo());
        }
        if (source.getCurrentCityForWayPoint() != null) {
            wayPointDto.setCurrentCityForWayPoint(cityRepository.findByName(source.getCurrentCityForWayPoint().getName()).get().getName());
        }

        return wayPointDto;
    }

    /**
     * @param source -PersonDto  object
     * @return Person object
     */
    public WayPoint fromDto(WayPointDto source) {
        WayPoint wayPoint = new WayPoint();
        wayPoint.setIsLoading(source.getIsLoading());
        if(!source.getOrderForWayPoint().isEmpty()){
            wayPoint.setOrderForWayPoint(orderRepository.findOrderByUniqueNumber(source.getOrderForWayPoint()).get());
        }
        if(!source.getCurrentCargoForWayPoint().isEmpty()){
            wayPoint.setCurrentCargoForWayPoint(cargoRepository.findCargoByNumberOfCargo(source.getCurrentCargoForWayPoint()).get());
        }
        if(!source.getCurrentCityForWayPoint().isEmpty()){
            wayPoint.setCurrentCityForWayPoint(cityRepository.findByName(source.getCurrentCityForWayPoint()).get());
        }
        return wayPoint;

    }

    /**
     * @param source -PersonToCreateDto object
     * @return Person object
     */
    public WayPoint fromCreateDto(WayPointToCreateDto source) {
        WayPoint wayPoint = new WayPoint();
        wayPoint.setIsLoading(source.getIsLoading());
        if(!source.getOrderForWayPoint().isEmpty()){
            wayPoint.setOrderForWayPoint(orderRepository.findOrderByUniqueNumber(source.getOrderForWayPoint()).get());
        }
        if(!source.getCurrentCargoForWayPoint().isEmpty()){
            wayPoint.setCurrentCargoForWayPoint(cargoRepository.findCargoByNumberOfCargo(source.getCurrentCargoForWayPoint()).get());
        }
        if(!source.getCurrentCityForWayPoint().isEmpty()){
            wayPoint.setCurrentCityForWayPoint(cityRepository.findByName(source.getCurrentCityForWayPoint()).get());
        }
        return wayPoint;
    }

    /**
     * @param objList - list of Person object
     * @return PersonDto list
     */
    public List<WayPointDto> convertListToDto(List<WayPoint> objList) {
        List<WayPointDto> wayPointDtos = new ArrayList<>();
        for (WayPoint wayPoint : objList) {
            wayPointDtos.add(toDto(wayPoint));
        }
        return wayPointDtos;
    }
}
