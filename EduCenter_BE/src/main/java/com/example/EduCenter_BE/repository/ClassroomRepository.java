package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.Classroom;
import com.example.EduCenter_BE.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

    @Query("SELECT c FROM Classroom AS c WHERE c.name = :name AND c.isDeleted = false")
    Classroom findClassroomByName(@Param("name") String name);

    @Query("SELECT COUNT(*) FROM Classroom AS c WHERE c.isDeleted = false")
    Long countAllClassroom();

    @Query("SELECT c FROM Classroom AS c WHERE c.isDeleted = false ORDER BY c.createdAt DESC")
    Page<Classroom> findAllClassroom(Pageable pageable);

    @Query(value = "SELECT \n" +
            "    COUNT(sc.student_id) AS current_student\n" +
            "FROM edu_center.classroom c\n" +
            "LEFT JOIN edu_center.student_classroom sc \n" +
            "       ON c.id = sc.classroom_id\n" +
            "      AND sc.is_deleted = false\n" +
            "      WHERE c.id = :id;", nativeQuery = true)
    Integer countStudentClassroom(@Param("id")  Long id);

    @Query(value = "SELECT s.*\n" +
            "FROM edu_center.classroom c\n" +
            "JOIN edu_center.student_classroom sc \n" +
            "    ON c.id = sc.classroom_id\n" +
            "JOIN edu_center.student s \n" +
            "    ON sc.student_id = s.id\n" +
            "WHERE c.id = :id", nativeQuery = true)
    List<Student> getAllStudentInClassroom(@Param("id") Long id);

    Page<Classroom> findByIsDeletedFalse(Pageable pageable);

}
