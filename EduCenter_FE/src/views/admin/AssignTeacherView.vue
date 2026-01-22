<template>
    <AdminHeader
        title="Phân công giáo viên"
        subtitle="Quản lý phân công giáo viên cho các lớp học"
    />

    <div class="d-flex justify-content-between mb-3">
        <button
            class="btn btn-primary"
            data-bs-toggle="modal"
            data-bs-target="#addClassroomModal"
        >
            + Phân công giáo viên
        </button>
    </div>
    <div class="table-wrapper">
        <AssignTeacherTable
            :assign-teacher="assignTeachers"
            :page="page"
            :size="size"
            @edit=""
            @delete=""
        />

        <StudentInClasroomTable
            v-if="showStudentModal"
            :students="students"
            :page="page"
            :size="size"
            :name="selectedClassroomName"
            @close="showStudentModal = false"
        />

        <!-- <EditClassroomModal :classroom="editingClassroom" @submit="" /> -->

        <Pagination :page="page" :total-pages="totalPages" @change="goToPage" />

        <!-- <ConfirmDeleteModal
            v-if="selectedClassroom"
            :fullName="selectedClassroom.name"
            :code="selectedClassroom.name"
            @confirm=""
        /> -->
    </div>
    <AddClassroomModal :key="modalKey" @submit="" />
    <AddStudentToClassroom ref="addStudentModal" :key="modalKey" @submit="" />
</template>

<script setup lang="ts">
import { ref } from "vue";
import AdminHeader from "../../components/admin/AdminHeader.vue";
import AssignTeacherTable from "../../components/admin/assign-teacher/AssignTeacherTable.vue";
import Pagination from "../../components/common/Pagination.vue";

import type { AssignTeacher } from "../../types/AssignTeacher";
import AddClassroomModal from "../../components/admin/classroom/AddClassroomModal.vue";
import ConfirmDeleteModal from "../../components/common/ConfirmDeleteModal.vue";
import EditClassroomModal from "../../components/admin/classroom/EditClassroomModal.vue";
import AddStudentToClassroom from "../../components/admin/classroom/AddStudentToClassroom.vue";

import StudentInClasroomTable from "../../components/admin/classroom/StudentInClasroomTable.vue";
import { useAssignTeachers } from "../../composables/useAssignTeacher";

const {
    assignTeachers,
    page,
    size,
    totalPages,
    goToPage,
    // createClassroom,
    // updateClassroom,
    // deleteClassroom,
    // addStudentToClassroom,
    // getAllStudentInClassroom,
} = useAssignTeachers();

const modalKey = ref(0); // Để reset modal mỗi lần mở

// const editingClassroom = ref<Classroom | null>(null);

// const selectedClassroom = ref<Classroom | null>(null);

const showStudentModal = ref(false);

const students = ref<any[]>([]);

const selectedClassroomName = ref("");

const addStudentModal = ref<InstanceType<typeof AddStudentToClassroom> | null>(
    null,
);

// async function onAddClassroom(classroom: Partial<Classroom>) {
//     try {
//         await createClassroom(classroom);

//         const modalEl = document.getElementById("addClassroomModal");
//         if (modalEl) {
//             const modal = Modal.getInstance(modalEl);
//             modal?.hide();
//         }
//         modalKey.value += 1; // reset modal
//     } catch {}
// }

// function onEditClassroom(classroom: Classroom) {
//     editingClassroom.value = classroom;
//     const modalEl = document.getElementById("editClassroomModal");
//     if (modalEl) {
//         const modal = new Modal(modalEl);
//         modal.show();
//     }
// }

// async function onUpdateClassroom(classroom: Classroom) {
//     try {
//         await updateClassroom(classroom); // nếu lỗi → nhảy catch

//         const modalEl = document.getElementById("editClassroomModal");
//         if (modalEl) {
//             const modal = Modal.getInstance(modalEl);
//             modal?.hide();
//         }
//     } catch {
//         // ❌ KHÔNG đóng modal
//         // Toast lỗi đã được xử lý trong composable
//     }
// }

// function onDeleteClassroom(classroom: Classroom) {
//     selectedClassroom.value = classroom;
//     const modalEl = document.getElementById("confirmDeleteModal");
//     if (modalEl) {
//         const modal = new Modal(modalEl);
//         modal.show();
//     }
// }

// async function onConfirmDelete() {
//     try {
//         if (selectedClassroom.value) {
//             await deleteClassroom(selectedClassroom.value.name.toString());

//             const modalEl = document.getElementById("confirmDeleteModal");
//             if (modalEl) {
//                 const modal = Modal.getInstance(modalEl);
//                 modal?.hide();
//             }
//         }
//     } catch (error) {}
// }

// async function onStudentAddedToClassroom(payload: {
//     name: string;
//     studentCode: string;
// }) {
//     try {
//         // Gọi composable để thêm học viên vào lớp học
//         await addStudentToClassroom(payload.name, payload.studentCode);

//         // Reset form khi thành công
//         addStudentModal.value?.resetForm();

//         // Đóng modal khi thành công
//         const modalEl = document.getElementById("addStudentToClassroomModal");
//         if (modalEl) {
//             const modal = Modal.getInstance(modalEl);
//             modal?.hide();
//         }
//     } catch (error) {
//         // ❌ Lỗi → KHÔNG đóng modal, KHÔNG reset form
//     }
// }

// async function openStudentList(classroom: Classroom) {
//     try {
//         selectedClassroomName.value = classroom.name.toString();

//         const res = await getAllStudentInClassroom(classroom.name.toString());
//         students.value = res;
//         showStudentModal.value = true;
//     } catch {}
// }
</script>
