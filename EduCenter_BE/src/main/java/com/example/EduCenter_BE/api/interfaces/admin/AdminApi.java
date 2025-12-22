package com.example.EduCenter_BE.api.interfaces.admin;

import com.example.EduCenter_BE.constant.uri.UriAdmin;
import com.example.EduCenter_BE.entity.Teacher;
import com.example.EduCenter_BE.request.*;
import com.example.EduCenter_BE.request.AddStudentToClassRequest;
import com.example.EduCenter_BE.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
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
    @RequestMapping(value = UriAdmin.UPDATE_STUDENT, method = RequestMethod.PUT)
    ResponseEntity<StudentResponse> updateStudent(@RequestBody UpdateStudentRequest request, @PathVariable("code") String studentCode);

    @RequestMapping(value = UriAdmin.UPDATE_TEACHER, method = RequestMethod.PUT)
    ResponseEntity<TeacherResponse> updateTeacher(@RequestBody UpdateTeacherRequest request, @PathVariable("code") String teacherCode);

    @RequestMapping(value = UriAdmin.UPDATE_CLASSROOM, method = RequestMethod.PUT)
    ResponseEntity<ClassroomResponse> updateClassroom(@RequestBody UpdateClassroomRequest request, @PathVariable("name") String name);

    // GET
    @RequestMapping(value = UriAdmin.GET_ALL_STUDENTS, method = RequestMethod.GET)
    ResponseEntity<Page<StudentResponse>> getAllStudents(Pageable pageable);

    @RequestMapping(value = UriAdmin.GET_STUDENT_BY_CODE, method = RequestMethod.GET)
    ResponseEntity<StudentResponse> getStudent(@PathVariable("code") String studentCode);

    @RequestMapping(value = UriAdmin.GET_ALL_TEACHERS, method = RequestMethod.GET)
    ResponseEntity<Page<TeacherResponse>> getAllTeachers(Pageable pageable);

    @RequestMapping(value = UriAdmin.GET_TEACHER_BY_CODE, method = RequestMethod.GET)
    ResponseEntity<TeacherResponse> getTeacher(@PathVariable("code") String teacherCode);

    @RequestMapping(value = UriAdmin.TOTAL_STUDENT, method = RequestMethod.GET)
    ResponseEntity<Long> totalStudent();

    @RequestMapping(value = UriAdmin.TOTAL_TEACHER, method = RequestMethod.GET)
    ResponseEntity<Long> totalTeacher();

    @RequestMapping(value = UriAdmin.TOTAL_CLASSROOM, method = RequestMethod.GET)
    ResponseEntity<Long> totalClass();

    @RequestMapping(value = UriAdmin.TOTAL_PARENT, method = RequestMethod.GET)
    ResponseEntity<Long> totalParent();

    @RequestMapping(value = UriAdmin.GET_ALL_CLASSROOMS, method = RequestMethod.GET)
    ResponseEntity<Page<ClassroomResponse>> getAllClassrooms(Pageable pageable);

    @RequestMapping(value = UriAdmin.GET_CLASSROOM_BY_NAME, method = RequestMethod.GET)
    ResponseEntity<ClassroomResponse> getClassroom(@PathVariable("name") String name);

    // DELETE
    @RequestMapping(value = UriAdmin.DELETE_STUDENT, method = RequestMethod.DELETE)
    ResponseEntity<String> deleteStudent(@PathVariable("code") String studentCode);

    @RequestMapping(value = UriAdmin.DELETE_TEACHER, method = RequestMethod.DELETE)
    ResponseEntity<String> deleteTeacher(@PathVariable("code") String teacherCode);

    @RequestMapping(value = UriAdmin.DELETE_CLASSROOM, method = RequestMethod.DELETE)
    ResponseEntity<String> deleteClassroom(@PathVariable("name") String name);

}
