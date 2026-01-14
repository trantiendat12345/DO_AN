package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.enums.AccountStatus;
import com.example.EduCenter_BE.constant.enums.UserType;
import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Account;
import com.example.EduCenter_BE.entity.Role;
import com.example.EduCenter_BE.entity.Student;
import com.example.EduCenter_BE.entity.Teacher;
import com.example.EduCenter_BE.exception.BadRequestException;
import com.example.EduCenter_BE.repository.AccountRepository;
import com.example.EduCenter_BE.repository.RoleRepository;
import com.example.EduCenter_BE.repository.StudentRepository;
import com.example.EduCenter_BE.repository.TeacherRepository;
import com.example.EduCenter_BE.request.account.CreateAccountRequest;
import com.example.EduCenter_BE.response.AccountResponse;
import com.example.EduCenter_BE.service.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AccountResponse createAccount(CreateAccountRequest request) {
        String roleName = request.getRoleName();
        String username = request.getUsername();
        UserType userType = request.getUserType();
        String userCode = request.getUserCode();
        String password = request.getPassword();

        Role checkRole = roleRepository.findRoleByName(roleName);
        Account checkAccount = accountRepository.findAccountByUsername(username);

        if (Objects.isNull(checkRole)){
            throw new RuntimeException(Message.ROLE_DOES_NOT_EXIST);
        }

        if (!Objects.isNull(checkAccount)){
            throw new RuntimeException(Message.ACCOUNT_EXISTED);
        }

        if (userCode.isEmpty() && userType.equals(UserType.ADMIN)){
            Account account = new Account();
            account.setUsername(username);
            account.setPassword(passwordEncoder.encode(password));
            account.setRole(checkRole);
            account.setType(userType);
            account.setStatus(AccountStatus.ACTIVE);
        }

        Long userId = resolveUserIdByCode(userType, userCode);

        Account account = new Account();
        account.setUsername(request.getUsername());
        account.setPassword(passwordEncoder.encode(password));
        account.setRole(checkRole);
        account.setType(request.getUserType());
        account.setUserId(userId);
        account.setStatus(AccountStatus.ACTIVE);

        return new AccountResponse(accountRepository.save(account));
    }

    @Override
    public Page<AccountResponse> getAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable)
                .map(account -> {

                    AccountResponse response = new AccountResponse(account);

                    String userCode = null;

                    switch (account.getType()) {
                        case STUDENT -> userCode = studentRepository
                                .findById(account.getUserId())
                                .map(Student::getStudentCode)
                                .orElse(null);

                        case TEACHER -> userCode = teacherRepository
                                .findById(account.getUserId())
                                .map(Teacher::getTeacherCode)
                                .orElse(null);
                    }

                    response.setUserCode(userCode);
                    return response;
                });
    }

    @Override
    public AccountResponse getAccountByUsername(String username) {
        Account account = accountRepository.findAccountByUsername(username);

        if (Objects.isNull(account)){
            throw new RuntimeException(Message.ACCOUNT_DOES_NOT_EXIST);
        }

        return new AccountResponse(account);
    }

    @Override
    public AccountResponse getAccountByUserCode(UserType type, String code) {
        Long userId = 0L;

        switch (type) {
            case STUDENT: Student student = studentRepository.findStudentByCode(code);
            if (Objects.isNull(student)){
                throw new RuntimeException(Message.STUDENT_DOES_NOT_EXIST);
            }
            userId = student.getId();
            break;
            case TEACHER: Teacher teacher = teacherRepository.findTeacherByCode(code);
            if (Objects.isNull(teacher)){
                throw new RuntimeException(Message.TEACHER_DOES_NOT_EXIST);
            }
            userId = teacher.getId();
            break;
            default:
                throw new RuntimeException(Message.INVALID_USER_TYPE);
        }

        Account account = accountRepository.findAccountByUserIdAndType(userId, type);

        if (Objects.isNull(account)){
            throw new RuntimeException(Message.ACCOUNT_DOES_NOT_EXIST);
        }

        return new AccountResponse(account);
    }

    private Long resolveUserIdByCode(UserType type, String userCode) {

        // 1️⃣ ADMIN
        if (type == UserType.ADMIN) {
            if (userCode != null && !userCode.isBlank()) {
                throw new BadRequestException("ADMIN không có userCode");
            }
            return 0L;
        }

        // 2️⃣ Các user khác bắt buộc có code
        if (userCode == null || userCode.isBlank()) {
            throw new BadRequestException("userCode không được để trống");
        }

        // 3️⃣ Resolve theo type
        if (type == UserType.STUDENT) {
            Student student = studentRepository.findStudentByCode(userCode);
            if (student == null) {
                throw new BadRequestException("Student không tồn tại");
            }
            return student.getId();
        }

        if (type == UserType.TEACHER) {
            Teacher teacher = teacherRepository.findTeacherByCode(userCode);
            if (teacher == null) {
                throw new BadRequestException("Teacher không tồn tại");
            }
            return teacher.getId();
        }

        throw new BadRequestException("UserType không hợp lệ");
    }


}
