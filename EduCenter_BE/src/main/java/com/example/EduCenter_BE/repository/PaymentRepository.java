package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("""
        SELECT COALESCE(SUM(p.amountPaid), 0)
        FROM Payment p
        WHERE p.fee.id = :feeId
          AND p.status = 'SUCCESS'
          AND p.isDeleted = false
    """)
    BigDecimal sumPaidAmountByFeeId(@Param("feeId") Long feeId);

}
