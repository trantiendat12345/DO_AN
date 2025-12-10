package com.example.EduCenter_BE.api.impl.admin;

import com.example.EduCenter_BE.api.interfaces.admin.AdminApi;
import com.example.EduCenter_BE.entity.*;
import com.example.EduCenter_BE.request.admin.*;
import com.example.EduCenter_BE.response.admin.*;
import com.example.EduCenter_BE.service.interfaces.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AdminApiImpl implements AdminApi {

    @Autowired
    private AdminService adminService;

    @Override
    public ResponseEntity<StudentAdminResponse> createStudent (CreateStudentAdminRequest request) {
        Student student = adminService.createStudent(request);
        StudentAdminResponse response = new StudentAdminResponse(student);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<RoleAdminResponse> createRole(CreateRoleAdminRequest request) {
        Role role = adminService.createRole(request);
        RoleAdminResponse response = new RoleAdminResponse(role);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<CourseAdminResponse> createCourse(CreateCourseAdminRequest request) {
        Course course = adminService.createCourse(request);
        CourseAdminResponse response = new CourseAdminResponse(course);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ParentAdminResponse> createParent(CreateParentAdminRequest request) {
        Parent parent = adminService.createParent(request);
        ParentAdminResponse response = new ParentAdminResponse(parent);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<TeacherAdminResponse> createTeacher(CreateTeacherAdminRequest request) {
        Teacher teacher = adminService.createTeacher(request);
        TeacherAdminResponse response = new TeacherAdminResponse(teacher);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ClassroomAdminResponse> createClassroom(CreateClassroomAdminRequest request) {
        Classroom classroom = adminService.createClassroom(request);
        ClassroomAdminResponse response = new ClassroomAdminResponse(classroom);
        return ResponseEntity.ok().body(response);
    }

}
