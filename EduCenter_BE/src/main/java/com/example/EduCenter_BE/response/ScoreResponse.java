package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.constant.enums.ScoreType;
import com.example.EduCenter_BE.entity.Score;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScoreResponse {

    private Long id;
    private String studentCode;
    private String studentName;
    private String classroomName;
    private ScoreType type;
    private Float value;
    private String note;

    public ScoreResponse(Score score) {
        this.id = score.getId();
        this.studentCode = score.getStudent().getStudentCode();
        this.studentName = score.getStudent().getFullName();
        this.classroomName = score.getClassroom().getName();
        this.type = score.getType();
        this.value = score.getValue();
        this.note = score.getNote();
    }
}
