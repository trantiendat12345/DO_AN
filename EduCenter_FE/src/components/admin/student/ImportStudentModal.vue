<template>
    <div
        class="modal fade"
        id="importStudentModal"
        tabindex="-1"
        aria-labelledby="importStudentModalLabel"
        aria-hidden="true"
    >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="importStudentModalLabel">
                        📥 Import danh sách học viên
                    </h5>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                    ></button>
                </div>

                <div class="modal-body">
                    <div class="mb-3">
                        <label class="form-label fw-bold">
                            Chọn file Excel (.xlsx, .xls)
                        </label>
                        <input
                            type="file"
                            class="form-control"
                            accept=".xlsx,.xls"
                            @change="onFileChange"
                            ref="fileInput"
                        />
                        <div class="form-text">
                            Hỗ trợ file Excel với định dạng .xlsx hoặc .xls
                        </div>
                    </div>

                    <div v-if="selectedFile" class="alert alert-info">
                        <strong>File đã chọn:</strong> {{ selectedFile.name }}
                        <br />
                        <small
                            >Kích thước:
                            {{ formatFileSize(selectedFile.size) }}</small
                        >
                    </div>

                    <div class="alert alert-warning">
                        <small>
                            <strong>Lưu ý:</strong> File Excel cần có các cột
                            sau:
                            <ul class="mb-0 mt-2">
                                <li>Họ và tên</li>
                                <li>Ngày sinh (dd/MM/yyyy)</li>
                                <li>Giới tính (Nam/Nữ)</li>
                                <li>Số điện thoại</li>
                                <li>Email</li>
                                <li>Địa chỉ</li>
                                <li>Trình độ</li>
                            </ul>
                        </small>
                    </div>

                    <div v-if="uploading" class="text-center">
                        <div class="spinner-border text-primary" role="status">
                            <span class="visually-hidden">Đang tải lên...</span>
                        </div>
                        <p class="mt-2">Đang xử lý file...</p>
                    </div>
                </div>

                <div class="modal-footer">
                    <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                        :disabled="uploading"
                    >
                        Hủy
                    </button>
                    <button
                        type="button"
                        class="btn btn-primary"
                        @click="onSubmit"
                        :disabled="!selectedFile || uploading"
                    >
                        <span v-if="uploading">
                            <span
                                class="spinner-border spinner-border-sm me-2"
                                role="status"
                            ></span>
                            Đang import...
                        </span>
                        <span v-else>Import</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref } from "vue";

const emit = defineEmits<{
    submit: [file: File];
}>();

const selectedFile = ref<File | null>(null);
const fileInput = ref<HTMLInputElement | null>(null);
const uploading = ref(false);

function onFileChange(event: Event) {
    const target = event.target as HTMLInputElement;
    if (target.files && target.files.length > 0) {
        const file = target.files[0];
        if (file) {
            selectedFile.value = file;
        }
    }
}

function formatFileSize(bytes: number): string {
    if (bytes < 1024) return bytes + " B";
    if (bytes < 1024 * 1024) return (bytes / 1024).toFixed(2) + " KB";
    return (bytes / (1024 * 1024)).toFixed(2) + " MB";
}

function onSubmit() {
    if (!selectedFile.value) return;

    uploading.value = true;
    emit("submit", selectedFile.value);
}

function resetForm() {
    selectedFile.value = null;
    uploading.value = false;
    if (fileInput.value) {
        fileInput.value.value = "";
    }
}

// Expose reset method để parent có thể gọi
defineExpose({
    resetForm,
});
</script>

<style scoped>
.modal-body {
    padding: 1.5rem;
}

.form-label {
    color: #495057;
}

.alert ul {
    padding-left: 1.2rem;
}

.alert-info {
    background-color: #d1ecf1;
    border-color: #bee5eb;
    color: #0c5460;
}

.alert-warning {
    background-color: #fff3cd;
    border-color: #ffeeba;
    color: #856404;
}
</style>
