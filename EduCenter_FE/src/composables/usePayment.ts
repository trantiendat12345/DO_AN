import * as PaymentService from "../services/payment.service";
import { useToast } from "./useToast";

export function usePayments() {
    const { success, error } = useToast();

    async function payByCash(payment: any) {
        try {
            await PaymentService.createPayment(payment);
            success("Thanh toán tiền mặt thành công");
        } catch (e: any) {
            error("Thanh toán tiền mặt thất bại");
            throw e;
        }
    }

    async function createQrPayment(payment: any) {
        try {
            return await PaymentService.createPayment(payment);
        } catch (e: any) {
            error("Tạo QR thất bại");
            throw e;
        }
    }

    async function confirmQrPayment(paymentId: number) {
        try {
            await PaymentService.confirmPayment(paymentId);
            success("Xác nhận thanh toán thành công");
        } catch (e: any) {
            error("Xác nhận QR thất bại");
            throw e;
        }
    }

    return {
        payByCash,
        createQrPayment,
        confirmQrPayment,
    };
}
