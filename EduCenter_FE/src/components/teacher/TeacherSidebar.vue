<template>
    <nav class="sidebar py-4 px-3">
        <!-- LOGO -->
        <div class="d-flex align-items-center mb-4 px-2">
            <div class="sidebar-logo me-3">
                <span class="logo-icon">👨‍🏫</span>
            </div>
            <div>
                <div class="fw-bold text-white" style="font-size: 1.1rem">
                    English Center
                </div>
                <small class="text-cyan-light"> Cổng giáo viên </small>
            </div>
        </div>

        <ul class="nav flex-column mb-4">
            <!-- TỔNG QUAN -->
            <li class="nav-item mt-2">
                <span class="nav-section-title">Tổng quan</span>
            </li>
            <li class="nav-item">
                <router-link
                    to="/teacher/dashboard"
                    class="nav-link"
                    active-class="active"
                >
                    <span class="nav-icon">📊</span> Dashboard
                </router-link>
            </li>

            <!-- GIẢNG DẠY -->
            <li class="nav-item mt-3">
                <span class="nav-section-title">Giảng dạy</span>
            </li>
            <li class="nav-item">
                <router-link
                    to="/teacher/classrooms"
                    class="nav-link"
                    active-class="active"
                >
                    <span class="nav-icon">🏫</span> Lớp học
                </router-link>
            </li>
            <li class="nav-item">
                <router-link
                    to="/teacher/schedule"
                    class="nav-link"
                    active-class="active"
                >
                    <span class="nav-icon">📅</span> Lịch dạy
                </router-link>
            </li>
        </ul>
    </nav>
</template>

<style scoped>
.sidebar-logo {
    width: 48px;
    height: 48px;
    background: linear-gradient(135deg, #06b6d4, #0891b2);
    border-radius: 14px;
    display: flex;
    align-items: center;
    justify-content: center;
    box-shadow: 0 4px 12px rgba(6, 182, 212, 0.4);
}

.logo-icon {
    font-size: 1.5rem;
}

.text-cyan-light {
    color: #67e8f9 !important;
    font-size: 0.8rem;
}

.nav-section-title {
    text-transform: uppercase;
    font-weight: 600;
    color: #64748b;
    font-size: 0.7rem;
    letter-spacing: 0.1em;
    padding: 0.5rem 1rem;
    display: block;
}

.nav-icon {
    margin-right: 10px;
    font-size: 1.1rem;
}

.btn-logout {
    background: linear-gradient(135deg, #ef4444, #dc2626);
    color: white;
    border: none;
    padding: 0.75rem 1rem;
    border-radius: 12px;
    font-weight: 600;
    transition: all 0.3s ease;
}

.btn-logout:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(239, 68, 68, 0.4);
}

.sidebar {
    display: flex;
    flex-direction: column;
    width: 250px;
    min-height: 100vh;
    background: linear-gradient(180deg, #0f172a, #1e293b);
    color: white;
}
</style>
