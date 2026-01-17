import axios from "axios";
import router from "../routers/index.ts";

import type { InternalAxiosRequestConfig } from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 10000,
});

// 🔐 INTERCEPTOR – GẮN JWT CHO MỌI REQUEST
api.interceptors.request.use(
    (config: InternalAxiosRequestConfig) => {
        const token = localStorage.getItem("access_token");

        if (token && config.headers) {
            config.headers.Authorization = `Bearer ${token}`;
        }

        return config;
    },
    (error) => Promise.reject(error),
);

api.interceptors.request.use((config) => {
    const token = localStorage.getItem("access_token");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

api.interceptors.response.use(
    (response) => response,
    (error) => {
        const status = error.response?.status;

        if (status === 401 || status === 403) {
            // Token hết hạn / không hợp lệ
            localStorage.removeItem("access_token");
            localStorage.removeItem("user");

            if (router.currentRoute.value.name !== "login") {
                router.replace({
                    name: "login",
                    query: { expired: "true" },
                });
            }
        }

        return Promise.reject(error);
    },
);

export default api;
