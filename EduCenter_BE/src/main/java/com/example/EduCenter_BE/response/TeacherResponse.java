package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.constant.enums.TeacherStatus;
import com.example.EduCenter_BE.entity.Teacher;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherResponse {

    private String email;
    private String fullName;
    private String phone;
    private TeacherStatus status;
    private String teacherCode;

    public TeacherResponse(Teacher teacher) {
        this.email = teacher.getEmail();
        this.fullName = teacher.getFullName();
        this.teacherCode = teacher.getTeacherCode();
        this.phone = teacher.getPhone();
        this.status = teacher.getStatus();
    }

}
