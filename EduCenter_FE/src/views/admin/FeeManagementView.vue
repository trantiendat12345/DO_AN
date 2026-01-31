<template>
    <AdminHeader
        title="Quản lý học phí"
        subtitle="Danh sách học phí của học viên"
    />

    <div class="table-wrapper">
        <FeeTable
            :fees="fees"
            :page="page"
            :size="10"
            @pay="openPaymentModal"
        />

        <Pagination :page="page" :total-pages="totalPages" @change="goToPage" />
    </div>

    <!-- PAYMENT MODAL -->
    <PaymentModal
        :student="selectedStudent"
        @submit="handleSubmitPayment"
        @confirm-paid="handleConfirmQr"
    />
</template>

<script setup lang="ts">
import { ref } from "vue";
import { Modal } from "bootstrap";

import AdminHeader from "../../components/admin/AdminHeader.vue";
import Pagination from "../../components/common/Pagination.vue";
import FeeTable from "../../components/admin/fee/FeeTable.vue";
import PaymentModal from "../../components/admin/payment/PaymentModal.vue";

import { useFees } from "../../composables/useFees";
import { usePayments } from "../../composables/usePayment";

/* ================= STATE ================= */

const selectedStudent = ref<any>(null);
const currentPaymentId = ref<number | null>(null);

/* ================= COMPOSABLE ================= */

const { fetchFees, fees, page, totalPages, goToPage } = useFees();
const { payByCash, createQrPayment, confirmQrPayment } = usePayments();

/* ================= MODAL ================= */

function openPaymentModal(student: any) {
    selectedStudent.value = student;
    currentPaymentId.value = null;

    const modalEl = document.getElementById("paymentModal");
    modalEl && new Modal(modalEl).show();
}

function closePaymentModal() {
    const modalEl = document.getElementById("paymentModal");
    Modal.getInstance(modalEl!)?.hide();
}

/* ================= HANDLERS ================= */

/**
 * SUBMIT (CASH / QR)
 */
async function handleSubmitPayment(payment: any) {
    // CASH
    if (payment.paymentMethod === "CASH") {
        await payByCash(payment);
        await fetchFees();
        closePaymentModal();
        return;
    }

    // QR → tạo payment PENDING
    const res = await createQrPayment(payment);
    currentPaymentId.value = res.paymentId;
}

/**
 * CONFIRM QR
 */
async function handleConfirmQr() {
    if (!currentPaymentId.value) {
        console.error("paymentId is null");
        return;
    }

    await confirmQrPayment(currentPaymentId.value);
    await fetchFees();
    closePaymentModal();
}
</script>
