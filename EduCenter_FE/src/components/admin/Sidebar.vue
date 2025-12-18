<template>
  <nav class="col-md-3 col-lg-2 d-md-block sidebar py-3 px-3">
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
      <li class="nav-item mt-2">
        <span class="text-uppercase fw-bold">Tổng quan</span>
      </li>
      <li class="nav-item">
        <RouterLink to="/admin/dashboard" class="nav-link active"
          >🏠 Dashboard</RouterLink
        >
      </li>

      <li class="nav-item mt-3">
        <span class="text-uppercase fw-bold">Quản lý người dùng</span>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" to="/admin/students">👨‍🎓 Học sinh</RouterLink>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" to="/admin/teachers">👨‍🏫 Giáo viên</RouterLink>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" to="/admin/parents">👨‍👩‍👧 Phụ huynh</RouterLink>
      </li>

      <li class="nav-item mt-3">
        <span class="text-uppercase fw-bold">Đào tạo</span>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" to="/admin/classes">🏫 Lớp học</RouterLink>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" to="/admin/assign-teachers"><span>📋</span> Phân công giáo viên</RouterLink>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" to="/admin/schedules"><span>📅</span> Lịch học</RouterLink>
      </li>
      <li class="nav-item mt-3">
        <span class="text-uppercase fw-bold fs-6">Tài chính &amp; Học tập</span>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" to="/admin/tuition"><span>💰</span> Học phí</RouterLink>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" to="/admin/grades"><span>📊</span> Điểm số tổng hợp</RouterLink>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" to="/admin/documents"><span>📂</span> Tài liệu học tập</RouterLink>
      </li>
      <li class="nav-item mt-3">
        <span class="text-uppercase fw-bold fs-6">Báo cáo &amp; Thông báo</span>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" to="/admin/reports"><span>📈</span> Báo cáo</RouterLink>
      </li>
      <li class="nav-item">
        <RouterLink class="nav-link" to="/admin/notifications"><span>🔔</span> Quản lý thông báo</RouterLink>
      </li>
    </ul>
  </nav>
</template>

<script setup lang="ts"></script>
