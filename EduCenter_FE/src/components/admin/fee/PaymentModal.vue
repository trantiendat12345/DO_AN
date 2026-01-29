<template>
    <div class="modal fade" id="paymentModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Đóng học phí</h5>
                </div>

                <div class="modal-body">
                    <p>
                        <b>{{ student?.fullName }}</b>
                    </p>
                    <p>Còn nợ: {{ formatPrice(student?.remaining) }}</p>

                    <select v-model="method" class="form-select">
                        <option value="CASH">Tiền mặt</option>
                        <option value="BANK">Ngân hàng</option>
                        <option value="MOMO">MoMo</option>
                        <option value="ZALOPAY">ZaloPay</option>
                    </select>

                    <input
                        v-model="amount"
                        type="number"
                        class="form-control mt-2"
                        placeholder="Nhập số tiền"
                    />

                    <div v-if="showQr" class="text-center mt-3">
                        <img src="./qr-demo.png" class="img-fluid" />
                    </div>
                </div>

                <div class="modal-footer">
                    <button class="btn btn-secondary" data-bs-dismiss="modal">
                        Hủy
                    </button>
                    <button class="btn btn-primary" @click="confirm">
                        Xác nhận
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import { formatPrice } from "../../../util/formats";

defineProps<{ student: any }>();
const emit = defineEmits(["submit"]);

const method = ref("CASH");
const amount = ref(0);
const showQr = ref(false);

function confirm() {
    if (method.value === "CASH") {
        emit("submit", { method: method.value, amount: amount.value });
    } else {
        showQr.value = true;
    }
}
</script>
