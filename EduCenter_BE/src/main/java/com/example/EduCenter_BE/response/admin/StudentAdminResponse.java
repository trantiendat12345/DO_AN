package com.example.EduCenter_BE.response.admin;

import com.example.EduCenter_BE.constant.enums.Gender;
import com.example.EduCenter_BE.constant.enums.StudentStatus;
import com.example.EduCenter_BE.entity.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentAdminResponse {

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

    public StudentAdminResponse(Student student) {
        this.fullName = student.getFullName();
        this.studentCode = student.getStudentCode();
        this.email = student.getEmail();
        this.dob = student.getDateOfBirth();
        this.gender = student.getGender();
        this.phone = student.getPhone();
        this.address = student.getAddress();
        this.status = student.getStatus();
        this.level = student.getLevel();
        this.note = student.getNote();
    }

}
