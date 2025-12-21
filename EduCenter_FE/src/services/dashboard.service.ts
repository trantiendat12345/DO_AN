import api from "../api/axios";

export function getDashboardSummary() {
  return api.get("/admin/dashboard/summary").then((res) => res.data);
}
