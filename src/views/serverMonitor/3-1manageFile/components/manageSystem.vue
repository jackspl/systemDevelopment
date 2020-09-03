<template>
  <!-- 系统 -->
  <div class="manageSystem">
    <div class="sy-top clearfix">
      <div class="sy-left clearfix">
        <div class="serCheck fl">
          <el-checkbox v-model="serChecked">启用服务器监控</el-checkbox>
        </div>
        <div class="serBtn fl">
          <el-button type="primary">重启服务器监控系统</el-button>
        </div>
        <div class="helpCheck">
          <el-checkbox v-model="helpChecked">启用帮助信息</el-checkbox>
        </div>
        <div class="startTime">系统启动时间：{{startTime}}</div>
        <div class="contTime">持续运行时间：{{contTime}}</div>
        <div class="refreshTime">页面刷新时间{{refreshTime}}秒</div>
      </div>
      <div class="sy-right">
        <div id="main3" style="width: 100%; height: 330px;background-color: #05356f;"></div>
      </div>
    </div>
    <div class="sy-bot">
      <el-row>
        <el-col :span="8">
          <div class="grid-content bg-purple">
            <div class="bot-title">监控服务器状态</div>
            <div class="serverTable">
              <el-table
                :data="companyBonusTableList"
                :header-cell-style="headFirst"
                cell-mouse-enter="false"
                border
              >
                <el-table-column prop="company" label="资源" align="center"></el-table-column>
                <el-table-column label="已用" align="center">
                  <el-table-column align="center">
                    <template slot-scope="{ row }">
                      <el-progress
                        :percentage="row.KPIjiafen"
                        :stroke-width="40"
                        :show-text="false"
                        :color="row.KPIjiafen | filterClass"
                      ></el-progress>
                    </template>
                  </el-table-column>

                  <el-table-column align="center">
                    <template slot-scope="{ row }">
                      <p class="beiJinNum">{{ row.KPIjiafen }}</p>
                    </template>
                  </el-table-column>
                </el-table-column>
              </el-table>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="grid-content bg-purple-light">
            <div class="bot-title">监控系统组件状态</div>
            <div class="serverTable">
              <el-table
                :data="companyBonusTableList"
                :header-cell-style="headFirst"
                cell-mouse-enter="false"
                border
              >
                <el-table-column prop="company" label="资源" align="center"></el-table-column>
                <el-table-column label="已用" align="center">
                  <el-table-column align="center">
                    <template slot-scope="{ row }">
                      <el-progress
                        :percentage="row.KPIjiafen"
                        :stroke-width="40"
                        :show-text="false"
                        :color="row.KPIjiafen | filterClass"
                      ></el-progress>
                    </template>
                  </el-table-column>

                  <el-table-column align="center">
                    <template slot-scope="{ row }">
                      <p class="beiJinNum">{{ row.KPIjiafen }}</p>
                    </template>
                  </el-table-column>
                </el-table-column>
              </el-table>
            </div>
          </div>
        </el-col>
        <el-col :span="8">
          <div class="grid-content bg-purple">
            <div class="bot-title">检测速度</div>
            <div class="testTable">
              <el-table :data="tableData" border style="width: 100%">
                <el-table-column align="center" prop="date" label="检测速度指标(秒)"></el-table-column>
                <el-table-column align="center" prop="name" label="最小"></el-table-column>
                <el-table-column align="center" prop="address" label="平均"></el-table-column>
                <el-table-column align="center" prop="max" label="最大"></el-table-column>
              </el-table>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
