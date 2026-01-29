<template>
    <AdminHeader
        title="Quản lý học phí"
        subtitle="Danh sách học phí của học viên"
    />

    <div class="d-flex justify-content-between mb-3">
        <div></div>
        <div></div>
    </div>

    <div class="table-wrapper">
        <FeeTable
            :fees="fees"
            :page="page"
            :size="10"
            @pay="openPaymentModal"
        />

        <Pagination :page="page" :total-pages="totalPages" @change="goToPage" />
    </div>

    <PaymentModal :student="selectedStudent" @submit="onConfirmPayment" />
</template>

<script setup lang="ts">
import { ref } from "vue";
import AdminHeader from "../../components/admin/AdminHeader.vue";
import Pagination from "../../components/common/Pagination.vue";
import { Modal } from "bootstrap";
import { useFees } from "../../composables/useFees";

import FeeTable from "../../components/admin/fee/FeeTable.vue";
import PaymentModal from "../../components/admin/fee/PaymentModal.vue";

const selectedStudent = ref<any>(null);

const { fees, page, totalPages, goToPage } = useFees();

function openPaymentModal(student: any) {
    selectedStudent.value = student;
    const modalEl = document.getElementById("paymentModal");
    if (modalEl) {
        const modal = new Modal(modalEl);
        modal?.show();
    }
}

function onConfirmPayment(payload: any) {
    // xử lý sau
}
</script>
