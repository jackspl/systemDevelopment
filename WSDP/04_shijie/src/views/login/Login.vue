<template>
  <div class="login">
    <div class="lg">
      <el-form ref="form" status-icon :rules="rules" :model="form" label-width="80px">
        <img class="logo" src="../../assets/avatar.jpg" />
        <el-form-item label="用户名" prop="username" placeholder="请输入用户名">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" type="password" placeholder="请输入密码"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button class="loginBtn" type="primary" @click="login">登录</el-button>
          <el-button @click="reset">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <vue-particles
      color="#dedede"
      :particleOpacity="0.7"
      :particlesNumber="80"
      shapeType="circle"
      :particleSize="4"
      linesColor="#dedede"
      :linesWidth="1"
      :lineLinked="true"
      :lineOpacity="0.4"
      :linesDistance="150"
      :moveSpeed="5"
      :hoverEffect="true"
      hoverMode="grab"
      :clickEffect="true"
      clickMode="push"
    ></vue-particles>
  </div>
</template>

<script>
export default {
  data () {
    return {
      form: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          { required: true, message: '用户名不能为空', trigger: 'blur' },
          {
            min: 3,
            max: 12,
            message: '用户名长度必须在3~12位',
            trigger: ['change', 'blur']
          }
        ],
        password: [
          { required: true, message: '密码不能为空', trigger: 'blur' },
          {
            min: 3,
            max: 12,
            message: '密码长度必须在3~12位',
            trigger: ['change', 'blur']
          }
        ]
      }
    }
  },
  mounted () {
    var that = this
    document.onkeydown = function (e) {
      if (window.event.keyCode === 13) {
        that.login()
      }
    }
  },
  methods: {
    // 重置按钮
    reset () {
      this.$refs.form.resetFields()
    },
    // 登录按钮
    async login () {
      await this.$refs.form.validate()
      // 搞成箭头函数，减少自己埋坑
      // 参数1：是否校验成功
      // 参数2：错误的信息提示对象
      /* this.$refs.form.validate(isValid => {
        // 先校验，如果失败直接return
        if (!isValid) return
      }) */
      if (this.form.username === 'admin' && this.form.password === '123123') {
        this.$router.push('/productDataAnalysis')
      } else {
        this.$message.error('用户名或密码错误')
      }
    }
  }
}
</script>

<style lang="scss">

#particles-js{
  height:100vh;
  // 至于vh:100vh就等于当前视窗的高度，同样的还有vw
}

.login {
  width: 100%;
  height: 100%;
  background-color: #173993;
}
.login .el-form-item__label {
  margin-bottom: 0px;
  font-weight: 400;
}
.login .lg {
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%,-50%);
  }
.login .el-form {
      width: 400px;
      background-color: #fff;
      border-radius: 20px;
      padding: 20px;
      padding-top: 75px;
      box-sizing: border-box;
    }
.login .logo {
        position: absolute;
        left: 50%;
        transform: translateX(-50%) !important;
        top: -75px;
        border-radius: 50%;
      }
.login .loginBtn {
        margin-right: 70px;
      }
</style>
