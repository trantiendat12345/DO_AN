<template>
    <AdminHeader title="Lớp học" subtitle="Quản lý lớp học và học viên" />

    <!-- Loading State -->
    <div v-if="loading" class="text-center py-5">
        <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
        <p class="text-muted mt-2">Đang tải danh sách lớp học...</p>
    </div>

    <!-- Classrooms List -->
    <div v-else>
        <div v-if="classrooms.length === 0" class="alert alert-info">
            <i class="bi bi-info-circle me-2"></i>
            Bạn chưa được phân công dạy lớp nào.
        </div>

        <div v-else class="row g-4">
            <div
                v-for="classroom in classrooms"
                :key="classroom.id"
                class="col-md-6"
            >
                <div class="card classroom-card">
                    <div class="card-body">
                        <div
                            class="d-flex justify-content-between align-items-start mb-3"
                        >
                            <h5 class="card-title mb-0">
                                <span class="classroom-icon">🏫</span>
                                {{ classroom.name }}
                            </h5>
                            <span
                                class="badge"
                                :class="getStatusClass(classroom.status)"
                            >
                                {{ getStatusText(classroom.status) }}
                            </span>
                        </div>

                        <div class="classroom-info mb-3">
                            <div class="info-row">
                                <span class="info-label">📚 Khóa học:</span>
                                <span class="info-value">{{
                                    classroom.courseName
                                }}</span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">👥 Học viên:</span>
                                <span class="info-value">
                                    {{ classroom.currentStudent }}/{{
                                        classroom.maxStudent
                                    }}
                                </span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">📅 Thời gian:</span>
                                <span class="info-value">
                                    {{ formatDate(classroom.startDate) }} -
                                    {{
                                        classroom.endDate
                                            ? formatDate(classroom.endDate)
                                            : "Đang học"
                                    }}
                                </span>
                            </div>
                            <div class="info-row">
                                <span class="info-label">👨‍🏫 Vai trò:</span>
                                <span class="badge bg-primary">
                                    {{ getRoleText(classroom.teacherRole) }}
                                </span>
                            </div>
                        </div>

                        <div class="d-flex gap-2">
                            <button
                                class="btn btn-sm btn-outline-primary flex-fill"
                                @click="viewStudents(classroom.name)"
                            >
                                <i class="bi bi-people me-1"></i> Xem học viên
                            </button>
                            <button
                                class="btn btn-sm btn-outline-success flex-fill"
                                @click="viewScores(classroom.name)"
                            >
                                <i class="bi bi-clipboard-check me-1"></i> Xem
                                điểm
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Pagination -->
        <Pagination
            v-if="totalPages > 1"
            :page="currentPage"
            :total-pages="totalPages"
            @change="goToPage"
        />
    </div>

    <!-- Students Modal -->
    <div
        class="modal fade"
        id="studentsModal"
        tabindex="-1"
        ref="studentsModalRef"
    >
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">
                        Danh sách học viên - {{ selectedClassroom }}
                    </h5>
                    <button
                        type="button"
                        class="btn-close"
                        @click="closeStudentsModal"
                    ></button>
                </div>
                <div class="modal-body">
                    <div v-if="studentsLoading" class="text-center py-3">
                        <div
                            class="spinner-border text-primary"
                            role="status"
                        ></div>
                    </div>
                    <div
                        v-else-if="students.length === 0"
                        class="text-center text-muted py-3"
                    >
                        Chưa có học viên nào trong lớp này.
                    </div>
                    <div v-else class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Mã HV</th>
                                    <th>Họ tên</th>
                                    <th>Email</th>
                                    <th>Số điện thoại</th>
                                    <th>Trạng thái</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr
                                    v-for="student in students"
                                    :key="student.studentCode"
                                >
                                    <td>{{ student.studentCode }}</td>
                                    <td>{{ student.fullName }}</td>
                                    <td>{{ student.email }}</td>
                                    <td>{{ student.phone }}</td>
                                    <td>
                                        <span
                                            class="badge"
                                            :class="
                                                getStudentStatusClass(
                                                    student.status,
                                                )
                                            "
                                        >
                                            {{
                                                getStudentStatusText(
                                                    student.status,
                                                )
                                            }}
                                        </span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Scores Modal -->
    <div class="modal fade" id="scoresModal" tabindex="-1" ref="scoresModalRef">
        <div class="modal-dialog modal-xl">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">
                        Bảng điểm - {{ selectedClassroom }}
                    </h5>
                    <button
                        type="button"
                        class="btn-close"
                        @click="closeScoresModal"
                    ></button>
                </div>
                <div class="modal-body">
                    <div v-if="scoresLoading" class="text-center py-3">
                        <div
                            class="spinner-border text-primary"
                            role="status"
                        ></div>
                    </div>
                    <div
                        v-else-if="scores.length === 0"
                        class="text-center text-muted py-3"
                    >
                        Chưa có điểm nào trong lớp này.
                    </div>
                    <div v-else class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Mã HV</th>
                                    <th>Họ tên</th>
                                    <th>Loại điểm</th>
                                    <th>Điểm</th>
                                    <th>Ghi chú</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="score in scores" :key="score.id">
                                    <td>{{ score.studentCode }}</td>
                                    <td>{{ score.studentName }}</td>
                                    <td>
                                        <span class="badge bg-info">
                                            {{ getScoreTypeText(score.type) }}
                                        </span>
                                    </td>
                                    <td>
                                        <span
                                            class="fw-bold"
                                            :class="getScoreColor(score.value)"
                                        >
                                            {{ score.value }}
                                        </span>
                                    </td>
                                    <td>{{ score.note || "-" }}</td>
                                </tr>
                            </tbody>
                        </table>

                        <!-- Pagination for Scores -->
                        <Pagination
                            v-if="scoresTotalPages > 1"
                            :page="scoresCurrentPage"
                            :total-pages="scoresTotalPages"
                            @change="
                                (page) =>
                                    scoresGoToPage(selectedClassroom, page)
                            "
                        />
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import AdminHeader from "../../components/admin/AdminHeader.vue";
import Pagination from "../../components/common/Pagination.vue";
import { useTeacherClassrooms } from "../../composables/useTeacherClassrooms";
import { useClassroomStudents } from "../../composables/useClassroomStudents";
import { useClassroomScores } from "../../composables/useClassroomScores";
import { formatDate } from "../../util/formats";
import { Modal } from "bootstrap";

