<template>
  <AdminHeader
    title="Quản lý giáo viên"
    subtitle="Danh sách giáo viên trong trung tâm"
  />

  <button
    class="btn btn-primary mb-3"
    data-bs-toggle="modal"
    data-bs-target="#addTeacherModal"
  >
    + Thêm giáo viên
  </button>
  <div class="table-wrapper">
    <TeacherTable
      :teachers="teachers"
      :page="page"
      :size="10"
      @edit="onEditTeacher"
      @delete="openDeleteModal"
    />

    <EditTeacherModal :teacher="selectedTeacher" @submit="onUpdateTeacher" />

    <Pagination :page="page" :total-pages="totalPages" @change="goToPage" />
    <ConfirmDeleteModal
      v-if="selectedTeacher"
      :fullName="selectedTeacher.fullName"
      :code="selectedTeacher.teacherCode"
      @confirm="onConfirmDelete"
    />
  </div>

  <AddTeacherModal @submit="onAddTeacher" />
</template>

<script setup lang="ts">
import { ref } from "vue";
import { Modal } from "bootstrap";
import AdminHeader from "../../components/admin/AdminHeader.vue";
import TeacherTable from "../../components/admin/TeacherTable.vue";
import AddTeacherModal from "../../components/admin/AddTeacherModal.vue";
import Pagination from "../../components/common/Pagination.vue";
import ConfirmDeleteModal from "../../components/common/ConfirmDeleteModal.vue";
import EditTeacherModal from "../../components/admin/EditTeacherModal.vue";

import { useTeacher } from "../../composables/useTeacher";
import type { Teacher } from "../../types/Teacher";

const {
  teachers,
  page,
  totalPages,
  goToPage,
  createTeacher,
  updateTeacher,
  deleteTeacher,
} = useTeacher();

const modalKey = ref(0); // Để reset modal mỗi lần mở

const selectedTeacher = ref<Teacher | null>(null);

async function onAddTeacher(data: Partial<Teacher>) {
  try {
    await createTeacher(data);

    const modalEl = document.getElementById("addTeacherModal");
    if (modalEl) {
      const modal = Modal.getInstance(modalEl);
      modal?.hide();
    }
    modalKey.value += 1; // Reset modal
  } catch (error) {}
}

function onEditTeacher(teacher: Teacher) {
  selectedTeacher.value = teacher;

  const modalEl = document.getElementById("editTeacherModal");
  if (modalEl) {
    new Modal(modalEl).show();
  }
}

async function onUpdateTeacher(teacher: Teacher) {
  try {
    await updateTeacher(teacher);

    const modalEl = document.getElementById("editTeacherModal");
    const modal = Modal.getInstance(modalEl!);
    modal?.hide();
  } catch {
    // lỗi đã được toast trong composable
  }
}

function openDeleteModal(teacher: Teacher) {
  selectedTeacher.value = teacher;

  const modalEl = document.getElementById("confirmDeleteModal");
  if (modalEl) {
    const modal = new Modal(modalEl);
    modal.show();
  }
}

async function onConfirmDelete(teacherCode: string) {
  try {
    await deleteTeacher(teacherCode);
    const modalEl = document.getElementById("confirmDeleteModal");
    if (modalEl) {
      const modal = Modal.getInstance(modalEl);
      modal?.hide();
    }
  } catch {}
}
</script>
