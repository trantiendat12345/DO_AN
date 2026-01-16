package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Classroom;
import com.example.EduCenter_BE.entity.Student;
import com.example.EduCenter_BE.entity.StudentClassroom;
import com.example.EduCenter_BE.repository.ClassroomRepository;
import com.example.EduCenter_BE.repository.StudentClassroomRepository;
import com.example.EduCenter_BE.repository.StudentRepository;
import com.example.EduCenter_BE.request.student.AddStudentToClassRequest;
import com.example.EduCenter_BE.response.StudentResponse;
import com.example.EduCenter_BE.service.interfaces.StudentClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StudentClassroomServiceImpl implements StudentClassroomService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private StudentClassroomRepository studentClassroomRepository;

    @Override
    public StudentResponse addStudentToClassroom(AddStudentToClassRequest request) {
        Student student = studentRepository.findStudentByCode(request.getStudentCode());
        Classroom classroom = classroomRepository.findClassroomByName(request.getName());

        if (Objects.isNull(classroom) ||  Objects.isNull(student)) {
            throw new RuntimeException(Message.STUDENT_OR_CLASSROOM_DOES_NOT_EXIST);
        }

        StudentClassroom isAssigned = studentClassroomRepository.existedByStudentCodeAndClassroomName(student.getStudentCode(), classroom.getName());

        if (isAssigned != null) {
            throw new RuntimeException(Message.STUDENT_WAS_ALREADY_PRESENT_IN_THE_CLASS);
        }

        StudentClassroom studentClassroom = new StudentClassroom();
        studentClassroom.setClassroom(classroom);
        studentClassroom.setStudent(student);
        studentClassroomRepository.save(studentClassroom);

        return new StudentResponse(student, classroom);
    }
}
