package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student AS s WHERE s.studentCode = :code AND s.isDeleted = false")
    Student findStudentByCode(@Param("code") String code);

    @Query("SELECT s FROM Student AS s WHERE s.phone = :phone AND s.isDeleted = false")
    Student findStudentByPhone(@Param("phone") String phone);

    @Query("SELECT s FROM Student AS s WHERE s.email = :email AND s.isDeleted = false")
    Student findStudentByEmail(@Param("email") String email);

    @Query("SELECT s FROM Student AS s WHERE s.isDeleted = false ORDER BY s.createdAt DESC")
    Page<Student> findAllStudents(Pageable pageable);

}
