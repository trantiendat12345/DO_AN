package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.constant.enums.Gender;
import com.example.EduCenter_BE.constant.enums.StudentStatus;
import com.example.EduCenter_BE.entity.Student;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Response chứa thông tin chi tiết học viên cho Student Portal
 * Bao gồm thông tin cá nhân và danh sách lớp học
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentInfoResponse {

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

    // Danh sách lớp học đang theo học
    private List<String> classrooms;

    /**
     * Constructor tạo response từ entity Student và danh sách classrooms
     */
    public StudentInfoResponse(Student student, List<String> classrooms) {
        this.studentCode = student.getStudentCode();
        this.fullName = student.getFullName();
        this.dob = student.getDateOfBirth();
        this.gender = student.getGender();
        this.phone = student.getPhone();
        this.email = student.getEmail();
        this.address = student.getAddress();
        this.status = student.getStatus();
        this.level = student.getLevel();
        this.note = student.getNote();
        this.classrooms = classrooms;
    }

}
