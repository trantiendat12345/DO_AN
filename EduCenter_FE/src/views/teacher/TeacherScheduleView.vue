<template>
    <AdminHeader title="Lịch dạy" subtitle="Xem lịch giảng dạy của bạn" />

    <!-- Loading State -->
    <div v-if="loading" class="text-center py-5">
        <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Loading...</span>
        </div>
        <p class="text-muted mt-2">Đang tải lịch dạy...</p>
    </div>

    <!-- Schedule List -->
    <div v-else>
        <div v-if="schedules.length === 0" class="alert alert-info">
            <i class="bi bi-info-circle me-2"></i>
            Bạn chưa có lịch dạy nào.
        </div>

        <div v-else class="card schedule-card">
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Lớp học</th>
                                <th>Ngày học</th>
                                <th>Thời gian</th>
                                <th>Trạng thái</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr
                                v-for="schedule in sortedSchedules"
                                :key="schedule.id"
                                :class="getScheduleRowClass(schedule.date)"
                            >
                                <td>
                                    <span class="classroom-name">
                                        🏫 {{ schedule.classroomName }}
                                    </span>
                                </td>
                                <td>
                                    <span class="schedule-date">
                                        📅 {{ formatDate(schedule.date) }}
                                    </span>
                                    <br />
                                    <small class="text-muted">
                                        {{ getDayOfWeek(schedule.date) }}
                                    </small>
                                </td>
                                <td>
                                    <span class="schedule-time">
                                        🕐 {{ schedule.startTime }} -
                                        {{ schedule.endTime }}
                                    </span>
                                </td>
                                <td>
                                    <span
                                        class="badge"
                                        :class="
                                            getScheduleStatusClass(
                                                schedule.date,
                                            )
                                        "
                                    >
                                        {{ getScheduleStatus(schedule.date) }}
                                    </span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Calendar View (Optional Enhancement) -->
        <div class="card mt-4">
            <div class="card-body">
                <h5 class="card-title mb-4">
                    <span class="title-icon">📅</span>
                    Lịch theo tuần
                </h5>
                <div class="calendar-grid">
                    <div
                        v-for="day in weekDays"
                        :key="day.date"
                        class="calendar-day"
                        :class="{ today: isToday(day.date) }"
                    >
                        <div class="day-header">
                            <div class="day-name">{{ day.name }}</div>
                            <div class="day-date">
                                {{ formatShortDate(day.date) }}
                            </div>
                        </div>
                        <div class="day-schedules">
                            <div
                                v-for="schedule in getSchedulesForDay(day.date)"
                                :key="schedule.id"
                                class="schedule-item"
                            >
                                <div class="schedule-classroom">
                                    {{ schedule.classroomName }}
                                </div>
                                <div class="schedule-time-small">
                                    {{ schedule.startTime }} -
                                    {{ schedule.endTime }}
                                </div>
                            </div>
                            <div
                                v-if="getSchedulesForDay(day.date).length === 0"
                                class="no-schedule"
                            >
                                Không có lịch
                            </div>
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
import { useTeacherSchedule } from "../../composables/useTeacherSchedule";
import { formatDate } from "../../util/formats";

const { schedules, loading } = useTeacherSchedule();

// Sort schedules by date and time
const sortedSchedules = computed(() => {
    return [...schedules.value].sort((a, b) => {
        const dateCompare =
            new Date(a.date).getTime() - new Date(b.date).getTime();
        if (dateCompare !== 0) return dateCompare;
        return a.startTime.localeCompare(b.startTime);
    });
});

// Get current week days
const weekDays = computed<Array<{ name: string; date: string }>>(() => {
    const today = new Date();
    const currentDay = today.getDay();
    const monday = new Date(today);
    monday.setDate(today.getDate() - currentDay + 1);

    const days: Array<{ name: string; date: string }> = [];
    const dayNames = [
        "Thứ 2",
        "Thứ 3",
        "Thứ 4",
        "Thứ 5",
        "Thứ 6",
        "Thứ 7",
        "Chủ nhật",
    ];

    for (let i = 0; i < 7; i++) {
        const date = new Date(monday);
        date.setDate(monday.getDate() + i);
        const dateStr = date.toISOString().split("T")[0];
        days.push({
            name: dayNames[i] || "",
            date: dateStr || "",
        });
    }

    return days;
});

