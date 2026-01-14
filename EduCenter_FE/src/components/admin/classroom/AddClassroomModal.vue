<template>
    <div class="modal fade" id="addClassroomModal" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <form class="modal-content" @submit.prevent="submit">
                <!-- HEADER -->
                <div class="modal-header">
                    <h5 class="modal-title">Thêm lớp học</h5>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                    ></button>
                </div>

                <!-- BODY -->
                <div class="modal-body">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <input
                                v-model="form.name"
                                class="form-control"
                                placeholder="Tên lớp *"
                                required
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
                                min="1"
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
                            <select v-model="form.status" class="form-select">
                                <option value="">Chọn trạng thái</option>
                                <option value="UPCOMING">Chưa bắt đầu</option>
                                <option value="ACTIVE">Đang học</option>
                                <option value="PAUSED">Tạm dừng</option>
                                <option value="COMPLETED">Đã kết thúc</option>
                                <option value="CANCELLED">Đã hủy</option>
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
                        Lưu lớp học
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref } from "vue";

/* EMIT */
const emit = defineEmits<{
    (e: "submit", data: any): void;
}>();

/* FORM */
const emptyForm = {
    name: "",
    nameCourse: "",
    teacherName: "",
    maxStudent: 0,
    startDate: "",
    endDate: "",
    status: "",
};

const form = ref({ ...emptyForm });

function submit() {
    emit("submit", { ...form.value });
}
</script>
