package com.example.EduCenter_BE.api.interfaces.admin;

import com.example.EduCenter_BE.constant.uri.UriAdmin;
import com.example.EduCenter_BE.request.admin.*;
import com.example.EduCenter_BE.response.admin.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

@RestController
@RequestMapping(value = UriAdmin.ADMIN)
public interface AdminApi {

    @RequestMapping(value = UriAdmin.CREATE_STUDENT, method = RequestMethod.POST)
    ResponseEntity<StudentAdminResponse> createStudent(@RequestBody CreateStudentAdminRequest request);

    @RequestMapping(value = UriAdmin.CREATE_ROLE, method = RequestMethod.POST)
    ResponseEntity<RoleAdminResponse> createRole(@RequestBody CreateRoleAdminRequest request);

    @RequestMapping(value = UriAdmin.CREATE_COURSE, method = RequestMethod.POST)
    ResponseEntity<CourseAdminResponse> createCourse(@RequestBody CreateCourseAdminRequest request);

    @RequestMapping(value = UriAdmin.CREATE_PARENT, method = RequestMethod.POST)
    ResponseEntity<ParentAdminResponse> createParent(@RequestBody CreateParentAdminRequest request);

    @RequestMapping(value = UriAdmin.CREATE_TEACHER, method = RequestMethod.POST)
    ResponseEntity<TeacherAdminResponse> createTeacher(@RequestBody CreateTeacherAdminRequest request);

    @RequestMapping(value = UriAdmin.CREATE_CLASSROOM, method = RequestMethod.POST)
    ResponseEntity<ClassroomAdminResponse> createClassroom(@RequestBody CreateClassroomAdminRequest request);

}
