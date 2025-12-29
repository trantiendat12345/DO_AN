package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.enums.UserType;
import com.example.EduCenter_BE.entity.Account;
import com.example.EduCenter_BE.entity.Role;
import com.example.EduCenter_BE.entity.Student;
import com.example.EduCenter_BE.repository.AccountRepository;
import com.example.EduCenter_BE.repository.RoleRepository;
import com.example.EduCenter_BE.repository.StudentRepository;
import com.example.EduCenter_BE.repository.TeacherRepository;
import com.example.EduCenter_BE.request.CreateAccountRequest;
import com.example.EduCenter_BE.response.AccountResponse;
import com.example.EduCenter_BE.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {
//        String roleName = request.getRoleName();
//        String username = request.getUsername();
//        UserType userType = request.getUserType();
//        String userCode = request.getUserCode();
//
//        Role checkRole = roleRepository.findRoleByName(roleName);
//        Account checkAccount = accountRepository.findAccountByUsername(username);


        return null;
    }

}
