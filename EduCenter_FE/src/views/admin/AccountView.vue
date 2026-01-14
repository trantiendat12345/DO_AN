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
            @edit="onEditAccount"
            @delete="onDeleteAccount"
        />

        <EditAccountModal
            :account="selectedAccount"
            @submit="onUpdateAccount"
        />

        <Pagination :page="page" :total-pages="totalPages" @change="goToPage" />

        <ConfirmDeleteModal
            v-if="selectedAccount"
            :fullName="selectedAccount.username"
            :code="selectedAccount.username"
            @confirm="onConfirmDelete"
        />
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
import EditAccountModal from "../../components/admin/account/EditAccountModal.vue";
import ConfirmDeleteModal from "../../components/common/ConfirmDeleteModal.vue";

const {
    accounts,
    page,
    size,
    totalPages,
    goToPage,
    createAccount,
    updateAccount,
    deleteAccount,
} = useAccounts();

const modalKey = ref(0); // Để reset modal mỗi lần mở

const selectedAccount = ref<Account | null>(null);

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

function onEditAccount(account: Account) {
    selectedAccount.value = account;
    const modalEl = document.getElementById("editAccountModal");
    if (modalEl) {
        const modal = new Modal(modalEl);
        modal.show();
    }
}

async function onUpdateAccount(account: Account) {
    try {
        await updateAccount(account); // nếu lỗi → nhảy catch

        const modalEl = document.getElementById("editAccountModal");
        if (modalEl) {
            const modal = Modal.getInstance(modalEl);
            modal?.hide();
        }
    } catch {
        // ❌ KHÔNG đóng modal
        // Toast lỗi đã được xử lý trong composable
    }
}

function onDeleteAccount(account: Account) {
    selectedAccount.value = account;
    const modalEl = document.getElementById("confirmDeleteModal");
    if (modalEl) {
        const modal = new Modal(modalEl);
        modal.show();
    }
}

async function onConfirmDelete() {
    try {
        if (selectedAccount.value) {
            await deleteAccount(selectedAccount.value);

            const modalEl = document.getElementById("confirmDeleteModal");
            if (modalEl) {
                const modal = Modal.getInstance(modalEl);
                modal?.hide();
            }
        }
    } catch (error) {}
}
</script>
