package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Classroom;
import com.example.EduCenter_BE.entity.Course;
import com.example.EduCenter_BE.exception.BusinessException;
import com.example.EduCenter_BE.repository.ClassroomRepository;
import com.example.EduCenter_BE.repository.CourseRepository;
import com.example.EduCenter_BE.request.classroom.CreateClassroomRequest;
import com.example.EduCenter_BE.request.classroom.UpdateClassroomRequest;
import com.example.EduCenter_BE.response.ClassroomResponse;
import com.example.EduCenter_BE.service.BaseService;
import com.example.EduCenter_BE.service.interfaces.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
public class ClassroomServiceImpl extends BaseService implements ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Classroom createClassroom(CreateClassroomRequest request) {
        String name = request.getName();

        Classroom checkClassroom = classroomRepository.findClassroomByName(name);

        if (!Objects.isNull(checkClassroom)) {
            throw new BusinessException(Message.CLASSROOM_EXISTED);
        }

        Course course = courseRepository.findCourseByName(request.getNameCourse());

        if (Objects.isNull(course)) {
            throw new BusinessException(Message.COURSE_DOES_NOT_EXIST);
        }

        String duration = course.getDuration();

        Set<DayOfWeek> studyDays = Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY);

        LocalDate endDate = BaseService.calculateEndDate(request.getStartDate(), Integer.parseInt(duration), studyDays);

        Classroom classroom = new Classroom();
        classroom.setName(request.getName());
        classroom.setStatus(request.getStatus());
        classroom.setStartDate(request.getStartDate());
        classroom.setEndDate(endDate);
        classroom.setMaxStudent(request.getMaxStudent());
        classroom.setCourse(course);
        return classroomRepository.save(classroom);
    }

    @Override
    public Long totalClassroomCount() {
        return classroomRepository.countAllClassroom();
    }

    @Override
    public ClassroomResponse updateClassroom(UpdateClassroomRequest request, String name) {
        Classroom classroom = classroomRepository.findClassroomByName(name);

        if (Objects.isNull(classroom)) {
            throw new BusinessException(Message.CLASSROOM_DOES_NOT_EXIST);
        }

        Course course = courseRepository.findCourseByName(request.getNameCourse());

        if (Objects.isNull(course)) {
            throw new BusinessException(Message.COURSE_DOES_NOT_EXIST);
        }

        String duration = course.getDuration();

        Set<DayOfWeek> studyDays = Set.of(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY);

        LocalDate endDate = BaseService.calculateEndDate(request.getStartDate(), Integer.parseInt(duration), studyDays);

        classroom.setStartDate(request.getStartDate());
        classroom.setMaxStudent(request.getMaxStudent());
        classroom.setCourse(course);
        classroom.setStatus(request.getStatus());
        classroom.setEndDate(endDate);

        classroom.setUpdatedBy(1L);

        Classroom updateClassroom = classroomRepository.save(classroom);

        return new ClassroomResponse(updateClassroom);
    }

    @Override
    public Page<ClassroomResponse> getAllClassrooms(Pageable pageable) {
        return classroomRepository.findAllClassroom(pageable).map((c) -> {
            Integer currentStudentCount = classroomRepository.countStudentClassroom(c.getId());
            return new ClassroomResponse(c,  currentStudentCount);
        });
    }

    @Override
    public ClassroomResponse getClassroom(String name) {
        Classroom classroom = classroomRepository.findClassroomByName(name);

        if (Objects.isNull(classroom)) {
            throw new BusinessException(Message.CLASSROOM_DOES_NOT_EXIST);
        }

        return new ClassroomResponse(classroom);
    }

    @Override
    public String deleteClassroom(String name) {
        Classroom classroom = classroomRepository.findClassroomByName(name);

        if (Objects.isNull(classroom)) {
            throw new BusinessException(Message.CLASSROOM_DOES_NOT_EXIST);
        }

        classroom.setIsDeleted(true);
        classroom.setDeletedAt(LocalDateTime.now());
        classroom.setDeletedBy(1L);
        classroomRepository.save(classroom);

        return Message.DELETED_SUCCESSFULLY;
    }
}
