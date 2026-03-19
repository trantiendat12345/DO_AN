<template>
    <div class="table-responsive">
        <table class="table table-striped align-middle">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Tên lớp</th>
                    <th>Khóa học</th>
                    <th>Ngày bắt đầu</th>
                    <th>Ngày kết thúc</th>
                    <th>Trạng thái</th>
                    <th>Số học viên</th>
                    <th width="150">Hành động</th>
                    <th width="120"></th>
                </tr>
            </thead>

            <tbody>
                <tr v-for="(c, i) in classrooms" :key="c.name">
                    <td>{{ page * size + i + 1 }}</td>
                    <td>{{ c.name }}</td>
                    <td>{{ c.nameCourse }}</td>
                    <td>{{ c.startDate }}</td>
                    <td>{{ c.endDate }}</td>
                    <td>
                        <span
                            class="status-badge"
                            :class="getStatusClass(c.status)"
                        >
                            {{ getStatusLabel(c.status) }}
                        </span>
                    </td>
                    <td>{{ `${c.currentStudent} / ${c.maxStudent}` }}</td>
                    <td>
                        <button
                            class="btn btn-sm btn-warning me-1"
                            @click="$emit('edit', c)"
                        >
                            Sửa
                        </button>
                        <button
                            class="btn btn-sm btn-danger"
                            @click="$emit('delete', c)"
                        >
                            Xóa
                        </button>
                    </td>
                    <td>
                        <button
                            class="btn btn-sm btn-info"
                            @click="$emit('detail', c)"
                        >
                            Chi tiết
                        </button>
                    </td>
                </tr>

                <tr v-if="!classrooms.length">
                    <td colspan="8" class="text-center text-muted">
                        Không có dữ liệu
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup lang="ts">
import type { Classroom } from "../../../types/Classroom";

defineProps<{
    classrooms: Classroom[];
    page: number;
    size: number;
}>();

defineEmits(["edit", "delete", "detail"]);

function getStatusClass(status: string) {
    const statusMap: Record<string, string> = {
        UPCOMING: "status-upcoming",
        ACTIVE: "status-active",
        PAUSED: "status-paused",
        COMPLETED: "status-completed",
        CANCELLED: "status-cancelled",
    };
    return statusMap[status] || "status-default";
}

function getStatusLabel(status: string) {
    const labelMap: Record<string, string> = {
        UPCOMING: "Chưa bắt đầu",
        ACTIVE: "Đang diễn ra",
        PAUSED: "Tạm dừng",
        COMPLETED: "Đã kết thúc",
        CANCELLED: "Đã hủy",
    };
    return labelMap[status] || status;
}
</script>

<style scoped>
.status-badge {
    padding: 0.35rem 0.7rem;
    border-radius: 8px;
    font-size: 0.8rem;
    font-weight: 500;
    display: inline-block;
}
.status-upcoming {
    background: rgba(251, 191, 36, 0.15);
    color: #d97706;
}
.status-active {
    background: rgba(34, 197, 94, 0.15);
    color: #16a34a;
}
.status-paused {
    background: rgba(249, 115, 22, 0.15);
    color: #ea580c;
}
.status-completed {
    background: rgba(99, 102, 241, 0.15);
    color: #4f46e5;
}
.status-cancelled {
    background: rgba(239, 68, 68, 0.15);
    color: #dc2626;
}
.status-default {
    background: rgba(100, 116, 139, 0.15);
    color: #475569;
}
</style>
