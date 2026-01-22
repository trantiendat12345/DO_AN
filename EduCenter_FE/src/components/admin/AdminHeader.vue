<template>
    <div class="d-flex flex-wrap mb-4 align-items-center">
        <div class="me-4">
            <h1 class="h4 mb-1 fw-bold">{{ title }}</h1>
            <p class="mb-0 text-secondary">{{ subtitle }}</p>
        </div>

        <div class="flex-grow-1 me-4">
            <input
                class="form-control rounded-pill"
                placeholder="Tìm kiếm học sinh, giáo viên, lớp học..."
            />
        </div>

        <div class="d-flex align-items-center gap-2">
            <button class="btn btn-light position-relative rounded-circle p-2">
                🔔
                <span
                    class="badge bg-danger position-absolute top-0 start-100 translate-middle"
                    >3</span
                >
            </button>

            <div class="dropdown">
                <button class="btn p-0" data-bs-toggle="dropdown">
                    <div class="admin-avatar">{{ userInitial }}</div>
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li>
                        <h6 class="dropdown-header">{{ currentUsername }}</h6>
                    </li>
                    <li>
                        <span class="dropdown-item-text small">{{
                            currentRole
                        }}</span>
                    </li>
                    <li><hr class="dropdown-divider" /></li>
                    <li>
                        <a class="dropdown-item" href="#">Thông tin cá nhân</a>
                    </li>
                    <li>
                        <a class="dropdown-item" @click="logout">Đăng xuất</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from "vue";
// import { useRoute } from "vue-router";
import { logout } from "../../composables/useLogout";

// const route = useRoute();
const currentUsername = ref<string>("");
const currentRole = ref<string>("");

const userInitial = computed(() => {
    return currentUsername.value?.charAt(0).toUpperCase() || "A";
});

defineProps<{
    title?: string;
    subtitle?: string;
}>();

// const pageTitle = computed(() => {
//     const routeName = route.path.split("/").pop();

//     const titles: { [key: string]: string } = {
//         dashboard: "Dashboard Admin",
//         students: "Student Admin",
//         teachers: "Teacher Admin",
//         classrooms: "Classroom Admin",
//         accounts: "Account Admin",
//         courses: "Course Admin",
//     };

//     return titles[routeName || "dashboard"] || "Dashboard Admin";
// });

// const pageSubtitle = computed(() => {
//     const routeName = route.path.split("/").pop();

//     const subtitles: { [key: string]: string } = {
//         dashboard: "Tổng quan hoạt động trung tâm",
//         students: "Quản lý thông tin học sinh",
//         teachers: "Quản lý thông tin giáo viên",
//         classrooms: "Quản lý lớp học",
//         accounts: "Quản lý tài khoản",
//         courses: "Quản lý khóa học",
//     };

//     return (
//         subtitles[routeName || "dashboard"] || "Tổng quan hoạt động trung tâm"
//     );
// });

onMounted(() => {
    // Lấy thông tin người dùng từ localStorage
    const username = localStorage.getItem("username") || "";
    const role = localStorage.getItem("role") || "";

    currentUsername.value = username;
    currentRole.value = role === "ROLE_ADMIN" ? "Quản trị viên" : role;
});
</script>
