<template>
    <div class="modal fade" id="addAccountModal" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <form class="modal-content" @submit.prevent="submit">
                <!-- HEADER -->
                <div class="modal-header">
                    <h5 class="modal-title">Thêm tài khoản</h5>
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
                                v-model="form.username"
                                class="form-control"
                                placeholder="Tên tài khoản *"
                                required
                            />
                        </div>

                        <div class="col-md-6">
                            <input
                                v-model="form.password"
                                class="form-control"
                                placeholder="Mật khẩu *"
                                type="password"
                                required
                            />
                        </div>

                        <div class="col-md-6">
                            <select v-model="form.roleName" class="form-select">
                                <option value="">Chọn vai trò *</option>
                                <option value="ADMIN">ADMIN</option>
                                <option value="STUDENT">Học sinh</option>
                                <option value="TEACHER">Giáo viên</option>
                            </select>
                        </div>

                        <div class="col-md-6">
                            <select v-model="form.userType" class="form-select">
                                <option value="">Chọn kiểu người dùng *</option>
                                <option value="ADMIN">ADMIN</option>
                                <option value="STUDENT">Học sinh</option>
                                <option value="TEACHER">Giáo viên</option>
                            </select>
                        </div>

                        <div class="col-md-6">
                            <input
                                v-model="form.userCode"
                                class="form-control"
                                placeholder="Mã người dùng"
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
                        Lưu tài khoản
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
    username: "",
    password: "",
    roleName: "",
    userType: "",
    userCode: "",
};

const form = ref({ ...emptyForm });

function submit() {
    emit("submit", { ...form.value });
}
</script>
