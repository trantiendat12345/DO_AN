package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.entity.Fee;
import com.example.EduCenter_BE.repository.FeeRepository;
import com.example.EduCenter_BE.repository.PaymentRepository;
import com.example.EduCenter_BE.response.FeeResponse;
import com.example.EduCenter_BE.service.interfaces.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    private FeeRepository feeRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Page<FeeResponse> getAllFees(Pageable pageable) {
        // 1️⃣ Lấy Page<Fee> từ DB
        Page<Fee> feePage = feeRepository.findByIsDeletedFalse(pageable);

        // 2️⃣ Map Page<Fee> -> Page<FeeResponse>
        return feePage.map(fee -> {

            BigDecimal totalFee = fee.getAmount();

            BigDecimal paidAmount =
                    paymentRepository.sumPaidAmountByFeeId(fee.getId());

            BigDecimal remaining =
                    totalFee.subtract(paidAmount);

            return new FeeResponse(
                    fee.getId(),
                    fee.getStudent().getStudentCode(),
                    fee.getStudent().getFullName(),
                    totalFee,
                    paidAmount,
                    remaining
            );
        });
    }

}
