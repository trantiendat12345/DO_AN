<template>
    <Transition name="toast">
        <div
            v-if="show"
            class="toast-container position-fixed bottom-0 end-0 p-3"
            style="z-index: 1055"
        >
            <div
                class="toast show custom-toast"
                :class="type === 'success' ? 'toast-success' : 'toast-error'"
            >
                <div class="toast-body d-flex align-items-center gap-3">
                    <span class="toast-icon">
                        {{ type === "success" ? "✅" : "❌" }}
                    </span>
                    <div class="toast-content">
                        <div class="toast-title">
                            {{
                                type === "success"
                                    ? "Thành công!"
                                    : "Có lỗi xảy ra!"
                            }}
                        </div>
                        <div class="toast-message">{{ message }}</div>
                    </div>
                </div>
            </div>
        </div>
    </Transition>
</template>

<script setup lang="ts">
import { useToast } from "../../composables/useToast";

const { show, message, type } = useToast();
</script>

<style scoped>
.custom-toast {
    border: none;
    border-radius: 16px;
    min-width: 320px;
    box-shadow: 0 10px 40px rgba(0, 0, 0, 0.15);
    overflow: hidden;
}

.toast-success {
    background: linear-gradient(135deg, #22c55e 0%, #16a34a 100%);
    color: #fff;
}

.toast-error {
    background: linear-gradient(135deg, #ef4444 0%, #dc2626 100%);
    color: #fff;
}

.toast-body {
    padding: 1rem 1.25rem;
}

.toast-icon {
    font-size: 1.5rem;
}

.toast-title {
    font-weight: 600;
    font-size: 0.95rem;
    margin-bottom: 2px;
}

.toast-message {
    font-size: 0.875rem;
    opacity: 0.9;
}

/* Animation */
.toast-enter-active {
    animation: slideIn 0.3s ease-out;
}

.toast-leave-active {
    animation: slideOut 0.3s ease-in;
}

@keyframes slideIn {
    from {
        transform: translateX(100%);
        opacity: 0;
    }
    to {
        transform: translateX(0);
        opacity: 1;
    }
}

@keyframes slideOut {
    from {
        transform: translateX(0);
        opacity: 1;
    }
    to {
        transform: translateX(100%);
        opacity: 0;
    }
}
</style>
