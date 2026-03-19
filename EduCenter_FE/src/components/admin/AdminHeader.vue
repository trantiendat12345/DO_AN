<template>
    <div class="admin-header d-flex flex-wrap mb-4 align-items-center">
        <div class="me-4">
            <h1 class="header-title mb-1">{{ title }}</h1>
            <p class="header-subtitle mb-0">{{ subtitle }}</p>
        </div>

        <div class="flex-grow-1 me-4">
            <div class="search-wrapper" ref="searchWrapperRef">
                <span class="search-icon">🔍</span>
                <input
                    class="form-control search-input"
                    placeholder="Tìm kiếm học sinh, giáo viên, lớp học..."
                    v-model="searchQuery"
                    @input="onSearchInput"
                    @keyup.enter="onSearchEnter"
                    @focus="showDropdown = true"
                />
                <div
                    v-if="showDropdown && searchQuery.trim()"
                    class="search-dropdown"
                >
                    <div class="search-dropdown-header">
                        Tìm "{{ searchQuery }}" trong:
                    </div>
                    <a
                        v-for="item in searchTargets"
                        :key="item.path"
                        class="search-dropdown-item"
                        @mousedown.prevent="navigateToSearch(item.path)"
                    >
                        <span class="search-dropdown-icon">{{
                            item.icon
                        }}</span>
                        <span>{{ item.label }}</span>
                    </a>
                </div>
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
import { ref, computed, onMounted, onBeforeUnmount } from "vue";
import { useRouter } from "vue-router";
import { logout } from "../../composables/useLogout";

const router = useRouter();

const currentUsername = ref<string>("");
const currentRole = ref<string>("");
const searchQuery = ref("");
const showDropdown = ref(false);
const searchWrapperRef = ref<HTMLElement | null>(null);

const searchTargets = [
    { path: "/admin/accounts", label: "Tài khoản", icon: "🔑" },
    { path: "/admin/students", label: "Học sinh", icon: "🎓" },
    { path: "/admin/teachers", label: "Giáo viên", icon: "👨‍🏫" },
    { path: "/admin/classrooms", label: "Lớp học", icon: "🏫" },
    { path: "/admin/courses", label: "Khoá học", icon: "📚" },
];

const userInitial = computed(() => {
    return currentUsername.value?.charAt(0).toUpperCase() || "A";
});

defineProps<{
    title?: string;
    subtitle?: string;
}>();

function onSearchInput() {
    showDropdown.value = true;
}

function onSearchEnter() {
    if (!searchQuery.value.trim()) return;
    // Mặc định search ở trang hiện tại, hoặc trang đầu tiên phù hợp
    const currentPath = router.currentRoute.value.path;
    const matchedTarget = searchTargets.find((t) =>
        currentPath.startsWith(t.path),
    );
    const targetPath =
        matchedTarget?.path ?? searchTargets[0]?.path ?? "/admin/accounts";
    navigateToSearch(targetPath);
}

function navigateToSearch(path: string) {
    showDropdown.value = false;
    router.push({ path, query: { search: searchQuery.value.trim() } });
}

function handleClickOutside(event: MouseEvent) {
    if (
        searchWrapperRef.value &&
        !searchWrapperRef.value.contains(event.target as Node)
    ) {
        showDropdown.value = false;
    }
}

onMounted(() => {
    const username = localStorage.getItem("username") || "";
    const role = localStorage.getItem("role") || "";

    currentUsername.value = username;
    currentRole.value = role === "ROLE_ADMIN" ? "Quản trị viên" : role;

    document.addEventListener("click", handleClickOutside);
});

onBeforeUnmount(() => {
    document.removeEventListener("click", handleClickOutside);
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

.search-dropdown {
    position: absolute;
    top: calc(100% + 6px);
    left: 0;
    right: 0;
    background: #ffffff;
    border: 1px solid #e2e8f0;
    border-radius: 12px;
    box-shadow: 0 10px 40px rgba(0, 0, 0, 0.12);
    z-index: 1000;
    overflow: hidden;
}

.search-dropdown-header {
    padding: 0.6rem 1rem;
    font-size: 0.8rem;
    color: #94a3b8;
    border-bottom: 1px solid #f1f5f9;
}

.search-dropdown-item {
    display: flex;
    align-items: center;
    gap: 0.6rem;
    padding: 0.65rem 1rem;
    font-size: 0.9rem;
    color: #334155;
    text-decoration: none;
    cursor: pointer;
    transition: background 0.15s ease;
}

.search-dropdown-item:hover {
    background: #f0fdfa;
    color: #0891b2;
}

.search-dropdown-icon {
    font-size: 1.1rem;
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
