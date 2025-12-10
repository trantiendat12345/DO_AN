package com.example.EduCenter_BE.response.admin;

import com.example.EduCenter_BE.entity.Student;
import lombok.Data;

@Data
public class StudentAdminResponse {

    private String fullName;
    private String studentCode;

    public StudentAdminResponse(Student student) {
        this.fullName = student.getFullName();
        this.studentCode = student.getStudentCode();
    }

}
