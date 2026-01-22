<template>
    <div class="modal fade" id="addTeacherModal" tabindex="-1">
        <div class="modal-dialog">
            <form class="modal-content" @submit.prevent="submit">
                <div class="modal-header">
                    <h5 class="modal-title">Thêm giáo viên</h5>
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
                        placeholder="Họ tên"
                        required
                    />
                    <input
                        v-model="form.phone"
                        class="form-control mb-3"
                        placeholder="Số điện thoại"
                    />
                    <input
                        v-model="form.email"
                        class="form-control mb-3"
                        placeholder="Email"
                    />
                    <select v-model="form.status" class="form-select">
                        <option value="">Chọn trạng thái</option>
                        <option value="ACTIVE">Đang dạy</option>
                        <option value="INACTIVE">Ngưng hoạt động</option>
                        <option value="ON_LEAVE">Nghỉ phép</option>
                        <option value="TERMINATED">Nghỉ hẳn</option>
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
                    <button class="btn btn-primary">Lưu giáo viên</button>
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