import { loginUserNums, comRankSum } from "@/api/table.js";
export default {
  data() {
    return {
      serChecked: true,
      helpChecked: true,
      startTime: "2020-02-01 09:15:26",
      contTime: "159天 29小时 27分 39秒",
      refreshTime: 10,
      // 模拟登录用户数量
      zheXianFlag: 3,
      zheXianDates: "",
      // 公司加分排名
      companyBonusTableList: [],
      // 公司KPI排名表单数据
      comForm: {
        // 公司KPI排名数据
        comCompany: "",
        comStartTime: "", // 公司开始时间
        comEndTime: "" // 公司结束时间
      },
      tableData: [
        {
          date: "主机检测延迟",
          name: "0.00",
          address: "0.00",
          max: "0.00"
        },
        {
          date: "主机检测执行时间",
          name: "0.00",
          address: "9.47",
          max: "11.00"
        },
        {
          date: "服务检测延迟",
          name: "0.00",
          address: "0.00",
          max: "0.00"
        },
        {
          date: "服务检测执行时间",
          name: "0.00",
          address: "16.04",
          max: "59.10"
        }
      ]
    };
  },
  created() {
    // 初始化带上时间
    this.Data();
    // 公司加分排名
    this.getComSerial();
    // this.filters();
  },
  filters: {
    filterClass(val) {
      console.log(val);
      if (val > 140) {
        return "green";
      } else if (val < 140 && val > 130) {
        return "red";
      } else if (val < 130 && val > 110) {
        return "blue";
      }
    }
  },
  methods: {
    headFirst({ row, colunm, rowIndex, columnIndex }) {
      if (rowIndex === 1) {
        //这里为了是将第二列的表头隐藏，就形成了合并表头的效果
        return { display: "none" };
      }
      return "background:#1f4271";
    },
    // 用户提示时间
    Data() {
      this.nowData = new Date();
      const d = this.nowData;
      let month =
        d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
      let day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
      let times = d.getFullYear() + "-" + month + "-" + day;
      this.zheXianDates = times;
    },
    // 登录用户数量柱状图数据
    async getInfoListDay() {
      const value = {
        zheXianFlag: this.zheXianFlag,
        zheXianDate: this.zheXianDates
      };
      try {
        const { data } = await loginUserNums(value);
        const { companyZheXianList } = data;
        // 所有数据
        const allSumName = companyZheXianList.map((item, index) => {
          return item.companyName;
        });
        const allSum22 = companyZheXianList.map((item, index) => {
          return item.children;
        });
        // 上海分公司数据
        const AllXTimes = allSum22[0].map((item, index) => {
          return item.zheXianX;
        });
        const AllYNums = allSum22[0].map((item, index) => {
          return item.zheXianX;
        });
        // 北京分公司数据
        const CrosXTimes = allSum22[1].map(item => {
          return item.zheXianX;
        });
        const CrosYNums = allSum22[1].map(item => {
          return item.zheXianY;
        });
        // 广州分公司数据
        const SecrXTimes = allSum22[2].map(item => {
          return item.zheXianX;
        });
        const SecrYNums = allSum22[2].map(item => {
          return item.zheXianY;
        });
        // 成都分公司数据
        const cdX = allSum22[3].map(item => {
          return item.zheXianX;
        });
        const cdY = allSum22[3].map(item => {
          return item.zheXianY;
        });
        this.initItem(
          allSumName,
          AllXTimes,
          AllYNums,
          CrosXTimes,
          CrosYNums,
          SecrXTimes,
          SecrYNums,
          cdX,
          cdY
        );
      } catch (e) {
        console.log(e);
      }
    },
    // card1登录用户数量柱状图
    initItem(
      allSumName,
      AllXTimes,
      AllYNums,
      CrosXTimes,
      CrosYNums,
      SecrXTimes,
      SecrYNums,
      cdX,
      cdY
    ) {
      let myChart3 = this.$echarts.init(document.getElementById("main3"));
      const option3 = {
        backgroundColor: "#05356f",
        color: ["#3398DB"],
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        title: {
          text: "检测事件",
          x: "center",
          textStyle: {
            color: "white",
            fontSize: 16,
            fontWeight: 100
          }
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true
        },
        dataZoom: [
          {
            show: true,
            height: 12,
            xAxisIndex: [0],
            bottom: "-6%",
            start: 0,
            end: 100,
            handleIcon:
              "path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z",
            handleSize: "110%",
            handleStyle: {
              color: "#d3dee5"
            },
            textStyle: {
              color: "#fff"
            },
            borderColor: "#90979c"
          },
          {
            type: "inside",
            show: true,
            height: 15,
            start: 1,
            end: 35
          }
        ],
        xAxis: [
          {
            type: "category",
            bottom: "0",
            data: CrosXTimes,
            axisTick: {
              alignWithLabel: true
            },
            axisLine: {
              lineStyle: {
                color: "white"
              }
            },
            axisLabel: {
              // interval: 0,
              // rotate: 40,
              textStyle: {
                fontFamily: "Microsoft YaHei"
              }
            }
          }
        ],
        yAxis: [
          {
            type: "value",
            minInterval: 1,
            axisLine: {
              show: false,
              lineStyle: {
                type: "solid",
                color: "white",
                width: 3
              }
            },
            splitLine: {
              show: true,
              lineStyle: {
                color: "rgba(255,255,255,0.3)"
              }
            }
          }
        ],
        series: [
          {
            name: allSumName[0],
            type: "bar",
            barWidth: "60%",
            data: CrosYNums
          }
        ]
        // ---------------------------
      };
      myChart3.setOption(option3);
    },
    // 公司加分排名
    async getComSerial() {
      // 传默认时间到后台
      const value = {
        actionBeginTime: "2020-07-19 13:57:51",
        actionEndTime: "2020-08-18 13:57:51",
        company: "",
        avgKPIFlag: 0
      };
      try {
        const { data } = await comRankSum(value);
        // 解构
        const { kpiRankingList } = data;
        this.companyBonusTableList = kpiRankingList;
        console.log(kpiRankingList);
      } catch (error) {
        console.log(error);
      }
    }
  },
  mounted() {
    // 登录用户数量柱状图回调函数
    this.getInfoListDay();
  }
};
</script>
<style lang="scss" scoped>
// @import "../../../../css/manageFile/manageSystem.css";
.manageSystem {
  /* 清除浮动的公共类 */
  .clearfix:before,
  .clearfix:after {
    content: "";
    display: table;
  }
  .clearfix:after {
    clear: both;
  }
  /* 为了兼容IE6,7 */
  .clearfix {
    *zoom: 1;
  }
  .fl {
    float: left;
  }
  .fr {
    float: right;
  }
  .sy-top {
    width: 100%;
    height: 330px;
    box-sizing: border-box;
    margin-top: 20px;
    ::v-deep .el-checkbox__label {
      font-size: 16px;
      font-weight: 100;
      color: white;
    }
    .sy-left {
      width: 30%;
      float: left;
      box-sizing: border-box;
      .serCheck {
        margin-right: 35px;
      }
      .helpCheck {
        margin-top: 40px;
      }
      .startTime {
        margin-top: 50px;
        color: white;
      }
      .contTime {
        margin-top: 30px;
        color: white;
      }
      .refreshTime {
        margin-top: 60px;
        color: white;
      }
    }
    .sy-right {
      width: 70%;
      height: 330px;
      float: left;
      box-sizing: border-box;
    }
  }
  .sy-bot {
    width: 100%;
    height: 330px;
    box-sizing: border-box;
    margin-top: 50px;
    .grid-content {
      border-radius: 4px;
      min-height: 36px;
      text-align: center;
      color: white;
      .bot-title {
        font-size: 18px;
        margin-bottom: 20px;
      }
      .serverTable {
        width: 330px;
        margin: 0 auto;
        ::v-deep .el-progress-bar__outer {
          border-radius: 0px;
          background-color: #1f4271;
        }
        ::v-deep .el-progress-bar__inner {
          border-radius: 0px;
        }
        .expRed {
          color: red;
        }
        .expGreen {
          color: green;
        }
        .expYellow {
          color: yellow;
        }
        .expBlue {
          color: blue;
        }
      }
      .testTable {
        width: 530px;
        margin: 0 auto;
      }
    }
    ::v-deep .el-table thead,
    .el-table {
      color: white;
    }
    ::v-deep .el-table th {
      background-color: #1f4271;
    }
    ::v-deep .el-table th.is-leaf {
      border-bottom: 1px solid #3e74a3;
      border-right: 1px solid #3e74a3;
    }
    ::v-deep .el-table td.is-center {
      border-bottom: 1px solid #3e74a3;
      border-right: 1px solid #3e74a3;
    }
    ::v-deep .el-table--border th {
      border-bottom: 1px solid #3e74a3;
    }
    ::v-deep .el-table .cell {
      padding: 0;
    }
  }
}
</style>
