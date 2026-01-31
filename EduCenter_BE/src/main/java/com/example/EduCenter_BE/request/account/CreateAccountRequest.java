package com.example.EduCenter_BE.request.account;

import com.example.EduCenter_BE.constant.enums.UserType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAccountRequest {

    @NotBlank(message = "Username không được để trống")
    @Size(min = 3, max = 50, message = "Username phải từ 3 đến 50 ký tự")
    private String username;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 6, max = 100, message = "Password phải từ 6 đến 100 ký tự")
    private String password;

    @NotBlank(message = "Role name không được để trống")
    private String roleName;

    @NotNull(message = "User type không được để trống")
    private UserType userType;

    @NotBlank(message = "User code không được để trống")
    private String userCode;

}
