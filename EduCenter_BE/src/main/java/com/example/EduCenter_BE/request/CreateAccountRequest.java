package com.example.EduCenter_BE.request;

import com.example.EduCenter_BE.constant.enums.AccountStatus;
import com.example.EduCenter_BE.constant.enums.UserType;
import lombok.Data;
import org.apache.catalina.User;

@Data
public class CreateAccountRequest {

    private String username;
    private String password;
    private String roleName;
    private UserType userType;
    private String userCode;

}
