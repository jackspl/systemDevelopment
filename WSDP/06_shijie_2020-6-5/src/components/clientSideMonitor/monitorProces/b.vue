<template>
  <div class="box">
    <div id="root">
      <transition name="fade">
        <div v-if="show">hello world</div>
      </transition>
      <button @click="handleClick">切换</button>
    </div>
  </div>
</template>
<script>
export default {

  data() {
    return {
    show: true
    }
  },
  methods: {
    handleClick: function() {
      this.show = this.show === true ? false : true;
    }
  }
};
</script>
<style>
.fade-enter,
.fade-leave-to {
  opacity: 0;
  color: aqua;
}
.fade-enter-active,
.fade-leave-active {
  color: red;
  transition: opacity 3s;
}
</style>
