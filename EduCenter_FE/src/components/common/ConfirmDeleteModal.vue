<template>
  <div class="modal fade" id="confirmDeleteModal" tabindex="-1">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title text-danger">Xác nhận xoá</h5>
          <button class="btn-close" data-bs-dismiss="modal"></button>
        </div>

        <div class="modal-body">
          <p>
            Bạn có chắc chắn muốn xoá
            <strong>{{ name }}</strong> không?
          </p>
        </div>

        <div class="modal-footer">
          <button class="btn btn-secondary" data-bs-dismiss="modal">
            Huỷ
          </button>
          <button class="btn btn-danger" @click="confirm">
            Xoá
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
const props = defineProps<{
  name: string
}>()

const emit = defineEmits(['confirm'])

function confirm() {
  emit('confirm')
}
</script>
