<template>
    <div class="modal fade" id="editCourseModal" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <form class="modal-content" @submit.prevent="submit">
                <!-- HEADER -->
                <div class="modal-header">
                    <h5 class="modal-title">Cập nhật khoá học</h5>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                    ></button>
                </div>

                <!-- BODY -->
                <div class="modal-body" v-if="form">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <input
                                v-model="form.nameCourse"
                                class="form-control"
                                placeholder="Tên khoá học *"
                                :disabled="true"
                            />
                        </div>

                        <div class="col-md-6">
                            <input
                                v-model="form.description"
                                class="form-control"
                                placeholder="Mô tả"
                            />
                        </div>

                        <div class="col-md-6">
                            <input
                                v-model.number="form.duration"
                                type="number"
                                class="form-control"
                                placeholder="Thời lượng (buổi)"
                            />
                        </div>

                        <div class="col-md-6">
                            <input
                                v-model.number="form.price"
                                type="number"
                                class="form-control"
                                placeholder="Giá khoá học"
                            />
                        </div>
                    </div>
                </div>

                <!-- FOOTER -->
                <div class="modal-footer">
                    <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                    >
                        Hủy
                    </button>
                    <button type="submit" class="btn btn-primary">
                        Lưu thay đổi
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, watch } from "vue";
import type { Course } from "../../../types/Course";

/* PROPS */
const props = defineProps<{
    course: Course | null;
}>();

/* EMIT */
const emit = defineEmits<{
    (e: "submit", data: Course): void;
}>();

/* FORM */
const form = ref<Course | null>(null);
/* Khi chọn lớp → clone vào form */
watch(
    () => props.course,
    (val) => {
        if (val) {
            form.value = { ...val };
        }
    },
    { immediate: true }
);

function submit() {
    if (!form.value) return;
    emit("submit", form.value);
}
</script>
