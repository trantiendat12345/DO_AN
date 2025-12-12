package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Course;
import com.example.EduCenter_BE.repository.CourseRepository;
import com.example.EduCenter_BE.request.CreateCourseRequest;
import com.example.EduCenter_BE.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;


    @Override
    public Course createCourse(CreateCourseRequest request) {
        String name = request.getName();

        Course checkCourse = courseRepository.findCourseByName(name);

        if (!Objects.isNull(checkCourse)) {
            throw new RuntimeException(Message.COURSE_EXISTED);
        }

        Course course = new Course();
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setDuration(request.getDuration());
        course.setPrice(request.getPrice());
        return courseRepository.save(course);
    }
}
