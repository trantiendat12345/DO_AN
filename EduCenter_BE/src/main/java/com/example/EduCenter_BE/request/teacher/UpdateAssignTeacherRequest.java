package com.example.EduCenter_BE.request.teacher;

import com.example.EduCenter_BE.constant.enums.TeacherRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateAssignTeacherRequest {

    @NotBlank(message = "Mã giáo viên không được để trống")
    private String teacherCode;

    private TeacherRole teacherRole;

}
