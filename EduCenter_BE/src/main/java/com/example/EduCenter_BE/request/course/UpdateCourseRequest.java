package com.example.EduCenter_BE.request.course;

import lombok.Data;

@Data
public class UpdateCourseRequest {

    private String description;
    private String duration;
    private String name;
    private Float price;

}
