package com.example.EduCenter_BE.api.impl.admin;

import com.example.EduCenter_BE.api.interfaces.admin.AdminApi;
import com.example.EduCenter_BE.entity.*;
import com.example.EduCenter_BE.request.*;
import com.example.EduCenter_BE.request.AddStudentToClassRequest;
import com.example.EduCenter_BE.response.*;
import com.example.EduCenter_BE.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AdminApiImpl implements AdminApi {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ParentService parentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private StudentClassroomService studentClassroomService;

    @Override
    public ResponseEntity<StudentResponse> createStudent (CreateStudentRequest request) {
        Student student = studentService.createStudent(request);
        StudentResponse response = new StudentResponse(student);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<RoleResponse> createRole(CreateRoleRequest request) {
        Role role = roleService.createRole(request);
        RoleResponse response = new RoleResponse(role);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<CourseResponse> createCourse(CreateCourseRequest request) {
        Course course = courseService.createCourse(request);
        CourseResponse response = new CourseResponse(course);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ParentResponse> createParent(CreateParentRequest request) {
        Parent parent = parentService.createParent(request);
        ParentResponse response = new ParentResponse(parent);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<TeacherResponse> createTeacher(CreateTeacherRequest request) {
        Teacher teacher = teacherService.createTeacher(request);
        TeacherResponse response = new TeacherResponse(teacher);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ClassroomResponse> createClassroom(CreateClassroomRequest request) {
        Classroom classroom = classroomService.createClassroom(request);
        ClassroomResponse response = new ClassroomResponse(classroom);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<StudentResponse> addStudentToClass(AddStudentToClassRequest request) {
        StudentResponse response = studentClassroomService.addStudentToClassroom(request);
        return ResponseEntity.ok().body(response);
    }

//    @Override
//    public ResponseEntity<Page<StudentResponse>> getAllStudents(Pageable pageable) {
//        Page<StudentResponse> response = adminService.getAllStudent();
//        return ResponseEntity.ok().body(response);
//    }


}
