package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("SELECT t FROM Teacher AS t WHERE t.teacherCode = :code")
    Teacher getTeacherByCode(@Param("code") String code);

}
