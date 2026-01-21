package com.example.EduCenter_BE.response;

import com.example.EduCenter_BE.entity.Classroom;
import com.example.EduCenter_BE.entity.Teacher;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AssignTeacherResponse {

    private String name;
    private TeacherResponse mainTeacher;
    private List<TeacherResponse> assistantTeacher;

    public AssignTeacherResponse(Classroom classroom) {
        this.name = classroom.getName();
        this.assistantTeacher = new ArrayList<>();
    }

}
