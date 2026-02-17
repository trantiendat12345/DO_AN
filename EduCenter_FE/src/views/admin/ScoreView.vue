<template>
    <AdminHeader
        title="Quản lý điểm"
        subtitle="Quản lý điểm số của học viên tại trung tâm"
    />

    <div class="page-actions mb-4">
        <button
            class="btn btn-primary btn-add"
            data-bs-toggle="modal"
            data-bs-target="#addScoreModal"
        >
            <span class="btn-icon">➕</span>
            Thêm điểm
        </button>
    </div>

    <div class="table-wrapper">
        <ScoreTable
            :scores="scores"
            :page="page"
            :size="10"
            @edit="onEditScore"
            @delete="openDeleteModal"
        />

        <EditScoreModal :score="editingScore" @submit="onUpdateScore" />

        <Pagination :page="page" :total-pages="totalPages" @change="goToPage" />

        <ConfirmDeleteScoreModal
            v-if="selectedScore"
            :studentName="selectedScore.studentName"
            :scoreId="selectedScore.id"
            @confirm="onConfirmDelete"
        />
    </div>

    <AddScoreModal :key="modalKey" @submit="onAddScore" />
</template>

<script setup lang="ts">
import { Modal } from "bootstrap";
import AdminHeader from "../../components/admin/AdminHeader.vue";
import ScoreTable from "../../components/admin/score/ScoreTable.vue";
import AddScoreModal from "../../components/admin/score/AddScoreModal.vue";
import EditScoreModal from "../../components/admin/score/EditScoreModal.vue";
import ConfirmDeleteScoreModal from "../../components/admin/score/ConfirmDeleteScoreModal.vue";
import Pagination from "../../components/common/Pagination.vue";
import { useScores } from "../../composables/useScore";
import type { Score } from "../../types/Score";
import { ref } from "vue";

const {
    scores,
    page,
    totalPages,
    goToPage,
    createScore,
    updateScore,
    deleteScore,
} = useScores();

const modalKey = ref(0);
const editingScore = ref<Score | null>(null);
const selectedScore = ref<Score | null>(null);

async function onAddScore(score: Partial<Score>) {
    try {
        await createScore(score);

        const modalEl = document.getElementById("addScoreModal");
        if (modalEl) {
            const modal = Modal.getInstance(modalEl);
            modal?.hide();
        }
        modalKey.value += 1;
    } catch {
        // lỗi đã có toast
    }
}

function onEditScore(score: Score) {
    editingScore.value = score;

    const modalEl = document.getElementById("editScoreModal");
    if (modalEl) {
        const modal = new Modal(modalEl);
        modal.show();
    }
}

async function onUpdateScore(score: Score) {
    try {
        await updateScore(score);
        const modalEl = document.getElementById("editScoreModal");
        if (modalEl) {
            const modal = Modal.getInstance(modalEl);
            modal?.hide();
        }
    } catch (error) {}
}

function openDeleteModal(score: Score) {
    selectedScore.value = score;

    const modalEl = document.getElementById("confirmDeleteScoreModal");
    if (modalEl) {
        new Modal(modalEl).show();
    }
}

async function onConfirmDelete(scoreId: number) {
    try {
        await deleteScore(scoreId);

        const modalEl = document.getElementById("confirmDeleteScoreModal");
        if (modalEl) {
            Modal.getInstance(modalEl)?.hide();
        }
    } catch {
        // lỗi đã có toast → không đóng modal
    }
}
</script>

<style scoped>
.page-actions {
    display: flex;
    justify-content: flex-end;
}

.btn-add {
    background: linear-gradient(135deg, #0891b2, #06b6d4);
    border: none;
    padding: 0.75rem 1.5rem;
    border-radius: 12px;
    font-weight: 500;
    display: flex;
    align-items: center;
    gap: 0.5rem;
    transition: all 0.3s ease;
}

.btn-add:hover {
    transform: translateY(-2px);
    box-shadow: 0 8px 20px rgba(6, 182, 212, 0.3);
}

.btn-icon {
    font-size: 1rem;
}

.table-wrapper {
    background: white;
    border-radius: 16px;
    padding: 1.5rem;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
}
</style>
