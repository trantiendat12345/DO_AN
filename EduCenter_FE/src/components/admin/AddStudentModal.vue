<template>
  <div class="modal fade" id="addStudentModal" tabindex="-1">
    <div class="modal-dialog modal-lg">
      <form class="modal-content" @submit.prevent="submit">
        <div class="modal-header">
          <h5 class="modal-title">Thêm học sinh</h5>
          <button class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <div class="modal-body">
          <div class="row g-3">
            <div class="col-md-6">
              <input
                v-model="form.studentCode"
                class="form-control"
                placeholder="Mã học sinh *"
                required
              />
            </div>
            <div class="col-md-6">
              <input
                v-model="form.fullName"
                class="form-control"
                placeholder="Họ và tên *"
                required
              />
            </div>
            <div class="col-md-6">
              <input v-model="form.dob" type="date" class="form-control" />
            </div>
            <div class="col-md-6">
              <select v-model="form.gender" class="form-select">
                <option value="">Chọn giới tính</option>
                <option value="MALE">Nam</option>
                <option value="FEMALE">Nữ</option>
                <option value="OTHER">Khác</option>
              </select>
            </div>
            <div class="col-md-6">
              <input
                v-model="form.phone"
                class="form-control"
                placeholder="Số điện thoại"
              />
            </div>
            <div class="col-md-6">
              <input
                v-model="form.email"
                type="email"
                class="form-control"
                placeholder="Email"
              />
            </div>
            <div class="col-12">
              <input
                v-model="form.address"
                class="form-control"
                placeholder="Địa chỉ"
              />
            </div>
            <div class="col-md-6">
              <select v-model="form.status" class="form-select">
                <option value="">Chọn trạng thái</option>
                <option value="ACTIVE">Đang học</option>
                <option value="INACTIVE">Đã nghỉ</option>
              </select>
            </div>
            <div class="col-md-6">
              <input
                v-model="form.level"
                class="form-control"
                placeholder="Trình độ"
              />
            </div>
            <div class="col-12">
              <input
                v-model="form.note"
                class="form-control"
                placeholder="Ghi chú"
              />
            </div>
          </div>
        </div>

        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Hủy
          </button>
          <button type="submit" class="btn btn-primary">Lưu học sinh</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";

const emit = defineEmits<{
  (e: "submit", data: any): void;
}>();

const emptyForm = {
  studentCode: "",
  fullName: "",
  dob: "",
  gender: "",
  phone: "",
  email: "",
  address: "",
  status: "",
  level: "",
  note: "",
  classroomName: "",
};

const form = ref({ ...emptyForm });

function submit() {
  emit("submit", { ...form.value });
}
</script>
