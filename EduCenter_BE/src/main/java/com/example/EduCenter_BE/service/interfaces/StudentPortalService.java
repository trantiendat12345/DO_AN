package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.response.ScoreResponse;
import com.example.EduCenter_BE.response.StudentInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service cho Student Portal
 * Xử lý logic nghiệp vụ cho học viên
 */
public interface StudentPortalService {

    /**
     * Lấy thông tin chi tiết của học viên đang đăng nhập
     * 
     * @param username - username từ SecurityContext
     * @return StudentInfoResponse chứa thông tin học viên
     */
    StudentInfoResponse getStudentInfo(String username);

    /**
     * Lấy danh sách điểm của học viên đang đăng nhập
     * 
     * @param username - username từ SecurityContext
     * @param pageable - thông tin phân trang
     * @return Page chứa danh sách điểm
     */
    Page<ScoreResponse> getMyScores(String username, Pageable pageable);

}
