package com.example.internservice.service;

import com.example.internservice.dto.authentication.AuthenticationDTO;
import com.example.internservice.dto.authentication.AuthenticationDTOLogin;

public interface IAuthenticationService {
    public AuthenticationDTO login(AuthenticationDTOLogin dtoLogin);
}
