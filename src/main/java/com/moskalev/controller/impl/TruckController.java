package com.moskalev.controller.impl;


import com.moskalev.domain.dto.impl.truckDto.TruckDto;
import com.moskalev.domain.dto.impl.truckDto.TruckFilterDto;
import com.moskalev.domain.dto.impl.truckDto.TruckToCreateDto;

import com.moskalev.service.impl.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @version 1.1
 * * @author Vasiliy Moskalev
 * @since 14.02.22
 * Controller - for handling requests to truckRepository through the truckService
 */
@Controller
@RequestMapping(path = "/api/truck")
@RequiredArgsConstructor
public class TruckController {

    private final TruckService truckService;

    @GetMapping("/new")
    public String newTruck(@ModelAttribute("truck") TruckToCreateDto truck){
        return "truck/new";
    }

    @PostMapping
    public String create(Model model, @ModelAttribute("truck") @Valid TruckToCreateDto truckToCreateDto) {
        truckService.create(truckToCreateDto);
        return "redirect:/api/truck";
    }

    @DeleteMapping(path = "/{registrationNumber}")
    public String delete(@ModelAttribute @PathVariable String registrationNumber) {
        truckService.delete(registrationNumber);
        return "redirect:/api/truck";
    }

//    @GetMapping(path = "/{uniqueNumber}")
//    public String read(@PathVariable String uniqueNumber, Model model) {
//        model.addAttribute("driver"driverService.read(uniqueNumber);
//    }



    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("trucks", truckService.readAll());
        return "truck/truckList";
    }

    @GetMapping("/{registrationNumber}/edit")
    public String edit(Model model, @PathVariable("registrationNumber") String registrationNumber){
        model.addAttribute("truck", truckService.read(registrationNumber));
        return "truck/edit";
    }

    @PatchMapping(path = "/{registrationNumber}")
    public String update(@PathVariable String registrationNumber, @ModelAttribute("truck") TruckDto truckDto) {
        truckService.update(registrationNumber, truckDto);
        return "redirect:/api/truck";
    }

    @PostMapping("/filter")
    public String filter(Model model, @ModelAttribute TruckFilterDto truckFilterDto) {
      model.addAttribute("trucks",truckService.filter(truckFilterDto));
        return "truck/filterTrucks";
    }


}
