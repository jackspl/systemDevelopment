
<template>
  <!-- 异常登录 -->
  <div class="abnormalLogin">
    <!-- 搜索条件 -->
    <el-form :inline="true" :model="form" ref="form">
      <div class="seachInfoAll">
        <div class="searchCriteria">
          <!-- 公司选择 -->
          <el-form-item label="搜索条件">
            <el-autocomplete
              v-model="form.searchCrite"
              placeholder="北京公司 研发部 白彦斌 异常登录"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="ConditionFuzzyQuery"
              @select="handleSelectScen"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <div class="searchImg">
          <img src="../../../../../assets/seve.png" style="margin-top:5px" />
        </div>
        <div class="seachStarTime">
          <!-- 开始时间 -->
          <el-form-item label="开始时间" prop="actionBeginTime" class="startTime">
            <el-date-picker
              v-model="form.actionBeginTime"
              align="right"
              type="datetime"
              format="yyyy-MM-dd HH:mm:ss"
              value-format="yyyy-MM-dd HH:mm:ss"
              clearable
              placeholder="选择日期"
              :picker-options="pickerOptions1"
            ></el-date-picker>
          </el-form-item>
        </div>
        <div class="seachEndTime">
          <!-- 结束时间 -->
          <el-form-item label="结束时间" prop="actionEndTime" class="entTime">
            <el-date-picker
              v-model="form.actionEndTime"
              type="datetime"
              format="yyyy-MM-dd HH:mm:ss"
              value-format="yyyy-MM-dd HH:mm:ss"
              clearable
            ></el-date-picker>
          </el-form-item>
        </div>
        <div class="seachButton">
          <!-- 查询 -->
          <div class="seachAll">
            <el-form-item style="float: none;">
              <el-button type="primary" @click="seachBtn">查找</el-button>
              <el-button>重置</el-button>
            </el-form-item>
          </div>
          <!-- 滑动按钮 -->
          <div class="changeButton clearfix">
            <div class="allButton" @click="allButton" :class="{'textColor':flagAll}">所有登录</div>
            <div class="abnormalButton" @click="abnormalButton" :class="{'textColor':flagAbn}">异常登录</div>
          </div>
        </div>
      </div>
    </el-form>

    <!-- 用户信息 -->
    <el-form :inline="true" :model="form" ref="form">
      <div class="userInfoAll">
        <div class="userInfo">
          <el-form-item label="用户信息"></el-form-item>
        </div>
        <div class="userInfoCompany">
          <!-- 公司 -->
          <el-form-item label="公司">
            <el-autocomplete
              v-model="form.userCom"
              placeholder="上海公司"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="queryCompany"
              @select="handleSelectScen"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <div class="userInfoDep">
          <!-- 部门 -->
          <el-form-item label="部门" class="deparWidth">
            <el-autocomplete
              v-model="form.employeeGroup"
              placeholder="研发部"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="queryDepartment"
              @select="handleSelectScen"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <div class="userChange">
          <!-- 公司 -->
          <el-form-item label="用户">
            <el-autocomplete
              v-model="form.employeeName"
              placeholder="白彦斌"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="querySearchName"
              @select="handleSelectScen"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <div class="infoHost">
          <!-- 主机信息 -->
          <el-form-item label="主机信息"></el-form-item>
        </div>
        <div class="infoHostCompany">
          <!-- 公司 -->
          <el-form-item label="公司">
            <el-autocomplete
              v-model="form.hostCom"
              placeholder="上海公司"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="querySeachHostCompany"
              @select="handleSelectScen"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <div class="infoHostDep">
          <!-- 部门 -->
          <el-form-item label="部门">
            <el-autocomplete
              v-model="form.hostNameGroup"
              placeholder="研发部"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="querySeachHostGroup"
              @select="handleSelectScen"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <div class="infoHostName">
          <!-- 主机名 -->
          <el-form-item label="主机名">
            <el-autocomplete
              v-model="form.hostName"
              placeholder="LENOVO-PC1"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="HostNameFuzzyQuery"
              @select="handleSelectScen"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <div class="infoHostIp">
          <!-- IP -->
          <el-form-item label="IP">
            <el-autocomplete
              v-model="form.ip"
              placeholder="101.204.150.189"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="IPFuzzyQuery"
              @select="handleSelectScen"
            ></el-autocomplete>
          </el-form-item>
        </div>
      </div>
    </el-form>

    <!-- 表格 -->
    <el-row class="formList">
      <el-col :span="24">
        <div class="grid-content bg-purple-dark">
          <el-table
            :data="userList.slice((pagesNum-1)*pageSize,pagesNum*pageSize)"
            border
            :row-class-name="tableRowClassName"
            :header-cell-style="{background:'#1f4271',color:'white',fontSize:'16px',border: '1px solid #405a86'}"
            :cell-style="{background:'#16457b',color:'white'}"
            @sort-change="sortChange"
          >
            <el-table-column
              type="index"
              label="序号"
              align="center"
              width="100"
              :index="table_index"
            ></el-table-column>
            <el-table-column
              align="center"
              prop="actionBeginTime"
              label="时间"
              width="178px"
              sortable="coumnt"
            ></el-table-column>
            <el-table-column align="center" prop="company" label="公司"></el-table-column>
            <el-table-column align="center" prop="employeeGroup" label="部门"></el-table-column>
            <el-table-column align="center" prop="employeeName" label="用户"></el-table-column>
            <el-table-column align="center" prop="hostCompany" label="主机所在公司"></el-table-column>
            <el-table-column align="center" prop="hostNameGroup" label="主机所在部门"></el-table-column>
            <el-table-column align="center" prop="hostName" label="主机名"></el-table-column>
            <el-table-column align="center" prop="IP" label="主机IP"></el-table-column>
            <el-table-column align="center" prop="operationTime" label="使用时长"></el-table-column>
            <el-table-column align="center" prop="loginStatus" label="登录情况">
              <!-- <a @click="clickSeach">点击查询</a> -->
            </el-table-column>
          </el-table>
        </div>
      </el-col>
    </el-row>
    <!-- 分页 -->
    <el-col :span="24" class="paging">
      <div class="grid-content">
        <el-pagination
          align="center"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pagesNum"
          :page-sizes="[25, 50, 100]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next"
          :total="userList.length"
        ></el-pagination>
      </div>
    </el-col>
  </div>
