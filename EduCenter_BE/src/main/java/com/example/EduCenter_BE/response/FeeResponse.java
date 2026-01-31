package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.entity.Fee;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FeeResponse {

    private Long feeId;
    private String studentCode;
    private String fullName;
    private BigDecimal amount;
    private BigDecimal paidAmount;
    private BigDecimal remaining;

    public FeeResponse(Long feeId, String studentCode, String studentName, BigDecimal amount, BigDecimal paidAmount, BigDecimal remaining) {
        this.feeId = feeId;
        this.studentCode = studentCode;
        this.fullName = studentName;
        this.amount = amount;
        this.paidAmount = paidAmount;
        this.remaining = remaining;

    }

}
