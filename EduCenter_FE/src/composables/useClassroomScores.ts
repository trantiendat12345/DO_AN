import { ref, computed } from "vue";
import { getScoresInClass } from "../services/teacherPortal.service";
import type { ScoreInClass } from "../types/Teacher";
import { useToast } from "./useToast";

/**
 * Composable để quản lý danh sách điểm trong lớp
 */
export function useClassroomScores() {
    const toast = useToast();
    const scores = ref<ScoreInClass[]>([]);
    const loading = ref(false);
    const error = ref<string | null>(null);

    // Pagination
    const currentPage = ref(0);
    const pageSize = ref(10);
    const totalElements = ref(0);
    const totalPages = ref(0);

    /**
     * Lấy danh sách điểm trong lớp
     */
    const fetchScores = async (classroomName: string, page: number = 0) => {
        loading.value = true;
        error.value = null;
        currentPage.value = page;
        try {
            const response = await getScoresInClass(
                classroomName,
                page,
                pageSize.value,
            );
            scores.value = response.content;
            totalElements.value = response.totalElements;
            totalPages.value = response.totalPages;
        } catch (err: any) {
            error.value =
                err.response?.data?.message || "Không thể tải danh sách điểm";
            toast.error(error.value);
        } finally {
            loading.value = false;
        }
    };

    /**
     * Chuyển trang
     */
    const goToPage = (classroomName: string, page: number) => {
        if (page >= 0 && page < totalPages.value) {
            fetchScores(classroomName, page);
        }
    };

    /**
     * Trang trước
     */
    const previousPage = (classroomName: string) => {
        if (currentPage.value > 0) {
            goToPage(classroomName, currentPage.value - 1);
        }
    };

    /**
     * Trang sau
     */
    const nextPage = (classroomName: string) => {
        if (currentPage.value < totalPages.value - 1) {
            goToPage(classroomName, currentPage.value + 1);
        }
    };

    /**
     * Kiểm tra có trang trước không
     */
    const hasPrevious = computed(() => currentPage.value > 0);

    /**
     * Kiểm tra có trang sau không
     */
    const hasNext = computed(() => currentPage.value < totalPages.value - 1);

    return {
        scores,
        loading,
        error,
        currentPage,
        pageSize,
        totalElements,
        totalPages,
        hasPrevious,
        hasNext,
        fetchScores,
        goToPage,
        previousPage,
        nextPage,
    };
}
