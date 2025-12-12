package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.entity.Teacher;
import com.example.EduCenter_BE.request.CreateTeacherRequest;

public interface TeacherService {

    Teacher createTeacher(CreateTeacherRequest request);

}
