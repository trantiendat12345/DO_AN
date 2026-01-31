package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.request.payment.CreatePaymentRequest;
import com.example.EduCenter_BE.response.ApiResponse;
import com.example.EduCenter_BE.response.PaymentResponse;

public interface PaymentService {

    PaymentResponse createPayment(CreatePaymentRequest request);

    ApiResponse confirmPayment(Long paymentId);

}
