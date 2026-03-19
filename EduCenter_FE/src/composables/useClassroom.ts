import { ref, onMounted, watch } from "vue";
import * as ClassroomService from "../services/classroom.service";
import type { Classroom } from "../types/Classroom";
import { useToast } from "./useToast";
import { Message } from "../constant/Message";
import { useRoute } from "vue-router";

export function useClassrooms() {
    const classrooms = ref<Classroom[]>([]);
    const page = ref(0);
    const size = ref(10);
    const totalPages = ref(0);
    const loading = ref(false);
    const keyword = ref("");

    const route = useRoute();
    const { success, error } = useToast();

    async function fetchClassrooms() {
        loading.value = true;
        try {
            const data = await ClassroomService.getClassrooms(
                page.value,
                size.value,
                keyword.value || undefined,
            );
            classrooms.value = data.content;
            totalPages.value = data.totalPages;
        } catch (e: any) {
            error(e.response?.data || Message.CLASSROOM_FETCH_FAILED);
        } finally {
            loading.value = false;
        }
    }

    function goToPage(p: number) {
        if (p < 0 || p >= totalPages.value) return;
        page.value = p;
        fetchClassrooms();
    }

    function searchByKeyword(kw: string) {
        keyword.value = kw;
        page.value = 0;
        fetchClassrooms();
    }

    async function createClassroom(data: Partial<Classroom>) {
        try {
            await ClassroomService.createClassroom(data);
            success(Message.CLASSROOM_ADD_SUCCESS);
            page.value = 0;
            fetchClassrooms();
        } catch (e: any) {
            error(e.response?.data || Message.CLASSROOM_CREATE_FAILED);
            throw e;
        }
    }

    async function updateClassroom(classroom: Classroom) {
        loading.value = true;
        try {
            await ClassroomService.updateClassroom(classroom.name, classroom);
            success(Message.CLASSROOM_UPDATE_SUCCESS);
            await fetchClassrooms();
        } catch (e: any) {
            error(e.response?.data || Message.CLASSROOM_UPDATE_FAILED);
            throw e;
        } finally {
            loading.value = false;
        }
    }

    async function deleteClassroom(name: string) {
        try {
            await ClassroomService.deleteClassroom(name);
            success(Message.CLASSROOM_DELETE_SUCCESS);
            fetchClassrooms();
        } catch (e: any) {
            error(e.response?.data || Message.CLASSROOM_DELETE_FAILED);
        }
    }

    async function addStudentToClassroom(
        classroomName: string,
        studentCode: string,
    ) {
        try {
            await ClassroomService.addStudentToClassroom(
                classroomName,
                studentCode,
            );
            success(Message.STUDENT_ADD_TO_CLASSROOM_SUCCESS);
            fetchClassrooms();
        } catch (e: any) {
            error(e.response?.data || Message.STUDENT_ADD_TO_CLASSROOM_FAILED);
            throw e;
        }
    }

    async function getAllStudentInClassroom(name: string) {
        try {
            return await ClassroomService.getAllStudentInClassroom(name);
        } catch (e: any) {
            error(
                e.response?.data || Message.FETCH_STUDENTS_IN_CLASSROOM_FAILED,
            );
            throw e;
        }
    }

    onMounted(() => {
        const search = route.query.search as string;
        if (search) {
            keyword.value = search;
        }
        fetchClassrooms();
    });

    watch(
        () => route.query.search,
        (newSearch) => {
            const kw = (newSearch as string) || "";
            keyword.value = kw;
            page.value = 0;
            fetchClassrooms();
        },
    );

    return {
        classrooms,
        page,
        size,
        totalPages,
        loading,
        keyword,
        goToPage,
        createClassroom,
        updateClassroom,
        deleteClassroom,
        addStudentToClassroom,
        getAllStudentInClassroom,
        searchByKeyword,
    };
}
