package com.example.EduCenter_BE.security.service.impl;

import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Account;
import com.example.EduCenter_BE.repository.AccountRepository;
import com.example.EduCenter_BE.security.service.interfaces.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService, CustomUserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Account account = accountRepository.findAccountByUsername(username);

        if (Objects.isNull(account)) {
            throw new UsernameNotFoundException(Message.ACCOUNT_DOES_NOT_EXIST);
        }

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .authorities("ROLE_" + account.getRole().getName())
                .build();
    }

}
