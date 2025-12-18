<template>
  <div class="d-flex flex-wrap mb-4 align-items-center">
    <div class="me-4">
      <h1 class="h4 mb-1 fw-bold">Dashboard Admin</h1>
      <p class="mb-0 text-secondary">Tổng quan hoạt động trung tâm</p>
    </div>

    <div class="flex-grow-1 me-4">
      <input class="form-control rounded-pill"
        placeholder="Tìm kiếm học sinh, giáo viên, lớp học..." />
    </div>

    <div class="d-flex align-items-center gap-2">
      <button class="btn btn-light position-relative rounded-circle p-2">
        🔔
        <span class="badge bg-danger position-absolute top-0 start-100 translate-middle">3</span>
      </button>

      <div class="dropdown">
        <button class="btn p-0" data-bs-toggle="dropdown">
          <div class="admin-avatar">A</div>
        </button>
        <ul class="dropdown-menu dropdown-menu-end">
          <li><h6 class="dropdown-header">Nguyễn Admin</h6></li>
          <li><span class="dropdown-item-text small">Administrator</span></li>
          <li><hr class="dropdown-divider"></li>
          <li><a class="dropdown-item" href="#">Thông tin cá nhân</a></li>
          <li><a class="dropdown-item" href="#">Đăng xuất</a></li>
        </ul>
      </div>
    </div>
  </div>
</template>
