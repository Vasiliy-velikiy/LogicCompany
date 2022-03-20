package com.moskalev.controller.impl;

import com.moskalev.domain.dto.impl.CargoDto.CargoDto;
import com.moskalev.domain.dto.impl.CargoDto.CargoToCreateDto;
import com.moskalev.domain.dto.impl.cityDto.CityDto;
import com.moskalev.domain.dto.impl.cityDto.CityToCreateDto;
import com.moskalev.domain.dto.impl.orderDto.OrderToCreateDto;
import com.moskalev.domain.entity.City;
import com.moskalev.service.impl.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/city")
public class CityController {


    private final CityService cityService;

    @GetMapping("/new")
    public String newCity(@ModelAttribute("city") CityToCreateDto order){
        return "city/new";
    }

    @GetMapping("/{name}/edit")
    public String edit(Model model, @PathVariable("name") String name){
        model.addAttribute("city",cityService.read(name));
        return "city/edit";
    }

    @PostMapping
    public String create(Model model, @ModelAttribute("city") @Valid CityToCreateDto cityToCreateDto) {
        cityService.create(cityToCreateDto);
        return "redirect:/api/city";
    }

    @DeleteMapping(path = "/{name}")
    public String delete(@ModelAttribute @PathVariable String name) {
        cityService.delete(name);
        return "redirect:/api/city";
    }

//    @GetMapping(path = "/{id}")
//    public CityDto read(@PathVariable Long id) {
//        return cityService.read(id);
//    }

    @GetMapping
    public String readAll(Model model) {
       model.addAttribute("cites",cityService.readAll());
       return "city/cityList";
    }

    @PatchMapping(path = "/{name}")
    public String update(@PathVariable String name, @ModelAttribute("city") CityDto cityDto) {
        cityService.update(name, cityDto);
        return "redirect:/api/city";

    }

}
