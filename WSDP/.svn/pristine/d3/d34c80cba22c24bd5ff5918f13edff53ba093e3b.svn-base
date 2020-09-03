<template>
  <div class="kpiDetailsQuery">
    <div class="seachAll">
      <el-form :inline="true" :model="form" ref="form">
        <!-- 三个单选按钮 -->
        <div class="threeRadio">
          <el-radio-group v-model="radio1">
            <el-radio :label="1" @change="comRadio">公司</el-radio>
            <el-radio :label="2" @change="depRadio">部门</el-radio>
            <el-radio :label="3" class="personal" @change="empRadio">个人</el-radio>
          </el-radio-group>
        </div>
        <!-- 用户信息 -->
        <div class="userInfo">
          <el-form-item label="用户信息"></el-form-item>
        </div>
        <!-- 公司选择 -->
        <div class="comChange" v-if="comDiv">
          <el-form-item label="公司">
            <el-autocomplete
              v-model="form.company"
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
        <!-- 部门选择 -->
        <div class="depChange" v-if="depDiv">
          <el-form-item label="部门">
            <el-autocomplete
              v-model="form.department"
              placeholder="研发部"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="queryDepartment"
              @select="handleSelectScen"
              style="width: 150px;"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <!-- 员工选择 -->
        <div class="userChange" v-if="empDiv">
          <el-form-item label="用户">
            <el-autocomplete
              v-model="form.name"
              placeholder="常艳丽"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="querySearchName"
              @select="handleSelectScen"
              style="width: 120px;"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <!-- 主机名选择 -->
        <div class="hostChange">
          <el-form-item label="主机名">
            <el-autocomplete
              v-model="form.hostName"
              placeholder="TC11WIN7BG"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="querySearchHostName"
              @select="handleSelectScen"
              style="width: 150px;"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <!-- 查询按钮 -->
        <div class="seachButton">
          <el-button type="primary" @click="seachBtn">查询</el-button>
        </div>
        <!-- 结束时间 -->
        <div class="endTime">
          <el-form-item label="结束日期" prop="endTime">
            <el-date-picker
              v-model="form.endTime"
              type="datetime"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              clearable
            ></el-date-picker>
          </el-form-item>
        </div>
        <!-- 开始时间 -->
        <div class="startTime">
          <el-form-item label="开始日期" prop="startTime">
            <el-date-picker
              v-model="form.startTime"
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

    <div class="execel">
      <el-table
        border
        :data=tableData
        @sort-change="sort_change"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        @select="pinSelect"
        ref="multipleTable"
        :header-cell-style="{
          background: '#153a6b',
          color: 'white',
          fontSize: '16px',
          border: '1px solid #316aa1'
        }"
        :cell-style="{ background: '#153a6b', color: 'white' }"
      >
        <el-table-column type="selection" width="50" align="center" border></el-table-column>
        <!-- <el-table-column type="selection" width="55"  > </el-table-column> -->
        <el-table-column type="index" label="序号" align="center" width="60" :index="table_index"></el-table-column>
        <el-table-column prop="employeeName" label="姓名" align="center" width="80"></el-table-column>
        <el-table-column prop="company" label="公司" align="center"></el-table-column>
        <el-table-column prop="employeeGroup" label="部门" align="center"></el-table-column>
        <el-table-column prop="hostName" label="主机名" align="center"></el-table-column>
        <el-table-column label="加分" align="center">
          <el-table-column prop="workingHours" align="center" label="有效工时" sortable="coumnt" width="120"></el-table-column>
          <el-table-column prop="operaCount" align="center" label="场景次数" sortable="coumnt" width="120"></el-table-column>
          <el-table-column prop="outputDocument" align="center" label="产出文档" sortable="coumnt" width="120"></el-table-column>
          <el-table-column prop="documentReference" align="center" label="文档引用" sortable="coumnt" width="120"></el-table-column>
          <el-table-column prop="projectWeighting" align="center" label="项目加权" sortable="coumnt" width="120"></el-table-column>
        </el-table-column>
        <el-table-column label="减分" align="center">
          <el-table-column prop="infoSafeViolation" align="center" label="信息安全违规" width="150" height="20" sortable="coumnt"></el-table-column>
          <el-table-column prop="irregularOperation" align="center" label="不规范操作" sortable="coumnt" width="150"></el-table-column>
        </el-table-column>
        <el-table-column prop="KPI" label="KPI" align="center" sortable="coumnt" width="90"></el-table-column>
      </el-table>
    </div>
    <div class="footerSum">
      <el-form :inline="true" :model="form" class="demo-form-inline form" ref="form">
        <!-- 取消按钮 -->
        <div class="cancelButton">
          <el-button @click="toggleSelection()">取消</el-button>
        </div>
        <!-- 显示行数 -->
        <div class="showNum">已选中{{ selectedNum }}行</div>
        <!-- 搜索条件 -->
        <div class="seachCond">
          <el-form-item label="搜索条件">
            <el-autocomplete
              v-model="form.searchCriteria"
              placeholder="常艳丽"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="querySearchName"
              @select="handleSelectScen"
              style="width: 310px;"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <!-- 保存按钮 -->
        <div class="save">
          <el-button type="primary" @click="save">保存</el-button>
        </div>
        <!-- 分页  -->
        <div class="pag">
          <el-pagination
            align="center"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pagesNum"
            :page-sizes="[25, 50, 100]"
            :page-size="pagesSize"
            layout="total, sizes, prev, pager, next"
            :total="total"
          ></el-pagination>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import {
  // 表格数据
  kpiTableInfo,
  // 主机名模糊查询
  queryKpiHostName,
  // 公司模糊查询
  querySearchCompany,
  // 姓名模糊查询
  querySearchName,
  // 部门模糊查询
  querySearchDocGroup
} from "@/api/table.js";
export default {
  data() {
    return {
      // 公司、部门、员工div盒子
      comDiv: 'true',
      depDiv: 'true',
      empDiv: 'true',
      // KPI详情表单数据
      form: {
        // KPI排名数据
        company: "", // 公司employeeGroup
        department: "", // 部门
        name: "", // 姓名
        hostName: "", // 主机名
        searchCriteria: "", // 搜索条件
        startTime: "", // 开始时间
        endTime: "" // 结束时间
      },
      checked1: true,
      checked2: false,
      checked3: false,
      checked4: false,
      radio1: 3,
      // 动态修用户提示时间
      nowData: "",
      // 表单里的数据
      // form: {
      //   // 下拉菜单选择
      //   region1: "",
      //   region2: "",
      //   region3: "",
      //   region4: "",
      //   region5: "",
      //   actionBeginTime: "", // 开始日期
      //   actionEndTime: "" // 结束日期
      // },
      // 选中的行数
      selectedNum: 0,
      arr: [],
      origin: -1, // 这里给一个变量作为起点
      pin: false, // 这里给一个变量，默认为false，不按住
      // 存放选中值的数组
      multipleSelection: [],
      // 表格数据
      tableData: [],
      //存放column.prop的字符串值
      proptype: "",
      // 排序后为第一页

      // 分页器数据
      pagesNum: 1, // 当前页
      pagesSize: 25, // 每页条数
      total: 5, // 总页数
      // 默认选中第4页
      currentPage4: 4,
      checked: false,
      pageKey: false
    };
  },
  created() {
    // 默认一个月的日期填充
    this.getSumTimes();
    this.Data();
    this.tableData.forEach((item, index) => {
      // 遍历索引,赋值给data数据
      item.index = index;
    });
    // document.onkeydown = (e) => {
    //   if (e.keyCode === 16) {
    //     this.pageKey = true
    //   }
    // }
    // document.onkeyup = (e) => {
    //   if (e.keyCode === 16) {
    //     this.pageKey = false
    //   }
    // }
    this.getTabList();
  },
  methods: {
    // 公司单选按钮
    comRadio() {
      this.comDiv = true
      this.depDiv = false
      this.empDiv = false
    },
    // 部门单选按钮
    depRadio() {
      this.depDiv = true
      this.empDiv = false
    },
    // 个人单选按钮
    empRadio() {
      this.comDiv = true
      this.depDiv = true
      this.empDiv = true
    },
    // 排序方法
    sort_change(column) {
      // console.log(column.prop); //当前列需要排序的数据
      // console.log(column.order); //排序规则：descending降序、ascending升序
      this.pagesNum = 1; // 排序后返回第一页
      if (column.prop === "workingHours") { // 表格每一列对应的字段，必须一一对应
        this.proptype = column.prop; // 将键名prop赋值给变量proptype
        if (column.order === "descending") {
          this.tableData.sort(this.my_desc_sort);
        } else if (column.order === "ascending") {
          this.tableData.sort(this.my_asc_sort);
        }
      } else if (column.prop === "operaCount") {
        this.proptype = column.prop;
        if (column.order === "descending") {
          this.tableData.sort(this.my_desc_sort);
        } else if (column.order === "ascending") {
          this.tableData.sort(this.my_asc_sort);
        }
      } else if (column.prop === "outputDocument") {
        this.proptype = column.prop;
        if (column.order === "descending") {
          this.tableData.sort(this.my_desc_sort);
        } else if (column.order === "ascending") {
          this.tableData.sort(this.my_asc_sort);
        }
      } else if (column.prop === "documentReference") {
        this.proptype = column.prop;
        if (column.order === "descending") {
          this.tableData.sort(this.my_desc_sort);
        } else if (column.order === "ascending") {
          this.tableData.sort(this.my_asc_sort);
        }
      } else if (column.prop === "projectWeighting") {
        this.proptype = column.prop;
        if (column.order === "descending") {
          this.tableData.sort(this.my_desc_sort);
        } else if (column.order === "ascending") {
          this.tableData.sort(this.my_asc_sort);
        }
      } else if (column.prop === "infoSafeViolation") {
        this.proptype = column.prop;
        if (column.order === "descending") {
          this.tableData.sort(this.my_desc_sort);
        } else if (column.order === "ascending") {
          this.tableData.sort(this.my_asc_sort);
        }
      } else if (column.prop === "irregularOperation") {
        this.proptype = column.prop;
        if (column.order === "descending") {
          this.tableData.sort(this.my_desc_sort);
        } else if (column.order === "ascending") {
          this.tableData.sort(this.my_asc_sort);
        }
      } else if (column.prop === "KPI") {
        this.proptype = column.prop;
        if (column.order === "descending") {
          this.tableData.sort(this.my_desc_sort);
        } else if (column.order === "ascending") {
          this.tableData.sort(this.my_asc_sort);
        }
      }
    },
    //排序方法
    my_desc_sort(a, b) {
      return b[this.proptype] - a[this.proptype]; // a["time"] 等价于 a.time
    },
    my_asc_sort(a, b) {
      return a[this.proptype] - b[this.proptype];
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
        callback(list);
      });
    },
    // 部门模糊查询 (第一个参数， 用户输入的值  第二个值回调函数)
    queryDepartment(queryString, callback) {
      // 需要随时更改的（从后台获取到对象数组）
      querySearchDocGroup({
        docGroup: queryString
      }).then(res => {
        let list = [];
        if (res.data.fuzzyQueryDocGroupList === null) {
          list = [];
        } else {
          res.data.fuzzyQueryDocGroupList.forEach(item => {
            list.push({
              value: item
            });
          });
        }
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
        callback(list);
      });
    },
    // 主机名模糊查询 (第一个参数， 用户输入的值  第二个值回调函数)
    querySearchHostName(queryString, callback) {
      // 需要随时更改的（从后台获取到对象数组）
      queryKpiHostName({ hostName: queryString }).then(res => {
        let list = [];
        if (res.data.workingHostNameList === null) {
          list = [];
        } else {
          res.data.workingHostNameList.forEach(item => {
            list.push({
              value: item
            });
          });
        }
        callback(list);
      });
    },
    handleSelectScen(item) {},
    // 取消全选
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
      this.arr = [];
      this.selectedNum = 0;
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      if (val.length === 0) {
        this.arr = [];
        this.selectedNum = 0;
      }
      this.arr.push(val.length);
      this.arr.sort((a, b) => {
        return a - b;
      });
      document.onkeyup = e => {
        if (e.keyCode !== "") {
          const max = this.arr[this.arr.length - 1]; // 56
          this.selectedNum = max;
        } else {
          this.selectedNum = val.length;
        }
      };
      this.selectedNum = val.length;
    },
    // 这里是select事件开始
    pinSelect(item, index) {
      const data = this.$refs.multipleTable.tableData; // 获取所以数据
      const origin = this.origin; // 起点数 从-1开始
      const endIdx = index.index; // 终点数
      if (this.pin && item.includes(data[origin])) {
        // 判断按住
        const sum = Math.abs(origin - endIdx) + 1; // 这里记录终点
        const min = Math.min(origin, endIdx); // 这里记录起点
        let i = 0;
        while (i < sum) {
          const index = min + i;
          this.$refs.multipleTable.toggleRowSelection(data[index], true); // 通过ref打点调用方法，第二个必须为true
          i++;
        }
      } else {
        this.origin = index.index; // 没按住记录起点
      }
    },
    // 这里是select事件结束
    // 分页
    handleSizeChange(val) {
      this.pagesSize = val;
      this.pagesNum = 1;
      this.getTabList();
    },
    handleCurrentChange(val) {
      this.pagesNum = val;
      this.getTabList();
    },
    // 表格分页序号
    table_index(index) {
      return (this.pagesNum - 1) * this.pagesSize + index + 1;
    },
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
      this.form.endTime = this.times;
      this.form.startTime = this.Dtimes;
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
    // 表格渲染函数
    async getTabList() {
      const value = {
        pagesNum: this.pagesNum,
        pagesSize: this.pagesSize,
        company: this.form.company,
        employeeGroup: this.form.department,
        searchCriteria: this.form.searchCriteria,
        employeeName: this.form.name,
        hostName:this.form.hostName,
        actionBeginTime: this.form.startTime,
        actionEndTime: this.form.endTime
      };
      try {
        const { data } = await kpiTableInfo(value);
        const { kpiEntityList,tableTotalList } = data;
        this.total = tableTotalList[0]
        if (kpiEntityList === null) {
          this.tableData = ""
        } else {
          this.tableData = kpiEntityList;
        }
      } catch (e) {
        console.log(e);
      }
    },
    // 查询按钮
    async seachBtn() {
      // 首先验证，在执行回车事件
      this.$refs.form.validate();
      const ttime1 = this.form.startTime;
      const ttime2 = this.form.endTime;
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("请合理选择日期");
        return;
      }
      this.pagesNum = 1;
      await this.$refs.form.validate(isValid => {
        if (isValid) {
          this.getTabList(this.form);
        } else {
        }
      });
      this.form.searchCriteria =
        this.form.company + this.form.department + this.form.name + this.form.hostName;
      // this.getTableList(this.form)
    },
    // 保存按钮
    async save() {
      // const value = {
      // searchCriteria:this.form.searchCriteria
      // }
      await this.$refs.form.validate(isValid => {
        if (isValid) {
          const value = {
            searchCriteria: this.form.searchCriteria
          };
          try {
            kpiTableInfo(value);
            this.$message.success("保存成功");
            this.form.searchCriteria = "";
          } catch (e) {
            console.log(e);
          }
        } else {
        }
      });
    }
  },
  // 这里是获取键盘事件
  mounted() {
    window.addEventListener("keydown", code => {
      // 这个是获取键盘按住事件
      // console.log(code); // 这个是你按住键盘打印出键盘信息，在浏览器中自行查看
      if (code.keyCode === 16 && code.shiftKey) {
        // 判断是否按住shift键，是就把pin赋值为true
        this.pin = true;
      }
    });
    window.addEventListener("keyup", code => {
      // 这个是获取键盘松开事件
      if (code.keyCode === 16) {
        // 判断是否松开shift键，是就把pin赋值为false
        this.pin = false;
      }
      this.tableData.forEach((item, index) => {
        // 遍历索引,赋值给data数据
        item.index = index;
      });
    });
  }
};
</script>

<style lang="scss" scoped>
@import "../../../../../css/clientSideMonitor/behaviorAnalysis/kpiAnalysis/components/kpiDetailsQuery.css";
</style>
