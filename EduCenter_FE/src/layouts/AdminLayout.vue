<template>
  <div class="container-fluid">
    <div class="row min-vh-100">
      <Sidebar />
      <main class="col-md-9 col-lg-10 px-4 py-4">
        <router-view />
        <AppToast />
        <footer class="text-center py-3 border-top mt-4">
          <small class="text-secondary">
            © 2025 English Center Management System
          </small>
        </footer>
      </main>
    </div>
  </div>
</template>

<script setup lang="ts">
import Sidebar from '../components/admin/Sidebar.vue';
import AppToast from '../components/common/AppToast.vue';
</script>

<style scoped>
@import '../assets/css/admin.css';
</style>
