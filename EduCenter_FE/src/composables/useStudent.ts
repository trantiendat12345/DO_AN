import { ref, onMounted } from "vue";
import * as StudentService from "../services/student.service";
import { useToast } from "./UseToast";
import type { Student } from "../types/Student";
import { Message } from "../constant/Message";

export function useStudents() {
  const students = ref<Student[]>([]);
  const page = ref(0);
  const size = ref(10);
  const totalPages = ref(0);
  const loading = ref(false);

  const { success, error } = useToast();

  async function fetchStudents() {
    loading.value = true;
    try {
      const data = await StudentService.getAllStudents(page.value, size.value);
      students.value = data.content;
      totalPages.value = data.totalPages;
    } catch {
      error(Message.STUDENT_FETCH_ERROR);
    } finally {
      loading.value = false;
    }
  }

  function goToPage(p: number) {
    if (p < 0 || p >= totalPages.value) return;
    page.value = p;
    fetchStudents();
  }

  async function createStudent(data: Partial<Student>) {
    loading.value = true;
    try {
      await StudentService.createStudent(data);
      success(Message.STUDENT_ADD_SUCCESS);
      page.value = 0;
      await fetchStudents();
    } catch (err: any) {
      error(err.response?.data?.message || Message.STUDENT_CREATE_ERROR);
      throw err;
    } finally {
      loading.value = false;
    }
  }

  onMounted(fetchStudents);

  return {
    students,
    page,
    totalPages,
    loading,
    goToPage,
    createStudent,
  };
}
