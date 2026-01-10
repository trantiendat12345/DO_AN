package com.example.EduCenter_BE.security.service.impl;

import com.example.EduCenter_BE.config.JWTConfig;
import com.example.EduCenter_BE.security.service.interfaces.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JWTServiceImpl implements JWTService {

    private final JWTConfig jwtConfig;

    public JWTServiceImpl(JWTConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    // 🔐 HS512 KEY (>= 64 bytes)
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(
                jwtConfig.getSecret()
                        .getBytes(StandardCharsets.UTF_8)
        );
    }

    @Override
    public String generateToken(UserDetails userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim(
                        "role",
                        userDetails.getAuthorities()
                                .iterator()
                                .next()
                                .getAuthority()
                )
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis()
                                + jwtConfig.getExpiration())
                )
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    @Override
    public boolean isTokenValid(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    @Override
    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
