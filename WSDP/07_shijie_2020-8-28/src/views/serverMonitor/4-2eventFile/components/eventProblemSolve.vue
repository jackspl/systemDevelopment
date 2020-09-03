<template>
  <!-- 问题处理 -->
  <div class="eventProblemSolve">
    <!-- 搜索样式 -->
    <div class="seachStyle clearfix">
      <el-form :inline="true" :model="surveyForm" ref="form">
        <!-- 类型选择 -->
        <div class="typeChange">
          <el-form-item label="类型">
            <el-select v-model="surveyForm.groupType" placeholder="全部">
              <el-option label="正常" value="正常"></el-option>
              <el-option label="警告" value="警告"></el-option>
              <el-option label="紧急" value="紧急"></el-option>
            </el-select>
          </el-form-item>
        </div>
        <!-- 开始时间 -->
        <div class="startTime">
          <el-form-item label="开始日期" prop="groupStartTime">
            <el-date-picker
              v-model="surveyForm.groupStartTime"
              type="datetime"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              align="right"
              clearable
            ></el-date-picker>
          </el-form-item>
        </div>
        <!-- 结束时间 -->
        <div class="endTime">
          <el-form-item label="结束日期" prop="groupEndTime">
            <el-date-picker
              v-model="surveyForm.groupEndTime"
              type="datetime"
              format="yyyy-MM-dd"
              value-format="yyyy-MM-dd"
              clearable
            ></el-date-picker>
          </el-form-item>
        </div>

        <!-- 查询按钮 -->
        <div class="seachButton">
          <el-button type="primary" @click="groupSeachButton">查询</el-button>
        </div>

        <!-- 搜索按钮 -->
        <div class="seachIcon">
          <el-button type="primary" icon="el-icon-search" @click="seachBtn"></el-button>
        </div>
        <!-- 搜索框  -->
        <div class="seachBox">
          <el-autocomplete
            placeholder="搜索..."
            v-model="surveyForm.seachBox"
            clearable
            type="text"
            :debounce="0"
            :fetch-suggestions="queryDepartment"
            @select="handleSelectScen"
            style="width: 210px;"
          ></el-autocomplete>
        </div>
      </el-form>
    </div>
    <!-- 表格  -->
    <div class="tableBox">
      <el-table
        :data="table"
        :span-method="objectSpanMethod"
        border
        style="text-align: center;"
        @current-change="handleSelectionChange"
      >
        <el-table-column label="来源">
          <el-table-column prop="hostArr" label="主机"></el-table-column>
          <el-table-column prop="serviceArr" label="服务"></el-table-column>
        </el-table-column>
        <el-table-column label="问题">
          <el-table-column prop="ipArr" label="服务状态"></el-table-column>
          <el-table-column prop="indexArr" label="指标"></el-table-column>
        </el-table-column>
        <el-table-column label="处理">
          <el-table-column label="固定">
            <template slot-scope="scope">
              <el-checkbox v-model="scope.row.checked1"></el-checkbox>
            </template>
          </el-table-column>
          <el-table-column prop="functionArr" label="通知">
            <template slot-scope="scope">
              <el-checkbox v-model="scope.row.checked2"></el-checkbox>
            </template>
          </el-table-column>
          <el-table-column prop="testingArr" label="报错">
            <template slot-scope="scope">
              <el-checkbox v-model="scope.row.checked3"></el-checkbox>
            </template>
          </el-table-column>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
