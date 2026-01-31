package com.example.EduCenter_BE.request.payment;

import com.example.EduCenter_BE.constant.enums.PaymentMethod;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePaymentRequest {

    private Long feeId;
    private PaymentMethod paymentMethod;
    private BigDecimal amountPaid;

}
