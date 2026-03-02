package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.enums.UserType;
import com.example.EduCenter_BE.entity.*;
import com.example.EduCenter_BE.exception.BusinessException;
import com.example.EduCenter_BE.repository.*;
import com.example.EduCenter_BE.response.*;
import com.example.EduCenter_BE.service.interfaces.TeacherPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation của TeacherPortalService
 * Chứa toàn bộ business logic cho Teacher Portal
 */
@Service
@Transactional
public class TeacherPortalServiceImpl implements TeacherPortalService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherClassroomRepository teacherClassroomRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private StudentClassroomRepository studentClassroomRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    /**
     * Lấy thông tin chi tiết của giáo viên
     * Logic:
     * 1. Tìm account theo username
     * 2. Validate account type phải là TEACHER
     * 3. Lấy thông tin teacher từ userId
     * 4. Tính toán tổng số lớp và học viên
     * 5. Trả về TeacherInfoResponse
     */
    @Override
    public TeacherInfoResponse getTeacherInfo(String username) {
        // Tìm account theo username
        Account account = accountRepository.findAccountByUsername(username);
        if (account == null) {
            throw new BusinessException("Không tìm thấy tài khoản");
        }

        // Validate loại tài khoản phải là TEACHER
        if (account.getType() != UserType.TEACHER) {
            throw new BusinessException("Tài khoản không phải là giáo viên");
        }

        // Lấy thông tin teacher
        Teacher teacher = teacherRepository.findById(account.getUserId())
                .orElseThrow(() -> new BusinessException("Không tìm thấy thông tin giáo viên"));

        // Kiểm tra teacher có bị xóa không
        if (teacher.getIsDeleted()) {
            throw new BusinessException("Thông tin giáo viên không còn tồn tại");
        }

        // Lấy danh sách lớp học của giáo viên
        List<TeacherClassroom> teacherClassrooms = teacherClassroomRepository.findAll().stream()
                .filter(tc -> tc.getTeacher().getId().equals(teacher.getId()) && !tc.getIsDeleted())
                .collect(Collectors.toList());

        Long totalClasses = (long) teacherClassrooms.size();

        // Tính tổng số học viên trong các lớp
        Long totalStudents = 0L;
        for (TeacherClassroom tc : teacherClassrooms) {
            Long count = studentClassroomRepository.findAll().stream()
                    .filter(sc -> sc.getClassroom().getId().equals(tc.getClassroom().getId()) && !sc.getIsDeleted())
                    .count();
            totalStudents += count;
        }

        // Tạo và trả về response
        return new TeacherInfoResponse(teacher, totalClasses, totalStudents);
    }

    /**
     * Lấy danh sách lớp học mà giáo viên đang dạy
     * Logic:
     * 1. Tìm account theo username
     * 2. Validate account type phải là TEACHER
     * 3. Lấy thông tin teacher
     * 4. Lấy danh sách lớp học từ TeacherClassroom
     * 5. Tính số học viên hiện tại trong mỗi lớp
     * 6. Phân trang và trả về
     */
    @Override
    public Page<TeacherClassroomResponse> getMyClassrooms(String username, Pageable pageable) {
        // Tìm account theo username
        Account account = accountRepository.findAccountByUsername(username);
        if (account == null) {
            throw new BusinessException("Không tìm thấy tài khoản");
        }

        // Validate loại tài khoản phải là TEACHER
        if (account.getType() != UserType.TEACHER) {
            throw new BusinessException("Tài khoản không phải là giáo viên");
        }

        // Lấy thông tin teacher
        Teacher teacher = teacherRepository.findById(account.getUserId())
                .orElseThrow(() -> new BusinessException("Không tìm thấy thông tin giáo viên"));

        // Kiểm tra teacher có bị xóa không
        if (teacher.getIsDeleted()) {
            throw new BusinessException("Thông tin giáo viên không còn tồn tại");
        }

        // Lấy danh sách lớp học của giáo viên
        List<TeacherClassroom> teacherClassrooms = teacherClassroomRepository.findAll().stream()
                .filter(tc -> tc.getTeacher().getId().equals(teacher.getId()) && !tc.getIsDeleted())
                .collect(Collectors.toList());

        // Tạo danh sách response
        List<TeacherClassroomResponse> responses = new ArrayList<>();
        for (TeacherClassroom tc : teacherClassrooms) {
            Classroom classroom = tc.getClassroom();

            // Đếm số học viên hiện tại trong lớp
            Integer currentStudent = (int) studentClassroomRepository.findAll().stream()
                    .filter(sc -> sc.getClassroom().getId().equals(classroom.getId()) && !sc.getIsDeleted())
                    .count();

            responses.add(new TeacherClassroomResponse(classroom, currentStudent, tc.getTeacherRole()));
        }

        // Phân trang thủ công
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), responses.size());

        List<TeacherClassroomResponse> pageContent;
        if (start > responses.size()) {
            pageContent = List.of();
        } else {
            pageContent = responses.subList(start, end);
        }

        // Tạo Page object với thông tin phân trang
        return new PageImpl<>(pageContent, pageable, responses.size());
    }

    /**
     * Lấy lịch dạy của giáo viên
     * Logic:
     * 1. Tìm account theo username
     * 2. Validate account type phải là TEACHER
     * 3. Lấy thông tin teacher
     * 4. Lấy danh sách lớp học của giáo viên
     * 5. Lấy lịch dạy từ các lớp học đó
     * 6. Trả về danh sách lịch dạy
     */
    @Override
    public List<TeacherScheduleResponse> getMySchedule(String username) {
        // Tìm account theo username
        Account account = accountRepository.findAccountByUsername(username);
        if (account == null) {
            throw new BusinessException("Không tìm thấy tài khoản");
        }

        // Validate loại tài khoản phải là TEACHER
        if (account.getType() != UserType.TEACHER) {
            throw new BusinessException("Tài khoản không phải là giáo viên");
        }

        // Lấy thông tin teacher
        Teacher teacher = teacherRepository.findById(account.getUserId())
                .orElseThrow(() -> new BusinessException("Không tìm thấy thông tin giáo viên"));

        // Kiểm tra teacher có bị xóa không
        if (teacher.getIsDeleted()) {
            throw new BusinessException("Thông tin giáo viên không còn tồn tại");
        }

        // Lấy danh sách lớp học của giáo viên
        List<TeacherClassroom> teacherClassrooms = teacherClassroomRepository.findAll().stream()
                .filter(tc -> tc.getTeacher().getId().equals(teacher.getId()) && !tc.getIsDeleted())
                .collect(Collectors.toList());

        // Lấy danh sách ID của các lớp học
        List<Long> classroomIds = teacherClassrooms.stream()
                .map(tc -> tc.getClassroom().getId())
                .collect(Collectors.toList());

        // Lấy lịch dạy từ các lớp học
        List<Schedule> schedules = scheduleRepository.findAll().stream()
                .filter(s -> classroomIds.contains(s.getClassroom().getId()) && !s.getIsDeleted())
                .collect(Collectors.toList());

        // Chuyển đổi sang response
        return schedules.stream()
                .map(TeacherScheduleResponse::new)
                .collect(Collectors.toList());
    }

    /**
     * Lấy danh sách học viên trong một lớp học cụ thể
     * Logic:
     * 1. Tìm account theo username
     * 2. Validate account type phải là TEACHER
     * 3. Lấy thông tin teacher
     * 4. Kiểm tra giáo viên có dạy lớp này không
     * 5. Lấy danh sách học viên trong lớp
     * 6. Trả về danh sách học viên
     */
    @Override
    public List<StudentResponse> getStudentsInClass(String username, String classroomName) {
        // Tìm account theo username
        Account account = accountRepository.findAccountByUsername(username);
        if (account == null) {
            throw new BusinessException("Không tìm thấy tài khoản");
        }

        // Validate loại tài khoản phải là TEACHER
        if (account.getType() != UserType.TEACHER) {
            throw new BusinessException("Tài khoản không phải là giáo viên");
        }

        // Lấy thông tin teacher
        Teacher teacher = teacherRepository.findById(account.getUserId())
                .orElseThrow(() -> new BusinessException("Không tìm thấy thông tin giáo viên"));

        // Kiểm tra teacher có bị xóa không
        if (teacher.getIsDeleted()) {
            throw new BusinessException("Thông tin giáo viên không còn tồn tại");
        }

        // Tìm classroom theo tên
        Classroom classroom = classroomRepository.findClassroomByName(classroomName);
        if (classroom == null || classroom.getIsDeleted()) {
            throw new BusinessException("Không tìm thấy lớp học hoặc lớp học đã bị xóa");
        }

        // Kiểm tra giáo viên có dạy lớp này không
        boolean isTeaching = teacherClassroomRepository.findAll().stream()
                .anyMatch(tc -> tc.getTeacher().getId().equals(teacher.getId())
                        && tc.getClassroom().getId().equals(classroom.getId())
                        && !tc.getIsDeleted());

        if (!isTeaching) {
            throw new BusinessException("Bạn không dạy lớp học này");
        }

        // Lấy danh sách học viên trong lớp
        List<StudentClassroom> studentClassrooms = studentClassroomRepository.findAll().stream()
                .filter(sc -> sc.getClassroom().getId().equals(classroom.getId()) && !sc.getIsDeleted())
                .collect(Collectors.toList());

        // Chuyển đổi sang response
        return studentClassrooms.stream()
                .map(sc -> new StudentResponse(sc.getStudent(), classroom))
                .collect(Collectors.toList());
    }

    /**
     * Lấy danh sách điểm của học viên trong một lớp học cụ thể
     * Logic:
     * 1. Tìm account theo username
     * 2. Validate account type phải là TEACHER
     * 3. Lấy thông tin teacher
     * 4. Kiểm tra giáo viên có dạy lớp này không
     * 5. Lấy danh sách điểm trong lớp
     * 6. Phân trang và trả về
     */
    @Override
    public Page<ScoreResponse> getScoresInClass(String username, String classroomName, Pageable pageable) {
        // Tìm account theo username
        Account account = accountRepository.findAccountByUsername(username);
        if (account == null) {
            throw new BusinessException("Không tìm thấy tài khoản");
        }

        // Validate loại tài khoản phải là TEACHER
        if (account.getType() != UserType.TEACHER) {
            throw new BusinessException("Tài khoản không phải là giáo viên");
        }

        // Lấy thông tin teacher
        Teacher teacher = teacherRepository.findById(account.getUserId())
                .orElseThrow(() -> new BusinessException("Không tìm thấy thông tin giáo viên"));

        // Kiểm tra teacher có bị xóa không
        if (teacher.getIsDeleted()) {
            throw new BusinessException("Thông tin giáo viên không còn tồn tại");
        }

        // Tìm classroom theo tên
        Classroom classroom = classroomRepository.findClassroomByName(classroomName);
        if (classroom == null || classroom.getIsDeleted()) {
            throw new BusinessException("Không tìm thấy lớp học hoặc lớp học đã bị xóa");
        }

        // Kiểm tra giáo viên có dạy lớp này không
        boolean isTeaching = teacherClassroomRepository.findAll().stream()
                .anyMatch(tc -> tc.getTeacher().getId().equals(teacher.getId())
                        && tc.getClassroom().getId().equals(classroom.getId())
                        && !tc.getIsDeleted());

        if (!isTeaching) {
            throw new BusinessException("Bạn không dạy lớp học này");
        }

        // Lấy danh sách điểm trong lớp
        List<Score> scores = scoreRepository.findScoresByClassroomName(classroomName);

        // Chuyển đổi sang response
        List<ScoreResponse> scoreResponses = scores.stream()
                .map(ScoreResponse::new)
                .collect(Collectors.toList());

        // Phân trang thủ công
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), scoreResponses.size());

        List<ScoreResponse> pageContent;
        if (start > scoreResponses.size()) {
            pageContent = List.of();
        } else {
            pageContent = scoreResponses.subList(start, end);
        }

        // Tạo Page object với thông tin phân trang
        return new PageImpl<>(pageContent, pageable, scoreResponses.size());
    }

}
