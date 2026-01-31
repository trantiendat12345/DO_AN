package com.example.EduCenter_BE.request.teacher;

import com.example.EduCenter_BE.constant.enums.TeacherRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AssignTeacherRequest {

    @NotBlank(message = "Tên lớp học không được để trống")
    private String name;

    @NotBlank(message = "Mã giáo viên không được để trống")
    private String teacherCode;

    @NotNull(message = "Vai trò giáo viên không được để trống")
    private TeacherRole teacherRole;

}
