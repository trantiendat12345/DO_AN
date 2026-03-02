package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.constant.enums.TeacherStatus;
import com.example.EduCenter_BE.entity.Teacher;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Response chứa thông tin chi tiết giáo viên cho Teacher Portal
 * Bao gồm thông tin cá nhân của giáo viên
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherInfoResponse {

    private String teacherCode;
    private String fullName;
    private String phone;
    private String email;
    private TeacherStatus status;
    private Long totalClasses; // Tổng số lớp đang dạy
    private Long totalStudents; // Tổng số học viên

    /**
     * Constructor tạo response từ entity Teacher
     */
    public TeacherInfoResponse(Teacher teacher, Long totalClasses, Long totalStudents) {
        this.teacherCode = teacher.getTeacherCode();
        this.fullName = teacher.getFullName();
        this.phone = teacher.getPhone();
        this.email = teacher.getEmail();
        this.status = teacher.getStatus();
        this.totalClasses = totalClasses;
        this.totalStudents = totalStudents;
    }

}
