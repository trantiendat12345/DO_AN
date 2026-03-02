import { ref, computed } from "vue";
import { getMyClassrooms } from "../services/teacherPortal.service";
import type { TeacherClassroom } from "../types/Teacher";
import { useToast } from "./useToast";

/**
 * Composable để quản lý danh sách lớp học của giáo viên
 */
export function useTeacherClassrooms() {
    const toast = useToast();
    const classrooms = ref<TeacherClassroom[]>([]);
    const loading = ref(false);
    const error = ref<string | null>(null);

    // Pagination
    const currentPage = ref(0);
    const pageSize = ref(10);
    const totalElements = ref(0);
    const totalPages = ref(0);

    /**
     * Lấy danh sách lớp học
     */
    const fetchClassrooms = async (page: number = 0) => {
        loading.value = true;
        error.value = null;
        currentPage.value = page;
        try {
            const response = await getMyClassrooms(page, pageSize.value);
            classrooms.value = response.content;
            totalElements.value = response.totalElements;
            totalPages.value = response.totalPages;
        } catch (err: any) {
            error.value =
                err.response?.data?.message ||
                "Không thể tải danh sách lớp học";
            toast.error(error.value);
        } finally {
            loading.value = false;
        }
    };

    /**
     * Chuyển trang
     */
    const goToPage = (page: number) => {
        if (page >= 0 && page < totalPages.value) {
            fetchClassrooms(page);
        }
    };

    /**
     * Trang trước
     */
    const previousPage = () => {
        if (currentPage.value > 0) {
            goToPage(currentPage.value - 1);
        }
    };

    /**
     * Trang sau
     */
    const nextPage = () => {
        if (currentPage.value < totalPages.value - 1) {
            goToPage(currentPage.value + 1);
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
        classrooms,
        loading,
        error,
        currentPage,
        pageSize,
        totalElements,
        totalPages,
        hasPrevious,
        hasNext,
        fetchClassrooms,
        goToPage,
        previousPage,
        nextPage,
    };
}
