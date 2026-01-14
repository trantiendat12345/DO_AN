package com.example.EduCenter_BE.request.student;

import lombok.Data;

@Data
public class AddStudentToClassRequest {

    private String className;
    private String studentCode;

}
