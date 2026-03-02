<template>
    <AdminHeader title="Điểm của tôi" subtitle="Xem kết quả học tập" />

    <!-- Stats Summary -->
    <div class="row g-4 mb-4">
        <div class="col-md-4">
            <div class="card stat-card">
                <div class="card-body text-center">
                    <div class="stat-icon">📝</div>
                    <h3 class="stat-value">{{ scores.totalElements }}</h3>
                    <p class="stat-label">Tổng số điểm</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card stat-card">
                <div class="card-body text-center">
                    <div class="stat-icon">📊</div>
                    <h3 class="stat-value">{{ averageScore }}</h3>
                    <p class="stat-label">Điểm trung bình</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card stat-card">
                <div class="card-body text-center">
                    <div class="stat-icon">⭐</div>
                    <h3 class="stat-value">{{ highestScore }}</h3>
                    <p class="stat-label">Điểm cao nhất</p>
                </div>
            </div>
        </div>
    </div>

    <!-- Scores Table -->
    <div class="card table-card">
        <div class="card-body">
            <h5 class="card-title mb-4">
                <span class="title-icon">📋</span>
                Bảng điểm chi tiết
            </h5>

            <!-- Loading State -->
            <div v-if="loading" class="text-center py-5">
                <div class="spinner-border text-primary" role="status">
                    <span class="visually-hidden">Loading...</span>
                </div>
                <p class="text-muted mt-2">Đang tải dữ liệu...</p>
            </div>

            <!-- Data State -->
            <div v-else-if="hasData">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>STT</th>
                                <th>Lớp học</th>
                                <th>Loại điểm</th>
                                <th>Điểm số</th>
                                <th>Ghi chú</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr
                                v-for="(score, index) in scores.content"
                                :key="score.id"
                            >
                                <td>
                                    {{
                                        currentPage * Number(pageSize) +
                                        (index as number) +
                                        1
                                    }}
                                </td>
                                <td>
                                    <span class="badge bg-info">
                                        {{ score.classroomName }}
                                    </span>
                                </td>
                                <td>
                                    <span
                                        class="badge"
                                        :class="getScoreTypeBadge(score.type)"
                                    >
                                        {{ getScoreTypeText(score.type) }}
                                    </span>
                                </td>
                                <td>
                                    <span
                                        class="score-value"
                                        :class="getScoreClass(score.value)"
                                    >
                                        {{ score.value }}
                                    </span>
                                </td>
                                <td>
                                    <span class="text-muted">
                                        {{ score.note || "—" }}
                                    </span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>

                <!-- Pagination -->
                <Pagination
                    :page="currentPage"
                    :total-pages="scores.totalPages"
                    @change="handlePageChange"
                />
            </div>

            <!-- Empty State -->
            <div v-else class="empty-state text-center py-5">
                <div class="empty-icon">📝</div>
                <h5>Chưa có dữ liệu điểm</h5>
                <p class="text-muted">
                    Bạn chưa có điểm nào được ghi nhận trong hệ thống.
                </p>
            </div>

            <!-- Error State -->
            <div v-if="error" class="alert alert-danger mt-3" role="alert">
                {{ error }}
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { computed } from "vue";
import AdminHeader from "../../components/admin/AdminHeader.vue";
import Pagination from "../../components/common/Pagination.vue";
import { useMyScores } from "../../composables/useMyScores";

// Sử dụng composable - logic được xử lý trong Service ở backend
const {
    scores,
    loading,
    error,
    currentPage,
    pageSize,
    hasData,
    handlePageChange,
} = useMyScores();

// Computed values cho stats
const averageScore = computed(() => {
    if (!hasData.value) return "—";
    const sum = scores.value.content.reduce(
        (acc: number, score: any) => acc + score.value,
        0,
    );
    return (sum / scores.value.content.length).toFixed(2);
});

const highestScore = computed(() => {
    if (!hasData.value) return "—";
    const max = Math.max(
        ...scores.value.content.map((score: any) => score.value),
    );
    return max.toFixed(2);
});

// Helper functions
const getScoreTypeText = (type: string) => {
    const typeMap: Record<string, string> = {
        MIDTERM: "Giữa kỳ",
        FINAL: "Cuối kỳ",
        QUIZ: "Kiểm tra",
        ASSIGNMENT: "Bài tập",
        SPEAKING: "Nói",
        WRITING: "Viết",
        LISTENING: "Nghe",
        READING: "Đọc",
    };
    return typeMap[type] || type;
};

const getScoreTypeBadge = (type: string) => {
    const badgeMap: Record<string, string> = {
        MIDTERM: "bg-warning",
        FINAL: "bg-danger",
        QUIZ: "bg-primary",
        ASSIGNMENT: "bg-secondary",
        SPEAKING: "bg-success",
        WRITING: "bg-info",
        LISTENING: "bg-dark",
        READING: "bg-purple",
    };
    return badgeMap[type] || "bg-secondary";
};

const getScoreClass = (value: number) => {
    if (value >= 8) return "text-success fw-bold";
    if (value >= 6.5) return "text-primary fw-bold";
    if (value >= 5) return "text-warning fw-bold";
    return "text-danger fw-bold";
};
</script>

<style scoped>
.stat-card {
    border: none;
    border-radius: 16px;
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
    transition: transform 0.3s ease;
}

.stat-card:hover {
    transform: translateY(-4px);
    box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
}

.stat-icon {
    font-size: 3rem;
    margin-bottom: 1rem;
}

.stat-value {
    font-size: 2.5rem;
    font-weight: 700;
    color: #0f172a;
    margin: 0;
}

.stat-label {
    color: #64748b;
    font-size: 0.9rem;
    margin: 0;
}

.table-card {
    border: none;
    border-radius: 16px;
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.08);
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

.table {
    margin-bottom: 0;
}

.table thead th {
    background: linear-gradient(135deg, #0f172a 0%, #1e293b 100%);
    color: white;
    font-weight: 600;
    text-transform: uppercase;
    font-size: 0.85rem;
    border: none;
    padding: 1rem;
}

.table tbody tr {
    transition: all 0.2s ease;
}

.table tbody tr:hover {
    background-color: #f8fafc;
    transform: scale(1.01);
}

.table tbody td {
    padding: 1rem;
    vertical-align: middle;
}

.score-value {
    font-size: 1.2rem;
}

.empty-state {
    padding: 3rem 1rem;
}

.empty-icon {
    font-size: 4rem;
    margin-bottom: 1rem;
    opacity: 0.5;
}

.bg-purple {
    background-color: #8b5cf6 !important;
}
</style>
