<template>
    <div class="col-6 col-md-4">
        <div class="card shadow-sm" :class="{ 'summary-card-warning': danger }">
            <div class="card-body">
                <div class="fw-bold mb-1" :class="{ 'text-danger': danger }">
                    {{ title }}
                </div>
                <div
                    class="fs-3 fw-bold"
                    :class="danger ? 'text-danger' : 'text-primary'"
                >
                    {{ value }}
                </div>
                <span class="summary-label" :class="{ 'text-danger': danger }">
                    {{ note }}
                </span>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
defineProps<{
    title: string;
    value: number;
    note: string;
    danger?: boolean;
}>();
</script>
