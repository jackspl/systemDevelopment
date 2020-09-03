<template>
  <div class="specificContextClient">
    <div class="seachSum clearfix">
      <el-form ref="form" :model="form">
        <!-- 隐藏的groupFlag -->
        <div>
          <el-form-item
            label="groupFlag"
            prop="groupFlag"
            style="display: none;"
          >
            <el-input v-model="form.groupFlag"></el-input>
          </el-form-item>
        </div>
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
          <img class="img" src="../../../../../assets/seve.png" />
        </div>
        <!-- 场景名称 -->
        <div class="sceneName">
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
        <!-- 主机名 -->
        <div class="hostName">
          <el-form-item label="主机名 " prop="hostName">
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
        <!-- 搜索按钮 -->
        <div class="seachButton">
          <el-button type="primary" @click="seachBtn">查询</el-button>
          <el-button @click="reset">重置</el-button>
        </div>
        <!-- 结束时间 -->
        <div class="endTime">
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
        <!-- 开始时间 -->
        <div class="startTime">
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
      </el-form>
    </div>

    <el-row>
      <el-col :span="4">
        <div id="main555" style="width: 100%; height: 430px"></div>
      </el-col>
      <el-col :span="20">
        <div class="histogramBox">
          <!-- 右侧柱状图渲染展示 -->
          <el-col :lg="24" :xl="24">
          <specificContextClient
            :categories="categories"
            :MaxTime="MaxTime"
            :userName="userName"
            :htName="htName"
            :scenName="scenName"
            :actTime="actTime"
            :endTime="endTime"
          ></specificContextClient>
          </el-col>
        </div>
      </el-col>
    </el-row>
    <!-- 下载Excel按钮 -->
    <!-- <el-button
      type="primary"
      icon="el-icon-document"
      round
      @click="handleDownload"
      class="excel"
    >Export Excel</el-button> -->

    <!-- 底部表格  -->
    <el-row style="padding: 0px">
      <el-col :span="24">
        <div class="grid-content bg-purple-dark">
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
                    <!-- <el-table-column prop="sceneNameDesc" align="center" label="场景名"></el-table-column> -->
                    <el-table-column prop="hostName" width="150" align="center" label="主机名"></el-table-column>
                    <el-table-column
                      prop="operationCount"
                      align="center"
                      sortable="coumnt"
                      label="场景操作次数"
                      width="150"
                    ></el-table-column>
                    <el-table-column align="center" label="次数时间分布">
                <template slot-scope="{row}">
                  <CellTable v-show="isShowCellTable&&row.cellForm" :tableDate="row.cellForm"></CellTable>
                </template>
              </el-table-column>
                  </el-table>
                </div>
              </el-col>
            </el-row>
          </div>
        </div>
      </el-col>
    </el-row>

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
import {
  getCountList,
  getPulldownListScene,
  getPulldownListHostName
} from '@/api/table.js'
import CellTable from '@/components/CellTable'
import moment from 'moment'
export default {
  components: {
    CellTable: CellTable
  },
  data () {
    return {
      isShowCellTable: true,
      // 分页默认第一页高亮
      currentPage: 1,
      // 现在开始时间
      times: '',
      // 获取当前的时间往前推一个月
      Dtimes: '',
      flag: true,
      form: {
        region: '',
        groupFlag: '2',
        userId: '', // 用户名
        sceneNameDesc: '', // 选中的场景名
        sceneName: '', // 英文的场景名
        hostName: '', // 客户端名称
        actionBeginTime: '', // 开始时间
        actionEndTime: '', // 结束时间
        sceneNameCount: '' // 场景操作次数
      },

      // Echarts图表数据
      categories: [],
      MaxTime: [],
      userName: '',
      htName: '',
      scenName: '',
      actTime: '',
      endTime: '',

      // 场景名下拉菜单
      sceneName: '',
      // 客户端下拉菜单
      hostNameList: '',

      // 表格数据
      userList: [],

      value: '',
      // 分页器数据
      pagesNum: 1, // 当前页
      pageSize: 25, // 每页条数
      total: 5, // 总页数

      filename: '基于特定场景（客户端）监控' + moment().format('YYYY年MM月DD日')
    }
  },
  created () {
    this.getSumTimes()
    this.getUserListBegin()
  },

  methods: {
    // 图表
    pageImg () {
      let myChart555 = this.$echarts.init(document.getElementById('main555'))
      const option555 = {
        color: ['#98c831', '#f4a014', '#eb1460'],
        animation: false,
        tooltip: {
          trigger: 'item',
          formatter: '{a} {b} :{d}%',
          textStyle: {
            fontSize: 14
          }
        },
        legend: {
          data: ['正常', '警告', '严重'],
          itemWidth: 16, // 设置宽度
          itemHeight: 10, // 设置高度
          itemGap: 20, // 设置间距
          bottom: '30%',
          left: '12%',
          orient: 'horizontal',
          textStyle: {
            // 图例文字的样式
            color: '#73d6db',
            fontSize: 16
          }
        },
        toolbox: {
          show: true,
          orient: 'vertical',
          left: 'right',
          top: 'center'
        },

        yAxis: [{ gridIndex: 0, name: '年级', show: false }],
        series: [
          {
            name: '',
            type: 'pie',
            hoverAnimation: false,
            radius: '82%',
            center: ['50%', '31%'],
            data: [
              { value: 30, name: '正常' },
              { value: 45, name: '警告' },
              { value: 25, name: '严重' }
            ],
            label: {
              normal: {
                position: 'inner',
                formatter: '{c}%',
                offset: [, 100],
                textStyle: {
                  color: '#ffffff',
                  fontSize: 14
                }
              }
            }
          }
        ]
      }
      myChart555.setOption(option555)
    },
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
      if (column.prop === 'operationCount') {
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
        const tHeader = ['场景名', '客户端', '场景操作次数']
        const filterVal = ['sceneNameDesc', 'hostName', 'operationCount']
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
        groupFlag: 2,
        sceneNameDesc: this.form.sceneNameDesc,
        ...valuee
      }
      try {
        const { data } = await getCountList(value)
        const { hostNameList, tableList } = data
        this.hostNameList = hostNameList
        if (tableList === null) {
          this.userList = ''
          this.total = '0'
        } else {
          this.userList = tableList
          this.userList.forEach((item, index) => {
            item.cellForm = {
              class: `min${index}`,
              CrosXTimes: [
                '2019-01-01',
                '2019-01-02',
                '2019-01-03',
                '2019-01-04',
                '2019-01-05',
                '2019-01-06',
                '2019-01-07',
                '2019-01-08',
                '2019-01-09',
                '2019-01-10',
                '2019-01-11',
                '2019-01-12',
                '2019-01-13',
                '2019-01-14',
                '2019-01-15',
                '2019-01-16',
                '2019-01-17',
                '2019-01-18',
                '2019-01-19',
                '2019-01-20',
                '2019-01-21',
                '2019-01-22',
                '2019-01-23',
                '2019-01-24',
                '2019-01-25',
                '2019-01-26',
                '2019-01-27',
                '2019-01-28',
                '2019-01-29',
                '2019-01-30'
              ],
              series: [
                {
                  name: '总计查看',
                  type: 'line',
                  symbol: 'none', // 取消折点圆圈
                  data: [
                    40,
                    20,
                    30,
                    50,
                    10,
                    40,
                    20,
                    30,
                    50,
                    10,
                    40,
                    20,
                    30,
                    50,
                    10,
                    40,
                    20,
                    30,
                    50,
                    10,
                    40,
                    20,
                    30,
                    50,
                    10,
                    40,
                    20,
                    30,
                    50,
                    10
                  ],

                  areaStyle: {
                    normal: {
                      // 颜色渐变函数 前四个参数分别表示四个位置依次为左、下、右、上
                      color: new this.$echarts.graphic.LinearGradient(
                        0,
                        0,
                        0,
                        1,
                        [
                          {
                            offset: 0,
                            color: 'rgba(248,225,131,0.39)'
                          },
                          {
                            offset: 0.34,
                            color: 'rgba(227,212,150,0.25)'
                          },
                          {
                            offset: 1,
                            color: 'rgba(201,193,163,0.00)'
                          }
                        ]
                      )
                    }
                  },
                  itemStyle: {
                    normal: {
                      color: '#ddaf84', // 改变折线点的颜色
                      lineStyle: {
                        color: '#ddaf84' // 改变折线颜色
                      }
                    }
                  }
                }
              ]
            }
          })
        }
        this.sceneNameDesc = tableList.sceneNameDesc
        this.sceneList = data.sceneList
        this.total = data.tableTotalList[0]

        // 传向图表标题的数据
        this.userName = this.form.userId
        this.htName = this.form.hostName
        this.scenName = this.form.sceneName
        this.actTime = moment(this.form.actionBeginTime).format(
          'YYYY-MM-DD HH:mm:ss'
        )
        this.endTime = moment(this.form.actionEndTime).format(
          'YYYY-MM-DD HH:mm:ss'
        )

        this.endTime = this.times
        this.actTime = this.Dtimes
        // X轴的场景名称
        this.categories = tableList.map(item => {
          return item.hostName
        })
        this.MaxTime = tableList.map(item => {
          return item.operationCount
        })

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
        groupFlag: 2,
        sceneNameDesc: this.form.sceneNameDesc,
        ...valuee
      }
      try {
        const { data } = await getCountList(value)
        const { hostNameList, tableList } = data
        this.hostNameList = hostNameList
        if (tableList === null) {
          this.userList = ''
          this.total = '0'
        } else {
          this.userList = tableList
          this.userList.forEach((item, index) => {
            item.cellForm = {
              class: `min${index}`,
              CrosXTimes: [
                '2019-01-01',
                '2019-01-02',
                '2019-01-03',
                '2019-01-04',
                '2019-01-05',
                '2019-01-06',
                '2019-01-07',
                '2019-01-08',
                '2019-01-09',
                '2019-01-10',
                '2019-01-11',
                '2019-01-12',
                '2019-01-13',
                '2019-01-14',
                '2019-01-15',
                '2019-01-16',
                '2019-01-17',
                '2019-01-18',
                '2019-01-19',
                '2019-01-20',
                '2019-01-21',
                '2019-01-22',
                '2019-01-23',
                '2019-01-24',
                '2019-01-25',
                '2019-01-26',
                '2019-01-27',
                '2019-01-28',
                '2019-01-29',
                '2019-01-30'
              ],
              series: [
                {
                  name: '总计查看',
                  type: 'line',
                  symbol: 'none', // 取消折点圆圈
                  data: [
                    40,
                    20,
                    30,
                    50,
                    10,
                    40,
                    20,
                    30,
                    50,
                    10,
                    40,
                    20,
                    30,
                    50,
                    10,
                    40,
                    20,
                    30,
                    50,
                    10,
                    40,
                    20,
                    30,
                    50,
                    10,
                    40,
                    20,
                    30,
                    50,
                    10
                  ],

                  areaStyle: {
                    normal: {
                      // 颜色渐变函数 前四个参数分别表示四个位置依次为左、下、右、上
                      color: new this.$echarts.graphic.LinearGradient(
                        0,
                        0,
                        0,
                        1,
                        [
                          {
                            offset: 0,
                            color: 'rgba(248,225,131,0.39)'
                          },
                          {
                            offset: 0.34,
                            color: 'rgba(227,212,150,0.25)'
                          },
                          {
                            offset: 1,
                            color: 'rgba(201,193,163,0.00)'
                          }
                        ]
                      )
                    }
                  },
                  itemStyle: {
                    normal: {
                      color: '#ddaf84', // 改变折线点的颜色
                      lineStyle: {
                        color: '#ddaf84' // 改变折线颜色
                      }
                    }
                  }
                }
              ]
            }
          })
        }
        this.sceneNameDesc = tableList.sceneNameDesc
        this.sceneList = data.sceneList
        this.total = data.tableTotalList[0]

        // 传向图表标题的数据
        this.userName = this.form.userId
        this.htName = this.form.hostName
        this.scenName = this.form.sceneName
        this.actTime = moment(this.form.actionBeginTime).format(
          'YYYY-MM-DD HH:mm:ss'
        )
        this.endTime = moment(this.form.actionEndTime).format(
          'YYYY-MM-DD HH:mm:ss'
        )
        // X轴的场景名称
        this.categories = tableList.map(item => {
          return item.hostName
        })
        this.MaxTime = tableList.map(item => {
          return item.operationCount
        })

        // 右侧柱状图渲染展示
      } catch (e) {
        console.log(e)
      }
    },
    // 分页功能
    // 更改每页个数
    handleSizeChange (val) {
      this.isShowCellTable = false
      this.$nextTick(() => {
        this.pageSize = val
        this.pagesNum = 1
        this.currentPage = 1
        this.isShowCellTable = true
      })
    },
    // 切换页
    handleCurrentChange (val) {
      this.isShowCellTable = false
      this.$nextTick(() => {
        this.pagesNum = val
        this.isShowCellTable = true
      })
    },
    // 立即查询
    async seachBtn () {
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
    },
    // 场景名称模糊查询
    // 第一个参数， 用户输入的值  第二个值回调函数
    querySearchScen (queryString, callback) {
      if (this.form.sceneName === '') {
        this.flag = false
      } else {
        this.flag = false
      }
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
    handleSelectHostName (item) {}
  },
  mounted () {
    var that = this
    document.onkeydown = function (e) {
      if (window.event.keyCode === 13) {
        that.seachBtn()
      }
    }
    this.$nextTick(() => {
      this.pageImg()
    })
  }
}
</script>

<style lang='scss' scoped>
@import "../../../../../css/base/base.css";
@import "../../../../../css/clientSideMonitor/sceneMonitor/sceneFrequencyMonitor/specificContextClient.css";
</style>
