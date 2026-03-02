package com.example.EduCenter_BE.api.student;

import com.example.EduCenter_BE.response.ScoreResponse;
import com.example.EduCenter_BE.response.StudentInfoResponse;
import com.example.EduCenter_BE.service.interfaces.StudentPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Implementation của StudentApi
 * Chỉ xử lý HTTP request/response, gọi service để xử lý logic
 */
@Component
public class StudentApiImpl implements StudentApi {

    @Autowired
    private StudentPortalService studentPortalService;

    /**
     * API lấy thông tin học viên
     * Lấy username từ SecurityContext và gọi service
     */
    @Override
    public ResponseEntity<StudentInfoResponse> getStudentInfo() {
        // Lấy username từ SecurityContext (người dùng đã đăng nhập)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Gọi service để xử lý logic
        StudentInfoResponse response = studentPortalService.getStudentInfo(username);

        // Trả về response
        return ResponseEntity.ok(response);
    }

    /**
     * API lấy danh sách điểm
     * Lấy username từ SecurityContext và gọi service
     */
    @Override
    public ResponseEntity<Page<ScoreResponse>> getMyScores(Pageable pageable) {
        // Lấy username từ SecurityContext (người dùng đã đăng nhập)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Gọi service để xử lý logic
        Page<ScoreResponse> scores = studentPortalService.getMyScores(username, pageable);

        // Trả về response
        return ResponseEntity.ok(scores);
    }

}
