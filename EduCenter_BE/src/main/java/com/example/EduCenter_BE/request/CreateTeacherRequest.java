package com.example.EduCenter_BE.request;

import com.example.EduCenter_BE.constant.enums.TeacherStatus;
import lombok.Data;

@Data
public class CreateTeacherRequest {

    private String email;
    private String fullName;
    private String phone;
    private TeacherStatus status;
    private String teacherCode;

}
