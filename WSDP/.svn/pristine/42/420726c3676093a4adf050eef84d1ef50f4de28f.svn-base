
<template>
  <!-- KPI趋势分析 -->
  <div class="trendAnalysisUnit">
    <!-- 服务状态所有内容 -->
    <div class="groupSum">
      <!-- 服务状态搜索样式 -->
      <div class="groupKPI">
        <el-form :inline="true" :model="groupForm" ref="form">
          <!-- 类型选择 -->
          <div class="typeChange">
            <el-form-item label="类型">
              <el-select v-model="groupForm.groupType" placeholder="正常、警告、紧急">
                <el-option label="正常" value="正常"></el-option>
                <el-option label="警告" value="警告"></el-option>
                <el-option label="紧急" value="紧急"></el-option>
              </el-select>
            </el-form-item>
          </div>
          <!-- 查询按钮 -->
          <div class="seachButton">
            <el-button type="primary" @click="groupSeachButton">查询</el-button>
          </div>
          <!-- 结束时间 -->
          <div class="endTime">
            <el-form-item label="结束日期" prop="groupEndTime">
              <el-date-picker
                v-model="groupForm.groupEndTime"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
          <!-- 开始时间 -->
          <div class="startTime">
            <el-form-item label="开始日期" prop="groupStartTime">
              <el-date-picker
                v-model="groupForm.groupStartTime"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                align="right"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
        </el-form>
      </div>
      <!-- 服务状态折线图样式 -->
      <div class="line" id="ttt">
        <div id="groupLine" style="width: 100%; height: 400px; margin: 0 auto;"></div>
      </div>
    </div>

    <!-- 服务问题所有内容 -->
    <div class="userSum">
      <!-- 服务问题KPI搜索样式 -->
      <div class="userKPI">
        <el-form :inline="true" :model="empForm" ref="form">
          <!-- 类型选择 -->
          <div class="comChange">
            <el-form-item label="类型">
              <el-select v-model="empForm.empCompany" placeholder="正常、警告、紧急">
                <el-option label="正常" value="正常"></el-option>
                <el-option label="警告" value="警告"></el-option>
                <el-option label="紧急" value="紧急"></el-option>
              </el-select>
              <!-- <el-autocomplete
                v-model="empForm.empCompany"
                clearable
                placeholder="正常、警告、紧急"
                type="text"
                :debounce="0"
                :fetch-suggestions="queryCompany"
                @select="handleSelectScen"
                style="width: 110px;"
              ></el-autocomplete>-->
            </el-form-item>
          </div>

          <!-- 查询按钮 -->
          <div class="seachButton">
            <el-button type="primary" @click="EmpseachBtn">查询</el-button>
          </div>
          <!-- 结束时间 -->
          <div class="endTime">
            <el-form-item label="结束日期" prop="empEndTime">
              <el-date-picker
                v-model="empForm.empEndTime"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
          <!-- 开始时间 -->
          <div class="startTime">
            <el-form-item label="开始日期" prop="empStartTime">
              <el-date-picker
                v-model="empForm.empStartTime"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                align="right"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
        </el-form>
      </div>
      <!-- 服务问题KPI折线图样式 -->
      <div class="line" id="ttt">
        <div id="mepLine" style="width: 100%; height: 400px; margin: 0 auto;"></div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getPLMLogin,
  // 服务问题折线图
  getEmpLine,
  // 公司模糊查询
  querySearchCompany,
  // 姓名模糊查询
  querySearchName,
  // 部门模糊查询
  queryDownListGroup
} from "@/api/table.js";
export default {
  data() {
    return {
      queryString: "",
      comQuerList: [],
      // 公司默认值
      comDefault: "",
      // 部门默认值
      depDefault: "",
      // 服务问题默认值
      infoDefault: "",
      // 服务状态表单数据
      groupForm: {
        groupType: "",
        groupStartTime: "",
        groupEndTime: ""
      },

      // 服务问题KPI排名表单数据
      empForm: {
        // 服务问题KPI排名数据
        empCompany: "", // 服务问题公司
        empDepartment: "", // 服务问题部门
        empName: "", // 服务问题姓名
        empStartTime: "", // 服务问题开始时间
        empEndTime: "" // 服务问题结束时间
      },

      // 动态修用户提示时间
      nowData: "",
      // 折线图数据
      zheXianFlag: 3,
      zheXianDate: "",
      // 表单里的数据
      form: {
        // 下拉菜单选择
        region1: "",
        region2: "",
        region3: "",
        region4: "",
        region5: "",
        actionBeginTime1: "", // 开始日期
        actionEndTime1: "", // 结束日期
        actionBeginTime2: "", // 开始日期
        actionEndTime2: "", // 结束日期
        actionBeginTime3: "", // 开始日期
        actionEndTime3: "" // 结束日期
      }
    };
  },
  async created() {
    // 默认一个月的日期填充
    this.getSumTimes();
  },
  methods: {
    // 服务问题总KPI
    empSumKpi() {
      this.getInfoEmpList(0);
    },
    // 服务问题平均KPI
    empAvgKpi() {
      this.getInfoEmpList(1);
    },

    // 公司模糊查询 (第一个参数， 用户输入的值  第二个值回调函数)
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
        this.comDefault = list[0].value;
        this.comQuerList = list[0].value;
        callback(list);
        // if (queryString=='') {
        //   list=[{value:res.data.fuzzyCompanyListList[0]}]
        // }
        //  if (!!callback) {
        //    console.log(list)
        //    callback(list);
        //  }
      });
    },
    // 部门模糊查询 (第一个参数， 用户输入的值  第二个值回调函数)
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

        this.depDefault = list[0].value;
        callback(list);
      });
    },
    // 服务问题模糊查询 (第一个参数， 用户输入的值  第二个值回调函数)
    querySearchName(queryString, callback) {
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
        this.infoDefault = list[0].value;
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
      // 查询里的默认时间
      this.form.actionEndTime1 = this.times;
      this.form.actionBeginTime1 = this.Dtimes;

      this.groupForm.groupEndTime = this.times;
      this.groupForm.groupStartTime = this.Dtimes;
      this.empForm.empEndTime = this.times;
      this.empForm.empStartTime = this.Dtimes;
    },
    // 用户提示时间
    Data() {
      this.nowData = new Date();
      const d = this.nowData;
      let month =
        d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
      let day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
      let times = d.getFullYear() + "-" + month + "-" + day;
      this.nowData = times;
      this.zheXianDate = times;
    },

    // ----------------------------------------------------------
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
      this.initWidth = document.getElementById("ttt").offsetWidth;
      let myChart1 = this.$echarts.init(document.getElementById("groupLine"));
      const option1 = {
        title: {
          text: "服务状态趋势",
          left: "50%",
          top: "1%",
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
          data: ["正常", "警告", "未知","紧急","问题","待定","未处理"],
          icon: "circle",
          itemWidth: 16, // 设置宽度
          itemHeight: 16, // 设置高度
          itemGap: 20, // 设置间距
          bottom: "0",
          left: "40%",
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
            name: "正常",
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
            name: "警告",
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
            name: "未知",
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
            name: "紧急",
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
          },
          {
            name: "问题",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: groupDemerit,
            lineStyle: {
              color: "#601986",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#601986",
              color: "#601986"
            }
          },
          {
            name: "待定",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: groupDemerit,
            lineStyle: {
              color: "#00b7ee",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#00b7ee",
              color: "#00b7ee"
            }
          },
          {
            name: "未处理",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: groupDemerit,
            lineStyle: {
              color: "#ffb255",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#ffb255",
              color: "#ffb255"
            }
          }
        ]
      };
      myChart1.setOption(option1);
      let resize4 = {
        width: this.initWidth,
        height: 400
      };
      myChart1.resize(resize4);
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
    // ---------------------------------------
    // 提取服务问题折线图数据
    async getInfoEmpList(index = 0) {
      const value = {
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        employeeName: this.empForm.empName,
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        avgKPIFlag: index
      };
      const { data } = await getEmpLine(value);
      const { dateEntityList } = data;
      if (!dateEntityList.length) {
        this.mepInitItem();
        return;
      }
      // 时间
      const AllXTimes = dateEntityList.map(item => {
        return item.zheXianX;
      });
      // 服务问题净值
      const empNetWorth = dateEntityList.map(item => {
        return item.KPI;
      });
      // 服务问题加分
      const empBonusPoints = dateEntityList.map(item => {
        return item.KPIjiafen;
      });
      // 服务问题减分
      const empDemerit = dateEntityList.map(item => {
        return item.KPIjianfen;
      });
      this.mepInitItem(AllXTimes, empNetWorth, empBonusPoints, empDemerit);
    },
    // 填充服务问题kpl趋势折线图
    mepInitItem(
      AllXTimes = "",
      empNetWorth = "",
      empBonusPoints = "",
      empDemerit = ""
    ) {
      this.initWidth = document.getElementById("ttt").offsetWidth;
      let myChart4 = this.$echarts.init(document.getElementById("mepLine"));
      const option4 = {
        title: {
          text: "服务问题处理率趋势",
          left: "50%",
          top: "1%",
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
          data: ["正常", "警告", "未知","紧急","问题","待定","未处理"],
          icon: "circle",
          itemWidth: 16, // 设置宽度
          itemHeight: 16, // 设置高度
          itemGap: 20, // 设置间距
          bottom: "0",
          left: "40%",
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
          onZero: true,
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
            name: "正常",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: empNetWorth,
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
            name: "警告",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: empBonusPoints,

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
            name: "未知",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: empDemerit,
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
            name: "紧急",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: empDemerit,
            lineStyle: {
              color: "#ff0000",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#ff0000",
              color: "#ff0000"
            }
          },
          {
            name: "问题",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: empDemerit,
            lineStyle: {
              color: "#601986",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#601986",
              color: "#601986"
            }
          },
          {
            name: "待定",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: empDemerit,
            lineStyle: {
              color: "#00b7ee",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#00b7ee",
              color: "#00b7ee"
            }
          },
          {
            name: "未处理",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: empDemerit,
            lineStyle: {
              color: "#ffb255",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#ffb255",
              color: "#ffb255"
            }
          }
        ]
      };
      myChart4.setOption(option4);
      let resize4 = {
        width: this.initWidth,
        height: 400
      };
      myChart4.resize(resize4);
    },
    // 服务问题查询按钮
    async EmpseachBtn() {
      const ttime1 = this.empForm.empStartTime;
      const ttime2 = this.empForm.empEndTime;
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
          this.getInfoEmpList();
        } else {
        }
      });
    }
  },
  destroyed() {},
  mounted() {
    // 服务问题
    this.getInfoEmpList();
    // 服务状态
    this.getGroupList();
  }
};
</script>
<style lang="scss" scoped>
</style>
