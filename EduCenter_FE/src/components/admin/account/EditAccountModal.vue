<template>
    <div class="modal fade" id="editAccountModal" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <form class="modal-content" @submit.prevent="submit">
                <!-- HEADER -->
                <div class="modal-header">
                    <h5 class="modal-title">Cập nhật tài khoản</h5>
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
                                v-model="form.username"
                                :disabled="true"
                                class="form-control"
                                placeholder="Tên tài khoản *"
                            />
                        </div>

                        <div class="col-md-6">
                            <input
                                v-model="form.password"
                                class="form-control"
                                placeholder="Mật khẩu *"
                                type="password"
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
                                :disabled="true"
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
                        Lưu thay đổi
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, watch } from "vue";
import type { Account } from "../../../types/Account";

/* PROPS */
const props = defineProps<{
    account: Account | null;
}>();

/* EMIT */
const emit = defineEmits<{
    (e: "submit", data: Account): void;
}>();

/* FORM */
const form = ref<Account | null>(null);
/* Khi chọn account → clone vào form */
watch(
    () => props.account,
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
