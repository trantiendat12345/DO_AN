<template>
    <div class="modal fade show d-block" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">{{ title }}</h5>
                    <button class="btn-close" @click="$emit('close')" />
                </div>

                <div class="modal-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Mã GV</th>
                                <th>Họ tên</th>
                                <th>Email</th>
                                <th>SĐT</th>
                                <th>Trạng thái</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(t, i) in teachers" :key="t.teacherCode">
                                <td>{{ i + 1 }}</td>
                                <td>{{ t.teacherCode }}</td>
                                <td>{{ t.fullName }}</td>
                                <td>{{ t.email }}</td>
                                <td>{{ t.phone }}</td>
                                <td>
                                    <span class="badge bg-success">
                                        {{ t.status }}
                                    </span>
                                </td>
                            </tr>

                            <tr v-if="!teachers.length">
                                <td colspan="6" class="text-center text-muted">
                                    Không có dữ liệu
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <div class="modal-footer">
                    <button class="btn btn-secondary" @click="$emit('close')">
                        Đóng
                    </button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal-backdrop fade show"></div>
</template>

<script setup lang="ts">
import type { Teacher } from "../../../types/Teacher";

defineProps<{
    title: string;
    teachers: Teacher[];
}>();

defineEmits(["close"]);
</script>
