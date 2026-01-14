package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.message.Message;
import com.example.EduCenter_BE.entity.Teacher;
import com.example.EduCenter_BE.repository.TeacherRepository;
import com.example.EduCenter_BE.request.teacher.CreateTeacherRequest;
import com.example.EduCenter_BE.request.teacher.UpdateTeacherRequest;
import com.example.EduCenter_BE.response.TeacherResponse;
import com.example.EduCenter_BE.service.interfaces.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public Page<TeacherResponse> getAllTeachers(Pageable pageable) {
        return teacherRepository.findAllTeachers(pageable).map(TeacherResponse::new);
    }

    @Override
    public TeacherResponse getTeacherByCode(String code) {
        Teacher teacher = teacherRepository.findTeacherByCode(code);

        if (Objects.isNull(teacher)) {
            throw new RuntimeException(Message.TEACHER_DOES_NOT_EXIST);
        }

        return new TeacherResponse(teacher);
    }

    @Override
    public TeacherResponse updateTeacher(UpdateTeacherRequest request, String teacherCode) {
        Teacher teacher = teacherRepository.findTeacherByCode(teacherCode);
        Teacher email = teacherRepository.findTeacherByEmail(request.getEmail());
        Teacher phone = teacherRepository.findTeacherByPhone(request.getPhone());

        if (Objects.isNull(teacher)) {
            throw new RuntimeException(Message.TEACHER_DOES_NOT_EXIST);
        }

        if (email != null && !email.getId().equals(teacher.getId())) {
            throw new RuntimeException(Message.EMAIL_EXISTED);
        }

        if (phone != null && !phone.getId().equals(teacher.getId())) {
            throw new RuntimeException(Message.PHONE_EXISTED);
        }

        teacher.setFullName(request.getFullName());
        teacher.setPhone(request.getPhone());
        teacher.setStatus(request.getStatus());
        teacher.setEmail(request.getEmail());

        teacher.setUpdatedBy(1L);

        Teacher updateTeacher = teacherRepository.save(teacher);

        return new TeacherResponse(updateTeacher);
    }

    @Override
    public String deleteTeacher(String teacherCode) {
        Teacher teacher = teacherRepository.findTeacherByCode(teacherCode);

        if (Objects.isNull(teacher)) {
            throw new RuntimeException(Message.TEACHER_DOES_NOT_EXIST);
        }

        teacher.setIsDeleted(true);
        teacher.setDeletedAt(LocalDateTime.now());
        teacher.setDeletedBy(1L);
        teacherRepository.save(teacher);

        return Message.DELETED_SUCCESSFULLY;
    }

    @Override
    public Long totalTeacherCount() {
        return teacherRepository.countAllTeachers();
    }
}
