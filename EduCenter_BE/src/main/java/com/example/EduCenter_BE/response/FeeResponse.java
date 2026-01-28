package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.entity.Fee;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class FeeResponse {

    private String studentCode;
    private String studentName;
    private BigDecimal amount;
    private BigDecimal paidAmount;
    private BigDecimal remaining;

    public FeeResponse(String studentCode, String studentName, BigDecimal amount, BigDecimal paidAmount, BigDecimal remaining) {
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.amount = amount;
        this.paidAmount = paidAmount;
        this.remaining = remaining;

    }

}
