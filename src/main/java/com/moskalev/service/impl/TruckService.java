package com.moskalev.service.impl;


import com.moskalev.domain.dto.impl.truckDto.TruckDto;
import com.moskalev.domain.dto.impl.truckDto.TruckToCreateDto;

import com.moskalev.domain.entity.Truck;
import com.moskalev.domain.mapper.TruckMapper;
import com.moskalev.domain.mapper.mupstruct.MergeMapperForTruck;
import com.moskalev.exception.CustomException;
import com.moskalev.repositories.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TruckService {

    private final TruckRepository truckRepository;
    private final TruckMapper truckMapper;
    private final MergeMapperForTruck mergeMapperForTruck;

    public void create(TruckToCreateDto truckToCreateDto) {
        Optional<Truck> truckOptional = truckRepository.findByRegistrationNumber(truckToCreateDto.getRegistrationNumber());
        if(!truckOptional.isPresent()){
            truckRepository.save(truckMapper.fromCreateDto(truckToCreateDto));
        }else {
            throw new CustomException(String.format("Truck with personal number : %s already exist ", truckToCreateDto.getRegistrationNumber()));
        }
    }

    public void delete(String number) {
        Optional<Truck> truckOptional = truckRepository.findByRegistrationNumber( number);
        if (truckOptional.isPresent()) {
            truckRepository.deleteById(truckOptional.get().getId());
        } else {
            throw new CustomException(String.format("Truck with number : %s not found ",  number));
        }
    }

    public TruckDto read(String number) {
        Optional<Truck> truckOptional = truckRepository.findByRegistrationNumber(number);
        if (truckOptional.isPresent()) {
            Truck truck = truckOptional.get();
            //  Hibernate.initialize(cargo.getWayPointList());
            TruckDto truckDto = truckMapper.toDto(truck);
            return truckDto;
        } else {
            throw new CustomException(String.format("Truck with number  : %s not found ", number));
        }
    }

    public List<TruckDto> readAll() {
        List<TruckDto> truckDtos = truckMapper.convertListToDto(truckRepository.findAll());
        return truckDtos;
    }

    public void update(String number, TruckDto truckDto) {
        Optional<Truck> truckOptional = truckRepository.findByRegistrationNumber(number);
        if (truckOptional.isPresent()) {
            Truck target = truckOptional.get();
            Truck  source = truckMapper.fromDto(truckDto);
            truckRepository.save(mergeMapperForTruck.merge(target, source));
        } else {
            throw new CustomException(String.format("Truck with number : %s not found ", number));
        }
    }
}
