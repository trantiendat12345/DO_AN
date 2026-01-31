package com.example.EduCenter_BE.request.classroom;

import com.example.EduCenter_BE.constant.enums.ClassroomStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateClassroomRequest {

    @FutureOrPresent(message = "Ngày bắt đầu phải là ngày hiện tại hoặc tương lai")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Min(value = 1, message = "Số học sinh tối đa phải ít nhất là 1")
    @Max(value = 100, message = "Số học sinh tối đa không được vượt quá 100")
    private Integer maxStudent;

    private ClassroomStatus status;

    @Size(max = 100, message = "Tên khóa học không được vượt quá 100 ký tự")
    private String nameCourse;

}
