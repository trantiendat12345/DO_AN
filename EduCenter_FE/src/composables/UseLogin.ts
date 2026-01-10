import { ref } from "vue";
import { useRouter } from "vue-router";
import api from "../api/axios";

interface LoginResponse {
    token: string;
    role: string;
    username: string;
}

export function useLogin() {
    const router = useRouter();

    // Form data
    const username = ref<string>("");
    const password = ref<string>("");
    const rememberMe = ref<boolean>(false);

    // UI state
    const showPassword = ref<boolean>(false);
    const message = ref<string>("");
    const messageClass = ref<string>("text-danger");
    const loading = ref<boolean>(false);

    const togglePassword = () => {
        showPassword.value = !showPassword.value;
    };

    const handleLogin = async () => {
        message.value = "";
        messageClass.value = "text-danger";

        if (!username.value || !password.value) {
            message.value = "Vui lòng nhập đầy đủ thông tin!";
            return;
        }

        loading.value = true;

        try {
            // 🔥 LOGIN THẬT – GỌI BACKEND
            const res = await api.post<LoginResponse>("/api/auth/login", {
                username: username.value,
                password: password.value,
            });

            const { token, role, username: user } = res.data;

            // 🔐 LƯU TOKEN & INFO
            localStorage.setItem("access_token", token);
            localStorage.setItem("role", role);
            localStorage.setItem("username", user);

            if (rememberMe.value) {
                localStorage.setItem(
                    "student_mgmt_user",
                    JSON.stringify({ username: user })
                );
            } else {
                localStorage.removeItem("student_mgmt_user");
            }

            messageClass.value = "text-success";
            message.value = "Đăng nhập thành công!";

            // 🚦 ĐIỀU HƯỚNG THEO ROLE
            setTimeout(() => {
                if (role === "ROLE_ADMIN") {
                    router.push("/admin/dashboard");
                } else if (role === "ROLE_TEACHER") {
                    router.push("/teacher/dashboard");
                } else if (role === "ROLE_STUDENT") {
                    router.push("/student/dashboard");
                } else {
                    router.push("/");
                }
            }, 300);
        } catch (err: any) {
            message.value =
                err?.response?.data?.message ||
                "Tên đăng nhập hoặc mật khẩu không đúng!";
        } finally {
            loading.value = false;
        }
    };

    return {
        username,
        password,
        rememberMe,
        showPassword,
        message,
        messageClass,
        loading,
        togglePassword,
        handleLogin,
    };
}
