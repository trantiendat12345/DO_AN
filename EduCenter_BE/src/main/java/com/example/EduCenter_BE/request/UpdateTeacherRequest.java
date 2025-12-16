package com.example.EduCenter_BE.request;

import com.example.EduCenter_BE.constant.enums.TeacherStatus;
import lombok.Data;

@Data
public class UpdateTeacherRequest {

    private String email;
    private String fullName;
    private String phone;
    private TeacherStatus status;

}
