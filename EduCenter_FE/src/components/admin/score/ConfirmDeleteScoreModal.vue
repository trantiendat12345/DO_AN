<template>
    <div class="modal fade" id="confirmDeleteScoreModal" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content delete-modal">
                <div class="modal-header delete-header">
                    <h5 class="modal-title">
                        <span class="warning-icon">⚠️</span>
                        Xác nhận xoá điểm
                    </h5>
                    <button
                        class="btn-close btn-close-white"
                        data-bs-dismiss="modal"
                    ></button>
                </div>

                <div class="modal-body text-center py-4">
                    <div class="delete-icon-wrapper mb-3">
                        <span class="delete-icon">🗑️</span>
                    </div>
                    <p class="mb-2">Bạn có chắc chắn muốn xoá điểm của</p>
                    <p class="fw-bold text-danger fs-5 mb-0">
                        {{ studentName }}
                    </p>
                </div>

                <div class="modal-footer justify-content-center gap-3">
                    <button class="btn btn-cancel" data-bs-dismiss="modal">
                        <span class="me-1">✕</span> Huỷ bỏ
                    </button>
                    <button class="btn btn-delete" @click="confirm">
                        <span class="me-1">🗑️</span> Xác nhận xoá
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
const props = defineProps<{
    studentName: string;
    scoreId: number;
}>();

const emit = defineEmits<{
    (e: "confirm", scoreId: number): void;
}>();

function confirm() {
    emit("confirm", props.scoreId);
}
</script>

<style scoped>
.delete-modal {
    border: none;
    border-radius: 20px;
    overflow: hidden;
}

.delete-header {
    background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
    color: #fff;
    border-bottom: none;
    padding: 1rem 1.5rem;
}

.delete-header .modal-title {
    display: flex;
    align-items: center;
    gap: 10px;
    font-weight: 600;
}

.warning-icon {
    font-size: 1.25rem;
}

.delete-icon-wrapper {
    width: 80px;
    height: 80px;
    background: linear-gradient(
        135deg,
        rgba(239, 68, 68, 0.1),
        rgba(220, 38, 38, 0.1)
    );
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0 auto;
}

.delete-icon {
    font-size: 2.5rem;
}

.modal-footer {
    border-top: 1px solid #f1f5f9;
    padding: 1rem 1.5rem;
}

.btn-cancel {
    background: #f1f5f9;
    color: #64748b;
    border: none;
    padding: 0.75rem 1.5rem;
    border-radius: 10px;
    font-weight: 500;
    transition: all 0.2s ease;
}

.btn-cancel:hover {
    background: #e2e8f0;
}

.btn-delete {
    background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
    color: white;
    border: none;
    padding: 0.75rem 1.5rem;
    border-radius: 10px;
    font-weight: 500;
    transition: all 0.2s ease;
}

.btn-delete:hover {
    transform: translateY(-1px);
    box-shadow: 0 4px 12px rgba(239, 68, 68, 0.3);
}
</style>
