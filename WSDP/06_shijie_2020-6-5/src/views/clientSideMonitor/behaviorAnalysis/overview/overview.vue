<template>
  <div class="dashboardData">
    <el-row>
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <dashboard-one id="mas2"></dashboard-one>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content bg-purple-light">
          <dashboard-two></dashboard-two>
        </div>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div class="grid-content bg-purple">
          <dashboard-three></dashboard-three>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="grid-content bg-purple-light">
          <dashboard-four></dashboard-four>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import dashboardOne from '../../../../components/clientSideMonitor/behaviorAnalysis/overview/dashboard1.vue'
import dashboardTwo from '../../../../components/clientSideMonitor/behaviorAnalysis/overview/dashboard2.vue'
import dashboardThree from '../../../../components/clientSideMonitor/behaviorAnalysis/overview/dashboard3.vue'
import dashboardFour from '../../../../components/clientSideMonitor/behaviorAnalysis/overview/dashboard4.vue'

export default {
  components: {
    'dashboard-one': dashboardOne,
    'dashboard-two': dashboardTwo,
    'dashboard-three': dashboardThree,
    'dashboard-four': dashboardFour
  }
}
</script>
<style lang='scss' scoped>
</style>
