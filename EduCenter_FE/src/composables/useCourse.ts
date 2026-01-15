import { ref, onMounted } from "vue";
import * as CourseService from "../services/course.service";
import type { Course } from "../types/Course";
import { useToast } from "./useToast";
import { Message } from "../constant/Message";

export function useCourses() {
    const courses = ref<Course[]>([]);
    const page = ref(0);
    const size = ref(10);
    const totalPages = ref(0);
    const loading = ref(false);

    const { success, error } = useToast();

    async function fetchCourses() {
        loading.value = true;
        try {
            const data = await CourseService.getCourses(page.value, size.value);
            courses.value = data.content;
            totalPages.value = data.totalPages;
        } catch {
            error(Message.COURSE_FETCH_FAILED);
        } finally {
            loading.value = false;
        }
    }

    function goToPage(p: number) {
        if (p < 0 || p >= totalPages.value) return;
        page.value = p;
        fetchCourses();
    }

    async function createCourse(data: Partial<Course>) {
        try {
            await CourseService.createCourse(data);
            success(Message.COURSE_ADD_SUCCESS);
            page.value = 0;
            fetchCourses();
        } catch (e: any) {
            error(e.response?.data?.message || Message.COURSE_CREATE_FAILED);
            throw e;
        }
    }

    async function updateCourse(course: Course) {
        loading.value = true;
        try {
            await CourseService.updateCourse(course.nameCourse, course);
            success(Message.COURSE_UPDATE_SUCCESS);
            await fetchCourses();
        } catch (e: any) {
            error(e.response?.data?.message || Message.CLASSROOM_UPDATE_FAILED);
            throw e; // 🔥 RẤT QUAN TRỌNG
        } finally {
            loading.value = false;
        }
    }

    async function deleteCourse(nameCourse: string) {
        try {
            await CourseService.deleteCourse(nameCourse);
            success(Message.COURSE_DELETE_SUCCESS);
            fetchCourses();
        } catch {
            error(Message.COURSE_DELETE_FAILED);
        }
    }

    onMounted(fetchCourses);

    return {
        courses,
        page,
        size,
        totalPages,
        loading,
        goToPage,
        createCourse,
        updateCourse,
        deleteCourse,
    };
}
