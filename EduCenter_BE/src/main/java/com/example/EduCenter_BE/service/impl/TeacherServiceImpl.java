package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Teacher;
import com.example.EduCenter_BE.repository.TeacherRepository;
import com.example.EduCenter_BE.request.CreateTeacherRequest;
import com.example.EduCenter_BE.service.interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public Teacher createTeacher(CreateTeacherRequest request) {
        String teacherCode = request.getTeacherCode();
        String teacherEmail = request.getEmail();
        String teacherPhone = request.getPhone();

        Teacher checkTeacherByCode = teacherRepository.findTeacherByCode(teacherCode);
        Teacher checkTeacherByEmail = teacherRepository.findTeacherByEmail(teacherEmail);
        Teacher checkTeacherByPhone = teacherRepository.findTeacherByPhone(teacherPhone);

        if (!Objects.isNull(checkTeacherByCode) || !Objects.isNull(checkTeacherByEmail) || !Objects.isNull(checkTeacherByPhone)) {
            throw new RuntimeException(Message.TEACHER_EXISTED);
        }

        Teacher teacher = new Teacher();
        teacher.setEmail(request.getEmail());
        teacher.setFullName(request.getFullName());
        teacher.setPhone(request.getPhone());
        teacher.setStatus(request.getStatus());
        teacher.setTeacherCode(request.getTeacherCode());
        return teacherRepository.save(teacher);
    }
}
