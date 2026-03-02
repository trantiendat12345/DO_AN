export function formatPrice(value: number | string): string {
    if (!value) return "0";
    return Number(value).toLocaleString("vi-VN");
}

export function formatDate(dateString: string | null | undefined): string {
    if (!dateString) return "N/A";
    const date = new Date(dateString);
    return date.toLocaleDateString("vi-VN");
}
