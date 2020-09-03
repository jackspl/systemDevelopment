<template>
  <!-- 信息安全 -->
  <div class="informationSecurity">
    <el-tabs type="border-card" @tab-click="handleClick" v-model="activeName">
      <el-tab-pane label="总览" name="first">
        <trends-Overview></trends-Overview>
      </el-tab-pane>
      <el-tab-pane label="趋势分析" name="second">
        <trend-Analysis v-if="isShowTabs[0].flag"></trend-Analysis>
      </el-tab-pane>
      <el-tab-pane label="异常登录" name="third">
        <i class="informationSecurityNum">
          <P>66</P>
        </i>
        <abnormal-Login v-if="isShowTabs[1].flag"></abnormal-Login>
      </el-tab-pane>
      <el-tab-pane label="异常查看" name="fourth">
        <i class="informationSecurityNum1">
          <P>55</P>
        </i>
        <abnormal-View v-if="isShowTabs[2].flag"></abnormal-View>
      </el-tab-pane>
      <el-tab-pane label="异常下载" name="fifth">
        <i class="informationSecurityNum2">
          <P>44</P>
        </i>
        <abnormal-Download v-if="isShowTabs[3].flag"></abnormal-Download>
      </el-tab-pane>
      <el-tab-pane label="异常删除" name="sixth">
        <i class="informationSecurityNum3">
          <P>33</P>
        </i>
        <abnormal-Delete v-if="isShowTabs[4].flag"></abnormal-Delete>
      </el-tab-pane>
    </el-tabs>
    <!-- <el-tabs type="border-card" @tab-click="handleClick" v-model="activeName">
      <el-tab-pane label="总览" name="first">
        <trends-Overview></trends-Overview>
      </el-tab-pane>
      <el-tab-pane label="趋势分析" name="second">
        <trend-Analysis></trend-Analysis>
      </el-tab-pane>
      <el-tab-pane label="异常登录" name="third" class="third">
        <i class="informationSecurityNum"><P>66</P></i>
        <abnormal-Login></abnormal-Login>
      </el-tab-pane>
      <el-tab-pane label="异常查看" name="fourth">
         <i class="informationSecurityNum1"><P>55</P></i>
        <abnormal-View></abnormal-View>
      </el-tab-pane>
      <el-tab-pane label="异常下载" name="fifth">
         <i class="informationSecurityNum2"><P>44</P></i>
        <abnormal-Download></abnormal-Download>
      </el-tab-pane>
      <el-tab-pane label="异常删除" name="sixth">
         <i class="informationSecurityNum3"><P>33</P></i>
        <abnormal-Delete></abnormal-Delete>
      </el-tab-pane>
    </el-tabs>-->
  </div>
</template>

<script>
import trendsOverview from "./components/trendsOverview";
import trendAnalysis from "./components/trendAnalysis";
import abnormalLogin from "./components/abnormalLogin";
import abnormalView from "./components/abnormalView";
import abnormalDownload from "./components/abnormalDownload";
import abnormalDelete from "./components/abnormalDelete";

export default {
  data() {
    return {
      activeName: "first",
      isShowTabs: [
        { flag: false },
        { flag: false },
        { flag: false },
        { flag: false },
        { flag: false }
      ]
    };
  },
  components: {
    "trends-Overview": trendsOverview,
    "trend-Analysis": trendAnalysis,
    "abnormal-Login": abnormalLogin,
    "abnormal-View": abnormalView,
    "abnormal-Download": abnormalDownload,
    "abnormal-Delete": abnormalDelete
  },
  created() {},
  methods: {
    // 点击的时候在执行渲染（点一个渲染一个）
    handleClick(tab, event) {
      this.isShowTabs[tab.index - 1].flag = true;
    }
  },
  mounted() {}
};
</script>

<style lang='scss' scoped>
//  .informationSecurity .right .el-tabs__item {
//   width: 120px;
//   text-align: center;
// }
@import "../../../../css/clientSideMonitor/behaviorAnalysis/informationSecurity/informationSecurity.css";
</style>
