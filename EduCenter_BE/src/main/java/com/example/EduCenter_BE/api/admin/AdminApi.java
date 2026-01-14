package com.example.EduCenter_BE.api.admin;

import com.example.EduCenter_BE.constant.enums.UserType;
import com.example.EduCenter_BE.constant.uri.URI;
import com.example.EduCenter_BE.request.classroom.CreateClassroomRequest;
import com.example.EduCenter_BE.request.classroom.UpdateClassroomRequest;
import com.example.EduCenter_BE.request.course.CreateCourseRequest;
import com.example.EduCenter_BE.request.parent.CreateParentRequest;
import com.example.EduCenter_BE.request.role.CreateRoleRequest;
import com.example.EduCenter_BE.request.student.AddStudentToClassRequest;
import com.example.EduCenter_BE.request.account.CreateAccountRequest;
import com.example.EduCenter_BE.request.account.UpdateAccountRequest;
import com.example.EduCenter_BE.request.student.CreateStudentRequest;
import com.example.EduCenter_BE.request.student.UpdateStudentRequest;
import com.example.EduCenter_BE.request.teacher.CreateTeacherRequest;
import com.example.EduCenter_BE.request.teacher.UpdateTeacherRequest;
import com.example.EduCenter_BE.response.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = URI.ADMIN)
@PreAuthorize("hasRole('ADMIN')")
public interface AdminApi {

    // CREATE
    @RequestMapping(value = URI.CREATE_STUDENT, method = RequestMethod.POST)
    ResponseEntity<StudentResponse> createStudent(@RequestBody CreateStudentRequest request);

    @RequestMapping(value = URI.CREATE_ROLE, method = RequestMethod.POST)
    ResponseEntity<RoleResponse> createRole(@RequestBody CreateRoleRequest request);

    @RequestMapping(value = URI.CREATE_COURSE, method = RequestMethod.POST)
    ResponseEntity<CourseResponse> createCourse(@RequestBody CreateCourseRequest request);

    @RequestMapping(value = URI.CREATE_PARENT, method = RequestMethod.POST)
    ResponseEntity<ParentResponse> createParent(@RequestBody CreateParentRequest request);

    @RequestMapping(value = URI.CREATE_TEACHER, method = RequestMethod.POST)
    ResponseEntity<TeacherResponse> createTeacher(@RequestBody CreateTeacherRequest request);

    @RequestMapping(value = URI.CREATE_CLASSROOM, method = RequestMethod.POST)
    ResponseEntity<ClassroomResponse> createClassroom(@RequestBody CreateClassroomRequest request);

    @RequestMapping(value = URI.CREATE_ACCOUNT, method = RequestMethod.POST)
    ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest request);

    // ADD
    @RequestMapping(value = URI.ADD_STUDENT_TO_CLASSROOM, method = RequestMethod.POST)
    ResponseEntity<StudentResponse> addStudentToClass(@RequestBody AddStudentToClassRequest request);

    // UPDATE
    @RequestMapping(value = URI.UPDATE_STUDENT, method = RequestMethod.PUT)
    ResponseEntity<StudentResponse> updateStudent(@RequestBody UpdateStudentRequest request, @PathVariable("code") String studentCode);

    @RequestMapping(value = URI.UPDATE_TEACHER, method = RequestMethod.PUT)
    ResponseEntity<TeacherResponse> updateTeacher(@RequestBody UpdateTeacherRequest request, @PathVariable("code") String teacherCode);

    @RequestMapping(value = URI.UPDATE_CLASSROOM, method = RequestMethod.PUT)
    ResponseEntity<ClassroomResponse> updateClassroom(@RequestBody UpdateClassroomRequest request, @PathVariable("name") String name);

    @RequestMapping(value = URI.UPDATE_ACCOUNT, method = RequestMethod.PUT)
    ResponseEntity<AccountResponse> updateAccount(@PathVariable("username") String username, @RequestBody UpdateAccountRequest request);

    // GET
    @RequestMapping(value = URI.GET_ALL_STUDENTS, method = RequestMethod.GET)
    ResponseEntity<Page<StudentResponse>> getAllStudents(Pageable pageable);

    @RequestMapping(value = URI.GET_STUDENT_BY_CODE, method = RequestMethod.GET)
    ResponseEntity<StudentResponse> getStudent(@PathVariable("code") String studentCode);

    @RequestMapping(value = URI.GET_ALL_TEACHERS, method = RequestMethod.GET)
    ResponseEntity<Page<TeacherResponse>> getAllTeachers(Pageable pageable);

    @RequestMapping(value = URI.GET_TEACHER_BY_CODE, method = RequestMethod.GET)
    ResponseEntity<TeacherResponse> getTeacher(@PathVariable("code") String teacherCode);

    @RequestMapping(value = URI.TOTAL_STUDENT, method = RequestMethod.GET)
    ResponseEntity<Long> totalStudent();

    @RequestMapping(value = URI.TOTAL_TEACHER, method = RequestMethod.GET)
    ResponseEntity<Long> totalTeacher();

    @RequestMapping(value = URI.TOTAL_CLASSROOM, method = RequestMethod.GET)
    ResponseEntity<Long> totalClass();

    @RequestMapping(value = URI.TOTAL_PARENT, method = RequestMethod.GET)
    ResponseEntity<Long> totalParent();

    @RequestMapping(value = URI.GET_ALL_CLASSROOMS, method = RequestMethod.GET)
    ResponseEntity<Page<ClassroomResponse>> getAllClassrooms(Pageable pageable);

    @RequestMapping(value = URI.GET_CLASSROOM_BY_NAME, method = RequestMethod.GET)
    ResponseEntity<ClassroomResponse> getClassroom(@PathVariable("name") String name);

    @RequestMapping(value = URI.GET_ALL_ACCOUNTS, method = RequestMethod.GET)
    ResponseEntity<Page<AccountResponse>> getAllAccounts(Pageable pageable);

    @RequestMapping(value = URI.GET_ACCOUNT_BY_CODE, method = RequestMethod.GET)
    ResponseEntity<AccountResponse> getAccountByCode(@RequestParam UserType type, @RequestParam String userCode);

    @RequestMapping(value = URI.GET_ACCOUNT_BY_USERNAME, method = RequestMethod.GET)
    ResponseEntity<AccountResponse> getAccountByUsername(@PathVariable("username") String username);

    // DELETE
    @RequestMapping(value = URI.DELETE_STUDENT, method = RequestMethod.DELETE)
    ResponseEntity<String> deleteStudent(@PathVariable("code") String studentCode);

    @RequestMapping(value = URI.DELETE_TEACHER, method = RequestMethod.DELETE)
    ResponseEntity<String> deleteTeacher(@PathVariable("code") String teacherCode);

    @RequestMapping(value = URI.DELETE_CLASSROOM, method = RequestMethod.DELETE)
    ResponseEntity<String> deleteClassroom(@PathVariable("name") String name);

    @RequestMapping(value = URI.DELETE_ACCOUNT, method = RequestMethod.DELETE)
    ResponseEntity<String> deleteAccount(@PathVariable("username") String username);

}
