<template>
  <AdminHeader
    title="Quản lý học sinh"
    subtitle="Danh sách học viên đang theo học tại trung tâm"
  />

  <StudentTable />
  <AddStudentModal />
</template>

<script setup lang="ts">
import AdminHeader from '../../components/admin/AdminHeader.vue';
import StudentTable from '../../components/admin/StudentTable.vue';
import AddStudentModal from '../../components/admin/AddStudentModal.vue';
</script>
