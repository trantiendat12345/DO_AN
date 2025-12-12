package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.entity.Course;
import com.example.EduCenter_BE.request.CreateCourseRequest;

public interface CourseService {

    Course createCourse(CreateCourseRequest request);

}
