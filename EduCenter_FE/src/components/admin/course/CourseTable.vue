<template>
    <div class="table-responsive">
        <table class="table table-striped align-middle">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Tên khoá học</th>
                    <th>Mô tả</th>
                    <th>Thời gian học (buổi)</th>
                    <th>Giá khoá học</th>
                    <th width="150">Hành động</th>
                </tr>
            </thead>

            <tbody>
                <tr v-for="(a, i) in course" :key="a.nameCourse">
                    <td>{{ page * size + i + 1 }}</td>
                    <td>{{ a.nameCourse }}</td>
                    <td>{{ a.description }}</td>
                    <td>{{ a.duration }}</td>
                    <td>{{ formatPrice(a.price) }}</td>
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

                <tr v-if="!course.length">
                    <td colspan="8" class="text-center text-muted">
                        Không có dữ liệu
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup lang="ts">
import type { Course } from "../../../types/Course";
import { formatPrice } from "../../../util/formats";

defineProps<{
    course: Course[];
    page: number;
    size: number;
}>();

defineEmits(["edit", "delete"]);
</script>
