<template>
<!-- 性能概要监控 -->
  <div class="scenSum">
    <div class="seachSum clearfix">
      <el-form ref="form" :model="form">
        <!-- 搜索条件 -->
        <div class="seachCond">
          <el-form-item label="搜素条件" class="the1">
            <el-select v-model="form.region" placeholder="top20 频次场景">
              <el-option
                label="top50 频次场景"
                value="shanghai"
              ></el-option>
              <el-option
                label="top100 频次场景"
                value="beijing"
              ></el-option>
            </el-select>
          </el-form-item>
        </div>
        <!-- 图片 -->
        <div class="seachImg">
          <img class="img" src="../../../../../assets/seve.png" />
        </div>
        <!-- 场景名称 -->
        <div class="sceneName">
          <el-form-item label="场景名称" prop="sceneName">
            <el-autocomplete
              v-model="form.sceneName"
              placeholder="所有场景"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="querySearchScen"
              @select="handleSelectScen"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <!-- 搜索按钮 -->
        <div class="seachButton">
          <el-button type="primary" @click="seachBtn">查询</el-button>
          <el-button @click="reset">重置</el-button>
        </div>
        <!-- 结束时间 -->
        <div class="endTime">
          <el-form-item label="结束时间" prop="actionEndTime">
            <el-date-picker
              v-model="form.actionEndTime"
              type="datetime"
              format="yyyy-MM-dd HH:mm:ss"
              value-format="yyyy-MM-dd HH:mm:ss"
              placeholder="选择日期时间"
              clearable
            ></el-date-picker>
          </el-form-item>
        </div>
        <!-- 开始时间 -->
        <div class="startTime">
          <el-form-item label="开始时间" prop="actionBeginTime">
            <el-date-picker
              v-model="form.actionBeginTime"
              type="datetime"
              format="yyyy-MM-dd HH:mm:ss"
              value-format="yyyy-MM-dd HH:mm:ss"
              placeholder="选择日期时间"
              clearable
            ></el-date-picker>
          </el-form-item>
        </div>
      </el-form>
    </div>
    <el-row>
      <el-col :span="4">
        <div>场景总时间：3300秒</div>
        <div>场景总次数：300</div>
        <div>场景平均时间：11秒</div>
      </el-col>
      <el-col :span="20">
        <div class="histogramBox">
          <!-- 右侧柱状图渲染展示 -->
          <el-col :lg="24" :xl="24">
            <scenSum
              :categories="categories"
              :MaxTime="MaxTime"
              :MinTime="MinTime"
              :AvgTime="AvgTime"
              :htName="htName"
              :actTime="actTime"
              :endTime="endTime"
            ></scenSum>
          </el-col>
        </div>
      </el-col>
    </el-row>

    <!-- 下载Excel按钮 -->
    <el-button
      type="primary"
      icon="el-icon-document"
      round
      @click="handleDownload"
      class="excel"
    >Export Excel</el-button>
    <!-- 底部表格  -->
    <!-- 表格部分 -->
    <div class="content">
      <el-row>
        <el-col :span="24">
          <div class="grid-content bg-purple-dark">
            <el-table
              :data="userList.slice((pagesNum-1)*pageSize,pagesNum*pageSize)"
              @sort-change="sort_change"
              border
              :row-class-name="tableRowClassName"
              :header-cell-style="{background:'#365a88',color:'white',fontSize:'16px',borderRight: '1px solid #344e77'}"
              :cell-style="{background:'#2f5580',color:'white'}"
            >
              <el-table-column
                type="index"
                label="序号"
                align="center"
                width="100"
                :index="table_index"
              ></el-table-column>
              <el-table-column prop="sceneNameDesc" align="center" label="场景"></el-table-column>
              <el-table-column
                prop="operationMinTime"
                align="center"
                sortable="coumnt"
                label="最小响应时间(s)"
              ></el-table-column>

              <el-table-column
                prop="operationAvgTime"
                align="center"
                sortable="coumnt"
                label="平均响应时间(s)"
              ></el-table-column>
               <el-table-column
                prop="operationMaxTime"
                align="center"
                sortable="coumnt"
                label="最大响应时间(s)"
              ></el-table-column>
               <el-table-column
                prop="number"
                align="center"
                sortable="coumnt"
                label="次数"
              ></el-table-column>
               <el-table-column
                prop="totalTime"
                align="center"
                sortable="coumnt"
                label="总时间"
              ></el-table-column>
            </el-table>
          </div>
        </el-col>
      </el-row>
    </div>
    <!-- 分页 -->
    <el-pagination
      align="center"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="currentPage"
      :page-sizes="[25, 50, 100]"
      :page-size="pageSize"
      layout="total, sizes, prev, pager, next"
      :total="userList.length"
    ></el-pagination>
  </div>
