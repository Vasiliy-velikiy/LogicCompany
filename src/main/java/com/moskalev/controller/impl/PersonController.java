package com.moskalev.controller.impl;


import com.moskalev.domain.dto.impl.personDto.PersonDto;
import com.moskalev.domain.dto.impl.personDto.PersonToCreateDto;
import com.moskalev.service.impl.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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
    @GetMapping
    public Page<PersonDto> readAll() {
        return personService.readAll();
    }

    /**
     * @param person -object that we want to create
     */
//    @Operation(description = "Create user")
//    @ApiResponse(responseCode = "200", description = "User successfully created")
//    @ApiResponse(responseCode = "500", description = "User already exists")
    // @PreAuthorize("hasRole('CUSTOMER') || hasAuthority('ROLE_CUSTOMER') || hasRole('ADMIN') || hasAuthority('ROLE_ADMIN') || hasRole('EMPLOYEE') || hasAuthority('ROLE_EMPLOYEE')" )
    @PostMapping
    public void create(@Valid @RequestBody PersonToCreateDto person) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        personService.create(person);
    }

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