package com.example.EduCenter_BE.request.admin.create;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateParentAdminRequest {

    private String address;
    private String email;
    private String fullName;
    private String phone;

}
