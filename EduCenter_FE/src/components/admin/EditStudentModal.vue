<template>
  <div class="modal fade" id="editStudentModal" tabindex="-1">
    <div class="modal-dialog">
      <form class="modal-content" @submit.prevent="submit">
        <div class="modal-header">
          <h5 class="modal-title">Cập nhật học sinh</h5>
          <button class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <div class="modal-body" v-if="student">
          <input
            v-model="student.name"
            class="form-control mb-3"
            placeholder="Tên học sinh"
            required
          />

          <input
            v-model="student.email"
            class="form-control mb-3"
            placeholder="Email"
          />

          <select v-model="student.status" class="form-select">
            <option>Đang học</option>
            <option>Tạm nghỉ</option>
            <option>Đã tốt nghiệp</option>
          </select>
        </div>

        <div class="modal-footer">
          <button class="btn btn-secondary" data-bs-dismiss="modal">
            Hủy
          </button>
          <button class="btn btn-primary">
            Lưu thay đổi
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useStudents } from '../../composables/UseStudents';

const { selectedStudent: student, updateStudent } = useStudents()

function submit() {
  updateStudent()
}
</script>
