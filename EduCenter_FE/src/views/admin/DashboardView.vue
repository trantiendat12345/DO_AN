<template>
  <AdminHeader />

  <div class="row g-3 mb-3">
    <SummaryCard
      title="Tổng số học sinh"
      value=""
      note=""
    />
    <SummaryCard
      title="Tổng số lớp học"
      value=""
      note=""
    />
    <SummaryCard 
      title="Tổng số giáo viên" 
      value="" 
      note="" 
    />
    <SummaryCard
      title="Tổng số phụ huynh"
      value=""
      note=""
    />
  </div>
</template>

<script setup lang="ts">
import AdminHeader from "../../components/admin/AdminHeader.vue";
import SummaryCard from "../../components/admin/SummaryCard.vue";
</script>
