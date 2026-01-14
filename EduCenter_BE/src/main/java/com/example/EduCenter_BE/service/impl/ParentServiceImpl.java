package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Parent;
import com.example.EduCenter_BE.repository.ParentRepository;
import com.example.EduCenter_BE.request.parent.CreateParentRequest;
import com.example.EduCenter_BE.service.interfaces.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ParentServiceImpl implements ParentService {

    @Autowired
    private ParentRepository parentRepository;

    @Override
    public Parent createParent(CreateParentRequest request) {
        String email = request.getEmail();
        String phone = request.getPhone();

        Parent checkParentByEmail = parentRepository.findParentByEmail(email);
        Parent checkParentByPhone = parentRepository.findParentByPhone(phone);

        if (!Objects.isNull(checkParentByEmail) || !Objects.isNull(checkParentByPhone)) {
            throw new RuntimeException(Message.PARENT_EXISTED);
        }

        Parent parent = new Parent();
        parent.setAddress(request.getAddress());
        parent.setPhone(request.getPhone());
        parent.setEmail(request.getEmail());
        parent.setFullName(request.getFullName());
        return parentRepository.save(parent);
    }

    @Override
    public Long totalParentCount() {
        return parentRepository.countAllParent();
    }
}
