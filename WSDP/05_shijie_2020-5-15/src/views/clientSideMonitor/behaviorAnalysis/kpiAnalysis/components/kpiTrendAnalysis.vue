<template>
  <!-- 分页前端进行分页 -->
  <div class="kpiTrendAnalysis">
    <!-- 集团所有内容 -->
    <div class="groupSum">
      <!-- 集团搜索样式 -->
      <div class="groupKPI">
        <el-form :inline="true" :model="form" ref="form">
          <!-- 查询按钮 -->
          <div class="seachButton">
            <el-button type="primary" @click="groupSeachButton">查询</el-button>
          </div>
          <!-- 结束时间 -->
          <div class="endTime">
            <el-form-item label="结束日期" prop="actionEndTime2">
              <el-date-picker
                v-model="form.actionEndTime2"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
          <!-- 开始时间 -->
          <div class="startTime">
            <el-form-item label="开始日期" prop="actionBeginTime2">
              <el-date-picker
                v-model="form.actionBeginTime2"
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
      <!-- 集团折线图样式 -->
      <div class="line" id="ttt">
        <div
          id="main4"
          style="width: 100%; height: 470px; margin: 0 auto;"
        ></div>
      </div>
    </div>

    <!-- 公司所有内容 -->
    <div class="compSum">
      <!-- 公司搜索样式 -->
      <div class="compKPI">
        <el-form :inline="true" :model="form" ref="form">
          <!-- 三个单选按钮 -->
          <div class="threeRadio">
            <el-radio-group v-model="radio1">
              <el-radio :label="1">公司</el-radio>
              <el-radio :label="2">部门</el-radio>
              <el-radio :label="3" class="personal">个人</el-radio>
            </el-radio-group>
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
          <!-- 三个checkbox选择按钮 -->
          <div class="threeCheck">
            <el-checkbox v-model="checked1" class="check1">净值</el-checkbox>
            <el-checkbox v-model="checked2">加分</el-checkbox>
            <el-checkbox v-model="checked3" class="check3">减分</el-checkbox>
          </div>
          <!-- 两个单选按钮 -->
          <div class="twoRadio">
            <el-radio-group v-model="radio2">
              <el-radio :label="4">总KPI</el-radio>
              <el-radio :label="5" class="radioTwo">平均KPI</el-radio>
            </el-radio-group>
          </div>
          <!-- 查询按钮 -->
          <div class="seachButton">
            <el-button type="primary" @click="seachBtn">查询</el-button>
          </div>
          <!-- 结束时间 -->
          <div class="endTime">
            <el-form-item label="结束日期" prop="actionEndTime2">
              <el-date-picker
                v-model="form.actionEndTime2"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
          <!-- 开始时间 -->
          <div class="startTime">
            <el-form-item label="开始日期" prop="actionBeginTime2">
              <el-date-picker
                v-model="form.actionBeginTime2"
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
      <!-- 公司折线图样式 -->
      <div class="line" id="ttt">
        <div
          id="main11"
          style="width: 100%; height: 470px; margin: 0 auto;"
        ></div>
      </div>
    </div>

    <!-- 部门所有内容 -->
    <div class="depSum">
      <!-- 部门KPI搜索样式 -->
      <div class="depKPI">
        <el-form :inline="true" :model="form" ref="form">
          <!-- 三个单选按钮 -->
          <div class="threeRadio">
            <el-radio-group v-model="radio1">
              <el-radio :label="1">公司</el-radio>
              <el-radio :label="2">部门</el-radio>
              <el-radio :label="3" class="personal">个人</el-radio>
            </el-radio-group>
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
          <!-- 三个checkbox选择按钮 -->
          <div class="threeCheck">
            <el-checkbox v-model="checked1" class="check1">净值</el-checkbox>
            <el-checkbox v-model="checked2">加分</el-checkbox>
            <el-checkbox v-model="checked3" class="check3">减分</el-checkbox>
          </div>
          <!-- 两个单选按钮 -->
          <div class="twoRadio">
            <el-radio-group v-model="radio2">
              <el-radio :label="4">总KPI</el-radio>
              <el-radio :label="5" class="radioTwo">平均KPI</el-radio>
            </el-radio-group>
          </div>
          <!-- 查询按钮 -->
          <div class="seachButton">
            <el-button type="primary" @click="seachBtn">查询</el-button>
          </div>
          <!-- 结束时间 -->
          <div class="endTime">
            <el-form-item label="结束日期" prop="actionEndTime2">
              <el-date-picker
                v-model="form.actionEndTime2"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
          <!-- 开始时间 -->
          <div class="startTime">
            <el-form-item label="开始日期" prop="actionBeginTime2">
              <el-date-picker
                v-model="form.actionBeginTime2"
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
      <!-- 部门KPI折线图样式 -->
      <div class="line" id="ttt">
        <div
          id="main22"
          style="width: 100%; height: 470px; margin: 0 auto;"
        ></div>
      </div>
    </div>

    <!-- 员工所有内容 -->
    <div class="userSum">
      <!-- 员工KPI搜索样式 -->
      <div class="userKPI">
        <el-form :inline="true" :model="form" ref="form">
          <!-- 三个单选按钮 -->
          <div class="threeRadio">
            <el-radio-group v-model="radio1">
              <el-radio :label="1">公司</el-radio>
              <el-radio :label="2">部门</el-radio>
              <el-radio :label="3" class="personal">个人</el-radio>
            </el-radio-group>
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
          <!-- 三个checkbox选择按钮 -->
          <div class="threeCheck">
            <el-checkbox v-model="checked1" class="check1">净值</el-checkbox>
            <el-checkbox v-model="checked2">加分</el-checkbox>
            <el-checkbox v-model="checked3" class="check3">减分</el-checkbox>
          </div>
          <!-- 两个单选按钮 -->
          <div class="twoRadio">
            <el-radio-group v-model="radio2">
              <el-radio :label="4">总KPI</el-radio>
              <el-radio :label="5" class="radioTwo">平均KPI</el-radio>
            </el-radio-group>
          </div>
          <!-- 查询按钮 -->
          <div class="seachButton">
            <el-button type="primary" @click="seachBtn">查询</el-button>
          </div>
          <!-- 结束时间 -->
          <div class="endTime">
            <el-form-item label="结束日期" prop="actionEndTime2">
              <el-date-picker
                v-model="form.actionEndTime2"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
          <!-- 开始时间 -->
          <div class="startTime">
            <el-form-item label="开始日期" prop="actionBeginTime2">
              <el-date-picker
                v-model="form.actionBeginTime2"
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
      <!-- 员工KPI折线图样式 -->
      <div class="line" id="ttt">
        <div
          id="main33"
          style="width: 100%; height: 470px; margin: 0 auto;"
        ></div>
      </div>
    </div>
  </div>
