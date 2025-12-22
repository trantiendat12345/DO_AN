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

    @Override
    public ResponseEntity<StudentResponse> updateStudent(UpdateStudentRequest request, String studentCode) {
        StudentResponse response = studentService.updateStudent(request, studentCode);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<TeacherResponse> updateTeacher(UpdateTeacherRequest request, String teacherCode) {
        TeacherResponse response = teacherService.updateTeacher(request, teacherCode);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<ClassroomResponse> updateClassroom(UpdateClassroomRequest request, String name) {
        ClassroomResponse response = classroomService.updateClassroom(request, name);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Page<StudentResponse>> getAllStudents(Pageable pageable) {
        Page<StudentResponse> response = studentService.getAllStudents(pageable);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<StudentResponse> getStudent(String studentCode) {
        StudentResponse response = studentService.getStudentByCode(studentCode);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Page<TeacherResponse>> getAllTeachers(Pageable pageable) {
        Page<TeacherResponse> response = teacherService.getAllTeachers(pageable);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<TeacherResponse> getTeacher(String teacherCode) {
        TeacherResponse response = teacherService.getTeacherByCode(teacherCode);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Long> totalStudent() {
        Long response = studentService.totalStudentCount();
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Long> totalTeacher() {
        Long response = teacherService.totalTeacherCount();
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Long> totalClass() {
        Long response = classroomService.totalClassroomCount();
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Long> totalParent() {
        Long response = parentService.totalParentCount();
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<Page<ClassroomResponse>> getAllClassrooms(Pageable pageable) {
        Page<ClassroomResponse> responses = classroomService.getAllClassrooms(pageable);
        return ResponseEntity.ok().body(responses);
    }

    @Override
    public ResponseEntity<ClassroomResponse> getClassroom(String name) {
        ClassroomResponse response = classroomService.getClassroom(name);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<String> deleteStudent(String studentCode) {
        String response = studentService.deleteStudent(studentCode);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<String> deleteTeacher(String teacherCode) {
        String response = teacherService.deleteTeacher(teacherCode);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public ResponseEntity<String> deleteClassroom(String name) {
        String response = classroomService.deleteClassroom(name);
        return ResponseEntity.ok().body(response);
    }


}
