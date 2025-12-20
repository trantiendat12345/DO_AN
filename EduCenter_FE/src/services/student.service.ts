import api from "../api/axios";
import type { Student } from "../types/Student";

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