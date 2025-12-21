import { ref, onMounted } from "vue";
import {
  getTotalStudents,
  getTotalTeachers,
  getTotalClassrooms,
  getTotalParents,
} from "../services/dashboard.service";
import { useToast } from "./useToast";
import { Message } from "../constant/Message";

export function useDashboard() {
  const loading = ref(false);

  const summary = ref({
    totalStudents: 0,
    totalTeachers: 0,
    totalClassrooms: 0,
    totalParents: 0,
  });

  const { error } = useToast();

  async function fetchSummary() {
    loading.value = true;
    try {
      const [
        students,
        teachers,
        classrooms,
        parents,
      ] = await Promise.all([
        getTotalStudents(),
        getTotalTeachers(),
        getTotalClassrooms(),
        getTotalParents(),
      ]);

      summary.value = {
        totalStudents: students,
        totalTeachers: teachers,
        totalClassrooms: classrooms,
        totalParents: parents,
      };
    } catch (e) {
      error(Message.DASHBOARD_FETCH_ERROR);
    } finally {
      loading.value = false;
    }
  }

  onMounted(fetchSummary);

  return {
    summary,
    loading,
    fetchSummary,
  };
}
