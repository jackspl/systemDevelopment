<template>
  <div class="infoRetr">
    <div class="seachAll clearfix">
      <el-form :inline="true" :model="form" ref="form">
        <!-- 隐藏的groupFlag -->
        <div>
          <el-form-item
            label="groupFlag"
            prop="groupFlag"
            style="display: none;"
          >
            <el-input v-model="form.groupFlag" style="width: 100%"></el-input>
          </el-form-item>
        </div>
        <!-- 用户名 -->
        <div class="searchUserName">
          <el-form-item label="用户名 " prop="userName">
            <el-autocomplete
              v-model="form.userName"
              placeholder="张伟"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="querySearchUserName"
              @select="handleSelectUserName"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <!-- 用户ID -->
        <div class="searchUserID">
          <el-form-item label="用户ID " prop="userId">
            <el-autocomplete
              v-model="form.userId"
              placeholder="01"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="querySearchUserId"
              @select="handleSelectUserId"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <!-- 主机名  -->
        <div class="hostName">
          <el-form-item label="主机名" style="padding: 0px" prop="hostName">
            <el-autocomplete
              v-model="form.hostName"
              placeholder="PC01"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="querySearchHostName"
              @select="handleSelectHostName"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <!-- 场景名称 -->
        <div class="searchScenarioName">
          <el-form-item label="场景名称 " prop="sceneName">
            <el-autocomplete
              v-model="form.sceneName"
              placeholder="发送到PSE、登录"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="querySearchScen"
              @select="handleSelectScen"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <!-- 开始时间 -->
        <div class="searchStarTime">
          <el-form-item label="开始时间 " prop="actionBeginTime">
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
        <!-- 结束时间 -->
        <div class="searchEndTime">
          <el-form-item label="结束时间 " prop="actionEndTime">
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
        <!-- 搜索按钮 -->
        <div class="searchButton">
          <el-button type="primary" @click="seachBtn">查询</el-button>
          <el-button @click="reset">重置</el-button>
        </div>
      </el-form>
    </div>
    <!-- 数据表格框 -->
    <el-row class="tableList">
      <el-col :span="24">
        <div class="grid-content bg-purple-dark">
          <!-- 表格部分 -->
          <div class="content">
            <el-row>
              <el-col :span="24">
                <div class="grid-content bg-purple-dark">
                  <el-table
                    :data="userList"
                    @sort-change="sort_change"
                    border
                    style="width: 100%"
                    :header-cell-style="{
                      background: '#365a88',
                      color: 'white',
                      fontSize: '16px'
                    }"
                    :cell-style="{ background: '#2f5580', color: 'white' }"
                  >
                    <el-table-column
                      type="index"
                      label="序号"
                      align="center"
                      width="80"
                      :index="table_index"
                    ></el-table-column>
                    <el-table-column
                      align="center"
                      prop="id"
                      label="用户ID"
                      width="80"
                    ></el-table-column>
                    <el-table-column
                      align="center"
                      prop="sceneNameDesc"
                      label="场景"
                    ></el-table-column>
                    <el-table-column
                      align="center"
                      prop="userId"
                      label="用户名"
                      width="100"
                    ></el-table-column>
                    <el-table-column
                      align="center"
                      prop="hostName"
                      label="主机名"
                    ></el-table-column>
                    <el-table-column
                      align="center"
                      prop="IP"
                      sortable="custom"
                      label="IP"
                      width="140"
                    ></el-table-column>
                    <el-table-column
                      align="center"
                      prop="actionBeginTime"
                      width="210"
                      label="开始时间"
                    ></el-table-column>
                    <el-table-column
                      align="center"
                      prop="actionEndTime"
                      width="210"
                      label="结束时间"
                    ></el-table-column>
                    <el-table-column
                      align="center"
                      prop="itemId"
                      label="操作Id"
                    ></el-table-column>
                    <el-table-column
                      align="center"
                      prop="operaData"
                      label="操作对象"
                    ></el-table-column>
                    <el-table-column
                      align="center"
                      prop="clientServerDelay"
                      label="ping(ms)"
                    ></el-table-column>
                    <el-table-column
                      align="center"
                      prop="clientServerTTL"
                      label="ping(TTL)"
                    ></el-table-column>
                  </el-table>
                </div>
              </el-col>
            </el-row>
          </div>
        </div>
      </el-col>
    </el-row>
    <div class="footerSum">
      <el-form
        :inline="true"
        :model="form"
        class="demo-form-inline form"
        ref="form"
      >
        <!-- 搜索条件 -->
        <div class="seachCond">
          <el-form-item label="搜素条件">
            <el-select v-model="form.region" placeholder="top20 频次场景">
              <el-option label="top50 频次场景" value="shanghai"></el-option>
              <el-option label="top100 频次场景" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <!-- 图片 -->
        <div class="seachImg">
          <!-- <img class="img" src="../../assets/seve.png" /> -->
          <img class="img" src="../../../../assets/seve.png" />
        </div>
        <!-- 分页  -->
        <div class="pag">
          <el-pagination
            align="center"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="currentPage"
            :page-sizes="[25, 50, 100]"
            :page-size="pagesSize"
            layout="total, sizes, prev, pager, next"
            :total="total"
          ></el-pagination>
        </div>
      </el-form>
    </div>

  </div>
