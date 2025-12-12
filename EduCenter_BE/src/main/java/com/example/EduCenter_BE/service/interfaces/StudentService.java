package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.entity.Student;
import com.example.EduCenter_BE.request.CreateStudentRequest;

public interface StudentService {

    Student createStudent(CreateStudentRequest request);

}
