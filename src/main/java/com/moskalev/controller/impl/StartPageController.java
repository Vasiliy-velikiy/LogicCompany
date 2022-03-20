package com.moskalev.controller.impl;

import com.moskalev.domain.dto.impl.personDto.PersonSignInDto;
import com.moskalev.domain.dto.impl.personDto.PersonToCreateDto;
import com.moskalev.service.impl.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @version 1.1
 * * @author Vasiliy Moskalev
 * @since 14.02.22
 * Controller - Start page for person. This class provides interaction with Sign Up and Sign In
 */
@Controller
@RequestMapping(path = "/api/startPages")
@RequiredArgsConstructor
public class StartPageController {

    private final SignUpService signUpService;

    @GetMapping()
    public String getStartPage(Model model) {
        return "sign/startPage";
    }

    /**
     * @param newPerson -Person who wants signUp in server
     */
    @PostMapping(path = "/signUp")
    public String signUp(Model model, @ModelAttribute("person") PersonToCreateDto newPerson) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        signUpService.signUp(newPerson);
        return "ok";
    }

    /**
     * @param personSignInDto -person transfers already exist information about personal area
     */
    @PostMapping(path = "/signIn")
    public String sighIn(Model model ,@ModelAttribute @Valid PersonSignInDto personSignInDto) throws UnsupportedEncodingException, NoSuchAlgorithmException {
     signUpService.sighIn(personSignInDto);
        return "redirect:/api/persons";
    }

    @GetMapping("/signIn")
    public String personSignIn(@ModelAttribute("person")  PersonSignInDto person){
        return "sign/signIn";
    }
}