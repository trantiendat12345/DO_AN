package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.TeacherClassroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherClassroomRepository extends JpaRepository<TeacherClassroom, Long> {
}
