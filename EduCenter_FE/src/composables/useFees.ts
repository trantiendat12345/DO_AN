import { ref, onMounted } from "vue";
import { useToast } from "./useToast";
import { Message } from "../constant/Message";
import type { Fee } from "../types/Fee";
import * as FeeService from "../services/fee.service";

export function useFees() {
    const fees = ref<Fee[]>([]);
    const page = ref(0);
    const size = ref(10);
    const totalPages = ref(0);
    const loading = ref(false);

    const { error } = useToast();

    async function fetchFees() {
        loading.value = true;
        try {
            const data = await FeeService.getAllFees(page.value, size.value);
            fees.value = data.content;
            totalPages.value = data.totalPages;
        } catch (e: any) {
            error(e.response?.data || Message.STUDENT_FETCH_ERROR);
        } finally {
            loading.value = false;
        }
    }

    function goToPage(p: number) {
        if (p < 0 || p >= totalPages.value) return;
        page.value = p;
        fetchFees();
    }

    onMounted(fetchFees);
    return {
        fetchFees,
        fees,
        page,
        totalPages,
        goToPage,
    };
}
