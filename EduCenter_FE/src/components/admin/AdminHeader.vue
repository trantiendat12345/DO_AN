<template>
    <div class="admin-header d-flex flex-wrap mb-4 align-items-center">
        <div class="me-4">
            <h1 class="header-title mb-1">{{ title }}</h1>
            <p class="header-subtitle mb-0">{{ subtitle }}</p>
        </div>

        <div class="flex-grow-1 me-4">
            <div class="search-wrapper">
                <span class="search-icon">🔍</span>
                <input
                    class="form-control search-input"
                    placeholder="Tìm kiếm học sinh, giáo viên, lớp học..."
                />
            </div>
        </div>

        <div class="d-flex align-items-center gap-3">
            <button class="btn notification-btn position-relative">
                <span class="notification-icon">🔔</span>
                <span class="badge notification-badge position-absolute"
                    >3</span
                >
            </button>

            <div class="dropdown">
                <button
                    class="btn p-0 user-dropdown-btn"
                    data-bs-toggle="dropdown"
                >
                    <div class="admin-avatar">{{ userInitial }}</div>
                </button>
                <ul class="dropdown-menu dropdown-menu-end user-dropdown">
                    <li class="dropdown-header-custom">
                        <div class="d-flex align-items-center gap-3 p-2">
                            <div class="admin-avatar-sm">{{ userInitial }}</div>
                            <div>
                                <div class="fw-semibold">
                                    {{ currentUsername }}
                                </div>
                                <small class="text-muted">{{
                                    currentRole
                                }}</small>
                            </div>
                        </div>
                    </li>
                    <li><hr class="dropdown-divider" /></li>
                    <li>
                        <a class="dropdown-item" href="#">
                            <span class="me-2">👤</span> Thông tin cá nhân
                        </a>
                    </li>
                    <li>
                        <a
                            class="dropdown-item dropdown-item-logout"
                            @click="logout"
                        >
                            <span class="me-2">🚪</span> Đăng xuất
                        </a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from "vue";
import { logout } from "../../composables/useLogout";

const currentUsername = ref<string>("");
const currentRole = ref<string>("");

const userInitial = computed(() => {
    return currentUsername.value?.charAt(0).toUpperCase() || "A";
});

defineProps<{
    title?: string;
    subtitle?: string;
}>();

onMounted(() => {
    const username = localStorage.getItem("username") || "";
    const role = localStorage.getItem("role") || "";

    currentUsername.value = username;
    currentRole.value = role === "ROLE_ADMIN" ? "Quản trị viên" : role;
});
</script>

<style scoped>
.admin-header {
    padding: 0.5rem 0;
}

.header-title {
    font-size: 1.5rem;
    font-weight: 700;
    color: #0f172a;
    margin: 0;
}

.header-subtitle {
    color: #64748b;
    font-size: 0.9rem;
}

.search-wrapper {
    position: relative;
    max-width: 400px;
}

.search-icon {
    position: absolute;
    left: 16px;
    top: 50%;
    transform: translateY(-50%);
    font-size: 1rem;
    z-index: 2;
}

.search-input {
    padding: 0.75rem 1rem 0.75rem 2.75rem;
    border-radius: 50px;
    border: 2px solid #e2e8f0;
    background: #ffffff;
    font-size: 0.9rem;
    transition: all 0.2s ease;
}

.search-input:focus {
    border-color: #06b6d4;
    box-shadow: 0 0 0 4px rgba(6, 182, 212, 0.1);
}

.notification-btn {
    width: 44px;
    height: 44px;
    border-radius: 12px;
    background: #ffffff;
    border: 2px solid #e2e8f0;
    display: flex;
    align-items: center;
    justify-content: center;
    transition: all 0.2s ease;
    padding: 0;
}

.notification-btn:hover {
    background: #f0fdfa;
    border-color: #06b6d4;
    transform: scale(1.05);
}

.notification-icon {
    font-size: 1.2rem;
}

.notification-badge {
    top: -4px !important;
    right: -4px !important;
    font-size: 0.7rem;
    padding: 0.3em 0.5em;
    border-radius: 8px;
    background: linear-gradient(135deg, #ef4444, #dc2626) !important;
    box-shadow: 0 2px 8px rgba(239, 68, 68, 0.4);
}

.user-dropdown-btn {
    border: none;
    background: none;
}

.user-dropdown {
    border: none;
    border-radius: 16px;
    box-shadow: 0 10px 40px rgba(0, 0, 0, 0.12);
    padding: 0.5rem;
    min-width: 220px;
}

.dropdown-header-custom {
    padding: 0.5rem;
}

.admin-avatar-sm {
    width: 36px;
    height: 36px;
    border-radius: 10px;
    background: linear-gradient(135deg, #06b6d4, #0891b2);
    color: #fff;
    font-weight: 600;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 0.9rem;
}

.dropdown-item {
    border-radius: 10px;
    padding: 0.65rem 1rem;
    transition: all 0.15s ease;
}

.dropdown-item:hover {
    background: #f0fdfa;
    color: #0891b2;
}

.dropdown-item-logout:hover {
    background: #fef2f2;
    color: #dc2626;
}
</style>
