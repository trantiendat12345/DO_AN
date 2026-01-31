package com.example.EduCenter_BE.request.account;

import com.example.EduCenter_BE.constant.enums.UserType;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateAccountRequest {

    @Size(min = 6, max = 100, message = "Password phải từ 6 đến 100 ký tự")
    private String password;

    private String roleName;

    private UserType userType;

}
