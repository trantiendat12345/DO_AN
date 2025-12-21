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
      @delete="openDeleteModal"
    />

    <EditStudentModal :student="editingStudent" @submit="onUpdateStudent" />

    <Pagination :page="page" :total-pages="totalPages" @change="goToPage" />

    <ConfirmDeleteModal
      v-if="selectedStudent"
      :fullName="selectedStudent.fullName"
      :code="selectedStudent.studentCode"
      @confirm="onConfirmDelete"
    />
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
import ConfirmDeleteModal from "../../components/common/ConfirmDeleteModal.vue";

const {
  students,
  page,
  totalPages,
  goToPage,
  createStudent,
  updateStudent,
  deleteStudent,
} = useStudents();

const modalKey = ref(0); // Để reset modal mỗi lần mở

const editingStudent = ref<Student | null>(null);

const selectedStudent = ref<Student | null>(null);

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

function openDeleteModal(student: Student) {
  selectedStudent.value = student;

  const modalEl = document.getElementById("confirmDeleteModal");
  if (modalEl) {
    new Modal(modalEl).show();
  }
}

async function onConfirmDelete(studentCode: string) {
  try {
    await deleteStudent(studentCode);

    const modalEl = document.getElementById("confirmDeleteModal");
    if (modalEl) {
      Modal.getInstance(modalEl)?.hide();
    }
  } catch {
    // lỗi đã có toast → không đóng modal
  }
}
</script>
