import api from "../api/axios";
import type { Course } from "../types/Course";

// GET PAGED
export function getCourses(page = 0, size = 10) {
    return api
        .get("/admin/get-all-courses", {
            params: { page, size },
        })
        .then((res) => res.data);
}

// CREATE
export function createCourse(data: Partial<Course>) {
    return api.post("/admin/create-course", data);
}

// UPDATE
export function updateCourse(nameCourse: string, data: Partial<Course>) {
    return api.put(`/admin/update-course/${nameCourse}`, data);
}

// DELETE
export function deleteCourse(nameCourse: string) {
    return api.delete(`/admin/delete-course/${nameCourse}`);
}
