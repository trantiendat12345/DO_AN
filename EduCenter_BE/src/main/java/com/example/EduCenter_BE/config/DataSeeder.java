package com.example.EduCenter_BE.config;

import com.example.EduCenter_BE.constant.enums.UserType;
import com.example.EduCenter_BE.entity.Account;
import com.example.EduCenter_BE.repository.AccountRepository;
import com.example.EduCenter_BE.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (accountRepository.findAll().isEmpty()) {
            Account account = new Account();
            account.setUsername("admin");
            account.setPassword(passwordEncoder.encode("admin"));
            account.setRole(roleRepository.findRoleById(1));
            account.setType(UserType.ADMIN);
            account.setUserId(0L);
            accountRepository.save(account);
        }
    }

}
