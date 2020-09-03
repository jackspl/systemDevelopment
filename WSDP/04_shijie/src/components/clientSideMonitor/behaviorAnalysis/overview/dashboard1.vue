<template>
  <div class="dashboard1">
    <div id="main1" style="width: 100%; height: 415px; margin: 0 auto; background-color: #164276;">
      <div class="msking" id="msked">
        <el-input placeholder="请输入数据库用户" :disabled="true"></el-input>
        <el-input placeholder="请输入数据库密码" :disabled="true"></el-input>
        <el-button type="primary" @click="intoDB">跳转</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { queryTable } from '@/api/table.js'

export default {
  data () {
    return {
      // 折线图数据
      zheXianFlag: 3, // flag 为3
      nowData: '' // 现在的日期
    }
  },
  created () {
    this.Data()
  },
  methods: {
    async getInfoList (calback) {
      const msked = document.getElementById('msked')
      msked.style.display = 'none'
      const value = {
        zheXianFlag: this.zheXianFlag,
        zheXianDate: this.nowData
      }
      try {
        const { data } = await queryTable(value)
        this.result = data.resultInfo
        const { tcOnlineInfoList } = data
        if (this.result !== 'error') {
          const msked = document.getElementById('msked')
          msked.style.display = 'none'
        } else {
          const msked = document.getElementById('msked')
          msked.style.display = 'block'
        }
        const AllXTimes = tcOnlineInfoList.map(item => {
          return item.zheXianX
        })
        const AllYNums = tcOnlineInfoList.map(item => {
          return item.zheXianY
        })

        calback(AllXTimes, AllYNums)
      } catch (e) {
        console.log(e)
      }
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
    },
    // 跳转到连接数据库
    intoDB () {
      this.$router.push('/connectDB')
    }
  },
  mounted () {
    this.getInfoList((AllXTimes, AllYNums) => {
      let myChart1 = this.$echarts.init(document.getElementById('main1'))
      const option1 = {
        title: {
          text: '账号并发量',
          left: '50%',
          top: '1%',
          textAlign: 'center',
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
          data: ['并发数'],
          top: '5%',
          left: '2%',
          textStyle: {
            color: 'white',
            fontSize: 14
          },
          itemWidth: 12,
          itemHeight: 14
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },

        xAxis: {
          type: 'category',
          data: AllXTimes,
          // X轴的网格线
          axisLine: {
            show: true,
            lineStyle: {
              color: '#6a86a6'
            }
          },
          // Y轴的网格线
          splitLine: {
            show: true,
            lineStyle: {
              color: '#6a86a6'
            }
          },
          // X轴提示文字颜色
          axisLabel: {
            textStyle: {
              color: 'white',
              fontSize: 12
            }
          },
          nameTextStyle: {
            fontSize: 12
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
        series: [
          {
            name: '并发数',
            type: 'line',
            data: AllYNums,
            ymbol: 'circle',
            symbolSize: 4,
            lineStyle: {
              color: 'white',
              width: 2
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: 'white',
              color: 'white'
            }
          }
        ]
      }
      myChart1.setOption(option1)
      window.onresize = function () {
        myChart1.resize()
      }
    })
  }
}
</script>

<style lang='scss' scoped>
.msking {
  position: absolute;
  top: 23%;
  width: 25%;
  height: 50%;
  left: 13%;
}
.skep {
  margin: 0 auto;
  margin-left: 22%;
  top: 20px;
  margin-top: 30px;
}
.input {
  width: 150px;
  height: 20px;
  border: 1px solid black;
  padding-left: 10px;
  margin-left: 20px;
  background-color: #ccc;
}
</style>
