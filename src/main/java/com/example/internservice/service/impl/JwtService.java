package com.example.internservice.service.impl;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.internservice.service.IJwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService implements IJwtService {

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 1h

    private static final String SECRETS_KEY = "244226452948404D6351665468576D5A7134743777217A25432A462D4A614E64";

    @Override
    public String extractUsername(String token) {

        return extractClaims(token).get("sub", String.class);
    }

    @Override
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }

    private Key getSigninKey() {
        byte[] bs = Decoders.BASE64.decode(SECRETS_KEY);
        return Keys.hmacShaKeyFor(bs);
    }

    @Override
    public String generateToken(UserDetails details) {
        // TODO Auto-generated method stub
        return Jwts
                .builder()
                .setSubject(details.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isValid(String token, UserDetails details) {
        String username = extractUsername(token);
        if (username.equals(details.getUsername()) && !isExpired(token)) {
            return true;
        }
        return false;
    }

    private boolean isExpired(String token) {

        return extractClaims(token).getExpiration().before(new Date());
    }

}
