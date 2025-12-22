<template>
    <div class="modal fade" id="editClassroomModal" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <form class="modal-content" @submit.prevent="submit">
                <!-- HEADER -->
                <div class="modal-header">
                    <h5 class="modal-title">Cập nhật lớp học</h5>
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
                                v-model="form.name"
                                :disabled="true"
                                class="form-control"
                                placeholder="Tên lớp *"
                            />
                        </div>

                        <div class="col-md-6">
                            <input
                                v-model="form.nameCourse"
                                class="form-control"
                                placeholder="Khóa học *"
                                required
                            />
                        </div>

                        <div class="col-md-6">
                            <input
                                v-model.number="form.maxStudent"
                                type="number"
                                class="form-control"
                                placeholder="Số học viên tối đa"
                            />
                        </div>

                        <div class="col-md-6">
                            <input
                                v-model="form.startDate"
                                type="date"
                                class="form-control"
                            />
                        </div>

                        <div class="col-md-6">
                            <input
                                v-model="form.endDate"
                                type="date"
                                class="form-control"
                            />
                        </div>

                        <div class="col-md-6">
                            <select v-model="form.status" class="form-select">
                                <option value="ACTIVE">Đang học</option>
                                <option value="INACTIVE">Tạm dừng</option>
                                <option value="FINISHED">Đã kết thúc</option>
                            </select>
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
import type { Classroom } from "../../types/Classroom";

/* PROPS */
const props = defineProps<{
    classroom: Classroom | null;
}>();

/* EMIT */
const emit = defineEmits<{
    (e: "submit", data: Classroom): void;
}>();

/* FORM */
const form = ref<Classroom | null>(null);

/* Khi chọn lớp → clone vào form */
watch(
    () => props.classroom,
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
