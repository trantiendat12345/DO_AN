package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Course;
import com.example.EduCenter_BE.repository.CourseRepository;
import com.example.EduCenter_BE.request.course.CreateCourseRequest;
import com.example.EduCenter_BE.request.course.UpdateCourseRequest;
import com.example.EduCenter_BE.response.CourseResponse;
import com.example.EduCenter_BE.service.interfaces.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public Page<CourseResponse> getAllCourses(Pageable pageable) {
        return courseRepository.findAllCourses(pageable).map(CourseResponse::new);
    }

    @Override
    public CourseResponse getCourseByCourseName(String courseName) {
        Course course = courseRepository.findCourseByName(courseName);

        if (Objects.isNull(course)) {
            throw new RuntimeException(Message.COURSE_DOES_NOT_EXIST);
        }

        return new CourseResponse(course);
    }

    @Override
    public CourseResponse updateCourseByNameCourse(String nameCourse, UpdateCourseRequest request) {
        Course course = courseRepository.findCourseByName(nameCourse);

        String name = request.getName();

        if (Objects.isNull(course)) {
            throw new RuntimeException(Message.COURSE_DOES_NOT_EXIST);
        }

        if (!Objects.isNull(name)) {
            throw new RuntimeException(Message.COURSE_EXISTED);
        }

        course.setName(name);
        course.setDescription(request.getDescription());
        course.setDuration(request.getDuration());
        course.setPrice(request.getPrice());
        Course savedCourse = courseRepository.save(course);

        return new CourseResponse(savedCourse);
    }

    @Override
    public String deleteCourse(String courseName) {
        Course course = courseRepository.findCourseByName(courseName);

        if (Objects.isNull(course)) {
            throw new RuntimeException(Message.COURSE_DOES_NOT_EXIST);
        }

        course.setIsDeleted(true);
        course.setDeletedAt(LocalDateTime.now());
        course.setDeletedBy(1L);
        courseRepository.save(course);

        return Message.DELETED_SUCCESSFULLY;
    }
}