</template>

<script>
import {
  infoRefr,
  getPulldownListUserName,
  getPulldownListUserId,
  getPulldownListScene,
  getPulldownListHostName
} from '@/api/table.js'
import moment from 'moment'

export default {
  data () {
    return {
      // 现在开始时间
      // nowStartTime: '',
      nowStartTime: '',
      // 获取当前的时间往前推一个月
      oldMonthAgo: '',
      form: {
        userName: '', // 用户名
        userId: '',
        sceneNameDesc: '', // 选中的场景名
        sceneName: '', // 英文的场景名
        hostName: '', // 客户端名称
        actionBeginTime: '', // 开始时间
        actionEndTime: '' // 结束时间
      },
      // 表格数据
      userList: [],

      // 用户名下拉菜单
      userName: '',
      // 用户ID下拉菜单
      userId: '',
      // 主机名下拉菜单
      hostNameList: '',
      // 场景名下拉菜单
      sceneName: '',

      // 分页器数据
      currentPage: 1, // 分页默认第一页高亮
      pagesNum: 1, // 当前页
      pagesSize: 25, // 每页条数
      total: 5, // 总页数

      // 下载表格表名+当前日期
      filename: '操作详情检索' + moment().format('YYYY年MM月DD日')
    }
  },
  // 一进页面就执行的钩子函数
  created () {
    this.getSumTimes()
    this.getTableList()
  },
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
      this.pagesNum = 1 // return to the first page after sorting
      this.currentPage = 1
      if (column.prop === 'IP') {
        this.userList = this.userList.sort(
          this.sortFun(column.prop, column.order === 'ascending')
        )
      } else if (column.prop === 'status') {
        this.userList = this.userList.sort(
          this.sortFun(column.prop, column.order === 'ascending')
        )
      } else if (column.prop === 'priority') {
        this.userList = this.userList.sort(
          this.sortFun(column.prop, column.order === 'ascending')
        )
      }
      // this.showed_data = this.userList.slice(0, this.pagesSize) // 排序完显示到第一页
      // console.log(this.showed_data)
    },
    // 表格分页序号
    table_index (index) {
      return (this.pagesNum - 1) * this.pagesSize + index + 1
    },
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
      let nowStartTime =
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
      this.nowStartTime = nowStartTime

      // 获取当前的时间往前推一个月， 变量名为oldMonthAgo
      let monthAgo = new Date(new Date(this.nowData).getTime() - 86400000 * 30)
      let Dmonth =
        monthAgo.getMonth() + 1 < 10
          ? '0' + (monthAgo.getMonth() + 1)
          : monthAgo.getMonth() + 1
      let Dday =
        monthAgo.getDate() < 10 ? '0' + monthAgo.getDate() : monthAgo.getDate()
      let Dhours =
        monthAgo.getHours() < 10
          ? '0' + monthAgo.getHours()
          : monthAgo.getHours()
      let Dmin =
        monthAgo.getMinutes() < 10
          ? '0' + monthAgo.getMinutes()
          : monthAgo.getMinutes()
      let Dsec =
        monthAgo.getSeconds() < 10
          ? '0' + monthAgo.getSeconds()
          : monthAgo.getSeconds()
      let oldMonthAgo =
        monthAgo.getFullYear() +
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
      this.oldMonthAgo = oldMonthAgo
      this.form.actionEndTime = this.nowStartTime
      this.form.actionBeginTime = this.oldMonthAgo
    },
    // 下载Excel表格数据
    handleDownload () {
      this.downloadLoading = true
      // import('../../utils/Export2Excel').then(excel => {
      import('../../../../utils/Export2Excel').then(excel => {
        const tHeader = [
          'ID',
          '场景',
          '用户名',
          '客户端名称',
          'IP',
          '开始时间',
          '结束时间',
          '操作Id',
          '操作对象',
          '服务器ping返回TTL',
          '服务器ping返回ms'
        ]
        const filterVal = [
          'id',
          'sceneNameDesc',
          'userId',
          'hostName',
          'IP',
          'actionBeginTime',
          'actionEndTime',
          'itemId',
          'operaData',
          'clientServerTTL',
          'clientServerDelay'
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
          return v[j]
        })
      )
    },
    // 初始化列表渲染 + 开始时间+结束时间+图表时间自带发送后台
    // 用户列表渲染
    async getTableList (valuee) {
      const value = {
        pagesNum: this.pagesNum,
        pagesSize: this.pagesSize,
        userName: this.form.userName,
        userId: this.form.userId,
        actionBeginTime: this.form.actionBeginTime,
        actionEndTime: this.form.actionEndTime,
        hostName: this.form.hostName,
        sceneName: this.form.sceneName,
        sceneNameDesc: this.form.sceneNameDesc,
        ...valuee
      }
      try {
        const { data } = await infoRefr(value)
        const { tableList } = data
        console.log(tableList)
        if (tableList === null) {
          this.userList = ''
          this.total = '0'
        } else {
          this.userList = tableList
        }
        this.total = data.tableTotalList[0]
        // 右侧柱状图渲染展示
      } catch (e) {
        console.log(e)
      }
    },
    // 分页功能
    // 更改每页个数
    handleSizeChange (val) {
      this.pagesSize = val
      this.pagesNum = 1
      this.currentPage = 1
      this.getTableList()
    },
    // 切换页
    handleCurrentChange (val) {
      this.pagesNum = val
      this.getTableList()
    },

    // 用户模糊查询
    // 第一个参数， 用户输入的值  第二个值回调函数
    querySearchUserName (queryString, callback) {
      // 调用的后台接口
      // 从后台获取到对象数组
      getPulldownListUserName({
        userId: queryString
      })
        .then(res => {
          console.log(res)
          let list = []
          if (res.data.fuzzyUserIdList === null) {
            list = []
          } else {
            res.data.fuzzyUserIdList.forEach(item => {
              list.push({
                value: item
              })
            })
          }
          callback(list)
        })
        .catch(error => {
          console.log(error)
        })
    },
    handleSelectUserName (item) {},

    // 用户id模糊查询
    // 第一个参数， 用户输入的值  第二个值回调函数
    querySearchUserId (queryString, callback) {
      // 调用的后台接口
      // 从后台获取到对象数组
      getPulldownListUserId({
        Id: queryString
      })
        .then(res => {
          let list = []
          if (res.data.fuzzyUserIdList === null) {
            list = []
          } else {
            res.data.fuzzyUserIdList.forEach(item => {
              list.push({
                value: item
              })
            })
          }
          callback(list)
        })
        .catch(error => {
          console.log(error)
        })
    },
    handleSelectUserId (item) {},

    // 主机名模糊查询
    // 第一个参数， 用户输入的值  第二个值回调函数
    querySearchHostName (queryString, callback) {
      // 调用的后台接口
      // 从后台获取到对象数组
      getPulldownListHostName({
        hostName: queryString
      })
        .then(res => {
          let list = []
          if (res.data.fuzzyHostNameList === null) {
            list = []
          } else {
            res.data.fuzzyHostNameList.forEach(item => {
              list.push({
                value: item
              })
            })
          }
          callback(list)
        })
        .catch(error => {
          console.log(error)
        })
    },
    handleSelectHostName (item) {},

    // 场景名称模糊查询
    // 第一个参数， 用户输入的值  第二个值回调函数
    querySearchScen (queryString, callback) {
      // 调用的后台接口
      // 从后台获取到对象数组
      getPulldownListScene({
        sceneName: queryString
      })
        .then(res => {
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
        .catch(error => {
          console.log(error)
        })
    },
    handleSelectScen (item) {},

    // 立即查询
    async seachBtn () {
      // 首先验证，在执行回车事件
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
          this.getTableList(this.form)
        } else {
          return
        }
        if (this.form.hostName === '' || this.form.sceneNameDesc === '') {
          this.categories = ''
          this.MaxTime = 0
          this.MinTime = 0
          this.AvgTime = 0
        }
      })
    },
    // 点击重置按钮
    reset () {
      this.form.userId = ''
      this.form.sceneName = ''
      this.form.hostName = ''
      this.form.actionBeginTime = ''
      this.form.actionEndTime = ''
      this.getSumTimes()
    }
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

<style lang="scss" scoped>
// @import "@../../../../css/infoRetr.css";
@import "../../../../css/clientSideMonitor/sceneMonitor/operationDetailsRetrieval/infoRetr.css";
</style>
