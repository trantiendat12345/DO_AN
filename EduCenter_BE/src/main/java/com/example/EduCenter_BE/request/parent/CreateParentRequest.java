package com.example.EduCenter_BE.request.parent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateParentRequest {

    private String address;
    private String email;
    private String fullName;
    private String phone;

}
