package com.moskalev.service.impl;

import com.moskalev.domain.dto.impl.CargoDto.CargoDto;
import com.moskalev.domain.dto.impl.CargoDto.CargoToCreateDto;
import com.moskalev.domain.entity.Cargo;
import com.moskalev.domain.mapper.CargoMapper;
import com.moskalev.domain.mapper.mupstruct.MergeMapperForCargo;
import com.moskalev.exception.CustomException;
import com.moskalev.repositories.CargoRepository;
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
public class CargoService {

    private final CargoRepository cargoRepository;
    private final CargoMapper cargoMapper;
    private final MergeMapperForCargo mergeMapperForCargo;

    public void create(CargoToCreateDto cargoToCreateDto) {
        Optional<Cargo> cargoOptional = cargoRepository.findCargoByNumberOfCargo(cargoToCreateDto.getNumberOfCargo());
        if (!cargoOptional.isPresent()) {
            Cargo cargo = cargoMapper.createFromDto(cargoToCreateDto);
            cargoRepository.save(cargo);
        } else {
            throw new CustomException(String.format("Cargo with number : %s already exist ", cargoToCreateDto.getNumberOfCargo()));
        }
    }

    public void delete(String name) {
        Optional<Cargo> cargoOptional = cargoRepository.findCargoByNumberOfCargo(name);
        if (cargoOptional.isPresent()) {
            cargoRepository.deleteById(cargoOptional.get().getId());
        } else {
            throw new CustomException(String.format("Cargo with number : %s not found ", name));
        }
    }

    public CargoDto read(String name) {
        Optional<Cargo> cargoOptional = cargoRepository.findCargoByNumberOfCargo(name);
        if (cargoOptional.isPresent()) {
            Cargo cargo = cargoOptional.get();
            //  Hibernate.initialize(cargo.getWayPointList());
            CargoDto cargoDto = cargoMapper.convertToDto(cargo);
            return cargoDto;
        } else {
            throw new CustomException(String.format("Cargo with number : %s not found ", name));
        }
    }

    public List<CargoDto> readAll() {
        List<CargoDto> listCargo = cargoMapper.convertListToDto(cargoRepository.findAll());
        return listCargo;
    }

    public void update(String name, CargoDto cargoDto) {
        Optional<Cargo> cargoOptional = cargoRepository.findCargoByNumberOfCargo(name);
        if (cargoOptional.isPresent()) {
            Cargo target = cargoOptional.get();
            Cargo source = cargoMapper.convertFromDto(cargoDto);
            cargoRepository.save(mergeMapperForCargo.merge(target, source));
        } else {
            throw new CustomException(String.format("Cargo with name : %s not found ", name));
        }
    }
}
