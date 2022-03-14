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

    public void delete(Long id) {
        Optional<Cargo> cargoOptional = cargoRepository.findById(id);
        if (cargoOptional.isPresent()) {
            cargoRepository.deleteById(id);
        } else {
            throw new CustomException(String.format("Cargo with id : %s not found ", id));
        }
    }

    public CargoDto read(Long id) {
        Optional<Cargo> cargoOptional = cargoRepository.findById(id);
        if (cargoOptional.isPresent()) {
            Cargo cargo = cargoOptional.get();
            //  Hibernate.initialize(cargo.getWayPointList());
            CargoDto cargoDto = cargoMapper.convertToDto(cargo);
            return cargoDto;
        } else {
            throw new CustomException(String.format("Cargo with id : %s not found ", id));
        }
    }

    public Page<CargoDto> readAll() {
        List<CargoDto> listCargo = cargoMapper.convertListToDto(cargoRepository.findAll());
        Pageable firstPageWithTwoElements = PageRequest.of(0, listCargo.size());
        return new PageImpl<>(listCargo, firstPageWithTwoElements, listCargo.size());
    }

    public void update(Long id, CargoDto cargoDto) {
        Optional<Cargo> cargoOptional = cargoRepository.findById(id);
        if (cargoOptional.isPresent()) {
            Cargo target = cargoOptional.get();
            Cargo source = cargoMapper.convertFromDto(cargoDto);
            cargoRepository.save(mergeMapperForCargo.merge(target, source));
        } else {
            throw new CustomException(String.format("Cargo with id : %s not found ", id));
        }
    }
}
