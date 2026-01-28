package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.enums.FeeStatus;
import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.*;
import com.example.EduCenter_BE.exception.BusinessException;
import com.example.EduCenter_BE.repository.ClassroomRepository;
import com.example.EduCenter_BE.repository.FeeRepository;
import com.example.EduCenter_BE.repository.StudentClassroomRepository;
import com.example.EduCenter_BE.repository.StudentRepository;
import com.example.EduCenter_BE.request.student.AddStudentToClassRequest;
import com.example.EduCenter_BE.response.StudentResponse;
import com.example.EduCenter_BE.service.interfaces.StudentClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class StudentClassroomServiceImpl implements StudentClassroomService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private StudentClassroomRepository studentClassroomRepository;

    @Autowired
    private FeeRepository feeRepository;

    @Override
    public StudentResponse addStudentToClassroom(AddStudentToClassRequest request) {
        Student student = studentRepository.findStudentByCode(request.getStudentCode());
        Classroom classroom = classroomRepository.findClassroomByName(request.getName());

        if (Objects.isNull(classroom) ||  Objects.isNull(student)) {
            throw new BusinessException(Message.STUDENT_OR_CLASSROOM_DOES_NOT_EXIST);
        }

        StudentClassroom isAssigned = studentClassroomRepository.existedByStudentCodeAndClassroomName(student.getStudentCode(), classroom.getName());

        if (isAssigned != null) {
            throw new BusinessException(Message.STUDENT_WAS_ALREADY_PRESENT_IN_THE_CLASS);
        }

        StudentClassroom studentClassroom = new StudentClassroom();
        studentClassroom.setClassroom(classroom);
        studentClassroom.setStudent(student);
        studentClassroomRepository.save(studentClassroom);

        //TẠO HỌC PHÍ
        if (!feeRepository
                .existsByStudentIdAndClassroomIdAndIsDeletedFalse(
                        student.getId(), classroom.getId())) {

            Course course = classroom.getCourse();

            Fee fee = new Fee();
            fee.setStudent(student);
            fee.setClassroom(classroom);
            fee.setAmount(course.getPrice());
            fee.setStatus(FeeStatus.UNPAID);
            fee.setDueDate(LocalDate.now().plusDays(30));

            feeRepository.save(fee);
        }

        return new StudentResponse(student, classroom);
    }
}
