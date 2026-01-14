package com.example.EduCenter_BE.request.account;

import com.example.EduCenter_BE.constant.enums.UserType;
import lombok.Data;

@Data
public class UpdateAccountRequest {

    private String password;
    private String roleName;
    private UserType userType;

}
