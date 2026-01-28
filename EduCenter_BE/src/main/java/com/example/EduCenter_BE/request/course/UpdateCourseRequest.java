package com.example.EduCenter_BE.request.course;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateCourseRequest {

    private String description;
    private String duration;
    private String nameCourse;
    private BigDecimal price;

}
