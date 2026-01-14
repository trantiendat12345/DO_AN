package com.example.EduCenter_BE.security.service.interfaces;

import com.example.EduCenter_BE.request.auth.LoginRequest;
import com.example.EduCenter_BE.response.LoginResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthService {

    LoginResponse login(@RequestBody LoginRequest request);

}
