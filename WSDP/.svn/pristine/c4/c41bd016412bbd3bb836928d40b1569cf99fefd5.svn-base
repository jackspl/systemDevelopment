<template>
  <!-- 趋势分析 -->
  <div class="trendAnalysis">
    <!-- 公司趋势分析 -->
    <el-form :inline="true" :model="comForm" ref="form" class="trenSeach1">
      <div class="seachAll clearfix">
        <!-- 搜索条件样式 -->
        <div class="searchCriteria">
          <el-form-item label="搜索条件" class="seachCond">
            <el-autocomplete
              v-model="comForm.comSearchCrite"
              placeholder="四个公司一年异常"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="ConditionFuzzyQuery"
              @select="handleSelectScen"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <div class="seachImg">
          <img src="../../../../../assets/seve.png" style="margin-top:8px; margin-left: 10px;" />
        </div>
        <!-- 公司下拉菜单选择 -->
        <div class="seachCompany">
          <el-form-item label="公司">
            <el-autocomplete
              v-model="comForm.comSearchCom"
              placeholder="上海公司"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="CompanyFuzzyQuery"
              @select="handleSelectScen"
              style="width: 150px;"
            ></el-autocomplete>
          </el-form-item>
        </div>

        <div class="seachButton">
          <el-form-item>
            <el-button type="primary" @click="seachBtn">查找</el-button>
            <el-button>重置</el-button>
          </el-form-item>
        </div>
        <div class="seachEndTime">
          <!-- 结束时间 -->
          <el-form-item label="结束日期" prop="actionEndTime1">
            <el-date-picker
              v-model="comForm.comEndTime"
              type="datetime"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              clearable
            ></el-date-picker>
          </el-form-item>
        </div>
        <div class="seachStarTime">
          <!-- 开始时间 -->
          <el-form-item label="开始日期" prop="actionBeginTime1">
            <el-date-picker
              v-model="comForm.comStartTime"
              type="datetime"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              align="right"
              clearable
            ></el-date-picker>
          </el-form-item>
        </div>
      </div>
    </el-form>

    <!-- 公司异常趋势主体一 -->
    <el-row class="mb20">
      <!-- 饼图 -->
      <el-col :span="4">
        <div class="grid-content infoLeft">
          <div id="main5" style="width: 100%;height:380px;"></div>
        </div>
      </el-col>
      <!-- 柱状图 -->
      <el-col :span="20">
        <div class="grid-content infoRight">
          <div id="main6" style="width: 100%;height:360px;"></div>
        </div>
      </el-col>
    </el-row>
    <!-- -------------------------------------------------------------------------------------- -->
    <!-- 部门趋势分析 -->
    <!-- 搜索查询 -->
    <div style="margin-bottom:10px; height: 50px;">
      <el-form :inline="true" :model="depForm" ref="form">
        <div class="depSeachAll">
          <!-- 搜索条件 -->
          <div class="searchCriteria">
            <el-form-item label="搜索条件">
              <el-autocomplete
                v-model="depForm.depSearchCrite"
                placeholder="上海公司所有部门"
                clearable
                type="text"
                :debounce="0"
                :fetch-suggestions="ConditionFuzzyQuery"
                @select="handleSelectScen"
              ></el-autocomplete>
            </el-form-item>
          </div>
          <div class="seachImg">
            <img src="../../../../../assets/seve.png" style="margin-top:8px; margin-left: 10px;" />
          </div>
          <!-- 公司选择 -->
          <div class="searchCompany">
            <el-form-item label="公司" class="comyne">
              <el-autocomplete
                v-model="depForm.depSearchCom"
                placeholder="北京公司"
                clearable
                type="text"
                :debounce="0"
                :fetch-suggestions="CompanyFuzzyQuery"
                @select="handleSelectScen"
              ></el-autocomplete>
            </el-form-item>
          </div>
          <!-- 部门选择 -->
          <div class="searchDep">
            <el-form-item label="部门" class="depar2">
              <el-autocomplete
                v-model="depForm.depSearchDepartment"
                placeholder="研发部"
                clearable
                type="text"
                :debounce="0"
                :fetch-suggestions="DepartmentFuzzyQuery"
                @select="handleSelectScen"
              ></el-autocomplete>
            </el-form-item>
          </div>

          <div class="seachButton">
            <el-form-item>
              <el-button type="primary" @click="seachBtn">查找</el-button>
              <el-button>重置</el-button>
            </el-form-item>
          </div>
          <div class="seachEndTime">
            <el-form-item label="结束日期">
              <el-date-picker
                v-model="depForm.depEndTime"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
          <div class="seachStarTime">
            <el-form-item label="开始日期">
              <el-date-picker
                v-model="depForm.depStartTime"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                align="right"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
        </div>
      </el-form>
    </div>

    <!-- 公司异常趋势主体一 -->
    <el-row class="mb20">
      <el-col :span="4">
        <div class="grid-content infoLeft">
          <div id="main7" style="width: 100%;height:380px;"></div>
        </div>
      </el-col>
      <el-col :span="20">
        <div class="grid-content infoRight">
          <div id="main8" style="width: 100%;height:360px;"></div>
        </div>
      </el-col>
    </el-row>
    <!-- ----------------------------------------------------------------------------------------------------- -->
    <!-- 个人趋势分析 -->
    <!-- 搜索查询 -->
    <div style="margin-bottom:10px; height: 50px;">
      <el-form :inline="true" :model="perForm" ref="form">
        <div class="depSeachAll">
          <!-- 搜索条件 -->
          <div class="searchCriteria">
            <el-form-item label="搜索条件">
              <el-autocomplete
                v-model="perForm.perSearchCrite"
                placeholder="上海公司所有部门"
                clearable
                type="text"
                :debounce="0"
                :fetch-suggestions="ConditionFuzzyQuery"
                @select="handleSelectScen"
              ></el-autocomplete>
            </el-form-item>
          </div>
          <div class="seachImg">
            <img src="../../../../../assets/seve.png" style="margin-top:8px; margin-left: 10px;" />
          </div>
          <!-- 公司选择 -->
          <div class="searchCompany">
            <el-form-item label="公司" class="comyne">
              <el-autocomplete
                v-model="perForm.perSearchCom"
                placeholder="北京公司"
                clearable
                type="text"
                :debounce="0"
                :fetch-suggestions="CompanyFuzzyQuery"
                @select="handleSelectScen"
              ></el-autocomplete>
            </el-form-item>
          </div>
          <div class="searchDep">
            <!-- 部门选择 -->
            <el-form-item label="部门" class="depar2">
              <el-autocomplete
                v-model="perForm.perSearchDepartment"
                placeholder="北京公司"
                clearable
                type="text"
                :debounce="0"
                :fetch-suggestions="DepartmentFuzzyQuery"
                @select="handleSelectScen"
              ></el-autocomplete>
            </el-form-item>
          </div>
          <div class="seachButton">
            <el-form-item>
              <el-button type="primary" @click="seachBtn">查找</el-button>
              <el-button>重置</el-button>
            </el-form-item>
          </div>

          <div class="seachEndTime">
            <el-form-item label="结束日期">
              <el-date-picker
                v-model="perForm.perEndTime"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
          <div class="seachStarTime">
            <el-form-item label="开始日期">
              <el-date-picker
                v-model="perForm.perStartTime"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                align="right"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
        </div>
      </el-form>
    </div>

    <!-- 个人异常趋势主体一 -->
    <el-row class="mb20">
      <el-col :span="4">
        <div class="grid-content infoLeft3">
          <div id="main9" style="width: 100%;height:360px;"></div>
        </div>
      </el-col>
      <el-col :span="20">
        <div class="grid-content infoRight3">
          <div id="main10" style="width: 100%;height:380px;"></div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {
  // 所有公司模糊查询（借用场景模糊查询）
  getPulldownListScene
} from "@/api/table.js";
export default {
  data() {
    return {
      // 公司表单
      comForm: {
        // 公司搜索条件下拉菜单
        comSearchCrite: "",
        // 公司下拉菜单
        comSearchCom: "",
        // 公司开始时间
        comStartTime: "",
        // 公司结束时间
        comEndTime: ""
      },
      // 部门表单
      depForm: {
        // 部门搜索条件下拉菜单
        depSearchCrite: "",
        // 公司下拉菜单
        depSearchCom: "",
        // 部门部门
        depSearchDepartment: "",
        // 部门开始时间
        depStartTime: "",
        // 部门结束时间
        depEndTime: ""
      },
      // 个人表单
      perForm: {
        // 个人搜索条件下拉菜单
        perSearchCrite: "",
        // 公司下拉菜单
        perSearchCom: "",
        // 部门下拉菜单
        perSearchDepartment: "",
        // 个人开始时间
        perStartTime: "",
        // 个人结束时间
        perEndTime: ""
      },
      form: {
        region1: "",
        region2: "",
        region3: "",
        region4: "",
        region5: "",
        region6: "",
        region7: "",
        region8: "",
        actionBeginTime1: "", // 开始日期
        actionEndTime1: "", // 结束日期
        actionBeginTime2: "", // 开始日期
        actionEndTime2: "", // 结束日期
        actionBeginTime3: "2019-01", // 开始日期
        actionEndTime3: "2019-12-31" // 结束日期
      }
    };
  },
  created() {
    // 默认一个月的日期填充
    this.getSumTimes();
    // this.Data()
  },
  methods: {
    // 搜索条件模糊查询
    ConditionFuzzyQuery(queryString, callback) {
      // 需要随时更改的（从后台获取到对象数组）
      getPulldownListScene({
        sceneName: queryString
      }).then(res => {
        let list = [];
        if (res.data.fuzzySceneList === null) {
          list = [];
        } else {
          res.data.fuzzySceneList.forEach(item => {
            list.push({
              value: item
            });
          });
        }
        callback(list);
      });
    },
    // 公司模糊查询
    CompanyFuzzyQuery(queryString, callback) {
      // 需要随时更改的（从后台获取到对象数组）
      getPulldownListScene({
        sceneName: queryString
      }).then(res => {
        let list = [];
        if (res.data.fuzzySceneList === null) {
          list = [];
        } else {
          res.data.fuzzySceneList.forEach(item => {
            list.push({
              value: item
            });
          });
        }
        callback(list);
      });
    },
    // 部门模糊查询
    DepartmentFuzzyQuery(queryString, callback) {
      // 需要随时更改的（从后台获取到对象数组）
      getPulldownListScene({
        sceneName: queryString
      }).then(res => {
        let list = [];
        if (res.data.fuzzySceneList === null) {
          list = [];
        } else {
          res.data.fuzzySceneList.forEach(item => {
            list.push({
              value: item
            });
          });
        }
        callback(list);
      });
    },
    handleSelectScen(item) {},
    // 获取当前日期
    getSumTimes() {
      // 获取当前的日期， 变量名为tiems
      this.nowData = new Date();
      const d = this.nowData;
      let month =
        d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
      let day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
      let times = d.getFullYear() + "-" + month + "-" + day + " ";
      this.times = times;
      // 获取当前的日期往前推一个月， 变量名为Dtimes
      let dTimes = new Date(new Date(this.nowData).getTime() - 86400000 * 30);
      let Dmonth =
        dTimes.getMonth() + 1 < 10
          ? "0" + (dTimes.getMonth() + 1)
          : dTimes.getMonth() + 1;
      let Dday =
        dTimes.getDate() < 10 ? "0" + dTimes.getDate() : dTimes.getDate();
      let Dtimes = dTimes.getFullYear() + "-" + Dmonth + "-" + Dday + " ";
      this.Dtimes = Dtimes;
      // .Dtimes 一个月之前的时间
      this.comForm.comStartTime = this.Dtimes;
      this.depForm.depStartTime = this.Dtimes;
      this.perForm.perStartTime = this.Dtimes;
      this.comForm.comEndTime = this.times;
      this.depForm.depEndTime = this.times;
      this.perForm.perEndTime = this.times;
    },
    // 查询按钮
    seachBtn() {
      // 首先验证，在执行回车事件
      this.$refs.form.validate();
      const ttime1 = this.form.actionBeginTime;
      const ttime2 = this.form.actionEndTime;
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("请合理选择日期");
        // return
      }
      // this.getTableList(this.form)
    }
  },
  mounted() {
    let myChart1 = this.$echarts.init(document.getElementById("main5"));
    let myChart2 = this.$echarts.init(document.getElementById("main6"));
    let myChart3 = this.$echarts.init(document.getElementById("main7"));
    let myChart4 = this.$echarts.init(document.getElementById("main8"));
    let myChart5 = this.$echarts.init(document.getElementById("main9"));
    let myChart6 = this.$echarts.init(document.getElementById("main10"));

    // 公司异常操作echarts
    const option1 = {
      animation: false,
      title: {
        text: "信息安全事件",
        subtext: "1268人次",
        x: "center",
        y: "28%",
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

      legend: {
        // orient: 'horizontal',
        x: "center",
        top: "260",
        textStyle: {
          // 图例文字的样式
          color: "#ccc",
          fontSize: 12
        },
        itemWidth: 22,
        itemHeight: 14,
        itemGap: 20,
        icon: "rect",
        orient: "vertical",
        left: "16%",
        align: "left",
        height: 80
      },
      // 鼠标放上去有提示
      tooltip: {
        show: true,
        trigger: "item",
        formatter: "{b}：{c}个<br/>占比：({d}%)"
      },
      series: [
        {
          type: "pie",
          stillShowZeroSum: true,
          radius: ["60%", "41%"],
          center: ["50%", "35%"],
          // 饼图轮廓阴影
          itemStyle: {
            normal: {
              // 设置扇形的阴影
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
          data: [
            {
              value: 250,
              name: "上海公司",
              radius: [0, "50%"],
              hoverAnimation: false,
              itemStyle: {
                normal: {
                  color: "#7171db"
                }
              }
            },

            {
              value: 250,
              name: "广州公司",
              radius: [0, "50%"],
              hoverAnimation: false,
              itemStyle: {
                normal: {
                  color: "#dedd5f"
                }
              }
            },
            {
              value: 250,
              name: "成都公司",
              radius: [0, "50%"],
              hoverAnimation: false,
              itemStyle: {
                normal: {
                  color: "#e7885f"
                }
              }
            },
            {
              value: 250,
              name: "北京公司",
              radius: [0, "50%"],
              hoverAnimation: false,
              textStyle: {
                color: "blue"
              },
              itemStyle: {
                normal: {
                  color: "#84ccc9"
                }
              }
            }
          ]
        }
      ]
    };
    const option2 = {
      title: {
        text: "公 司 异 常 趋 势",
        left: "center",
        textStyle: {
          color: "white",
          fontWeight: 400
        }
      },
      tooltip: {
        trigger: "axis",
        axisPointer: {
          // 坐标轴指示器，坐标轴触发有效
          type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
        }
      },
      toolbox: {
        x: "92%",
        y: "20",
        feature: {
          magicType: {
            show: true,
            type: ["line", "bar"],
            iconStyle: {
              borderColor: "white"
            }
          },
          saveAsImage: {
            show: true,
            iconStyle: {
              borderColor: "white"
            },
            backgroundColor: "#164276"
          }
        }
      },
      grid: {
        left: "3%",
        right: "4%",
        bottom: "3%",
        containLabel: true
      },
      xAxis: [
        {
          type: "category",
          data: [
            "2020-01-01",
            "2020-01-02",
            "2020-01-03",
            "2020-01-04",
            "2020-01-05",
            "2020-01-06",
            "2020-01-07",
            "2020-01-08",
            "2020-01-09",
            "2020-01-10",
            "2020-01-11",
            "2020-01-12",
            "2020-01-13",
            "2020-01-14",
            "2020-01-15",
            "2020-01-16",
            "2020-01-17",
            "2020-01-18",
            "2020-01-19",
            "2020-01-20",
            "2020-01-21",
            "2020-01-22",
            "2020-01-23",
            "2020-01-24",
            "2020-01-25",
            "2020-01-26",
            "2020-01-27",
            "2020-01-28",
            "2020-01-28",
            "2020-01-30",
            "2020-01-31"
          ],
          axisLabel: {
            rotate: 40,
            textStyle: {
              color: "#fff"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#4ea6ad", // 颜色
              width: 1 // 粗细
            }
          }
        }
      ],
      yAxis: [
        {
          type: "value",
          axisLabel: {
            textStyle: {
              color: "#4ea6ad"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#4ea6ad",
              width: 1 // 这里是为了突出显示加上的
            }
          }
        }
      ],
      series: [
        {
          name: "成都公司",
          type: "bar",
          stack: "广告",
          // 柱状图宽度设置
          barWidth: 15,
          barMaxWidth: 15,
          data: [
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121,
            309,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121
          ],
          color: "#84ccc9"
        },
        {
          name: "上海公司",
          type: "bar",
          stack: "广告",
          data: [
            220,
            182,
            191,
            234,
            290,
            330,
            310,
            220,
            340,
            121,
            309,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121
          ],
          color: "#dedd5f"
        },
        {
          name: "广州公司",
          type: "bar",
          stack: "广告",
          data: [
            150,
            232,
            201,
            154,
            190,
            330,
            410,
            220,
            340,
            121,
            309,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121
          ],
          color: "#e7885f"
        },
        {
          name: "北京公司",
          type: "bar",
          stack: "广告",
          data: [
            150,
            232,
            201,
            154,
            190,
            330,
            410,
            220,
            340,
            121,
            309,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121
          ],
          color: "#7171db"
        }
      ]
    };
    // 部门异常操作echarts
    const option3 = {
      animation: false,
      title: {
        text: "北京公司",
        subtext: "200人次",
        x: "center",
        y: "28%",
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

      legend: {
        // orient: 'horizontal',
        x: "center",
        top: "260",
        textStyle: {
          // 图例文字的样式
          color: "#ccc",
          fontSize: 12
        },
        itemWidth: 22,
        itemHeight: 14,
        itemGap: 20,
        icon: "rect",
        orient: "vertical",
        left: "8%",
        align: "left",
        height: 80
      },
      // 鼠标放上去有提示
      tooltip: {
        show: true,
        trigger: "item",
        formatter: "{b}：{c}个<br/>占比：({d}%)"
      },
      series: [
        {
          type: "pie",
          stillShowZeroSum: true,
          radius: ["60%", "41%"],
          center: ["50%", "35%"],
          // 饼图轮廓阴影
          itemStyle: {
            normal: {
              // 设置扇形的阴影
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
          data: [
            {
              value: 250,
              name: "研发部（50）",
              radius: [0, "50%"],
              hoverAnimation: false,
              itemStyle: {
                normal: {
                  color: "#7171db "
                }
              }
            },

            {
              value: 250,
              name: "制造部（50）",
              radius: [0, "50%"],
              hoverAnimation: false,
              itemStyle: {
                normal: {
                  color: "#dedd5f"
                }
              }
            },
            {
              value: 250,
              name: "销售部（50）",
              radius: [0, "50%"],
              hoverAnimation: false,
              itemStyle: {
                normal: {
                  color: "#e7885f"
                }
              }
            },
            {
              value: 250,
              name: "财务部（50）",
              radius: [0, "50%"],
              hoverAnimation: false,
              textStyle: {
                color: "red"
              },
              itemStyle: {
                normal: {
                  color: "#84ccc9"
                }
              }
            }
          ]
        }
      ]
    };
    const option4 = {
      animation: false,
      title: {
        text: "部 门 异 常 趋 势",
        left: "center",
        textStyle: {
          color: "white",
          fontWeight: 400
        }
      },
      tooltip: {
        trigger: "axis",
        axisPointer: {
          // 坐标轴指示器，坐标轴触发有效
          type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
        }
      },
      toolbox: {
        x: "92%",
        y: "20",
        feature: {
          magicType: {
            show: true,
            type: ["line", "bar"],
            iconStyle: {
              borderColor: "white"
            }
          },
          saveAsImage: {
            show: true,
            iconStyle: {
              borderColor: "white"
            },
            backgroundColor: "#164276"
          }
        }
      },
      grid: {
        left: "3%",
        right: "4%",
        bottom: "3%",
        containLabel: true
      },
      xAxis: [
        {
          type: "category",
          data: [
            "2020-01-01",
            "2020-01-02",
            "2020-01-03",
            "2020-01-04",
            "2020-01-05",
            "2020-01-06",
            "2020-01-07",
            "2020-01-08",
            "2020-01-09",
            "2020-01-10",
            "2020-01-11",
            "2020-01-12",
            "2020-01-13",
            "2020-01-14",
            "2020-01-15",
            "2020-01-16",
            "2020-01-17",
            "2020-01-18",
            "2020-01-19",
            "2020-01-20",
            "2020-01-21",
            "2020-01-22",
            "2020-01-23",
            "2020-01-24",
            "2020-01-25",
            "2020-01-26",
            "2020-01-27",
            "2020-01-28",
            "2020-01-28",
            "2020-01-30",
            "2020-01-31"
          ],
          axisLabel: {
            rotate: 40,
            textStyle: {
              color: "#fff"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#4ea6ad", // 颜色
              width: 1 // 粗细
            }
          }
        }
      ],
      yAxis: [
        {
          type: "value",
          axisLabel: {
            textStyle: {
              color: "#4ea6ad"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#4ea6ad",
              width: 1 // 这里是为了突出显示加上的
            }
          }
        }
      ],
      series: [
        {
          name: "成都公司",
          type: "bar",
          stack: "广告",
          // 柱状图宽度设置
          barWidth: 15,
          barMaxWidth: 15,
          data: [
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121,
            309,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121
          ],
          color: "#84ccc9"
        },
        {
          name: "上海公司",
          type: "bar",
          stack: "广告",
          data: [
            220,
            182,
            191,
            234,
            290,
            330,
            310,
            220,
            340,
            121,
            309,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121
          ],
          color: "#dedd5f"
        },
        {
          name: "广州公司",
          type: "bar",
          stack: "广告",
          data: [
            150,
            232,
            201,
            154,
            190,
            330,
            410,
            220,
            340,
            121,
            309,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121
          ],
          color: "#e7885f"
        },
        {
          name: "北京公司",
          type: "bar",
          stack: "广告",
          data: [
            150,
            232,
            201,
            154,
            190,
            330,
            410,
            220,
            340,
            121,
            309,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121,
            120,
            132,
            101,
            134,
            90,
            230,
            210,
            220,
            340,
            121
          ],
          color: "#7171db"
        }
      ]
    };
    // 个人异常操作echarts
    const option5 = {
      title: {
        // text: "北京公司",
        subtext: "200人次",
        x: "center",
        y: "28%",
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

      legend: {
        // orient: 'horizontal',
        x: "center",
        top: "260",
        textStyle: {
          // 图例文字的样式
          color: "#ccc",
          fontSize: 12
        },
        itemWidth: 22,
        itemHeight: 14,
        itemGap: 20,
        icon: "rect",
        orient: "vertical",
        left: "8%",
        align: "left",
        height: 80
      },
      // 鼠标放上去有提示
      tooltip: {
        show: true,
        trigger: "item",
        formatter: "{b}：{c}个<br/>占比：({d}%)"
      },
      series: [
        {
          type: "pie",
          stillShowZeroSum: true,
          radius: ["60%", "41%"],
          center: ["50%", "35%"],
          // 饼图轮廓阴影
          itemStyle: {
            normal: {
              // 设置扇形的阴影
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
          data: [
            {
              value: 250,
              name: "白彦斌（50）",
              radius: [0, "50%"],
              hoverAnimation: false,
              itemStyle: {
                normal: {
                  color: "#7171db"
                }
              }
            },

            {
              value: 250,
              name: "郭晓林（50）",
              radius: [0, "50%"],
              hoverAnimation: false,
              itemStyle: {
                normal: {
                  color: "#dedd5f"
                }
              }
            },
            {
              value: 250,
              name: "沈志国（50）",
              radius: [0, "50%"],
              hoverAnimation: false,
              itemStyle: {
                normal: {
                  color: "#e7885f"
                }
              }
            },
            {
              value: 250,
              name: "陈啸风（50）",
              radius: [0, "50%"],
              hoverAnimation: false,
              textStyle: {
                color: "red"
              },
              itemStyle: {
                normal: {
                  color: "#84ccc9"
                }
              }
            }
          ]
        }
      ]
    };
    const option6 = {
      color: ["#7171db", "#e7885f", "#dedd5f", "#84ccc9"],
      title: {
        text: "个 人 异 常 趋 势",
        left: "center",
        textStyle: {
          color: "white",
          fontWeight: 400
        }
      },

      legend: {
        data: ["白彦斌", "沈志国", "郭晓林", "陈啸风"],
        orient: "horizontal",
        left: "center",
        bottom: "0",
        textStyle: {
          // 图例文字的样式
          color: "#ccc",
          fontSize: 12
        },
        itemWidth: 22,
        itemHeight: 14,
        itemGap: 20,
        icon: "rect"
      },
      grid: {
        left: "3%",
        right: "4%",
        bottom: "10%",
        containLabel: true
      },
      toolbox: {
        x: "92%",
        y: "20",
        feature: {
          magicType: {
            show: true,
            type: ["line", "bar"],
            iconStyle: {
              borderColor: "white"
            }
          },
          saveAsImage: {
            show: true,
            iconStyle: {
              borderColor: "white"
            },
            backgroundColor: "#164276"
          }
        }
      },
      xAxis: {
        type: "category",
        boundaryGap: false,

        data: [
          "2019-01",
          "2019-02",
          "2019-03",
          "2019-04",
          "2019-05",
          "2019-06",
          "2019-07",
          "2019-08",
          "2019-09",
          "2019-10",
          "2019-11",
          "2019-12"
        ],
        axisLabel: {
          // X轴倾斜角度
          // rotate: 40,
          textStyle: {
            color: "#fff"
          }
        },
        axisLine: {
          lineStyle: {
            color: "#4ea6ad", // 颜色
            width: 1 // 粗细
          }
        },
        splitLine: {
          show: true,
          lineStyle: {
            color: ["#2b5687"],
            width: 1,
            type: "solid"
          }
        }
      },
      yAxis: {
        type: "value",
        axisLabel: {
          textStyle: {
            color: "#4ea6ad"
          }
        },
        axisLine: {
          lineStyle: {
            color: "#4ea6ad",
            width: 1 // 这里是为了突出显示加上的
          }
        },

        // 设置网格线
        splitLine: {
          show: true,
          lineStyle: {
            color: ["#2b5687"],
            width: 1,
            type: "solid"
          }
        }
      },
      series: [
        {
          name: "白彦斌",
          type: "line",
          stack: "总量",
          data: [50, 100, 50, 100, 50, 100, 50, 100, 50, 100, 50, 100],
          // 折线颜色
          lineStyle: {
            color: "#7171db" // 改变折线颜色
          }
          // 取消折点
        },
        {
          name: "沈志国",
          type: "line",
          stack: "总量",
          data: [50, 100, 50, 100, 50, 100, 50, 100, 50, 100, 50, 100],
          lineStyle: {
            color: "#e7885f" // 改变折线颜色
          }
          // 取消折点
        },
        {
          name: "郭晓林",
          type: "line",
          stack: "总量",
          data: [50, 100, 50, 100, 50, 100, 50, 100, 50, 100, 50, 100],
          lineStyle: {
            color: "#dedd5f" // 改变折线颜色
          }
          // 取消折点
        },
        {
          name: "陈啸风",
          type: "line",
          stack: "总量",
          data: [50, 100, 50, 100, 50, 100, 50, 100, 50, 100, 50, 100],
          lineStyle: {
            color: "#84ccc9" // 改变折线颜色
          }
          // 取消折点
        }
      ]
    };
    myChart1.setOption(option1);
    myChart2.setOption(option2);
    myChart3.setOption(option3);
    myChart4.setOption(option4);
    myChart5.setOption(option5);
    myChart6.setOption(option6);
    // --------------------------------------------------------------------------------------
  }
};
</script>

<style lang='scss' scoped>
@import "../../../../../css/clientSideMonitor/behaviorAnalysis/informationSecurity/components/trendAnalysis.css";
</style>
