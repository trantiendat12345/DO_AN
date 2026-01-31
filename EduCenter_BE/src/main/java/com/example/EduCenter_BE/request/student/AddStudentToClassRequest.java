package com.example.EduCenter_BE.request.student;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddStudentToClassRequest {

    @NotBlank(message = "Tên lớp học không được để trống")
    private String name;

    @NotBlank(message = "Mã học sinh không được để trống")
    private String studentCode;

}
