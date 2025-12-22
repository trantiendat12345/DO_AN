package com.example.EduCenter_BE.request;

import com.example.EduCenter_BE.constant.enums.ClassroomStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateClassroomRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private Integer maxStudent;
    private String name;
    private ClassroomStatus status;
    private String nameCourse;

}
