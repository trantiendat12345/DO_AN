// src/composables/useToast.ts
import { ref } from "vue";

// 🔥 STATE GLOBAL (ngoài function)
const show = ref(false);
const message = ref("");
const type = ref<"success" | "error">("success");

export function useToast() {
    function success(msg: string) {
        message.value = msg;
        type.value = "success";
        show.value = true;
        autoHide();
    }

    function error(msgOrError: string | any) {
        // Nếu là string, sử dụng trực tiếp
        if (typeof msgOrError === "string") {
            message.value = msgOrError;
        } else {
            // Nếu là object từ response.data, lấy message property
            message.value = msgOrError?.message || "";
        }
        type.value = "error";
        show.value = true;
        autoHide();
    }

    function autoHide() {
        setTimeout(() => {
            show.value = false;
        }, 3000);
    }

    return {
        show,
        message,
        type,
        success,
        error,
    };
}
