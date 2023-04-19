package com.example.internservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.internservice.dto.authentication.AuthenticationDTO;
import com.example.internservice.dto.authentication.AuthenticationDTOLogin;
import com.example.internservice.service.IAuthenticationService;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationDTO login(AuthenticationDTOLogin dtoLogin) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dtoLogin.getEmail(), dtoLogin.getPassword()));

        return new AuthenticationDTO(jwtService.generateToken((UserDetails) auth.getPrincipal()));
    }

}
