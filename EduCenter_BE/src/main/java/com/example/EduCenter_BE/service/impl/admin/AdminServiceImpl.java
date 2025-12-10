package com.example.EduCenter_BE.service.impl.admin;

import com.example.EduCenter_BE.constant.message.MessageAdmin;
import com.example.EduCenter_BE.entity.*;
import com.example.EduCenter_BE.repository.*;
import com.example.EduCenter_BE.request.admin.*;
import com.example.EduCenter_BE.service.BaseService;
import com.example.EduCenter_BE.service.interfaces.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminServiceImpl extends BaseService implements AdminService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    public Student createStudent (CreateStudentAdminRequest request) {
        String studentCode = request.getStudentCode();
        Student checkStudent = studentRepository.getStudentByCode(studentCode);

        if (!Objects.isNull(checkStudent)) {
            throw new RuntimeException(MessageAdmin.STUDENT_EXITS);
        }

        Student student = new Student();
        student.setStudentCode(request.getStudentCode());
        student.setFullName(request.getFullName());
        student.setDateOfBirth(request.getDob());
        student.setGender(request.getGender());
        student.setPhone(request.getPhone());
        student.setAddress(request.getAddress());
        student.setEmail(request.getEmail());
        student.setStatus(request.getStatus());
        student.setLevel(request.getLevel());
        student.setNote(request.getNote());

        return studentRepository.save(student);
    }

    @Override
    public Role createRole(CreateRoleAdminRequest request) {
        Integer id = request.getId();
        Role checkRole = roleRepository.getById(id);

        if (!Objects.isNull(checkRole)) {
            throw new RuntimeException(MessageAdmin.ROLE_EXITS);
        }

        Role role = new Role();
        role.setId(request.getId());
        role.setName(request.getName());

        return roleRepository.save(role);
    }

    @Override
    public Course createCourse(CreateCourseAdminRequest request) {
        String name = request.getName();
        Course checkCourse = courseRepository.getCourseByName(name);

        if (!Objects.isNull(checkCourse)) {
            throw new RuntimeException(MessageAdmin.COURSE_EXITS);
        }

        Course course = new Course();
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setDuration(request.getDuration());
        course.setPrice(request.getPrice());
        return courseRepository.save(course);
    }

    @Override
    public Parent createParent(CreateParentAdminRequest request) {
        String email = request.getEmail();
        Parent checkParent = parentRepository.getParentByEmail(email);

        if (!Objects.isNull(checkParent)) {
            throw new RuntimeException(MessageAdmin.PARENT_EXITS);
        }

        Parent parent = new Parent();
        parent.setAddress(request.getAddress());
        parent.setPhone(request.getPhone());
        parent.setEmail(request.getEmail());
        parent.setFullName(request.getFullName());
        return parentRepository.save(parent);
    }

    @Override
    public Teacher createTeacher(CreateTeacherAdminRequest request) {
        String teacherCode = request.getTeacherCode();
        Teacher checkTeacher = teacherRepository.getTeacherByCode(teacherCode);

        if (!Objects.isNull(checkTeacher)) {
            throw new RuntimeException(MessageAdmin.TEACHER_EXITS);
        }

        Teacher teacher = new Teacher();
        teacher.setEmail(request.getEmail());
        teacher.setFullName(request.getFullName());
        teacher.setPhone(request.getPhone());
        teacher.setStatus(request.getStatus());
        teacher.setTeacherCode(request.getTeacherCode());
        return teacherRepository.save(teacher);
    }

    @Override
    public Classroom createClassroom(CreateClassroomAdminRequest request) {
        String name = request.getName();
        Classroom checkClassroom = classroomRepository.getClassroomByName(name);

        if (!Objects.isNull(checkClassroom)) {
            throw new RuntimeException(MessageAdmin.CLASSROOM_EXITS);
        }

        Course course = courseRepository.getCourseByName(request.getNameCourse());

        if (Objects.isNull(course)) {
            throw new RuntimeException(MessageAdmin.COURSE_EXIST);
        }

        Classroom classroom = new Classroom();
        classroom.setName(request.getName());
        classroom.setStatus(request.getStatus());
        classroom.setStartDate(request.getStartDate());
        classroom.setEndDate(request.getEndDate());
        classroom.setMaxStudent(request.getMaxStudent());
        classroom.setCourse(course);
        return classroomRepository.save(classroom);
    }

}
