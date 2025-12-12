package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.StudentClassroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentClassroomRepository extends JpaRepository<StudentClassroom, Long> {

    @Query(value = "SELECT sc.*\n" +
            "FROM student s \n" +
            "LEFT JOIN student_classroom sc ON s.id = sc.student_id \n" +
            "LEFT JOIN classroom c ON c.id = sc.classroom_id \n" +
            "WHERE s.student_code  = :code AND c.name  = :name", nativeQuery = true)
    StudentClassroom existedByStudentCodeAndClassroomName(@Param("code") String studentCode, @Param("name") String classroomName);

}
