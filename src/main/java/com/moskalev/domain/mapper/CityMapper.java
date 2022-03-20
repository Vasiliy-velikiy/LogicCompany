package com.moskalev.domain.mapper;

import com.moskalev.domain.dto.impl.CargoDto.CargoDto;
import com.moskalev.domain.dto.impl.CargoDto.CargoToCreateDto;
import com.moskalev.domain.dto.impl.cityDto.CityDto;
import com.moskalev.domain.dto.impl.cityDto.CityToCreateDto;
import com.moskalev.domain.entity.City;
import com.moskalev.domain.entity.MapOfCountry;
import com.moskalev.exception.CustomException;
import com.moskalev.repositories.MapOfCountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CityMapper {

    private final MapOfCountryRepository mapOfCountryRepository;


    public City createFromDto(CityToCreateDto cityToCreateDto) {
        City city = new City();
        city.setCoordinateX(cityToCreateDto.getCoordinateX());
        city.setCoordinateY(cityToCreateDto.getCoordinateY());
        city.setName(cityToCreateDto.getName());
        city.setMapOfCountry(mapOfCountryRepository.findByMapName(cityToCreateDto.getMapOfCountry()).get());
        return city;
    }

    public City convertFromDto(CityDto cityDto) {
        City city = new City();
        city.setName(cityDto.getName());
        city.setCoordinateY(cityDto.getCoordinateY());
        city.setCoordinateX(cityDto.getCoordinateX());
        if(cityDto.getMapOfCountry()!=null) {
            Optional<MapOfCountry> mapOfCountryOptional = mapOfCountryRepository.findByMapName(cityDto.getMapOfCountry().toLowerCase(Locale.ROOT));
            if (mapOfCountryOptional.isPresent()) {
                city.setMapOfCountry(mapOfCountryOptional.get());
            }
            else {
                throw new CustomException(String.format("Country with name: %s not found. Please create this country in Database",
                        cityDto.getMapOfCountry()));
            }
        }
        return city;
    }

    public CityDto convertToDto(City city) {
        CityDto cityDto = new CityDto();
        cityDto.setCoordinateX(city.getCoordinateX());
        cityDto.setCoordinateY(city.getCoordinateY());
        cityDto.setName(city.getName());
        cityDto.setMapOfCountry(mapOfCountryRepository.findByMapName(city.getMapOfCountry().getMapName()).get().getMapName());
        return cityDto;
    }

    public List<City> convertListFromDto(List<CityDto> objList) {
        return objList.stream().map(this::convertFromDto).collect(Collectors.toList());
    }

    public List<CityDto> convertListToDto(List<City> objList) {
        return objList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
