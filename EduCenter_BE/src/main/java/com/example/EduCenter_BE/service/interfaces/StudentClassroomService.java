package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.request.AddStudentToClassRequest;
import com.example.EduCenter_BE.response.StudentResponse;

public interface StudentClassroomService {

    StudentResponse addStudentToClassroom(AddStudentToClassRequest request);

}
