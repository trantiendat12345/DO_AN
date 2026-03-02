import { ref, onMounted } from "vue";
import { getMySchedule } from "../services/teacherPortal.service";
import type { TeacherSchedule } from "../types/Teacher";
import { useToast } from "./useToast";

/**
 * Composable để quản lý lịch dạy của giáo viên
 */
export function useTeacherSchedule() {
    const toast = useToast();
    const schedules = ref<TeacherSchedule[]>([]);
    const loading = ref(false);
    const error = ref<string | null>(null);

    /**
     * Lấy lịch dạy
     */
    const fetchSchedule = async () => {
        loading.value = true;
        error.value = null;
        try {
            schedules.value = await getMySchedule();
        } catch (err: any) {
            error.value =
                err.response?.data?.message || "Không thể tải lịch dạy";
            toast.error(error.value);
        } finally {
            loading.value = false;
        }
    };

    onMounted(() => {
        fetchSchedule();
    });

    return {
        schedules,
        loading,
        error,
        fetchSchedule,
    };
}
