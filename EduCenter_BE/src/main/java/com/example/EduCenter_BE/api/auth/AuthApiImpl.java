package com.example.EduCenter_BE.api.auth;

import com.example.EduCenter_BE.request.LoginRequest;
import com.example.EduCenter_BE.response.LoginResponse;
import com.example.EduCenter_BE.security.service.interfaces.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthApiImpl implements AuthApi {

    private final AuthService authService;

    public AuthApiImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

}
