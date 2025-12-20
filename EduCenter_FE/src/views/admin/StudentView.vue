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
    <StudentTable
      :students="students"
      :page="page"
      :size="10"
      @edit="onEditStudent"
    />

    <EditStudentModal :student="editingStudent" @submit="onUpdateStudent" />

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
import EditStudentModal from "../../components/admin/EditStudentModal.vue";

const { students, page, totalPages, goToPage, createStudent, updateStudent } = useStudents();

const modalKey = ref(0); // Để reset modal mỗi lần mở

const editingStudent = ref<Student | null>(null);

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

function onEditStudent(student: Student) {
  editingStudent.value = student;

  const modalEl = document.getElementById("editStudentModal");
  if (modalEl) {
    const modal = new Modal(modalEl);
    modal.show();
  }
}

async function onUpdateStudent(student: Student) {
  try {
    await updateStudent(student);
    // ✅ Đóng modal
    const modalEl = document.getElementById("editStudentModal");
    if (modalEl) {
      const modal = Modal.getInstance(modalEl);
      modal?.hide();
    }
  } catch (error) {}
}
</script>
