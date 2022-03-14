package com.moskalev.domain.mapper;

import com.moskalev.domain.dto.impl.CargoDto.CargoDto;
import com.moskalev.domain.dto.impl.CargoDto.CargoToCreateDto;
import com.moskalev.domain.entity.Cargo;
import com.moskalev.domain.entity.WayPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CargoMapper {

    public Cargo createFromDto(CargoToCreateDto cargoDto) {
        Cargo cargo = new Cargo();
        cargo.setNameOfCargo(cargoDto.getNameOfCargo());
        cargo.setStatusOfCargo(cargoDto.getStatusOfCargo());
        cargo.setNumberOfCargo(cargoDto.getNumberOfCargo());
        cargo.setWeight(cargoDto.getWeight());
        return cargo;
    }

    public Cargo convertFromDto(CargoDto cargoDto) {
        Cargo cargo = new Cargo();
        cargo.setNameOfCargo(cargoDto.getNameOfCargo());
        cargo.setStatusOfCargo(cargoDto.getStatusOfCargo());
        cargo.setNumberOfCargo(cargoDto.getNumberOfCargo());
        cargo.setWeight(cargoDto.getWeight());
        cargo.setWayPointList(cargo.getWayPointList());
        return cargo;
    }

    public CargoDto convertToDto(Cargo cargo) {
        CargoDto cargoDto = new CargoDto();
        cargoDto.setNameOfCargo(cargo.getNameOfCargo());
        cargoDto.setStatusOfCargo(cargo.getStatusOfCargo());
        cargoDto.setNumberOfCargo(cargo.getNumberOfCargo());
        cargoDto.setWeight(cargo.getWeight());
        //  cargoDto.setWayPoints(cargo.getWayPointList());
        return cargoDto;
    }

    public List<Cargo> convertListFromDto(List<CargoDto> objList) {
        return objList.stream().map(this::convertFromDto).collect(Collectors.toList());
    }

    public List<CargoDto> convertListToDto(List<Cargo> objList) {
        return objList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
