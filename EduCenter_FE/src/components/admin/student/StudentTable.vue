<!-- src/components/admin/StudentTable.vue -->
<template>
    <div class="table-responsive table-container">
        <table class="table table-striped align-middle">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Mã học viên</th>
                    <th>Họ và tên</th>
                    <th>SĐT</th>
                    <th>Email</th>
                    <th>Trạng thái</th>
                    <th>Trình độ</th>
                    <th>Ghi chú</th>
                    <th width="160">Hành động</th>
                </tr>
            </thead>

            <tbody>
                <tr v-for="(s, i) in students" :key="s.studentCode">
                    <td class="text-muted">{{ page * size + i + 1 }}</td>
                    <td>
                        <span class="code-badge">{{ s.studentCode }}</span>
                    </td>
                    <td>
                        <div class="d-flex align-items-center gap-2">
                            <div class="avatar-sm">
                                {{ s.fullName?.charAt(0) || "H" }}
                            </div>
                            <span class="fw-medium">{{ s.fullName }}</span>
                        </div>
                    </td>
                    <td>{{ s.phone }}</td>
                    <td class="text-muted">{{ s.email }}</td>
                    <td>
                        <span
                            class="badge status-badge"
                            :class="getStatusClass(s.status)"
                        >
                            {{ s.status }}
                        </span>
                    </td>
                    <td>
                        <span class="level-badge">{{ s.level }}</span>
                    </td>
                    <td class="text-muted">{{ s.note }}</td>
                    <td>
                        <div class="action-buttons">
                            <button
                                class="btn btn-action btn-edit"
                                @click="$emit('edit', s)"
                                title="Sửa"
                            >
                                ✏️
                            </button>
                            <button
                                class="btn btn-action btn-delete"
                                @click="$emit('delete', s)"
                                title="Xóa"
                            >
                                🗑️
                            </button>
                        </div>
                    </td>
                </tr>

                <tr v-if="!students.length">
                    <td colspan="9" class="text-center text-muted py-5">
                        <div class="empty-state">
                            <span class="empty-icon">📭</span>
                            <p class="mb-0 mt-2">Không có dữ liệu</p>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup lang="ts">
import type { Student } from "../../../types/Student";

defineProps<{
    students: Student[];
    page: number;
    size: number;
}>();

defineEmits<{
    (e: "edit", student: Student): void;
    (e: "delete", student: Student): void;
}>();

function getStatusClass(status: string) {
    const statusMap: Record<string, string> = {
        "Đang học": "status-active",
        "Nghỉ học": "status-inactive",
        "Hoàn thành": "status-completed",
    };
    return statusMap[status] || "status-default";
}
</script>

<style scoped>
.table-container {
    background: #ffffff;
    border-radius: 16px;
    overflow: hidden;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.04);
}

.code-badge {
    background: linear-gradient(135deg, #f0fdfa, #ecfeff);
    color: #0891b2;
    padding: 0.3rem 0.6rem;
    border-radius: 6px;
    font-size: 0.85rem;
    font-weight: 500;
}

.avatar-sm {
    width: 32px;
    height: 32px;
    border-radius: 8px;
    background: linear-gradient(135deg, #06b6d4, #0891b2);
    color: #fff;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 0.8rem;
    font-weight: 600;
}

.status-badge {
    padding: 0.4rem 0.75rem;
    border-radius: 8px;
    font-size: 0.8rem;
    font-weight: 500;
}

.status-active {
    background: linear-gradient(
        135deg,
        rgba(34, 197, 94, 0.15),
        rgba(22, 163, 74, 0.15)
    );
    color: #16a34a;
}

.status-inactive {
    background: linear-gradient(
        135deg,
        rgba(239, 68, 68, 0.15),
        rgba(220, 38, 38, 0.15)
    );
    color: #dc2626;
}

.status-completed {
    background: linear-gradient(
        135deg,
        rgba(99, 102, 241, 0.15),
        rgba(79, 70, 229, 0.15)
    );
    color: #4f46e5;
}

.status-default {
    background: linear-gradient(
        135deg,
        rgba(100, 116, 139, 0.15),
        rgba(71, 85, 105, 0.15)
    );
    color: #475569;
}

.level-badge {
    background: #f1f5f9;
    color: #475569;
    padding: 0.3rem 0.6rem;
    border-radius: 6px;
    font-size: 0.85rem;
}

.action-buttons {
    display: flex;
    gap: 6px;
}

.btn-action {
    width: 36px;
    height: 36px;
    padding: 0;
    border-radius: 10px;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: all 0.2s ease;
    border: none;
}

.btn-edit {
    background: linear-gradient(
        135deg,
        rgba(245, 158, 11, 0.15),
        rgba(217, 119, 6, 0.15)
    );
    color: #d97706;
}

.btn-edit:hover {
    background: linear-gradient(135deg, #f59e0b, #d97706);
    color: #fff;
    transform: scale(1.05);
}

.btn-delete {
    background: linear-gradient(
        135deg,
        rgba(239, 68, 68, 0.15),
        rgba(220, 38, 38, 0.15)
    );
    color: #dc2626;
}

.btn-delete:hover {
    background: linear-gradient(135deg, #ef4444, #dc2626);
    color: #fff;
    transform: scale(1.05);
}

.empty-state {
    padding: 2rem;
}

.empty-icon {
    font-size: 3rem;
    opacity: 0.5;
}

.fw-medium {
    font-weight: 500;
}
</style>
