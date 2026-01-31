<template>
    <AdminHeader title="Dashboard" subtitle="Tổng quan hoạt động trung tâm" />

    <!-- Welcome Banner -->
    <div class="welcome-banner mb-4">
        <div class="row align-items-center">
            <div class="col-md-8">
                <h2 class="welcome-title">Chào mừng trở lại! 👋</h2>
                <p class="welcome-text">
                    Đây là tổng quan về hoạt động của trung tâm English Center.
                </p>
            </div>
            <div class="col-md-4 text-end">
                <span class="date-badge">📅 {{ currentDate }}</span>
            </div>
        </div>
    </div>

    <!-- Summary Cards - Row 1 -->
    <div class="row g-4 mb-4">
        <SummaryCard
            title="Tổng số học sinh"
            :value="summary.totalStudents"
            note="Học viên đang theo học"
        >
            <template #icon>👨‍🎓</template>
        </SummaryCard>
        <SummaryCard
            title="Tổng số lớp học"
            :value="summary.totalClassrooms"
            note="Lớp đang hoạt động"
        >
            <template #icon>🏫</template>
        </SummaryCard>
        <SummaryCard
            title="Tổng số giáo viên"
            :value="summary.totalTeachers"
            note="Giáo viên đang giảng dạy"
        >
            <template #icon>👨‍🏫</template>
        </SummaryCard>
    </div>

    <!-- Quick Actions -->
    <div class="row g-4">
        <div class="col-md-6">
            <div class="card quick-actions-card">
                <div class="card-body">
                    <h5 class="card-title mb-4">
                        <span class="title-icon">⚡</span>
                        Thao tác nhanh
                    </h5>
                    <div class="quick-actions-grid">
                        <router-link
                            to="/admin/students"
                            class="quick-action-item"
                        >
                            <span class="action-icon">👨‍🎓</span>
                            <span>Thêm học sinh</span>
                        </router-link>
                        <router-link
                            to="/admin/teachers"
                            class="quick-action-item"
                        >
                            <span class="action-icon">👨‍🏫</span>
                            <span>Thêm giáo viên</span>
                        </router-link>
                        <router-link
                            to="/admin/classrooms"
                            class="quick-action-item"
                        >
                            <span class="action-icon">🏫</span>
                            <span>Tạo lớp học</span>
                        </router-link>
                        <router-link
                            to="/admin/courses"
                            class="quick-action-item"
                        >
                            <span class="action-icon">📚</span>
                            <span>Thêm khoá học</span>
                        </router-link>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card info-card">
                <div class="card-body">
                    <h5 class="card-title mb-4">
                        <span class="title-icon">📊</span>
                        Thống kê nhanh
                    </h5>
                    <div class="stats-list">
                        <div class="stat-item">
                            <span class="stat-label">Tổng học sinh</span>
                            <span class="stat-value">{{
                                summary.totalStudents
                            }}</span>
                        </div>
                        <div class="stat-item">
                            <span class="stat-label">Tổng giáo viên</span>
                            <span class="stat-value text-success">{{
                                summary.totalTeachers
                            }}</span>
                        </div>
                        <div class="stat-item">
                            <span class="stat-label">Tổng lớp học</span>
                            <span class="stat-value text-primary">{{
                                summary.totalClassrooms
                            }}</span>
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
import SummaryCard from "../../components/admin/SummaryCard.vue";
import { useDashboard } from "../../composables/useDashboard";

const { summary } = useDashboard();

const currentDate = computed(() => {
    return new Date().toLocaleDateString("vi-VN", {
        weekday: "long",
        year: "numeric",
        month: "long",
        day: "numeric",
    });
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
    right: -20%;
    width: 60%;
    height: 200%;
    background: radial-gradient(
        circle,
        rgba(6, 182, 212, 0.2) 0%,
        transparent 70%
    );
    pointer-events: none;
}

.welcome-title {
    font-size: 1.75rem;
    font-weight: 700;
    margin-bottom: 0.5rem;
}

.welcome-text {
    color: #94a3b8;
    margin-bottom: 0;
    font-size: 1rem;
}

.date-badge {
    background: rgba(6, 182, 212, 0.2);
    color: #67e8f9;
    padding: 0.5rem 1rem;
    border-radius: 10px;
    font-size: 0.9rem;
}

.quick-actions-card,
.info-card {
    height: 100%;
    border: none;
    border-radius: 20px;
}

.card-title {
    font-weight: 600;
    color: #0f172a;
    display: flex;
    align-items: center;
    gap: 10px;
}

.title-icon {
    font-size: 1.25rem;
}

.quick-actions-grid {
    display: grid;
    grid-template-columns: repeat(2, 1fr);
    gap: 12px;
}

.quick-action-item {
    display: flex;
    align-items: center;
    gap: 12px;
    padding: 1rem;
    background: linear-gradient(135deg, #f0fdfa, #ecfeff);
    border-radius: 12px;
    text-decoration: none;
    color: #0f172a;
    font-weight: 500;
    transition: all 0.2s ease;
}

.quick-action-item:hover {
    background: linear-gradient(135deg, #06b6d4, #0891b2);
    color: #fff;
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(6, 182, 212, 0.3);
}

.action-icon {
    font-size: 1.5rem;
}

.stats-list {
    display: flex;
    flex-direction: column;
    gap: 1rem;
}

.stat-item {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 1rem;
    background: #f8fafc;
    border-radius: 12px;
}

.stat-label {
    color: #64748b;
    font-size: 0.9rem;
}

.stat-value {
    font-weight: 700;
    font-size: 1.1rem;
    color: #0f172a;
}

.text-success {
    color: #16a34a !important;
}

.text-primary {
    color: #0891b2 !important;
}
</style>
