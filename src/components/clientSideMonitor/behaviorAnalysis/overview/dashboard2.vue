<template>
  <div class="dashboard1">
    <div id="main2" style="width: 100%; height: 415px; margin: 0 auto; background-color: #164276;"></div>
  </div>
</template>

<script>
import { getPLMLogin } from '@/api/table.js'

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
      const value = {
        zheXianFlag: this.zheXianFlag,
        zheXianDate: this.nowData
      }
      try {
        const { data } = await getPLMLogin(value)
        const { zheXianListAll } = data
        const AllXTimes = zheXianListAll.map(item => {
          return item.zheXianX
        })
        const AllYNums = zheXianListAll.map(item => {
          return item.zheXianY
        })

        const AbnXTimes = zheXianListAll.map(item => {
          return item.zheXianX
        })

        const AbnYNums = zheXianListAll.map(item => {
          return item.zheXianY
        })
        calback(AllXTimes, AllYNums, AbnXTimes, AbnYNums)
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
    }
  },
  mounted () {
    this.getInfoList((AllXTimes, AllYNums, AbnXTimes, AbnYNums) => {
      let myChart2 = this.$echarts.init(document.getElementById('main2'))
      const option2 = {
        title: {
          text: '登录记录',
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
          data: ['异常登录', '总计登录'],
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
            name: '异常登录',
            type: 'line',
            data: AbnYNums,
            symbol: 'triangle',
            symbolSize: 4,
            lineStyle: {
              color: 'red',
              width: 2
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: 'red',
              color: 'red'
            }
          },
          {
            name: '总计登录',
            type: 'line',
            data: AllYNums,
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
      myChart2.setOption(option2)
      window.onresize = function () {
        myChart2.resize()
      }
    })
  }
}
</script>

<style>
</style>
