package com.example.EduCenter_BE.response.admin;

import com.example.EduCenter_BE.constant.enums.TeacherStatus;
import com.example.EduCenter_BE.entity.Teacher;
import lombok.Data;

@Data
public class TeacherAdminResponse {

    private String email;
    private String fullName;
    private String phone;
    private TeacherStatus status;
    private String teacherCode;

    public TeacherAdminResponse(Teacher teacher) {
        this.email = teacher.getEmail();
        this.fullName = teacher.getFullName();
        this.phone = teacher.getPhone();
        this.status = teacher.getStatus();
        this.teacherCode = teacher.getTeacherCode();
    }

}
