package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.constant.enums.UserType;
import com.example.EduCenter_BE.request.account.CreateAccountRequest;
import com.example.EduCenter_BE.response.AccountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {

    AccountResponse createAccount(CreateAccountRequest request);

    Page<AccountResponse> getAllAccounts(Pageable pageable);

    AccountResponse getAccountByUsername(String username);

    AccountResponse getAccountByUserCode(UserType type, String code);

}
