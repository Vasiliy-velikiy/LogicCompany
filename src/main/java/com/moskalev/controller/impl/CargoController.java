package com.moskalev.controller.impl;

import com.moskalev.domain.dto.impl.CargoDto.CargoDto;
import com.moskalev.domain.dto.impl.CargoDto.CargoToCreateDto;
import com.moskalev.service.impl.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/cargo")
public class CargoController  {

    private final CargoService cargoService;


    @PostMapping
    public void create(@RequestBody CargoToCreateDto cargoToCreateDto) {
        cargoService.create(cargoToCreateDto);
    }


    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
    cargoService.delete(id);
    }

    @GetMapping(path = "/{id}")
    public CargoDto read(@PathVariable Long id) {
       return cargoService.read(id);
    }

    @GetMapping
    public Page<CargoDto> readAll() {
        return cargoService.readAll();
    }


    @PatchMapping(path = "/{id}")
    public void update(@PathVariable Long id, @RequestBody CargoDto cargoDto) {
        cargoService.update(id, cargoDto);
    }
}

