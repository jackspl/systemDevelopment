
<template>
  <!-- 异常查看 -->
  <div class="abnormalView clearfix">
    <div class="seachAllSum">
      <el-form :inline="true" :model="form" ref="form">
        <div class="seachF1 clearfix">
          <div class="searchCriteria">
            <!-- 搜索条件 -->
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
          <div class="userInfoAll">
            <div class="userInfo">
              <el-form-item label="用户信息"></el-form-item>
            </div>
            <div class="userInfoCompany">
              <!-- 公司 -->
              <el-form-item label="公司">
                <el-autocomplete
                  v-model="form.employeeName"
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
          </div>
        </div>
        <div class="seachF2 clearfix">
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
          <div class="userInfoAll infoMation">
            <div class="infoHost">
              <!-- 文档信息 -->
              <el-form-item label="文档信息"></el-form-item>
            </div>
            <div class="infoHostCompany">
              <!-- 公司 -->
              <el-form-item label="公司">
                <el-autocomplete
                  v-model="form.docGroup"
                  placeholder="上海公司"
                  clearable
                  type="text"
                  :debounce="0"
                  :fetch-suggestions="queryCompany"
                  @select="handleSelectScen"
                ></el-autocomplete>
              </el-form-item>
            </div>
            <div class="infoHostDep">
              <!-- 部门 -->
              <el-form-item label="部门">
                <el-autocomplete
                  v-model="form.docGroup"
                  placeholder="研发部"
                  clearable
                  type="text"
                  :debounce="0"
                  :fetch-suggestions="queryDepartment"
                  @select="handleSelectScen"
                ></el-autocomplete>
              </el-form-item>
            </div>
            <div class="infoHostName">
              <!-- 主机名 -->
              <el-form-item label="所有者">
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
          </div>
        </div>
        <div class="seachF3">
          <!-- 滑动按钮 -->
          <div class="changeButton clearfix">
            <div class="allButton" @click="allButton" :class="{'textColor':flagAll}">所有登录</div>
            <div class="abnormalButton" @click="abnormalButton" :class="{'textColor':flagAbn}">异常登录</div>
          </div>
          <div class="acrossDepView">
            <el-switch v-model="crossDepartmentViewFlag"
              active-value="1" inactive-value="0" @change="getTableList" inactive-text="跨部门查看"></el-switch>
          </div>
          <div class="confDocumentsView">
            <el-switch v-model="secretFileViewFlag"
              active-value="1" inactive-value="0" @change="getTableList" inactive-text="查看机密文件"></el-switch>
          </div>
        </div>
        <div class="seachF4">
          <el-button type="primary" @click="seachBtn" class="seachBtn">查询</el-button>
        </div>
      </el-form>
    </div>
    <!-- 表格 -->
    <div class="tableList">
      <el-table
        :data="userList.slice((pagesNum-1)*pageSize,pagesNum*pageSize)"
        border
        :header-cell-style="{background:'#1f4271',color:'white',fontSize:'16px',border: '1px solid #405a86'}"
        :cell-style="{background:'#16457b',color:'white'}"
        @sort-change="sortChange"
      >
        <el-table-column type="index" label="序号" align="center" width="60" :index="table_index"></el-table-column>
        <el-table-column
          align="center"
          prop="actionBeginTime"
          label="时间"
          width="180"
          sortable="coumnt"
        ></el-table-column>
        <el-table-column align="center" prop="employeeName" label="公司" width="120"></el-table-column>
        <el-table-column align="center" prop="employeeGroup" label="部门"></el-table-column>
        <el-table-column align="center" prop="employeeName" label="用户" width="100"></el-table-column>
        <el-table-column align="center" prop="docGroup" label="文档所属公司"></el-table-column>
        <el-table-column align="center" prop="docGroup" label="文档所属部门"></el-table-column>
        <el-table-column align="center" prop="secretFileView" label="机密" width="60">
          <template slot-scope="scope">
            <div
              :style="{'color': scope.row.secretFileView === '是'?'red':'#fff'}"
            >{{scope.row.secretFileView}}</div>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="crossDepartmentView" label="跨部门" width="80">
          <template slot-scope="scope">
            <div
              :style="{'color': scope.row.crossDepartmentView === '是'?'red':'#fff'}"
            >{{scope.row.crossDepartmentView}}</div>
          </template>
        </el-table-column>
        <el-table-column align="center" prop="docName" label="文档"></el-table-column>
      </el-table>
    </div>
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
import { getViewList, getPulldownListScene,
  // 公司模糊查询
  querySearchCompany,
  // 姓名模糊查询
  querySearchName,
  // 部门模糊查询
  queryDownListGroup } from "@/api/table.js";

export default {
  // components: {
  //   layout: layout
  // },
  data() {
    return {
      // 表格数据
      userList: [],
      crossDepartmentViewFlag: "", // 跨部门下载
      secretFileViewFlag: "", // 机密文件下载
      // 排序flag
      paiXuFlag: "",
      // 用于排序queryKpiHostName
      prop1: "",
      upDing: "",
      downDing: "",
      columnOrder: "",
      value: "",
      // 分页器数据
      pagesNum: 1, // 当前页码
      pageSize: 25, // 每页的数据条数
      total: 5, // 总条数
      // 跨部门查看
      value3: true,
      value4: true,
      //  所有登录选项
      flagAll: true,
      flagAbn: false,
      form: {

        region1: "",
        region2: "",
        region3: "",
        region4: "",
        region5: "",
        region6: "",
        region7: "",

        actionBeginTime: '', // 开始时间
        actionEndTime: '', // 结束时间

        userCom: '', // 用户公司
        employeeGroup: '', // 用户部门
        employeeName: '', // 用户

        docCom: '', // 文档所属公司
        docGroup: '', // 文档所属部门
        docOwner: '' // 文档所有者
      }
    };
  },
  created() {
    // 默认一个月的日期填充
    this.getSumTimes();
    // 表格数据渲染
    this.getTableList();
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
    // 用户模糊查询
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
    // 所有者模糊查询
    OwnerFuzzyQuery(queryString, callback) {
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
      this.getTableList({crossDepartmentViewFlag: this.crossDepartmentViewFlag=0},{secretFileViewFlag: this.secretFileViewFlag=0})
    },
    // 异常数据圆角按钮
    abnormalButton() {
      this.flagAll = false;
      this.flagAbn = true;
      this.getTableList({crossDepartmentViewFlag: this.crossDepartmentViewFlag=1},{secretFileViewFlag: this.secretFileViewFlag=1})
    },
    // 渲染表格
    async getTableList(Flag) {
      const value = {
        employeeName: this.form.employeeName,
        actionBeginTime: this.form.actionBeginTime,
        actionEndTime: this.form.actionEndTime,
        employeeProject: this.form.employeeProject,
        employeeGroup: this.form.employeeGroup,
        hostName: this.form.hostName,
        hostNameGroup: this.form.hostNameGroup,
        crossDepartmentViewFlag: this.crossDepartmentViewFlag,
        secretFileViewFlag: this.secretFileViewFlag,
        pagesNum: this.pagesNum,
        ...Flag
      };
      try {
        const { data } = await getViewList(value);
        console.log(data);
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
      this.pagesNum = 1
      this.getTableList()
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
      this.form.actionEndTime = this.times;
      this.form.actionBeginTime = this.Dtimes;
    }
  },
  destroyed() {},
  mounted() {}
};
</script>

<style lang='scss'>
@import "../../../../../css/clientSideMonitor/behaviorAnalysis/informationSecurity/components/abnormalView.css";
</style>
