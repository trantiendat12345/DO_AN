package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.MaterialClassroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialClassroomRepository extends JpaRepository<MaterialClassroom, Long> {
}
