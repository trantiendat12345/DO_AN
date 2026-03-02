<template>
    <AdminHeader title="Dashboard" subtitle="Thông tin học viên" />

    <!-- Welcome Banner -->
    <div class="welcome-banner mb-4">
        <div class="row align-items-center">
            <div class="col-md-8">
                <h2 class="welcome-title">
                    Xin chào, {{ studentInfo?.fullName || "Học viên" }}! 👋
                </h2>
                <p class="welcome-text">
                    Đây là thông tin chi tiết về hồ sơ học tập của bạn.
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

    <!-- Student Info Cards -->
    <div v-else-if="studentInfo" class="row g-4">
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
                            <span class="info-label">Mã học viên:</span>
                            <span class="info-value">{{
                                studentInfo.studentCode
                            }}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Họ và tên:</span>
                            <span class="info-value">{{
                                studentInfo.fullName
                            }}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Ngày sinh:</span>
                            <span class="info-value">{{
                                formatDate(studentInfo.dob)
                            }}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Giới tính:</span>
                            <span class="info-value">{{
                                studentInfo.gender === "MALE" ? "Nam" : "Nữ"
                            }}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Trình độ:</span>
                            <span class="info-value">{{
                                studentInfo.level
                            }}</span>
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
                                studentInfo.email
                            }}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Số điện thoại:</span>
                            <span class="info-value">{{
                                studentInfo.phone
                            }}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Địa chỉ:</span>
                            <span class="info-value">{{
                                studentInfo.address
                            }}</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">Trạng thái:</span>
                            <span class="badge" :class="statusClass">
                                {{ statusText }}
                            </span>
                        </div>
                        <div v-if="studentInfo.note" class="info-item">
                            <span class="info-label">Ghi chú:</span>
                            <span class="info-value">{{
                                studentInfo.note
                            }}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Classroom Info Card -->
        <div class="col-12">
            <div class="card info-card">
                <div class="card-body">
                    <h5 class="card-title mb-4">
                        <span class="title-icon">🏫</span>
                        Lớp học đang theo học
                    </h5>
                    <div
                        v-if="
                            studentInfo.classrooms &&
                            studentInfo.classrooms.length > 0
                        "
                        class="classroom-list"
                    >
                        <span
                            v-for="(classroom, index) in studentInfo.classrooms"
                            :key="index"
                            class="badge bg-primary me-2 mb-2"
                            style="font-size: 0.9rem; padding: 0.5rem 1rem"
                        >
                            🏫 {{ classroom }}
                        </span>
                    </div>
                    <div v-else class="text-muted">
                        Chưa tham gia lớp học nào
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Error State -->
    <div v-else-if="error" class="alert alert-danger" role="alert">
        {{ error }}
    </div>
</template>

<script setup lang="ts">
import { computed } from "vue";
import AdminHeader from "../../components/admin/AdminHeader.vue";
import { useStudentInfo } from "../../composables/useStudentInfo";

// Sử dụng composable - logic được xử lý trong Service ở backend
const { studentInfo, loading, error } = useStudentInfo();

const currentDate = computed(() => {
    return new Date().toLocaleDateString("vi-VN", {
        weekday: "long",
        year: "numeric",
        month: "long",
        day: "numeric",
    });
});

const formatDate = (date: string) => {
    if (!date) return "";
    return new Date(date).toLocaleDateString("vi-VN");
};

const statusClass = computed(() => {
    if (!studentInfo.value) return "";
    return studentInfo.value.status === "ACTIVE" ? "bg-success" : "bg-danger";
});

const statusText = computed(() => {
    if (!studentInfo.value) return "";
    switch (studentInfo.value.status) {
        case "ACTIVE":
            return "Đang học";
        case "INACTIVE":
            return "Không hoạt động";
        case "SUSPENDED":
            return "Tạm ngưng";
        default:
            return studentInfo.value.status;
    }
});
</script>

<style scoped>
.welcome-banner {
    background: linear-gradient(135deg, #0f172a 0%, #1e293b 100%);
    border-radius: 20px;
    padding: 2rem;
    color: #fff;
    position: relative;
    overflow: hidden;
}

.welcome-banner::before {
    content: "";
    position: absolute;
    top: -50%;
    right: -10%;
    width: 400px;
    height: 400px;
    background: radial-gradient(
        circle,
        rgba(6, 182, 212, 0.2) 0%,
        transparent 70%
    );
    border-radius: 50%;
}

.welcome-title {
    font-size: 1.8rem;
    font-weight: 700;
    margin-bottom: 0.5rem;
    position: relative;
}

.welcome-text {
    font-size: 1rem;
    color: #cbd5e1;
    margin: 0;
    position: relative;
}

.date-badge {
    background: rgba(255, 255, 255, 0.15);
    padding: 0.5rem 1.2rem;
    border-radius: 12px;
    font-size: 0.9rem;
    font-weight: 500;
    backdrop-filter: blur(10px);
}

.info-card {
    border: none;
    border-radius: 16px;
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
    transition:
        transform 0.3s ease,
        box-shadow 0.3s ease;
}

.info-card:hover {
    transform: translateY(-4px);
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
}

.card-title {
    font-size: 1.2rem;
    font-weight: 700;
    color: #0f172a;
    display: flex;
    align-items: center;
}

.title-icon {
    font-size: 1.5rem;
    margin-right: 0.75rem;
}

.info-list {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

.info-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0.75rem 0;
    border-bottom: 1px solid #e2e8f0;
}

.info-item:last-child {
    border-bottom: none;
}

.info-label {
    font-weight: 600;
    color: #64748b;
    font-size: 0.9rem;
}

.info-value {
    font-weight: 500;
    color: #0f172a;
    text-align: right;
}

.classroom-list {
    display: flex;
    flex-wrap: wrap;
}
</style>
