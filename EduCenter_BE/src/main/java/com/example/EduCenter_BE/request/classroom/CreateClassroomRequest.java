package com.example.EduCenter_BE.request.classroom;

import com.example.EduCenter_BE.constant.enums.ClassroomStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateClassroomRequest {

    @NotNull(message = "Ngày bắt đầu không được để trống")
    @FutureOrPresent(message = "Ngày bắt đầu phải là ngày hiện tại hoặc tương lai")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "Số học sinh tối đa không được để trống")
    @Min(value = 1, message = "Số học sinh tối đa phải ít nhất là 1")
    @Max(value = 100, message = "Số học sinh tối đa không được vượt quá 100")
    private Integer maxStudent;

    @NotBlank(message = "Tên lớp học không được để trống")
    @Size(max = 50, message = "Tên lớp học không được vượt quá 50 ký tự")
    private String name;

    private ClassroomStatus status;

    @NotBlank(message = "Tên khóa học không được để trống")
    private String nameCourse;

}
