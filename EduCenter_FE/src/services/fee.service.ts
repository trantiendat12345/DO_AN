import api from "../api/axios";

export function getAllFees(page = 0, size = 10) {
    return api
        .get("/admin/get-all-fees", { params: { page, size } })
        .then((res) => res.data);
}
