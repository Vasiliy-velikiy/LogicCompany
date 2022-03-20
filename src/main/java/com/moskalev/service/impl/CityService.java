package com.moskalev.service.impl;

import com.moskalev.domain.dto.impl.CargoDto.CargoDto;
import com.moskalev.domain.dto.impl.CargoDto.CargoToCreateDto;
import com.moskalev.domain.dto.impl.cityDto.CityDto;
import com.moskalev.domain.dto.impl.cityDto.CityToCreateDto;
import com.moskalev.domain.entity.Cargo;
import com.moskalev.domain.entity.City;
import com.moskalev.domain.mapper.CityMapper;
import com.moskalev.domain.mapper.mupstruct.MergeMapperForCity;
import com.moskalev.exception.CustomException;
import com.moskalev.repositories.CityRepository;
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
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final MergeMapperForCity mergeMapperForCity;

    public void create(CityToCreateDto cityToCreateDto) {
       Optional<City> cityOptional = cityRepository.findByName(cityToCreateDto.getName());
       if(!cityOptional.isPresent()){
           cityRepository.save(cityMapper.createFromDto(cityToCreateDto));
       }else {
           throw new CustomException(String.format("City with name : %s already exist ", cityToCreateDto.getName()));
       }
    }

    public void delete(String name) {
        Optional<City> cityOptional = cityRepository.findByName(name);
        if (cityOptional.isPresent()) {
            cityRepository.deleteById(cityOptional.get().getId());
        } else {
            throw new CustomException(String.format("City with name : %s not found ", name));
        }
    }

    public CityDto read(String name) {
        Optional<City> cargoOptional = cityRepository.findByName(name);
        if (cargoOptional.isPresent()) {
            City city = cargoOptional.get();
            //  Hibernate.initialize(cargo.getWayPointList());
            CityDto cityDto = cityMapper.convertToDto(city);
            return cityDto;

            //у спринга есть проекции -сразу мапить проекции -
        } else {
            throw new CustomException(String.format("City with name : %s not found ", name));
        }
    }

    public List<CityDto> readAll() {
        List<CityDto> cityDtos = cityMapper.convertListToDto(cityRepository.findAll());
       return cityDtos;
    }

    public void update(String name, CityDto cityDto) {
        Optional<City> cityOptional = cityRepository.findByName(name);
        if (cityOptional.isPresent()) {
            City target = cityOptional.get();
            City source = cityMapper.convertFromDto(cityDto);
            cityRepository.save(mergeMapperForCity.merge(target, source));
        } else {
            throw new CustomException(String.format("City with id : %s not found ", name));
        }
    }
}
