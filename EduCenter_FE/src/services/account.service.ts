import api from "../api/axios";
import type { Account } from "../types/Account";

export function getAllAccounts(page = 0, size = 10) {
    return api
        .get("/admin/get-all-accounts", {
            params: { page, size },
        })
        .then((res) => res.data);
}
