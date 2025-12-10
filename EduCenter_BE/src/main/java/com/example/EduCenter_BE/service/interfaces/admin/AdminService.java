package com.example.EduCenter_BE.service.interfaces.admin;

import com.example.EduCenter_BE.entity.*;
import com.example.EduCenter_BE.request.admin.*;

public interface AdminService {

    Student createStudent(CreateStudentAdminRequest request);

    Role createRole(CreateRoleAdminRequest request);

    Course createCourse(CreateCourseAdminRequest request);

    Parent createParent(CreateParentAdminRequest request);

    Teacher createTeacher(CreateTeacherAdminRequest request);

    Classroom createClassroom(CreateClassroomAdminRequest request);

}
