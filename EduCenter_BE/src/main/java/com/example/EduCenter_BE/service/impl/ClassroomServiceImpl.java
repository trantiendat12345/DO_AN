package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Classroom;
import com.example.EduCenter_BE.entity.Course;
import com.example.EduCenter_BE.repository.ClassroomRepository;
import com.example.EduCenter_BE.repository.CourseRepository;
import com.example.EduCenter_BE.request.CreateClassroomRequest;
import com.example.EduCenter_BE.service.interfaces.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Classroom createClassroom(CreateClassroomRequest request) {
        String name = request.getName();

        Classroom checkClassroom = classroomRepository.findClassroomByName(name);

        if (!Objects.isNull(checkClassroom)) {
            throw new RuntimeException(Message.CLASSROOM_EXISTED);
        }

        Course course = courseRepository.findCourseByName(request.getNameCourse());

        if (Objects.isNull(course)) {
            throw new RuntimeException(Message.COURSE_DOES_NOT_EXIST);
        }

        Classroom classroom = new Classroom();
        classroom.setName(request.getName());
        classroom.setStatus(request.getStatus());
        classroom.setStartDate(request.getStartDate());
        classroom.setEndDate(request.getEndDate());
        classroom.setMaxStudent(request.getMaxStudent());
        classroom.setCourse(course);
        return classroomRepository.save(classroom);
    }

    @Override
    public Long totalClassroomCount() {
        return classroomRepository.countAllClassroom();
    }
}
