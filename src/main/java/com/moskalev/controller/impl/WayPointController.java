package com.moskalev.controller.impl;

import com.moskalev.domain.dto.impl.truckDto.TruckDto;
import com.moskalev.domain.dto.impl.truckDto.TruckToCreateDto;
import com.moskalev.domain.dto.impl.waypointDto.WayPointDto;
import com.moskalev.domain.dto.impl.waypointDto.WayPointToCreateDto;
import com.moskalev.service.impl.WayPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/api/way")
@RequiredArgsConstructor
public class WayPointController {

    private final WayPointService wayPointService;

    @GetMapping("/new")
    public String newWayPoint(@ModelAttribute("way") WayPointToCreateDto way){
        return "way/new";
    }

    @PostMapping
    public String create(Model model, @ModelAttribute("way") @Valid WayPointDto way) {
        wayPointService.create(way);
        return "redirect:/api/way";
    }

    @DeleteMapping(path = "/{id}")
    public String delete(@ModelAttribute @PathVariable Long id) {
        wayPointService.delete(id);
        return "redirect:/api/way";
    }

//    @GetMapping(path = "/{uniqueNumber}")
//    public String read(@PathVariable String uniqueNumber, Model model) {
//        model.addAttribute("driver"driverService.read(uniqueNumber);
//    }



    @GetMapping
    public String readAll(Model model) {
        model.addAttribute("ways", wayPointService.readAll());
        return "way/wayList";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id){
        model.addAttribute("way", wayPointService.read(id));
        return "way/edit";
    }

    @PatchMapping(path = "/{id}")
    public String update(@PathVariable Long id, @ModelAttribute("way") WayPointDto wayPointDto) {
        wayPointService.update(id, wayPointDto);
        return "redirect:/api/way";
    }
}
