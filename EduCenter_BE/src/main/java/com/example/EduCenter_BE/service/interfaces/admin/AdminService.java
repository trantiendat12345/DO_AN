package com.example.EduCenter_BE.service.interfaces.admin;

import com.example.EduCenter_BE.entity.*;
import com.example.EduCenter_BE.request.admin.add.AddStudentToClassAdminRequest;
import com.example.EduCenter_BE.request.admin.create.*;
import com.example.EduCenter_BE.response.admin.StudentAdminResponse;

public interface AdminService {

    // CREATE
    Student createStudent(CreateStudentAdminRequest request);

    Role createRole(CreateRoleAdminRequest request);

    Course createCourse(CreateCourseAdminRequest request);

    Parent createParent(CreateParentAdminRequest request);

    Teacher createTeacher(CreateTeacherAdminRequest request);

    Classroom createClassroom(CreateClassroomAdminRequest request);

    // ADD
    StudentAdminResponse addStudentToClassroom(AddStudentToClassAdminRequest request);

}
