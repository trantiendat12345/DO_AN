package com.example.EduCenter_BE.request.student;

import com.example.EduCenter_BE.constant.enums.Gender;
import com.example.EduCenter_BE.constant.enums.StudentStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateStudentRequest {

    private String studentCode;
    private String fullName;
    private LocalDate dob;
    private Gender gender;
    private String phone;
    private String email;
    private String address;
    private StudentStatus status;
    private String level;
    private String note;

}
