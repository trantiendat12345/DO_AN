package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.entity.Classroom;
import com.example.EduCenter_BE.request.CreateClassroomRequest;
import com.example.EduCenter_BE.request.UpdateClassroomRequest;
import com.example.EduCenter_BE.response.ClassroomResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClassroomService {

    Classroom createClassroom(CreateClassroomRequest classroom);

    Long totalClassroomCount();

    ClassroomResponse updateClassroom(UpdateClassroomRequest request, String name);

    Page<ClassroomResponse> getAllClassrooms(Pageable pageable);

    ClassroomResponse getClassroom(String name);

    String deleteClassroom(String name);

}
