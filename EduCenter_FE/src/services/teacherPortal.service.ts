import api from "../api/axios";
import type {
    TeacherInfo,
    TeacherClassroom,
    TeacherSchedule,
    StudentInClass,
    ScoreInClass,
} from "../types/Teacher";

// Lấy thông tin giáo viên đang đăng nhập
export function getTeacherInfo() {
    return api.get<TeacherInfo>("/teacher/info").then((res) => res.data);
}

// Lấy danh sách lớp học đang dạy (có phân trang)
export function getMyClassrooms(page = 0, size = 10) {
    return api
        .get("/teacher/my-classrooms", {
            params: { page, size },
        })
        .then((res) => res.data);
}

// Lấy lịch dạy
export function getMySchedule() {
    return api
        .get<TeacherSchedule[]>("/teacher/my-schedule")
        .then((res) => res.data);
}

// Lấy danh sách học viên trong lớp
export function getStudentsInClass(classroomName: string) {
    return api
        .get<StudentInClass[]>(`/teacher/students-in-class/${classroomName}`)
        .then((res) => res.data);
}

// Lấy danh sách điểm trong lớp (có phân trang)
export function getScoresInClass(classroomName: string, page = 0, size = 10) {
    return api
        .get(`/teacher/scores-in-class/${classroomName}`, {
            params: { page, size },
        })
        .then((res) => res.data);
}
