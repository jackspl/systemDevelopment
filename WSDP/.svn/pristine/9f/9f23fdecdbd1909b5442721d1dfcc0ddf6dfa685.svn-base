<template>
  <!-- 概况 -->
  <div class="hostUnit">
    <div class="topBox clearfix">
      <!-- 左侧服务概况 -->
      <div class="boxLeft">
        <div class="leftPei">
          <div id="topPei">
            <div id="allServer"></div>
          </div>
        </div>
        <div class="rightTab">
          <div class="topTab">
            <div class="m10">服务概况</div>
            <el-table :data="tableData" border>
              <el-table-column prop="1" label="全部"></el-table-column>
              <el-table-column prop="2" label="正常">
                <template slot="header">
                  <span style="width: 20px;display: block;height: 20px;background-color: #83e243;margin-right: 5px;border-radius: 50%;float: left;"></span>
                  <span style="font-size:14px">正常</span>
                </template>
              </el-table-column>
              <el-table-column prop="3" label="不可达">
                <template slot="header">
                  <span style="width: 20px;display: block;height: 20px;background-color: #ffff00;margin-right: 5px;border-radius: 50%;float: left;"></span>
                  <span style="font-size:14px">不可达</span>
                </template>
              </el-table-column>
              <el-table-column prop="4" label="宕机">
                <template slot="header">
                  <span style="width: 20px;display: block;height: 20px;background-color: #ff0000;margin-right: 5px;border-radius: 50%;float: left;"></span>
                  <span style="font-size:14px">宕机</span>
                </template>
              </el-table-column>
              <el-table-column prop="5" label="问题">
                <template slot="header">
                  <span style="width: 20px;display: block;height: 20px;background-color: #601986;margin-right: 5px;border-radius: 50%;float: left;"></span>
                  <span style="font-size:14px">问题</span>
                </template>
              </el-table-column>
              <el-table-column prop="6" label="待定">
                <template slot="header">
                  <span style="width: 20px;display: block;height: 20px;background-color: #00b7ee;margin-right: 5px;border-radius: 50%;float: left;"></span>
                  <span style="font-size:14px">待定</span>
                </template>
              </el-table-column>
              <el-table-column prop="8" label="未处理">
                <template slot="header">
                  <span style="width: 20px;display: block;height: 20px;background-color: #ffb255;margin-right: 5px;border-radius: 50%;float: left;"></span>
                  <span style="font-size:14px">未处理</span>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="bottomTab">
            <div class="m10">问题来源主机组排名</div>
            <el-table :data="hostTable" border height="300px">
              <el-table-column label="主机">
              <el-table-column prop="1" label="主机组" width="135px"></el-table-column>
              <el-table-column prop="2" label="主机" width="64px"></el-table-column>
              <el-table-column prop="3" label="正常" width="64px"></el-table-column>
              <el-table-column prop="4" label="不可达" width="70px"></el-table-column>
              <el-table-column prop="5" label="宕机" width="64px"></el-table-column>
              </el-table-column>
              <el-table-column label="服务">
              <el-table-column prop="6" label="紧急" width="64px"></el-table-column>
              <el-table-column prop="7" label="待定" width="64px"></el-table-column>
              <el-table-column prop="8" label="问题" width="64px"></el-table-column>
              <el-table-column prop="9" label="未处理" width="80px"></el-table-column>
              </el-table-column>
            </el-table>
          </div>
        </div>
      </div>
      <!-- 右侧指标概况 -->
      <div class="boxRight">
        <div class="leftPei">
          <div id="topPei">
            <div id="allIndex"></div>
          </div>
        </div>
        <div class="rightTab">
          <div class="topTab">
            <div class="m10">服务概况</div>
            <el-table :data="tableData" border>
              <el-table-column prop="1" label="全部"></el-table-column>
              <el-table-column prop="2" label="正常">
                <template slot="header">
                  <span style="width: 20px;display: block;height: 20px;background-color: #83e243;margin-right: 5px;border-radius: 50%;float: left;"></span>
                  <span style="font-size:14px">正常</span>
                </template>
              </el-table-column>
              <el-table-column prop="3" label="不可达">
                <template slot="header">
                  <span style="width: 20px;display: block;height: 20px;background-color: #ffff00;margin-right: 5px;border-radius: 50%;float: left;"></span>
                  <span style="font-size:14px">不可达</span>
                </template>
              </el-table-column>
              <el-table-column prop="4" label="宕机">
                <template slot="header">
                  <span style="width: 20px;display: block;height: 20px;background-color: #ff0000;margin-right: 5px;border-radius: 50%;float: left;"></span>
                  <span style="font-size:14px">宕机</span>
                </template>
              </el-table-column>
              <el-table-column prop="5" label="问题">
                <template slot="header">
                  <span style="width: 20px;display: block;height: 20px;background-color: #601986;margin-right: 5px;border-radius: 50%;float: left;"></span>
                  <span style="font-size:14px">问题</span>
                </template>
              </el-table-column>
              <el-table-column prop="6" label="待定">
                <template slot="header">
                  <span style="width: 20px;display: block;height: 20px;background-color: #00b7ee;margin-right: 5px;border-radius: 50%;float: left;"></span>
                  <span style="font-size:14px">待定</span>
                </template>
              </el-table-column>
              <el-table-column prop="8" label="未处理">
                <template slot="header">
                  <span style="width: 20px;display: block;height: 20px;background-color: #ffb255;margin-right: 5px;border-radius: 50%;float: left;"></span>
                  <span style="font-size:14px">未处理</span>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <div class="bottomTab">
            <div class="m10">问题来源主机排名</div>
            <el-table :data="hostTable" border height="300px">
              <el-table-column prop="1" label="主机" width="135px"></el-table-column>
              <el-table-column prop="2" label="全部" width="64px"></el-table-column>
              <el-table-column prop="3" label="正常" width="64px"></el-table-column>
              <el-table-column prop="4" label="警告" width="64px"></el-table-column>
              <el-table-column prop="5" label="未知" width="64px"></el-table-column>
              <el-table-column prop="6" label="紧急" width="64px"></el-table-column>
              <el-table-column prop="7" label="待定" width="64px"></el-table-column>
              <el-table-column prop="8" label="问题" width="64px"></el-table-column>
              <el-table-column prop="9" label="未处理" width="80px"></el-table-column>
            </el-table>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import {
  // 12、公司KPI贡献接口
  comloyeKpiPie
} from "@/api/table.js";
export default {
  data() {
    return {
      tableData: [
        {
          1: "5976",
          2: "4283",
          3: "1186",
          4: "63",
          5: "95",
          6: "36",
          7: "35",
          8: "78"
        },
        {
          1: "100%",
          2: "24.9%",
          3: "1.4%",
          4: "2.5%",
          5: "0.7%",
          6: "0.9%",
          7: "2.9%",
          8: "78%"
        }
      ],
      hostTable: [
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760"
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760"
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760"
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760"
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760"
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760"
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760"
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760"
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760"
        }
      ],
      // -----------------------------
      // 动态表格数据
      info: {
        string: "",
        avg: "(平均值)",
        sum: "(总数)"
      },
      testTableArr: [], // table数据
      spanKey: [], // 需要合并的列,每个元素为表格的prop值（键值）
      tableTitle: {}, // table表头显示
      tableTitleKey: [], // 所有的列的prop值（键值）
      spanMethod: [], // 表格向下合并的方式
      divArr: [], // 分片数据，用于将数据分片，保证不会
      divIndex: [0] // 用于记录分片的位置
    };
  },
  created() {
    // 全部主机组饼图
    this.getAllServer();

    // 全部主机饼图
    this.getAllIndex();
  },
  methods: {
    labelHead: function(h, { column, $index }) {
      let l = column.label.length;
      let f = 16;
      column.minWidth = f * (l + 2); //加上一个文字长度
      return h("div", { class: "table-head", style: { width: "100%" } }, [
        column.label
      ]);
    },
    // 提取全部主机组公共饼图函数
    async getAllServer(index = 1) {
      const value = {
        actionBeginTime: "2020-06-21 10:10:34",
        actionEndTime: "2020-07-21 10:10:34",
        kpiContributionFlag: 1
      };
      const { data } = await comloyeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.map((item, index) => {
        const comRandColor = this.randColor(item, index);
        return {
          value: item.KPI,
          name:
            item.company +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          itemStyle: {
            normal: {
              color: comRandColor
            }
          }
        };
      });
      this.serverPei(newArr, allKPI);
    },
    // 全部主机组饼图函数
    serverPei(arr, allKPI) {
      let myChart1 = this.$echarts.init(document.getElementById("allServer"));
      this.initHeight1 = document.getElementById("topPei").offsetHeight;
      this.initWidth1 = document.getElementById("topPei").offsetWidth;
      const option1 = {
        animation: false,
        title: {
          text: "全部主机组",
          subtext: allKPI,
          x: "center",
          y: "40%",
          textStyle: {
            fontWeight: "normal",
            fontSize: 14,
            color: "white"
          },
          subtextStyle: {
            fontSize: 14,
            color: "white"
          }
        },
        // 鼠标放上去有提示
        tooltip: {
          show: true,
          trigger: "item",
          formatter: "{b}：{c}"
          // formatter: '{b}：{c}个<br/>占比：({d}%)'
        },
        series: [
          {
            type: "pie",
            stillShowZeroSum: true,
            //     外圈大小  内圈大小
            radius: ["80%", "50%"],
            //     左右居中  上下居中
            center: ["50%", "50%"],
            // 饼图轮廓阴影
            itemStyle: {
              normal: {
                shadowBlur: 30,
                shadowColor: "rgba(255,227,42,0.3)",
                shadowOffsetX: -5,
                shadowOffsetY: 5
              }
            },
            label: {
              normal: {
                position: "inner",
                show: false,
                textStyle: {
                  color: "#fff",
                  fontSize: 12
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: arr
          }
        ]
      };
      myChart1.setOption(option1);
      let resize1 = {
        height: this.initHeight1,
        width: this.initWidth1
      };
      myChart1.resize(resize1);
    },
    // ----------------------------------

    // --------------------------------------
    // 提取全部主机公共饼图函数
    async getAllIndex(index = 1) {
      const value = {
        actionBeginTime: "2020-06-21 10:10:34",
        actionEndTime: "2020-07-21 10:10:34",
        kpiContributionFlag: 1
      };
      const { data } = await comloyeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.map((item, index) => {
        const comRandColor = this.randColor(item, index);
        return {
          value: item.KPI,
          name:
            item.company +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          itemStyle: {
            normal: {
              color: comRandColor
            }
          }
        };
      });
      this.indexPei(newArr, allKPI);
    },
    // 全部主机饼图函数
    indexPei(arr, allKPI) {
      let myChart3 = this.$echarts.init(document.getElementById("allIndex"));
      this.initHeight3 = document.getElementById("topPei").offsetHeight;
      this.initWidth3 = document.getElementById("topPei").offsetWidth;
      const option3 = {
        animation: false,
        title: {
          text: "全部主机",
          subtext: allKPI,
          x: "center",
          y: "40%",
          textStyle: {
            fontWeight: "normal",
            fontSize: 14,
            color: "white"
          },
          subtextStyle: {
            fontSize: 14,
            color: "white"
          }
        },
        // 鼠标放上去有提示
        tooltip: {
          show: true,
          trigger: "item",
          formatter: "{b}：{c}"
          // formatter: '{b}：{c}个<br/>占比：({d}%)'
        },
        series: [
          {
            type: "pie",
            stillShowZeroSum: true,
            //     外圈大小  内圈大小
            radius: ["80%", "50%"],
            //     左右居中  上下居中
            center: ["50%", "50%"],
            // 饼图轮廓阴影
            itemStyle: {
              normal: {
                shadowBlur: 30,
                shadowColor: "rgba(255,227,42,0.3)",
                shadowOffsetX: -5,
                shadowOffsetY: 5
              }
            },
            label: {
              normal: {
                position: "inner",
                show: false,
                textStyle: {
                  color: "#fff",
                  fontSize: 12
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            data: arr
          }
        ]
      };
      myChart3.setOption(option3);
      let resize3 = {
        height: this.initHeight3,
        width: this.initWidth3
      };
      myChart3.resize(resize3);
    },
    // --------------------------------------

    // 随机颜色
    randColor(item, index) {
      // const Arr = ['red', 'black', 'yellow', 'blue', '#FD70B8',
      // '#CBEAED', '#8EEBAC', '#6139E5', '#F38946', '#7BA5F4', '#3CFA89'
      // , '#4FEE1F', '#CB59F6', '#FDB10F', '#EBD50C', '#F0D54E']
      const Arr = [
        "#CBEAED",
        "#8EEBAC",
        "#6139E5",
        "#F38946",
        "#7BA5F4",
        "#3CFA89",
        "#4FEE1F",
        "#CB59F6",
        "#6139E5",
        "#F38946",
        "yellow",
        "blue",
        "red",
        "black",
        "yellow",
        "blue",
        "red",
        "black",
        "yellow",
        "blue",
        "red",
        "black",
        "yellow",
        "blue",
        "red",
        "black",
        "yellow",
        "blue"
      ];
      // const color = Math.floor(Math.random() * Arr.length + 1)-1;
      const color = Arr[index];
      return color;
    }
    // -----------------------------
  },
  mounted() {
    // -----------------------------
  }
};
</script>
<style lang="scss" >
@import "../../../../css/hostFile/hostUnit.css";
.hostUnit {
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
  .topBox {
    // 左侧服务概况
    .boxLeft {
      width: 50%;
      height: 630px;
      // border: 1px solid #3e74a3;
      // background-color: #193153;
      box-sizing: border-box;
      margin-top: 10px;
      float: left;
      border-radius: 5px;
      .leftPei {
        width: 20%;
        height: 100%;
        box-sizing: border-box;
        float: left;
        #topPei {
          height: 200px;
          z-index: 999;
        }
        #bottomPei {
          height: 200px;
          z-index: 999;
        }
      }
      .rightTab {
        width: 80%;
        height: 100%;
        box-sizing: border-box;
        float: left;
        .topTab {
          text-align: center;
          .m10 {
            margin: 10px 0;
            color: white;
            font-size: 20px;
          }
          .el-table th,
          .el-table tr {
            background-color: #02356f;
            color: white;
            border-bottom: 1px solid #3e74a3;
            border-right: 1px solid #3e74a3;
            text-align: center;
          }
        }
        .bottomTab {
          .m10 {
            margin: 10px 0;
            color: white;
            font-size: 20px;
          }
          .el-table th,
          .el-table tr {
            background-color: #02356f;
            color: white;
            border-bottom: 1px solid #3e74a3;
            border-right: 1px solid #3e74a3;
            text-align: center;
          }
          text-align: center;
          margin-top: 70px;
          .el-table__header tr,
          .el-table__header th {
            padding: 0;
            height: 40px;
          }
          .el-table__body tr,
          .el-table__body td {
            padding: 0;
            height: 24px;
          }
          /* 最为关键得两个样式代码，可以设置全局滚动条样式，也可以按需设置 */
          ::-webkit-scrollbar {
            /* 设置竖向滚动条的宽度 */
            width: 4px;
            /* 设置横向滚动条的高度 */
            height: 5px;
          }
          ::-webkit-scrollbar-thumb {
            /*滚动条的背景色*/
            background-color: rgba(144, 147, 153, 0.3);
            border-radius: 5px;
            /* 设置竖向滚动条的宽度 */
            width: 4px;
            /* 设置横向滚动条的高度 */
            height: 5px;
          }
        }
      }
    }
    // 右侧指标概况
    .boxRight {
      width: 50%;
      height: 430px;
      // background-color: #193153;
      box-sizing: border-box;
      margin-top: 10px;
      float: left;
      border-radius: 5px;
      .leftPei {
        width: 20%;
        height: 100%;
        background-color: #02356f;
        box-sizing: border-box;
        float: left;
        #topPei {
          height: 200px;
          z-index: 999;
        }
        #bottomPei {
          height: 200px;
          z-index: 999;
        }
      }
      .rightTab {
        width: 80%;
        height: 100%;
        box-sizing: border-box;
        float: left;
        .topTab {
          text-align: center;
          .m10 {
            margin: 10px 0;
            color: white;
            font-size: 20px;
          }
          .normalRound {
            display: block;
            width: 10px;
            height: 10px;
            background-color: greenyellow;
            border-radius: 50%;
            float: left;
          }
          .el-table th,
          .el-table tr {
            background-color: #02356f;
            color: white;
            border-bottom: 1px solid #3e74a3;
            border-right: 1px solid #3e74a3;
            text-align: center;
          }
        }
        .bottomTab {
          .m10 {
            margin: 10px 0;
            color: white;
            font-size: 20px;
          }
          .el-table th,
          .el-table tr {
            background-color: #02356f;
            color: white;
            border-bottom: 1px solid #3e74a3;
            border-right: 1px solid #3e74a3;
            text-align: center;
          }
          text-align: center;
          margin-top: 70px;
          .el-table__header tr,
          .el-table__header th {
            padding: 0;
            height: 40px;
          }
          .el-table__body tr,
          .el-table__body td {
            padding: 0;
            height: 24px;
          }
          /* 最为关键得两个样式代码，可以设置全局滚动条样式，也可以按需设置 */
          ::-webkit-scrollbar {
            /* 设置竖向滚动条的宽度 */
            width: 4px;
            /* 设置横向滚动条的高度 */
            height: 5px;
          }
          ::-webkit-scrollbar-thumb {
            /*滚动条的背景色*/
            background-color: rgba(144, 147, 153, 0.3);
            border-radius: 5px;
            /* 设置竖向滚动条的宽度 */
            width: 4px;
            /* 设置横向滚动条的高度 */
            height: 5px;
          }
        }
      }
    }
  }
  // 动态表格
  .botBox {
    width: 100%;
    height: 530px;
    border: 1px solid #3e74a3;
    margin-top: 20px;
    .el-table thead {
      color: red;
    }
    .titleColor1 {
      writing-mode: tb-rl;
    }
    .titleColor2 {
      writing-mode: tb-rl;
    }
    .el-table th,
    .el-table tr {
      background-color: #02356f;
      color: white;
      border-bottom: 1px solid #3e74a3;
      border-right: 1px solid #3e74a3;
      text-align: center;
    }
    .el-table--striped .el-table__body tr.el-table__row--striped td {
      background-color: #02356f;
    }
    /* 最为关键得两个样式代码，可以设置全局滚动条样式，也可以按需设置 */
    ::-webkit-scrollbar {
      /* 设置竖向滚动条的宽度 */
      width: 4px;
      /* 设置横向滚动条的高度 */
      height: 5px;
    }
    ::-webkit-scrollbar-thumb {
      /*滚动条的背景色*/
      background-color: rgba(144, 147, 153, 0.3);
      border-radius: 5px;
      /* 设置竖向滚动条的宽度 */
      width: 4px;
      /* 设置横向滚动条的高度 */
      height: 5px;
    }
  }
}
</style>
