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
                        <span class="badge bg-success">{{ t.status }}</span>
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
</script>