export function mergeTableRow(data, merge) {
  if (!merge || merge.length === 0) {
    return data;
  }
  merge.forEach(m => {
    const mList = {};
    data = data.map((v, index) => {
      const rowVal = v[m];
      if (mList[rowVal] && mList[rowVal].newIndex === index) {
        mList[rowVal]["num"]++;
        mList[rowVal]["newIndex"]++;
        data[mList[rowVal]["index"]][m + "-span"].rowspan++;
        v[m + "-span"] = {
          rowspan: 0,
          colspan: 0
        };
      } else {
        mList[rowVal] = { num: 1, index: index, newIndex: index + 1 };
        v[m + "-span"] = {
          rowspan: 1,
          colspan: 1
        };
      }
      return v;
    });
  });
  return data;
}
import {
  // 主机组模糊查询
  queryDownListGroup
} from "@/api/table.js";
import { tableData, tableTitle } from "./infoTable";
export default {
  data() {
    return {
      checked1: null, // 如果使用单选框,定义一个model值
      checked2: null, // 如果使用单选框,定义一个model值
      checked3: null, // 如果使用单选框,定义一个model值
      // 概况表单数据
      surveyForm: {
        groupType: "",
        groupStartTime: "",
        groupEndTime: "",
        seachBox: "" // 状态
      },
      // -----------------------------
      table: [
        {
          hostArr: "TC11",
          aliasArr: "TC11",
          ipArr: "192.168.1.20",
          serviceArr: "TC11_web",
          indexArr: "Tc Web Tier Events",
          stateArr: "紧急",
          attemptArr: "5/5",
          functionArr: "83天 3时 10分 18秒",
          testingArr: "2020-07-04 15:50:58",
          stateInfoArr: "1521/tc11",
          referenceLogArr: "web日志syslog",
          id: "3"
        },
        {
          hostArr: "TC11",
          aliasArr: "TC11",
          ipArr: "192.168.1.20",
          serviceArr: "TC11_web",
          indexArr: "Tc Web Tier Events",
          stateArr: "警告",
          attemptArr: "5/5",
          functionArr: "83天 3时 10分 18秒",
          testingArr: "2020-07-04 15:50:58",
          stateInfoArr: "1521/tc11",
          referenceLogArr: "web日志syslog",
          id: "1"
        },
        {
          hostArr: "TC12",
          aliasArr: "TC12",
          ipArr: "192.168.1.21",
          serviceArr: "TC12_web",
          indexArr: "Tc Web Tier Events",
          stateArr: "正常",
          attemptArr: "5/5",
          functionArr: "83天 3时 10分 18秒",
          testingArr: "2020-07-04 15:50:58",
          stateInfoArr: "1521/tc11",
          referenceLogArr: "web日志syslog",
          id: "2"
        },
        {
          hostArr: "TC12",
          aliasArr: "TC12",
          ipArr: "192.168.1.21",
          serviceArr: "TC12_web",
          indexArr: "Tc Web Tier Events",
          stateArr: "未知",
          attemptArr: "5/5",
          functionArr: "83天 3时 10分 18秒",
          testingArr: "2020-07-04 15:50:58",
          stateInfoArr: "1521/tc11",
          referenceLogArr: "web日志syslog",
          id: "4"
        }
      ]
    };
  },
  created() {
    // 给table赋值，重新遍历新增rowSpan属性，checkRoom，appointmentTime为table里面需要合并的属性名称
    this.table = mergeTableRow(this.table, [
      "hostArr",
      "aliasArr",
      "serviceArr"
    ]);
    // 默认一个月的日期填充
    this.getSumTimes();
  },
  methods: {
    // 表格里的单选按钮
    handleSelectionChange(row) {
      // el-radio单选框,不需要这一步
      this.table.forEach(item => {
        // 排他,每次选择时把其他选项都清除
        if (item.id !== row.id) {
          item.checked = false;
        }
      });
    },
    // 主机组模糊查询 (第一个参数， 用户输入的值  第二个值回调函数)
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
    handleSelectScen(item) {},
    // 搜索按钮
    seachBtn() {},
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

      this.surveyForm.groupEndTime = this.times;
      this.surveyForm.groupStartTime = this.Dtimes;
    },
    // 服务状态查询按钮
    async groupSeachButton() {
      console.log(123);
    },
    // -----------------------------
    // 动态表格数据
    objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      const span = column["property"] + "-span";
      if (row[span]) {
        return row[span];
      }
    }
  },
  mounted() {
    // -----------------------------
  }
};
</script>
<style lang="scss" scoped>
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
.eventProblemSolve {
  // 搜索样式
  .seachStyle {
    margin-top: 30px;
    margin-bottom: 20px;
    // 类型选择
    .typeChange {
      float: left;
    }
    // 开始时间
    .startTime {
      float: left;
    }
    // 结束时间
    .endTime {
      float: left;
    }
    // 查询按钮
    .seachButton {
      float: left;
    }
    // 图标查询按钮
    .seachIcon {
      float: right;
    }
    // 搜索框
    .seachBox {
      float: right;
      margin-right: 10px;
    }
    ::v-deep .el-form-item__label {
      color: white;
    }
    ::v-deep .el-input__inner {
      background-color: #06385c;
      border: 1px solid #0c7ebd;
      height: 40px;
      line-height: 40px;
      color: white;
    }
  }
  // 表格
  .tableBox {
    ::v-deep .el-table th {
      background-color: #153a6b;
      text-align: center;
    color: white;
    }
    ::v-deep .el-table thead.is-group th {
      background-color: #153a6b;
      text-align: center;
    }
    ::v-deep .el-table__body td {
      text-align: center;
      color: white;
    }
    ::v-deep .el-table--border th {
      border-bottom: 1px solid #3e74a3;
      border-right: 1px solid #3e74a3;
    }
    ::v-deep .el-table--border td {
      border-bottom: 1px solid #3e74a3;
      border-right: 1px solid #3e74a3;
      background-color: #153a6b;
      color: white;
    }
  }
}
</style>
