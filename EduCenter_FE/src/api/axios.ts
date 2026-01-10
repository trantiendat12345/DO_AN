import axios from "axios";

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
    (error) => Promise.reject(error)
);

export default api;
