<template>
    <div class="table-responsive">
        <table class="table table-striped align-middle">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Tên lớp</th>
                    <th>Giáo viên chính</th>
                    <th>Giáo viên phụ</th>
                    <!-- <th width="150">Hành động</th> -->
                </tr>
            </thead>

            <tbody>
                <tr v-for="(at, i) in assignTeacher" :key="at.name">
                    <td>{{ page * size + i + 1 }}</td>
                    <td>{{ at.name }}</td>
                    <td>
                        <a
                            v-if="at.mainTeacher"
                            href="#"
                            class="text-black text-decoration-none"
                            @click.prevent="
                                $emit('view-main-teacher', at.mainTeacher)
                            "
                        >
                            {{ at.mainTeacher.fullName }}
                        </a>
                        <span v-else class="text-black">Chưa phân công</span>
                    </td>
                    <td>
                        <button
                            class="btn btn-sm btn-info"
                            @click="
                                $emit(
                                    'view-assistant-teachers',
                                    at.assistantTeacher || [],
                                )
                            "
                        >
                            Chi tiết
                        </button>
                    </td>
                    <!-- <td>
                        <button
                            class="btn btn-sm btn-warning me-1"
                            @click="$emit('edit', at)"
                        >
                            Sửa
                        </button>
                        <button
                            class="btn btn-sm btn-danger"
                            @click="$emit('delete', at)"
                        >
                            Xóa
                        </button>
                    </td> -->
                </tr>

                <tr v-if="!assignTeacher.length">
                    <td colspan="8" class="text-center text-muted">
                        Không có dữ liệu
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup lang="ts">
import type { AssignTeacher } from "../../../types/AssignTeacher";

defineProps<{
    assignTeacher: AssignTeacher[];
    page: number;
    size: number;
}>();

defineEmits(["edit", "delete", "view-main-teacher", "view-assistant-teachers"]);
</script>
