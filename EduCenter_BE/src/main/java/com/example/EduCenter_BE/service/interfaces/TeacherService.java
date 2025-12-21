package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.entity.Teacher;
import com.example.EduCenter_BE.request.CreateTeacherRequest;
import com.example.EduCenter_BE.request.UpdateTeacherRequest;
import com.example.EduCenter_BE.response.TeacherResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {

    Teacher createTeacher(CreateTeacherRequest request);

    Page<TeacherResponse> getAllTeachers(Pageable pageable);

    TeacherResponse getTeacherByCode(String code);

    TeacherResponse updateTeacher(UpdateTeacherRequest request, String teacherCode);

    String deleteTeacher(String teacherCode);

    Long totalTeacherCount();

}
