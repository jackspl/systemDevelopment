.<template>
  <div class="connectDB">
    <!-- 主体 -->
    <!-- 右侧<main> -->
    <el-form ref="form" :model="form" :rules="rules" status-icon class="demo-ruleForm">
      <el-main id="main1">
        <h3>数据库引擎选择</h3>
        <div class="info">
          <span>以下参数是连接数据库所必需的。请确保填写了所有相关联字段，且提供的数据准确。如果不确定这其中的任何参数，请联系系统管理员以获取详细信息。</span>
        </div>

        <main>
          <el-form-item label="数据库引擎" prop="selectDB" class="labelColor">
            <el-select v-model="form.selectDB" placeholder="请选择数据库引擎" clearable>
              <el-option label="Oracle" value="Oracle" clearable></el-option>
            </el-select>
          </el-form-item>
          <!-- v-model 里面跟的是开发文档接口里提供的接口数据 -->
          <el-form-item label="数据库URL" prop="url" class="labelColor">
            <el-input v-model="form.url" placeholder="数据库URL" clearable></el-input>
          </el-form-item>
          <el-form-item label="数据库名称" prop="serviceName" class="labelColor">
            <el-input v-model="form.serviceName" placeholder="数据库名称" clearable></el-input>
          </el-form-item>
          <el-form-item label="数据库端口" prop="port" class="labelColor">
            <el-input v-model="form.port" placeholder="端口" clearable></el-input>
          </el-form-item>
          <el-form-item label="数据库用户" prop="userName" class="labelColor">
            <el-input v-model="form.userName" placeholder="数据库用户" clearable></el-input>
          </el-form-item>
          <el-form-item label="数据库密码" prop="passWord" class="labelColor">
            <el-input v-model="form.passWord" placeholder="数据库密码" type="password" clearable></el-input>
          </el-form-item>
          <div class="lin60">
            <el-button type="primary" style="margin-left: 120px; font-size: 16px" @click="login">连 接</el-button>
          </div>
        </main>
      </el-main>
    </el-form>
  </div>
</template>

<script>
import { tcDB } from '@/api/table.js'
export default {
  data () {
    return {
      options: [
        {
          value: '选项1',
          flag: '1',
          label: 'Oracle'
        }
      ],
      form: {
        selectDB: '',
        url: '',
        serviceName: '',
        port: '',
        userName: '',
        passWord: ''
      },
      query: '',
      queryState: '',
      // elementValue: 4,

      rules: {
        selectDB: [
          {
            required: true,
            message: '请选择数据库',
            trigger: ['blur', 'change']
          }
        ],
        url: [
          {
            // pattern: /192.168.1.128/,
            required: true,
            message: '请输入正确的数据库URL',
            trigger: ['blur', 'change']
          }
        ],
        serviceName: [
          {
            // pattern: /tc10/,
            required: true,
            message: '请输入正确的数据库名称',
            trigger: ['blur', 'change']
          }
        ],
        port: [
          {
            // pattern: /1521/,
            required: true,
            message: '请输入正确的数据库端口',
            trigger: ['blur', 'change']
          }
        ],
        userName: [
          {
            // pattern: /infodba/,
            required: true,
            message: '请输入正确的数据库用户名',
            trigger: ['blur', 'change']
          }
        ],
        passWord: [
          {
            // pattern: /infodba/,
            required: true,
            message: '请输入正确的数据库密码',
            trigger: ['blur', 'change']
          }
        ]
      }
    }
  },
  methods: {
    async getTCInfo () {
      const value = {
        url: this.url,
        serviceName: this.serviceName,
        port: this.port,
        userName: this.userName,
        passWord: this.passWord,
        selectDB: this.selectDB
      }

      try {
        const { data } = await tcDB(value)
        const { resultInfo } = data
        this.queryState = resultInfo
      } catch (e) {
        console.log(e)
      }
    },
    c1 (value) {
      let obj = this.options.find(item => {
        return item.value === value
      })
      const selectLable = obj.label
      this.selectDB = selectLable
    },
    async login () {
      await this.$refs.form.validate()
      const value = {
        url: this.form.url,
        serviceName: this.form.serviceName,
        port: this.form.port,
        userName: this.form.userName,
        passWord: this.form.passWord,
        selectDB: this.form.selectDB
      }
      try {
        const { data } = await tcDB(value)
        const { resultInfo, onlinePeopleCount } = data
        this.queryState = resultInfo
        this.query = onlinePeopleCount
        // 传值
        // localStorage.setItem('query', JSON.stringify(this.query))
        // localStorage.setItem('queryState', JSON.stringify(this.queryState))
        // this.$router.push('/showDB')
      } catch (e) {
        console.log(e)
      }
      if (this.queryState === 'linkError') {
        this.$message.error('请正确输入TC连接信息')
      } else if (this.queryState === 'linkSuccess') {
        this.$message.success('连接成功')
      } else if (this.queryState === 'linkSkip') {
        this.$message.success('您已连接过')
      }
    }
  },
  mounted () {
    const that = this
    document.onkeydown = function (e) {
      if (window.event.keyCode === 13) {
        that.login()
      }
    }
  }
}
</script>

<style lang='scss' scoped>
@import "../../../css/administrativeConsole/connectDB.css";
</style>
