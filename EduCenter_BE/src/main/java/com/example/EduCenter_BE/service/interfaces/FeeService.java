package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.response.FeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FeeService {

    Page<FeeResponse> getAllFees(Pageable pageable);

}
