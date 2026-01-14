package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.entity.Role;
import com.example.EduCenter_BE.request.role.CreateRoleRequest;

public interface RoleService {

    Role createRole(CreateRoleRequest request);

}
