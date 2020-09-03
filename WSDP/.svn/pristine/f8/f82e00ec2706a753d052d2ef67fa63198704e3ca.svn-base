<template>
  <!-- 趋势分析 -->
  <div class="trendAnalysis">
    <!-- 公司趋势分析搜索条件 -->
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
              :fetch-suggestions="queryCompany"
              @select="handleSelectScen"
              style="width: 150px;"
            ></el-autocomplete>
          </el-form-item>
        </div>

        <div class="seachButton">
          <el-form-item>
            <el-button type="primary" @click="ComSeachBtn">查找</el-button>
            <el-button @click="ComReset">重置</el-button>
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
    <!-- 公司异常趋势主体 -->
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

    <!-- 部门趋势分析搜索条件 -->
    <el-form :inline="true" :model="depForm" ref="form">
      <div class="depSeachAll">
        <!-- 搜索条件 -->
        <div class="searchCriteria">
          <el-form-item label="搜索条件">
            <el-autocomplete
              v-model="depForm.depSearchCrite"
              placeholder="所有部门"
              clearable
              type="text"
              :debounce="0"
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
              :fetch-suggestions="queryCompany"
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
              :fetch-suggestions="queryDepartment"
              @select="handleSelectScen"
            ></el-autocomplete>
          </el-form-item>
        </div>

        <div class="seachButton">
          <el-form-item>
            <el-button type="primary" @click="DepSeachBtn">查找</el-button>
            <el-button @click="DepReset">重置</el-button>
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

    <!-- 公司异常趋势主体 -->
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

    <!-- 个人趋势分析搜索条件 -->
    <el-form :inline="true" :model="perForm" ref="form">
      <div class="depSeachAll">
        <!-- 搜索条件 -->
        <div class="searchCriteria">
          <el-form-item label="搜索条件">
            <el-autocomplete
              v-model="perForm.perSearchCrite"
              placeholder="所有员工"
              clearable
              type="text"
              :debounce="0"
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
              :fetch-suggestions="queryCompany"
              @select="handleSelectScen"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <div class="searchDep">
          <!-- 部门选择 -->
          <el-form-item label="部门" class="depar2">
            <el-autocomplete
              v-model="perForm.perSearchDepartment"
              placeholder="第一事业部"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="queryDepartment"
              @select="handleSelectScen"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <div class="seachButton">
          <el-form-item>
            <el-button type="primary" @click="PerSeachBtn">查找</el-button>
            <el-button @click="PerReset">重置</el-button>
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

    <!-- 个人异常趋势主体 -->
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
  getPulldownListScene,
  // 公司模糊查询
  querySearchCompany,
  // 姓名模糊查询
  querySearchName,
  // 部门模糊查询
  queryDownListGroup,
  // 个人异常趋势
  personalAbnormality,
  // 部门异常趋势
  depAlAbnormality,
  // 公司异常趋势
  comAlAbnormality
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
      depNameArr: [],
      // 个人——表单
      perForm: {
        // 个人——搜索条件下拉菜单
        perSearchCrite: "",
        // 个人——公司下拉菜单
        perSearchCom: "",
        // 个人——部门下拉菜单
        perSearchDepartment: "",
        // 个人——开始时间
        perStartTime: "",
        // 个人——结束时间
        perEndTime: ""
      },
      perFatherData: [],
      perSonData: [],
      SonNameList: [],
      // 员工参数
      PerData3: [],
      PerLinData: [],
      // 部门参数
      DepData2: [],
      // 公司参数
      ComData1: [],
      form: {
        actionBeginTime1: "", // 开始日期
        actionEndTime1: "", // 结束日期
        actionBeginTime2: "", // 开始日期
        actionEndTime2: ""
      }
    };
  },
  created() {
    // 默认一个月的日期填充
    this.getSumTimes();
    // 员工刷新数据
    this.getPerDataSum();
    // 部门刷新数据
    this.getDepDataSum();
    // 公司刷新数据
    this.getComDataSum();
  },
  methods: {
    // 提取员工饼图
    getPerPie(arr, allCount) {
      let myChart5 = this.$echarts.init(document.getElementById("main9"));
      const option5 = {
        title: {
          // text: "北京公司",
          subtext: allCount + "次",
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
          left: "4%",
          align: "left",
          height: 80
        },
        // 鼠标放上去有提示
        tooltip: {
          show: true,
          trigger: "item"
          // formatter: "{b}：{c}个<br/>占比：({d}%)"
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
            data: arr
          }
        ]
      };
      myChart5.setOption(option5);
    },
    // 提取员工折线图
    getPerLin(sonArr3, dataNameList, PerData3) {
      let myChart6 = this.$echarts.init(document.getElementById("main10"));
      const option6 = {
        // 此处有BUG、这里的颜色数据要和公共颜色函数里的数据保持一致
        color: [
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
          "#d861f1",
          "#61e2f1",
          "#61f1c0",
          "#6ff161",
          "#c3f161",
          "#f1e261",
          "#f17661",
          "#f161db",
          "#6176f1",
          "#71758d"
        ],
        title: {
          text: "个 人 异 常 趋 势",
          left: "center",
          textStyle: {
            color: "white",
            fontWeight: 400
          }
        },

        legend: {
          data: dataNameList.slice(0,10),
          type: "scroll",
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
        // 鼠标放上去有提示
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        xAxis: {
          type: "category",
          boundaryGap: false,
          data: PerData3,
          axisLabel: {
            // X轴倾斜角度
            // rotate: 40,
            textStyle: {
              color: "#fff"
            },

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
        series: sonArr3
      };
      myChart6.setOption(option6, true);
    },
    // 员工刷新数据
    async getPerDataSum() {
      const value = {
        company: this.perForm.perSearchCom,
        employeeGroup: this.perForm.perSearchDepartment,
        actionBeginTime: this.perForm.perStartTime,
        actionEndTime: this.perForm.perEndTime
      };
      const { data } = await personalAbnormality(value);
      const { employeeNamePieChartList, allCount, nameList } = data;
      const sonNameList = nameList.map(item => {
        return item.children;
      });
      const dataList = [];
      const dataNameList = [];
      const PerformList = [];
      sonNameList.forEach(item1 => {
        dataNameList.push(item1[0].employeeName);
        item1.forEach(item2 => {
          dataList.push({
            employeeName: item2.employeeName,
            operaCount: item2.operaCount,
            zheXianX: item2.zheXianX
          });
        });
      });
      dataNameList.forEach((item, index) => {
        PerformList[index] = dataList.filter(son => {
          return son.employeeName === item;
        });
      });
      // 封装饼图
      const newArr = employeeNamePieChartList.map((item, index) => {
        const comColor = this.randColor(item, index);
        return {
          value: item.operaCount,
          name: item.employeeName,
          radius: [0, "50%"],
          hoverAnimation: false,
          textStyle: {
            color: comColor
          },
          itemStyle: {
            normal: {
              color: comColor
            }
          }
        };
      });
      // 调用饼图函数、传参
      this.getPerPie(newArr, allCount);

      // 封装折线图
      const sonArr3 = dataNameList.map((item, index) => {
        this.PerData3 = PerformList[index].map(item1 => {
          return item1.zheXianX;
        });
        const comColor = this.randColor(item, index);
        this.PerLinData = PerformList[index].map(item1 => {
          return item1.operaCount;
        });
        return {
          name: item,
          type: "line",
          stack: "",
          data: this.PerLinData,
          // 折线颜色
          lineStyle: {
            color: comColor // 改变折线颜色
          }
        };
      });
      // 调用折线图函数、传参
      this.getPerLin(sonArr3, dataNameList, this.PerData3);
    },
    // 员工趋势分析查询按钮
    PerSeachBtn() {
      const ttime1 = this.perForm.actionBeginTime;
      const ttime2 = this.perForm.actionEndTime;
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("请合理选择日期");
        return;
      }
      this.getPerDataSum(this.perForm);
      this.perForm.perSearchCrite =
        this.perForm.perSearchCom + " " + this.perForm.perSearchDepartment;
    },
    // 员工重置按钮
    PerReset() {
      this.perForm.perSearchCom = "";
      this.perForm.perSearchDepartment = "";
      this.perForm.perSearchCrite = "";
      this.perForm.perStartTime = this.Dtimes;
      this.perForm.perEndTime = this.times;
      this.getPerDataSum(this.perForm);
      // this.getSumTimes()
    },
    // ------------------------------------------------------------------------
    // 提取部门饼图
    getDepPie(arr, allCount) {
      let myChart3 = this.$echarts.init(document.getElementById("main7"));
      // 部门异常操作饼图
      const option3 = {
        animation: false,
        title: {
          // text: "北京公司",
          subtext: allCount + "个",
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
          left: "4%",
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
            data: arr
          }
        ]
      };
      myChart3.setOption(option3);
    },
    // 提取部门柱状图
    getDepLin(sonArr2, DepData2) {
      let myChart4 = this.$echarts.init(document.getElementById("main8"));
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
        legend: {
          top: "10%",
          left: "2%",
          orient: "vertical",
          data: ["操作次数", "最小响应时间", "平均响应时间"],
          textStyle: {
            color: "white",
            fontSize: 14
          },
          itemWidth: 12,
          itemHeight: 14
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
            data: DepData2,
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
        series: sonArr2
      };
      myChart4.setOption(option4, true);
    },
    // 部门刷新数据
    async getDepDataSum() {
      const value = {
        company: this.depForm.depSearchCom,
        employeeGroup: this.depForm.depSearchDepartment,
        actionBeginTime: this.depForm.depStartTime,
        actionEndTime: this.depForm.depEndTime
      };
      const { data } = await depAlAbnormality(value);
      const { employeeGroupPieChartList, allCount, nameList } = data;
      const sonNameList = nameList.map(item => {
        return item.children;
      });
      const GroupList = [];
      const dataGroupList = [];
      const GroupFormList = [];

      sonNameList.forEach(item1 => {
        dataGroupList.push(item1[0].employeeGroup);
        item1.forEach(item2 => {
          GroupList.push({
            employeeGroup: item2.employeeGroup,
            operaCount: item2.operaCount,
            zheXianX: item2.zheXianX
          });
        });
      });
      dataGroupList.forEach((item, index) => {
        GroupFormList[index] = GroupList.filter(son => {
          return son.employeeGroup === item;
        });
      });
      this.depNameArr = dataGroupList;
      // 封装饼图
      const newArr = employeeGroupPieChartList.map((item, index) => {
        const comColor = this.randColor(item, index);
        return {
          value: item.operaCount,
          name: item.employeeGroup,
          radius: [0, "50%"],
          hoverAnimation: false,
          itemStyle: {
            normal: {
              color: comColor
            }
          }
        };
      });
      // 调用饼图函数、传参
      this.getDepPie(newArr, allCount);
      // 封装部门柱状图
      const sonArr2 = dataGroupList.map((item, index) => {
        const comColor = this.randColor(item, index);
        this.DepData2 = GroupFormList[index].map(item1 => {
          return item1.zheXianX;
        });
        const operaCountList = GroupFormList[index].map(item1 => {
          return item1.operaCount;
        });
        return {
          name: item,
          type: "bar",
          stack: "广告",
          // 柱状图宽度设置
          barWidth: 15,
          barMaxWidth: 15,
          data: operaCountList,
          color: comColor
        };
      });
      // 调用部门柱状图函数、传参
      this.getDepLin(sonArr2, this.DepData2);
    },

    // ------------------------------------------------------------------------
    // 提取公司饼图
    getComPie(arr, allCount) {
      let myChart1 = this.$echarts.init(document.getElementById("main5"));
      const option1 = {
        animation: false,
        title: {
          // text: "信息安全事件",
          subtext: allCount + "个",
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
          itemGap: 40,
          icon: "rect",
          orient: "vertical",
          left: "4%",
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
            data: arr
          }
        ]
      };
      myChart1.setOption(option1);
    },
    // 提取公司柱状图
    getComBar(sonArr1, ComData1) {
      let myChart2 = this.$echarts.init(document.getElementById("main6"));
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
            data: ComData1,
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
        series: sonArr1
      };
      myChart2.setOption(option2, true);
    },
    // 公司刷新数据
    async getComDataSum() {
      const value = {
        company: this.comForm.comSearchCom,
        actionBeginTime: this.comForm.comStartTime,
        actionEndTime: this.comForm.comEndTime
      };
      const { data } = await comAlAbnormality(value);
      const { employeeCompanyPieChartList, allCount, nameList } = data;
      const sonNameList = nameList.map(item => {
        return item.children;
      });
      const ComList = [];
      const dataComList = [];
      const ComFormList = [];
      sonNameList.forEach(item1 => {
        dataComList.push(item1[0].company);
        item1.forEach(item2 => {
          ComList.push({
            company: item2.company,
            operaCount: item2.operaCount,
            zheXianX: item2.zheXianX
          });
        });
      });
      dataComList.forEach((item, index) => {
        ComFormList[index] = ComList.filter(son => {
          return son.company === item;
        });
      });

      // 封装饼图
      const newArr = employeeCompanyPieChartList.map((item, index) => {
        const comColor = this.randColor(item, index);
        return {
          value: item.operaCount,
          name: item.company,
          radius: [0, "50%"],
          hoverAnimation: false,
          itemStyle: {
            normal: {
              color: comColor
            }
          }
        };
      });

      // 调用公司饼图函数、传参
      this.getComPie(newArr, allCount);
      // 封装公司柱状图
      const sonArr1 = dataComList.map((item, index) => {
        const comColor = this.randColor(item, index);
        this.ComData1 = ComFormList[index].map(item1 => {
          return item1.zheXianX;
        });
        const operaCountList = ComFormList[index].map(item1 => {
          return item1.operaCount;
        });
        return {
          name: item,
          type: "bar",
          stack: "广告",
          // 柱状图宽度设置
          barWidth: 15,
          barMaxWidth: 15,
          data: operaCountList,
          color: comColor
        };
      });
      // 调用折线图函数、传参
      this.getComBar(sonArr1, this.ComData1);
    },

    // ------------------------------------------------------------------------
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
    queryCompany(queryString, callback) {
      // 需要随时更改的（从后台获取到对象数组）
      querySearchCompany({
        company: queryString
      }).then(res => {
        let list = [];
        if (res.data.fuzzyCompanyListList === null) {
          list = [];
        } else {
          res.data.fuzzyCompanyListList.forEach(item => {
            list.push({
              value: item
            });
          });
        }
        callback(list);
      });
    },
    // 部门模糊查询
    queryDepartment(queryString, callback) {
      // 需要随时更改的（从后台获取到对象数组）
      queryDownListGroup({
        employeeGroup: queryString
      }).then(res => {
        let list = [];
        if (res.data.fuzzyEmployeeGroupList === null) {
          list = [];
        } else {
          res.data.fuzzyEmployeeGroupList.forEach(item => {
            list.push({
              value: item
            });
          });
        }
        callback(list);
      });
    },
    // 姓名模糊查询
    queryPerName(queryString, callback) {
      // 需要随时更改的（从后台获取到对象数组）
      querySearchName({
        employeeName: queryString
      }).then(res => {
        let list = [];
        if (res.data.fuzzyEmployeeNameList === null) {
          list = [];
        } else {
          res.data.fuzzyEmployeeNameList.forEach(item => {
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
    },
    // 公司趋势分析查询按钮
    ComSeachBtn() {
      const ttime1 = this.comForm.actionBeginTime;
      const ttime2 = this.comForm.actionEndTime;
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("请合理选择日期");
        return;
      }
      this.getComDataSum(this.comForm);
      this.comForm.comSearchCrite = this.comForm.comSearchCom;
    },
    // 公司重置按钮
    ComReset() {
      this.comForm.comSearchCom = "";
      this.comForm.comSearchCrite = "";
      this.comForm.comStartTime = this.Dtimes;
      this.comForm.comEndTime = this.times;
      this.getComDataSum(this.comForm);
      // this.getSumTimes()
    },
    // 部门趋势分析查询按钮
    DepSeachBtn() {
      const ttime1 = this.depForm.actionBeginTime;
      const ttime2 = this.depForm.actionEndTime;
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("请合理选择日期");
        return;
      }
      this.getDepDataSum(this.depForm);
      this.depForm.depSearchCrite =
        this.depForm.depSearchCom + " " + this.depForm.depSearchDepartment;
    },
    // 部门重置按钮
    DepReset() {
      this.depForm.depSearchCom = "";
      this.depForm.depSearchDepartment = "";
      this.depForm.depSearchCrite = "";
      this.depForm.depStartTime = this.Dtimes;
      this.depForm.depEndTime = this.times;
      this.getDepDataSum(this.depForm);
    },

    // 随机颜色
    randColor(item, index) {
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
        "#d861f1",
        "#61e2f1",
        "#61f1c0",
        "#6ff161",
        "#c3f161",
        "#f1e261",
        "#f17661",
        "#f161db",
        "#6176f1",
        "#71758d"
      ];
      const color = Arr[index];
      return color;
    }
  },
  mounted() {
    // this.getPerLin();
  },
  computed: {}
};
</script>

<style lang='scss' scoped>
@import "../../../../../css/clientSideMonitor/behaviorAnalysis/informationSecurity/components/trendAnalysis.css";
</style>
