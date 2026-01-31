<!-- src/components/common/Pagination.vue -->
<template>
    <nav v-if="totalPages > 1" class="d-flex justify-content-center mt-4">
        <ul class="pagination custom-pagination">
            <li class="page-item" :class="{ disabled: page === 0 }">
                <button class="page-link page-nav" @click="emitPage(page - 1)">
                    <span>←</span>
                </button>
            </li>

            <li
                v-for="p in visiblePages"
                :key="p"
                class="page-item"
                :class="{ active: page === p - 1 }"
            >
                <button class="page-link" @click="emitPage(p - 1)">
                    {{ p }}
                </button>
            </li>

            <li
                class="page-item"
                :class="{ disabled: page === totalPages - 1 }"
            >
                <button class="page-link page-nav" @click="emitPage(page + 1)">
                    <span>→</span>
                </button>
            </li>
        </ul>
    </nav>
</template>

<script setup lang="ts">
import { computed } from "vue";

const props = defineProps<{
    page: number;
    totalPages: number;
}>();

const emit = defineEmits<{
    (e: "change", page: number): void;
}>();

const visiblePages = computed(() => {
    const pages: number[] = [];
    const maxVisible = 5;
    let start = Math.max(1, props.page + 1 - Math.floor(maxVisible / 2));
    let end = Math.min(props.totalPages, start + maxVisible - 1);

    if (end - start + 1 < maxVisible) {
        start = Math.max(1, end - maxVisible + 1);
    }

    for (let i = start; i <= end; i++) {
        pages.push(i);
    }
    return pages;
});

function emitPage(p: number) {
    emit("change", p);
}
</script>

<style scoped>
.custom-pagination {
    gap: 6px;
    margin: 0;
}

.custom-pagination .page-link {
    border: none;
    border-radius: 10px;
    padding: 0.5rem 1rem;
    color: #475569;
    font-weight: 500;
    background: #ffffff;
    box-shadow: 0 1px 3px rgba(0, 0, 0, 0.05);
    transition: all 0.2s ease;
    min-width: 40px;
    text-align: center;
}

.custom-pagination .page-link:hover {
    background: linear-gradient(135deg, #06b6d4, #0891b2);
    color: #ffffff;
    transform: translateY(-2px);
    box-shadow: 0 4px 12px rgba(6, 182, 212, 0.3);
}

.custom-pagination .page-item.active .page-link {
    background: linear-gradient(135deg, #06b6d4, #0891b2);
    color: #ffffff;
    box-shadow: 0 4px 12px rgba(6, 182, 212, 0.3);
}

.custom-pagination .page-item.disabled .page-link {
    background: #f1f5f9;
    color: #cbd5e1;
    cursor: not-allowed;
}

.custom-pagination .page-nav {
    font-size: 1rem;
}
</style>
