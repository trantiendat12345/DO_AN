<template>
  <div class="modal fade" id="addStudentModal" tabindex="-1">
    <div class="modal-dialog">
      <form class="modal-content" @submit.prevent="submit">
        <div class="modal-header">
          <h5 class="modal-title">Thêm học sinh</h5>
          <button class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <div class="modal-body">
          <input v-model="name" class="form-control mb-3" placeholder="Tên học sinh" />
        </div>

        <div class="modal-footer">
          <button class="btn btn-primary">Lưu</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const name = ref('')

function submit() {
  console.log(name.value)
  name.value = ''
}
</script>
