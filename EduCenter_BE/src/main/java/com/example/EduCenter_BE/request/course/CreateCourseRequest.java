package com.example.EduCenter_BE.request.course;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CreateCourseRequest {

    private String description;
    private String duration;
    private String nameCourse;
    private BigDecimal price;

}
