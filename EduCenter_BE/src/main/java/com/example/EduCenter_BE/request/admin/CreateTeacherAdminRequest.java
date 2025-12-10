package com.example.EduCenter_BE.request.admin;

import com.example.EduCenter_BE.constant.enums.TeacherStatus;
import lombok.Data;

@Data
public class CreateTeacherAdminRequest {

    private String email;
    private String fullName;
    private String phone;
    private TeacherStatus status;
    private String teacherCode;

}
