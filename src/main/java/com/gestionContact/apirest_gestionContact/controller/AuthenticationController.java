package com.gestionContact.apirest_gestionContact.controller;

import com.gestionContact.apirest_gestionContact.dto.LoginResponseDto;
import com.gestionContact.apirest_gestionContact.dto.RegistrationDto;
import com.gestionContact.apirest_gestionContact.model.ApplicationUser;
import com.gestionContact.apirest_gestionContact.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser resisterUser(@RequestBody RegistrationDto body){
        return authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDto loginUser(@RequestBody RegistrationDto body){
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }
}
