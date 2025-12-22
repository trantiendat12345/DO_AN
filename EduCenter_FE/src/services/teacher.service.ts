import api from "../api/axios";
import type { Teacher } from "../types/Teacher";

// GET ALL (PAGINATION)
export function getAllTeachers(page = 0, size = 10) {
    return api
        .get("/admin/get-all-teachers", {
            params: { page, size },
        })
        .then((res) => res.data);
}

// CREATE
export function createTeacher(data: Partial<Teacher>) {
    return api.post("/admin/create-teacher", data);
}

// UPDATE
export function updateTeacher(code: string, data: Partial<Teacher>) {
    return api.put(`/admin/update-teacher/${code}`, data);
}

// DELETE
export function deleteTeacher(code: string) {
    return api.delete(`/admin/delete-teacher/${code}`);
}
