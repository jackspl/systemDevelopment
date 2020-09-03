<template>
  <!-- KPI趋势分析 -->
  <div class="kpiTrendAnalysis">
    <!-- 集团所有内容 -->
    <div class="groupSum">
      <!-- 集团搜索样式 -->
      <div class="groupKPI">
        <el-form :inline="true" :model="groupForm" ref="form">
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
      <!-- 集团折线图样式 -->
      <div class="line" id="ttt">
        <div id="groupLine" style="width: 100%; height: 370px; margin: 0 auto;"></div>
      </div>
    </div>

    <!-- 公司所有内容 -->
    <div class="compSum">
      <!-- 公司搜索样式 -->
      <div class="compKPI">
        <el-form :inline="true" :model="comForm" ref="form">
          <!-- 三个单选按钮 -->
          <div class="threeRadio">
            <el-radio-group v-model="radio1">
              <el-radio :label="1">公司</el-radio>
              <el-radio :label="2">部门</el-radio>
              <el-radio :label="3" class="personal">个人</el-radio>
            </el-radio-group>
          </div>
          <!-- 公司选择 -->
          <div class="comChange">
            <el-form-item label="公司">
              <el-autocomplete
                v-model="comForm.comCompany"
                :placeholder="comDefault"
                clearable
                type="text"
                :debounce="0"
                :fetch-suggestions="queryCompany"
                @select="handleSelectScen"
                style="width: 160px;"
              ></el-autocomplete>
            </el-form-item>
          </div>
          <!-- 三个checkbox选择按钮 -->
          <div class="threeCheck">
            <el-checkbox v-model="checked1" class="check1">净值</el-checkbox>
            <el-checkbox v-model="checked2">加分</el-checkbox>
            <el-checkbox v-model="checked3" class="check3">减分</el-checkbox>
          </div>
          <!-- 两个单选按钮 -->
          <div class="twoRadio">
            <el-radio-group v-model="radio2">
              <el-radio :label="1">总KPI</el-radio>
              <el-radio :label="2" class="radioTwo">平均KPI</el-radio>
            </el-radio-group>
          </div>
          <!-- 查询按钮 -->
          <div class="seachButton">
            <el-button type="primary" @click="comSeachBtn">查询</el-button>
          </div>
          <!-- 结束时间 -->
          <div class="endTime">
            <el-form-item label="结束日期" prop="comEndTime">
              <el-date-picker
                v-model="comForm.comEndTime"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
          <!-- 开始时间 -->
          <div class="startTime">
            <el-form-item label="开始日期" prop="comStartTime">
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
        </el-form>
      </div>
      <!-- 公司折线图样式 -->
      <div class="line" id="ttt">
        <div id="comLine" style="width: 100%; height: 370px; margin: 0 auto;"></div>
      </div>
    </div>

    <!-- 部门所有内容 -->
    <div class="depSum">
      <!-- 部门KPI搜索样式 -->
      <div class="depKPI">
        <el-form :inline="true" :model="depForm" ref="form">
          <!-- 三个单选按钮 -->
          <div class="threeRadio">
            <el-radio-group v-model="radio3">
              <el-radio :label="1">公司</el-radio>
              <el-radio :label="2">部门</el-radio>
              <el-radio :label="3" class="personal">个人</el-radio>
            </el-radio-group>
          </div>
          <!-- 公司选择 -->
          <div class="comChange">
            <el-form-item label="公司">
              <el-autocomplete
                v-model="depForm.depCompany"
                clearable
                type="text"
                :debounce="0"
                :fetch-suggestions="queryCompany"
                @select="handleSelectScen"
                style="width: 160px;"
              ></el-autocomplete>
            </el-form-item>
          </div>
          <!-- 部门选择 -->
          <div class="depChange">
            <el-form-item label="部门" style="margin-left:10px">
              <el-autocomplete
                v-model="depForm.depDepartment"
                :placeholder="depDefault"
                clearable
                type="text"
                :debounce="0"
                :fetch-suggestions="queryDepartment"
                @select="handleSelectScen"
                style="width: 160px;"
              ></el-autocomplete>
            </el-form-item>
          </div>
          <!-- 三个checkbox选择按钮 -->
          <div class="threeCheck">
            <el-checkbox v-model="checked1" class="check1">净值</el-checkbox>
            <el-checkbox v-model="checked2">加分</el-checkbox>
            <el-checkbox v-model="checked3" class="check3">减分</el-checkbox>
          </div>
          <!-- 两个单选按钮 -->
          <div class="twoRadio">
            <el-radio-group v-model="radio4">
              <el-radio :label="1">总KPI</el-radio>
              <el-radio :label="2" class="radioTwo">平均KPI</el-radio>
            </el-radio-group>
          </div>
          <!-- 查询按钮 -->
          <div class="seachButton">
            <el-button type="primary" @click="depSeachBtn">查询</el-button>
          </div>
          <!-- 结束时间 -->
          <div class="endTime">
            <el-form-item label="结束日期" prop="depEndTime">
              <el-date-picker
                v-model="depForm.depEndTime"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
          <!-- 开始时间 -->
          <div class="startTime">
            <el-form-item label="开始日期" prop="depStartTime">
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
        </el-form>
      </div>
      <!-- 部门KPI折线图样式 -->
      <div class="line" id="ttt">
        <div id="depLine" style="width: 100%; height: 370px; margin: 0 auto;"></div>
      </div>
    </div>

    <!-- 员工所有内容 -->
    <div class="userSum">
      <!-- 员工KPI搜索样式 -->
      <div class="userKPI">
        <el-form :inline="true" :model="empForm" ref="form">
          <!-- 三个单选按钮 -->
          <div class="threeRadio">
            <el-radio-group v-model="radio5">
              <el-radio :label="1">公司</el-radio>
              <el-radio :label="2">部门</el-radio>
              <el-radio :label="3" class="personal">个人</el-radio>
            </el-radio-group>
          </div>
          <!-- 公司选择 -->
          <div class="comChange">
            <el-form-item label="公司">
              <el-autocomplete
                v-model="empForm.empCompany"
                clearable
                type="text"
                :debounce="0"
                :fetch-suggestions="queryCompany"
                @select="handleSelectScen"
                style="width: 110px;"
              ></el-autocomplete>
            </el-form-item>
          </div>
          <!-- 部门选择 -->
          <div class="depChange">
            <el-form-item label="部门" style="margin-left:10px">
              <el-autocomplete
                v-model="empForm.empDepartment"
                clearable
                type="text"
                :debounce="0"
                :fetch-suggestions="queryDepartment"
                @select="handleSelectScen"
                style="width: 110px;"
              ></el-autocomplete>
            </el-form-item>
          </div>
          <!-- 员工选择 -->
          <div class="userChange">
            <el-form-item label="用户" style="margin-left: 10px;">
              <el-autocomplete
                v-model="empForm.empName"
                :placeholder="infoDefault"
                clearable
                type="text"
                :debounce="0"
                :fetch-suggestions="querySearchName"
                @select="handleSelectScen"
                style="width: 110px;"
              ></el-autocomplete>
            </el-form-item>
          </div>
          <!-- 三个checkbox选择按钮 -->
          <div class="threeCheck">
            <el-checkbox v-model="checked1" class="check1">净值</el-checkbox>
            <el-checkbox v-model="checked2">加分</el-checkbox>
            <el-checkbox v-model="checked3" class="check3">减分</el-checkbox>
          </div>
          <!-- 两个单选按钮 -->
          <div class="twoRadio">
            <el-radio-group v-model="radio6">
              <el-radio :label="1">总KPI</el-radio>
              <el-radio :label="2" class="radioTwo">平均KPI</el-radio>
            </el-radio-group>
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
      <!-- 员工KPI折线图样式 -->
      <div class="line" id="ttt">
        <div id="mepLine" style="width: 100%; height: 370px; margin: 0 auto;"></div>
      </div>
    </div>
  </div>
