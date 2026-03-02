<template>
    <AdminHeader title="Dashboard" subtitle="Thông tin giáo viên" />

    <!-- Welcome Banner -->
    <div class="welcome-banner mb-4">
        <div class="row align-items-center">
            <div class="col-md-8">
                <h2 class="welcome-title">
                    Xin chào, {{ teacherInfo?.fullName || "Giáo viên" }}! 👨‍🏫
                </h2>
                <p class="welcome-text">
                    Đây là thông tin chi tiết về hoạt động giảng dạy của bạn.
                </p>
            </div>
            <div class="col-md-4 text-end">
                <span class="date-badge">📅 {{ currentDate }}</span>
            </div>
        </div>
    </div>

    <!-- Loading State -->
    <div v-if="loading" class="text-center py-5">
        <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
        <p class="text-muted mt-2">Đang tải thông tin...</p>
    </div>

    <!-- Teacher Info Cards -->
    <div v-else-if="teacherInfo" class="row g-4">
        <!-- Summary Cards -->
        <div class="col-md-4">
            <div class="card summary-card bg-gradient-primary">
                <div class="card-body">
                    <div class="d-flex align-items-center">
                        <div class="icon-wrapper bg-white bg-opacity-25">
                            <span class="summary-icon">🏫</span>
                        </div>
                        <div class="ms-3">
                            <h3 class="mb-0 text-white">
                                {{ teacherInfo.totalClasses }}
                            </h3>
                            <p class="mb-0 text-white-50">Lớp đang dạy</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card summary-card bg-gradient-success">
                <div class="card-body">
                    <div class="d-flex align-items-center">
                        <div class="icon-wrapper bg-white bg-opacity-25">
                            <span class="summary-icon">👨‍🎓</span>
                        </div>
                        <div class="ms-3">
                            <h3 class="mb-0 text-white">
                                {{ teacherInfo.totalStudents }}
                            </h3>
                            <p class="mb-0 text-white-50">Học viên</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="card summary-card bg-gradient-info">
                <div class="card-body">
                    <div class="d-flex align-items-center">
                        <div class="icon-wrapper bg-white bg-opacity-25">
                            <span class="summary-icon">✓</span>
                        </div>
                        <div class="ms-3">
                            <h3 class="mb-0 text-white">{{ statusText }}</h3>
                            <p class="mb-0 text-white-50">Trạng thái</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Personal Info Card -->
        <div class="col-md-6">
            <div class="card info-card">
                <div class="card-body">
                    <h5 class="card-title mb-4">
                        <span class="title-icon">👤</span>
                        Thông tin cá nhân
                    </h5>
                    <div class="info-list">
                        <div class="info-item">
                            <span class="info-label">Mã giáo viên:</span>
                            <span class="info-value">{{
                                teacherInfo.teacherCode
                            }}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Họ và tên:</span>
                            <span class="info-value">{{
                                teacherInfo.fullName
                            }}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Trạng thái:</span>
                            <span class="badge" :class="statusClass">
                                {{ statusText }}
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Contact Info Card -->
        <div class="col-md-6">
            <div class="card info-card">
                <div class="card-body">
                    <h5 class="card-title mb-4">
                        <span class="title-icon">📞</span>
                        Thông tin liên hệ
                    </h5>
                    <div class="info-list">
                        <div class="info-item">
                            <span class="info-label">Email:</span>
                            <span class="info-value">{{
                                teacherInfo.email
                            }}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Số điện thoại:</span>
                            <span class="info-value">{{
                                teacherInfo.phone
                            }}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Quick Actions Card -->
        <div class="col-12">
            <div class="card info-card">
                <div class="card-body">
                    <h5 class="card-title mb-4">
                        <span class="title-icon">⚡</span>
                        Truy cập nhanh
                    </h5>
                    <div class="row g-3">
                        <div class="col-md-4">
                            <router-link
                                to="/teacher/classrooms"
                                class="quick-action-btn"
                            >
                                <span class="action-icon">🏫</span>
                                <span>Quản lý lớp học</span>
                            </router-link>
                        </div>
                        <div class="col-md-4">
                            <router-link
                                to="/teacher/schedule"
                                class="quick-action-btn"
                            >
                                <span class="action-icon">📅</span>
                                <span>Xem lịch dạy</span>
                            </router-link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { computed } from "vue";
import AdminHeader from "../../components/admin/AdminHeader.vue";
import { useTeacherInfo } from "../../composables/useTeacherInfo";

const { teacherInfo, loading } = useTeacherInfo();

// Current date
const currentDate = computed(() => {
    return new Date().toLocaleDateString("vi-VN", {
        weekday: "long",
        year: "numeric",
        month: "long",
        day: "numeric",
    });
});

// Status mapping
const statusText = computed(() => {
    switch (teacherInfo.value?.status) {
        case "ACTIVE":
            return "Đang hoạt động";
        case "INACTIVE":
            return "Ngừng hoạt động";
        default:
            return teacherInfo.value?.status || "N/A";
    }
});

const statusClass = computed(() => {
    switch (teacherInfo.value?.status) {
        case "ACTIVE":
            return "bg-success";
        case "INACTIVE":
            return "bg-secondary";
        default:
            return "bg-secondary";
    }
});
</script>

<style scoped>
.welcome-banner {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    padding: 2rem;
    border-radius: 16px;
    color: white;
    box-shadow: 0 4px 20px rgba(102, 126, 234, 0.3);
}

.welcome-title {
    font-size: 1.8rem;
    font-weight: 700;
    margin-bottom: 0.5rem;
}

.welcome-text {
    font-size: 1rem;
    opacity: 0.9;
    margin: 0;
}

.date-badge {
    background: rgba(255, 255, 255, 0.2);
    padding: 0.5rem 1rem;
    border-radius: 20px;
    font-size: 0.9rem;
    backdrop-filter: blur(10px);
}

.summary-card {
    border: none;
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    overflow: hidden;
}

.bg-gradient-primary {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.bg-gradient-success {
    background: linear-gradient(135deg, #10b981 0%, #059669 100%);
}

.bg-gradient-info {
    background: linear-gradient(135deg, #06b6d4 0%, #0891b2 100%);
}

.icon-wrapper {
    width: 60px;
    height: 60px;
    border-radius: 12px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.summary-icon {
    font-size: 2rem;
}

.info-card {
    border: none;
    border-radius: 12px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
    transition: all 0.3s ease;
}

.info-card:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
}

.card-title {
    color: #1e293b;
    font-weight: 600;
    font-size: 1.1rem;
}

.title-icon {
    font-size: 1.3rem;
    margin-right: 0.5rem;
}

.info-list {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

.info-item {
    display: flex;
    justify-content: space-between;
    padding-bottom: 1rem;
    border-bottom: 1px solid #f1f5f9;
}

.info-item:last-child {
    border-bottom: none;
    padding-bottom: 0;
}

.info-label {
    color: #64748b;
    font-weight: 500;
}

.info-value {
    color: #1e293b;
    font-weight: 600;
}

.quick-action-btn {
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 0.75rem;
    padding: 1.5rem;
    background: linear-gradient(135deg, #f0f9ff 0%, #e0f2fe 100%);
    border: 2px solid #bae6fd;
    border-radius: 12px;
    color: #0369a1;
    font-weight: 600;
    text-decoration: none;
    transition: all 0.3s ease;
}

.quick-action-btn:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(3, 105, 161, 0.2);
    background: linear-gradient(135deg, #e0f2fe 0%, #bae6fd 100%);
}

.action-icon {
    font-size: 1.5rem;
}
</style>
