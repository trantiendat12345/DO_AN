import { ref, onMounted, watch } from "vue";
import * as TeacherService from "../services/teacher.service";
import { useToast } from "./useToast";
import type { Teacher } from "../types/Teacher";
import { Message } from "../constant/Message";
import { useRoute } from "vue-router";

export function useTeacher() {
    const teachers = ref<Teacher[]>([]);
    const page = ref(0);
    const size = ref(10);
    const totalPages = ref(0);
    const loading = ref(false);
    const keyword = ref("");

    const route = useRoute();
    const { success, error } = useToast();

    async function fetchTeachers() {
        loading.value = true;
        try {
            const data = await TeacherService.getAllTeachers(
                page.value,
                size.value,
                keyword.value || undefined,
            );
            teachers.value = data.content;
            totalPages.value = data.totalPages;
        } catch (e: any) {
            error(e.response?.data || Message.TEACHER_FETCH_ERROR);
        } finally {
            loading.value = false;
        }
    }

    function goToPage(p: number) {
        if (p < 0 || p >= totalPages.value) return;
        page.value = p;
        fetchTeachers();
    }

    function searchByKeyword(kw: string) {
        keyword.value = kw;
        page.value = 0;
        fetchTeachers();
    }

    async function createTeacher(data: Partial<Teacher>) {
        loading.value = true;
        try {
            await TeacherService.createTeacher(data);
            success(Message.TEACHER_ADD_SUCCESS);
            page.value = 0;
            await fetchTeachers();
        } catch (e: any) {
            error(e.response?.data || Message.TEACHER_ADD_ERROR);
            throw e;
        } finally {
            loading.value = false;
        }
    }

    async function updateTeacher(teacher: Teacher) {
        loading.value = true;
        try {
            await TeacherService.updateTeacher(teacher.teacherCode, teacher);

            success(Message.TEACHER_UPDATE_SUCCESS);

            await fetchTeachers();
        } catch (e: any) {
            error(e.response?.data || Message.TEACHER_UPDATE_ERROR);
            throw e;
        } finally {
            loading.value = false;
        }
    }

    async function deleteTeacher(code: string) {
        try {
            await TeacherService.deleteTeacher(code);
            success(Message.TEACHER_DELETE_SUCCESS);

            // Nếu xoá ở trang cuối → lùi trang
            if (teachers.value.length === 1 && page.value > 0) {
                page.value--;
            }
            await fetchTeachers();
        } catch (e: any) {
            error(e.response?.data || Message.TEACHER_DELETE_ERROR);
        }
    }

    onMounted(() => {
        const search = route.query.search as string;
        if (search) {
            keyword.value = search;
        }
        fetchTeachers();
    });

    watch(
        () => route.query.search,
        (newSearch) => {
            const kw = (newSearch as string) || "";
            keyword.value = kw;
            page.value = 0;
            fetchTeachers();
        },
    );

    return {
        teachers,
        page,
        size,
        totalPages,
        loading,
        keyword,
        goToPage,
        createTeacher,
        updateTeacher,
        deleteTeacher,
        searchByKeyword,
    };
}
