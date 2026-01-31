package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.Fee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {

    boolean existsByStudentIdAndClassroomIdAndIsDeletedFalse(
            Long studentId,
            Long classroomId
    );

    Page<Fee> findByIsDeletedFalse(Pageable pageable);

}
