package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Role;
import com.example.EduCenter_BE.exception.BusinessException;
import com.example.EduCenter_BE.repository.RoleRepository;
import com.example.EduCenter_BE.request.role.CreateRoleRequest;
import com.example.EduCenter_BE.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role createRole(CreateRoleRequest request) {
        Integer id = request.getId();
        String name = request.getName();

        Role checkRoleById = roleRepository.findRoleById(id);
        Role checkRoleByName = roleRepository.findRoleByName(name);

        if (!Objects.isNull(checkRoleById) || !Objects.isNull(checkRoleByName)) {
            throw new BusinessException(Message.ROLE_EXISTED);
        }

        Role role = new Role();
        role.setId(request.getId());
        role.setName(request.getName());

        return roleRepository.save(role);
    }
}
