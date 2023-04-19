package com.example.internservice.service;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface IJwtService {
    public String extractUsername(String token);

    public Claims extractClaims(String token);

    public String generateToken(UserDetails details);

    public boolean isValid(String token , UserDetails details);
}
