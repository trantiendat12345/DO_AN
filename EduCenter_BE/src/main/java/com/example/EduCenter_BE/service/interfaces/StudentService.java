package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.entity.Student;
import com.example.EduCenter_BE.request.student.CreateStudentRequest;
import com.example.EduCenter_BE.request.student.UpdateStudentRequest;
import com.example.EduCenter_BE.response.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {

    Student createStudent(CreateStudentRequest request);

    Page<StudentResponse> getAllStudents(Pageable pageable);

    Page<StudentResponse> searchStudents(String keyword, Pageable pageable);

    StudentResponse getStudentByCode(String studentCode);

    StudentResponse updateStudent(UpdateStudentRequest request, String studentCode);

    String deleteStudent(String studentCode);

    Long totalStudentCount();

    String importStudents(MultipartFile file);

}
