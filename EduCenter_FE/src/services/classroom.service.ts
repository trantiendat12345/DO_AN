import api from "../api/axios";
import type { Classroom } from "../types/Classroom";

// GET PAGED
export function getClassrooms(page = 0, size = 10) {
    return api
        .get("/admin/get-all-classrooms", {
            params: { page, size },
        })
        .then((res) => res.data);
}

// CREATE
export function createClassroom(data: Partial<Classroom>) {
    return api.post("/admin/create-classroom", data);
}

// UPDATE
export function updateClassroom(name: string, data: Partial<Classroom>) {
    return api.put(`/admin/update-classroom/${name}`, data);
}

// DELETE
export function deleteClassroom(name: string) {
    return api.delete(`/admin/delete-classroom/${name}`);
}

// ADD STUDENT TO CLASSROOM
export function addStudentToClassroom(name: string, studentCode: string) {
    return api.post(`/admin/add-student-to-classroom`, {
        name,
        studentCode,
    });
}

export function getAllStudentInClassroom(name: string, page = 0, size = 10) {
    console.log("CALL API WITH:", name);
    return api
        .get("/admin/get-all-students-in-classroom", {
            params: { name, page, size },
        })
        .then((res) => res.data);
}
