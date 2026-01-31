<template>
    <div
        class="login-container min-vh-100 d-flex align-items-center justify-content-center"
    >
        <div class="login-card card p-4 p-md-5">
            <!-- Logo -->
            <div class="text-center mb-4">
                <div class="login-logo mb-3">🎓</div>
                <h2 class="login-title mb-1">English Center</h2>
                <p class="login-subtitle">Đăng nhập để tiếp tục</p>
            </div>

            <form @submit.prevent="handleLogin">
                <div class="mb-4">
                    <label class="form-label">Tên đăng nhập</label>
                    <div class="input-icon-wrapper">
                        <span class="input-icon">👤</span>
                        <input
                            class="form-control form-control-lg ps-5"
                            v-model="username"
                            placeholder="Nhập tên đăng nhập"
                        />
                    </div>
                </div>

                <div class="mb-4">
                    <label class="form-label">Mật khẩu</label>
                    <div class="input-icon-wrapper">
                        <span class="input-icon">🔒</span>
                        <input
                            class="form-control form-control-lg ps-5"
                            :type="showPassword ? 'text' : 'password'"
                            v-model="password"
                            placeholder="Nhập mật khẩu"
                        />
                        <button
                            type="button"
                            class="btn-toggle-password"
                            @click="togglePassword"
                        >
                            {{ showPassword ? "👁️" : "👁️‍🗨️" }}
                        </button>
                    </div>
                </div>

                <div class="form-check mb-4">
                    <input
                        class="form-check-input"
                        type="checkbox"
                        v-model="rememberMe"
                        id="rememberMe"
                    />
                    <label class="form-check-label" for="rememberMe"
                        >Ghi nhớ tài khoản</label
                    >
                </div>

                <button class="btn btn-primary btn-lg w-100 mb-3">
                    Đăng nhập
                </button>

                <div
                    v-if="message"
                    class="alert"
                    :class="messageClass"
                    role="alert"
                >
                    {{ message }}
                </div>
            </form>

            <div class="text-center mt-4">
                <small class="text-muted"
                    >© 2025 English Center Management System</small
                >
            </div>
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

<style scoped>
.login-container {
    background: linear-gradient(135deg, #0f172a 0%, #1e293b 50%, #0f172a 100%);
    position: relative;
    overflow: hidden;
}

.login-container::before {
    content: "";
    position: absolute;
    top: -50%;
    right: -50%;
    width: 100%;
    height: 100%;
    background: radial-gradient(
        circle,
        rgba(6, 182, 212, 0.15) 0%,
        transparent 70%
    );
    pointer-events: none;
}

.login-container::after {
    content: "";
    position: absolute;
    bottom: -50%;
    left: -50%;
    width: 100%;
    height: 100%;
    background: radial-gradient(
        circle,
        rgba(99, 102, 241, 0.1) 0%,
        transparent 70%
    );
    pointer-events: none;
}

.login-card {
    max-width: 420px;
    width: 100%;
    border: none;
    border-radius: 24px;
    background: rgba(255, 255, 255, 0.98);
    box-shadow:
        0 25px 80px rgba(0, 0, 0, 0.3),
        0 10px 30px rgba(6, 182, 212, 0.1);
    position: relative;
    z-index: 1;
}

.login-logo {
    font-size: 3.5rem;
    animation: bounce 2s ease-in-out infinite;
}

@keyframes bounce {
    0%,
    100% {
        transform: translateY(0);
    }
    50% {
        transform: translateY(-10px);
    }
}

.login-title {
    font-weight: 700;
    color: #0f172a;
    font-size: 1.75rem;
}

.login-subtitle {
    color: #64748b;
    font-size: 0.95rem;
    margin-bottom: 0;
}

.input-icon-wrapper {
    position: relative;
}

.input-icon {
    position: absolute;
    left: 16px;
    top: 50%;
    transform: translateY(-50%);
    font-size: 1.1rem;
    z-index: 2;
}

.form-control-lg {
    padding: 0.875rem 1rem 0.875rem 3rem;
    font-size: 1rem;
    border-radius: 12px;
    border: 2px solid #e2e8f0;
    transition: all 0.2s ease;
}

.form-control-lg:focus {
    border-color: #06b6d4;
    box-shadow: 0 0 0 4px rgba(6, 182, 212, 0.15);
}

.btn-toggle-password {
    position: absolute;
    right: 12px;
    top: 50%;
    transform: translateY(-50%);
    background: none;
    border: none;
    padding: 4px 8px;
    cursor: pointer;
    font-size: 1.1rem;
    opacity: 0.6;
    transition: opacity 0.2s ease;
}

.btn-toggle-password:hover {
    opacity: 1;
}

.btn-primary {
    background: linear-gradient(135deg, #06b6d4 0%, #0891b2 100%);
    border: none;
    border-radius: 12px;
    font-weight: 600;
    padding: 0.875rem;
    box-shadow: 0 4px 16px rgba(6, 182, 212, 0.35);
    transition: all 0.3s ease;
}

.btn-primary:hover {
    background: linear-gradient(135deg, #0891b2 0%, #0e7490 100%);
    box-shadow: 0 6px 20px rgba(6, 182, 212, 0.45);
    transform: translateY(-2px);
}

.form-label {
    font-weight: 600;
    color: #374151;
    font-size: 0.9rem;
    margin-bottom: 0.5rem;
}

.form-check-input:checked {
    background-color: #06b6d4;
    border-color: #06b6d4;
}

.alert {
    border-radius: 12px;
    font-size: 0.9rem;
    padding: 0.75rem 1rem;
}
</style>
