
<template>
  <!-- 异常查看 -->
  <div class="abnormalView clearfix">
    <div class="seachAllSum">
      <el-form :inline="true" :model="form" ref="form">
        <div class="seachF1 clearfix">
          <div class="searchCriteria">
            <!-- 公司选择 -->
            <el-form-item label="搜索条件">
              <el-select v-model="form.region1" placeholder="北京公司 研发部 白彦斌 异常登录 2019年 上半年">
                <el-option label="北京公司 研发部 白彦斌 异常登录 2019年 上半年" value="shanghai"></el-option>
                <el-option label="成都公司 制造部 刘伟 异常登录 2019年 下半年" value="beijing"></el-option>
                <el-option label="广州公司 销售部 张斌 异常登录 2019年 上半年" value="chengdu"></el-option>
              </el-select>
            </el-form-item>
          </div>
          <div class="searchImg">
            <img src="../../../../../assets/seve.png" style="margin-top:5px" />
          </div>
          <div class="userInfoAll">
            <div class="userInfo">
              <el-form-item label="用户信息"></el-form-item>
            </div>
            <div class="userInfoCompany">
              <!-- 公司 -->
              <el-form-item label="公司">
                <el-select v-model="form.region2" placeholder="北京公司">
                  <el-option label="上海公司" value="shanghai"></el-option>
                  <el-option label="广州公司" value="beijing"></el-option>
                  <el-option label="成都公司" value="chengdu"></el-option>
                </el-select>
              </el-form-item>
            </div>
            <div class="userInfoDep">
              <!-- 部门 -->
              <el-form-item label="部门" class="deparWidth">
                <el-select v-model="form.region3" placeholder="研发部">
                  <el-option label="制造部" value="shanghai"></el-option>
                  <el-option label="销售部" value="beijing"></el-option>
                  <el-option label="制造部" value="chengdu"></el-option>
                </el-select>
              </el-form-item>
            </div>
            <div class="userChange">
              <!-- 公司 -->
              <el-form-item label="用户">
                <el-select v-model="form.region4" placeholder="白彦斌">
                  <el-option label="赵忠祥" value="shanghai"></el-option>
                  <el-option label="刘志伟" value="beijing"></el-option>
                  <el-option label="孙雷" value="chengdu"></el-option>
                </el-select>
              </el-form-item>
            </div>
          </div>
        </div>
        <div class="seachF2 clearfix">
          <div class="seachStarTime">
            <!-- 开始时间 -->
            <el-form-item label="开始时间" prop="actionBeginTime1" class="startTime">
              <el-date-picker
                v-model="form.actionBeginTime1"
                align="right"
                type="datetime"
                format="yyyy-MM-dd HH:mm:ss"
                value-format="yyyy-MM-dd HH:mm:ss"
                clearable
                placeholder="选择日期"
              ></el-date-picker>
            </el-form-item>
          </div>
          <div class="seachEndTime">
            <!-- 结束时间 -->
            <el-form-item label="结束时间" prop="actionEndTime1" class="entTime">
              <el-date-picker
                v-model="form.actionEndTime1"
                type="datetime"
                format="yyyy-MM-dd HH:mm:ss"
                value-format="yyyy-MM-dd HH:mm:ss"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
          <div class="userInfoAll infoMation">
            <div class="infoHost">
              <!-- 文档信息 -->
              <el-form-item label="文档信息"></el-form-item>
            </div>
            <div class="infoHostCompany">
              <!-- 公司 -->
              <el-form-item label="公司">
                <el-select v-model="form.region5" placeholder="北京公司">
                  <el-option label="上海公司" value="shanghai"></el-option>
                  <el-option label="广州公司" value="beijing"></el-option>
                  <el-option label="成都公司" value="chengdu"></el-option>
                </el-select>
              </el-form-item>
            </div>
            <div class="infoHostDep">
              <!-- 部门 -->
              <el-form-item label="部门">
                <el-select v-model="form.region6" placeholder="研发部">
                  <el-option label="销售部" value="shanghai"></el-option>
                  <el-option label="制造部" value="beijing"></el-option>
                  <el-option label="财务部" value="chengdu"></el-option>
                </el-select>
              </el-form-item>
            </div>
            <div class="infoHostName">
              <!-- 主机名 -->
              <el-form-item label="所有者">
                <el-select v-model="form.region7" placeholder="白彦斌">
                  <el-option label="白彦斌" value="shanghai"></el-option>
                  <el-option label="白彦斌" value="beijing"></el-option>
                  <el-option label="白彦斌" value="chengdu"></el-option>
                </el-select>
              </el-form-item>
            </div>
          </div>
        </div>
        <div class="seachF3">
          <!-- 滑动按钮 -->
          <div class="changeButton clearfix">
            <div class="allButton" @click="allButton" :class="{'textColor':flagAll}">所有登录</div>
            <div class="abnormalButton" @click="abnormalButton" :class="{'textColor':flagAbn}">异常登录</div>
          </div>
          <div class="acrossDepView">
            <el-switch v-model="value3" inactive-text="跨部门查看"></el-switch>
          </div>
          <div class="confDocumentsView">
            <el-switch v-model="value4" inactive-text="查看机密文件"></el-switch>
          </div>
        </div>
        <div class="seachF4">
          <el-button type="primary" @click="seachBtn" class="seachBtn">查询</el-button>
        </div>
      </el-form>
    </div>
    <!-- 表格 -->
    <div class="tableList">
      <el-table
        :data="userList.slice((pagesNum-1)*pageSize,pagesNum*pageSize)"
        border
        :header-cell-style="{background:'#1f4271',color:'white',fontSize:'16px',border: '1px solid #405a86'}"
        :cell-style="{background:'#16457b',color:'white'}"
        @sort-change="sortChange"
      >
        <el-table-column type="index" label="序号" align="center" width="100" :index="table_index"></el-table-column>
        <el-table-column align="center" prop="actionBeginTime" label="时间" width="178px" sortable="coumnt"></el-table-column>
        <el-table-column align="center" prop="employeeName" label="公司"></el-table-column>
        <el-table-column align="center" prop="employeeGroup" label="部门"></el-table-column>
        <el-table-column align="center" prop="employeeName" label="用户"></el-table-column>
        <el-table-column align="center" prop="employeeName" label="主机所在公司"></el-table-column>
        <el-table-column align="center" prop="hostNameGroup" label="主机所在部门"></el-table-column>
        <el-table-column align="center" prop="hostName" label="主机名"></el-table-column>
        <el-table-column align="center" prop="IP" label="主机IP"></el-table-column>
        <el-table-column align="center" prop="employeeName" label="使用时长"></el-table-column>
      </el-table>
    </div>
    <!-- 分页 -->
    <el-col :span="24" class="paging">
      <div class="grid-content">
        <el-pagination
          align="center"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagesNum"
          :page-sizes="[25, 50, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next"
          :total="userList.length"
        ></el-pagination>
      </div>
    </el-col>
  </div>
