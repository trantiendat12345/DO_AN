package com.example.EduCenter_BE.request;

import com.example.EduCenter_BE.constant.enums.ClassroomStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateClassroomRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer maxStudent;
    private String name;
    private ClassroomStatus status;
    private String nameCourse;

}
