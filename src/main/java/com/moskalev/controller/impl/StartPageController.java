package com.moskalev.controller.impl;

import com.moskalev.domain.dto.impl.personDto.PersonSignInDto;
import com.moskalev.domain.dto.impl.personDto.PersonToCreateDto;
import com.moskalev.service.impl.SignUpService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @version 1.1
 * * @author Vasiliy Moskalev
 * @since 14.02.22
 * Controller - Start page for person. This class provides interaction with Sign Up and Sign In
 */
@RestController
@RequestMapping(path = "/api/startPages")
public class StartPageController {

    private final SignUpService signUpService;


    public StartPageController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    /**
     * @param newPerson -Person who wants signUp in server
     */
    @PostMapping(path = "/signUp")
    public String signUp(@RequestBody PersonToCreateDto newPerson) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return signUpService.signUp(newPerson);
    }

    /**
     * @param personSignInDto -person transfers already exist information about personal area
     */
    @PostMapping(path = "/signIn")
    public String sighIn(@RequestBody PersonSignInDto personSignInDto) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return signUpService.sighIn(personSignInDto);
    }
}