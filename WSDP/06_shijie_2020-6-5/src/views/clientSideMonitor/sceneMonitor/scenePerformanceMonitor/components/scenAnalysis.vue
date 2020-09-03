<template>
<!-- 基于趋势分析 -->
  <div class="scenAnalysis">
    <div class="seachSum clearfix">
      <el-form ref="form" :model="form">
        <!-- 搜索条件 -->
        <div class="seachCond">
          <el-form-item label="搜素条件">
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
          <el-form-item label="结束时间" prop="actionEndTime1">
            <el-date-picker
              v-model="form.actionEndTime1"
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
          <el-form-item label="开始时间" prop="actionBeginTime1">
            <el-date-picker
              v-model="form.actionBeginTime1"
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
      <!-- 右侧柱状图渲染展示 -->
      <el-col :lg="24" :xl="24" class="borderCard">
        <scenAnalysis
          :categories="categories"
          :MaxTime="MaxTime"
          :MinTime="MinTime"
          :AvgTime="AvgTime"
          :htName="htName"
          :actTime="actTime"
          :endTime="endTime"
        ></scenAnalysis>
      </el-col>
    </el-row>

    <!-- 客户端性能搜索条件 -->
    <div class="seachSum clearfix">
      <el-form ref="form2" :model="form2">
        <!-- 搜索条件 -->
        <div class="seachCond">
          <el-form-item label="搜素条件">
            <el-select v-model="form2.region" placeholder="top20 频次场景">
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
        <!-- 用户名 -->
        <div class="userName">
          <el-form-item label="用户" prop="userId">
            <el-autocomplete
              v-model="form2.userId"
              placeholder="张伟"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="querySearchUser"
              @select="handleSelectUser"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <!-- 场景名称 -->
        <div class="sceneName">
          <el-form-item label="场景" prop="sceneName">
            <el-autocomplete
              v-model="form2.sceneName"
              placeholder="登录，登出"
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
          <el-button type="primary" @click="seachBtn2">查询</el-button>
          <el-button @click="reset2">重置</el-button>
        </div>
        <!-- 结束时间 -->
        <div class="endTime">
          <el-form-item label="结束时间" prop="actionEndTime2">
            <el-date-picker
              v-model="form2.actionEndTime2"
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
          <el-form-item label="开始时间" prop="actionBeginTime2">
            <el-date-picker
              v-model="form2.actionBeginTime2"
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
      <!-- 右侧柱状图渲染展示 -->
      <el-col :lg="24" :xl="24" id="card1">
        <div id="main19" style="width: 100%; height: 430px;background-color: rgb(22, 66, 118);"></div>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import { getList, getPulldownListScene, getViewZheXian, getPulldownListUserId } from '@/api/table.js'
