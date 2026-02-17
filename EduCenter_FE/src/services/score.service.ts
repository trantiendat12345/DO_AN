import api from "../api/axios";
import type { Score } from "../types/Score";

export function getAllScores(page = 0, size = 10) {
    return api
        .get("/admin/get-all-scores", { params: { page, size } })
        .then((res) => res.data);
}

export function getScoresByClassroom(
    classroomName: string,
    page = 0,
    size = 10,
) {
    return api
        .get(`/admin/get-scores-by-classroom/${classroomName}`, {
            params: { page, size },
        })
        .then((res) => res.data);
}

export function getScoresByStudent(studentCode: string) {
    return api
        .get(`/admin/get-scores-by-student/${studentCode}`)
        .then((res) => res.data);
}

export function getScoreById(id: number) {
    return api.get(`/admin/get-score/${id}`).then((res) => res.data);
}

export function createScore(data: Partial<Score>) {
    return api.post("/admin/create-score", data);
}

export function updateScore(id: number, data: Partial<Score>) {
    return api.put(`/admin/update-score/${id}`, data);
}

export function deleteScore(id: number) {
    return api.delete(`/admin/delete-score/${id}`);
}
