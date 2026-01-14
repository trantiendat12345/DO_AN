package com.example.EduCenter_BE.request.auth;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;
    private String password;

}
