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
                        <span class="badge bg-success">{{ c.status }}</span>
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
</script>
