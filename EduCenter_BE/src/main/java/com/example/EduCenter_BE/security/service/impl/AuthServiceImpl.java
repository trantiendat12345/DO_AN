package com.example.EduCenter_BE.security.service.impl;

import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.exception.LoginException;
import com.example.EduCenter_BE.request.LoginRequest;
import com.example.EduCenter_BE.response.LoginResponse;
import com.example.EduCenter_BE.security.service.interfaces.AuthService;
import com.example.EduCenter_BE.security.service.interfaces.JWTService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JWTService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        try {
            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getUsername(),
                                    request.getPassword()
                            )
                    );

            UserDetails user = (UserDetails) authentication.getPrincipal();

            String role = user.getAuthorities()
                    .iterator()
                    .next()
                    .getAuthority();

            String token = jwtService.generateToken(user);

            return LoginResponse.builder()
                    .username(user.getUsername())
                    .role(role)
                    .token(token)
                    .build();
        } catch (BadCredentialsException e) {
            throw new LoginException(Message.USERNAME_OR_PASSWORD_INCORRECT);
        } catch (DisabledException e) {
            throw new LoginException(Message.ACCOUNT_DISABLE);
        } catch (LockedException e) {
            throw new LoginException(Message.ACCOUNT_LOCKED);
        }
    }
}
