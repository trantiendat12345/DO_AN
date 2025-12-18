<template>
  <div class="table-responsive">
    <table class="table table-striped align-middle">
      <thead>
        <tr>
          <th>#</th>
          <th>Họ tên</th>
          <th>Lớp</th>
          <th>Email</th>
          <th>Trạng thái</th>
          <th>Hành động</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(s, i) in students" :key="i">
          <td>{{ i + 1 }}</td>
          <td>{{ s.name }}</td>
          <td>{{ s.class }}</td>
          <td>{{ s.email }}</td>
          <td>
            <span class="badge bg-success">{{ s.status }}</span>
          </td>
          <td>
            <div class="btn-group btn-group-sm">
              <button
                class="btn btn-outline-warning"
                data-bs-toggle="modal"
                data-bs-target="#editStudentModal"
                @click="selectStudent(s)"
              >
                Sửa
              </button>
              <button
                class="btn btn-outline-danger"
                @click="deleteStudent(s.id)"
              >
                Xóa
              </button>
            </div>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup lang="ts">
import { useStudents } from '../../composables/UseStudents';
const { students, selectStudent, deleteStudent } = useStudents()
</script>
