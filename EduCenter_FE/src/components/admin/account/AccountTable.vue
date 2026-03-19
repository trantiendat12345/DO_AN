<template>
    <div class="table-responsive">
        <table class="table table-striped align-middle">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Tài khoản</th>
                    <th>Vai trò</th>
                    <th>Mã người dùng</th>
                    <th>Phân loại</th>
                    <th>Trạng thái</th>
                    <th width="150">Hành động</th>
                </tr>
            </thead>

            <tbody>
                <tr v-for="(a, i) in account" :key="a.username">
                    <td>{{ page * size + i + 1 }}</td>
                    <td>{{ a.username }}</td>
                    <td>{{ a.roleName }}</td>
                    <td>{{ a.userCode }}</td>
                    <td>{{ a.userType }}</td>
                    <td>
                        <span
                            class="status-badge"
                            :class="getStatusClass(a.accountStatus)"
                        >
                            {{ getStatusLabel(a.accountStatus) }}
                        </span>
                    </td>
                    <td>
                        <button
                            class="btn btn-sm btn-warning me-1"
                            @click="$emit('edit', a)"
                        >
                            Sửa
                        </button>
                        <button
                            class="btn btn-sm btn-danger"
                            @click="$emit('delete', a)"
                        >
                            Xóa
                        </button>
                    </td>
                </tr>

                <tr v-if="!account.length">
                    <td colspan="8" class="text-center text-muted">
                        Không có dữ liệu
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup lang="ts">
import type { Account } from "../../../types/Account";

defineProps<{
    account: Account[];
    page: number;
    size: number;
}>();

defineEmits(["edit", "delete"]);

function getStatusClass(status: string) {
    const statusMap: Record<string, string> = {
        ACTIVE: "status-active",
        INACTIVE: "status-inactive",
        LOCKED: "status-locked",
    };
    return statusMap[status] || "status-default";
}

function getStatusLabel(status: string) {
    const labelMap: Record<string, string> = {
        ACTIVE: "Hoạt động",
        INACTIVE: "Không hoạt động",
        LOCKED: "Đã khóa",
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
.status-locked {
    background: rgba(239, 68, 68, 0.15);
    color: #dc2626;
}
.status-default {
    background: rgba(100, 116, 139, 0.15);
    color: #475569;
}
</style>
