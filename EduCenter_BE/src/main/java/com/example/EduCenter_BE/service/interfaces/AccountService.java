package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.request.CreateAccountRequest;
import com.example.EduCenter_BE.response.AccountResponse;

public interface AccountService {

    AccountResponse createAccount(CreateAccountRequest request);

}