</template>

<script>
import { getPLMTable } from '@/api/table.js'

export default {
  // components: {
  //   layout: layout
  // },
  data () {
    return {
      // 表格数据
      userList: [],
      // 排序flag
      paiXuFlag: '',
      // 用于排序
      prop1: '',
      upDing: '',
      downDing: '',
      columnOrder: '',
      value: '',
      // 分页器数据
      pagesNum: 1, // 当前页码
      pageSize: 25, // 每页的数据条数
      total: 5, // 总条数
      // 跨部门查看
      value3: true,
      value4: true,
      //  所有登录选项
      flagAll: true,
      flagAbn: false,
      form: {
        region1: '',
        region2: '',
        region3: '',
        region4: '',
        region5: '',
        region6: '',
        region7: '',
        actionBeginTime1: '2019-01-01 01:01:01', // 开始日期
        actionEndTime1: '2019-01-01 23:23:50', // 结束日期
        actionBeginTime2: '', // 开始日期
        actionEndTime2: '', // 结束日期
        actionBeginTime3: '2019-01-01', // 开始日期
        actionEndTime3: '2019-12-31' // 结束日期
      }
    }
  },
  created () {
    // 表格数据渲染
    this.getTableList()
  },
  methods: {
    // 所有数据圆角按钮
    allButton () {
      this.flagAll = true
      this.flagAbn = false
    },
    // 异常数据圆角按钮
    abnormalButton () {
      this.flagAll = false
      this.flagAbn = true
    },
    // 表格分页序号
    table_index (index) {
      return (this.pagesNum - 1) * this.pageSize + index + 1
    },
    // 排序方法
    sortChange (column) {
      this.prop1 = column.prop
      if (this.lable === '1') {
        if (this.prop1 === 'actionBeginTime') {
          if (column.order === 'ascending') {
            this.upDing = column.order
            this.downDing = ''
            this.pagesNum = 1
            this.paiXuFlag = 3
            this.getTableList({ paiXuFlag: this.paiXuFlag })
          }
          if (column.order === 'descending') {
            this.downDing = column.order
            this.upDing = ''
            this.pagesNum = 1
            this.paiXuFlag = 4
            this.getTableList({ paiXuFlag: this.paiXuFlag })
          }
          if (column.order === null) {
            this.downDing = column.order
            this.pagesNum = 1
            this.paiXuFlag = ''
            this.getTableList({ paiXuFlag: this.paiXuFlag })
          }
        }
      }
      if (this.lable === '2') {
        if (this.prop1 === 'actionBeginTime') {
          this.columnOrder = column.order
          if (column.order === 'ascending') {
            this.upDing = column.order
            this.downDing = ''
            this.pagesNum = 1
            this.paiXuFlag = 3
            this.exceptionLoginFlag = 1
            this.getTableList({
              exceptionLoginFlag: this.exceptionLoginFlag,
              paiXuFlag: this.paiXuFlag
            })
          }
          if (column.order === 'descending') {
            this.downDing = column.order
            this.upDing = ''
            this.pagesNum = 1
            this.paiXuFlag = 4
            this.exceptionLoginFlag = 1
            this.getTableList({
              exceptionLoginFlag: this.exceptionLoginFlag,
              paiXuFlag: this.paiXuFlag
            })
          }
          if (column.order === null) {
            this.downDing = column.order
            this.pagesNum = 1
            this.paiXuFlag = ''
            this.exceptionLoginFlag = 1
            this.getTableList({
              exceptionLoginFlag: this.exceptionLoginFlag,
              paiXuFlag: this.paiXuFlag
            })
          }
        }
      }
    },
    // 渲染表格
    async getTableList (Flag) {
      const value = {
        employeeName: this.form.employeeName,
        actionBeginTime: this.form.actionBeginTime,
        actionEndTime: this.form.actionEndTime,
        employeeProject: this.form.employeeProject,
        employeeGroup: this.form.employeeGroup,
        hostName: this.form.hostName,
        hostNameGroup: this.form.hostNameGroup,
        pagesNum: this.pagesNum,
        ...Flag
      }
      try {
        const { data } = await getPLMTable(value)
        const { tableList, tableTotalList } = data
        if (tableList === null) {
          this.userList = ''
          this.total = '0'
        } else {
          this.userList = tableList
        }
        this.total = tableTotalList[0]
      } catch (e) {
        console.log(e)
      }
    },
    // 分页功能

    // 更改每页个数
    handleSizeChange (val) {
      this.pagesNum = 1
      this.pageSize = val
      this.getTableList()
    },
    // 切换页
    handleCurrentChange (val) {
      this.pagesNum = val
      this.getTableList()
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
  destroyed () {},
  mounted () {}
}
</script>

<style lang='scss'>
@import "../../../../../css/clientSideMonitor/behaviorAnalysis/informationSecurity/components/abnormalView.css";
</style>
