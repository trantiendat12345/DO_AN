import { ref } from "vue";
import { getStudentsInClass } from "../services/teacherPortal.service";
import type { StudentInClass } from "../types/Teacher";
import { useToast } from "./useToast";

/**
 * Composable để quản lý danh sách học viên trong lớp
 */
export function useClassroomStudents() {
    const toast = useToast();
    const students = ref<StudentInClass[]>([]);
    const loading = ref(false);
    const error = ref<string | null>(null);

    /**
     * Lấy danh sách học viên trong lớp
     */
    const fetchStudents = async (classroomName: string) => {
        loading.value = true;
        error.value = null;
        try {
            students.value = await getStudentsInClass(classroomName);
        } catch (err: any) {
            error.value =
                err.response?.data?.message ||
                "Không thể tải danh sách học viên";
            toast.error(error.value);
        } finally {
            loading.value = false;
        }
    };

    return {
        students,
        loading,
        error,
        fetchStudents,
    };
}
