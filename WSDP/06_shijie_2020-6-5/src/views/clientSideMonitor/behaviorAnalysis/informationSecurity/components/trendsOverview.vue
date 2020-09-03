<template>
  <!-- 趋势总览 -->
  <div class="trendsOverview">
    <!-- 搜索查询 -->
    <el-form :inline="true" :model="form" ref="form">
      <div class="seachAll clearfix">
        <!-- 查询 -->
        <div class="seach">
          <el-form-item>
            <el-button type="primary" @click="seachBtn">查找</el-button>
            <el-button>重置</el-button>
          </el-form-item>
        </div>

        <!-- 结束日期 -->
        <div class="endTime">
          <el-form-item label="结束日期" prop="actionEndTime1">
            <el-date-picker
              v-model="form.actionEndTime1"
              type="datetime"
              format="yyyy-MM-dd HH:mm:ss"
              value-format="yyyy-MM-dd HH:mm:ss"
              clearable
            ></el-date-picker>
          </el-form-item>
        </div>
        <!-- 开始日期 -->
        <div class="starTime">
          <el-form-item label="开始日期" prop="actionBeginTime1">
            <el-date-picker
              v-model="form.actionBeginTime1"
              type="datetime"
              format="yyyy-MM-dd HH:mm:ss"
              value-format="yyyy-MM-dd HH:mm:ss"
              align="right"
              clearable
            ></el-date-picker>
          </el-form-item>
        </div>
      </div>
    </el-form>

    <!-- 主体一 -->
    <el-row>
      <el-col :span="4">
        <div class="grid-content infoLeft">
          <div id="card1">
            <div id="main1" style="width: 100%; top: -10px !important"></div>
          </div>
        </div>
      </el-col>
      <el-col :span="20" id="card2">
        <div class="grid-content infoRight">
          <div id="main2" style="width: 100%;"></div>
        </div>
      </el-col>
    </el-row>
    <!-- 主体二 -->
    <el-row class="mbotton">
      <el-col :span="6" class="clearfix">
        <div class="grid-content subject1">
          <div class="tit">异常操作公司排名</div>
          <div class="tip">（4个公司违规）</div>
        </div>
      </el-col>
      <el-col :span="9">
        <div class="grid-content subject1">
          <div class="tit">异常操作部门排名</div>
          <div class="tip">（10个公司违规）</div>
        </div>
      </el-col>
      <el-col :span="9">
        <div class="grid-content subject1">
          <div class="tit">异常操作个人排名</div>
          <div class="tip">（10个公司违规）</div>
        </div>
      </el-col>
    </el-row>

    <el-row class="abnPage">
      <!-- 异常操作公司排名 -->
      <el-col :span="6">
        <div class="grid-content subject2">
          <div class="comTop clearfix">
            <ul>
              <li class="comTop0"></li>
              <li class="comTop1">公司</li>
              <li class="comTop2">比例</li>
              <li class="comTop3">次数</li>
            </ul>
          </div>
          <!-- <el-table :data="abnormalComList" class="tableBox" :border="false" te> -->
          <el-table
            :data="
              abnormalComList.slice(
                (pagesNum - 1) * pageSize,
                pagesNum * pageSize
              )
            "
            :show-header="false"
            style="width: 100%"
          >
            <el-table-column prop="date" width="35px" align="center"></el-table-column>
            <el-table-column prop="company" width="80px" label="公司" align="center"></el-table-column>
            <el-table-column label="比例" width="230px" prop="upNum" align="center">
              <!-- 循环遍历显示百分百数值 -->
              <template slot-scope="{ row }">
                <div class="colorBoxs clearfix">
                  <div
                    :style="{ width: row.skylBlue }"
                    style="height:20px; float:left; backgroundColor:#84ccc9"
                  ></div>
                  <div
                    :style="{ width: row.yellow }"
                    style="height:20px; float:left; backgroundColor:#dedd5f"
                  ></div>
                  <div
                    :style="{ width: row.orange }"
                    style="height:20px; float:left; backgroundColor:#e7885f"
                  ></div>
                  <div
                    :style="{ width: row.purple }"
                    style="height:20px; float:left; backgroundColor:#7171db"
                  ></div>
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="address" label="次数" align="center" width="55px">
              <template slot-scope="{ row }">
                <div v-if="row.downNum > 0">
                  <p class="sequentialNum">{{ row.downNum }}</p>
                </div>
                <div v-else>
                  <p class="sequentialNumRed">{{ row.downNum }}</p>
                </div>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            class="comPag"
            @current-change="comCurrentChange"
            :current-page="pagesNum"
            :page-sizes="[25, 50, 100]"
            :page-size="pageSize"
            :total="abnormalComList.length"
            small
            layout="total,prev, pager, next"
          ></el-pagination>
        </div>
      </el-col>
      <!-- 异常操作部门排名 -->
      <el-col :span="9">
        <div class="grid-content subject2">
          <div class="comTop clearfix">
            <ul>
              <li class="comTop0"></li>
              <li class="comTop1">部门</li>
              <li class="comTop2">比例</li>
              <li class="comTop3">次数</li>
            </ul>
          </div>
          <!-- <el-table
            :data="departmentViolatList"
            class="tableBox"
            :border="false"
            te
          >-->
          <el-table
            :data="
              departmentViolatList.slice(
                (deppagesNum - 1) * deppageSize,
                deppagesNum * deppageSize
              )
            "
            :show-header="false"
            style="width: 100%"
          >
            <el-table-column prop="date" width="40px" align="center"></el-table-column>
            <!-- <el-table-column prop="company" width="80px" label="姓名" align="center"></el-table-column> -->
            <el-table-column prop="company" width="130px" label="部门" align="center"></el-table-column>
            <el-table-column label="比例" width="380px" prop="upNum" align="center">
              <!-- 循环遍历显示百分百数值 -->
              <template slot-scope="{ row }">
                <div class="colorBoxs clearfix">
                  <div
                    :style="{ width: row.skylBlue }"
                    style="height:20px; float:left; backgroundColor:#84ccc9"
                  ></div>
                  <div
                    :style="{ width: row.yellow }"
                    style="height:20px; float:left; backgroundColor:#dedd5f"
                  ></div>
                  <div
                    :style="{ width: row.orange }"
                    style="height:20px; float:left; backgroundColor:#e7885f"
                  ></div>
                  <div
                    :style="{ width: row.purple }"
                    style="height:20px; float:left; backgroundColor:#7171db"
                  ></div>
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="address" label="次数" align="center" width="55px">
              <template slot-scope="{ row }">
                <div v-if="row.downNum > 0">
                  <p class="sequentialNum">{{ row.downNum }}</p>
                </div>
                <div v-else>
                  <p class="sequentialNumRed">{{ row.downNum }}</p>
                </div>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            class="comPag"
            @current-change="depCurrentChange"
            :current-page="deppagesNum"
            :page-sizes="[25, 50, 100]"
            :page-size="deppageSize"
            :total="departmentViolatList.length"
            small
            layout="total,prev, pager, next"
          ></el-pagination>
        </div>
      </el-col>
      <!-- 异常操作个人排名 -->
      <el-col :span="9">
        <div class="grid-content subject2 mr0">
          <div class="personTop clearfix">
            <ul>
              <li class="personTop0"></li>
              <li class="personTop1">姓名</li>
              <li class="personTop2">部门</li>
              <li class="personTop3">比例</li>
              <li class="personTop4">次数</li>
            </ul>
          </div>
          <!-- <el-table
            :data="personalViolatList"
            class="tableBox"
            :border="false"
            te
          >-->
          <el-table
            :data="
              personalViolatList.slice(
                (perpagesNum - 1) * perpageSize,
                perpagesNum * perpageSize
              )
            "
            :show-header="false"
            style="width: 100%"
          >
            <el-table-column prop="date" width="40px" align="center"></el-table-column>
            <el-table-column prop="name" width="80px" label="姓名" align="center"></el-table-column>
            <el-table-column prop="department" width="130px" label="部门" align="center"></el-table-column>
            <el-table-column label="比例" width="310px" prop="upNum" align="center">
              <!-- 循环遍历显示百分百数值 -->
              <template slot-scope="{ row }">
                <div class="colorBoxs clearfix">
                  <div
                    :style="{ width: row.skylBlue }"
                    style="height:20px; float:left; backgroundColor:#84ccc9"
                  ></div>
                  <div
                    :style="{ width: row.yellow }"
                    style="height:20px; float:left; backgroundColor:#dedd5f"
                  ></div>
                  <div
                    :style="{ width: row.orange }"
                    style="height:20px; float:left; backgroundColor:#e7885f"
                  ></div>
                  <div
                    :style="{ width: row.purple }"
                    style="height:20px; float:left; backgroundColor:#7171db"
                  ></div>
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="address" label="次数" align="center" width="55px">
              <template slot-scope="{ row }">
                <div v-if="row.downNum > 0">
                  <p class="sequentialNum">{{ row.downNum }}</p>
                </div>
                <div v-else>
                  <p class="sequentialNumRed">{{ row.downNum }}</p>
                </div>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            class="comPag"
            @current-change="perCurrentChange"
            :current-page="perpagesNum"
            :page-sizes="[25, 50, 100]"
            :page-size="perpageSize"
            :total="personalViolatList.length"
            small
            layout="total,prev, pager, next"
          ></el-pagination>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {
  // 模拟饼图接口
  getPieData
} from "@/api/table.js";
export default {
  data() {
    return {
      // 分页器数据
      pagesNum: 1, // 当前页
      pageSize: 10, // 每页条数
      total: 5, // 总页数
      // 分页器数据
      deppagesNum: 1, // 当前页
      deppageSize: 10, // 每页条数
      deptotal: 5, // 总页数
      // 分页器数据
      perpagesNum: 1, // 当前页
      perpageSize: 10, // 每页条数
      pertotal: 5, // 总页数
      form: {
        actionBeginTime1: "", // 开始日期
        actionEndTime1: "", // 结束日期
        actionBeginTime2: "", // 开始日期
        actionEndTime2: "", // 结束日期
        actionBeginTime3: "", // 开始日期
        actionEndTime3: "" // 结束日期
      },
      abnormalComList: [],
      departmentViolatList: [],
      personalViolatList: [
        {
          date: "1",
          name: "白彦斌",
          department: "北京公司研发部",
          address: "34",
          upNum: 90,
          downNum: "378",
          purple: "30%",
          orange: "10%",
          yellow: "10%",
          skylBlue: "50%"
        },
        {
          date: "2",
          name: "沈志国",
          department: "上海公司制造部",
          address: "34",
          upNum: 80,
          downNum: "320",
          purple: "15%",
          orange: "5%",
          yellow: "40%",
          skylBlue: "30%"
        },
        {
          date: "3",
          name: "郭晓林",
          department: "广州公司销售部",
          address: "34",
          upNum: 60,
          downNum: "290",
          purple: "9%",
          orange: "17%",
          yellow: "33%",
          skylBlue: "21%"
        },
        {
          date: "4",
          name: "程志伟",
          department: "成都公司销售部",
          address: "34",
          upNum: 10,
          downNum: "280",
          purple: "18%",
          orange: "25%",
          yellow: "12%",
          skylBlue: "5%"
        },
        {
          date: "5",
          name: "白彦斌",
          department: "北京公司研发部",
          address: "34",
          upNum: 90,
          downNum: "378",
          purple: "30%",
          orange: "10%",
          yellow: "10%",
          skylBlue: "50%"
        },
        {
          date: "6",
          name: "沈志国",
          department: "上海公司制造部",
          address: "34",
          upNum: 80,
          downNum: "320",
          purple: "15%",
          orange: "5%",
          yellow: "40%",
          skylBlue: "30%"
        },
        {
          date: "7",
          name: "郭晓林",
          department: "广州公司销售部",
          address: "34",
          upNum: 60,
          downNum: "290",
          purple: "9%",
          orange: "17%",
          yellow: "33%",
          skylBlue: "21%"
        },
        {
          date: "8",
          name: "程志伟",
          department: "成都公司销售部",
          address: "34",
          upNum: 10,
          downNum: "280",
          purple: "18%",
          orange: "25%",
          yellow: "12%",
          skylBlue: "5%"
        },
        {
          date: "9",
          name: "郭晓林",
          department: "广州公司销售部",
          address: "34",
          upNum: 60,
          downNum: "290",
          purple: "9%",
          orange: "17%",
          yellow: "33%",
          skylBlue: "21%"
        },
        {
          date: "10",
          name: "程志伟",
          department: "成都公司销售部",
          address: "34",
          upNum: 10,
          downNum: "280",
          purple: "18%",
          orange: "25%",
          yellow: "12%",
          skylBlue: "5%"
        },
        {
          date: "9",
          name: "郭晓林",
          department: "广州公司销售部",
          address: "34",
          upNum: 60,
          downNum: "290",
          purple: "9%",
          orange: "17%",
          yellow: "33%",
          skylBlue: "21%"
        },
        {
          date: "10",
          name: "程志伟",
          department: "成都公司销售部",
          address: "34",
          upNum: 10,
          downNum: "280",
          purple: "18%",
          orange: "25%",
          yellow: "12%",
          skylBlue: "5%"
        }
      ]
    };
  },
  created() {
    // 默认一个月的日期填充
    this.getSumTimes();
    this.Data();
    this.initializeFunction();
    this.depFunction();
  },
  methods: {
    // 分页功能
    // 切换页
    comCurrentChange(val) {
      this.pagesNum = val;
      this.initializeFunction();
    },
    // 切换页
    depCurrentChange(val) {
      this.deppagesNum = val;
      this.depFunction();
    },
    // 切换页
    perCurrentChange(val) {
      this.perpagesNum = val;
      this.initializeFunction();
    },
    // 初始化函数
    async initializeFunction() {
      const value = {
        pagesNum: this.pagesNum,
        actionBeginTime: "2017-01-30 13:41:07",
        actionEndTime: "2020-03-05 13:19:08"
      };
      try {
        const { data } = await getPieData(value);
        const { abnormalCom } = data.data;
        this.abnormalComList = abnormalCom;
      } catch (error) {
        console.log(error);
      }
    },
    // 初始化函数
    async depFunction() {
      const value = {
        pagesNum: this.deppagesNum,
        actionBeginTime: "2017-01-30 13:41:07",
        actionEndTime: "2020-03-05 13:19:08"
      };
      try {
        const { data } = await getPieData(value);
        const { departmentViolatList } = data.data;
        this.departmentViolatList = departmentViolatList;
      } catch (error) {
        console.log(error);
      }
    },
    // 总览饼图函数（模拟接口）
    async informationPieFunction(callback) {
      try {
        const { data } = await getPieData();
        const { informationSecurityAllPie } = data.data;
        const vlue1 = informationSecurityAllPie[0].value;
        const name1 = informationSecurityAllPie[0].name;

        const vlue2 = informationSecurityAllPie[1].value;
        const name2 = informationSecurityAllPie[1].name;

        const vlue3 = informationSecurityAllPie[2].value;
        const name3 = informationSecurityAllPie[2].name;

        const vlue4 = informationSecurityAllPie[3].value;
        const name4 = informationSecurityAllPie[3].name;
        const companyName = informationSecurityAllPie[4].overviewName;
        const companyNum = informationSecurityAllPie[4].overviewNum;

        callback(
          vlue1,
          name1,
          vlue2,
          name2,
          vlue3,
          name3,
          vlue4,
          name4,
          companyName,
          companyNum
        );
      } catch (error) {
        console.log(error);
      }
    },
    // 总览饼图
    informationPie(
      vlue1,
      name1,
      vlue2,
      name2,
      vlue3,
      name3,
      vlue4,
      name4,
      companyName,
      companyNum
    ) {
      let myChart1 = this.$echarts.init(document.getElementById("main1"));
      this.initHeight1 = document.getElementById("card1").offsetHeight;
      this.initWidth1 = document.getElementById("card1").offsetWidth;
      const option1 = {
        animation: false,
        title: {
          text: companyName,
          subtext: companyNum + "人次",
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
          top: "240",
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
          left: "20%",
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
                value: vlue1,
                name: name1,
                radius: [0, "50%"],
                hoverAnimation: false,
                textStyle: {
                  color: "#333"
                },
                itemStyle: {
                  normal: {
                    color: "#fd9d73"
                  }
                }
              },
              {
                value: vlue2,
                name: name2,
                radius: [0, "50%"],
                hoverAnimation: false,
                itemStyle: {
                  normal: {
                    color: "#0be3f5"
                  }
                }
              },
              {
                value: vlue3,
                name: name3,
                radius: [0, "50%"],
                hoverAnimation: false,
                itemStyle: {
                  normal: {
                    color: "#fffd10"
                  }
                }
              },
              {
                value: vlue4,
                name: name4,
                radius: [0, "50%"],
                hoverAnimation: false,
                itemStyle: {
                  normal: {
                    color: "#3f98fd"
                  }
                }
              }
            ]
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
    getSumTimes() {
      // 获取当前的时间， 变量名为tiems
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

      // 获取当前的时间往前推一个月， 变量名为Dtimes
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
      // 查询里的默认时间
      this.form.actionEndTime1 = this.times;
      this.form.actionBeginTime1 = this.Dtimes;
      this.form.actionEndTime2 = this.times;
      this.form.actionBeginTime2 = this.Dtimes;
      this.form.actionEndTime3 = this.times;
      this.form.actionBeginTime3 = this.Dtimes;
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
  destroyed() {},
  mounted() {
    this.informationPieFunction(this.informationPie);
    let myChart2 = this.$echarts.init(document.getElementById("main2"));

    this.initHeight = document.getElementById("card1").offsetHeight;
    this.initWidth = document.getElementById("card1").offsetWidth;
    this.initHeight2 = document.getElementById("card2").offsetHeight;
    this.initWidth2 = document.getElementById("card2").offsetWidth;

    const option2 = {
      tooltip: {
        trigger: "axis",
        axisPointer: {
          // 坐标轴指示器，坐标轴触发有效
          type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
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
          name: "邮件营销",
          type: "bar",
          stack: "广告",
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
          name: "联盟广告",
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
          name: "视频广告",
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
          name: "无敌广告",
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
    myChart2.setOption(option2);

    let resize2 = {
      height: this.initHeight2,
      width: this.initWidth2
    };
    myChart2.resize(resize2);
    // --------------------------------------------------------------------------------------
  }
};
</script>

<style lang="scss" scoped>
@import "../../../../../css/clientSideMonitor/behaviorAnalysis/informationSecurity/components/trendsOverview.css";
</style>
