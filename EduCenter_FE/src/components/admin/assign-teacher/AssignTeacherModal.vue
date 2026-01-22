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
                        v-model="form.teacherCode"
                        class="form-control mb-3"
                        placeholder="Mã giáo viên"
                        required
                    />
                    <input
                        v-model="form.fullName"
                        class="form-control mb-3"
                        placeholder="Tên lớp"
                        required
                    />
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary">Lưu</button>
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
    teacherCode: "",
    fullName: "",
    phone: "",
    email: "",
    status: "",
};

const form = ref({ ...emptyForm });

function submit() {
    emit("submit", { ...form.value });
}
</script>
