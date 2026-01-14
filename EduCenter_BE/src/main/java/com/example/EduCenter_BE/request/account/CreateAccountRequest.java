package com.example.EduCenter_BE.request.account;

import com.example.EduCenter_BE.constant.enums.UserType;
import lombok.Data;

@Data
public class CreateAccountRequest {

    private String username;
    private String password;
    private String roleName;
    private UserType userType;
    private String userCode;

}
