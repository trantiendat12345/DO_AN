package com.example.EduCenter_BE.api.teacher;

import com.example.EduCenter_BE.response.ScoreResponse;
import com.example.EduCenter_BE.response.StudentResponse;
import com.example.EduCenter_BE.response.TeacherClassroomResponse;
import com.example.EduCenter_BE.response.TeacherInfoResponse;
import com.example.EduCenter_BE.response.TeacherScheduleResponse;
import com.example.EduCenter_BE.service.interfaces.TeacherPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation của TeacherApi
 * Chỉ xử lý HTTP request/response, gọi service để xử lý logic
 */
@Component
public class TeacherApiImpl implements TeacherApi {

    @Autowired
    private TeacherPortalService teacherPortalService;

    /**
     * API lấy thông tin giáo viên
     * Lấy username từ SecurityContext và gọi service
     */
    @Override
    public ResponseEntity<TeacherInfoResponse> getTeacherInfo() {
        // Lấy username từ SecurityContext (người dùng đã đăng nhập)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Gọi service để xử lý logic
        TeacherInfoResponse response = teacherPortalService.getTeacherInfo(username);

        // Trả về response
        return ResponseEntity.ok(response);
    }

    /**
     * API lấy danh sách lớp học đang dạy
     * Lấy username từ SecurityContext và gọi service
     */
    @Override
    public ResponseEntity<Page<TeacherClassroomResponse>> getMyClassrooms(Pageable pageable) {
        // Lấy username từ SecurityContext (người dùng đã đăng nhập)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Gọi service để xử lý logic
        Page<TeacherClassroomResponse> classrooms = teacherPortalService.getMyClassrooms(username, pageable);

        // Trả về response
        return ResponseEntity.ok(classrooms);
    }

    /**
     * API lấy lịch dạy
     * Lấy username từ SecurityContext và gọi service
     */
    @Override
    public ResponseEntity<List<TeacherScheduleResponse>> getMySchedule() {
        // Lấy username từ SecurityContext (người dùng đã đăng nhập)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Gọi service để xử lý logic
        List<TeacherScheduleResponse> schedule = teacherPortalService.getMySchedule(username);

        // Trả về response
        return ResponseEntity.ok(schedule);
    }

    /**
     * API lấy danh sách học viên trong lớp
     * Lấy username từ SecurityContext và gọi service
     */
    @Override
    public ResponseEntity<List<StudentResponse>> getStudentsInClass(String name) {
        // Lấy username từ SecurityContext (người dùng đã đăng nhập)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Gọi service để xử lý logic
        List<StudentResponse> students = teacherPortalService.getStudentsInClass(username, name);

        // Trả về response
        return ResponseEntity.ok(students);
    }

    /**
     * API lấy danh sách điểm trong lớp
     * Lấy username từ SecurityContext và gọi service
     */
    @Override
    public ResponseEntity<Page<ScoreResponse>> getScoresInClass(String name, Pageable pageable) {
        // Lấy username từ SecurityContext (người dùng đã đăng nhập)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Gọi service để xử lý logic
        Page<ScoreResponse> scores = teacherPortalService.getScoresInClass(username, name, pageable);

        // Trả về response
        return ResponseEntity.ok(scores);
    }

}
