package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.request.teacher.AssignTeacherRequest;
import com.example.EduCenter_BE.response.AssignTeacherResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherClassroomService {

    AssignTeacherResponse assignTeacher(AssignTeacherRequest request);

    Page<AssignTeacherResponse> getAllAssignedTeacher(Pageable pageable);
}
