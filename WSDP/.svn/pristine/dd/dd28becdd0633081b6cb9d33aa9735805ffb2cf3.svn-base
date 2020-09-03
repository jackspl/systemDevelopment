<template>
  <!-- 总览 -->
  <div class="trendsOverview">
    <!-- 搜索查询 -->
    <el-form :inline="true" :model="form" ref="form">
      <div class="seachAll clearfix">
        <!-- 查询 -->
        <div class="seach">
          <el-form-item>
            <el-button type="primary" @click="seachBtn">查找</el-button>
            <!-- <el-button>重置</el-button> -->
          </el-form-item>
        </div>
        <!-- 结束日期 -->
        <div class="endTime">
          <el-form-item label="结束日期" prop="actionEndTime">
            <el-date-picker
              v-model="form.actionEndTime"
              type="datetime"
              format="yyyy-MM-dd HH:mm:ss"
              value-format="yyyy-MM-dd HH:mm:ss"
              clearable
            ></el-date-picker>
          </el-form-item>
        </div>
        <!-- 开始日期 -->
        <div class="starTime">
          <el-form-item label="开始日期" prop="actionBeginTime">
            <el-date-picker
              v-model="form.actionBeginTime"
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

    <!-- 主体一（饼图+柱状图） -->
    <el-row>
      <!-- 饼图  -->
      <el-col :span="4">
        <div class="grid-content infoLeft">
          <div id="card1">
            <div id="main1" style="width: 100%; top: -10px !important"></div>
          </div>
        </div>
      </el-col>
      <!-- 柱状图 -->
      <el-col :span="20" id="card2">
        <div class="grid-content infoRight">
          <div id="main2" style="width: 100%;"></div>
        </div>
      </el-col>
    </el-row>
    <!-- 异常导航栏 -->
    <el-row class="mbotton">
      <!-- 异常操作公司排名 -->
      <el-col :span="6" class="clearfix">
        <div class="grid-content subject1">
          <div class="tit">异常操作公司排名</div>
          <div class="tip">（{{abnormalComList.length}}个公司违规）</div>
        </div>
      </el-col>
      <!-- 异常操作部门排名 -->
      <el-col :span="9">
        <div class="grid-content subject1">
          <div class="tit">异常操作部门排名</div>
          <div class="tip">（{{personalViolatDepList.length}}个部门违规）</div>
        </div>
      </el-col>
      <!-- 异常操作个人排名 -->
      <el-col :span="9">
        <div class="grid-content subject1">
          <div class="tit">异常操作个人排名</div>
          <div class="tip">（{{personalViolatPerList.length}}个人违规）</div>
        </div>
      </el-col>
    </el-row>

    <!-- 主体二（三个横向柱状图） -->
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
          <el-table
            :data="
              abnormalComList.slice(
                (comPagesNum - 1) * comPageSize,
                comPagesNum * comPageSize
              )
            "
            :show-header="false"
            style="width: 100%"
          >
            <el-table-column type="index" label align="center" width="30" :index="table_index"></el-table-column>
            <el-table-column prop="company" label="公司" width="100" align="center"></el-table-column>
            <el-table-column label="比例" prop="upNum"  align="center">
              <!-- 循环遍历显示百分百数值 -->
              <template slot-scope="{ row }">
                <div class="colorBoxs clearfix">
                  <div
                    :style="{ width: row.abnormalDelete+'%' }"
                    style="height:20px; float:left; backgroundColor:#e7884f"
                  ></div>
                  <div
                    :style="{ width: row.abnormalDownload+'%' }"
                    style="height:20px; float:left; backgroundColor:#dede64"
                  ></div>
                  <div
                    :style="{ width: row.abnormalLogin+'%' }"
                    style="height:20px; float:left; backgroundColor:#84ccc9"
                  ></div>
                  <div
                    :style="{ width: row.abnormalView+'%' }"
                    style="height:20px; float:left; backgroundColor:#7171db"
                  ></div>
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="address" label="次数" width="55" align="center">
              <template slot-scope="{ row }">
                <div v-if="row.operaCount > 0">
                  <p class="sequentialNum">{{ row.operaCount }}</p>
                </div>
                <div v-else>
                  <p class="sequentialNumRed">{{ row.operaCount }}</p>
                </div>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            class="comPag"
            @current-change="comCurrentChange"
            :current-page="comPagesNum"
            :page-sizes="[25, 50, 100]"
            :page-size="comPageSize"
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
          <el-table
            :data="
              personalViolatDepList.slice(
                (depPagesNum - 1) * depPageSize,
                depPagesNum * depPageSize
              )
            "
            :show-header="false"
            style="width: 100%"
          >
            <el-table-column type="index" label align="center" width="30" :index="table_index2"></el-table-column>
            <el-table-column prop="employeeGroup" width="130px" label="部门" align="center"></el-table-column>
            <el-table-column label="比例" width="380px" prop="abnormalLogin" align="center">
              <!-- 循环遍历显示百分百数值 -->
              <template slot-scope="{ row }">
                <div class="colorBoxs clearfix">
                  <div
                    :style="{ width: row.abnormalDelete+'%' }"
                    style="height:20px; float:left; backgroundColor:#e7884f"
                  ></div>
                  <div
                    :style="{ width: row.abnormalDownload+'%' }"
                    style="height:20px; float:left; backgroundColor:#dede64"
                  ></div>
                  <div
                    :style="{ width: row.abnormalLogin+'%' }"
                    style="height:20px; float:left; backgroundColor:#84ccc9"
                  ></div>
                  <div
                    :style="{ width: row.abnormalView+'%' }"
                    style="height:20px; float:left; backgroundColor:#7171db"
                  ></div>
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="address" label="次数" align="center" width="55px">
              <template slot-scope="{ row }">
                <div v-if="row.operaCount > 0">
                  <p class="sequentialNum">{{ row.operaCount }}</p>
                </div>
                <div v-else>
                  <p class="sequentialNumRed">{{ row.operaCount }}</p>
                </div>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            class="comPag"
            @current-change="depCurrentChange"
            :current-page="depPagesNum"
            :page-sizes="[25, 50, 100]"
            :page-size="depPageSize"
            :total="personalViolatDepList.length"
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
          <el-table
            :data="
              personalViolatPerList.slice(
                (perPagesNum - 1) * perPageSize,
                perPagesNum * perPageSize
              )
            "
            :show-header="false"
            style="width: 100%"
          >
            <el-table-column type="index" label align="center" :index="table_index3"></el-table-column>
            <el-table-column prop="employeeName" width="80px" label="姓名" align="center"></el-table-column>
            <el-table-column prop="employeeGroup" width="130px" label="部门" align="center"></el-table-column>
            <el-table-column label="比例" width="310px" prop="upNum" align="center">
              <!-- 循环遍历显示百分百数值 -->
              <template slot-scope="{ row }">
                <div class="colorBoxs clearfix">
                  <div
                    :style="{ width: row.abnormalDelete+'%' }"
                    style="height:20px; float:left; backgroundColor:#e7884f"
                  ></div>
                  <div
                    :style="{ width: row.abnormalDownload+'%' }"
                    style="height:20px; float:left; backgroundColor:#dede64"
                  ></div>
                  <div
                    :style="{ width: row.abnormalLogin+'%' }"
                    style="height:20px; float:left; backgroundColor:#84ccc9"
                  ></div>
                  <div
                    :style="{ width: row.abnormalView+'%' }"
                    style="height:20px; float:left; backgroundColor:#7171db"
                  ></div>
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="address" label="次数" align="center" width="55px">
              <template slot-scope="{ row }">
                <div v-if="row.operaCount > 0">
                  <p class="sequentialNum">{{ row.operaCount }}</p>
                </div>
                <div v-else>
                  <p class="sequentialNumRed">{{ row.operaCount }}</p>
                </div>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            class="comPag"
            @current-change="perCurrentChange"
            :current-page="perPagesNum"
            :page-sizes="[25, 50, 100]"
            :page-size="perPageSize"
            :total="personalViolatPerList.length"
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
  getPieData,
  comSeachAll,
  getEmpLine,
  depSeachAll,
  // 四种异常饼状图+柱状图
  AllMality,
  // 所有违规记录
  AllViolations
} from "@/api/table.js";
export default {
  data() {
    return {
      // 异常公司分页
      comPagesNum: 1, //当前页
      comPageSize: 10, //每页10条

      // 异常部门分页
      depPagesNum: 1, //当前页
      depPageSize: 10, //每页10条

      // 异常部门分页
      perPagesNum: 1, //当前页
      perPageSize: 10, //每页10条

      form: {
        actionBeginTime: "", // 开始日期
        actionEndTime: ""
      },
      // 异常柱状图参数
      DepColor2: [],
      DepData2: [],
      departmentViolatList: [],
      // 违规操作公司
      personalViolatComList: [],
      abnormalComList: [],
      // 违规操作部门
      personalViolatDepList: [],
      // 违规操作个人
      personalViolatPerList: [],
    };
  },
  created() {
    // 默认一个月的日期填充
    this.getSumTimes();
    this.Data();
    // 所有违规  刷新数据
    this.getAllViolaData();
  },
  methods: {
    // 随机颜色
    randColor(item, index) {
      const Arr = [
        // 异常下载
        "#dede64",
        // 异常删除
        "#e7884f",
        // 异常查看
        "#7171db",
        // 异常登录
        "#84ccc9"
      ];
      const color = Arr[index];
      return color;
    },
    // 表格分页序号
    table_index(index) {
      return (this.comPagesNum - 1) * this.comPageSize + index + 1;
    },
    table_index2(index) {
      return (this.depPagesNum - 1) * this.depPageSize + index + 1;
    },
    table_index3(index) {
      return (this.perPagesNum - 1) * this.perPageSize + index + 1;
    },
    // 分页功能
    // 异常公司切换页
    comCurrentChange(val) {
      this.comPagesNum = val;
    },
    // 异常部门切换页
    depCurrentChange(val) {
      this.depPagesNum = val;
    },
    // 异常员工切换页
    perCurrentChange(val) {
      this.perPagesNum = val;
    },
    // ----------------------------------------------------
    // 所有违规  刷新数据
    async getAllViolaData() {
      const value = {
        actionBeginTime: this.form.actionBeginTime,
        actionEndTime: this.form.actionEndTime
      };
      const { data } = await AllViolations(value);
      const { violationGroupList, violationCompanyList, violationNameList } = data;
      // 违规操作公司
      this.abnormalComList = violationCompanyList
      // 违规操作部门
      this.personalViolatDepList = violationGroupList
      // 违规操作个人
      this.personalViolatPerList = violationNameList
      // alert((Math.round(violationGroupList[0].abnormalDownload * 100)/100).toFixed(2) + '%')
    },

    // ----------------------------------------------------
    // 提取异常饼状图
    informationPie(arr, allKPI) {
      let myChart1 = this.$echarts.init(document.getElementById("main1"));
      this.initHeight1 = document.getElementById("card1").offsetHeight;
      this.initWidth1 = document.getElementById("card1").offsetWidth;
      const option1 = {
        animation: false,
        title: {
          text: "信息安全事件",
          subtext: allKPI + "人次",
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
    // 提取异常柱状图
    informationBar(sonArr2, DepColor2, DepData2) {
      let myChart2 = this.$echarts.init(document.getElementById("main2"));
      this.initHeight2 = document.getElementById("card2").offsetHeight;
      this.initWidth2 = document.getElementById("card2").offsetWidth;
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
      myChart2.setOption(option2);
      let resize2 = {
        height: this.initHeight2,
        width: this.initWidth2
      };
      myChart2.resize(resize2);
    },
    // 四种异常饼状图+柱状图  刷新数据
    async informationPieFunction() {
      const value = {
        actionBeginTime: this.form.actionBeginTime,
        actionEndTime: this.form.actionEndTime
      };
      const { data } = await AllMality(value);
      const { allPieChartList, nameList, allCount } = data;
      const sonNameList = nameList.map(item => {
        return item.children;
      });
      const GroupList = [];
      const dataGroupList = [];
      const GroupFormList = [];
      sonNameList.forEach(item1 => {
        dataGroupList.push(item1[0].recentInfo);
        item1.forEach(item2 => {
          GroupList.push({
            recentInfo: item2.recentInfo,
            operaCount: item2.operaCount,
            zheXianX: item2.zheXianX
          });
        });
      });
      dataGroupList.forEach((item, index) => {
        GroupFormList[index] = GroupList.filter(son => {
          return son.recentInfo === item;
        });
      });

      // 封装饼图
      const newArr = allPieChartList.map((item, index) => {
        const comColor = this.randColor(item, index);
        return {
          value: item.operaCount,
          name: item.recentInfo,
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
      this.informationPie(newArr, allCount);

      // 封装部门柱状图
      const sonArr2 = dataGroupList.map((item, index) => {
        this.DepColor2 = this.randColor(item, index);
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
          data: operaCountList,
          color: comColor
        };
      })
      // 调用折线图函数、传参
      this.informationBar(sonArr2, this.DepColor2, this.DepData2);
    },
    // 获取当前的时间
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
      this.form.actionEndTime = this.times;
      this.form.actionBeginTime = this.Dtimes;
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
    // 查询刷新数据
    async comButtonSeach() {
      const value = {
        actionBeginTime: this.form.actionBeginTime,
        actionEndTime: this.form.actionEndTime
      };
      const { data } = await depSeachAll(value);
      const { allKPI, contributionList, kpiRankingList } = data;
      if (!dateEntityList.length) {
        this.mepInitItem();
        return;
      }
      // 时间
      const AllXTimes = dateEntityList.map(item => {
        return item.zheXianX;
      });
      // 员工净值
      const empNetWorth = dateEntityList.map(item => {
        return item.KPI;
      });
      // 员工加分
      const empBonusPoints = dateEntityList.map(item => {
        return item.KPIjiafen;
      });
      // 员工减分
      const empDemerit = dateEntityList.map(item => {
        return item.KPIjianfen;
      });
      this.mepInitItem(AllXTimes, empNetWorth, empBonusPoints, empDemerit);
    },
    // 查询按钮
    seachBtn() {
      this.$refs.form.validate();
      const ttime1 = this.form.actionBeginTime;
      const ttime2 = this.form.actionEndTime;
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("请合理选择日期");
        // return
      }
      this.informationPieFunction(this.form);
      this.getAllViolaData(this.form);
    }
  },
  mounted() {
    // 主体一柱状图
    this.informationPieFunction();
  }
};
</script>
<style lang="scss" scoped>
@import "../../../../../css/clientSideMonitor/behaviorAnalysis/informationSecurity/components/trendsOverview.css";
</style>
