package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.constant.enums.AccountStatus;
import com.example.EduCenter_BE.constant.enums.UserType;
import com.example.EduCenter_BE.entity.Account;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponse {

    private String roleName;
    private String username;
    private String userCode;
    private UserType userType;
    private AccountStatus accountStatus;

    public AccountResponse(Account account) {
        this.roleName = account.getRole().getName();
        this.username = account.getUsername();
        this.userType = account.getType();
        this.accountStatus = account.getStatus();
    }
}
