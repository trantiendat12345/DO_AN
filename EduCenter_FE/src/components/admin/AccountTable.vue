<template>
    <div class="table-responsive">
        <table class="table table-striped align-middle">
            <thead>
                <tr>
                    <th>#</th>
                    <th>Tài khoản</th>
                    <th>Vai trò</th>
                    <th>Mã người dùng</th>
                    <th>Phân loại</th>
                    <th>Trạng thái</th>
                    <th width="150">Hành động</th>
                </tr>
            </thead>

            <tbody>
                <tr v-for="(a, i) in account" :key="a.username">
                    <td>{{ page * size + i + 1 }}</td>
                    <td>{{ a.username }}</td>
                    <td>{{ a.roleName }}</td>
                    <td>{{ a.userCode }}</td>
                    <td>{{ a.userType }}</td>
                    <td>
                        <span class="badge bg-success">{{
                            a.accountStatus
                        }}</span>
                    </td>
                    <td>
                        <button
                            class="btn btn-sm btn-warning me-1"
                            @click="$emit('edit', a)"
                        >
                            Sửa
                        </button>
                        <button
                            class="btn btn-sm btn-danger"
                            @click="$emit('delete', a)"
                        >
                            Xóa
                        </button>
                    </td>
                </tr>

                <tr v-if="!account.length">
                    <td colspan="8" class="text-center text-muted">
                        Không có dữ liệu
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup lang="ts">
import type { Account } from "../../types/Account";

defineProps<{
    account: Account[];
    page: number;
    size: number;
}>();

defineEmits(["edit", "delete"]);
</script>
