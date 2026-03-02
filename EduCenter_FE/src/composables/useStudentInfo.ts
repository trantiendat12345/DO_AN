import { ref, onMounted } from "vue";
import { getStudentInfo, type StudentInfo } from "../services/student.service";
import { useToast } from "./useToast";

/**
 * Composable để quản lý thông tin học viên
 * Logic gọi API được xử lý bởi StudentPortalService ở backend
 */
export function useStudentInfo() {
    const toast = useToast();

    // State
    const studentInfo = ref<StudentInfo | null>(null);
    const loading = ref(false);
    const error = ref<string | null>(null);

    /**
     * Fetch thông tin học viên từ backend
     * Backend sẽ tự động lấy username từ JWT token
     */
    const fetchStudentInfo = async () => {
        loading.value = true;
        error.value = null;

        try {
            studentInfo.value = await getStudentInfo();
        } catch (err: any) {
            const errorMessage =
                err?.response?.data?.message ||
                "Không thể tải thông tin học viên";
            error.value = errorMessage;
            toast.error(errorMessage);
        } finally {
            loading.value = false;
        }
    };

    /**
     * Refresh thông tin học viên
     */
    const refresh = () => {
        fetchStudentInfo();
    };

    // Tự động load khi component mount
    onMounted(() => {
        fetchStudentInfo();
    });

    return {
        studentInfo,
        loading,
        error,
        fetchStudentInfo,
        refresh,
    };
}
