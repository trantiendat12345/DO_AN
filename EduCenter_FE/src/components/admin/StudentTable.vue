<!-- src/components/admin/StudentTable.vue -->
<template>
  <div class="table-responsive">
    <table class="table table-striped align-middle">
      <thead>
        <tr>
          <th>#</th>
          <th>Mã học viên</th>
          <th>Họ và tên</th>
          <th>SĐT</th>
          <th>Email</th>
          <th>Trạng thái</th>
          <th>Trình độ</th>
          <th>Ghi chú</th>
          <th width="160">Hành động</th>
        </tr>
      </thead>

      <tbody>
        <tr v-for="(s, i) in students" :key="s.studentCode">
          <td>{{ page * size + i + 1 }}</td>
          <td>{{ s.studentCode }}</td>
          <td>{{ s.fullName }}</td>
          <td>{{ s.phone }}</td>
          <td>{{ s.email }}</td>
          <td>
            <span class="badge bg-success">{{ s.status }}</span>
          </td>
          <td>{{ s.level }}</td>
          <td>{{ s.note }}</td>
          <td>
            <div class="btn-group btn-group-sm">
              <button class="btn btn-outline-warning" @click="$emit('edit', s)">
                Sửa
              </button>
              <button
                class="btn btn-outline-danger"
                @click="$emit('delete', s)"
              >
                Xóa
              </button>
            </div>
          </td>
        </tr>

        <tr v-if="!students.length">
          <td colspan="9" class="text-center text-muted">Không có dữ liệu</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import type { Student } from "../../types/Student";

defineProps<{
  students: Student[];
  page: number; // 0-based
  size: number;
}>();

defineEmits<{
  (e: "edit", student: Student): void;
  (e: "delete", student: Student): void;
}>();
</script>