import moment from 'moment'
// import scenClient from '../../../../components/scenClient'
export default {
  // components: {
  //   scenClient: scenClient
  // },
  // 返回数据存储
  data () {
    return {
      // 模拟登录用户数量
      zheXianFlag: 2,
      zheXianDates: '',
      // 分页默认第一页高亮
      currentPage: 1,
      // 现在开始时间
      times: '',
      // 获取当前的时间往前推一个月
      Dtimes: '',
      flag: false,
      // 表单数据
      form: {
        region1: '',
        region2: '',
        actionBeginTime1: '', // 开始时间
        actionEndTime1: '', // 结束时间
        sceneNameDesc: '', // 选中的场景名
        sceneName: '' // 英文的场景名
      },

      form2: {
        region1: '',
        region2: '',
        actionBeginTime2: '', // 开始时间
        actionEndTime2: '', // 结束时间
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
    // 初始化带上时间
    this.Data()
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
    // 用户提示时间
    Data () {
      this.nowData = new Date()
      const d = this.nowData
      let month =
        d.getMonth() + 1 < 10 ? '0' + (d.getMonth() + 1) : d.getMonth() + 1
      let day = d.getDate() < 10 ? '0' + d.getDate() : d.getDate()
      let times = d.getFullYear() + '-' + month + '-' + day
      this.zheXianDate = times
      this.zheXianDates = times
      this.zheXianDateDown = times
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
      this.form.actionEndTime1 = this.times
      this.form.actionBeginTime1 = this.Dtimes
      this.form.actionEndTime2 = this.times
      this.form.actionBeginTime2 = this.Dtimes
      this.form2.actionEndTime1 = this.times
      this.form2.actionBeginTime1 = this.Dtimes
      this.form2.actionEndTime2 = this.times
      this.form2.actionBeginTime2 = this.Dtimes
    },
    // Excel表格数据
    handleDownload () {
      this.downloadLoading = true
      import('@/utils/Export2Excel').then(excel => {
        const tHeader = [
          '场景操作名称',
          '最大响应时间（s)',
          '最小响应时间（s)',
          '平均响应时间（s)'
        ]
        const filterVal = [
          'sceneNameDesc',
          'operationMaxTime',
          'operationMinTime',
          'operationAvgTime'
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
      this.form.actionEndTime1 = this.times
      this.form.actionBeginTime1 = this.Dtimes
      this.form.actionEndTime2 = this.times
      this.form.actionBeginTime2 = this.Dtimes
      const value = {
        pagesNum: this.pagesNum,
        userId: this.form.userId,
        actionBeginTime: this.form.actionBeginTime1,
        actionEndTime: this.form.actionEndTime1,
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

        this.actTime = moment(this.form.actionBeginTime1).format(
          'YYYY-MM-DD HH:mm:ss'
        )
        this.endTime = moment(this.form.actionEndTime1).format(
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
        actionBeginTime: this.form.actionBeginTime1,
        actionEndTime: this.form.actionEndTime1,
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

        this.actTime = moment(this.form.actionBeginTime1).format(
          'YYYY-MM-DD HH:mm:ss'
        )
        this.endTime = moment(this.form.actionEndTime1).format(
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
    // 登录用户数量柱状图数据
    async getInfoListDay (calback) {
      const value = {
        zheXianFlag: this.zheXianFlag,
        zheXianDate: this.zheXianDates,
        pagesNum: this.pagesNum,
        userId: this.form2.userId,
        actionBeginTime: this.form2.actionBeginTime2,
        actionEndTime: this.form2.actionEndTime2,
        hostName: this.form2.hostName,
        sceneName: this.form2.sceneName,
        sceneNameDesc: this.form2.sceneNameDesc
      }
      try {
        const { data } = await getViewZheXian(value)
        console.log(data)
        const {
          zheXianListAll,
          zheXianListCrossDept,
          zheXianListSecretFile
        } = data
        // 所有数据
        const AllXTimes = zheXianListAll.map(item => {
          return item.zheXianX
        })
        const AllYNums = zheXianListAll.map(item => {
          return item.zheXianY
        })

        // 跨部门数据
        const CrosXTimes = zheXianListCrossDept.map(item => {
          return item.zheXianX
        })

        const CrosYNums = zheXianListCrossDept.map(item => {
          return item.zheXianY
        })

        // 机密文件下载数据
        const SecrXTimes = zheXianListSecretFile.map(item => {
          return item.zheXianX
        })

        const SecrYNums = zheXianListSecretFile.map(item => {
          return item.zheXianY
        })
        calback(
          AllXTimes,
          AllYNums,
          CrosXTimes,
          CrosYNums,
          SecrXTimes,
          SecrYNums
        )
      } catch (e) {
        console.log(e)
      }
    },
    // 立即查询
    async seachBtn () {
      // 首先验证，在执行回车事件
      this.$refs.form.validate()
      const ttime1 = this.form.actionBeginTime1
      const ttime2 = this.form.actionEndTime1
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
      this.form.actionBeginTime1 = ''
      this.form.actionEndTime1 = ''
      this.getSumTimes()
    },
    // --------------------------------------------------
    // 立即查询
    async seachBtn2 () {
      // 首先验证，在执行回车事件
      // this.$refs.form.validate()
      // const ttime1 = this.form2.actionBeginTime2
      // const ttime2 = this.form2.actionEndTime2
      // const timeStr1 = new Date(ttime1).getTime()
      // const timeStr2 = new Date(ttime2).getTime()
      // if (timeStr1 > timeStr2) {
      //   this.$message.error('请合理选择时间')
      //   return
      // }
      // this.pagesNum = 1

      // await this.$refs.form.validate(isValid => {
      //   if (isValid) {
      //     this.getInfoListDay(this.form2)
      //   } else {
      //   }
      // })
      // if (this.form.sceneNameDesc === '') {
      //   this.categories = ''
      //   this.MaxTime = 0
      //   this.MinTime = 0
      //   this.AvgTime = 0
      // }
    },
    // 点击重置按钮
    reset2 () {
      this.form.userId = ''
      this.form.sceneName = ''
      this.form.hostName = ''
      this.form.actionBeginTime2 = ''
      this.form.actionEndTime2 = ''
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
    handleSelectScen (item) {},
    // 用户模糊查询
    // 第一个参数， 用户输入的值  第二个值回调函数
    querySearchUser (queryString, callback) {
      if (this.form.userId === '') {
        this.flag = false
      } else {
        this.flag = false
      }
      // 调用的后台接口
      // 从后台获取到对象数组
      getPulldownListUserId({
        userId: queryString
      }).then(res => {
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
    },
    handleSelectUser (item) {},
    // 登录用户数量柱状图
    initItem (
      AllXTimes,
      AllYNums,
      CrosXTimes,
      CrosYNums,
      SecrXTimes,
      SecrYNums
    ) {
      let myChart1 = this.$echarts.init(document.getElementById('main19'))

      this.initHeight = document.getElementById('card1').offsetHeight
      this.initWidth = document.getElementById('card1').offsetWidth

      const option1 = {
        title: {
          text: '客户端性能趋势图',
          subtext: '用户: 张伟' + '  ' + this.form.sceneNameDesc + '统计周期:' + this.form.actionBeginTime2 + '~' + this.form.actionEndTime2,
          subtextStyle: {
            color: 'white',
            fontSize: 16
          },
          x: 'center',
          textStyle: {
            fontWeight: 400,
            fontSize: 20,
            color: 'white'
          },
          top: 10
        },
        // backgroundColor: '#040f3c',
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: '2%',
          right: '4%',
          bottom: '14%',
          top: '16%',
          containLabel: true
        },
        legend: {
          data: ['登录', '登出', '创建零组件'],
          orient: 'horizontal',
          x: 'center',
          top: '400',
          icon: 'rectangle',
          textStyle: {
            // 图例文字的样式
            color: '#52a9d0',
            fontSize: 12
          },
          itemWidth: 20,
          itemHeight: 15,
          itemGap: 30
        },
        xAxis: {
          type: 'category',
          data: CrosXTimes,
          axisLine: {
            lineStyle: {
              color: 'white'
            }
          },
          axisLabel: {
            // interval: 0,
            // rotate: 40,
            textStyle: {
              fontFamily: 'Microsoft YaHei'
            }
          }
        },

        yAxis: {
          type: 'value',
          left: '5',
          name: '响应时间(秒)',
          textStyle: {
            color: 'white'
          },
          axisLine: {
            lineStyle: {
              color: '#4798a5',
              left: 10
            }
          },
          splitLine: {
            show: true
          },
          axisLabel: {
            textStyle: {
              color: '#4798a5' // 这里用参数代替了
            }
          },
          top: 50
        },
        dataZoom: [
          {
            show: true,
            height: 12,
            xAxisIndex: [0],
            bottom: '8%',
            start: 10,
            end: 90,
            handleIcon:
              'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
            handleSize: '110%',
            handleStyle: {
              color: '#d3dee5'
            },
            textStyle: {
              color: '#fff'
            },
            borderColor: '#90979c'
          },
          {
            type: 'inside',
            show: true,
            height: 15,
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: '登录',
            type: 'line',
            barWidth: '15%',
            color: '#8fc31f',
            data: CrosYNums,
            label: {
              normal: {
                show: true,
                position: 'top'
              }
            }
          },
          {
            name: '登出',
            type: 'line',
            barWidth: '15%',
            color: '#00b7ee',
            data: SecrYNums,
            label: {
              normal: {
                show: true,
                position: 'top'
              }
            }
          },
          {
            name: '创建零组件',
            type: 'line',
            barWidth: '15%',
            color: '#ff00ff',
            data: AllYNums,
            label: {
              normal: {
                show: true,
                position: 'top'
              }
            }
          }
        ]
      }

      myChart1.setOption(option1)
      let resize = {
        height: this.initHeight,
        width: this.initWidth
      }

      myChart1.resize(resize)
    }
  },
  mounted () {
    const that = this
    document.onkeydown = function (e) {
      if (window.event.keyCode === 13) {
        that.seachBtn()
      }
    }
    // 登录用户数量柱状图回调函数
    this.getInfoListDay(this.initItem)
  }
}
</script>

<style lang='scss' scoped>
// @import "../../../../css/scenAnalysis.css";
@import "../../../../../css/clientSideMonitor/sceneMonitor/scenePerformanceMonitor/scenAnalysis.css";
</style>
