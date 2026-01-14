import type { Account } from "./../types/Account";
import { ref, onMounted } from "vue";
import * as AccountService from "../services/account.service";
import { useToast } from "./useToast";
import { Message } from "../constant/Message";

export function useAccounts() {
    const accounts = ref<Account[]>([]);
    const page = ref(0);
    const size = ref(10);
    const totalPages = ref(0);
    const loading = ref(false);

    const { success, error } = useToast();

    async function fetchAccount() {
        loading.value = true;
        try {
            const data = await AccountService.getAllAccounts(
                page.value,
                size.value
            );
            accounts.value = data.content;
            totalPages.value = data.totalPages;
        } catch {
            error(Message.ACCOUNT_FETCH_FAILED);
        } finally {
            loading.value = false;
        }
    }

    function goToPage(p: number) {
        if (p < 0 || p >= totalPages.value) return;
        page.value = p;
        fetchAccount();
    }

    async function createAccount(data: Partial<Account>) {
        try {
            await AccountService.createAccount(data);
            success(Message.ACCOUNT_ADD_SUCCESS);
            page.value = 0;
            fetchAccount();
        } catch (e: any) {
            error(e.response?.data?.message || Message.ACCOUNT_CREATE_FAILED);
            throw e;
        }
    }

    // async function updateClassroom(classroom: Classroom) {
    //     loading.value = true;
    //     try {
    //         await ClassroomService.updateClassroom(classroom.name, classroom);
    //         success(Message.CLASSROOM_UPDATE_SUCCESS);
    //         await fetchAccount();
    //     } catch (e: any) {
    //         error(e.response?.data?.message || Message.CLASSROOM_UPDATE_FAILED);
    //         throw e; // 🔥 RẤT QUAN TRỌNG
    //     } finally {
    //         loading.value = false;
    //     }
    // }

    // async function deleteClassroom(name: string) {
    //     try {
    //         await ClassroomService.deleteClassroom(name);
    //         success(Message.CLASSROOM_DELETE_SUCCESS);
    //         fetchAccount();
    //     } catch {
    //         error(Message.CLASSROOM_DELETE_FAILED);
    //     }
    // }

    onMounted(fetchAccount);

    return {
        accounts,
        page,
        size,
        totalPages,
        loading,
        goToPage,
        createAccount,
        // updateClassroom,
        // deleteClassroom,
    };
}
