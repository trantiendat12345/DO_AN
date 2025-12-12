package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.constant.enums.ClassroomStatus;
import com.example.EduCenter_BE.entity.Classroom;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClassroomResponse {

    private LocalDate startDate;
    private LocalDate endDate;
    private Integer maxStudent;
    private String name;
    private ClassroomStatus status;
    private String nameCourse;

    public ClassroomResponse(Classroom classroom) {
        this.startDate = classroom.getStartDate();
        this.endDate = classroom.getEndDate();
        this.maxStudent = classroom.getMaxStudent();
        this.name = classroom.getName();
        this.status = classroom.getStatus();
        this.nameCourse = classroom.getCourse().getName();
    }

}
