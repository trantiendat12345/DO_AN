<template>
    <AdminHeader
        title="Quản lý lớp học"
        subtitle="Danh sách các lớp đang hoạt động"
    />

    <button
        class="btn btn-primary mb-3"
        data-bs-toggle="modal"
        data-bs-target="#addCourseModal"
    >
        + Thêm khoá học
    </button>
    <div class="table-wrapper">
        <CourseTable
            :course="courses"
            :page="page"
            :size="size"
            @edit="onEditCourse"
            @delete="onDeleteCourse"
        />

        <EditCourseModal :course="selectedCourse" @submit="onUpdateCourse" />

        <Pagination :page="page" :total-pages="totalPages" @change="goToPage" />

        <ConfirmDeleteModal
            v-if="selectedCourse"
            :fullName="selectedCourse.nameCourse"
            :code="selectedCourse.nameCourse"
            @confirm="onConfirmDelete"
        />
    </div>
    <AddCourseModal :key="modalKey" @submit="onAddCourse" />
</template>

<script setup lang="ts">
import { ref } from "vue";
import AdminHeader from "../../components/admin/AdminHeader.vue";
import CourseTable from "../../components/admin/course/CourseTable.vue";
import Pagination from "../../components/common/Pagination.vue";

import { useCourses } from "../../composables/useCourse";
import { Modal } from "bootstrap";
import type { Course } from "../../types/Course";
import AddCourseModal from "../../components/admin/course/AddCouseModal.vue";
import EditCourseModal from "../../components/admin/course/EditCourseModal.vue";
import ConfirmDeleteModal from "../../components/common/ConfirmDeleteModal.vue";

const {
    courses,
    page,
    size,
    totalPages,
    goToPage,
    createCourse,
    updateCourse,
    deleteCourse,
} = useCourses();

const modalKey = ref(0); // Để reset modal mỗi lần mở

const selectedCourse = ref<Course | null>(null);

async function onAddCourse(course: Partial<Course>) {
    try {
        await createCourse(course);
        const modalEl = document.getElementById("addCourseModal");
        if (modalEl) {
            const modal = Modal.getInstance(modalEl);
            modal?.hide();
        }
        modalKey.value += 1; // reset modal
    } catch {}
}

function onEditCourse(course: Course) {
    selectedCourse.value = course;
    const modalEl = document.getElementById("editCourseModal");
    if (modalEl) {
        const modal = new Modal(modalEl);
        modal.show();
    }
}

async function onUpdateCourse(course: Course) {
    try {
        await updateCourse(course); // nếu lỗi → nhảy catch

        const modalEl = document.getElementById("editCourseModal");
        if (modalEl) {
            const modal = Modal.getInstance(modalEl);
            modal?.hide();
        }
    } catch {
        // ❌ KHÔNG đóng modal
        // Toast lỗi đã được xử lý trong composable
    }
}

function onDeleteCourse(course: Course) {
    selectedCourse.value = course;
    const modalEl = document.getElementById("confirmDeleteModal");
    if (modalEl) {
        const modal = new Modal(modalEl);
        modal.show();
    }
}

async function onConfirmDelete() {
    try {
        if (selectedCourse.value) {
            await deleteCourse(selectedCourse.value.nameCourse.toString());

            const modalEl = document.getElementById("confirmDeleteModal");
            if (modalEl) {
                const modal = Modal.getInstance(modalEl);
                modal?.hide();
            }
        }
    } catch (error) {}
}
</script>
