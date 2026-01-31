import api from "../api/axios";
import type { Payment } from "../types/Payment";

export function createPayment(payment: Payment) {
    return api.post("/admin/create-payment", payment).then((res) => res.data);
}

export function confirmPayment(paymentId: number) {
    return api
        .post(`/admin/confirm-payment/${paymentId}`)
        .then((res) => res.data);
}
