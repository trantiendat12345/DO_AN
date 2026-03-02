package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.constant.enums.ClassroomStatus;
import com.example.EduCenter_BE.constant.enums.TeacherRole;
import com.example.EduCenter_BE.entity.Classroom;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

/**
 * Response chứa thông tin lớp học mà giáo viên đang dạy
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherClassroomResponse {

    private Long id;
    private String name;
    private String courseName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer maxStudent;
    private Integer currentStudent; // Số học viên hiện tại
    private ClassroomStatus status;
    private TeacherRole teacherRole; // Vai trò của giáo viên trong lớp này

    /**
     * Constructor tạo response từ Classroom và các thông tin bổ sung
     */
    public TeacherClassroomResponse(Classroom classroom, Integer currentStudent, TeacherRole teacherRole) {
        this.id = classroom.getId();
        this.name = classroom.getName();
        this.courseName = classroom.getCourse().getName();
        this.startDate = classroom.getStartDate();
        this.endDate = classroom.getEndDate();
        this.maxStudent = classroom.getMaxStudent();
        this.currentStudent = currentStudent;
        this.status = classroom.getStatus();
        this.teacherRole = teacherRole;
    }

}
