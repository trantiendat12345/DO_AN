import { ref, onMounted } from "vue";
import * as StudentService from "../services/student.service";
import { useToast } from "./useToast";
import type { Student } from "../types/Student";
import { Message } from "../constant/Message";
import type { Fee } from "../types/Fee";
import * as FeeService from "../services/fee.service";

export function useFees() {
    const fees = ref<Fee[]>([]);
    const page = ref(0);
    const size = ref(10);
    const totalPages = ref(0);
    const loading = ref(false);

    const { success, error } = useToast();

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

    async function createStudent(data: Partial<Student>) {
        loading.value = true;
        try {
            await StudentService.createStudent(data);
            success(Message.STUDENT_ADD_SUCCESS);
            page.value = 0;
            await fetchFees();
        } catch (err: any) {
            error(err.response?.data || Message.STUDENT_CREATE_ERROR);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    async function updateStudent(student: Student) {
        loading.value = true;
        try {
            await StudentService.updateStudent(student.studentCode, student);

            success(Message.STUDENT_UPDATE_SUCCESS);

            await fetchFees(); // ✅ RELOAD DATA
        } catch (e: any) {
            error(e.response?.data || Message.STUDENT_UPDATE_ERROR);
            throw e;
        } finally {
            loading.value = false;
        }
    }

    async function deleteStudent(studentCode: string) {
        loading.value = true;
        try {
            await StudentService.deleteStudent(studentCode);

            success(Message.STUDENT_DELETE_SUCCESS); // ✅ TOAST

            // Nếu xoá ở trang cuối → lùi trang
            if (fees.value.length === 1 && page.value > 0) {
                page.value--;
            }

            await fetchFees(); // ✅ RELOAD DATA
        } catch (e: any) {
            error(e.response?.data || Message.STUDENT_DELETE_ERROR);
            throw e;
        } finally {
            loading.value = false;
        }
    }

    onMounted(fetchFees);
    return {
        fees,
        page,
        totalPages,
        goToPage,
        createStudent,
        updateStudent,
        deleteStudent,
    };
}
