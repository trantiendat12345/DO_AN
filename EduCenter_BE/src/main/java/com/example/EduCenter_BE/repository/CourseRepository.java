package com.example.EduCenter_BE.repository;

import com.example.EduCenter_BE.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course AS c WHERE c.name = :name")
    Course getCourseByName(@Param("name") String name);

}
