package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.entity.Parent;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParentResponse {

    private String address;
    private String email;
    private String fullName;
    private String phone;

    public ParentResponse(Parent parent) {
        this.address = parent.getAddress();
        this.email = parent.getEmail();
        this.fullName = parent.getFullName();
        this.phone = parent.getPhone();
    }

}
