package com.example.EduCenter_BE.service.interfaces;

import com.example.EduCenter_BE.response.ScoreResponse;
import com.example.EduCenter_BE.response.StudentResponse;
import com.example.EduCenter_BE.response.TeacherClassroomResponse;
import com.example.EduCenter_BE.response.TeacherInfoResponse;
import com.example.EduCenter_BE.response.TeacherScheduleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service cho Teacher Portal
 * Xử lý logic nghiệp vụ cho giáo viên
 */
public interface TeacherPortalService {

    /**
     * Lấy thông tin chi tiết của giáo viên đang đăng nhập
     * 
     * @param username - username từ SecurityContext
     * @return TeacherInfoResponse chứa thông tin giáo viên
     */
    TeacherInfoResponse getTeacherInfo(String username);

    /**
     * Lấy danh sách lớp học mà giáo viên đang dạy
     * 
     * @param username - username từ SecurityContext
     * @param pageable - thông tin phân trang
     * @return Page chứa danh sách lớp học
     */
    Page<TeacherClassroomResponse> getMyClassrooms(String username, Pageable pageable);

    /**
     * Lấy lịch dạy của giáo viên
     * 
     * @param username - username từ SecurityContext
     * @return List chứa lịch dạy
     */
    List<TeacherScheduleResponse> getMySchedule(String username);

    /**
     * Lấy danh sách học viên trong một lớp học cụ thể
     * 
     * @param username      - username từ SecurityContext
     * @param classroomName - tên lớp học
     * @return List chứa danh sách học viên
     */
    List<StudentResponse> getStudentsInClass(String username, String classroomName);

    /**
     * Lấy danh sách điểm của học viên trong một lớp học cụ thể
     * 
     * @param username      - username từ SecurityContext
     * @param classroomName - tên lớp học
     * @param pageable      - thông tin phân trang
     * @return Page chứa danh sách điểm
     */
    Page<ScoreResponse> getScoresInClass(String username, String classroomName, Pageable pageable);

}
