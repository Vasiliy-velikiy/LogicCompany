package com.moskalev.controller.impl;

import com.moskalev.domain.dto.impl.CargoDto.CargoDto;
import com.moskalev.domain.dto.impl.CargoDto.CargoToCreateDto;
import com.moskalev.domain.dto.impl.cityDto.CityToCreateDto;
import com.moskalev.service.impl.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/cargo")
public class CargoController  {

    private final CargoService cargoService;

    @GetMapping("/new")
    public String newCargo(@ModelAttribute("cargo") CargoToCreateDto cargo){
        return "cargo/new";
    }

    @GetMapping("/{name}/edit")
    public String edit(Model model, @PathVariable("name") String name){
        model.addAttribute("cargo",cargoService.read(name));
        return "cargo/edit";
    }

    @PostMapping
    public String create(Model model, @ModelAttribute("cargo") @Valid CargoToCreateDto cargoToCreateDto) {
        cargoService.create(cargoToCreateDto);
        return "redirect:/api/cargo";
    }

    @DeleteMapping(path = "/{name}")
    public String delete(@ModelAttribute @PathVariable String name) {
    cargoService.delete(name);
        return "redirect:/api/cargo";
    }

//    @GetMapping(path = "/{id}")
//    public CargoDto read(@PathVariable Long id) {
//       return cargoService.read(id);
//    }

    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("cargos",cargoService.readAll());
        return "cargo/cargoList";
    }

    @PatchMapping(path = "/{name}")
    public String update(@PathVariable String name, @ModelAttribute("cargo") CargoDto cargoDto) {
        cargoService.update(name, cargoDto);
        return "redirect:/api/cargo";
    }
}

