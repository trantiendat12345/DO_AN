<template>
    <nav class="sidebar py-4 px-3">
        <!-- LOGO -->
        <div class="d-flex align-items-center mb-4 px-2">
            <div class="sidebar-logo me-3">
                <span class="logo-icon">🎓</span>
            </div>
            <div>
                <div class="fw-bold text-white" style="font-size: 1.1rem">
                    English Center
                </div>
                <small class="text-cyan-light"> Admin Panel </small>
            </div>
        </div>

        <ul class="nav flex-column mb-4">
            <!-- TỔNG QUAN -->
            <li class="nav-item mt-2">
                <span class="nav-section-title">Tổng quan</span>
            </li>
            <li class="nav-item">
                <router-link
                    to="/admin/dashboard"
                    class="nav-link"
                    active-class="active"
                >
                    <span class="nav-icon">📊</span> Dashboard
                </router-link>
            </li>

            <!-- QUẢN LÝ NGƯỜI DÙNG -->
            <li class="nav-item mt-3">
                <span class="nav-section-title">Quản lý người dùng</span>
            </li>
            <li class="nav-item">
                <router-link
                    to="/admin/students"
                    class="nav-link"
                    active-class="active"
                >
                    <span class="nav-icon">👨‍🎓</span> Học sinh
                </router-link>
            </li>
            <li class="nav-item">
                <router-link
                    to="/admin/teachers"
                    class="nav-link"
                    active-class="active"
                >
                    <span class="nav-icon">👨‍🏫</span> Giáo viên
                </router-link>
            </li>

            <!-- QUẢN LÝ HỆ THỐNG -->
            <li class="nav-item mt-3">
                <span class="nav-section-title">Quản lý hệ thống</span>
            </li>
            <li class="nav-item">
                <router-link
                    to="/admin/accounts"
                    class="nav-link"
                    active-class="active"
                >
                    <span class="nav-icon">👥</span> Tài khoản
                </router-link>
            </li>

            <!-- ĐÀO TẠO -->
            <li class="nav-item mt-3">
                <span class="nav-section-title">Đào tạo</span>
            </li>
            <li class="nav-item">
                <router-link
                    to="/admin/classrooms"
                    class="nav-link"
                    active-class="active"
                >
                    <span class="nav-icon">🏫</span> Lớp học
                </router-link>
            </li>
            <li class="nav-item">
                <router-link
                    to="/admin/courses"
                    class="nav-link"
                    active-class="active"
                >
                    <span class="nav-icon">📚</span> Khóa học
                </router-link>
            </li>
            <li class="nav-item">
                <router-link
                    to="/admin/assign-teachers"
                    class="nav-link"
                    active-class="active"
                >
                    <span class="nav-icon">📋</span> Phân công giáo viên
                </router-link>
            </li>
            <li class="nav-item">
                <router-link
                    to="/admin/scores"
                    class="nav-link"
                    active-class="active"
                >
                    <span class="nav-icon">📊</span> Quản lý điểm
                </router-link>
            </li>

            <!-- TÀI CHÍNH -->
            <li class="nav-item mt-3">
                <span class="nav-section-title">Tài chính</span>
            </li>
            <li class="nav-item">
                <router-link
                    to="/admin/fee"
                    class="nav-link"
                    active-class="active"
                >
                    <span class="nav-icon">💰</span> Học phí
                </router-link>
            </li>
        </ul>
    </nav>
</template>

<script setup lang="ts">
// router-link is globally registered by vue-router
</script>

<style scoped>
.sidebar-logo {
    width: 48px;
    height: 48px;
    background: linear-gradient(135deg, #06b6d4, #0891b2);
    border-radius: 14px;
    display: flex;
    align-items: center;
    justify-content: center;
    box-shadow: 0 4px 12px rgba(6, 182, 212, 0.4);
}

.logo-icon {
    font-size: 1.5rem;
}

.text-cyan-light {
    color: #67e8f9 !important;
    font-size: 0.8rem;
}

.nav-section-title {
    text-transform: uppercase;
    font-weight: 600;
    color: #64748b;
    font-size: 0.7rem;
    letter-spacing: 0.1em;
    padding: 0.5rem 1rem;
    display: block;
}

.nav-icon {
    margin-right: 10px;
    font-size: 1.1rem;
}
</style>
