<template>
    <nav class="col-md-3 col-lg-2 d-md-block sidebar py-3 px-3">
        <!-- LOGO -->
        <div class="d-flex align-items-center mb-3">
            <div class="admin-avatar me-2">EC</div>
            <div>
                <div class="fw-semibold">English Center</div>
                <small class="text-secondary" style="color: #dbeafe !important">
                    Admin Panel
                </small>
            </div>
        </div>

        <ul class="nav flex-column mb-4">
            <!-- TỔNG QUAN -->
            <li class="nav-item mt-2">
                <span class="text-uppercase fw-bold">Tổng quan</span>
            </li>
            <li class="nav-item">
                <RouterLink
                    to="/admin/dashboard"
                    class="nav-link"
                    active-class="active"
                >
                    🏠 Dashboard
                </RouterLink>
            </li>

            <!-- QUẢN LÝ NGƯỜI DÙNG -->
            <li class="nav-item mt-3">
                <span class="text-uppercase fw-bold">Quản lý người dùng</span>
            </li>
            <li class="nav-item">
                <RouterLink
                    to="/admin/students"
                    class="nav-link"
                    active-class="active"
                >
                    👨‍🎓 Học sinh
                </RouterLink>
            </li>
            <li class="nav-item">
                <RouterLink
                    to="/admin/teachers"
                    class="nav-link"
                    active-class="active"
                >
                    👨‍🏫 Giáo viên
                </RouterLink>
            </li>
            <!-- <li class="nav-item">
        <RouterLink to="/admin/parents" class="nav-link" active-class="active">
          👨‍👩‍👧 Phụ huynh
        </RouterLink>
      </li> -->

            <!-- QUẢN LÝ HỆ THỐNG -->
            <li class="nav-item mt-3">
                <span class="text-uppercase fw-bold">Quản lý hệ thống</span>
            </li>
            <li class="nav-item">
                <RouterLink
                    to="/admin/accounts"
                    class="nav-link"
                    active-class="active"
                >
                    👥 Tài khoản
                </RouterLink>
            </li>
            <!-- ĐÀO TẠO -->
            <li class="nav-item mt-3">
                <span class="text-uppercase fw-bold">Đào tạo</span>
            </li>
            <li class="nav-item">
                <RouterLink
                    to="/admin/classrooms"
                    class="nav-link"
                    active-class="active"
                >
                    🏫 Lớp học
                </RouterLink>
            </li>
            <li class="nav-item">
                <RouterLink
                    to="/admin/courses"
                    class="nav-link"
                    active-class="active"
                >
                    📚 Khóa học
                </RouterLink>
            </li>
            <li class="nav-item">
                <RouterLink
                    to="/admin/assign-teachers"
                    class="nav-link"
                    active-class="active"
                >
                    📋 Phân công giáo viên
                </RouterLink>
            </li>
            <li class="nav-item">
                <RouterLink
                    to="/admin/schedules"
                    class="nav-link"
                    active-class="active"
                >
                    📅 Lịch học
                </RouterLink>
            </li>

            <!-- TÀI CHÍNH & HỌC TẬP -->
            <li class="nav-item mt-3">
                <span class="text-uppercase fw-bold"
                    >Tài chính &amp; Học tập</span
                >
            </li>
            <li class="nav-item">
                <RouterLink
                    to="/admin/tuition"
                    class="nav-link"
                    active-class="active"
                >
                    💰 Học phí
                </RouterLink>
            </li>
            <li class="nav-item">
                <RouterLink
                    to="/admin/grades"
                    class="nav-link"
                    active-class="active"
                >
                    📊 Điểm số tổng hợp
                </RouterLink>
            </li>
            <li class="nav-item">
                <RouterLink
                    to="/admin/documents"
                    class="nav-link"
                    active-class="active"
                >
                    📂 Tài liệu học tập
                </RouterLink>
            </li>

            <!-- BÁO CÁO -->
            <!-- <li class="nav-item mt-3">
                <span class="text-uppercase fw-bold"
                    >Báo cáo &amp; Thông báo</span
                >
            </li>
            <li class="nav-item">
                <RouterLink
                    to="/admin/reports"
                    class="nav-link"
                    active-class="active"
                >
                    📈 Báo cáo
                </RouterLink>
            </li>
            <li class="nav-item">
                <RouterLink
                    to="/admin/notifications"
                    class="nav-link"
                    active-class="active"
                >
                    🔔 Quản lý thông báo
                </RouterLink>
            </li> -->
        </ul>
    </nav>
</template>

<script setup lang="ts">
import { RouterLink } from "vue-router";
</script>
