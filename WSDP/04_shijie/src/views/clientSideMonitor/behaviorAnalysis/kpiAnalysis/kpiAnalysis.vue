<template>
  <div class="kpiAnalysis">
    <el-tabs type="border-card" @tab-click="handleClick" v-model="activeName">
      <el-tab-pane label="KPI排名" name="first">
        <kpi-Rank></kpi-Rank>
      </el-tab-pane>
      <el-tab-pane label="KPI趋势分析" name="second">
        <kpi-Trend-Analysis v-if="isShowTabs[0].flag"></kpi-Trend-Analysis>
      </el-tab-pane>
      <el-tab-pane label="KPI详情查询" name="third">
        <kpi-Details-Query  v-if="isShowTabs[1].flag"></kpi-Details-Query>
      </el-tab-pane>
    </el-tabs>
    <!-- <el-tabs type="border-card" @tab-click="handleClick" v-model="activeName">
      <el-tab-pane label="KPI排名" name="first">
        <kpi-Rank></kpi-Rank>
      </el-tab-pane>
      <el-tab-pane label="KPI趋势分析" name="second">
        <kpi-Trend-Analysis ></kpi-Trend-Analysis>
      </el-tab-pane>
      <el-tab-pane label="KPI详情查询" name="third">
        <kpi-Details-Query  ></kpi-Details-Query>
      </el-tab-pane>
    </el-tabs> -->
  </div>
</template>

<script>
import kpiRank from './components/kpiRank'
import kpiTrendAnalysis from './components/kpiTrendAnalysis'
import kpiDetailsQuery from './components/kpiDetailsQuery'

export default {
  data () {
    return {
      activeName: 'first',
      isShowTabs: [
        { flag: false },
        { flag: false }
      ]
    }
  },
  components: {
    'kpi-Rank': kpiRank,
    'kpi-Trend-Analysis': kpiTrendAnalysis,
    'kpi-Details-Query': kpiDetailsQuery
  },
  created () {},
  methods: {
    // 点击的时候在执行渲染（点一个渲染一个）
    handleClick (tab, event) {
      this.isShowTabs[tab.index - 1].flag = true
    }
  },
  mounted () {}

}
</script>

<style lang='scss' scoped>
// .kpiAnalysis .right .el-tabs__item {
//   width: 120px;
//   text-align: center;
// }
</style>
