package com.example.EduCenter_BE.request.admin.create;

import com.example.EduCenter_BE.constant.enums.ClassroomStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateClassroomAdminRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer maxStudent;
    private String name;
    private ClassroomStatus status;
    private String nameCourse;

}
