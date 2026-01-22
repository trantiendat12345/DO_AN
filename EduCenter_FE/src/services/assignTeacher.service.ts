import api from "../api/axios";

// GET PAGED
export function getAllAssignTeachers(page = 0, size = 10) {
    return api
        .get("/admin/get-all-assigned-teacher", {
            params: { page, size },
        })
        .then((res) => res.data);
}

// ASSIGN TEACHER TO CLASSROOM
export function assignTeacherToClassroom(
    name: string,
    teacherCode: string,
    teacherRole: string,
) {
    return api.post("/admin/assign-teacher", {
        name,
        teacherCode,
        teacherRole,
    });
}

// // UPDATE
// export function updateClassroom(name: string, data: Partial<Classroom>) {
//     return api.put(`/admin/update-classroom/${name}`, data);
// }

// // DELETE
// export function deleteClassroom(name: string) {
//     return api.delete(`/admin/delete-classroom/${name}`);
// }

// // ADD STUDENT TO CLASSROOM
// export function addStudentToClassroom(name: string, studentCode: string) {
//     return api.post(`/admin/add-student-to-classroom`, {
//         name,
//         studentCode,
//     });
// }

// export function getAllStudentInClassroom(name: string) {
//     return api
//         .get(`/admin/get-all-students-in-classroom/${name}`)
//         .then((res) => res.data);
// }
