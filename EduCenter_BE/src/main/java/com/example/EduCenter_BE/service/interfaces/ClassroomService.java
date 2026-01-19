package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.entity.Classroom;
import com.example.EduCenter_BE.request.classroom.CreateClassroomRequest;
import com.example.EduCenter_BE.request.classroom.UpdateClassroomRequest;
import com.example.EduCenter_BE.response.ClassroomResponse;
import com.example.EduCenter_BE.response.StudentResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClassroomService {

    Classroom createClassroom(CreateClassroomRequest classroom);

    Long totalClassroomCount();

    ClassroomResponse updateClassroom(UpdateClassroomRequest request, String name);

    Page<ClassroomResponse> getAllClassrooms(Pageable pageable);

    ClassroomResponse getClassroom(String name);

    String deleteClassroom(String name);

    List<StudentResponse> getAllStudentInClassroom(String name);

}
