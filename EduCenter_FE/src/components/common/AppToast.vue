<template>
  <div
    v-if="show"
    class="toast-container position-fixed bottom-0 end-0 p-3"
    style="z-index: 1055"
  >
    <div
      class="toast show"
      :class="type === 'success' ? 'text-bg-success' : 'text-bg-danger'"
    >
      <div class="toast-body">
        {{ message }}
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { useToast } from '../../composables/useToast'

const { show, message, type } = useToast()
</script>
