package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleResponse {

    private Integer id;
    private String name;

    public RoleResponse(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

}
