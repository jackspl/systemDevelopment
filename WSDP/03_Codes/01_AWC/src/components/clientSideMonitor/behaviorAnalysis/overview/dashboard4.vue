<template>
  <div class="dashboard1">
    <div id="main4" style="width: 100%; height: 415px; background-color: #164276;"></div>
  </div>
</template>

<script>
import { getDownZheXian } from '@/api/table.js'
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
        const { data } = await getDownZheXian(value)
        const {
          zheXianListAll,
          zheXianListOverload,
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

        // 超量下载数据
        const OverXTimes = zheXianListOverload.map(item => {
          return item.zheXianY
        })

        const OverYNums = zheXianListOverload.map(item => {
          return item.zheXianY
        })

        // 跨部门数据
        const CrosXTimes = zheXianListCrossDept.map(item => {
          return item.zheXianY
        })

        const CrosYNums = zheXianListCrossDept.map(item => {
          return item.zheXianY
        })

        // 机密文件下载数据
        const SecrXTimes = zheXianListSecretFile.map(item => {
          return item.zheXianY
        })

        const SecrYNums = zheXianListSecretFile.map(item => {
          return item.zheXianY
        })
        calback(
          AllXTimes,
          AllYNums,
          OverXTimes,
          OverYNums,
          CrosXTimes,
          CrosYNums,
          SecrXTimes,
          SecrYNums
        )
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
    this.getInfoList(
      (
        AllXTimes,
        AllYNums,
        OverXTimes,
        OverYNums,
        CrosXTimes,
        CrosYNums,
        SecrXTimes,
        SecrYNums
      ) => {
        let myChart4 = this.$echarts.init(document.getElementById('main4'))
        const option4 = {
          title: {
            text: '文档下载',
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
            data: ['超量下载', '跨部门下载', '机密文件下载', '总计下载'],
            top: '7%',
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
            boundaryGap: false,
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
            // name: '日期'
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
              name: '超量下载',
              type: 'line',
              data: OverYNums,
              ymbol: 'circle',
              symbolSize: 1,
              lineStyle: {
                color: '#36ff00',
                width: 2
              },
              itemStyle: {
                borderWidth: 1,
                borderColor: '#36ff00',
                color: '#36ff00'
              }
            },
            {
              name: '跨部门下载',
              type: 'line',
              data: CrosYNums,
              ymbol: 'circle',
              symbolSize: 1,
              lineStyle: {
                color: 'yellow',
                width: 2
              },
              itemStyle: {
                borderWidth: 1,
                borderColor: 'yellow',
                color: 'yellow'
              }
            },
            {
              name: '机密文件下载',
              type: 'line',
              data: SecrYNums,
              ymbol: 'circle',
              symbolSize: 1,
              lineStyle: {
                color: '#ff9600',
                width: 2
              },
              itemStyle: {
                borderWidth: 1,
                borderColor: '#ff9600',
                color: '#ff9600'
              }
            },
            {
              name: '总计下载',
              type: 'line',
              data: AllYNums,
              ymbol: 'circle',
              symbolSize: 1,
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
        myChart4.setOption(option4)
        window.onresize = function () {
          myChart4.resize()
        }
      }
    )
  }
}
</script>

<style>
</style>
