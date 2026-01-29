<template>
    <table class="table table-striped align-middle">
        <thead>
            <tr>
                <th>#</th>
                <th>Mã HV</th>
                <th>Họ tên</th>
                <th>Tổng học phí</th>
                <th>Đã đóng</th>
                <th>Còn nợ</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(f, i) in fees" :key="f.studentCode">
                <td>{{ i + 1 }}</td>
                <td>{{ f.studentCode }}</td>
                <td>{{ f.fullName }}</td>
                <td>{{ formatPrice(f.amount) }}</td>
                <td>{{ formatPrice(f.paidAmount) }}</td>
                <td class="text-danger">{{ formatPrice(f.remaining) }}</td>
                <td>
                    <button
                        class="btn btn-success btn-sm"
                        :disabled="f.remaining === 0"
                        @click="$emit('pay', f)"
                    >
                        Đóng học phí
                    </button>
                </td>
            </tr>
        </tbody>
    </table>
</template>

<script setup lang="ts">
import type { Fee } from "../../../types/Fee";
import { formatPrice } from "../../../util/formats";

defineProps<{
    fees: Fee[];
    page: number;
    size: number;
}>();

defineEmits(["pay"]);
</script>
