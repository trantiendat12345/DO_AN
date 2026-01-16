<template>
    <div class="modal fade show d-block" tabindex="-1">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Danh sách học viên – {{ name }}</h5>
                    <button class="btn-close" @click="$emit('close')" />
                </div>

                <div class="modal-body">
                    <div class="table-responsive">
                        <table class="table table-striped align-middle">
                            <thead class="table-light">
                                <tr>
                                    <th>#</th>
                                    <th>Mã HV</th>
                                    <th>Họ tên</th>
                                    <th>Email</th>
                                    <th>SĐT</th>
                                    <th>Giới tính</th>
                                    <th>Trạng thái</th>
                                </tr>
                            </thead>

                            <tbody>
                                <tr
                                    v-for="(s, i) in students"
                                    :key="s.studentCode"
                                >
                                    <td>{{ page * size + i + 1 }}</td>
                                    <td>{{ s.studentCode }}</td>
                                    <td>{{ s.fullName }}</td>
                                    <td>{{ s.email }}</td>
                                    <td>{{ s.phone }}</td>
                                    <td>{{ s.gender }}</td>
                                    <td>
                                        <span class="badge bg-success">
                                            {{ s.status }}
                                        </span>
                                    </td>
                                </tr>

                                <tr v-if="!students.length">
                                    <td
                                        colspan="7"
                                        class="text-center text-muted"
                                    >
                                        Không có học viên
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
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
defineProps<{
    students: any[];
    page: number;
    size: number;
    name: string;
}>();

defineEmits(["close"]);
</script>
