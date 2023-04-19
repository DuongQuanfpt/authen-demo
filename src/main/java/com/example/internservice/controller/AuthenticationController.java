 package com.example.internservice.controller;

import com.example.internservice.service.impl.InternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.internservice.dto.authentication.AuthenticationDTO;
import com.example.internservice.dto.authentication.AuthenticationDTOLogin;
import com.example.internservice.service.impl.AuthenticationService;

@RestController
@RequestMapping("/intern/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private InternService internService;

    @GetMapping(value = "/login")
    public AuthenticationDTO login(@RequestBody AuthenticationDTOLogin dtoLogin) {

        return authenticationService.login(dtoLogin);
    }

    @PutMapping(value = "/getcode/{id}")
    public String sendOTP(@PathVariable Long id) {

        return internService.getActivateCode(id);
    }

    @PutMapping(value = "/activate/{code}")
    public String activate(@PathVariable String code) {

        return internService.active(code);
    }
}
