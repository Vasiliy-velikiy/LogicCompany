package com.moskalev.controller.impl;

import com.moskalev.domain.dto.impl.cityDto.CityDto;
import com.moskalev.domain.dto.impl.cityDto.CityToCreateDto;
import com.moskalev.domain.dto.impl.driverDto.DriverDto;
import com.moskalev.domain.dto.impl.driverDto.DriverToCreateDto;
import com.moskalev.domain.dto.impl.personDto.PersonToCreateDto;
import com.moskalev.service.impl.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/driver")
public class DriverController {

    private final DriverService driverService;

    @GetMapping("/new")
    public String newDriver(@ModelAttribute("driver") DriverToCreateDto person){
        return "driver/new";
    }

    @PostMapping
    public String create(Model model, @ModelAttribute("driver") @Valid DriverToCreateDto driverToCreateDto) {
        driverService.create(driverToCreateDto);
        return "redirect:/api/driver";
    }

    @DeleteMapping(path = "/{personNumber}")
    public String delete(@ModelAttribute @PathVariable String personNumber) {
        driverService.delete(personNumber);
        return "redirect:/api/driver";
    }

//    @GetMapping(path = "/{uniqueNumber}")
//    public String read(@PathVariable String uniqueNumber, Model model) {
//        model.addAttribute("driver"driverService.read(uniqueNumber);
//    }

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("drivers", driverService.readAll());
        return "driver/driverList";
    }

    @GetMapping("/{personNumber}/edit")
    public String edit(Model model, @PathVariable("personNumber") String personNumber){
        model.addAttribute("driver",driverService.read(personNumber));
        return "driver/edit";
    }

    @PatchMapping(path = "/{personNumber}")
    public String update(@PathVariable String personNumber,@ModelAttribute("driver") DriverDto driverDto) {
        driverService.update(personNumber, driverDto);
        return "redirect:/api/driver";
    }

}
