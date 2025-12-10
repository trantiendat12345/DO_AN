package com.example.EduCenter_BE.request.admin;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCourseAdminRequest {

    private String description;
    private String duration;
    private String name;
    private Float price;

}
