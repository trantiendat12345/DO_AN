package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.Course;
import com.example.EduCenter_BE.response.CourseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course AS c WHERE c.name = :name AND c.isDeleted = false")
    Course findCourseByName(@Param("name") String name);

    @Query("SELECT c FROM Course AS c WHERE c.isDeleted = false ORDER BY c.createdAt DESC")
    Page<Course> findAllCourses(Pageable pageable);

}
