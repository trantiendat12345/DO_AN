package com.example.EduCenter_BE.api.interfaces.admin;

import com.example.EduCenter_BE.constant.uri.UriAdmin;
import com.example.EduCenter_BE.request.*;
import com.example.EduCenter_BE.request.AddStudentToClassRequest;
import com.example.EduCenter_BE.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = UriAdmin.ADMIN)
public interface AdminApi {

    // CREATE
    @RequestMapping(value = UriAdmin.CREATE_STUDENT, method = RequestMethod.POST)
    ResponseEntity<StudentResponse> createStudent(@RequestBody CreateStudentRequest request);

    @RequestMapping(value = UriAdmin.CREATE_ROLE, method = RequestMethod.POST)
    ResponseEntity<RoleResponse> createRole(@RequestBody CreateRoleRequest request);

    @RequestMapping(value = UriAdmin.CREATE_COURSE, method = RequestMethod.POST)
    ResponseEntity<CourseResponse> createCourse(@RequestBody CreateCourseRequest request);

    @RequestMapping(value = UriAdmin.CREATE_PARENT, method = RequestMethod.POST)
    ResponseEntity<ParentResponse> createParent(@RequestBody CreateParentRequest request);

    @RequestMapping(value = UriAdmin.CREATE_TEACHER, method = RequestMethod.POST)
    ResponseEntity<TeacherResponse> createTeacher(@RequestBody CreateTeacherRequest request);

    @RequestMapping(value = UriAdmin.CREATE_CLASSROOM, method = RequestMethod.POST)
    ResponseEntity<ClassroomResponse> createClassroom(@RequestBody CreateClassroomRequest request);

    // ADD
    @RequestMapping(value = UriAdmin.ADD_STUDENT_TO_CLASSROOM, method = RequestMethod.POST)
    ResponseEntity<StudentResponse> addStudentToClass(@RequestBody AddStudentToClassRequest request);

    // UPDATE

    // GET
//    @RequestMapping(value = UriAdmin.GET_ALL_STUDENTS, method = RequestMethod.GET)
//    ResponseEntity<Page<StudentResponse>> getAllStudents(Pageable pageable);

}
