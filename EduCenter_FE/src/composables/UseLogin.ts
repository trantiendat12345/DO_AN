import { ref } from "vue";
import { useRouter } from "vue-router";

export function useLogin() {
    const router = useRouter();

    // Form data
    const username = ref("");
    const password = ref("");
    const rememberMe = ref(false);

    // UI state
    const showPassword = ref(false);
    const message = ref("");
    const messageClass = ref("text-danger");

    // Methods
    const togglePassword = () => {
        showPassword.value = !showPassword.value;
    };

    const handleLogin = () => {
        message.value = "";
        messageClass.value = "text-danger";

        if (!username.value || !password.value) {
            message.value = "Vui lòng nhập đầy đủ thông tin!";
            return;
        }

        // Demo login
        if (username.value === "admin" && password.value === "123456") {
            messageClass.value = "text-success";
            message.value = "Đăng nhập thành công!";

            if (rememberMe.value) {
                localStorage.setItem(
                    "student_mgmt_user",
                    JSON.stringify({ username: username.value })
                );
            } else {
                localStorage.removeItem("student_mgmt_user");
            }

            setTimeout(() => {
                router.push("/admin/dashboard");
            }, 800);
        } else {
            message.value = "Tên đăng nhập hoặc mật khẩu không đúng!";
        }
    };

    return {
        username,
        password,
        rememberMe,
        showPassword,
        message,
        messageClass,
        togglePassword,
        handleLogin,
    };
}
