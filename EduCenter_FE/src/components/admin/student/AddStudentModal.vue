<template>
    <div class="modal fade" id="addStudentModal" tabindex="-1">
        <div class="modal-dialog modal-lg modal-dialog-centered">
            <form class="modal-content custom-modal" @submit.prevent="submit">
                <div class="modal-header">
                    <h5 class="modal-title">
                        <span class="modal-icon">👨‍🎓</span>
                        Thêm học sinh mới
                    </h5>
                    <button
                        class="btn-close btn-close-white"
                        data-bs-dismiss="modal"
                    ></button>
                </div>

                <div class="modal-body">
                    <div class="row g-4">
                        <div class="col-md-6">
                            <label class="form-label"
                                >Mã học sinh
                                <span class="text-danger">*</span></label
                            >
                            <input
                                v-model="form.studentCode"
                                class="form-control"
                                placeholder="Nhập mã học sinh"
                                required
                            />
                        </div>
                        <div class="col-md-6">
                            <label class="form-label"
                                >Họ và tên
                                <span class="text-danger">*</span></label
                            >
                            <input
                                v-model="form.fullName"
                                class="form-control"
                                placeholder="Nhập họ và tên"
                                required
                            />
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Ngày sinh</label>
                            <input
                                v-model="form.dob"
                                type="date"
                                class="form-control"
                            />
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Giới tính</label>
                            <select v-model="form.gender" class="form-select">
                                <option value="">Chọn giới tính</option>
                                <option value="MALE">Nam</option>
                                <option value="FEMALE">Nữ</option>
                                <option value="OTHER">Khác</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Số điện thoại</label>
                            <input
                                v-model="form.phone"
                                class="form-control"
                                placeholder="Nhập số điện thoại"
                            />
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Email</label>
                            <input
                                v-model="form.email"
                                type="email"
                                class="form-control"
                                placeholder="Nhập email"
                            />
                        </div>
                        <div class="col-12">
                            <label class="form-label">Địa chỉ</label>
                            <input
                                v-model="form.address"
                                class="form-control"
                                placeholder="Nhập địa chỉ"
                            />
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Trạng thái</label>
                            <select v-model="form.status" class="form-select">
                                <option value="">Chọn trạng thái</option>
                                <option value="ACTIVE">Đang học</option>
                                <option value="INACTIVE">Đã nghỉ</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Trình độ</label>
                            <input
                                v-model="form.level"
                                class="form-control"
                                placeholder="Nhập trình độ"
                            />
                        </div>
                        <div class="col-12">
                            <label class="form-label">Ghi chú</label>
                            <textarea
                                v-model="form.note"
                                class="form-control"
                                placeholder="Nhập ghi chú"
                                rows="2"
                            ></textarea>
                        </div>
                    </div>
                </div>

                <div class="modal-footer">
                    <button
                        type="button"
                        class="btn btn-cancel"
                        data-bs-dismiss="modal"
                    >
                        <span class="me-1">✕</span> Hủy
                    </button>
                    <button type="submit" class="btn btn-submit">
                        <span class="me-1">💾</span> Lưu học sinh
                    </button>
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

<style scoped>
.custom-modal {
    border: none;
    border-radius: 20px;
    overflow: hidden;
}

.modal-header {
    background: linear-gradient(135deg, #06b6d4 0%, #0891b2 100%);
    color: #fff;
    padding: 1.25rem 1.5rem;
}

.modal-title {
    display: flex;
    align-items: center;
    gap: 10px;
    font-weight: 600;
}

.modal-icon {
    font-size: 1.25rem;
}

.modal-body {
    padding: 1.5rem;
}

.form-label {
    font-weight: 500;
    color: #374151;
    margin-bottom: 0.5rem;
    font-size: 0.9rem;
}

.form-control,
.form-select {
    border: 2px solid #e2e8f0;
    border-radius: 10px;
    padding: 0.65rem 1rem;
    transition: all 0.2s ease;
}

.form-control:focus,
.form-select:focus {
    border-color: #06b6d4;
    box-shadow: 0 0 0 4px rgba(6, 182, 212, 0.1);
}

.modal-footer {
    border-top: 1px solid #f1f5f9;
    padding: 1rem 1.5rem;
    gap: 12px;
}

.btn-cancel {
    background: #f1f5f9;
    color: #475569;
    border: none;
    padding: 0.65rem 1.25rem;
    border-radius: 10px;
    font-weight: 500;
    transition: all 0.2s ease;
}

.btn-cancel:hover {
    background: #e2e8f0;
    color: #1e293b;
}

.btn-submit {
    background: linear-gradient(135deg, #06b6d4 0%, #0891b2 100%);
    color: #fff;
    border: none;
    padding: 0.65rem 1.25rem;
    border-radius: 10px;
    font-weight: 500;
    box-shadow: 0 4px 12px rgba(6, 182, 212, 0.3);
    transition: all 0.2s ease;
}

.btn-submit:hover {
    background: linear-gradient(135deg, #0891b2 0%, #0e7490 100%);
    transform: translateY(-2px);
    box-shadow: 0 6px 16px rgba(6, 182, 212, 0.4);
}
</style>
