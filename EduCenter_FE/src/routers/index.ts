import { createRouter, createWebHistory } from "vue-router";
import LoginView from "../views/LoginView.vue";
import AdminLayout from "../layouts/AdminLayout.vue";

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
            meta: { requiresAuth: true },
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
            ],
        },
    ],
});

router.beforeEach((to, from, next) => {
    const token = localStorage.getItem("access_token");

    if (to.matched.some((route) => route.meta.requiresAuth) && !token) {
        next({ name: "login" });
    } else {
        next();
    }
});

export default router;
