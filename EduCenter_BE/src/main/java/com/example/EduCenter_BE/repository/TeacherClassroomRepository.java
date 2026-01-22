package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.constant.enums.TeacherRole;
import com.example.EduCenter_BE.entity.Classroom;
import com.example.EduCenter_BE.entity.Teacher;
import com.example.EduCenter_BE.entity.TeacherClassroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherClassroomRepository extends JpaRepository<TeacherClassroom, Long> {

    boolean existsByTeacherAndClassroom(Teacher teacher, Classroom classroom);

    List<TeacherClassroom> findByClassroom(Classroom classroom);

    boolean existsByClassroomAndTeacherRole(Classroom classroom, TeacherRole role);

    List<TeacherClassroom> findByClassroomInAndIsDeletedFalse(
            List<Classroom> classrooms
    );


}
