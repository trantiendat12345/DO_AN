<template>
  <AdminHeader />

  <div class="row g-3 mb-3">
    <SummaryCard
      title="Học sinh đang theo học"
      value="320"
      note="+15 trong 7 ngày"
    />
    <SummaryCard
      title="Lớp đang hoạt động"
      value="24"
      note="3 lớp mới trong tháng"
    />
    <SummaryCard title="Giáo viên" value="18" note="2 part-time" />
    <SummaryCard
      title="Học phí quá hạn"
      value="12"
      note="Tổng nợ: 15,000,000đ"
      danger
    />
  </div>
</template>

<script setup lang="ts">
import AdminHeader from "../../components/admin/AdminHeader.vue";
import SummaryCard from "../../components/admin/SummaryCard.vue";
</script>
