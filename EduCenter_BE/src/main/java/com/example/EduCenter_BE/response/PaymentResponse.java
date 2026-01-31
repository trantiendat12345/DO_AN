package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.constant.enums.PaymentMethod;
import com.example.EduCenter_BE.constant.enums.PaymentStatus;
import com.example.EduCenter_BE.entity.Payment;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentResponse {

    private Long paymentId;        // ID của payment
    private Long feeId;            // ID của fee
    private PaymentMethod paymentMethod;
    private BigDecimal amountPaid;
    private PaymentStatus paymentStatus;

    public PaymentResponse(Payment payment) {
        this.paymentId = payment.getId();                    // ✅ đúng
        this.feeId = payment.getFee().getId();               // ✅ đúng
        this.paymentMethod = payment.getMethod();     // ✅ đúng
        this.amountPaid = payment.getAmountPaid();
        this.paymentStatus = payment.getStatus();
    }

}
