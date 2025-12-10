package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student AS s WHERE s.studentCode = :code")
    Student getStudentByCode (@Param("code") String code);

}
