<template>
    <AdminHeader
        title="Quản lý tài khoản"
        subtitle="Quản lý tài khoản người dùng trong hệ thống"
    />
    <button
        class="btn btn-primary mb-3"
        data-bs-toggle="modal"
        data-bs-target="#addAccountModal"
    >
        + Thêm tài khoản
    </button>
    <div class="table-wrapper">
        <AccountTable :account="accounts" :page="page" :size="size" />

        <Pagination :page="page" :total-pages="totalPages" @change="goToPage" />
    </div>
</template>

<script setup lang="ts">
import AccountTable from "../../components/admin/AccountTable.vue";
import AdminHeader from "../../components/admin/AdminHeader.vue";
import { useAccounts } from "../../composables/useAccount";

const { accounts, page, size, totalPages, goToPage } = useAccounts();
</script>
