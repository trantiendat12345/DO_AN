package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.entity.Classroom;
import com.example.EduCenter_BE.request.CreateClassroomRequest;

public interface ClassroomService {

    Classroom createClassroom(CreateClassroomRequest classroom);

}