</template>

<script>
import { getList, getPulldownListScene } from '@/api/table.js'
import moment from 'moment'

export default {
  // 返回数据存储
  data () {
    return {
      // 分页默认第一页高亮
      currentPage: 1,
      // 现在开始时间
      times: '',
      // 获取当前的时间往前推一个月
      Dtimes: '',
      flag: false,
      // 表单数据
      form: {
        actionBeginTime: '', // 开始时间
        actionEndTime: '', // 结束时间
        sceneNameDesc: '', // 选中的场景名
        sceneName: '' // 英文的场景名
      },

      value: '',

      // Echarts图表数据
      categories: [],
      MaxTime: [],
      MinTime: [],
      AvgTime: [],
      htName: '',
      actTime: '',
      endTime: '',

      // 场景名模糊下拉菜单
      sceneName: '',

      // 表格数据
      userList: [],

      // 下载表格带标题和当前日期
      filename: '性能概要监控' + moment().format('YYYY年MM月DD日'),

      // 分页器数据
      pagesNum: 1, // 当前页
      pageSize: 25, // 每页条数
      total: 5 // 总页数
    }
  },

  // 一进页面就执行的钩子函数
  created () {
    this.getSumTimes()
    this.getUserListBegin()
  },

  // 页面方法
  methods: {
    // 表格排序功能
    sortFun: function (attr, rev) {
      // 第一个参数传入info里的prop表示排的是哪一列，第二个参数是升还是降排序
      if (rev === undefined) {
        rev = 1
      } else {
        rev = rev ? 1 : -1
      }

      return function (a, b) {
        a = a[attr]
        b = b[attr]
        if (a < b) {
          return rev * -1
        }
        if (a > b) {
          return rev * 1
        }
        return 0
      }
    },
    sort_change (column) {
      // column是个形参，就是前面说的info，你叫什么都可以
      console.log(column)
      this.pagesNum = 1 // return to the first page after sorting
      this.currentPage = 1
      if (column.prop === 'operationMaxTime') {
        this.userList = this.userList.sort(
          this.sortFun(column.prop, column.order === 'ascending')
        )
        console.log(this.userList)
      } else if (column.prop === 'status') {
        this.userList = this.userList.sort(
          this.sortFun(column.prop, column.order === 'ascending')
        )
      } else if (column.prop === 'priority') {
        this.userList = this.userList.sort(
          this.sortFun(column.prop, column.order === 'ascending')
        )
      }
      this.showed_data = this.userList.slice(0, this.pageSize) // 排序完显示到第一页

      if (column.prop === 'operationMinTime') {
        this.userList = this.userList.sort(
          this.sortFun(column.prop, column.order === 'ascending')
        )
        console.log(this.userList)
      } else if (column.prop === 'status') {
        this.userList = this.userList.sort(
          this.sortFun(column.prop, column.order === 'ascending')
        )
      } else if (column.prop === 'priority') {
        this.userList = this.userList.sort(
          this.sortFun(column.prop, column.order === 'ascending')
        )
      }
      this.showed_data = this.userList.slice(0, this.pageSize) // 排序完显示到第一页

      if (column.prop === 'operationAvgTime') {
        this.userList = this.userList.sort(
          this.sortFun(column.prop, column.order === 'ascending')
        )
        console.log(this.userList)
      } else if (column.prop === 'status') {
        this.userList = this.userList.sort(
          this.sortFun(column.prop, column.order === 'ascending')
        )
      } else if (column.prop === 'priority') {
        this.userList = this.userList.sort(
          this.sortFun(column.prop, column.order === 'ascending')
        )
      }
      this.showed_data = this.userList.slice(0, this.pageSize) // 排序完显示到第一页
    },
    // 表格分页序号
    table_index (index) {
      return (this.pagesNum - 1) * this.pageSize + index + 1
    },
    // 刚进页面判断当前时间
    getSumTimes () {
      // 获取当前的时间， 变量名为tiems
      this.nowData = new Date()
      const d = this.nowData
      let month =
        d.getMonth() + 1 < 10 ? '0' + (d.getMonth() + 1) : d.getMonth() + 1
      let day = d.getDate() < 10 ? '0' + d.getDate() : d.getDate()
      let hours = d.getHours() < 10 ? '0' + d.getHours() : d.getHours()
      let min = d.getMinutes() < 10 ? '0' + d.getMinutes() : d.getMinutes()
      let sec = d.getSeconds() < 10 ? '0' + d.getSeconds() : d.getSeconds()
      let times =
        d.getFullYear() +
        '-' +
        month +
        '-' +
        day +
        ' ' +
        hours +
        ':' +
        min +
        ':' +
        sec
      this.times = times

      // 获取当前的时间往前推一个月， 变量名为Dtimes
      let dTimes = new Date(new Date(this.nowData).getTime() - 86400000 * 30)
      let Dmonth =
        dTimes.getMonth() + 1 < 10
          ? '0' + (dTimes.getMonth() + 1)
          : dTimes.getMonth() + 1
      let Dday =
        dTimes.getDate() < 10 ? '0' + dTimes.getDate() : dTimes.getDate()
      let Dhours =
        dTimes.getHours() < 10 ? '0' + dTimes.getHours() : dTimes.getHours()
      let Dmin =
        dTimes.getMinutes() < 10
          ? '0' + dTimes.getMinutes()
          : dTimes.getMinutes()
      let Dsec =
        dTimes.getSeconds() < 10
          ? '0' + dTimes.getSeconds()
          : dTimes.getSeconds()
      let Dtimes =
        dTimes.getFullYear() +
        '-' +
        Dmonth +
        '-' +
        Dday +
        ' ' +
        Dhours +
        ':' +
        Dmin +
        ':' +
        Dsec
      this.Dtimes = Dtimes
      this.form.actionEndTime = this.times
      this.form.actionBeginTime = this.Dtimes
    },
    // Excel表格数据
    handleDownload () {
      this.downloadLoading = true
      import('@/utils/Export2Excel').then(excel => {
        const tHeader = [
          '场景',
          '最小响应时间（s)',
          '平均响应时间（s)',
          '最大响应时间（s)',
          '次数',
          '总时间'
        ]
        const filterVal = [
          'sceneNameDesc',
          'operationMinTime',
          'operationAvgTime',
          'operationMaxTime',
          'number',
          'totalTime'
        ]
        const list = this.userList
        const data = this.formatJson(filterVal, list)
        excel.export_json_to_excel({
          header: tHeader, // 表头 必填
          data, // 具体数据 必填
          filename: this.filename, // 非必填
          autoWidth: this.autoWidth, // 非必填
          bookType: this.bookType // 非必填 'xlsx'
        })
        this.downloadLoading = false
      })
    },
    formatJson (filterVal, jsonData) {
      // 处理对应列名下的数据格式的，例如时间Date
      return jsonData.map(v =>
        filterVal.map(j => {
          /* if (j === 'Data') {
          return parseTime(v[j])
        } else {
          return v[j]
        } */
          return v[j]
        })
      )
    },
    // 图表显示
    // 隔行变色
    tableRowClassName ({ row, rowIndex }) {
      if (rowIndex % 2 === 0) {
        return 'warning-row'
      } else {
        return ''
      }
    },
    // 初始化列表渲染 + 开始时间+结束时间+图表时间自带发送后台
    async getUserListBegin (valuee) {
      // 传默认时间到后台
      this.form.actionEndTime = this.times
      this.form.actionBeginTime = this.Dtimes
      const value = {
        pagesNum: this.pagesNum,
        userId: this.form.userId,
        actionBeginTime: this.form.actionBeginTime,
        actionEndTime: this.form.actionEndTime,
        hostName: this.form.hostName,
        sceneName: this.form.sceneName,
        sceneNameDesc: this.form.sceneNameDesc,
        ...valuee
      }
      try {
        const { data } = await getList(value)
        const { hostNameList, tableList } = data
        if (tableList === null) {
          this.userList = ''
          this.total = '0'
        } else {
          this.userList = tableList
        }
        this.hostNameList = hostNameList
        this.sceneNameDesc = tableList.sceneNameDesc
        this.total = data.tableTotalList[0]

        this.actTime = moment(this.form.actionBeginTime).format(
          'YYYY-MM-DD HH:mm:ss'
        )
        this.endTime = moment(this.form.actionEndTime).format(
          'YYYY-MM-DD HH:mm:ss'
        )
        this.categories = tableList.map(item => {
          return item.sceneNameDesc
        })
        this.MaxTime = tableList.map(item => {
          return item.operationMaxTime
        })
        this.MinTime = tableList.map(item => {
          return item.operationMinTime
        })
        this.AvgTime = tableList.map(item => {
          return item.operationAvgTime
        })
        this.htName = this.form.sceneName
        this.endTime = this.times
        this.actTime = this.Dtimes

        // 右侧柱状图渲染展示
      } catch (e) {
        console.log(e)
      }
    },
    // 用户列表渲染
    async getUserList (valuee) {
      const value = {
        pagesNum: this.pagesNum,
        userId: this.form.userId,
        actionBeginTime: this.form.actionBeginTime,
        actionEndTime: this.form.actionEndTime,
        hostName: this.form.hostName,
        sceneName: this.form.sceneName,
        sceneNameDesc: this.form.sceneNameDesc,
        ...valuee
      }
      try {
        const { data } = await getList(value)
        const { hostNameList, tableList } = data
        this.hostNameList = hostNameList
        if (tableList === null) {
          this.userList = ''
          this.total = '0'
        } else {
          this.userList = tableList
        }
        this.sceneNameDesc = tableList.sceneNameDesc
        this.total = data.tableTotalList[0]

        this.actTime = moment(this.form.actionBeginTime).format(
          'YYYY-MM-DD HH:mm:ss'
        )
        this.endTime = moment(this.form.actionEndTime).format(
          'YYYY-MM-DD HH:mm:ss'
        )
        this.categories = tableList.map(item => {
          return item.sceneNameDesc
        })
        this.MaxTime = tableList.map(item => {
          return item.operationMaxTime
        })
        this.MinTime = tableList.map(item => {
          return item.operationMinTime
        })
        this.AvgTime = tableList.map(item => {
          return item.operationAvgTime
        })
        this.htName = this.form.sceneName

        // 右侧柱状图渲染展示
      } catch (e) {
        console.log(e)
      }
    },
    // 分页功能
    // 更改每页个数
    handleSizeChange (val) {
      this.pageSize = val
      this.pagesNum = 1
      this.currentPage = 1
      this.getUserList()
    },
    // 切换页
    handleCurrentChange (val) {
      this.pagesNum = val
      this.getUserList()
    },
    // 立即查询
    async seachBtn () {
      // 首先验证，在执行回车事件
      this.$refs.form.validate()
      const ttime1 = this.form.actionBeginTime
      const ttime2 = this.form.actionEndTime
      const timeStr1 = new Date(ttime1).getTime()
      const timeStr2 = new Date(ttime2).getTime()
      if (timeStr1 > timeStr2) {
        this.$message.error('请合理选择时间')
        return
      }
      this.pagesNum = 1

      await this.$refs.form.validate(isValid => {
        if (isValid) {
          this.getUserList(this.form)
        } else {
        }
      })
      if (this.form.sceneNameDesc === '') {
        this.categories = ''
        this.MaxTime = 0
        this.MinTime = 0
        this.AvgTime = 0
      }
    },
    // 点击重置按钮
    reset () {
      this.form.userId = ''
      this.form.sceneName = ''
      this.form.hostName = ''
      this.form.actionBeginTime = ''
      this.form.actionEndTime = ''
      this.getSumTimes()
    },
    // 第一个参数， 用户输入的值  第二个值回调函数
    // 场景模糊查询
    querySearchScen (queryString, callback) {
      // 从后台获取到对象数组
      getPulldownListScene({
        sceneName: queryString
      }).then(res => {
        let list = []
        if (res.data.fuzzySceneList === null) {
          list = []
        } else {
          res.data.fuzzySceneList.forEach(item => {
            list.push({
              value: item
            })
          })
        }
        callback(list)
      })
    },
    handleSelectScen (item) {}
  },
  mounted () {
    const that = this
    document.onkeydown = function (e) {
      if (window.event.keyCode === 13) {
        that.seachBtn()
      }
    }
  }
}
</script>

<style lang='scss' scoped>
@import "../../../../../css/base/base.css";
@import "../../../../../css/clientSideMonitor/sceneMonitor/scenePerformanceMonitor/scenSum.css";
</style>
