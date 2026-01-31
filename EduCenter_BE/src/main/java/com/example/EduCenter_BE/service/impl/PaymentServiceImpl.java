package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.enums.FeeStatus;
import com.example.EduCenter_BE.constant.enums.PaymentMethod;
import com.example.EduCenter_BE.constant.enums.PaymentStatus;
import com.example.EduCenter_BE.entity.Fee;
import com.example.EduCenter_BE.entity.Payment;
import com.example.EduCenter_BE.exception.BusinessException;
import com.example.EduCenter_BE.repository.FeeRepository;
import com.example.EduCenter_BE.repository.PaymentRepository;
import com.example.EduCenter_BE.request.payment.CreatePaymentRequest;
import com.example.EduCenter_BE.response.ApiResponse;
import com.example.EduCenter_BE.response.PaymentResponse;
import com.example.EduCenter_BE.service.interfaces.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private FeeRepository feeRepository;

    @Override
    public PaymentResponse createPayment(CreatePaymentRequest request) {

        if (request.getFeeId() == null) {
            throw new BusinessException("FeeId must not be null");
        }

        Fee fee = feeRepository.findById(request.getFeeId())
                .orElseThrow(() -> new BusinessException("Fee not found"));

        BigDecimal totalPaid =
                paymentRepository.sumPaidAmountByFeeId(fee.getId());

        if (totalPaid == null) {
            totalPaid = BigDecimal.ZERO;
        }

        BigDecimal remaining =
                fee.getAmount().subtract(totalPaid);

        if (request.getAmountPaid().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Invalid amount");
        }

        if (request.getAmountPaid().compareTo(remaining) > 0) {
            throw new BusinessException("Amount exceeds remaining");
        }

        Payment payment = new Payment();
        payment.setFee(fee);
        payment.setAmountPaid(request.getAmountPaid());
        payment.setMethod(request.getPaymentMethod());

        if (request.getPaymentMethod() == PaymentMethod.CASH) {
            payment.setStatus(PaymentStatus.PAID);
            payment.setPaidAt(LocalDateTime.now());
        } else {
            payment.setStatus(PaymentStatus.PENDING);
            payment.setTransactionCode("QR_" + System.currentTimeMillis());
        }

        paymentRepository.save(payment);

        updateFeeStatus(fee);

        return new PaymentResponse(payment);
    }


    @Override
    public ApiResponse confirmPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new BusinessException("Payment not found"));

        if (payment.getStatus() == PaymentStatus.PAID) {
            return new ApiResponse("ALREADY_CONFIRMED");
        }

        payment.setStatus(PaymentStatus.PAID);
        payment.setPaidAt(LocalDateTime.now());

        paymentRepository.save(payment);

        updateFeeStatus(payment.getFee());

        return new ApiResponse("SUCCESS");
    }

    private void updateFeeStatus(Fee fee) {

        BigDecimal totalPaid =
                paymentRepository.sumPaidAmountByFeeId(fee.getId());

        if (totalPaid.compareTo(BigDecimal.ZERO) == 0) {
            fee.setStatus(FeeStatus.UNPAID);
        } else if (totalPaid.compareTo(fee.getAmount()) < 0) {
            fee.setStatus(FeeStatus.PARTIAL_PAID);
        } else {
            fee.setStatus(FeeStatus.PAID);
        }

        feeRepository.save(fee);
    }

}
