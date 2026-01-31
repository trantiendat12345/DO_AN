package com.example.EduCenter_BE.request.course;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateCourseRequest {

    @Size(max = 500, message = "Mô tả không được vượt quá 500 ký tự")
    private String description;

    @Size(max = 50, message = "Thời lượng không được vượt quá 50 ký tự")
    private String duration;

    @Size(max = 100, message = "Tên khóa học không được vượt quá 100 ký tự")
    private String nameCourse;

    @DecimalMin(value = "0.0", inclusive = false, message = "Giá phải lớn hơn 0")
    private BigDecimal price;

}
