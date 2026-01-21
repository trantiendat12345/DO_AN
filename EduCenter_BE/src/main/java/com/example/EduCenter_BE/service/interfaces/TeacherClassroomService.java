package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.request.teacher.AssignTeacherRequest;
import com.example.EduCenter_BE.response.AssignTeacherResponse;

public interface TeacherClassroomService {

    AssignTeacherResponse assignTeacher(AssignTeacherRequest request);

}
