<template>
    <div class="col-6 col-md-4">
        <div
            class="card summary-card"
            :class="{ 'summary-card-warning': danger }"
        >
            <div class="card-body">
                <div
                    class="d-flex align-items-center justify-content-between mb-2"
                >
                    <div
                        class="summary-title"
                        :class="{ 'text-danger': danger }"
                    >
                        {{ title }}
                    </div>
                    <div
                        class="summary-icon"
                        :class="danger ? 'icon-danger' : 'icon-primary'"
                    >
                        <slot name="icon">📊</slot>
                    </div>
                </div>
                <div
                    class="summary-value"
                    :class="danger ? 'text-danger' : 'text-gradient'"
                >
                    {{ formatValue(value) }}
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

function formatValue(val: number): string {
    if (val >= 1000000) {
        return (val / 1000000).toFixed(1) + "M";
    } else if (val >= 1000) {
        return (val / 1000).toFixed(1) + "K";
    }
    return val.toLocaleString("vi-VN");
}
</script>

<style scoped>
.summary-card {
    border: none;
    border-radius: 16px;
    background: linear-gradient(135deg, #ffffff 0%, #f8fafc 100%);
    box-shadow:
        0 1px 3px rgba(0, 0, 0, 0.04),
        0 4px 16px rgba(0, 0, 0, 0.04);
    transition: all 0.3s ease;
    overflow: hidden;
    position: relative;
}

.summary-card::before {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    height: 4px;
    background: linear-gradient(90deg, #06b6d4, #0891b2);
}

.summary-card:hover {
    transform: translateY(-4px);
    box-shadow:
        0 8px 24px rgba(6, 182, 212, 0.15),
        0 16px 48px rgba(0, 0, 0, 0.08);
}

.summary-card-warning::before {
    background: linear-gradient(90deg, #ef4444, #dc2626);
}

.summary-title {
    font-weight: 600;
    font-size: 0.9rem;
    color: #475569;
}

.summary-value {
    font-size: 2rem;
    font-weight: 700;
    margin: 0.5rem 0;
    line-height: 1.2;
}

.text-gradient {
    background: linear-gradient(135deg, #0891b2, #06b6d4);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
}

.summary-label {
    font-size: 0.8rem;
    color: #64748b;
    font-weight: 500;
}

.summary-icon {
    width: 42px;
    height: 42px;
    border-radius: 12px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.2rem;
}

.icon-primary {
    background: linear-gradient(
        135deg,
        rgba(6, 182, 212, 0.15),
        rgba(8, 145, 178, 0.15)
    );
}

.icon-danger {
    background: linear-gradient(
        135deg,
        rgba(239, 68, 68, 0.15),
        rgba(220, 38, 38, 0.15)
    );
}
</style>
