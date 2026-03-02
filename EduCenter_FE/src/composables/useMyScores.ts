import { ref, onMounted, computed } from "vue";
import { getMyScores } from "../services/student.service";
import { useToast } from "./useToast";

/**
 * Composable để quản lý danh sách điểm của học viên
 * Logic phân trang và filter được xử lý bởi StudentPortalService ở backend
 */
export function useMyScores() {
    const toast = useToast();

    // State
    const scores = ref<any>({
        content: [],
        totalElements: 0,
        totalPages: 0,
        number: 0,
    });
    const loading = ref(false);
    const error = ref<string | null>(null);
    const currentPage = ref(0);
    const pageSize = ref(10);

    /**
     * Fetch danh sách điểm từ backend
     * Backend tự động filter theo học viên đang đăng nhập
     */
    const fetchScores = async (page = 0) => {
        loading.value = true;
        error.value = null;

        try {
            const data = await getMyScores(page, pageSize.value);
            scores.value = data;
            currentPage.value = page;
        } catch (err: any) {
            const errorMessage =
                err?.response?.data?.message || "Không thể tải danh sách điểm";
            error.value = errorMessage;
            toast.error(errorMessage);
        } finally {
            loading.value = false;
        }
    };

    /**
     * Xử lý sự kiện chuyển trang
     */
    const handlePageChange = (page: number) => {
        fetchScores(page);
    };

    /**
     * Refresh danh sách điểm
     */
    const refresh = () => {
        fetchScores(currentPage.value);
    };

    /**
     * Computed: Có dữ liệu không?
     */
    const hasData = computed(() => {
        return scores.value.content && scores.value.content.length > 0;
    });

    // Tự động load khi component mount
    onMounted(() => {
        fetchScores();
    });

    return {
        scores,
        loading,
        error,
        currentPage,
        pageSize,
        hasData,
        fetchScores,
        handlePageChange,
        refresh,
    };
}
