package com.example.EduCenter_BE.response.admin;

import com.example.EduCenter_BE.entity.Course;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseAdminResponse {

    private String description;
    private String duration;
    private String name;
    private Float price;

    public CourseAdminResponse(Course course) {
        this.description = course.getDescription();
        this.duration = course.getDuration();
        this.name = course.getName();
        this.price = course.getPrice();
    }
}
