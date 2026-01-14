package com.example.EduCenter_BE.api.auth;

import com.example.EduCenter_BE.constant.uri.URI;
import com.example.EduCenter_BE.request.auth.LoginRequest;
import com.example.EduCenter_BE.response.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = URI.AUTH)
public interface AuthApi {

    @RequestMapping(value = URI.LOGIN, method = RequestMethod.POST)
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request);

}
