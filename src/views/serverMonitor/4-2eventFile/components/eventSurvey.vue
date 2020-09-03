<template>
  <!-- 事件——概况 -->
  <div class="eventSurvey">
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
            style="width: 110px;"
          ></el-autocomplete>
        </div>
      </el-form>
    </div>
    <!-- 最近监控事件 -->
    <div class="monitEvent">最近监控事件</div>
    <!-- 最近监控事件表格  -->
    <div class="monitEventTable">
      <el-table :data="RecentMonitorEventsTable" border>
        <el-table-column prop="avatar" label="类型" width="92px">
          <template slot-scope="scope">
            <img v-for="item in scope" :key="item.id" :src="item.img" alt />
          </template>
        </el-table-column>
        <el-table-column prop="time" label="时间" width="258px" style="text-align: center;"></el-table-column>
        <el-table-column prop="info" label="信息"></el-table-column>
      </el-table>
    </div>
    <!-- 最近用户操作事件 -->
    <div class="monitEvent">最近用户操作事件</div>
    <!-- 最近用户操作事件表格  -->
    <div class="monitUserTable">
      <el-table :data="RecentUserOperationTable" border>
        <!-- <el-table-column prop="avatar" label="空空" width="92px">
          <template slot-scope="scope">
            <div v-for="item in scope.row.arr"
              :key="item.id" class="nameStyle"> {{item.type}}</div>
          </template>
        </el-table-column>-->
        <el-table-column prop="avatar" label="类型" width="92px">
          <template slot-scope="scope">
            <img v-for="item in scope" :key="item.id" :src="item.img" alt />
          </template>
        </el-table-column>
        <el-table-column prop="time" label="时间" width="258px" style="text-align: center;"></el-table-column>
        <el-table-column prop="id" label="ID"></el-table-column>
        <el-table-column prop="user" label="用户"></el-table-column>
        <el-table-column prop="ip" label="IP"></el-table-column>
        <el-table-column prop="operation" label="操作"></el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import {
  // 主机组模糊查询
  queryDownListGroup
} from "@/api/table.js";
export default {
  data() {
    return {
      // 概况表单数据
      surveyForm: {
        groupType: "",
        groupStartTime: "",
        groupEndTime: "",
        seachBox: "" // 状态
      },
      RecentMonitorEventsTable: [
        {
          time: "2020-07-04 15:50:58",
          info: "Tc Web Tier Events",
          img:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596519269904&di=ab8ed5e3e0bffa814177b9ab4a036165&imgtype=0&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D3138596021%2C3343248862%26fm%3D214%26gp%3D0.jpg"
        },
        {
          time: "2020-07-05 15:50:58",
          info: "三反五反刚刚上个",
          img:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596519269904&di=ab8ed5e3e0bffa814177b9ab4a036165&imgtype=0&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D3138596021%2C3343248862%26fm%3D214%26gp%3D0.jpg"
        },
        {
          time: "2020-07-05 15:50:58",
          info: "三反五反刚刚上个",
          img:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596519269904&di=ab8ed5e3e0bffa814177b9ab4a036165&imgtype=0&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D3138596021%2C3343248862%26fm%3D214%26gp%3D0.jpg"
        }
      ],
      RecentUserOperationTable: [
        {
          // 第一行
          arr: [
            // 红绿灯
            {
              id: 1,
              type: "0" //绿
            },
            {
              id: 2,
              type: "2" //黄
            },
            {
              id: 3,
              type: "2" //红
            }
          ],
          img:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596519269904&di=ab8ed5e3e0bffa814177b9ab4a036165&imgtype=0&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D3138596021%2C3343248862%26fm%3D214%26gp%3D0.jpg",
          time: "2020-07-04 15:50:58",
          id: "877",
          user: "zhangqiang",
          ip: "192.168.1.1",
          operation: "用户登录"
        },
        {
          img:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596519269904&di=ab8ed5e3e0bffa814177b9ab4a036165&imgtype=0&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D3138596021%2C3343248862%26fm%3D214%26gp%3D0.jpg",
          time: "2020-07-05 15:50:58",
          id: "876",
          user: "zhangqiang",
          ip: "192.168.1.2",
          operation: "用户登录"
        },
        {
          img:
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1596519269904&di=ab8ed5e3e0bffa814177b9ab4a036165&imgtype=0&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D3138596021%2C3343248862%26fm%3D214%26gp%3D0.jpg",
          time: "2020-07-05 15:50:58",
          id: "875",
          user: "zhangqiang",
          ip: "192.168.1.3",
          operation: "用户登录"
        }
      ]
    };
  },
  created() {
    // 默认一个月的日期填充
    this.getSumTimes();
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
    }
  },
  mounted() {}
};
</script>
<style lang="scss" scoped>
.eventSurvey {
  // 搜索样式
  .seachStyle {
    margin-top: 30px;
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
  .monitEventTable {
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
  // 表格
  .monitUserTable {
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
  // 最近监控事件
  .monitEvent {
    font-size: 20px;
    color: white;
    margin: 30px 0 10px 0;
    letter-spacing: 10px;
  }
  .nameStyle {
    width: 30px;
    float: left;
  }
}
</style>
