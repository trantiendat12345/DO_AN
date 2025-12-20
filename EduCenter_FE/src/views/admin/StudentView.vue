<template>
  <AdminHeader
    title="Quản lý học sinh"
    subtitle="Danh sách học viên đang theo học tại trung tâm"
  />

  <button
    class="btn btn-primary mb-3"
    data-bs-toggle="modal"
    data-bs-target="#addStudentModal"
  >
    + Thêm học sinh
  </button>
  <div class="table-wrapper">
    <StudentTable :students="students" :page="page" :size="10" />
  
    <Pagination :page="page" :total-pages="totalPages" @change="goToPage" />
  </div>

  <AddStudentModal :key="modalKey" @submit="onAddStudent" />
</template>

<script setup lang="ts">
import { Modal } from "bootstrap";
import AdminHeader from "../../components/admin/AdminHeader.vue";
import StudentTable from "../../components/admin/StudentTable.vue";
import AddStudentModal from "../../components/admin/AddStudentModal.vue";
import Pagination from "../../components/common/Pagination.vue";
import { useStudents } from "../../composables/useStudent";
import type { Student } from "../../types/Student";
import { ref } from "vue";

const { students, page, totalPages, goToPage, createStudent } = useStudents();

const modalKey = ref(0); // Để reset modal mỗi lần mở

async function onAddStudent(student: Partial<Student>) {
  try {
    await createStudent(student);

    // ✅ chỉ đóng modal khi thành công
    const modalEl = document.getElementById("addStudentModal");
    if (modalEl) {
      const modal = Modal.getInstance(modalEl);
      modal?.hide();
    }
    modalKey.value += 1; // Reset modal
  } catch {
    // ❌ lỗi → KHÔNG đóng modal
  }
}
</script>
