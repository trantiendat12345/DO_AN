package com.example.EduCenter_BE.response.admin;

import com.example.EduCenter_BE.entity.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleAdminResponse {

    private Integer id;
    private String name;

    public RoleAdminResponse(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

}
