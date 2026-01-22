<template>
    <div class="modal fade" id="assignTeacherModal" tabindex="-1">
        <div class="modal-dialog">
            <form class="modal-content" @submit.prevent="submit">
                <div class="modal-header">
                    <h5 class="modal-title">Phân công giáo viên</h5>
                    <button class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <div class="modal-body">
                    <input
                        v-model="form.name"
                        class="form-control mb-3"
                        placeholder="Tên lớp"
                        required
                    />
                    <input
                        v-model="form.teacherCode"
                        class="form-control mb-3"
                        placeholder="Mã giáo viên"
                        required
                    />
                    <select v-model="form.teacherRole" class="form-select">
                        <option value="">Chọn vai trò giáo viên</option>
                        <option value="MAIN">Giáo viên chủ nhiệm</option>
                        <option value="ASSISTANT">Giáo viên hỗ trợ</option>
                    </select>
                </div>
                <div class="modal-footer">
                    <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                    >
                        Hủy
                    </button>
                    <button type="submit" class="btn btn-primary">Lưu</button>
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
    name: "",
    teacherCode: "",
    teacherRole: "",
};

const form = ref({ ...emptyForm });

function submit() {
    console.log("Submitting assign teacher:", form.value);
    emit("submit", { ...form.value });
}
</script>
