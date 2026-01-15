<template>
    <div class="modal fade" id="addCourseModal" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <form class="modal-content" @submit.prevent="submit">
                <!-- HEADER -->
                <div class="modal-header">
                    <h5 class="modal-title">Thêm khoá học</h5>
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
                                v-model="form.nameCourse"
                                class="form-control"
                                placeholder="Tên khoá học *"
                                required
                            />
                        </div>

                        <div class="col-md-6">
                            <input
                                v-model.number="form.description"
                                class="form-control"
                                placeholder="Mô tả"
                            />
                        </div>

                        <div class="col-md-6">
                            <input
                                v-model="form.duration"
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
    nameCourse: "",
    description: "",
    duration: "",
    price: "",
};

const form = ref({ ...emptyForm });

function submit() {
    emit("submit", { ...form.value });
}
</script>
