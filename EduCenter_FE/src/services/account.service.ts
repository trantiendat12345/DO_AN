import api from "../api/axios";
import type { Account } from "../types/Account";

export function getAllAccounts(page = 0, size = 10) {
    return api
        .get("/admin/get-all-accounts", {
            params: { page, size },
        })
        .then((res) => res.data);
}

export function createAccount(data: Partial<Account>) {
    return api.post("/admin/create-account", data).then((res) => res.data);
}

export function updateAccount(username: string, data: Partial<Account>) {
    return api
        .put(`/admin/update-account/${username}`, data)
        .then((res) => res.data);
}

export function deleteAccount(username: string) {
    return api
        .delete(`/admin/delete-account/${username}`)
        .then((res) => res.data);
}
