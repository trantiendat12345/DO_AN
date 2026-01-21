package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.enums.TeacherRole;
import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Classroom;
import com.example.EduCenter_BE.entity.Teacher;
import com.example.EduCenter_BE.entity.TeacherClassroom;
import com.example.EduCenter_BE.exception.BusinessException;
import com.example.EduCenter_BE.repository.ClassroomRepository;
import com.example.EduCenter_BE.repository.TeacherClassroomRepository;
import com.example.EduCenter_BE.repository.TeacherRepository;
import com.example.EduCenter_BE.request.teacher.AssignTeacherRequest;
import com.example.EduCenter_BE.response.AssignTeacherResponse;
import com.example.EduCenter_BE.response.TeacherResponse;
import com.example.EduCenter_BE.service.interfaces.TeacherClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TeacherClassroomServiceImpl implements TeacherClassroomService {

    @Autowired
    private TeacherClassroomRepository teacherClassroomRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public AssignTeacherResponse assignTeacher(AssignTeacherRequest request) {
        Teacher checkTeacherByCode = teacherRepository.findTeacherByCode(request.getTeacherCode());
        Classroom checkClassroomByName = classroomRepository.findClassroomByName(request.getName());

        if (Objects.isNull(checkTeacherByCode)) {
            throw new BusinessException(Message.TEACHER_DOES_NOT_EXIST);
        }

        if (Objects.isNull(checkClassroomByName)) {
            throw new BusinessException(Message.CLASSROOM_DOES_NOT_EXIST);
        }

        if (teacherClassroomRepository.existsByTeacherAndClassroom(checkTeacherByCode, checkClassroomByName)) {
            throw new BusinessException(Message.TEACHER_WHO_TAUGHT_THIS_CLASS);
        }

        if (request.getTeacherRole().equals(TeacherRole.MAIN) && teacherClassroomRepository.existsByClassroomAndTeacherRole(checkClassroomByName, TeacherRole.MAIN)) {
            throw new BusinessException(Message.THE_CLASS_ALREADY_HAS_A_MAIN_TEACHER);
        }

        TeacherClassroom teacherClassroom = new TeacherClassroom();
        teacherClassroom.setClassroom(checkClassroomByName);
        teacherClassroom.setTeacher(checkTeacherByCode);
        teacherClassroom.setTeacherRole(request.getTeacherRole());
        teacherClassroomRepository.save(teacherClassroom);

        return buildAssignTeacherResponse(checkClassroomByName);
    }

    private AssignTeacherResponse buildAssignTeacherResponse(Classroom classroom) {

        var list = teacherClassroomRepository.findByClassroom(classroom);

        AssignTeacherResponse response = new AssignTeacherResponse(classroom);

        for (TeacherClassroom tc : list) {

            Teacher teacher = tc.getTeacher();
            TeacherResponse teacherResponse = new TeacherResponse(teacher);

            if (tc.getTeacherRole() == TeacherRole.MAIN) {
                response.setMainTeacher(teacherResponse);
            } else {
                response.getAssistantTeacher().add(teacherResponse);
            }
        }

        return response;
    }



}
