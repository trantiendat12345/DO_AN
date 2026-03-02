package com.example.EduCenter_BE.api.teacher;

import com.example.EduCenter_BE.constant.uri.URI;
import com.example.EduCenter_BE.response.ScoreResponse;
import com.example.EduCenter_BE.response.StudentResponse;
import com.example.EduCenter_BE.response.TeacherClassroomResponse;
import com.example.EduCenter_BE.response.TeacherInfoResponse;
import com.example.EduCenter_BE.response.TeacherScheduleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * API Interface cho Teacher Portal
 * Định nghĩa các endpoints cho giáo viên
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = URI.TEACHER)
@PreAuthorize("hasRole('TEACHER')")
public interface TeacherApi {

    /**
     * Lấy thông tin chi tiết của giáo viên đang đăng nhập
     * 
     * @return TeacherInfoResponse chứa thông tin giáo viên
     */
    @RequestMapping(value = URI.GET_TEACHER_INFO, method = RequestMethod.GET)
    ResponseEntity<TeacherInfoResponse> getTeacherInfo();

    /**
     * Lấy danh sách lớp học mà giáo viên đang dạy
     * 
     * @param pageable - thông tin phân trang (page, size, sort)
     * @return Page chứa danh sách lớp học
     */
    @RequestMapping(value = URI.GET_MY_CLASSROOMS, method = RequestMethod.GET)
    ResponseEntity<Page<TeacherClassroomResponse>> getMyClassrooms(Pageable pageable);

    /**
     * Lấy lịch dạy của giáo viên
     * 
     * @return List chứa lịch dạy
     */
    @RequestMapping(value = URI.GET_MY_SCHEDULE, method = RequestMethod.GET)
    ResponseEntity<List<TeacherScheduleResponse>> getMySchedule();

    /**
     * Lấy danh sách học viên trong một lớp học cụ thể
     * 
     * @param name - tên lớp học
     * @return List chứa danh sách học viên
     */
    @RequestMapping(value = URI.GET_STUDENTS_IN_CLASS, method = RequestMethod.GET)
    ResponseEntity<List<StudentResponse>> getStudentsInClass(@PathVariable("name") String name);

    /**
     * Lấy danh sách điểm của học viên trong một lớp học cụ thể
     * 
     * @param name     - tên lớp học
     * @param pageable - thông tin phân trang (page, size, sort)
     * @return Page chứa danh sách điểm
     */
    @RequestMapping(value = URI.GET_SCORES_IN_CLASS, method = RequestMethod.GET)
    ResponseEntity<Page<ScoreResponse>> getScoresInClass(
            @PathVariable("name") String name,
            Pageable pageable);

}
