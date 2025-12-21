<template>
  <div class="modal fade" id="editTeacherModal" tabindex="-1">
    <div class="modal-dialog modal-lg">
      <form class="modal-content" @submit.prevent="submit">
        <!-- HEADER -->
        <div class="modal-header">
          <h5 class="modal-title">Cập nhật giáo viên</h5>
          <button class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <!-- BODY -->
        <div class="modal-body" v-if="form">
          <div class="row g-3">
            <div class="col-md-6">
              <input class="form-control" :value="form.teacherCode" disabled />
            </div>
            <div class="col-md-6">
              <input
                v-model="form.fullName"
                class="form-control"
                placeholder="Họ tên"
                required
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

            <div class="col-md-6">
              <input
                v-model="form.phone"
                class="form-control"
                placeholder="Số điện thoại"
              />
            </div>

            <div class="col-md-6">
              <select v-model="form.status" class="form-select">
                <option value="ACTIVE">Đang dạy</option>
                <option value="INACTIVE">Ngưng hoạt động</option>
                <option value="ON_LEAVE">Nghỉ phép</option>
                <option value="TERMINATED">Nghỉ hẳn</option>
              </select>
            </div>
          </div>
        </div>

        <!-- FOOTER -->
        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary"
            data-bs-dismiss="modal"
          >
            Hủy
          </button>
          <button class="btn btn-primary">Lưu thay đổi</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, watch } from "vue";
import type { Teacher } from "../../types/Teacher";

/* ===== PROPS ===== */
const props = defineProps<{
  teacher: Teacher | null;
}>();

/* ===== EMIT ===== */
const emit = defineEmits<{
  (e: "submit", teacher: Teacher): void;
}>();

/* ===== FORM ===== */
const form = ref<Teacher | null>(null);

/* Khi chọn giáo viên → clone sang form */
watch(
  () => props.teacher,
  (val) => {
    if (val) {
      form.value = { ...val };
    }
  },
  { immediate: true }
);

/* SUBMIT */
function submit() {
  if (!form.value) return;
  emit("submit", form.value);
}
</script>