</template>

<script>
import { getViewZheXian } from '@/api/table.js'
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
      // 折线图数据
      zheXianFlag: 3,
      zheXianDate: '',
      oldDay: '',
      newDay: '',
      // 表单里的数据
      form: {
        // 下拉菜单选择
        region1: '',
        region2: '',
        region3: '',
        region4: '',
        region5: '',
        actionBeginTime1: '', // 开始日期
        actionEndTime1: '', // 结束日期
        actionBeginTime2: '', // 开始日期
        actionEndTime2: '', // 结束日期
        actionBeginTime3: '', // 开始日期
        actionEndTime3: '' // 结束日期
      }
    }
  },
  created () {
    // 默认一个月的日期填充
    this.getSumTimes()
    this.Data()
  },
  methods: {
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
      this.form.actionEndTime1 = this.times
      this.form.actionBeginTime1 = this.Dtimes
      this.form.actionEndTime2 = this.times
      this.form.actionBeginTime2 = this.Dtimes
      this.form.actionEndTime3 = this.times
      this.form.actionBeginTime3 = this.Dtimes
      // 表头下面的默认时间
      this.oldDay = this.Dtimes
      this.newDay = this.times
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

    // 集团查询按钮
    groupSeachButton () {
      // 首先验证，在执行回车事件
      const ttime1 = this.form.actionBeginTime
      const ttime2 = this.form.actionEndTime
      const timeStr1 = new Date(ttime1).getTime()
      const timeStr2 = new Date(ttime2).getTime()
      if (timeStr1 > timeStr2) {
        this.$message.error('请合理选择日期')
        // return
      }
      // this.getTableList(this.form)
    },
    // 折线图函数数据
    async getInfoList (calback) {
      const value = {
        zheXianFlag: this.zheXianFlag,
        zheXianDate: this.zheXianDate
      }
      try {
        const { data } = await getViewZheXian(value)
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
    // 集团kpl趋势折线图
    initItem (
      AllXTimes,
      AllYNums,
      CrosXTimes,
      CrosYNums,
      SecrXTimes,
      SecrYNums
    ) {
      // 一天的图表
      this.initWidth = document.getElementById('ttt').offsetWidth
      let myChart4 = this.$echarts.init(document.getElementById('main4'))
      const option4 = {
        title: {
          text: '集团KPI趋势',
          left: '50%',
          top: '1%',
          textAlign: 'center',
          subtextStyle: {
            color: 'white'
          },
          x: 'center',
          textStyle: {
            fontWeight: 400,
            fontSize: 18,
            color: 'white'
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend: {
          data: ['净值', '加分', '减分'],
          icon: 'rect',
          itemWidth: 16, // 设置宽度
          itemHeight: 10, // 设置高度
          itemGap: 20, // 设置间距
          top: '9%',
          left: '45%',
          textStyle: {
            color: 'white',
            fontSize: 12
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          x: '92%',
          y: '20',
          feature: {
            magicType: {
              show: true,
              type: ['line', 'bar'],
              iconStyle: {
                borderColor: 'white'
              }
            },
            saveAsImage: {
              show: true,
              iconStyle: {
                borderColor: 'white'
              },
              backgroundColor: '#164276'
            }
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          // 设置网格线
          splitLine: {
            show: true,
            lineStyle: {
              color: ['#315070'],
              width: 1,
              type: 'solid'
            }
          },
          data: CrosXTimes,
          axisLine: {
            show: true,
            lineStyle: {
              color: '#6a86a6'
            }
          },
          // Y轴的网格线
          // splitLine: {
          //   show: true,
          //   lineStyle: {
          //     color: '#6a86a6'
          //   }
          // },
          axisLabel: {
            interval: 0,
            rotate: 50,
            textStyle: {
              fontFamily: '宋体',
              color: 'white',
              fontSize: 16
            }
          }
        },
        yAxis: {
          type: 'value',
          left: '5',
          axisLine: {
            show: true,
            lineStyle: {
              color: '#6a86a6',
              left: 10
            }
          },
          // 设置网格线
          splitLine: {
            show: true,
            lineStyle: {
              color: ['#315070'],
              width: 1,
              type: 'solid'
            }
          },
          axisLabel: {
            textStyle: {
              color: '#4798a5' // 这里用参数代替了
            }
          },
          top: 50
        },
        series: [
          {
            name: '净值',
            type: 'line',
            symbol: 'none', // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: CrosYNums,
            lineStyle: {
              color: '#00b7ee',
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: '#00b7ee',
              color: '#00b7ee'
            }
          },
          {
            name: '加分',
            type: 'line',
            symbol: 'none', // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: SecrYNums,

            lineStyle: {
              color: '#8fc32b',
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: '#8fc32b',
              color: '#8fc32b'
            }
          },
          {
            name: '减分',
            type: 'line',
            symbol: 'none', // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllYNums,
            lineStyle: {
              color: '#f39800',
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: '#f39800',
              color: '#f39800'
            }
          }
        ]
      }
      myChart4.setOption(option4)
      let resize4 = {
        width: this.initWidth,
        height: 445
      }
      myChart4.resize(resize4)
    },
    // 部门Kkpl趋势折线图
    initItem11 (
      AllXTimes,
      AllYNums,
      CrosXTimes,
      CrosYNums,
      SecrXTimes,
      SecrYNums
    ) {
      // 一天的图表
      this.initWidth = document.getElementById('ttt').offsetWidth
      let myChart11 = this.$echarts.init(document.getElementById('main11'))
      const option11 = {
        title: {
          text: '公司KPI趋势',
          left: '50%',
          top: '1%',
          textAlign: 'center',
          subtextStyle: {
            color: 'white'
          },
          x: 'center',
          textStyle: {
            fontWeight: 400,
            fontSize: 18,
            color: 'white'
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend: {
          data: ['净值', '加分', '减分'],
          icon: 'rect',
          itemWidth: 16, // 设置宽度
          itemHeight: 10, // 设置高度
          itemGap: 20, // 设置间距
          top: '9%',
          left: '45%',
          textStyle: {
            color: 'white',
            fontSize: 12
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          x: '92%',
          y: '20',
          feature: {
            magicType: {
              show: true,
              type: ['line', 'bar'],
              iconStyle: {
                borderColor: 'white'
              }
            },
            saveAsImage: {
              show: true,
              iconStyle: {
                borderColor: 'white'
              },
              backgroundColor: '#164276'
            }
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          // 设置网格线
          splitLine: {
            show: true,
            lineStyle: {
              color: ['#315070'],
              width: 1,
              type: 'solid'
            }
          },
          data: CrosXTimes,
          axisLine: {
            show: true,
            lineStyle: {
              color: '#6a86a6'
            }
          },
          // Y轴的网格线
          // splitLine: {
          //   show: true,
          //   lineStyle: {
          //     color: '#6a86a6'
          //   }
          // },
          axisLabel: {
            interval: 0,
            rotate: 50,
            textStyle: {
              fontFamily: '宋体',
              color: 'white',
              fontSize: 16
            }
          }
        },
        yAxis: {
          type: 'value',
          left: '5',
          axisLine: {
            show: true,
            lineStyle: {
              color: '#6a86a6',
              left: 10
            }
          },
          // 设置网格线
          splitLine: {
            show: true,
            lineStyle: {
              color: ['#315070'],
              width: 1,
              type: 'solid'
            }
          },
          axisLabel: {
            textStyle: {
              color: '#4798a5' // 这里用参数代替了
            }
          },
          top: 50
        },
        series: [
          {
            name: '净值',
            type: 'line',
            symbol: 'none', // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: CrosYNums,
            lineStyle: {
              color: '#00b7ee',
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: '#00b7ee',
              color: '#00b7ee'
            }
          },
          {
            name: '加分',
            type: 'line',
            symbol: 'none', // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: SecrYNums,

            lineStyle: {
              color: '#8fc32b',
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: '#8fc32b',
              color: '#8fc32b'
            }
          },
          {
            name: '减分',
            type: 'line',
            symbol: 'none', // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllYNums,
            lineStyle: {
              color: '#f39800',
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: '#f39800',
              color: '#f39800'
            }
          }
        ]
      }
      myChart11.setOption(option11)
      let resize4 = {
        width: this.initWidth,
        height: 445
      }
      myChart11.resize(resize4)
    },
    // 部门Kkpl趋势折线图
    initItem22 (
      AllXTimes,
      AllYNums,
      CrosXTimes,
      CrosYNums,
      SecrXTimes,
      SecrYNums
    ) {
      // 一天的图表
      this.initWidth = document.getElementById('ttt').offsetWidth
      let myChart22 = this.$echarts.init(document.getElementById('main22'))
      const option22 = {
        title: {
          text: '部门KPI趋势',
          left: '50%',
          top: '1%',
          textAlign: 'center',
          subtextStyle: {
            color: 'white'
          },
          x: 'center',
          textStyle: {
            fontWeight: 400,
            fontSize: 18,
            color: 'white'
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend: {
          data: ['净值', '加分', '减分'],
          icon: 'rect',
          itemWidth: 16, // 设置宽度
          itemHeight: 10, // 设置高度
          itemGap: 20, // 设置间距
          top: '9%',
          left: '45%',
          textStyle: {
            color: 'white',
            fontSize: 12
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          x: '92%',
          y: '20',
          feature: {
            magicType: {
              show: true,
              type: ['line', 'bar'],
              iconStyle: {
                borderColor: 'white'
              }
            },
            saveAsImage: {
              show: true,
              iconStyle: {
                borderColor: 'white'
              },
              backgroundColor: '#164276'
            }
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          // 设置网格线
          splitLine: {
            show: true,
            lineStyle: {
              color: ['#315070'],
              width: 1,
              type: 'solid'
            }
          },
          data: CrosXTimes,
          axisLine: {
            show: true,
            lineStyle: {
              color: '#6a86a6'
            }
          },
          // Y轴的网格线
          // splitLine: {
          //   show: true,
          //   lineStyle: {
          //     color: '#6a86a6'
          //   }
          // },
          axisLabel: {
            interval: 0,
            rotate: 50,
            textStyle: {
              fontFamily: '宋体',
              color: 'white',
              fontSize: 16
            }
          }
        },
        yAxis: {
          type: 'value',
          left: '5',
          axisLine: {
            show: true,
            lineStyle: {
              color: '#6a86a6',
              left: 10
            }
          },
          // 设置网格线
          splitLine: {
            show: true,
            lineStyle: {
              color: ['#315070'],
              width: 1,
              type: 'solid'
            }
          },
          axisLabel: {
            textStyle: {
              color: '#4798a5' // 这里用参数代替了
            }
          },
          top: 50
        },
        series: [
          {
            name: '净值',
            type: 'line',
            symbol: 'none', // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: CrosYNums,
            lineStyle: {
              color: '#00b7ee',
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: '#00b7ee',
              color: '#00b7ee'
            }
          },
          {
            name: '加分',
            type: 'line',
            symbol: 'none', // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: SecrYNums,

            lineStyle: {
              color: '#8fc32b',
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: '#8fc32b',
              color: '#8fc32b'
            }
          },
          {
            name: '减分',
            type: 'line',
            symbol: 'none', // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllYNums,
            lineStyle: {
              color: '#f39800',
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: '#f39800',
              color: '#f39800'
            }
          }
        ]
      }
      myChart22.setOption(option22)
      let resize4 = {
        width: this.initWidth,
        height: 445
      }
      myChart22.resize(resize4)
    },
    // 员工Kkpl趋势折线图
    initItem33 (
      AllXTimes,
      AllYNums,
      CrosXTimes,
      CrosYNums,
      SecrXTimes,
      SecrYNums
    ) {
      // 一天的图表
      this.initWidth = document.getElementById('ttt').offsetWidth
      let myChart33 = this.$echarts.init(document.getElementById('main33'))
      const option33 = {
        title: {
          text: '员工KPI趋势',
          left: '50%',
          top: '1%',
          textAlign: 'center',
          subtextStyle: {
            color: 'white'
          },
          x: 'center',
          textStyle: {
            fontWeight: 400,
            fontSize: 18,
            color: 'white'
          }
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend: {
          data: ['净值', '加分', '减分'],
          icon: 'rect',
          itemWidth: 16, // 设置宽度
          itemHeight: 10, // 设置高度
          itemGap: 20, // 设置间距
          top: '9%',
          left: '45%',
          textStyle: {
            color: 'white',
            fontSize: 12
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        toolbox: {
          x: '92%',
          y: '20',
          feature: {
            magicType: {
              show: true,
              type: ['line', 'bar'],
              iconStyle: {
                borderColor: 'white'
              }
            },
            saveAsImage: {
              show: true,
              iconStyle: {
                borderColor: 'white'
              },
              backgroundColor: '#164276'
            }
          }
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
          // 设置网格线
          splitLine: {
            show: true,
            lineStyle: {
              color: ['#315070'],
              width: 1,
              type: 'solid'
            }
          },
          data: CrosXTimes,
          axisLine: {
            show: true,
            lineStyle: {
              color: '#6a86a6'
            }
          },
          // Y轴的网格线
          // splitLine: {
          //   show: true,
          //   lineStyle: {
          //     color: '#6a86a6'
          //   }
          // },
          axisLabel: {
            interval: 0,
            rotate: 50,
            textStyle: {
              fontFamily: '宋体',
              color: 'white',
              fontSize: 16
            }
          }
        },
        yAxis: {
          type: 'value',
          left: '5',
          axisLine: {
            show: true,
            lineStyle: {
              color: '#6a86a6',
              left: 10
            }
          },
          // 设置网格线
          splitLine: {
            show: true,
            lineStyle: {
              color: ['#315070'],
              width: 1,
              type: 'solid'
            }
          },
          axisLabel: {
            textStyle: {
              color: '#4798a5' // 这里用参数代替了
            }
          },
          top: 50
        },
        series: [
          {
            name: '净值',
            type: 'line',
            symbol: 'none', // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: CrosYNums,
            lineStyle: {
              color: '#00b7ee',
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: '#00b7ee',
              color: '#00b7ee'
            }
          },
          {
            name: '加分',
            type: 'line',
            symbol: 'none', // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: SecrYNums,

            lineStyle: {
              color: '#8fc32b',
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: '#8fc32b',
              color: '#8fc32b'
            }
          },
          {
            name: '减分',
            type: 'line',
            symbol: 'none', // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllYNums,
            lineStyle: {
              color: '#f39800',
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: '#f39800',
              color: '#f39800'
            }
          }
        ]
      }
      myChart33.setOption(option33)
      let resize4 = {
        width: this.initWidth,
        height: 445
      }
      myChart33.resize(resize4)
    }
  },
  destroyed () {},
  mounted () {
    // 回调函数渲染折线图
    this.getInfoList(this.initItem)
    this.getInfoList(this.initItem11)
    this.getInfoList(this.initItem22)
    this.getInfoList(this.initItem33)
  }
}
</script>

<style lang="scss" scoped>
@import "../../../../../css/base/base.css";
@import "../../../../../css/clientSideMonitor/behaviorAnalysis/kpiAnalysis/components/kpiTrendAnalysis.css";
</style>
