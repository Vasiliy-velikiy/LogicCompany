package com.moskalev.controller.impl;


import com.moskalev.domain.dto.impl.personDto.PersonDto;
import com.moskalev.domain.dto.impl.personDto.PersonToCreateDto;
import com.moskalev.domain.entity.Person;
import com.moskalev.service.impl.PersonService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
@RestController
@RequestMapping(path = "/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

   static List<PersonDto> personPage=new ArrayList<>();

//    // Вводится (inject) из application.properties.
//    @Value("${welcome.message}")
//    private String message;
//
//    @Value("${error.message}")
//    private String errorMessage;

    /**
     * @param email -certain email that is unique
     * @return -certain product that we want to get
     */
//    @Operation(description = "Find user by email")
//    @ApiResponse(responseCode = "200", description = "User successfully found")
//    @ApiResponse(responseCode = "500", description = "User not found")
    //@PreAuthorize("hasRole('ADMIN') || hasAuthority('ROLE_ADMIN')" )
    @GetMapping(path = "/email")
    public PersonDto read(@RequestParam(name = "email") String email) {
        return personService.read(email);
    }

    /**
     * @return page of Persons
     */
//    @Operation(description = "Find all users")
//    @ApiResponse(responseCode = "200", description = "All Users successfully found")
//    @ApiResponse(responseCode = "500", description = "Users not found")
    //@PreAuthorize("hasRole('ADMIN') || hasAuthority('ROLE_ADMIN')" )
//    @GetMapping
//    public Page<PersonDto> readAll() {
//        return personService.readAll();
//    }
    @GetMapping()
    public String readAll(Model model) {
        personPage=personService.readAll();
        model.addAttribute("persons",personPage);
        return "personList";
        //return personService.readAll();
    }

    /**
     * @param person -object that we want to create
     */
//    @Operation(description = "Create user")
//    @ApiResponse(responseCode = "200", description = "User successfully created")
//    @ApiResponse(responseCode = "500", description = "User already exists")
    // @PreAuthorize("hasRole('CUSTOMER') || hasAuthority('ROLE_CUSTOMER') || hasRole('ADMIN') || hasAuthority('ROLE_ADMIN') || hasRole('EMPLOYEE') || hasAuthority('ROLE_EMPLOYEE')" )
//    @PostMapping
//    public void create(@Valid @RequestBody PersonToCreateDto person) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        personService.create(person);
//    }
    @PostMapping
    public String create(Model model,
                         @ModelAttribute("newPerson") PersonToCreateDto person) throws UnsupportedEncodingException, NoSuchAlgorithmException {


//        if (person.getFirstName() != null && person.getFirstName().length() > 0 //
//                && person.getLastName() != null && person.getLastName().length() > 0) {
        personService.create(person);
           // model.addAttribute("person",new Person());
        //return "redirect:/personList";
            return "successPage";
  //  }
//        model.addAttribute("errorMessage",errorMessage);
     //   return "addPerson";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person",new Person());

        return "new";

    }
//@GetMapping("/new")
//public ModelAndView newPerson() {
//    ModelAndView modelAndView = new ModelAndView();
//    modelAndView.setViewName("/new");
//    return modelAndView;
//}

    /**
     * @param id -object that we want to delete
     */
//    @Operation(description = "Delete user by id")
//    @ApiResponse(responseCode = "204", description = "User successfully deleted")
//    @PreAuthorize("hasRole('ADMIN') || hasAuthority('ROLE_ADMIN')" )
    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        personService.delete(id);
    }

    /**
     * @param id               -certain name that is unique
     * @param newPerson-object that we want to update
     */
//    @Operation(description = "Update user")
//    @ApiResponse(responseCode = "200", description = "User successfully updated")
//    @PreAuthorize("hasRole('CUSTOMER') || hasAuthority('ROLE_CUSTOMER') || hasRole('ADMIN') || hasAuthority('ROLE_ADMIN') || hasRole('EMPLOYEE') || hasAuthority('ROLE_EMPLOYEE')" )
    @PatchMapping(path = "/{id}")
    public void update(@PathVariable Long id, @RequestBody PersonDto newPerson) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        personService.update(id, newPerson);
    }
}