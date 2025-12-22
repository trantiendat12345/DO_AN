<template>
    <AdminHeader />

    <div class="row g-3 mb-3">
        <SummaryCard
            title="Tổng số học sinh"
            :value="summary.totalStudents"
            note=""
        />
        <SummaryCard
            title="Tổng số lớp học"
            :value="summary.totalClassrooms"
            note=""
        />
        <SummaryCard
            title="Tổng số giáo viên"
            :value="summary.totalTeachers"
            note=""
        />
    </div>
</template>

<script setup lang="ts">
import AdminHeader from "../../components/admin/AdminHeader.vue";
import SummaryCard from "../../components/admin/SummaryCard.vue";
import { useDashboard } from "../../composables/useDashboard";

const { summary } = useDashboard();
</script>
