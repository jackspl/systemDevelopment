<template>
  <!-- 详情 -->
  <div class="detailsUnit">
    <!-- 搜索的所有样式 -->
    <div class="seachAll clearfix">
      <el-form :inline="true" :model="seachForm" ref="form">
        <!-- 主机组 -->
        <div class="hostGroup">
          <el-form-item label="主机组">
            <el-autocomplete
              placeholder="全部"
              v-model="seachForm.hostGroup"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="queryDepartment"
              @select="handleSelectScen"
              style="width: 110px;"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <!-- 主机  -->
        <div class="host">
          <el-form-item label="主机" style="margin-left:10px">
            <el-autocomplete
              placeholder="全部"
              v-model="seachForm.host"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="queryDepartment"
              @select="handleSelectScen"
              style="width: 110px;"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <!-- 服务组 -->
        <div class="serviceGroup">
          <el-form-item label="服务组" style="margin-left:10px">
            <el-autocomplete
              placeholder="全部"
              v-model="seachForm.serviceGroup"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="queryDepartment"
              @select="handleSelectScen"
              style="width: 110px;"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <!-- 服务  -->
        <div class="service">
          <el-form-item label="服务" style="margin-left:10px">
            <el-autocomplete
              placeholder="全部"
              v-model="seachForm.service"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="queryDepartment"
              @select="handleSelectScen"
              style="width: 110px;"
            ></el-autocomplete>
          </el-form-item>
        </div>
        <!-- 状态 -->
        <div class="state">
          <el-form-item label="服务" style="margin-left:10px">
            <el-autocomplete
              placeholder="全部"
              v-model="seachForm.state"
              clearable
              type="text"
              :debounce="0"
              :fetch-suggestions="queryDepartment"
              @select="handleSelectScen"
              style="width: 110px;"
            ></el-autocomplete>
          </el-form-item>
        </div>

        <!-- 搜索按钮 -->
        <div class="seachButton">
          <el-button type="primary" @click="seachBtn">查询</el-button>
        </div>
        <!-- 搜索框  -->
        <div class="seachBox">
          <el-autocomplete
            placeholder="搜索..."
            v-model="seachForm.seachBox"
            clearable
            type="text"
            :debounce="0"
            :fetch-suggestions="queryDepartment"
            @select="handleSelectScen"
            style="width: 110px;"
          ></el-autocomplete>
        </div>
      </el-form>
    </div>
    <div class="tableBox">
      <el-table :data="table" :span-method="objectSpanMethod" border style="text-align: center;">
        <el-table-column prop="hostArr" label="主机" width="95"></el-table-column>
        <el-table-column prop="aliasArr" label="别名" width="70"></el-table-column>
        <el-table-column prop="ipArr" label="IP" width="126"></el-table-column>
        <el-table-column prop="serviceArr" label="服务" width="90"></el-table-column>
        <el-table-column prop="indexArr" label="指标" width="234"></el-table-column>
        <el-table-column prop="stateArr" label="状态" class="p0" width="84">
          <template slot-scope="{row}">
            <div
              v-if="row.stateArr === '紧急'"
              class="p0"
              style="background-color: red;padding:0px;line-height: 48px;padding-left: 0px;padding-right: 0px;"
            >{{row.stateArr}}</div>
            <div
              v-if="row.stateArr === '警告'"
              class="p0"
              style="background-color: #ba8c54;padding:0px;line-height: 48px;padding-left: 0px;padding-right: 0px;"
            >{{row.stateArr}}</div>
            <div
              v-if="row.stateArr === '正常'"
              class="p0"
              style="background-color: #559440;padding:0px;line-height: 48px;padding-left: 0px;padding-right: 0px;"
            >{{row.stateArr}}</div>
            <div
              v-if="row.stateArr === '未知'"
              class="p0"
              style="background-color: #8e959f;padding:0px;line-height: 48px;padding-left: 0px;padding-right: 0px;"
            >{{row.stateArr}}</div>
          </template>
        </el-table-column>
        <el-table-column prop="attemptArr" label="尝试" width="54"></el-table-column>
        <el-table-column prop="functionArr" label="运行时长" width="174"></el-table-column>
        <el-table-column prop="testingArr" label="上次检测" width="194"></el-table-column>
        <el-table-column prop="stateInfoArr" label="状态信息" width="378"></el-table-column>
        <el-table-column prop="referenceLogArr" label="参考日志"></el-table-column>
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
      // 搜索表单数据
      seachForm: {
        hostGroup: "", // 主机组
        host: "", // 主机
        serviceGroup: "", // 服务组
        service: "", // 服务
        state: "", // 状态
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
          referenceLogArr: "web日志syslog"
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
          referenceLogArr: "web日志syslog"
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
          referenceLogArr: "web日志syslog"
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
          referenceLogArr: "web日志syslog"
        }
      ]
    };
  },
  created() {
    // 给table赋值，重新遍历新增rowSpan属性，checkRoom，appointmentTime为table里面需要合并的属性名称
    this.table = mergeTableRow(this.table, [
      "hostArr",
      "aliasArr",
      "ipArr",
      "serviceArr"
    ]);
  },
  methods: {
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
.detailsUnit {
  // 搜索的所有样式
  .seachAll {
    height: 30px;
    line-height: 30px;
    margin-top: 20px;
    margin-bottom: 20px;
    // 主机组
    .hostGroup {
      float: left;
    }
    // 主机
    .host {
      float: left;
    }
    // 服务组
    .serviceGroup {
      float: left;
    }
    // 服务
    .service {
      float: left;
    }
    // 状态
    .state {
      float: left;
    }
    // 搜索按钮
    .seachButton {
      float: right;
    }
    // 搜索框
    .seachBox {
      float: right;
    }
    ::v-deep .el-form-item__label {
      color: white;
    }
    ::v-deep .el-input__inner {
      background-color: #06385c;
      border: 1px solid #0c7ebd;
      height: 30px;
      line-height: 30px;
      color: white;
    }
    ::v-deep .el-button--primary {
      height: 30px;
      line-height: 0px;
      margin-left: 10px;
    }
  }
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
    ::v-deep .el-table tbody tr:hover > td {
      background-color: #153a6b !important;
    }
    ::v-deep .el-table .cell {
      padding: 0;
    }
  }
}
</style>
