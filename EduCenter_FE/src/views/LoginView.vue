<template>
    <div
        class="bg-primary bg-gradient min-vh-100 d-flex align-items-center justify-content-center"
    >
        <div class="card shadow-lg p-4" style="max-width: 380px; width: 100%">
            <h2 class="text-center mb-4 text-primary fw-bold">ĐĂNG NHẬP</h2>

            <form @submit.prevent="handleLogin">
                <div class="mb-3">
                    <label class="form-label">Tên đăng nhập</label>
                    <input class="form-control" v-model="username" />
                </div>

                <div class="mb-3">
                    <label class="form-label">Mật khẩu</label>
                    <div class="input-group">
                        <input
                            class="form-control"
                            :type="showPassword ? 'text' : 'password'"
                            v-model="password"
                        />
                        <button
                            type="button"
                            class="btn btn-outline-secondary"
                            @click="togglePassword"
                        >
                            {{ showPassword ? "Ẩn" : "Hiện" }}
                        </button>
                    </div>
                </div>

                <div class="form-check mb-3">
                    <input
                        class="form-check-input"
                        type="checkbox"
                        v-model="rememberMe"
                    />
                    <label class="form-check-label">Ghi nhớ tài khoản</label>
                </div>

                <button class="btn btn-primary w-100">Đăng nhập</button>

                <div class="mt-3 text-center">
                    <span :class="messageClass">{{ message }}</span>
                </div>
            </form>
        </div>
    </div>
</template>
<script setup lang="ts">
import { useLogin } from "../composables/useLogin";

const {
    username,
    password,
    rememberMe,
    showPassword,
    message,
    messageClass,
    togglePassword,
    handleLogin,
} = useLogin();
</script>
