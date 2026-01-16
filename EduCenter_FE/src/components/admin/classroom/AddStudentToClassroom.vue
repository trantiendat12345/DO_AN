<template>
    <div class="modal fade" id="addStudentToClassroomModal" tabindex="-1">
        <div class="modal-dialog modal-md">
            <form class="modal-content" @submit.prevent="submit">
                <!-- HEADER -->
                <div class="modal-header">
                    <h5 class="modal-title">Thêm học viên vào lớp</h5>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                    ></button>
                </div>

                <!-- BODY -->
                <div class="modal-body">
                    <div class="row g-3">
                        <!-- Mã học viên -->
                        <div class="col-12">
                            <input
                                v-model="form.studentCode"
                                class="form-control"
                                placeholder="Mã học viên *"
                                required
                            />
                        </div>

                        <!-- Tên lớp học -->
                        <div class="col-12">
                            <input
                                v-model="form.name"
                                class="form-control"
                                placeholder="Tên lớp học *"
                                required
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
                    <button type="submit" class="btn btn-success">
                        Thêm học viên
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
    (
        e: "submit",
        data: {
            studentCode: string;
            name: string;
        }
    ): void;
}>();

/* FORM */
const emptyForm = {
    studentCode: "",
    name: "",
};

const form = ref({ ...emptyForm });

function submit() {
    emit("submit", { ...form.value });
}

function resetForm() {
    form.value = { ...emptyForm };
}

defineExpose({
    resetForm,
});
</script>
