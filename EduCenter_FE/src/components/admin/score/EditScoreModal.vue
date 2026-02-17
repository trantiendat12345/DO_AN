<template>
    <div class="modal fade" id="editScoreModal" tabindex="-1">
        <div class="modal-dialog modal-lg modal-dialog-centered">
            <form class="modal-content custom-modal" @submit.prevent="submit">
                <div class="modal-header">
                    <h5 class="modal-title">
                        <span class="modal-icon">✏️</span>
                        Cập nhật điểm
                    </h5>
                    <button
                        class="btn-close btn-close-white"
                        data-bs-dismiss="modal"
                    ></button>
                </div>

                <div class="modal-body" v-if="form">
                    <div class="row g-4">
                        <div class="col-md-6">
                            <label class="form-label">Mã học viên</label>
                            <input
                                class="form-control"
                                :value="form.studentCode"
                                disabled
                            />
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Họ và tên</label>
                            <input
                                class="form-control"
                                :value="form.studentName"
                                disabled
                            />
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Lớp học</label>
                            <input
                                class="form-control"
                                :value="form.classroomName"
                                disabled
                            />
                        </div>
                        <div class="col-md-6">
                            <label class="form-label"
                                >Loại điểm
                                <span class="text-danger">*</span></label
                            >
                            <select
                                v-model="form.type"
                                class="form-select"
                                required
                            >
                                <option value="">Chọn loại điểm</option>
                                <option value="HOMEWORK">Bài tập</option>
                                <option value="TEST">Kiểm tra</option>
                                <option value="MIDTERM">Giữa kỳ</option>
                                <option value="FINAL">Cuối kỳ</option>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label"
                                >Điểm số
                                <span class="text-danger">*</span></label
                            >
                            <input
                                v-model.number="form.value"
                                type="number"
                                step="0.1"
                                min="0"
                                max="10"
                                class="form-control"
                                placeholder="Nhập điểm (0 - 10)"
                                required
                            />
                        </div>
                        <div class="col-md-6">
                            <label class="form-label">Ghi chú</label>
                            <input
                                v-model="form.note"
                                class="form-control"
                                placeholder="Nhập ghi chú"
                            />
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
                        <span class="me-1">💾</span> Lưu thay đổi
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, watch } from "vue";
import type { Score } from "../../../types/Score";

const props = defineProps<{
    score: Score | null;
}>();

const emit = defineEmits<{
    (e: "submit", score: Score): void;
}>();

const form = ref<Score | null>(null);

watch(
    () => props.score,
    (val) => {
        if (val) {
            form.value = { ...val };
        }
    },
    { immediate: true },
);

function submit() {
    if (form.value) {
        emit("submit", { ...form.value });
    }
}
</script>

<style scoped>
.custom-modal {
    border: none;
    border-radius: 16px;
    overflow: hidden;
}

.modal-header {
    background: linear-gradient(135deg, #0891b2, #06b6d4);
    color: white;
    border: none;
    padding: 1.25rem 1.5rem;
}

.modal-title {
    display: flex;
    align-items: center;
    gap: 0.75rem;
    font-weight: 600;
}

.modal-icon {
    font-size: 1.5rem;
}

.modal-body {
    padding: 1.5rem;
}

.form-label {
    font-weight: 500;
    color: #334155;
    margin-bottom: 0.5rem;
}

.form-control,
.form-select {
    border-radius: 8px;
    border: 1px solid #e2e8f0;
    padding: 0.625rem 0.875rem;
    transition: all 0.2s ease;
}

.form-control:focus,
.form-select:focus {
    border-color: #06b6d4;
    box-shadow: 0 0 0 3px rgba(6, 182, 212, 0.1);
}

.form-control:disabled {
    background: #f8fafc;
    color: #64748b;
}

.modal-footer {
    border-top: 1px solid #f1f5f9;
    padding: 1rem 1.5rem;
    gap: 0.75rem;
}

.btn-cancel {
    background: #f1f5f9;
    color: #64748b;
    border: none;
    padding: 0.625rem 1.25rem;
    border-radius: 8px;
    font-weight: 500;
    transition: all 0.2s ease;
}

.btn-cancel:hover {
    background: #e2e8f0;
}

.btn-submit {
    background: linear-gradient(135deg, #0891b2, #06b6d4);
    color: white;
    border: none;
    padding: 0.625rem 1.25rem;
    border-radius: 8px;
    font-weight: 500;
    transition: all 0.2s ease;
}

.btn-submit:hover {
    transform: translateY(-1px);
    box-shadow: 0 4px 12px rgba(6, 182, 212, 0.3);
}
</style>
