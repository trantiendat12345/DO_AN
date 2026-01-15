package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.entity.Course;
import com.example.EduCenter_BE.request.course.CreateCourseRequest;
import com.example.EduCenter_BE.request.course.UpdateCourseRequest;
import com.example.EduCenter_BE.response.CourseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    Course createCourse(CreateCourseRequest request);

    Page<CourseResponse> getAllCourses(Pageable pageable);

    CourseResponse getCourseByCourseName(String courseName);

    CourseResponse updateCourseByNameCourse(String nameCourse, UpdateCourseRequest request);

    String deleteCourse(String courseName);

}
