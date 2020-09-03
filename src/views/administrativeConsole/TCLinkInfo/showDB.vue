<template>
  <div class="showDB">
    <span class="onLineNums">在线人数:</span>
    <span class="onLineNums">{{count}}</span>
  </div>
</template>

<script>
export default {
  data () {
    return {
      count: ''
    }
  },
  mounted () {
    this.count = JSON.parse(localStorage.getItem('query'))
    // this.count2 = JSON.parse(localStorage.getItem('queryState'))
  }
}
</script>

<style lang='scss' scoped>
.showDB {
  width: 500px;
  height: 40px;
  line-height: 40px;
}
.onLineNums {
  display: block;
  width: 80px;
  height: 40px;
  line-height: 40px;
  float: left;
}
.onInput {
  width: 250px;
  height: 40px;
  line-height: 40px;
  float: left;
}
.el-input.is-disabled .el-input__inner {
  color: black;
}
</style>
