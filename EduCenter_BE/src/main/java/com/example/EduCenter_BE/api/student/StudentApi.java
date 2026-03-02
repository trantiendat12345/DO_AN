package com.example.EduCenter_BE.api.student;

import com.example.EduCenter_BE.constant.uri.URI;
import com.example.EduCenter_BE.response.ScoreResponse;
import com.example.EduCenter_BE.response.StudentInfoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * API Interface cho Student Portal
 * Định nghĩa các endpoints cho học viên
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = URI.STUDENT)
@PreAuthorize("hasRole('STUDENT')")
public interface StudentApi {

    /**
     * Lấy thông tin chi tiết của học viên đang đăng nhập
     * 
     * @return StudentInfoResponse chứa thông tin học viên, lớp học
     */
    @RequestMapping(value = URI.GET_STUDENT_INFO, method = RequestMethod.GET)
    ResponseEntity<StudentInfoResponse> getStudentInfo();

    /**
     * Lấy danh sách điểm của học viên đang đăng nhập
     * 
     * @param pageable - thông tin phân trang (page, size, sort)
     * @return Page chứa danh sách điểm
     */
    @RequestMapping(value = URI.GET_MY_SCORES, method = RequestMethod.GET)
    ResponseEntity<Page<ScoreResponse>> getMyScores(Pageable pageable);

}
