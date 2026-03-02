package com.example.EduCenter_BE.service.impl;

import com.example.EduCenter_BE.constant.enums.UserType;
import com.example.EduCenter_BE.entity.Account;
import com.example.EduCenter_BE.entity.Student;
import com.example.EduCenter_BE.exception.BusinessException;
import com.example.EduCenter_BE.repository.AccountRepository;
import com.example.EduCenter_BE.repository.StudentClassroomRepository;
import com.example.EduCenter_BE.repository.StudentRepository;
import com.example.EduCenter_BE.response.ScoreResponse;
import com.example.EduCenter_BE.response.StudentInfoResponse;
import com.example.EduCenter_BE.service.interfaces.ScoreService;
import com.example.EduCenter_BE.service.interfaces.StudentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation của StudentPortalService
 * Chứa toàn bộ business logic cho Student Portal
 */
@Service
@Transactional
public class StudentPortalServiceImpl implements StudentPortalService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentClassroomRepository studentClassroomRepository;

    @Autowired
    private ScoreService scoreService;

    /**
     * Lấy thông tin chi tiết của học viên
     * Logic:
     * 1. Tìm account theo username
     * 2. Validate account type phải là STUDENT
     * 3. Lấy thông tin student từ userId
     * 4. Lấy danh sách lớp học của student
     * 5. Trả về StudentInfoResponse
     */
    @Override
    public StudentInfoResponse getStudentInfo(String username) {
        // Tìm account theo username
        Account account = accountRepository.findAccountByUsername(username);
        if (account == null) {
            throw new BusinessException("Không tìm thấy tài khoản");
        }

        // Validate loại tài khoản phải là STUDENT
        if (account.getType() != UserType.STUDENT) {
            throw new BusinessException("Tài khoản không phải là học viên");
        }

        // Lấy thông tin student
        Student student = studentRepository.findById(account.getUserId())
                .orElseThrow(() -> new BusinessException("Không tìm thấy thông tin học viên"));

        // Kiểm tra student có bị xóa không
        if (student.getIsDeleted()) {
            throw new BusinessException("Thông tin học viên không còn tồn tại");
        }

        // Lấy danh sách lớp học của student
        List<String> classrooms = studentClassroomRepository.findAll().stream()
                .filter(sc -> sc.getStudent().getId().equals(student.getId()) && !sc.getIsDeleted())
                .map(sc -> sc.getClassroom().getName())
                .collect(Collectors.toList());

        // Tạo và trả về response
        return new StudentInfoResponse(student, classrooms);
    }

    /**
     * Lấy danh sách điểm của học viên
     * Logic:
     * 1. Tìm account theo username
     * 2. Validate account type phải là STUDENT
     * 3. Lấy thông tin student
     * 4. Lấy danh sách điểm theo studentCode
     * 5. Phân trang và trả về
     */
    @Override
    public Page<ScoreResponse> getMyScores(String username, Pageable pageable) {
        // Tìm account theo username
        Account account = accountRepository.findAccountByUsername(username);
        if (account == null) {
            throw new BusinessException("Không tìm thấy tài khoản");
        }

        // Validate loại tài khoản phải là STUDENT
        if (account.getType() != UserType.STUDENT) {
            throw new BusinessException("Tài khoản không phải là học viên");
        }

        // Lấy thông tin student
        Student student = studentRepository.findById(account.getUserId())
                .orElseThrow(() -> new BusinessException("Không tìm thấy thông tin học viên"));

        // Kiểm tra student có bị xóa không
        if (student.getIsDeleted()) {
            throw new BusinessException("Thông tin học viên không còn tồn tại");
        }

        // Lấy danh sách điểm của student
        List<ScoreResponse> allScores = scoreService.getScoresByStudentCode(student.getStudentCode());

        // Phân trang thủ công
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allScores.size());

        List<ScoreResponse> pageContent;
        if (start > allScores.size()) {
            pageContent = List.of();
        } else {
            pageContent = allScores.subList(start, end);
        }

        // Tạo Page object với thông tin phân trang
        return new PageImpl<>(pageContent, pageable, allScores.size());
    }

}