</template>

<script>
import {
  getPLMLogin,
  // 员工折线图
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
      // 公司默认值
      comDefault: '',
      // 部门默认值
      depDefault: '',
      // 员工默认值
      infoDefault: '',
      // 集团表单数据
      groupForm: {
        groupStartTime: "",
        groupEndTime: ""
      },
      // 公司KPI排名表单数据
      comForm: {
        comCompany: "", // 公司公司
        comStartTime: "", // 公司开始时间
        comEndTime: "" // 公司结束时间
      },
      // 部门KPI排名表单数据
      depForm: {
        // 部门KPI排名数据
        depCompany: "", // 部门公司
        depDepartment: "", // 部门部门
        depStartTime: "", // 部门开始时间
        depEndTime: "" // 部门结束时间
      },
      // 员工KPI排名表单数据
      empForm: {
        // 员工KPI排名数据
        empCompany: "", // 员工公司
        empDepartment: "", // 员工部门
        empName: "", // 员工姓名
        empStartTime: "", // 员工开始时间
        empEndTime: "" // 员工结束时间
      },
      checked1: true,
      checked2: false,
      checked3: false,
      checked4: false,
      radio1: 1,
      radio2: 1,
      radio3: 1,
      radio4: 1,
      radio5: 1,
      radio6: 1,
      // 动态修用户提示时间
      nowData: "",
      // 折线图数据
      zheXianFlag: 3,
      zheXianDate: "",
      oldDay: "",
      newDay: "",
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
  created() {
    // 默认一个月的日期填充
    this.getSumTimes();
  },
  methods: {

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
        this.comDefault=list[0].value
        console.log('测试',this.comDefault)
        callback(list);
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
        this.depDefault=list[0].value
        callback(list);
      });
    },
    // 员工模糊查询 (第一个参数， 用户输入的值  第二个值回调函数)
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
        this.infoDefault=list[0].value
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
      this.comForm.comEndTime = this.times;
      this.comForm.comStartTime = this.Dtimes;
      this.depForm.depEndTime = this.times;
      this.depForm.depStartTime = this.Dtimes;
      this.empForm.empEndTime = this.times;
      this.empForm.empStartTime = this.Dtimes;
      // 表头下面的默认时间
      this.oldDay = this.Dtimes;
      this.newDay = this.times;
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

    // 集团折线图函数数据
    async getGroupList(callback) {
      const value = {
        actionBeginTime: this.groupForm.groupStartTime,
        actionEndTime: this.groupForm.groupEndTime
      };
      const { data } = await getEmpLine(value);
      const { dateEntityList } = data;
      if (!dateEntityList.length) {
        this.groupInitItem();
        return;
      }
      const AllXTimes = dateEntityList.map(item => {
        return item.zheXianX;
      });
      const AllYNums = dateEntityList.map(item => {
        return item.KPI;
      });
      this.groupInitItem(AllXTimes, AllYNums);
    },
    // 集团kpl趋势折线图
    groupInitItem(AllXTimes = "", AllYNums = "") {
      // 集团的图表
      this.initWidth = document.getElementById("ttt").offsetWidth;
      let myChart1 = this.$echarts.init(document.getElementById("groupLine"));
      const option1 = {
        title: {
          text: "集团KPI趋势",
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
          data: ["净值", "加分", "减分"],
          icon: "rect",
          itemWidth: 16, // 设置宽度
          itemHeight: 10, // 设置高度
          itemGap: 20, // 设置间距
          top: "9%",
          left: "45%",
          textStyle: {
            color: "white",
            fontSize: 12
          }
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
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
            name: "净值",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllXTimes,
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
            name: "加分",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllYNums,

            lineStyle: {
              color: "#8fc32b",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#8fc32b",
              color: "#8fc32b"
            }
          },
          {
            name: "减分",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllYNums,
            lineStyle: {
              color: "#f39800",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#f39800",
              color: "#f39800"
            }
          }
        ]
      };
      myChart1.setOption(option1);
      let resize4 = {
        width: this.initWidth,
        height: 370
      };
      myChart1.resize(resize4);
    },
    // 集团查询按钮
    async groupSeachButton() {
      // 首先验证，在执行回车事件
      // this.$refs.form.validate();
      const ttime1 = this.groupForm.groupStartTime;
      const ttime2 = this.groupForm.groupEndTime;
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("开始时间应小于结束时间");
        return;
      }
      if (timeStr2 > timeStr1 + 2592000000) {
        this.$message.error("时间区间为一个月");
        return;
      }
      this.pagesNum = 1;
      await this.$refs.form.validate(isValid => {
        if (isValid) {
          this.getGroupList();
        } else {
        }
      });
    },
    // ----------------------------------------------------------

    // 公司折线图函数数据
    async getComList(callback) {
      const value = {
        company: this.comDefault,
        actionBeginTime: this.comForm.comStartTime,
        actionEndTime: this.comForm.comEndTime,
      };
      const { data } = await getEmpLine(value);
      const { dateEntityList } = data;
      if (!dateEntityList.length) {
        this.comInitItem();
        return;
      }
      const AllXTimes = dateEntityList.map(item => {
        return item.zheXianX;
      });
      const AllYNums = dateEntityList.map(item => {
        return item.KPI;
      });
      this.comInitItem(AllXTimes, AllYNums);

    },
    // 公司kpl趋势折线图
    comInitItem(AllXTimes = "", AllYNums = "") {
      // 集团的图表
      this.initWidth = document.getElementById("ttt").offsetWidth;
      let myChart2 = this.$echarts.init(document.getElementById("comLine"));
      const option2 = {
        title: {
          text: "公司KPI趋势",
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
          data: ["净值", "加分", "减分"],
          icon: "rect",
          itemWidth: 16, // 设置宽度
          itemHeight: 10, // 设置高度
          itemGap: 20, // 设置间距
          top: "9%",
          left: "45%",
          textStyle: {
            color: "white",
            fontSize: 12
          }
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
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
            name: "净值",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllXTimes,
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
            name: "加分",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllYNums,

            lineStyle: {
              color: "#8fc32b",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#8fc32b",
              color: "#8fc32b"
            }
          },
          {
            name: "减分",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllYNums,
            lineStyle: {
              color: "#f39800",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#f39800",
              color: "#f39800"
            }
          }
        ]
      };
      myChart2.setOption(option2);
      let resize4 = {
        width: this.initWidth,
        height: 370
      };
      myChart2.resize(resize4);
    },
    // 公司查询按钮
    async comSeachBtn() {
      // 首先验证，在执行回车事件
      const ttime1 = this.comForm.comStartTime;
      const ttime2 = this.comForm.comEndTime;
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("开始时间应小于结束时间");
        return;
      }
      if (timeStr2 > timeStr1 + 2592000000) {
        this.$message.error("时间区间为一个月");
        return;
      }
      this.pagesNum = 1;
      await this.$refs.form.validate(isValid => {
        if (isValid) {
          this.getComList();
        } else {
        }
      });
    },
    // -------------------------------------------------------------

    // 部门折线图函数数据
    async getDepList (callback) {
      const value = {
        company: this.depForm.depCompany,
        employeeGroup: this.depForm.depDepartment,
        actionBeginTime: this.depForm.depStartTime,
        actionEndTime: this.depForm.depEndTime
      };
      const { data } = await getEmpLine(value);
      const { dateEntityList } = data;
      if (!dateEntityList.length) {
        this.depInitItem();
        return;
      }
      const AllXTimes = dateEntityList.map(item => {
        return item.zheXianX;
      });
      const AllYNums = dateEntityList.map(item => {
        return item.KPI;
      });
      this.depInitItem(AllXTimes, AllYNums);
    },
    // 部门kpl趋势折线图
    depInitItem(AllXTimes = "", AllYNums = "") {
      // 集团的图表
      this.initWidth = document.getElementById("ttt").offsetWidth;
      let myChart3 = this.$echarts.init(document.getElementById("depLine"));
      const option3 = {
        title: {
          text: "部门KPI趋势",
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
          data: ["净值", "加分", "减分"],
          icon: "rect",
          itemWidth: 16, // 设置宽度
          itemHeight: 10, // 设置高度
          itemGap: 20, // 设置间距
          top: "9%",
          left: "45%",
          textStyle: {
            color: "white",
            fontSize: 12
          }
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
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
            name: "净值",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllXTimes,
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
            name: "加分",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllYNums,

            lineStyle: {
              color: "#8fc32b",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#8fc32b",
              color: "#8fc32b"
            }
          },
          {
            name: "减分",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllYNums,
            lineStyle: {
              color: "#f39800",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#f39800",
              color: "#f39800"
            }
          }
        ]
      };
      myChart3.setOption(option3);
      let resize4 = {
        width: this.initWidth,
        height: 370
      };
      myChart3.resize(resize4);
    },
    // 员工查询按钮
    async depSeachBtn() {
      // 首先验证，在执行回车事件
      // this.$refs.form.validate();
      const ttime1 = this.depForm.depStartTime;
      const ttime2 = this.depForm.depEndTime;
      // var timestamp=new Date().getTime();
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("开始时间应小于结束时间");
        return;
      }
      if (timeStr2 > timeStr1 + 2592000000) {
        this.$message.error("时间区间为一个月");
        return;
      }
      this.pagesNum = 1;
      await this.$refs.form.validate(isValid => {
        if (isValid) {
          this.getDepList();
        } else {
        }
      });
    },
    // ---------------------------------------

    // 员工折线图函数数据
    async getInfoEmpList(callback) {
      const value = {
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        employeeName: this.empForm.empName,
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime
      };
      const { data } = await getEmpLine(value);
      const { dateEntityList } = data;
      if (!dateEntityList.length) {
        this.mepInitItem();
        return;
      }
      const AllXTimes = dateEntityList.map(item => {
        return item.zheXianX;
      });
      const AllYNums = dateEntityList.map(item => {
        return item.KPI;
      });
      this.mepInitItem(AllXTimes, AllYNums);
    },
    // 员工kpl趋势折线图
    mepInitItem(AllXTimes = "", AllYNums = "") {
      // 集团的图表
      this.initWidth = document.getElementById("ttt").offsetWidth;
      let myChart4 = this.$echarts.init(document.getElementById("mepLine"));
      const option4 = {
        title: {
          text: "员工KPI趋势",
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
          data: ["净值", "加分", "减分"],
          icon: "rect",
          itemWidth: 16, // 设置宽度
          itemHeight: 10, // 设置高度
          itemGap: 20, // 设置间距
          top: "9%",
          left: "45%",
          textStyle: {
            color: "white",
            fontSize: 12
          }
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
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
            name: "净值",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllXTimes,
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
            name: "加分",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllYNums,

            lineStyle: {
              color: "#8fc32b",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#8fc32b",
              color: "#8fc32b"
            }
          },
          {
            name: "减分",
            type: "line",
            symbol: "none", // 这句就是去掉点的
            // smooth: true, // 这句就是让曲线变平滑的
            data: AllYNums,
            lineStyle: {
              color: "#f39800",
              width: 1
            },
            itemStyle: {
              borderWidth: 1,
              borderColor: "#f39800",
              color: "#f39800"
            }
          }
        ]
      };
      myChart4.setOption(option4);
      let resize4 = {
        width: this.initWidth,
        height: 370
      };
      myChart4.resize(resize4);
    },
    // 员工查询按钮
    async EmpseachBtn() {
      // 首先验证，在执行回车事件
      // this.$refs.form.validate();
      const ttime1 = this.empForm.empStartTime;
      const ttime2 = this.empForm.empEndTime;
      // var timestamp=new Date().getTime();
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("开始时间应小于结束时间");
        return;
      }
      if (timeStr2 > timeStr1 + 2592000000) {
        this.$message.error("时间区间为一个月");
        return;
      }
      this.pagesNum = 1;
      await this.$refs.form.validate(isValid => {
        if (isValid) {
          this.getInfoEmpList();
        } else {
        }
      });
    }
    // --------------------------------------
  },
  destroyed() {},
  mounted() {
    this.queryCompany()
    this.queryDepartment()
    this.querySearchName()
    // 回调函数渲染折线图
    this.getGroupList();
    this.getComList();
    this.getDepList();
    this.getInfoEmpList();
  }
};
</script>

<style lang="scss" scoped>
@import "../../../../../css/base/base.css";
@import "../../../../../css/clientSideMonitor/behaviorAnalysis/kpiAnalysis/components/kpiTrendAnalysis.css";
</style>
