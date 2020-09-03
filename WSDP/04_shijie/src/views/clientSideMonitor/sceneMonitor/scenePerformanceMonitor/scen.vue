<template>
  <div class="scen">
    <el-tabs type="border-card" @tab-click="handleClick" id="ttt">
      <el-tab-pane label="性能概要监控">
        <scen-sum></scen-sum>
      </el-tab-pane>
      <el-tab-pane label="性能趋势分析">
        <scen-analysis v-if="isShowTabs[0].flag"></scen-analysis>
      </el-tab-pane>
      <el-tab-pane label="基于用户监控">
        <scen-user v-if="isShowTabs[1].flag"></scen-user>
      </el-tab-pane>
      <el-tab-pane label="基于客户端监控">
        <scen-machine v-if="isShowTabs[2].flag"></scen-machine>
      </el-tab-pane>
      <el-tab-pane label="基于特定场景（用户）">
        <spe-sce-user v-if="isShowTabs[3].flag"></spe-sce-user>
      </el-tab-pane>
      <el-tab-pane label="基于特定场景（客户端）">
        <spe-sce-machine v-if="isShowTabs[4].flag"></spe-sce-machine>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>

import scenSum from './components/scenSum'
import scenAnalysis from './components/scenAnalysis'
import scenUser from './components/scenUser'
import scenMachine from './components/scenMachine'
import scenSceUser from './components/scenSceUser'
import scenSceMachine from './components/scenSceMachine'

export default {
  data () {
    return {
      isShowTabs: [
        { flag: false },
        { flag: false },
        { flag: false },
        { flag: false },
        { flag: false }
      ]
    }
  },
  components: {
    'scen-sum': scenSum,
    'scen-analysis': scenAnalysis,
    'scen-user': scenUser,
    'scen-machine': scenMachine,
    'spe-sce-user': scenSceUser,
    'spe-sce-machine': scenSceMachine
  },
  methods: {
    // 点击的时候在执行渲染（点一个渲染一个）
    handleClick (tab, event) {
      this.isShowTabs[tab.index - 1].flag = true
    }
  }
}
</script>

<style lang='scss' scoped>
</style>
