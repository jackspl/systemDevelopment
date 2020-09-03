<template>
  <div class="dashboard1">
    <div :class="form.class" style="width: 100%; height: 50px; background-color: rgb(47, 85, 128);"></div>
  </div>
</template>

<script>
import { getViewZheXian } from "@/api/table.js";
export default {
  props: ["tableDate"],
  data() {
    return {
      nowData: "", // 现在的日期
      flag: true,
      form: {
        class: "min1",
        CrosXTimes: ["2019-01-03", "2019-01-04", "2019-01-05"],
        series: [
          {
            name: "总计查看",
            type: "line",
            data: [4, 5, 6]
          }
        ]
      }
    };
  },
  created() {
    this.Data();
  },
  methods: {
    // 用户提示时间
    Data() {
      this.nowData = new Date();
      const d = this.nowData;
      let month =
        d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
      let day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
      let times = d.getFullYear() + "-" + month + "-" + day;
      this.nowData = times;
    }
  },
  mounted() {
    let myChart3 = this.$echarts.init(
      document.querySelector(`.${this.form.class}`)
    );
    this.initWidth = document.querySelector(".dashboard1").offsetWidth;
    const option3 = {
      grid: {
        top:"5px",
        left: "5px",
        right: "5px",
        width: "auto", //图例宽度
        height: "100%"
      },
      xAxis: {
        type: "category",
        data: this.form.CrosXTimes,
        axisTick: {
          show: false
        },
        // X轴的网格线
        axisLine: {
          show: false
        },
        // Y轴的网格线
        splitLine: {
          show: false
        },
        // X轴提示文字颜色
        axisLabel: {
          show: false
        }
      },
      yAxis: {
        show: false,
        type: "value",
        axisTick: {
          show: false
        },
        axisLine: {
          show: false
        },
        splitLine: {
          show: false
        },
        axisLabel: {
          show: false
        }
      },
      series: this.form.series
    };
    myChart3.setOption(option3);
    let resize3 = {
      width: this.initWidth,
      height: 50
    };
    myChart3.resize(resize3);
  },
  watch: {
    tableDate: {
      deep: true,
      immediate: true,
      handler: function(curr, old) {
        if (curr) {
          this.form = this.tableDate;
        }
      }
    }
  }
};
</script>

<style>
</style>
