<template>
  <div class="frequency">
    <router-link class="btn1" to="/users/usersFrequency/specificSum" active-class="active">   操作频次监控</router-link>
    <router-link class="btn2" to="/users/usersFrequency/specificUser" active-class="active">  基于用户监控</router-link>
    <router-link class="btn2" to="/users/usersFrequency/specificClient" active-class="active">基于客户端监空</router-link>
    <router-link class="btn2" to="/users/usersFrequency/specificContextUser" active-class="active">基于特定场景（用户）</router-link>
    <router-link class="btn2" to="/users/usersFrequency/specificContextClient" active-class="active">基于特定场景（客户端）</router-link>
    <router-view/>
  </div>
</template>
<script>
export default {
  data () {
    return {

    }
  },
  methods: {

  }
}
</script>

<style lang='scss' scoped>
.frequency .el-button {
  border-radius: 0px;
  background-color: #062c4e;
  color: white;
  margin-bottom: 10px;
}
.frequency .el-button+.el-button {
  margin-left: 0px;
}
.frequency .btn1 {
  border: 1px solid #3279a9;
  background-color: #062c4e;

}
.frequency .btn2 {
  border-top: 1px solid #3279a9;
  border-right: 1px solid #3279a9;
  border-bottom: 1px solid #3279a9;
  border-left: 0px solid #3279a9;
  background-color: #062c4e;

}
.frequency .active {
  background-color: #596c7e;
}
.frequency .bgColor {
  background-color: red;
}
.frequency  a {
  text-decoration: none;
  color: #fff;
  font-size: 14px;
  display: block;
  width: 200px;
  height: 40px;
  line-height: 40px;
  padding: 0px 16px;
  text-align: center;
  float: left;
  border: 1px solid #3279a9;
  box-sizing: border-box;
  margin-bottom: 10px;
}

</style>
