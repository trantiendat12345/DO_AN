import { ref, onMounted } from "vue";
import * as ScoreService from "../services/score.service";
import { useToast } from "./useToast";
import type { Score } from "../types/Score";
import { Message } from "../constant/Message";

export function useScores() {
    const scores = ref<Score[]>([]);
    const page = ref(0);
    const size = ref(10);
    const totalPages = ref(0);
    const loading = ref(false);

    const { success, error } = useToast();

    async function fetchScores() {
        loading.value = true;
        try {
            const data = await ScoreService.getAllScores(
                page.value,
                size.value,
            );
            scores.value = data.content;
            totalPages.value = data.totalPages;
        } catch (e: any) {
            error(e.response?.data || Message.SCORE_FETCH_ERROR);
        } finally {
            loading.value = false;
        }
    }

    async function fetchScoresByClassroom(classroomName: string) {
        loading.value = true;
        try {
            const data = await ScoreService.getScoresByClassroom(
                classroomName,
                page.value,
                size.value,
            );
            scores.value = data.content;
            totalPages.value = data.totalPages;
        } catch (e: any) {
            error(e.response?.data || Message.SCORE_FETCH_ERROR);
        } finally {
            loading.value = false;
        }
    }

    function goToPage(p: number) {
        if (p < 0 || p >= totalPages.value) return;
        page.value = p;
        fetchScores();
    }

    async function createScore(data: Partial<Score>) {
        loading.value = true;
        try {
            await ScoreService.createScore(data);
            success(Message.SCORE_ADD_SUCCESS);
            page.value = 0;
            await fetchScores();
        } catch (err: any) {
            error(err.response?.data || Message.SCORE_CREATE_ERROR);
            throw err;
        } finally {
            loading.value = false;
        }
    }

    async function updateScore(score: Score) {
        loading.value = true;
        try {
            await ScoreService.updateScore(score.id, score);
            success(Message.SCORE_UPDATE_SUCCESS);
            await fetchScores();
        } catch (e: any) {
            error(e.response?.data || Message.SCORE_UPDATE_ERROR);
            throw e;
        } finally {
            loading.value = false;
        }
    }

    async function deleteScore(scoreId: number) {
        loading.value = true;
        try {
            await ScoreService.deleteScore(scoreId);
            success(Message.SCORE_DELETE_SUCCESS);

            if (scores.value.length === 1 && page.value > 0) {
                page.value--;
            }

            await fetchScores();
        } catch (e: any) {
            error(e.response?.data || Message.SCORE_DELETE_ERROR);
            throw e;
        } finally {
            loading.value = false;
        }
    }

    onMounted(fetchScores);

    return {
        scores,
        page,
        totalPages,
        loading,
        goToPage,
        fetchScores,
        fetchScoresByClassroom,
        createScore,
        updateScore,
        deleteScore,
    };
}
