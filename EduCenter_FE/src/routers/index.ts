import { createRouter, createWebHistory } from "vue-router";
import LoginView from "../views/LoginView.vue";
import AdminLayout from "../layouts/AdminLayout.vue";
import StudentLayout from "../layouts/StudentLayout.vue";

const router = createRouter({
    history: createWebHistory(),
    routes: [
        // LOGIN (không sidebar)
        {
            path: "/",
            redirect: "/login",
        },
        {
            path: "/login",
            name: "login",
            component: LoginView,
        },

        // ADMIN (có sidebar)
        {
            path: "/admin",
            component: AdminLayout,
            meta: { requiresAuth: true, role: "ROLE_ADMIN" },
            redirect: "/admin/dashboard",
            children: [
                {
                    path: "dashboard",
                    component: () => import("../views/admin/DashboardView.vue"),
                },
                {
                    path: "students",
                    component: () => import("../views/admin/StudentView.vue"),
                },
                {
                    path: "teachers",
                    component: () => import("../views/admin/TeacherView.vue"),
                },
                {
                    path: "classrooms",
                    component: () => import("../views/admin/ClassroomView.vue"),
                },
                {
                    path: "accounts",
                    component: () => import("../views/admin/AccountView.vue"),
                },
                {
                    path: "courses",
                    component: () => import("../views/admin/CourseView.vue"),
                },
                {
                    path: "assign-teachers",
                    component: () =>
                        import("../views/admin/AssignTeacherView.vue"),
                },
                {
                    path: "fee",
                    component: () =>
                        import("../views/admin/FeeManagementView.vue"),
                },
                {
                    path: "scores",
                    component: () => import("../views/admin/ScoreView.vue"),
                },
            ],
        },

        // STUDENT (có sidebar) - Cổng học viên
        {
            path: "/student",
            component: StudentLayout,
            meta: { requiresAuth: true, role: "ROLE_STUDENT" },
            redirect: "/student/dashboard",
            children: [
                {
                    path: "dashboard",
                    name: "student-dashboard",
                    component: () =>
                        import("../views/student/StudentDashboardView.vue"),
                },
                {
                    path: "scores",
                    name: "student-scores",
                    component: () =>
                        import("../views/student/StudentScoresView.vue"),
                },
            ],
        },
    ],
});

/**
 * Router Guard
 * - Kiểm tra authentication (token)
 * - Kiểm tra authorization (role)
 * - Redirect về trang phù hợp
 */
router.beforeEach((to, from, next) => {
    const token = localStorage.getItem("access_token");
    const userRole = localStorage.getItem("role");

    // Check authentication
    if (to.matched.some((route) => route.meta.requiresAuth)) {
        if (!token) {
            // Chưa đăng nhập -> redirect về login
            next({ name: "login" });
            return;
        }

        // Check authorization (role)
        if (to.meta.role && to.meta.role !== userRole) {
            // Sai role -> redirect về dashboard phù hợp
            if (userRole === "ROLE_ADMIN") {
                next("/admin/dashboard");
            } else if (userRole === "ROLE_STUDENT") {
                next("/student/dashboard");
            } else {
                // Role không hợp lệ -> logout
                next({ name: "login" });
            }
            return;
        }
    }

    next();
});

export default router;
