package com.moskalev.controller.impl;

import com.moskalev.domain.dto.impl.CargoDto.CargoDto;
import com.moskalev.domain.dto.impl.CargoDto.CargoToCreateDto;
import com.moskalev.domain.dto.impl.cityDto.CityDto;
import com.moskalev.domain.dto.impl.cityDto.CityToCreateDto;
import com.moskalev.domain.entity.City;
import com.moskalev.service.impl.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/city")
public class CityController {


    private final CityService cityService;

    @PostMapping
    public void create(@RequestBody CityToCreateDto cityToCreateDto) {
        cityService.create(cityToCreateDto);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }

    @GetMapping(path = "/{id}")
    public CityDto read(@PathVariable Long id) {
        return cityService.read(id);
    }

    @GetMapping
    public Page<CityDto> readAll() {
        return cityService.readAll();
    }

    @PatchMapping(path = "/{id}")
    public void update(@PathVariable Long id, @RequestBody CityDto cityDto) {
        cityService.update(id, cityDto);
    }

}
