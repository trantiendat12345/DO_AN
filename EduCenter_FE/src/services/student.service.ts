import api from "../api/axios";
import type { Student } from "../types/Student";

// ============================================
// ADMIN APIs - Quản lý học sinh
// ============================================

export function getAllStudents(page = 0, size = 10) {
    return api
        .get("/admin/get-all-students", { params: { page, size } })
        .then((res) => res.data);
}

export function createStudent(data: Partial<Student>) {
    return api.post("/admin/create-student", data);
}

export function updateStudent(code: string, data: Partial<Student>) {
    return api.put(`/admin/update-student/${code}`, data);
}

export function deleteStudent(code: string) {
    return api.delete(`/admin/delete-student/${code}`);
}

export function importStudents(file: File) {
    const formData = new FormData();
    formData.append("file", file);
    return api.post("/admin/import-students", formData, {
        headers: {
            "Content-Type": "multipart/form-data",
        },
    });
}

// ============================================
// STUDENT PORTAL APIs - Cổng học viên
// ============================================

/**
 * Interface cho thông tin học viên chi tiết
 */
export interface StudentInfo {
    studentCode: string;
    fullName: string;
    dob: string; // LocalDate from backend
    gender: "MALE" | "FEMALE";
    phone: string;
    email: string;
    address: string;
    status: "ACTIVE" | "INACTIVE" | "SUSPENDED";
    level: string;
    note?: string;
    classrooms: string[]; // Danh sách tên lớp học
}

/**
 * Lấy thông tin chi tiết học viên đang đăng nhập
 * Logic xử lý nằm trong StudentPortalService
 */
export function getStudentInfo() {
    return api.get<StudentInfo>("/student/info").then((res) => res.data);
}

/**
 * Lấy danh sách điểm của học viên đang đăng nhập
 * Logic xử lý nằm trong StudentPortalService
 */
export function getMyScores(page = 0, size = 10) {
    return api
        .get("/student/my-scores", {
            params: { page, size },
        })
        .then((res) => res.data);
}
