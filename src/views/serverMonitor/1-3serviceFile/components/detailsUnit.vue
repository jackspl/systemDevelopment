<template>
  <!-- 详情 -->
  <div class="detailsUnit">
    <!-- 警告栏 -->
    <div class="warne">
      <img class="imgClass" src="../../../../assets/error.png" />
      <p class="unknown">未知：连接失败，连接被拒绝</p>
    </div>
    <!-- 主体栏 -->
    <div class="boxMain">
      <div class="box-left">
        <div class="left-top">
          <p class="lin20">主机：{{hostName}}({{hostId}})</p>
          <p>服务：{{serverName}}</p>
          <span>服务状态：{{serverState}}</span>
          <span>
            <el-button type="primary" @click="seachBtn">状态历史</el-button>
          </span>
          <p>服务稳定性：{{serverStable}}</p>
        </div>
        <div class="left-bot">
          <p class="lin20">持续时间：{{duration}}</p>
          <p>上次检测：{{lastTest}}</p>
          <span>下次检测：{{nextTest}}</span>
          <span>
            <el-button type="primary" @click="seachBtn">立即检测</el-button>
          </span>
        </div>
      </div>
      <div class="box-right">
        <!-- 服务状态所有内容 -->
        <div class="groupSum">
          <!-- 服务状态搜索样式 -->
          <div class="groupKPI clearfix">
            <el-form :inline="true" :model="groupForm" ref="form">
              <!-- 查询按钮 -->
              <div class="seachButton fr">
                <el-button type="primary" @click="groupSeachButton">查询</el-button>
              </div>
              <!-- 结束时间 -->
              <div class="endTime fr">
                <el-form-item label="结束日期" prop="groupEndTime">
                  <el-date-picker
                    v-model="groupForm.groupEndTime"
                    type="datetime"
                    format="yyyy-MM-dd HH:mm:ss"
                    value-format="yyyy-MM-dd HH:mm:ss"
                    clearable
                  ></el-date-picker>
                </el-form-item>
              </div>
              <!-- 开始时间 -->
              <div class="startTime fr">
                <el-form-item label="开始日期" prop="groupStartTime">
                  <el-date-picker
                    v-model="groupForm.groupStartTime"
                    type="datetime"
                    format="yyyy-MM-dd HH:mm:ss"
                    value-format="yyyy-MM-dd HH:mm:ss"
                    align="right"
                    clearable
                  ></el-date-picker>
                </el-form-item>
              </div>
            </el-form>
          </div>
          <!-- 服务状态折线图样式 -->
          <div id="groupLine" style="width: 100%; height: 350px; margin: 0 auto;"></div>
        </div>
      </div>
    </div>
    <!-- 表格  -->
    <div class="tableBox">
      <el-table :data="table" :span-method="objectSpanMethod" border style="text-align: center;">
        <el-table-column prop="serviceArr" label="服务" width="90"></el-table-column>
        <el-table-column prop="indexArr" label="指标" ></el-table-column>
        <el-table-column prop="stateArr" label="状态" class="p0" width="84">
          <template slot-scope="{row}">
            <div
              v-if="row.stateArr === '紧急'"
              class="p0"
              style="background-color: red;padding:0px;line-height: 48px;padding-left: 0px;padding-right: 0px;"
            >{{row.stateArr}}</div>
            <div
              v-if="row.stateArr === '警告'"
              class="p0"
              style="background-color: #ba8c54;padding:0px;line-height: 48px;padding-left: 0px;padding-right: 0px;"
            >{{row.stateArr}}</div>
            <div
              v-if="row.stateArr === '正常'"
              class="p0"
              style="background-color: #559440;padding:0px;line-height: 48px;padding-left: 0px;padding-right: 0px;"
            >{{row.stateArr}}</div>
            <div
              v-if="row.stateArr === '未知'"
              class="p0"
              style="background-color: #8e959f;padding:0px;line-height: 48px;padding-left: 0px;padding-right: 0px;"
            >{{row.stateArr}}</div>
          </template>
        </el-table-column>
        <el-table-column prop="hostArr" label="当前值" ></el-table-column>
        <el-table-column prop="aliasArr" label="正常值" ></el-table-column>
        <el-table-column prop="ipArr" label="警告阈值" ></el-table-column>
        <el-table-column prop="attemptArr" label="严重阈值" ></el-table-column>
        <el-table-column prop="stateInfoArr" label="状态信息"></el-table-column>
        <el-table-column prop="functionArr" label="指标描述"></el-table-column>
        <el-table-column prop="testingArr" label="处理建议"></el-table-column>
        <el-table-column prop="referenceLogArr" label="参考日志"></el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import {
  // 服务问题折线图
  getEmpLine
} from "@/api/table.js";
export function mergeTableRow(data, merge) {
  if (!merge || merge.length === 0) {
    return data;
  }
  merge.forEach(m => {
    const mList = {};
    data = data.map((v, index) => {
      const rowVal = v[m];
      if (mList[rowVal] && mList[rowVal].newIndex === index) {
        mList[rowVal]["num"]++;
        mList[rowVal]["newIndex"]++;
        data[mList[rowVal]["index"]][m + "-span"].rowspan++;
        v[m + "-span"] = {
          rowspan: 0,
          colspan: 0
        };
      } else {
        mList[rowVal] = { num: 1, index: index, newIndex: index + 1 };
        v[m + "-span"] = {
          rowspan: 1,
          colspan: 1
        };
      }
      return v;
    });
  });
  return data;
}
import {
  // 主机组模糊查询
  queryDownListGroup
} from "@/api/table.js";
import { tableData, tableTitle } from "./infoTable";
export default {
  data() {
    return {
      hostName: "tc11",
      hostId: "192.168.23.45",
      serverName: "tcll_fse",
      serverState: "未知(连接失败)",
      serverStable: "Unchangin (stable)",
      duration: "102天 8时 44分 33秒",
      lastTest: "2020-07-04 15:50:58",
      nextTest: "2020-08-04 15:50:58",
      // 服务状态表单数据
      groupForm: {
        groupType: "",
        groupStartTime: "",
        groupEndTime: ""
      },
      // -----------------------------
      table: [
        {
          hostArr: "TC11",
          aliasArr: "TC11",
          ipArr: "192.168.1.20",
          serviceArr: "TC11_web",
          indexArr: "Tc Web Tier Events",
          stateArr: "紧急",
          attemptArr: "5/5",
          functionArr: "83天 3时 10分 18秒",
          testingArr: "2020-07-04 15:50:58",
          stateInfoArr: "1521/tc11",
          referenceLogArr: "web日志syslog"
        },
        {
          hostArr: "TC11",
          aliasArr: "TC11",
          ipArr: "192.168.1.20",
          serviceArr: "TC11_web",
          indexArr: "Tc Web Tier Events",
          stateArr: "警告",
          attemptArr: "5/5",
          functionArr: "83天 3时 10分 18秒",
          testingArr: "2020-07-04 15:50:58",
          stateInfoArr: "1521/tc11",
          referenceLogArr: "web日志syslog"
        },
        {
          hostArr: "TC12",
          aliasArr: "TC12",
          ipArr: "192.168.1.21",
          serviceArr: "TC12_web",
          indexArr: "Tc Web Tier Events",
          stateArr: "正常",
          attemptArr: "5/5",
          functionArr: "83天 3时 10分 18秒",
          testingArr: "2020-07-04 15:50:58",
          stateInfoArr: "1521/tc11",
          referenceLogArr: "web日志syslog"
        },
        {
          hostArr: "TC12",
          aliasArr: "TC12",
          ipArr: "192.168.1.21",
          serviceArr: "TC12_web",
          indexArr: "Tc Web Tier Events",
          stateArr: "未知",
          attemptArr: "5/5",
          functionArr: "83天 3时 10分 18秒",
          testingArr: "2020-07-04 15:50:58",
          stateInfoArr: "1521/tc11",
          referenceLogArr: "web日志syslog"
        }
      ]
    };
  },
  created() {
    // 给table赋值，重新遍历新增rowSpan属性，checkRoom，appointmentTime为table里面需要合并的属性名称
    this.table = mergeTableRow(this.table, [
      "serviceArr"
    ]);
    // 默认一个月的日期填充
    this.getSumTimes();
  },
  methods: {
    seachBtn(){},
    // 获取当前日期
    getSumTimes() {
      // 获取当前的日期， 变量名为tiems
      this.nowData = new Date();
      const d = this.nowData;
      let month =
        d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
      let day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
      let hours = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
      let min = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
      let sec = d.getSeconds() < 10 ? "0" + d.getSeconds() : d.getSeconds();
      let times =
        d.getFullYear() +
        "-" +
        month +
        "-" +
        day +
        " " +
        hours +
        ":" +
        min +
        ":" +
        sec;
      this.times = times;

      // 获取当前的日期往前推一个月， 变量名为Dtimes
      let dTimes = new Date(new Date(this.nowData).getTime() - 86400000 * 30);
      let Dmonth =
        dTimes.getMonth() + 1 < 10
          ? "0" + (dTimes.getMonth() + 1)
          : dTimes.getMonth() + 1;
      let Dday =
        dTimes.getDate() < 10 ? "0" + dTimes.getDate() : dTimes.getDate();
      let Dhours =
        dTimes.getHours() < 10 ? "0" + dTimes.getHours() : dTimes.getHours();
      let Dmin =
        dTimes.getMinutes() < 10
          ? "0" + dTimes.getMinutes()
          : dTimes.getMinutes();
      let Dsec =
        dTimes.getSeconds() < 10
          ? "0" + dTimes.getSeconds()
          : dTimes.getSeconds();
      let Dtimes =
        dTimes.getFullYear() +
        "-" +
        Dmonth +
        "-" +
        Dday +
        " " +
        Dhours +
        ":" +
        Dmin +
        ":" +
        Dsec;
      this.Dtimes = Dtimes;

      this.groupForm.groupEndTime = this.times;
      this.groupForm.groupStartTime = this.Dtimes;
    },
    // 服务状态查询按钮
    async groupSeachButton() {
      const ttime1 = this.groupForm.groupStartTime;
      const ttime2 = this.groupForm.groupEndTime;
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("开始时间应小于结束时间");
        return;
      }
      // if (timeStr2 > timeStr1 + 2592000000) {
      //   this.$message.error("时间区间为一个月");
      //   return;
      // }
      this.pagesNum = 1;
      await this.$refs.form.validate(isValid => {
        if (isValid) {
          this.getGroupList();
        } else {
        }
      });
    },
    // 服务状态折线图函数数据
    async getGroupList() {
      const value = {
        groupType: this.groupForm.groupType,
        actionBeginTime: this.groupForm.groupStartTime,
        actionEndTime: this.groupForm.groupEndTime
      };
      const { data } = await getEmpLine(value);
      const { dateEntityList } = data;
      if (!dateEntityList.length) {
        this.groupInitItem();
        return;
      }
      // 时间
      const AllXTimes = dateEntityList.map(item => {
        return item.zheXianX;
      });
      // 服务问题净值
      const groupNetWorth = dateEntityList.map(item => {
        return item.KPI;
      });
      // 服务问题加分
      const groupBonusPoints = dateEntityList.map(item => {
        return item.KPIjiafen;
      });
      // 服务问题减分
      const groupDemerit = dateEntityList.map(item => {
        return item.KPIjianfen;
      });
      this.groupInitItem(
        AllXTimes,
        groupNetWorth,
        groupBonusPoints,
        groupDemerit
      );
    },
    // 服务状态kpl趋势折线图
    groupInitItem(
      AllXTimes = "",
      groupNetWorth = "",
      groupBonusPoints = "",
      groupDemerit = ""
    ) {
      // 服务状态的图表
      let myChart1 = this.$echarts.init(document.getElementById("groupLine"));
      const option1 = {
        title: {
          text: "指标趋势",
          left: "50%",
          top: "3%",
          textAlign: "center",
          subtextStyle: {
            color: "white"
          },
          x: "center",
          textStyle: {
            fontWeight: 400,
            fontSize: 18,
            color: "white"
          }
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        legend: {
          data: [
            "FSC Admin Read",
            "FSC All Routes Failed",
            "FSC Expired Ticket",
            "FSC Generic Error"
          ],
          icon: "rectangle",
          itemWidth: 16, // 设置宽度
          itemHeight: 16, // 设置高度
          itemGap: 40, // 设置间距
          bottom: "0",
          left: "20%",
          textStyle: {
            color: "white",
            fontSize: 12
          }
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "6%",
          containLabel: true
        },

        xAxis: {
          type: "category",
          boundaryGap: false,
          // 设置网格线
          splitLine: {
            show: true,
            lineStyle: {
              color: ["#315070"],
              width: 1,
              type: "solid"
            }
          },
          data: AllXTimes,
          axisLine: {
            show: true,
            lineStyle: {
              color: "#6a86a6"
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
            rotate: 40,
            textStyle: {
              fontFamily: "宋体",
              color: "white",
              fontSize: 16
            }
          }
        },
        yAxis: {
          type: "value",
          left: "5",
          axisLine: {
            show: true,
            lineStyle: {
              color: "#6a86a6",
              left: 10
            }
          },
          // 设置网格线
          splitLine: {
            show: true,
            lineStyle: {
              color: ["#315070"],
              width: 1,
              type: "solid"
            }
          },
          axisLabel: {
            textStyle: {
              color: "#4798a5" // 这里用参数代替了
            }
          },
          top: 50
        },
        dataZoom: [
          {
            show: true,
            height: 0,
            xAxisIndex: [0],
            bottom: "0%",
            start: 10,
            end: 90,
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
            height: 0,
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: "FSC Admin Read",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: groupNetWorth,
            lineStyle: {
              color: "#83e243",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#83e243",
              color: "#83e243"
            }
          },
          {
            name: "FSC All Routes Failed",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: groupBonusPoints,

            lineStyle: {
              color: "#ffff00",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#ffff00",
              color: "#ffff00"
            }
          },
          {
            name: "FSC Expired Ticket",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: groupDemerit,
            lineStyle: {
              color: "#bfbfbf",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#bfbfbf",
              color: "#bfbfbf"
            }
          },
          {
            name: "FSC Generic Error",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: groupDemerit,
            lineStyle: {
              color: "#ff0000",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#ff0000",
              color: "#ff0000"
            }
          }
        ]
      };
      myChart1.setOption(option1);
    },
    // -----------------------------
    // 动态表格数据
    objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      const span = column["property"] + "-span";
      if (row[span]) {
        return row[span];
      }
    }
  },
  mounted() {
    // -----------------------------
    // 服务状态
    this.getGroupList();
  }
};
</script>
<style lang="scss" scoped>
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
.detailsUnit {
  // 警告栏
  .warne {
    width: 100%;
    height: 100px;
    line-height: 100px;
    border-radius: 10px;
    margin: 30px 0 30px 0;
    background-color: #287bc2;
    .unknown {
      display: block;
      float: left;
      line-height: 100px;
      color: white;
    }
    .imgClass {
      line-height: 100px;
      display: block;
      float: left;
      margin: 0 auto;
      width: 60px;
      height: 50px;
      margin: 20px 30px 0 20px;
    }
  }
  // 主体栏
  .boxMain {
    width: 100%;
    height: 450px;
    box-sizing: border-box;
    .box-left {
      width: 30%;
      height: 450px;
      box-sizing: border-box;
      color: white;
      float: left;
      .left-top {
        height: 230px;
        .lin20 {
          line-height: 20px;
        }
        p {
          line-height: 40px;
        }
        ::v-deep .el-button--primary {
          height: 30px;
          line-height: 0px;
          margin-right: 100px;
          float: right;
        }
      }
      .left-bot {
        .lin20 {
          line-height: 20px;
        }
        p {
          line-height: 40px;
        }
        ::v-deep .el-button--primary {
          height: 30px;
          line-height: 0px;
          margin-right: 100px;
          float: right;
        }
      }
    }
    .box-right {
      width: 70%;
      height: 450px;
      box-sizing: border-box;
      float: left;
      .groupSum {
        ::v-deep .el-form-item__label {
          color: white;
        }
        ::v-deep .el-input__inner {
          background-color: #06385c;
          border: 1px solid #0c7ebd;
          height: 30px;
          line-height: 30px;
          color: white;
        }
        ::v-deep .el-button--primary {
          height: 30px;
          line-height: 0px;
          margin-left: 10px;
          margin-top: 5px;
        }
        ::v-deep .el-form-item {
          margin-bottom: 0;
        }
      }
    }
  }
  // 搜索的所有样式
  .seachAll {
    height: 30px;
    line-height: 30px;
    margin-top: 20px;
    margin-bottom: 20px;
    // 主机组
    .hostGroup {
      float: left;
    }
    // 主机
    .host {
      float: left;
    }
    // 服务组
    .serviceGroup {
      float: left;
    }
    // 服务
    .service {
      float: left;
    }
    // 状态
    .state {
      float: left;
    }
    // 搜索按钮
    .seachButton {
      float: right;
    }
    // 搜索框
    .seachBox {
      float: right;
    }
    ::v-deep .el-form-item__label {
      color: white;
    }
    ::v-deep .el-input__inner {
      background-color: #06385c;
      border: 1px solid #0c7ebd;
      height: 30px;
      line-height: 30px;
      color: white;
    }
    ::v-deep .el-button--primary {
      height: 30px;
      line-height: 0px;
      margin-left: 10px;
    }
  }
  // 表格
  .tableBox {
    ::v-deep .el-table th {
      background-color: #153a6b;
      text-align: center;
      color: white;
    }
    ::v-deep .el-table thead.is-group th {
      background-color: #153a6b;
      text-align: center;
    }
    ::v-deep .el-table__body td {
      text-align: center;
      color: white;
    }
    ::v-deep .el-table--border th {
      border-bottom: 1px solid #3e74a3;
      border-right: 1px solid #3e74a3;
    }
    ::v-deep .el-table--border td {
      border-bottom: 1px solid #3e74a3;
      border-right: 1px solid #3e74a3;
      background-color: #153a6b;
      color: white;
    }
    ::v-deep .el-table tbody tr:hover > td {
      background-color: #153a6b !important;
    }
    ::v-deep .el-table .cell {
      padding: 0;
    }
  }
}
</style>
