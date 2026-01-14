<template>
    <div class="modal fade" id="editStudentModal" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <form class="modal-content" @submit.prevent="submit">
                <!-- HEADER -->
                <div class="modal-header">
                    <h5 class="modal-title">Cập nhật học sinh</h5>
                    <button class="btn-close" data-bs-dismiss="modal"></button>
                </div>

                <!-- BODY -->
                <div class="modal-body" v-if="form">
                    <div class="row g-3">
                        <!-- MÃ HỌC VIÊN (KHÓA) -->
                        <div class="col-md-6">
                            <input
                                class="form-control"
                                :value="form.studentCode"
                                disabled
                            />
                        </div>

                        <!-- HỌ TÊN -->
                        <div class="col-md-6">
                            <input
                                v-model="form.fullName"
                                class="form-control"
                                placeholder="Họ và tên"
                                required
                            />
                        </div>

                        <!-- NGÀY SINH -->
                        <div class="col-md-6">
                            <input
                                v-model="form.dob"
                                type="date"
                                class="form-control"
                            />
                        </div>

                        <!-- GIỚI TÍNH -->
                        <div class="col-md-6">
                            <select v-model="form.gender" class="form-select">
                                <option value="">Chọn giới tính</option>
                                <option value="MALE">Nam</option>
                                <option value="FEMALE">Nữ</option>
                                <option value="OTHER">Khác</option>
                            </select>
                        </div>

                        <!-- SĐT -->
                        <div class="col-md-6">
                            <input
                                v-model="form.phone"
                                class="form-control"
                                placeholder="Số điện thoại"
                            />
                        </div>

                        <!-- EMAIL -->
                        <div class="col-md-6">
                            <input
                                v-model="form.email"
                                type="email"
                                class="form-control"
                                placeholder="Email"
                            />
                        </div>

                        <!-- ĐỊA CHỈ -->
                        <div class="col-12">
                            <input
                                v-model="form.address"
                                class="form-control"
                                placeholder="Địa chỉ"
                            />
                        </div>

                        <!-- TRẠNG THÁI -->
                        <div class="col-md-6">
                            <select v-model="form.status" class="form-select">
                                <option value="ACTIVE">Đang học</option>
                                <option value="INACTIVE">Tạm nghỉ</option>
                            </select>
                        </div>

                        <!-- TRÌNH ĐỘ -->
                        <div class="col-md-6">
                            <input
                                v-model="form.level"
                                class="form-control"
                                placeholder="Trình độ"
                            />
                        </div>

                        <!-- GHI CHÚ -->
                        <div class="col-12">
                            <input
                                v-model="form.note"
                                class="form-control"
                                placeholder="Ghi chú"
                            />
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
                    <button type="submit" class="btn btn-primary">
                        Lưu thay đổi
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, watch } from "vue";
import type { Student } from "../../../types/Student";

/* ===== PROPS ===== */
const props = defineProps<{
    student: Student | null;
}>();

/* ===== EMIT ===== */
const emit = defineEmits<{
    (e: "submit", student: Student): void;
}>();

/* ===== FORM ===== */
const form = ref<Student | null>(null);

/* Clone student → form */
watch(
    () => props.student,
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
