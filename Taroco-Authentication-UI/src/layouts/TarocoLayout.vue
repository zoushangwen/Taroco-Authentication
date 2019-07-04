<template>
  <div>
    <transition name="page-transition">
      <router-view />
    </transition>
  </div>
</template>

<script>
export default {
  name: 'TarocoLayout'
}
</script>

<style lang="less">
@import url('../components/global.less');

.page-transition-enter {
  opacity: 0;
}

.page-transition-leave-active {
  opacity: 0;
}

.page-transition-enter .page-transition-container,
.page-transition-leave-active .page-transition-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}
</style>