// Helper functions
const getDayOfWeek = (dateStr: string) => {
    const date = new Date(dateStr);
    const days = [
        "Chủ nhật",
        "Thứ 2",
        "Thứ 3",
        "Thứ 4",
        "Thứ 5",
        "Thứ 6",
        "Thứ 7",
    ];
    return days[date.getDay()];
};

const getScheduleStatus = (dateStr: string) => {
    const scheduleDate = new Date(dateStr);
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    scheduleDate.setHours(0, 0, 0, 0);

    if (scheduleDate < today) return "Đã dạy";
    if (scheduleDate.getTime() === today.getTime()) return "Hôm nay";
    return "Sắp tới";
};

const getScheduleStatusClass = (dateStr: string) => {
    const scheduleDate = new Date(dateStr);
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    scheduleDate.setHours(0, 0, 0, 0);

    if (scheduleDate < today) return "bg-secondary";
    if (scheduleDate.getTime() === today.getTime()) return "bg-success";
    return "bg-primary";
};

const getScheduleRowClass = (dateStr: string) => {
    const scheduleDate = new Date(dateStr);
    const today = new Date();
    today.setHours(0, 0, 0, 0);
    scheduleDate.setHours(0, 0, 0, 0);

    if (scheduleDate.getTime() === today.getTime()) return "table-success";
    return "";
};

const isToday = (dateStr: string) => {
    const date = new Date(dateStr);
    const today = new Date();
    return (
        date.getDate() === today.getDate() &&
        date.getMonth() === today.getMonth() &&
        date.getFullYear() === today.getFullYear()
    );
};

const formatShortDate = (dateStr: string) => {
    const date = new Date(dateStr);
    return `${date.getDate()}/${date.getMonth() + 1}`;
};

const getSchedulesForDay = (dateStr: string) => {
    return schedules.value.filter((schedule) => schedule.date === dateStr);
};
</script>

<style scoped>
.schedule-card {
    border: none;
    border-radius: 12px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
}

.classroom-name {
    font-weight: 600;
    color: #1e293b;
}

.schedule-date {
    font-weight: 500;
    color: #475569;
}

.schedule-time {
    color: #64748b;
}

.title-icon {
    font-size: 1.3rem;
    margin-right: 0.5rem;
}

.calendar-grid {
    display: grid;
    grid-template-columns: repeat(7, 1fr);
    gap: 1rem;
}

.calendar-day {
    border: 2px solid #e2e8f0;
    border-radius: 8px;
    padding: 1rem;
    min-height: 150px;
    transition: all 0.3s ease;
}

.calendar-day:hover {
    border-color: #06b6d4;
    box-shadow: 0 2px 8px rgba(6, 182, 212, 0.2);
}

.calendar-day.today {
    border-color: #10b981;
    background: linear-gradient(135deg, #f0fdf4 0%, #dcfce7 100%);
}

.day-header {
    text-align: center;
    margin-bottom: 0.75rem;
    padding-bottom: 0.75rem;
    border-bottom: 1px solid #e2e8f0;
}

.day-name {
    font-weight: 600;
    color: #1e293b;
    font-size: 0.9rem;
}

.day-date {
    color: #64748b;
    font-size: 0.8rem;
    margin-top: 0.25rem;
}

.day-schedules {
    display: flex;
    flex-direction: column;
    gap: 0.5rem;
}

.schedule-item {
    background: linear-gradient(135deg, #e0f2fe 0%, #bae6fd 100%);
    padding: 0.5rem;
    border-radius: 6px;
    border-left: 3px solid #0891b2;
}

.schedule-classroom {
    font-weight: 600;
    color: #0369a1;
    font-size: 0.85rem;
    margin-bottom: 0.25rem;
}

.schedule-time-small {
    color: #0284c7;
    font-size: 0.75rem;
}

.no-schedule {
    text-align: center;
    color: #94a3b8;
    font-size: 0.85rem;
    padding: 1rem 0;
}

@media (max-width: 992px) {
    .calendar-grid {
        grid-template-columns: repeat(4, 1fr);
    }
}

@media (max-width: 768px) {
    .calendar-grid {
        grid-template-columns: repeat(2, 1fr);
    }
}

@media (max-width: 576px) {
    .calendar-grid {
        grid-template-columns: 1fr;
    }
}
</style>
