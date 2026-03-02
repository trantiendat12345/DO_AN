package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Response chứa thông tin lịch dạy của giáo viên
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherScheduleResponse {

    private Long id;
    private String classroomName;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor tạo response từ Schedule entity
     */
    public TeacherScheduleResponse(Schedule schedule) {
        this.id = schedule.getId();
        this.classroomName = schedule.getClassroom().getName();
        this.date = schedule.getDate();
        this.startTime = schedule.getStartTime();
        this.endTime = schedule.getEndTime();
    }

}
