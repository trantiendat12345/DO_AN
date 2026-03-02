<template>
    <div class="student-layout">
        <div class="student-wrapper">
            <StudentSidebar />
            <main class="main-content">
                <div class="content-wrapper">
                    <router-view />
                </div>
                <footer class="student-footer text-center py-3">
                    <small class="text-muted">
                        © 2025 English Center Management System | Built with ❤️
                    </small>
                </footer>
            </main>
        </div>
    </div>
</template>

<script setup lang="ts">
import StudentSidebar from "../components/student/StudentSidebar.vue";
</script>

<style scoped>
@import "../assets/css/admin.css";

.student-layout {
    min-height: 100vh;
    background: linear-gradient(135deg, #f0fdfa 0%, #ecfeff 50%, #f0f9ff 100%);
}

.student-wrapper {
    display: flex;
    min-height: 100vh;
}

.main-content {
    flex: 1;
    padding: 1.5rem 2rem;
    display: flex;
    flex-direction: column;
    min-height: 100vh;
    overflow-x: hidden;
}

.content-wrapper {
    flex: 1;
}

.student-footer {
    margin-top: auto;
    border-top: 1px solid rgba(0, 0, 0, 0.05);
    padding-top: 1.5rem !important;
}

@media (max-width: 768px) {
    .student-wrapper {
        flex-direction: column;
    }

    .main-content {
        padding: 1rem;
    }
}
</style>