</template>

<script>
import {
  // 表格渲染
  abnormalLoginTableList,
  // 用户——公司模糊查询
  querySearchCompany,
  // 用户——部门模糊查询
  queryDownListGroup,
  // 用户——姓名模糊查询
  querySearchName,
  // 主机——主机名模糊查询
  queryHostCompany,
  // 主机——部门模糊查询
  queryHostGroup,
  // 主机——主机名模糊查询
  querySearchHostName,
  // 主机——IP模糊查询
  queryID
  } from "@/api/table.js";
export default {
  // components: {
  //   layout: layout
  // },
  data() {
    return {
      // 表格数据
      userList: [],
      // 排序flag
      paiXuFlag: "",
      // 用于排序
      prop1: "",
      upDing: "",
      downDing: "",
      columnOrder: "",
      value: "",
      // 分页器数据
      pagesNum: 1, // 当前页码
      pageSize: 25, // 每页的数据条数
      total: 5, // 总条数

      // 事件控件里的最早数据、最晚数据
      pickerOptions1: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
        shortcuts: [
          {
            text: "最早数据",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            }
          },
          {
            text: "最晚数据",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            }
          }
        ]
      },

      flagAll: true,
      flagAbn: false,
      form: {
        // 搜索条件下拉菜单
        searchCrite: "",

        employeeName: '', // 姓名
        actionBeginTime: '', // 开始时间
        actionEndTime: '', // 结束时间
        employeeProject: '',
        hostNameGroup: '', // 主机所在部门
        userCom: '', // 用户公司
        ip: '', // 主机名
        employeeGroup: '', // 用户部门
        hostName: '', // 主机名
        hostCom: '', // 主机所在公司


      }
    };
  },
  created() {
    // 默认一个月的日期填充
    this.getSumTimes();
    // 用户当前时间提示
    this.Data();
    // 表格数据渲染
    this.getTableList();
  },
  methods: {
    // 搜索条件模糊查询
    ConditionFuzzyQuery(queryString, callback) {
      // 需要随时更改的（从后台获取到对象数组）
      queryHostGroup({
        hostNameGroup: queryString
      }).then(res => {
        let list = [];
        if (res.data.fuzzyQueryHostGroupList === null) {
          list = [];
        } else {
          res.data.fuzzyQueryHostGroupList.forEach(item => {
            list.push({
              value: item
            });
          });
        }
        callback(list);
      });
    },
    // 用户——公司模糊查询
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
    // 用户——部门模糊查询
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
    // 用户——姓名模糊查询
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
        callback(list);
      });
    },
    // 主机——公司模糊查询
    querySeachHostCompany(queryString, callback) {
      // 需要随时更改的（从后台获取到对象数组）
      queryHostCompany({
        hostCompany: queryString
      }).then(res => {
        let list = [];
        if (res.data.fuzzyHostCompanyList === null) {
          list = [];
        } else {
          res.data.fuzzyHostCompanyList.forEach(item => {
            list.push({
              value: item
            });
          });
        }
        callback(list);
      });
    },
    // 主机——部门模糊查询
    querySeachHostGroup(queryString, callback) {
      // 需要随时更改的（从后台获取到对象数组）
      queryHostGroup({
        hostNameGroup: queryString
      }).then(res => {
        let list = [];
        if (res.data.fuzzyQueryHostGroupList === null) {
          list = [];
        } else {
          res.data.fuzzyQueryHostGroupList.forEach(item => {
            list.push({
              value: item
            });
          });
        }
        callback(list);
      });
    },
    // 主机——主机名模糊查询
    HostNameFuzzyQuery(queryString, callback) {
      // 需要随时更改的（从后台获取到对象数组）
      querySearchHostName({
        hostName: queryString
      }).then(res => {
        let list = [];
        if (res.data.fuzzyQueryHostNameList === null) {
          list = [];
        } else {
          res.data.fuzzyQueryHostNameList.forEach(item => {
            list.push({
              value: item
            });
          });
        }
        callback(list);
      });
    },
    // 主机——ip模糊查询
    IPFuzzyQuery(queryString, callback) {
      // 需要随时更改的（从后台获取到对象数组）
      queryID({
        ip: queryString
      }).then(res => {
        let list = [];
        if (res.data.fuzzyIPList === null) {
          list = [];
        } else {
          res.data.fuzzyIPList.forEach(item => {
            list.push({
              value: item
            });
          });
        }
        callback(list);
      });
    },

    handleSelectScen(item) {},
    // 点击查询
    // clickSeach() {
    // },
    // 表格分页序号
    table_index(index) {
      return (this.pagesNum - 1) * this.pageSize + index + 1;
    },
    // 排序方法
    sortChange(column) {
      this.prop1 = column.prop;
      if (this.lable === "1") {
        if (this.prop1 === "actionBeginTime") {
          if (column.order === "ascending") {
            this.upDing = column.order;
            this.downDing = "";
            this.pagesNum = 1;
            this.paiXuFlag = 3;
            this.getTableList({ paiXuFlag: this.paiXuFlag });
          }
          if (column.order === "descending") {
            this.downDing = column.order;
            this.upDing = "";
            this.pagesNum = 1;
            this.paiXuFlag = 4;
            this.getTableList({ paiXuFlag: this.paiXuFlag });
          }
          if (column.order === null) {
            this.downDing = column.order;
            this.pagesNum = 1;
            this.paiXuFlag = "";
            this.getTableList({ paiXuFlag: this.paiXuFlag });
          }
        }
      }
      if (this.lable === "2") {
        if (this.prop1 === "actionBeginTime") {
          this.columnOrder = column.order;
          if (column.order === "ascending") {
            this.upDing = column.order;
            this.downDing = "";
            this.pagesNum = 1;
            this.paiXuFlag = 3;
            this.exceptionLoginFlag = 1;
            this.getTableList({
              exceptionLoginFlag: this.exceptionLoginFlag,
              paiXuFlag: this.paiXuFlag
            });
          }
          if (column.order === "descending") {
            this.downDing = column.order;
            this.upDing = "";
            this.pagesNum = 1;
            this.paiXuFlag = 4;
            this.exceptionLoginFlag = 1;
            this.getTableList({
              exceptionLoginFlag: this.exceptionLoginFlag,
              paiXuFlag: this.paiXuFlag
            });
          }
          if (column.order === null) {
            this.downDing = column.order;
            this.pagesNum = 1;
            this.paiXuFlag = "";
            this.exceptionLoginFlag = 1;
            this.getTableList({
              exceptionLoginFlag: this.exceptionLoginFlag,
              paiXuFlag: this.paiXuFlag
            });
          }
        }
      }
    },
       // 所有数据圆角按钮
    allButton() {
      this.flagAll = true;
      this.flagAbn = false;
    },
    // 异常数据圆角按钮
    abnormalButton() {
      this.flagAll = false;
      this.flagAbn = true;
    },
    // 渲染表格
    async getTableList(Flag) {
      const value = {
        employeeName: this.form.employeeName,
        actionBeginTime: this.form.actionBeginTime,
        actionEndTime: this.form.actionEndTime,
        employeeProject: this.form.employeeProject,
        hostCompany: this.form.hostCom,
        hostNameGroup: this.form.hostNameGroup,
        company: this.form.userCom,
        ip: this.form.ip,
        employeeGroup: this.form.employeeGroup,
        hostName: this.form.hostName,
        pagesNum: this.pagesNum,
        ...Flag
      };
      try {
        const { data } = await abnormalLoginTableList(value);
        const { tableList, tableTotalList } = data;
        if (tableList === null) {
          this.userList = "";
          this.total = "0";
        } else {
          this.userList = tableList;
        }
        this.total = tableTotalList[0];
      } catch (e) {
        console.log(e);
      }
    },
    // 分页功能

    // 更改每页个数
    handleSizeChange(val) {
      this.pagesNum = 1;
      this.pageSize = val;
      this.getTableList();
    },
    // 切换页
    handleCurrentChange(val) {
      this.pagesNum = val;
      this.getTableList();
    },

    // 获取当前日期
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
    tableRowClassName({ row, rowIndex }) {},

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
        return
      }
      this.pagesNum = 1
      this.getTableList(this.form)
      // this.getTableList(this.form)
    }
  },
  destroyed() {},
  mounted() {}
};
</script>

<style lang='scss' scoped>
@import "../../../../../css/clientSideMonitor/behaviorAnalysis/informationSecurity/components/abnormalLogin.css";
</style>
