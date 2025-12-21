import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import AdminLayout from '../layouts/AdminLayout.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    // LOGIN (không sidebar)
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      component: LoginView
    },

    // ADMIN (có sidebar)
    {
      path: '/admin',
      component: AdminLayout,
      redirect: '/admin/dashboard',
      children: [
        {
          path: 'dashboard',
          component: () => import('../views/admin/DashboardView.vue')
        },
        {
          path: 'students',
          component: () => import('../views/admin/StudentView.vue')
        },
        {
          path: 'teachers',
          component: () => import('../views/admin/TeacherView.vue')
        },
      ]
    }
  ]
})

export default router
