package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Student;
import com.example.EduCenter_BE.repository.StudentRepository;
import com.example.EduCenter_BE.request.CreateStudentRequest;
import com.example.EduCenter_BE.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(CreateStudentRequest request) {
        String codeStudent = request.getStudentCode();
        String emailStudent = request.getEmail();
        String phoneStudent = request.getPhone();

        Student checkStudentByCode = studentRepository.findStudentByCode(codeStudent);
        Student checkStudentByEmail = studentRepository.findStudentByEmail(emailStudent);
        Student checkStudentByPhone = studentRepository.findStudentByPhone(phoneStudent);

        if (!Objects.isNull(checkStudentByCode) || !Objects.isNull(checkStudentByEmail) || !Objects.isNull(checkStudentByPhone)) {
            throw new RuntimeException(Message.STUDENT_EXISTED);
        }

        Student student = new Student();
        student.setStudentCode(request.getStudentCode());
        student.setFullName(request.getFullName());
        student.setDateOfBirth(request.getDob());
        student.setGender(request.getGender());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setEmail(request.getEmail());
        student.setStatus(request.getStatus());
        student.setLevel(request.getLevel());
        student.setNote(request.getNote());

        return studentRepository.save(student);
    }

}
