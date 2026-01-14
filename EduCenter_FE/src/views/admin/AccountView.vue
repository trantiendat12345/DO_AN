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
        <AccountTable
            :account="accounts"
            :page="page"
            :size="size"
            @edit=""
            @delete=""
        />

        <Pagination :page="page" :total-pages="totalPages" @change="goToPage" />
    </div>
    <AddAccountModal :key="modalKey" @submit="onAddAccount" />
</template>

<script setup lang="ts">
import { ref } from "vue";
import AccountTable from "../../components/admin/account/AccountTable.vue";
import AdminHeader from "../../components/admin/AdminHeader.vue";
import { useAccounts } from "../../composables/useAccount";
import Pagination from "../../components/common/Pagination.vue";
import type { Account } from "../../types/Account";
import { Modal } from "bootstrap";
import AddAccountModal from "../../components/admin/account/AddAccountModal.vue";

const { accounts, page, size, totalPages, goToPage, createAccount } =
    useAccounts();

const modalKey = ref(0); // Để reset modal mỗi lần mở

async function onAddAccount(account: Partial<Account>) {
    try {
        await createAccount(account);

        const modalEl = document.getElementById("addAccountModal");
        if (modalEl) {
            const modal = Modal.getInstance(modalEl);
            modal?.hide();
        }
        modalKey.value++; // Reset modal
    } catch {}
}
</script>
