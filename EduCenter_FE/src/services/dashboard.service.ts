import api from "../api/axios";

export function getTotalStudents() {
    return api.get("/admin/total-student").then((res) => res.data);
}

export function getTotalTeachers() {
    return api.get("/admin/total-teacher").then((res) => res.data);
}

export function getTotalClassrooms() {
    return api.get("/admin/total-classroom").then((res) => res.data);
}

export function getTotalParents() {
    return api.get("/admin/total-parent").then((res) => res.data);
}
