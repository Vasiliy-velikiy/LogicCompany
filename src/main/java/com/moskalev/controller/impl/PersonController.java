package com.moskalev.controller.impl;


import com.moskalev.domain.dto.impl.personDto.PersonDto;
import com.moskalev.domain.dto.impl.personDto.PersonToCreateDto;
import com.moskalev.service.impl.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vasiliy Moskalev
 * @version 1.1
 * @since 09.03.22
 * Class controller for handling requests to personRepository through the personService
 */
@Controller
@RequestMapping(path = "/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    /**
     * @param email -certain email that is unique
     * @return -certain product that we want to get
     */
//    @Operation(description = "Find user by email")
//    @ApiResponse(responseCode = "200", description = "User successfully found")
//    @ApiResponse(responseCode = "500", description = "User not found")
    //@PreAuthorize("hasRole('ADMIN') || hasAuthority('ROLE_ADMIN')" )
    @GetMapping(path = "/{email}")
    public String read(@PathVariable(name = "email") String email, Model model) {
        model.addAttribute("person",personService.read(email));
        return "Person/showCertainPerson";
    }

    /**
     * @return page of Persons
     */
//    @Operation(description = "Find all users")
//    @ApiResponse(responseCode = "200", description = "All Users successfully found")
//    @ApiResponse(responseCode = "500", description = "Users not found")
    //@PreAuthorize("hasRole('ADMIN') || hasAuthority('ROLE_ADMIN')" )
    @GetMapping()
    public String readAll(Model model) {
        model.addAttribute("persons",personService.readAll());
        return "Person/personList";
    }

    /**
     * @param person -object that we want to create
     */
//    @Operation(description = "Create user")
//    @ApiResponse(responseCode = "200", description = "User successfully created")
//    @ApiResponse(responseCode = "500", description = "User already exists")
    // @PreAuthorize("hasRole('CUSTOMER') || hasAuthority('ROLE_CUSTOMER') || hasRole('ADMIN') || hasAuthority('ROLE_ADMIN') || hasRole('EMPLOYEE') || hasAuthority('ROLE_EMPLOYEE')" )
    @PostMapping
    public String create(Model model,
                         @ModelAttribute("person") @Valid PersonToCreateDto person) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        personService.create(person);
            return "redirect:/api/persons";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person")  PersonToCreateDto person){
        return "Person/new";
    }

    @GetMapping("/{email}/edit")
    public String edit(Model model, @PathVariable("email") String email){
        model.addAttribute("person",personService.read(email));
        return "Person/edit";
    }

    /**
     * @param id -object that we want to delete
     */
//    @Operation(description = "Delete user by id")
//    @ApiResponse(responseCode = "204", description = "User successfully deleted")
//    @PreAuthorize("hasRole('ADMIN') || hasAuthority('ROLE_ADMIN')" )
    @DeleteMapping(path = "/{email}")
    public String delete(@ModelAttribute @PathVariable String email, Model model) {
        personService.delete(email);
        return "redirect:/api/persons";
    }

    /**
     * @param email             -certain name that is unique
     * @param newPerson-object that we want to update
     */
//    @Operation(description = "Update user")
//    @ApiResponse(responseCode = "200", description = "User successfully updated")
//    @PreAuthorize("hasRole('CUSTOMER') || hasAuthority('ROLE_CUSTOMER') || hasRole('ADMIN') || hasAuthority('ROLE_ADMIN') || hasRole('EMPLOYEE') || hasAuthority('ROLE_EMPLOYEE')" )
    @PatchMapping(path = "/{email}")
    public String update( @PathVariable String email, @ModelAttribute("person") PersonDto newPerson) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        personService.update(email, newPerson);
        return "redirect:/api/persons";
    }
}