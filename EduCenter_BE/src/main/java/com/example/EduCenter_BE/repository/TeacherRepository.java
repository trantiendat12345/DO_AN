package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t FROM Teacher AS t WHERE t.teacherCode = :code AND t.isDeleted = false")
    Teacher findTeacherByCode(@Param("code") String code);

    @Query("SELECT t FROM Teacher AS t WHERE t.phone = :phone AND t.isDeleted = false")
    Teacher findTeacherByPhone(@Param("phone") String phone);

    @Query("SELECT t FROM Teacher AS t WHERE t.email = :email AND t.isDeleted = false")
    Teacher findTeacherByEmail(@Param("email") String email);

}
