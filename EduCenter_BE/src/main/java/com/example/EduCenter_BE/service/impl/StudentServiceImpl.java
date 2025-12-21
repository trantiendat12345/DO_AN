package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Student;
import com.example.EduCenter_BE.repository.StudentRepository;
import com.example.EduCenter_BE.request.CreateStudentRequest;
import com.example.EduCenter_BE.request.UpdateStudentRequest;
import com.example.EduCenter_BE.response.StudentResponse;
import com.example.EduCenter_BE.service.interfaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public Page<StudentResponse> getAllStudents(Pageable pageable) {
        return  studentRepository.findAllStudents(pageable).map(StudentResponse::new);
    }

    @Override
    public StudentResponse getStudentByCode(String studentCode) {
        Student student = studentRepository.findStudentByCode(studentCode);

        if (Objects.isNull(student)) {
            throw new RuntimeException(Message.STUDENT_DOES_NOT_EXIST);
        }

        return new StudentResponse(student);
    }

    @Override
    public StudentResponse updateStudent(UpdateStudentRequest request, String studentCode) {
        Student student = studentRepository.findStudentByCode(studentCode);
        Student email = studentRepository.findStudentByEmail(request.getEmail());
        Student phone = studentRepository.findStudentByPhone(request.getPhone());

        if (Objects.isNull(student)) {
            throw new RuntimeException(Message.STUDENT_DOES_NOT_EXIST);
        }

        if (email != null && !email.getId().equals(student.getId())) {
            throw new RuntimeException(Message.EMAIL_EXISTED);
        }

        if (phone != null && !phone.getId().equals(student.getId())) {
            throw new RuntimeException(Message.PHONE_EXISTED);
        }

        student.setFullName(request.getFullName());
        student.setDateOfBirth(request.getDob());
        student.setGender(request.getGender());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setEmail(request.getEmail());
        student.setStatus(request.getStatus());
        student.setLevel(request.getLevel());
        student.setNote(request.getNote());

        student.setUpdatedBy(1L);

        Student updatedStudent = studentRepository.save(student);

        return new StudentResponse(updatedStudent);
    }

    @Override
    public String deleteStudent(String studentCode) {
        Student student = studentRepository.findStudentByCode(studentCode);

        if (Objects.isNull(student)) {
            throw new RuntimeException(Message.STUDENT_DOES_NOT_EXIST);
        }

        student.setIsDeleted(true);
        student.setDeletedAt(LocalDateTime.now());
        student.setDeletedBy(1L);
        studentRepository.save(student);

        return Message.DELETED_SUCCESSFULLY;
    }

    @Override
    public Long totalStudentCount() {
        return studentRepository.countAllStudents();
    }

}
