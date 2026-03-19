<template>
    <div class="table-responsive">
        <table class="table table-striped align-middle">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Mã GV</th>
                    <th>Họ tên</th>
                    <th>SĐT</th>
                    <th>Email</th>
                    <th>Trạng thái</th>
                    <th>Ghi chú</th>
                    <th width="120">Hành động</th>
                </tr>
            </thead>

            <tbody>
                <tr v-for="(t, i) in teachers" :key="t.teacherCode">
                    <td>{{ page * size + i + 1 }}</td>
                    <td>{{ t.teacherCode }}</td>
                    <td>{{ t.fullName }}</td>
                    <td>{{ t.phone }}</td>
                    <td>{{ t.email }}</td>
                    <td>
                        <span
                            class="status-badge"
                            :class="getStatusClass(t.status)"
                        >
                            {{ getStatusLabel(t.status) }}
                        </span>
                    </td>
                    <td>{{ t.note }}</td>
                    <td>
                        <div class="btn-group btn-group-sm">
                            <button
                                class="btn btn-sm btn-warning me-1"
                                @click="$emit('edit', t)"
                            >
                                Sửa
                            </button>
                            <button
                                class="btn btn-sm btn-danger"
                                @click="$emit('delete', t)"
                            >
                                Xóa
                            </button>
                        </div>
                    </td>
                </tr>

                <tr v-if="!teachers.length">
                    <td colspan="7" class="text-center text-muted">
                        Không có dữ liệu
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup lang="ts">
import type { Teacher } from "../../../types/Teacher";

defineProps<{
    teachers: Teacher[];
    page: number;
    size: number;
}>();

defineEmits<{
    (e: "edit", teacher: Teacher): void;
    (e: "delete", teacher: Teacher): void;
}>();

function getStatusClass(status: string) {
    const statusMap: Record<string, string> = {
        ACTIVE: "status-active",
        INACTIVE: "status-inactive",
        ON_LEAVE: "status-on-leave",
        RETIRED: "status-retired",
        TERMINATED: "status-terminated",
    };
    return statusMap[status] || "status-default";
}

function getStatusLabel(status: string) {
    const labelMap: Record<string, string> = {
        ACTIVE: "Đang dạy",
        INACTIVE: "Ngưng hoạt động",
        ON_LEAVE: "Nghỉ phép",
        RETIRED: "Nghỉ hưu",
        TERMINATED: "Đã nghỉ việc",
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
.status-active {
    background: rgba(34, 197, 94, 0.15);
    color: #16a34a;
}
.status-inactive {
    background: rgba(100, 116, 139, 0.15);
    color: #475569;
}
.status-on-leave {
    background: rgba(251, 191, 36, 0.15);
    color: #d97706;
}
.status-retired {
    background: rgba(99, 102, 241, 0.15);
    color: #4f46e5;
}
.status-terminated {
    background: rgba(239, 68, 68, 0.15);
    color: #dc2626;
}
.status-default {
    background: rgba(100, 116, 139, 0.15);
    color: #475569;
}
</style>
