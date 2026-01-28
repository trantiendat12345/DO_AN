package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.entity.Course;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponse {

    private String description;
    private String duration;
    private String nameCourse;
    private BigDecimal price;

    public CourseResponse(Course course) {
        this.description = course.getDescription();
        this.duration = course.getDuration();
        this.nameCourse = course.getName();
        this.price = course.getPrice();
    }
}
