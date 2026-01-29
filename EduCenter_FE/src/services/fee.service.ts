import api from "../api/axios";
import type { Fee } from "../types/Fee";

export function getAllFees(page = 0, size = 10) {
    return api
        .get("/admin/get-all-fees", { params: { page, size } })
        .then((res) => res.data);
}
