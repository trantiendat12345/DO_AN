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
        :show-qr="showQr"
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
const showQr = ref(false);

/* ================= COMPOSABLE ================= */

const { fetchFees, fees, page, totalPages, goToPage } = useFees();
const { payByCash, createQrPayment, confirmQrPayment } = usePayments();

/* ================= MODAL ================= */

function openPaymentModal(student: any) {
    selectedStudent.value = student;
    currentPaymentId.value = null;
    showQr.value = false;

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
    console.log("Payment created response:", res);
    // Handle different response structures
    currentPaymentId.value = res?.paymentId || res?.data?.paymentId || res?.id;
    if (!currentPaymentId.value) {
        console.error("paymentId not found in response:", res);
    } else {
        // Show QR after payment is created successfully
        showQr.value = true;
    }
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
    showQr.value = false;
    closePaymentModal();
}
</script>
