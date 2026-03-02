import { ref, onMounted } from "vue";
import { getTeacherInfo } from "../services/teacherPortal.service";
import type { TeacherInfo } from "../types/Teacher";
import { useToast } from "./useToast";

/**
 * Composable để quản lý thông tin giáo viên
 */
export function useTeacherInfo() {
    const toast = useToast();
    const teacherInfo = ref<TeacherInfo | null>(null);
    const loading = ref(false);
    const error = ref<string | null>(null);

    /**
     * Lấy thông tin giáo viên
     */
    const fetchTeacherInfo = async () => {
        loading.value = true;
        error.value = null;
        try {
            teacherInfo.value = await getTeacherInfo();
        } catch (err: any) {
            error.value =
                err.response?.data?.message ||
                "Không thể tải thông tin giáo viên";
            toast.error(error.value);
        } finally {
            loading.value = false;
        }
    };

    onMounted(() => {
        fetchTeacherInfo();
    });

    return {
        teacherInfo,
        loading,
        error,
        fetchTeacherInfo,
    };
}
