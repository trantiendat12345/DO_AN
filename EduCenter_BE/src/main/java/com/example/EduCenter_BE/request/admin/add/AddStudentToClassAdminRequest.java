package com.example.EduCenter_BE.request.admin.add;

import lombok.Data;

@Data
public class AddStudentToClassAdminRequest {

    private String className;
    private String studentCode;

}
