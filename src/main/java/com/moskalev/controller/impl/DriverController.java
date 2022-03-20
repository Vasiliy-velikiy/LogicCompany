package com.moskalev.controller.impl;

import com.moskalev.domain.dto.impl.cityDto.CityDto;
import com.moskalev.domain.dto.impl.cityDto.CityToCreateDto;
import com.moskalev.domain.dto.impl.driverDto.DriverDto;
import com.moskalev.domain.dto.impl.driverDto.DriverToCreateDto;
import com.moskalev.service.impl.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/driver")
public class DriverController {


    private final DriverService driverService;

    @PostMapping
    public void create(@RequestBody DriverToCreateDto driverToCreateDto) {
        driverService.create(driverToCreateDto);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        driverService.delete(id);
    }

    @GetMapping(path = "/{id}")
    public DriverDto read(@PathVariable Long id) {
        return driverService.read(id);
    }

    @GetMapping
    public Page<DriverDto> readAll() {
        return driverService.readAll();
    }

    @PatchMapping(path = "/{id}")
    public void update(@PathVariable Long id, @RequestBody DriverDto driverDto) {
        driverService.update(id, driverDto);
    }

}
