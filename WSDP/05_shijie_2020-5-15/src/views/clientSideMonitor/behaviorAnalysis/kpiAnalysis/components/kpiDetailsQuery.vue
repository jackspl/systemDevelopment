<template>
  <div class="kpiDetailsQuery">
    <div class="seachAll">
      <el-form :inline="true" :model="form" ref="form">
        <!-- 三个单选按钮 -->
        <div class="threeRadio">
          <el-radio-group v-model="radio1">
            <el-radio :label="1">公司</el-radio>
            <el-radio :label="2">部门</el-radio>
            <el-radio :label="3" class="personal">个人</el-radio>
          </el-radio-group>
        </div>
        <!-- 用户信息 -->
        <div class="userInfo">
          <el-form-item label="用户信息"> </el-form-item>
        </div>
        <!-- 公司选择 -->
        <div class="comChange">
          <el-form-item label="公司">
            <el-select
              v-model="form.region2"
              placeholder="北京公司"
              style="width: 100px; height:30px;"
            >
              <el-option label="上海公司" value="shanghai"></el-option>
              <el-option label="广州公司" value="beijing"></el-option>
              <el-option label="成都公司" value="chengdu"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <!-- 部门选择 -->
        <div class="depChange">
          <el-form-item label="部门" style="margin-left:10px">
            <el-select
              v-model="form.region3"
              placeholder="研发部"
              style="width: 100px; height:30px"
            >
              <el-option label="科研部" value="shanghai"></el-option>
              <el-option label="生产部" value="beijing"></el-option>
              <el-option label="销售部" value="chengdu"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <!-- 员工选择 -->
        <div class="userChange">
          <el-form-item label="用户" style="margin-left: 10px;">
            <el-select
              v-model="form.region4"
              placeholder="张磊"
              style="width: 100px; height:30px"
            >
              <el-option label="李岩" value="liYan"></el-option>
              <el-option label="刘杰" value="jiuJie"></el-option>
              <el-option label="吴伟" value="weiWei"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <!-- 查询按钮 -->
        <div class="seachButton">
          <el-button type="primary" @click="seachBtn">查询</el-button>
        </div>
        <!-- 结束时间 -->
        <div class="endTime">
          <el-form-item label="结束日期" prop="actionEndTime">
            <el-date-picker
              v-model="form.actionEndTime"
              type="datetime"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              clearable
            ></el-date-picker>
          </el-form-item>
        </div>
        <!-- 开始时间 -->
        <div class="startTime">
          <el-form-item label="开始日期" prop="actionBeginTime">
            <el-date-picker
              v-model="form.actionBeginTime"
              type="datetime"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              align="right"
              clearable
            ></el-date-picker>
          </el-form-item>
        </div>
      </el-form>
    </div>

    <div class="execel">
      <el-table
        border
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        @select="pinSelect"
        ref="multipleTable"
        :header-cell-style="{
          background: '#153a6b',
          color: 'white',
          fontSize: '16px',
          border: '1px solid #316aa1'
        }"
        :cell-style="{ background: '#153a6b', color: 'white' }"
      >
        <el-table-column
          type="selection"
          width="55"
          align="center"
          border
        ></el-table-column>
        <!-- <el-table-column type="selection" width="55"  > </el-table-column> -->
        <el-table-column
          type="index"
          label="序号"
          align="center"
          width="100"
          :index="table_index"
        ></el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          align="center"
          sortable="coumnt"
        ></el-table-column>
        <el-table-column
          prop="company"
          label="公司"
          align="center"
          sortable="coumnt"
        ></el-table-column>
        <el-table-column
          prop="department"
          label="部门"
          align="center"
          sortable="coumnt"
        ></el-table-column>
        <el-table-column label="加分" align="center">
          <el-table-column
            prop="department"
            align="center"
            label="有效工时"
            sortable="coumnt"
          ></el-table-column>
          <el-table-column
            prop="numberScenarios"
            align="center"
            label="场景次数"
            sortable="coumnt"
          ></el-table-column>
          <el-table-column
            prop="outputDocument"
            align="center"
            label="产出文档"
            sortable="coumnt"
          ></el-table-column>
          <el-table-column
            prop="documentReference"
            align="center"
            label="文档引用"
            sortable="coumnt"
          ></el-table-column>
          <el-table-column
            prop="name"
            align="center"
            label="项目加权"
            sortable="coumnt"
          ></el-table-column>
        </el-table-column>
        <el-table-column label="减分" align="center">
          <el-table-column
            prop="projectWeighted"
            align="center"
            label="信息安全违规"
            width="150"
            height="20"
            sortable="coumnt"
          ></el-table-column>
          <el-table-column
            prop="lrregularOperation"
            align="center"
            label="不规范操作"
            sortable="coumnt"
          ></el-table-column>
        </el-table-column>
        <el-table-column
          prop="KPI"
          label="KPI"
          align="center"
          sortable="coumnt"
        ></el-table-column>
      </el-table>
    </div>
    <div class="footerSum">
      <el-form
        :inline="true"
        :model="form"
        class="demo-form-inline form"
        ref="form"
      >
        <!-- 取消按钮 -->
        <div class="cancelButton">
          <el-button @click="toggleSelection()">取消</el-button>
        </div>
        <!-- 显示行数 -->
        <div class="showNum">已选中{{ selectedNum }}行</div>
        <!-- 搜索条件 -->
        <div class="seachCond">
          <el-form-item label="搜索条件">
            <el-select
              v-model="form.region5"
              placeholder="白彦斌 一月份"
              style="width:150px"
            >
              <el-option label="李岩 一月份" value="shanghai"></el-option>
              <el-option label="刘杰 二月份" value="beijing"></el-option>
              <el-option label="吴伟 三月份" value="chengdu"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <!-- 分页  -->
        <div class="pag">
          <el-pagination
            align="center"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pagesNum"
            :page-sizes="[25, 50, 100]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next"
            :total="7"
          ></el-pagination>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      checked1: true,
      checked2: false,
      checked3: false,
      checked4: false,
      radio1: 1,
      radio2: 4,
      // 动态修用户提示时间
      nowData: '',
      // 表单里的数据
      form: {
        // 下拉菜单选择
        region1: '',
        region2: '',
        region3: '',
        region4: '',
        region5: '',
        actionBeginTime: '', // 开始日期
        actionEndTime: '' // 结束日期
      },
      // 选中的行数
      selectedNum: 0,
      arr: [],
      origin: -1, // 这里给一个变量作为起点
      pin: false, // 这里给一个变量，默认为false，不按住
      // 存放选中值的数组
      multipleSelection: [],
      // 表格数据
      tableData: [
        {
          name: '白彦斌',
          company: '北京公司',
          department: '研发部',
          workTime: '75.6',
          numberScenarios: '1672',
          outputDocument: '4',
          documentReference: '19',
          projectWeighted: '1.1',
          informationSecurityViolation: '-20',
          lrregularOperation: '-30',
          KPI: '2795'
        },
        {
          name: '沈志国',
          company: '上海公司',
          department: '制造部',
          workTime: '49.2',
          numberScenarios: '1085',
          outputDocument: '2',
          documentReference: '8',
          projectWeighted: '1.0',
          informationSecurityViolation: '-6',
          lrregularOperation: '-20',
          KPI: '1162'
        },
        {
          name: '沈志国',
          company: '上海公司',
          department: '制造部',
          workTime: '49.2',
          numberScenarios: '1085',
          outputDocument: '2',
          documentReference: '8',
          projectWeighted: '1.0',
          informationSecurityViolation: '-6',
          lrregularOperation: '-20',
          KPI: '1162'
        },
        {
          name: '沈志国',
          company: '上海公司',
          department: '制造部',
          workTime: '49.2',
          numberScenarios: '1085',
          outputDocument: '2',
          documentReference: '8',
          projectWeighted: '1.0',
          informationSecurityViolation: '-6',
          lrregularOperation: '-20',
          KPI: '1162'
        },
        {
          name: '沈志国',
          company: '上海公司',
          department: '制造部',
          workTime: '49.2',
          numberScenarios: '1085',
          outputDocument: '2',
          documentReference: '8',
          projectWeighted: '1.0',
          informationSecurityViolation: '-6',
          lrregularOperation: '-20',
          KPI: '1162'
        },
        {
          name: '沈志国',
          company: '上海公司',
          department: '制造部',
          workTime: '49.2',
          numberScenarios: '1085',
          outputDocument: '2',
          documentReference: '8',
          projectWeighted: '1.0',
          informationSecurityViolation: '-6',
          lrregularOperation: '-20',
          KPI: '1162'
        },
        {
          name: '沈志国',
          company: '上海公司',
          department: '制造部',
          workTime: '49.2',
          numberScenarios: '1085',
          outputDocument: '2',
          documentReference: '8',
          projectWeighted: '1.0',
          informationSecurityViolation: '-6',
          lrregularOperation: '-20',
          KPI: '1162'
        }
      ],

      // 分页器数据
      pagesNum: 1, // 当前页
      pageSize: 25, // 每页条数
      total: 5, // 总页数
      // 默认选中第4页
      currentPage4: 4,
      checked: false,
      pageKey: false
    }
  },
  methods: {
    // 取消全选
    toggleSelection (rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row)
        })
      } else {
        this.$refs.multipleTable.clearSelection()
      }
      this.arr = []
      this.selectedNum = 0
    },
    handleSelectionChange (val) {
      this.multipleSelection = val
      if (val.length === 0) {
        this.arr = []
        this.selectedNum = 0
      }
      this.arr.push(val.length)
      this.arr.sort((a, b) => {
        return a - b
      })
      document.onkeyup = e => {
        if (e.keyCode !== '') {
          const max = this.arr[this.arr.length - 1] // 56
          this.selectedNum = max
        } else {
          this.selectedNum = val.length
        }
      }
      this.selectedNum = val.length
    },
    // 这里是select事件开始
    pinSelect (item, index) {
      const data = this.$refs.multipleTable.tableData // 获取所以数据
      const origin = this.origin // 起点数 从-1开始
      const endIdx = index.index // 终点数
      if (this.pin && item.includes(data[origin])) {
        // 判断按住
        const sum = Math.abs(origin - endIdx) + 1 // 这里记录终点
        const min = Math.min(origin, endIdx) // 这里记录起点
        let i = 0
        while (i < sum) {
          const index = min + i
          this.$refs.multipleTable.toggleRowSelection(data[index], true) // 通过ref打点调用方法，第二个必须为true
          i++
        }
      } else {
        this.origin = index.index // 没按住记录起点
      }
    },
    // 这里是select事件结束
    // 分页
    handleSizeChange (val) {
      console.log('每页' + { val } + '条')
    },
    handleCurrentChange (val) {
      console.log('当前页:' + { val })
    },
    // 表格分页序号
    table_index (index) {
      return (this.pagesNum - 1) * this.pageSize + index + 1
    },
    // 获取当前日期
    getSumTimes () {
      // 获取当前的日期， 变量名为tiems
      this.nowData = new Date()
      const d = this.nowData
      let month =
        d.getMonth() + 1 < 10 ? '0' + (d.getMonth() + 1) : d.getMonth() + 1
      let day = d.getDate() < 10 ? '0' + d.getDate() : d.getDate()
      let times = d.getFullYear() + '-' + month + '-' + day + ' '
      this.times = times

      // 获取当前的日期往前推一个月， 变量名为Dtimes
      let dTimes = new Date(new Date(this.nowData).getTime() - 86400000 * 30)
      let Dmonth =
        dTimes.getMonth() + 1 < 10
          ? '0' + (dTimes.getMonth() + 1)
          : dTimes.getMonth() + 1
      let Dday =
        dTimes.getDate() < 10 ? '0' + dTimes.getDate() : dTimes.getDate()
      let Dtimes = dTimes.getFullYear() + '-' + Dmonth + '-' + Dday + ' '
      this.Dtimes = Dtimes
      // 查询里的默认时间
      this.form.actionEndTime = this.times
      this.form.actionBeginTime = this.Dtimes
    },
    // 用户提示时间
    Data () {
      this.nowData = new Date()
      const d = this.nowData
      let month =
        d.getMonth() + 1 < 10 ? '0' + (d.getMonth() + 1) : d.getMonth() + 1
      let day = d.getDate() < 10 ? '0' + d.getDate() : d.getDate()
      let times = d.getFullYear() + '-' + month + '-' + day
      this.nowData = times
      this.zheXianDate = times
    },

    // 查询按钮
    seachBtn () {
      // 首先验证，在执行回车事件
      this.$refs.form.validate()
      const ttime1 = this.form.actionBeginTime
      const ttime2 = this.form.actionEndTime
      const timeStr1 = new Date(ttime1).getTime()
      const timeStr2 = new Date(ttime2).getTime()
      if (timeStr1 > timeStr2) {
        this.$message.error('请合理选择日期')
        // return
      }
      // this.getTableList(this.form)
    }
  },
  // 这里是获取键盘事件
  mounted () {
    window.addEventListener('keydown', code => {
      // 这个是获取键盘按住事件
      // console.log(code); // 这个是你按住键盘打印出键盘信息，在浏览器中自行查看
      if (code.keyCode === 16 && code.shiftKey) {
        // 判断是否按住shift键，是就把pin赋值为true
        this.pin = true
      }
    })
    window.addEventListener('keyup', code => {
      // 这个是获取键盘松开事件
      if (code.keyCode === 16) {
        // 判断是否松开shift键，是就把pin赋值为false
        this.pin = false
      }
      this.tableData.forEach((item, index) => {
        // 遍历索引,赋值给data数据
        item.index = index
      })
    })
  },
  created () {
    // 默认一个月的日期填充
    this.getSumTimes()
    this.Data()
    this.tableData.forEach((item, index) => {
      // 遍历索引,赋值给data数据
      item.index = index
    })
    // document.onkeydown = (e) => {
    //   if (e.keyCode === 16) {
    //     this.pageKey = true
    //   }
    // }
    // document.onkeyup = (e) => {
    //   if (e.keyCode === 16) {
    //     this.pageKey = false
    //   }
    // }
  }
}
</script>

<style lang="scss" scoped>
@import "../../../../../css/clientSideMonitor/behaviorAnalysis/kpiAnalysis/components/kpiDetailsQuery.css";
</style>
