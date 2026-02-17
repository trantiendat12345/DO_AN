<template>
    <div class="table-responsive table-container">
        <table class="table table-striped align-middle">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Mã học viên</th>
                    <th>Họ và tên</th>
                    <th>Lớp học</th>
                    <th>Loại điểm</th>
                    <th>Điểm</th>
                    <th>Ghi chú</th>
                    <th width="160">Hành động</th>
                </tr>
            </thead>

            <tbody>
                <tr v-for="(s, i) in scores" :key="s.id">
                    <td class="text-muted">{{ page * size + i + 1 }}</td>
                    <td>
                        <span class="code-badge">{{ s.studentCode }}</span>
                    </td>
                    <td>
                        <div class="d-flex align-items-center gap-2">
                            <div class="avatar-sm">
                                {{ s.studentName?.charAt(0) || "H" }}
                            </div>
                            <span class="fw-medium">{{ s.studentName }}</span>
                        </div>
                    </td>
                    <td>
                        <span class="classroom-badge">{{
                            s.classroomName
                        }}</span>
                    </td>
                    <td>
                        <span
                            class="badge type-badge"
                            :class="getTypeClass(s.type)"
                        >
                            {{ getTypeLabel(s.type) }}
                        </span>
                    </td>
                    <td>
                        <span
                            class="score-value"
                            :class="getScoreClass(s.value)"
                        >
                            {{ s.value.toFixed(1) }}
                        </span>
                    </td>
                    <td class="text-muted">{{ s.note || "-" }}</td>
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

                <tr v-if="!scores.length">
                    <td colspan="8" class="text-center text-muted py-5">
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
import type { Score, ScoreType } from "../../../types/Score";
import { ScoreTypeLabels } from "../../../types/Score";

defineProps<{
    scores: Score[];
    page: number;
    size: number;
}>();

defineEmits<{
    (e: "edit", score: Score): void;
    (e: "delete", score: Score): void;
}>();

function getTypeLabel(type: ScoreType): string {
    return ScoreTypeLabels[type] || type;
}

function getTypeClass(type: ScoreType): string {
    const typeMap: Record<ScoreType, string> = {
        HOMEWORK: "type-homework",
        TEST: "type-test",
        MIDTERM: "type-midterm",
        FINAL: "type-final",
    };
    return typeMap[type] || "type-default";
}

function getScoreClass(value: number): string {
    if (value >= 8) return "score-excellent";
    if (value >= 6.5) return "score-good";
    if (value >= 5) return "score-average";
    return "score-poor";
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

.classroom-badge {
    background: linear-gradient(135deg, #fef3c7, #fde68a);
    color: #92400e;
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

.type-badge {
    padding: 0.4rem 0.75rem;
    border-radius: 8px;
    font-size: 0.8rem;
    font-weight: 500;
}

.type-homework {
    background: linear-gradient(
        135deg,
        rgba(59, 130, 246, 0.15),
        rgba(37, 99, 235, 0.15)
    );
    color: #2563eb;
}

.type-test {
    background: linear-gradient(
        135deg,
        rgba(249, 115, 22, 0.15),
        rgba(234, 88, 12, 0.15)
    );
    color: #ea580c;
}

.type-midterm {
    background: linear-gradient(
        135deg,
        rgba(139, 92, 246, 0.15),
        rgba(124, 58, 237, 0.15)
    );
    color: #7c3aed;
}

.type-final {
    background: linear-gradient(
        135deg,
        rgba(236, 72, 153, 0.15),
        rgba(219, 39, 119, 0.15)
    );
    color: #db2777;
}

.score-value {
    font-weight: 700;
    font-size: 1rem;
    padding: 0.3rem 0.6rem;
    border-radius: 6px;
}

.score-excellent {
    color: #16a34a;
    background: rgba(34, 197, 94, 0.1);
}

.score-good {
    color: #2563eb;
    background: rgba(59, 130, 246, 0.1);
}

.score-average {
    color: #d97706;
    background: rgba(245, 158, 11, 0.1);
}

.score-poor {
    color: #dc2626;
    background: rgba(239, 68, 68, 0.1);
}

.action-buttons {
    display: flex;
    gap: 0.5rem;
}

.btn-action {
    width: 36px;
    height: 36px;
    border-radius: 8px;
    display: flex;
    align-items: center;
    justify-content: center;
    border: none;
    transition: all 0.2s ease;
}

.btn-edit {
    background: linear-gradient(
        135deg,
        rgba(59, 130, 246, 0.1),
        rgba(37, 99, 235, 0.1)
    );
}

.btn-edit:hover {
    background: linear-gradient(
        135deg,
        rgba(59, 130, 246, 0.2),
        rgba(37, 99, 235, 0.2)
    );
    transform: translateY(-2px);
}

.btn-delete {
    background: linear-gradient(
        135deg,
        rgba(239, 68, 68, 0.1),
        rgba(220, 38, 38, 0.1)
    );
}

.btn-delete:hover {
    background: linear-gradient(
        135deg,
        rgba(239, 68, 68, 0.2),
        rgba(220, 38, 38, 0.2)
    );
    transform: translateY(-2px);
}

.empty-state {
    padding: 2rem;
}

.empty-icon {
    font-size: 3rem;
    opacity: 0.5;
}
</style>
