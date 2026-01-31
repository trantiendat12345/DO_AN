export interface PaymentRequest {
    feeId: number;
    paymentMethod: "CASH" | "BANK_TRANSFER" | "MOMO" | "ZALO_PAY";
    amountPaid: number;
}

export interface Payment {
    paymentId: number;
    paymentMethod: "CASH" | "BANK_TRANSFER" | "MOMO" | "ZALO_PAY";
    amountPaid: number;
    status: string;
}