const {
    classrooms,
    loading,
    currentPage,
    totalPages,
    fetchClassrooms,
    goToPage,
} = useTeacherClassrooms();

const {
    students,
    loading: studentsLoading,
    fetchStudents,
} = useClassroomStudents();

const {
    scores,
    loading: scoresLoading,
    currentPage: scoresCurrentPage,
    totalPages: scoresTotalPages,
    fetchScores,
    goToPage: scoresGoToPage,
} = useClassroomScores();

const selectedClassroom = ref("");
const studentsModalRef = ref<HTMLElement | null>(null);
const scoresModalRef = ref<HTMLElement | null>(null);
let studentsModalInstance: Modal | null = null;
let scoresModalInstance: Modal | null = null;

onMounted(() => {
    fetchClassrooms(0);
});

const viewStudents = async (classroomName: string) => {
    selectedClassroom.value = classroomName;
    await fetchStudents(classroomName);

    if (studentsModalRef.value) {
        studentsModalInstance = new Modal(studentsModalRef.value);
        studentsModalInstance.show();
    }
};

const viewScores = async (classroomName: string) => {
    selectedClassroom.value = classroomName;
    await fetchScores(classroomName, 0);

    if (scoresModalRef.value) {
        scoresModalInstance = new Modal(scoresModalRef.value);
        scoresModalInstance.show();
    }
};

const closeStudentsModal = () => {
    studentsModalInstance?.hide();
};

const closeScoresModal = () => {
    scoresModalInstance?.hide();
};

// Helper functions
const getStatusClass = (status: string) => {
    switch (status) {
        case "ACTIVE":
            return "bg-success";
        case "COMPLETED":
            return "bg-secondary";
        case "PENDING":
            return "bg-warning";
        default:
            return "bg-secondary";
    }
};

const getStatusText = (status: string) => {
    switch (status) {
        case "ACTIVE":
            return "Đang hoạt động";
        case "COMPLETED":
            return "Đã kết thúc";
        case "PENDING":
            return "Chờ khai giảng";
        default:
            return status;
    }
};

const getRoleText = (role: string) => {
    switch (role) {
        case "PRIMARY":
            return "Giáo viên chính";
        case "ASSISTANT":
            return "Trợ giảng";
        default:
            return role;
    }
};

const getStudentStatusClass = (status: string) => {
    switch (status) {
        case "ACTIVE":
            return "bg-success";
        case "INACTIVE":
            return "bg-secondary";
        default:
            return "bg-secondary";
    }
};

const getStudentStatusText = (status: string) => {
    switch (status) {
        case "ACTIVE":
            return "Đang học";
        case "INACTIVE":
            return "Ngừng học";
        default:
            return status;
    }
};

const getScoreTypeText = (type: string) => {
    switch (type) {
        case "MIDTERM":
            return "Giữa kỳ";
        case "FINAL":
            return "Cuối kỳ";
        case "ASSIGNMENT":
            return "Bài tập";
        case "PARTICIPATION":
            return "Tham gia";
        default:
            return type;
    }
};

const getScoreColor = (value: number) => {
    if (value >= 8) return "text-success";
    if (value >= 6.5) return "text-primary";
    if (value >= 5) return "text-warning";
    return "text-danger";
};
</script>

<style scoped>
.classroom-card {
    border: none;
    border-radius: 12px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
    transition: all 0.3s ease;
}

.classroom-card:hover {
    transform: translateY(-4px);
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
}

.classroom-icon {
    font-size: 1.3rem;
    margin-right: 0.5rem;
}

.classroom-info {
    background: #f8fafc;
    padding: 1rem;
    border-radius: 8px;
}

.info-row {
    display: flex;
    justify-content: space-between;
    padding: 0.5rem 0;
    border-bottom: 1px solid #e2e8f0;
}

.info-row:last-child {
    border-bottom: none;
}

.info-label {
    color: #64748b;
    font-weight: 500;
}

.info-value {
    color: #1e293b;
    font-weight: 600;
}
</style>
