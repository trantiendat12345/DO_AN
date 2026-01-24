package com.example.EduCenter_BE.request.teacher;

import com.example.EduCenter_BE.constant.enums.TeacherRole;
import lombok.Data;

@Data
public class UpdateAssignTeacherRequest {

    private String teacherCode;
    private TeacherRole teacherRole;

}
