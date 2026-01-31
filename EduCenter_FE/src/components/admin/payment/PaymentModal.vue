<template>
    <div class="modal fade" id="paymentModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- HEADER -->
                <div class="modal-header">
                    <h5 class="modal-title">Đóng học phí</h5>
                    <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                    ></button>
                </div>

                <!-- BODY -->
                <div class="modal-body">
                    <p>
                        <b>{{ student?.fullName }}</b>
                    </p>
                    <p>Còn nợ: {{ formatPrice(student?.remaining) }}</p>

                    <!-- Method -->
                    <select v-model="payment.paymentMethod" class="form-select">
                        <option value="CASH">Tiền mặt</option>
                        <option value="BANK">Ngân hàng</option>
                        <option value="MOMO">MoMo</option>
                        <option value="ZALOPAY">ZaloPay</option>
                    </select>

                    <!-- Amount -->
                    <input
                        v-model.number="payment.amountPaid"
                        type="number"
                        class="form-control mt-2"
                        min="0"
                        placeholder="Nhập số tiền"
                    />

                    <!-- QR -->
                    <div v-if="shouldShowQr" class="text-center mt-3">
                        <img src="./qr-demo.png" class="img-fluid" />
                        <p class="mt-2 text-muted">
                            Vui lòng quét mã để thanh toán
                        </p>
                        <button
                            class="btn btn-success mt-2"
                            @click="confirmPaid"
                        >
                            Tôi đã thanh toán
                        </button>
                    </div>
                </div>

                <!-- FOOTER -->
                <div class="modal-footer">
                    <button class="btn btn-secondary" data-bs-dismiss="modal">
                        Hủy
                    </button>

                    <button
                        v-if="!shouldShowQr"
                        class="btn btn-primary"
                        :disabled="!canSubmit"
                        @click="submit"
                    >
                        Xác nhận
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, computed, watch } from "vue";
import { formatPrice } from "../../../util/formats";
import type { PaymentRequest } from "../../../types/Payment";

/* PROPS + EMIT */
const props = defineProps<{ student: any }>();
const emit = defineEmits(["submit", "confirm-paid"]);

/* STATE */
const payment = ref<PaymentRequest>({
    feeId: 0,
    paymentMethod: "CASH",
    amountPaid: 0,
});

const confirmed = ref(false);

/* RESET KHI MỞ MODAL */
watch(
    () => props.student,
    (s) => {
        if (s) {
            payment.value = {
                feeId: s.feeId,
                paymentMethod: "CASH",
                amountPaid: 0,
            };
            confirmed.value = false;
        }
    },
);

/* ===== VIEW LOGIC ===== */

const canSubmit = computed(() => {
    return (
        payment.value.amountPaid > 0 &&
        payment.value.amountPaid <= props.student?.remaining
    );
});

const shouldShowQr = computed(() => {
    return confirmed.value && payment.value.paymentMethod !== "CASH";
});

/* ===== ACTION ===== */

function submit() {
    if (payment.value.paymentMethod === "CASH") {
        emit("submit", payment.value);
    } else {
        confirmed.value = true;
    }
}

function confirmPaid() {
    emit("confirm-paid");
}
</script>
