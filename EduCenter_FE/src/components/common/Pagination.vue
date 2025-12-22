<!-- src/components/common/Pagination.vue -->
<template>
    <nav v-if="totalPages > 1" class="d-flex justify-content-center mt-4">
        <ul class="pagination">
            <li class="page-item" :class="{ disabled: page === 0 }">
                <button class="page-link" @click="emitPage(page - 1)">«</button>
            </li>

            <li
                v-for="p in totalPages"
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
                <button class="page-link" @click="emitPage(page + 1)">»</button>
            </li>
        </ul>
    </nav>
</template>

<script setup lang="ts">
defineProps<{
    page: number;
    totalPages: number;
}>();

const emit = defineEmits<{
    (e: "change", page: number): void;
}>();

function emitPage(p: number) {
    emit("change", p);
}
</script>
