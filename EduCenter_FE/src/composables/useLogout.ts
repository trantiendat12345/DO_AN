import router from "../routers";

export function logout() {
    // 1. Xóa token & user
    localStorage.removeItem("access_token");
    localStorage.removeItem("user");

    // 2. Chuyển về trang login
    router.replace({ name: "login" });
}
