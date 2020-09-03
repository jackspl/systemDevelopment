<template>
  <!-- 概况 -->
  <div class="surveyUnit">
    <div class="topBox clearfix">
      <!-- 左侧服务概况 -->
      <div class="boxLeft">
        <div class="leftPei">
          <div id="topPei">
            <div id="allServer"></div>
          </div>
          <div id="bottomPei">
            <div id="allHost"></div>
          </div>
        </div>
        <div class="rightTab">
          <div class="topTab">
            <div class="m10">服务概况</div>
            <el-table :data="tableData" border>
              <el-table-column prop="1" label="全部"></el-table-column>
              <el-table-column prop="2" label="正常"></el-table-column>
              <el-table-column prop="3" label="警告"></el-table-column>
              <el-table-column prop="4" label="未知"></el-table-column>
              <el-table-column prop="5" label="紧急"></el-table-column>
              <el-table-column prop="6" label="问题"></el-table-column>
              <el-table-column prop="7" label="待定"></el-table-column>
              <el-table-column prop="8" label="未处理"></el-table-column>
            </el-table>
          </div>
          <div class="bottomTab">
            <div class="m10">问题来源主机排名</div>
            <el-table :data="hostTable" border height="200px">
              <el-table-column prop="1" label="主机" width="135px"></el-table-column>
              <el-table-column prop="2" label="全部" width="64px"></el-table-column>
              <el-table-column prop="3" label="正常" width="64px"></el-table-column>
              <el-table-column prop="4" label="警告" width="64px"></el-table-column>
              <el-table-column prop="5" label="未知" width="64px"></el-table-column>
              <el-table-column prop="6" label="紧急" width="64px"></el-table-column>
              <el-table-column prop="7" label="待定" width="64px"></el-table-column>
              <el-table-column prop="8" label="问题" width="64px"></el-table-column>
              <el-table-column prop="9" label="未处理" width="80px"></el-table-column>
            </el-table>
          </div>
        </div>
      </div>
      <!-- 右侧指标概况 -->
      <div class="boxRight">
        <div class="leftPei">
          <div id="topPei">
            <div id="allIndex"></div>
          </div>
          <div id="bottomPei">
            <div id="allHostIndex"></div>
          </div>
        </div>
        <div class="rightTab">
          <div class="topTab">
            <div class="m10">服务概况</div>
            <el-table :data="tableData" border>
              <el-table-column prop="1" label="全部"></el-table-column>
              <el-table-column prop="2" label="正常"></el-table-column>
              <el-table-column prop="3" label="警告"></el-table-column>
              <el-table-column prop="4" label="未知"></el-table-column>
              <el-table-column prop="5" label="紧急"></el-table-column>
              <el-table-column prop="6" label="问题"></el-table-column>
              <el-table-column prop="7" label="待定"></el-table-column>
              <el-table-column prop="8" label="未处理"></el-table-column>
            </el-table>
          </div>
          <div class="bottomTab">
            <div class="m10">问题来源主机排名</div>
            <el-table :data="hostTable" border height="200px">
              <el-table-column prop="1" label="主机" width="135px"></el-table-column>
              <el-table-column prop="2" label="全部" width="64px"></el-table-column>
              <el-table-column prop="3" label="正常" width="64px"></el-table-column>
              <el-table-column prop="4" label="警告" width="64px"></el-table-column>
              <el-table-column prop="5" label="未知" width="64px"></el-table-column>
              <el-table-column prop="6" label="紧急" width="64px"></el-table-column>
              <el-table-column prop="7" label="待定" width="64px"></el-table-column>
              <el-table-column prop="8" label="问题" width="64px"></el-table-column>
              <el-table-column prop="9" label="未处理" width="80px"></el-table-column>
            </el-table>
          </div>
        </div>
      </div>
    </div>
    <!-- 动态表格 -->
    <!-- 外围黑色盒子的 -->
    <div class="boxSum">
      <!-- 头部倾斜的 -->
      <el-scrollbar>
        <!-- 表格主体的 -->
        <div class="tableBox">
          <el-table
            :data="table"
            :span-method="objectSpanMethod"
            border
            style="text-align: center;"
          >
            <el-table-column prop="hostNames" label width="126"></el-table-column>
            <el-table-column prop="hostArr" label width="146"></el-table-column>
            <el-table-column prop="type" class="cellTable" label width="2900">
              <template slot="header" v-for="col in infoTable">
                <div class="boxLi" :key="col.index">{{col}}</div>
              </template>
              <template slot-scope="scope">
                <div v-for="item in scope.row.arr" :key="item.id" class="colorBox">
                  <span
                    :class="{'yellow':item.type=='0','green':item.type=='1','red':item.type=='2','colorNone':item.type=='3'}"
                  ></span>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-scrollbar>
    </div>
  </div>
</template>
<script>
export function mergeTableRow(data, merge) {
  if (!merge || merge.length === 0) {
    return data;
  }
  merge.forEach((m) => {
    const mList = {};
    data = data.map((v, index) => {
      const rowVal = v[m];
      if (mList[rowVal] && mList[rowVal].newIndex === index) {
        mList[rowVal]["num"]++;
        mList[rowVal]["newIndex"]++;
        data[mList[rowVal]["index"]][m + "-span"].rowspan++;
        v[m + "-span"] = {
          rowspan: 0,
          colspan: 0,
        };
      } else {
        mList[rowVal] = { num: 1, index: index, newIndex: index + 1 };
        v[m + "-span"] = {
          rowspan: 1,
          colspan: 1,
        };
      }
      return v;
    });
  });
  return data;
}
import {
  // 12、公司KPI贡献接口
  comloyeKpiPie,
} from "@/api/table.js";
import { tableData, tableTitle } from "./mock";
export default {
  data() {
    return {
      tableData: [
        {
          1: "5976",
          2: "4283",
          3: "1186",
          4: "63",
          5: "95",
          6: "36",
          7: "35",
          8: "78",
        },
        {
          1: "100%",
          2: "24.9%",
          3: "1.4%",
          4: "2.5%",
          5: "0.7%",
          6: "0.9%",
          7: "2.9%",
          8: "78%",
        },
      ],
      hostTable: [
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760",
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760",
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760",
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760",
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760",
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760",
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760",
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760",
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760",
          5: "760",
          6: "760",
          7: "760",
          8: "760",
          9: "760",
        },
      ],
      // -----------------------------
      // 动态表格数据
      table: [
        {
          hostNames: "host_group11",
          hostArr: "TC11",
          // 红绿灯
          arr: [
            {
              id: 1,
              type: "0", //黄色
            },
            {
              id: 2,
              type: "1", //绿色
            },
            {
              id: 3,
              type: "2", //红色
            },
            {
              id: 4,
              type: "3", //正常
            },
          ],
        },
        {
          hostNames: "host_group11",
          hostArr: "TC12",
          // 第一行
          arr: [
            // 红绿灯
            {
              id: 1,
              type: "0", //绿
            },
            {
              id: 2,
              type: "2", //黄
            },
            {
              id: 3,
              type: "2", //红
            },
          ],
        },
        {
          hostNames: "host_group22",
          hostArr: "TC11",
          // 第一行
          arr: [
            // 红绿灯
            {
              id: 4,
              type: "0", //绿
            },
            {
              id: 5,
              type: "3", //黄
            },
            {
              id: 6,
              type: "2", //红
            },
            {
              id: 6,
              type: "3", //红
            },
          ],
        },
        {
          hostNames: "host_group22",
          hostArr: "TC12",
          // 第一行
          arr: [
            // 红绿灯
            {
              id: 4,
              type: "0", //绿
            },
            {
              id: 5,
              type: "1", //黄
            },
            {
              id: 6,
              type: "2", //红
            },
          ],
        },
        {
          hostNames: "host_group22",
          hostArr: "TC12",
          // 第一行
          arr: [
            // 红绿灯
            {
              id: 4,
              type: "0", //绿
            },
            {
              id: 5,
              type: "1", //黄
            },
            {
              id: 6,
              type: "2", //红
            },
            {
              id: 4,
              type: "3", //绿
            },
            {
              id: 5,
              type: "3", //黄
            },
            {
              id: 6,
              type: "1", //红
            },
          ],
        },
        {
          hostNames: "host_group22",
          hostArr: "TC12",
          // 第一行
          arr: [
            // 红绿灯
            {
              id: 4,
              type: "0", //绿
            },
            {
              id: 5,
              type: "1", //黄
            },
            {
              id: 6,
              type: "2", //红
            },
          ],
        },
        {
          hostNames: "host_group22",
          hostArr: "TC12",
          // 第一行
          arr: [
            // 红绿灯
            {
              id: 4,
              type: "0", //绿
            },
            {
              id: 5,
              type: "1", //黄
            },
            {
              id: 6,
              type: "2", //红
            },
          ],
        },
        {
          hostNames: "host_group22",
          hostArr: "TC12",
          // 第一行
          arr: [
            // 红绿灯
            {
              id: 4,
              type: "0", //绿
            },
            {
              id: 5,
              type: "1", //黄
            },
            {
              id: 6,
              type: "2", //红
            },
          ],
        },
        {
          hostNames: "host_group33",
          hostArr: "TC12",
          // 第一行
          arr: [
            // 红绿灯
            {
              id: 4,
              type: "0", //绿
            },
            {
              id: 5,
              type: "1", //黄
            },
            {
              id: 6,
              type: "2", //红
            },
          ],
        },
        {
          hostNames: "host_group33",
          hostArr: "TC13",
          // 第一行
          arr: [
            // 红绿灯
            {
              id: 4,
              type: "0", //绿
            },
            {
              id: 5,
              type: "1", //黄
            },
            {
              id: 6,
              type: "2", //红
            },
          ],
        },
        {
          hostNames: "host_group33",
          hostArr: "TC14",
          // 第一行
          arr: [
            // 红绿灯
            {
              id: 4,
              type: "0", //绿
            },
            {
              id: 5,
              type: "1", //黄
            },
            {
              id: 6,
              type: "2", //红
            },
          ],
        },
      ],
      // 表格头部倾斜数据
      infoTable: ["123456789", "123456789123456789", "123", "12398", "12398"],
    };
  },
  created() {
    // 全部服务饼图
    this.getAllServer();

    // 全部服务饼图
    this.getAllHost();

    // 全部指标饼图
    this.getAllIndex();
    // 全部服务指标饼图
    this.getAllHostIndex();
    this.table = mergeTableRow(this.table, ["hostNames"]);
  },
  methods: {
    // 提取全部服务公共饼图函数
    async getAllServer(index = 1) {
      const value = {
        actionBeginTime: "2020-06-21 10:10:34",
        actionEndTime: "2020-07-21 10:10:34",
        kpiContributionFlag: 1,
      };
      const { data } = await comloyeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.map((item, index) => {
        const comRandColor = this.randColor(item, index);
        return {
          value: item.KPI,
          name:
            item.company +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          itemStyle: {
            normal: {
              color: comRandColor,
            },
          },
        };
      });
      this.serverPei(newArr, allKPI);
    },
    // 全部服务饼图函数
    serverPei(arr, allKPI) {
      let myChart1 = this.$echarts.init(document.getElementById("allServer"));
      this.initHeight1 = document.getElementById("topPei").offsetHeight;
      this.initWidth1 = document.getElementById("topPei").offsetWidth;
      const option1 = {
        animation: false,
        title: {
          text: "全部服务",
          subtext: allKPI,
          x: "center",
          y: "40%",
          textStyle: {
            fontWeight: "normal",
            fontSize: 14,
            color: "white",
          },
          subtextStyle: {
            fontSize: 14,
            color: "white",
          },
        },
        // 鼠标放上去有提示
        tooltip: {
          show: true,
          trigger: "item",
          formatter: "{b}：{c}",
          // formatter: '{b}：{c}个<br/>占比：({d}%)'
        },
        series: [
          {
            type: "pie",
            stillShowZeroSum: true,
            //     外圈大小  内圈大小
            radius: ["80%", "50%"],
            //     左右居中  上下居中
            center: ["50%", "50%"],
            // 饼图轮廓阴影
            itemStyle: {
              normal: {
                shadowBlur: 30,
                shadowColor: "rgba(255,227,42,0.3)",
                shadowOffsetX: -5,
                shadowOffsetY: 5,
              },
            },
            label: {
              normal: {
                position: "inner",
                show: false,
                textStyle: {
                  color: "#fff",
                  fontSize: 12,
                },
              },
            },
            labelLine: {
              normal: {
                show: false,
              },
            },
            data: arr,
          },
        ],
      };
      myChart1.setOption(option1);
      let resize1 = {
        height: this.initHeight1,
        width: this.initWidth1,
      };
      myChart1.resize(resize1);
    },
    // ----------------------------------
    // 提取全部主机公共饼图函数
    async getAllHost(index = 1) {
      const value = {
        actionBeginTime: "2020-06-21 10:10:34",
        actionEndTime: "2020-07-21 10:10:34",
        kpiContributionFlag: 1,
      };
      const { data } = await comloyeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.map((item, index) => {
        const comRandColor = this.randColor(item, index);
        return {
          value: item.KPI,
          name:
            item.company +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          itemStyle: {
            normal: {
              color: comRandColor,
            },
          },
        };
      });
      this.hostPei(newArr, allKPI);
    },
    // 全部主机饼图函数
    hostPei(arr, allKPI) {
      let myChart2 = this.$echarts.init(document.getElementById("allHost"));
      this.initHeight2 = document.getElementById("bottomPei").offsetHeight;
      this.initWidth2 = document.getElementById("bottomPei").offsetWidth;
      const option2 = {
        animation: false,
        title: {
          text: "全部主机",
          subtext: allKPI,
          x: "center",
          y: "50%",
          textStyle: {
            fontWeight: "normal",
            fontSize: 14,
            color: "white",
          },
          subtextStyle: {
            fontSize: 14,
            color: "white",
          },
        },
        // 鼠标放上去有提示
        tooltip: {
          show: true,
          trigger: "item",
          formatter: "{b}：{c}",
          // formatter: '{b}：{c}个<br/>占比：({d}%)'
        },
        series: [
          {
            type: "pie",
            stillShowZeroSum: true,
            //     外圈大小  内圈大小
            radius: ["90%", "50%"],
            //     左右居中  上下居中
            center: ["50%", "60%"],
            // 饼图轮廓阴影
            itemStyle: {
              normal: {
                shadowBlur: 30,
                shadowColor: "rgba(255,227,42,0.3)",
                shadowOffsetX: -5,
                shadowOffsetY: 5,
              },
            },
            label: {
              normal: {
                position: "inner",
                show: false,
                textStyle: {
                  color: "#fff",
                  fontSize: 12,
                },
              },
            },
            labelLine: {
              normal: {
                show: false,
              },
            },
            data: arr,
          },
        ],
      };
      myChart2.setOption(option2);
      let resize2 = {
        height: this.initHeight2,
        width: this.initWidth2,
      };
      myChart2.resize(resize2);
    },
    // --------------------------------------
    // 提取全部指标公共饼图函数
    async getAllIndex(index = 1) {
      const value = {
        actionBeginTime: "2020-06-21 10:10:34",
        actionEndTime: "2020-07-21 10:10:34",
        kpiContributionFlag: 1,
      };
      const { data } = await comloyeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.map((item, index) => {
        const comRandColor = this.randColor(item, index);
        return {
          value: item.KPI,
          name:
            item.company +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          itemStyle: {
            normal: {
              color: comRandColor,
            },
          },
        };
      });
      this.indexPei(newArr, allKPI);
    },
    // 全部指标饼图函数
    indexPei(arr, allKPI) {
      let myChart3 = this.$echarts.init(document.getElementById("allIndex"));
      this.initHeight3 = document.getElementById("topPei").offsetHeight;
      this.initWidth3 = document.getElementById("topPei").offsetWidth;
      const option3 = {
        animation: false,
        title: {
          text: "全部指标",
          subtext: allKPI,
          x: "center",
          y: "40%",
          textStyle: {
            fontWeight: "normal",
            fontSize: 14,
            color: "white",
          },
          subtextStyle: {
            fontSize: 14,
            color: "white",
          },
        },
        // 鼠标放上去有提示
        tooltip: {
          show: true,
          trigger: "item",
          formatter: "{b}：{c}",
          // formatter: '{b}：{c}个<br/>占比：({d}%)'
        },
        series: [
          {
            type: "pie",
            stillShowZeroSum: true,
            //     外圈大小  内圈大小
            radius: ["80%", "50%"],
            //     左右居中  上下居中
            center: ["50%", "50%"],
            // 饼图轮廓阴影
            itemStyle: {
              normal: {
                shadowBlur: 30,
                shadowColor: "rgba(255,227,42,0.3)",
                shadowOffsetX: -5,
                shadowOffsetY: 5,
              },
            },
            label: {
              normal: {
                position: "inner",
                show: false,
                textStyle: {
                  color: "#fff",
                  fontSize: 12,
                },
              },
            },
            labelLine: {
              normal: {
                show: false,
              },
            },
            data: arr,
          },
        ],
      };
      myChart3.setOption(option3);
      let resize3 = {
        height: this.initHeight3,
        width: this.initWidth3,
      };
      myChart3.resize(resize3);
    },
    // --------------------------------------
    // 提取全部主机指标公共饼图函数
    async getAllHostIndex(index = 1) {
      const value = {
        actionBeginTime: "2020-06-21 10:10:34",
        actionEndTime: "2020-07-21 10:10:34",
        kpiContributionFlag: 1,
      };
      const { data } = await comloyeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.map((item, index) => {
        const comRandColor = this.randColor(item, index);
        return {
          value: item.KPI,
          name:
            item.company +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          itemStyle: {
            normal: {
              color: comRandColor,
            },
          },
        };
      });
      this.hostIndexPei(newArr, allKPI);
    },
    // 全部主机指标饼图函数
    hostIndexPei(arr, allKPI) {
      let myChart4 = this.$echarts.init(
        document.getElementById("allHostIndex")
      );
      this.initHeight4 = document.getElementById("bottomPei").offsetHeight;
      this.initWidth4 = document.getElementById("bottomPei").offsetWidth;
      const option4 = {
        animation: false,
        title: {
          text: "全部主机",
          subtext: allKPI,
          x: "center",
          y: "50%",
          textStyle: {
            fontWeight: "normal",
            fontSize: 14,
            color: "white",
          },
          subtextStyle: {
            fontSize: 14,
            color: "white",
          },
        },
        // 鼠标放上去有提示
        tooltip: {
          show: true,
          trigger: "item",
          formatter: "{b}：{c}",
          // formatter: '{b}：{c}个<br/>占比：({d}%)'
        },
        series: [
          {
            type: "pie",
            stillShowZeroSum: true,
            //     外圈大小  内圈大小
            radius: ["90%", "50%"],
            //     左右居中  上下居中
            center: ["50%", "60%"],
            // 饼图轮廓阴影
            itemStyle: {
              normal: {
                shadowBlur: 30,
                shadowColor: "rgba(255,227,42,0.3)",
                shadowOffsetX: -5,
                shadowOffsetY: 5,
              },
            },
            label: {
              normal: {
                position: "inner",
                show: false,
                textStyle: {
                  color: "#fff",
                  fontSize: 12,
                },
              },
            },
            labelLine: {
              normal: {
                show: false,
              },
            },
            data: arr,
          },
        ],
      };
      myChart4.setOption(option4);
      let resize4 = {
        height: this.initHeight4,
        width: this.initWidth4,
      };
      myChart4.resize(resize4);
    },
    // 随机颜色
    randColor(item, index) {
      // const Arr = ['red', 'black', 'yellow', 'blue', '#FD70B8',
      // '#CBEAED', '#8EEBAC', '#6139E5', '#F38946', '#7BA5F4', '#3CFA89'
      // , '#4FEE1F', '#CB59F6', '#FDB10F', '#EBD50C', '#F0D54E']
      const Arr = [
        "#CBEAED",
        "#8EEBAC",
        "#6139E5",
        "#F38946",
        "#7BA5F4",
        "#3CFA89",
        "#4FEE1F",
        "#CB59F6",
        "#6139E5",
        "#F38946",
        "yellow",
        "blue",
        "red",
        "black",
        "yellow",
        "blue",
        "red",
        "black",
        "yellow",
        "blue",
        "red",
        "black",
        "yellow",
        "blue",
        "red",
        "black",
        "yellow",
        "blue",
      ];
      // const color = Math.floor(Math.random() * Arr.length + 1)-1;
      const color = Arr[index];
      return color;
    },
    // -----------------------------
    // 动态表格数据
    // 动态表格数据
    objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      const span = column["property"] + "-span";
      if (row[span]) {
        return row[span];
      }
    },
  },
  mounted() {
    // -----------------------------
  },
};
</script>
<style lang="scss" scoped>
.surveyUnit {
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

  .topBox {
    margin-bottom: 30px;
    ::v-deep .el-table__body td {
      background-color: #02356f;
      color: white;
      border-bottom: 1px solid #3e74a3;
      border-right: 1px solid #3e74a3;
      text-align: center;
    }
    ::v-deep .el-table th,
    .el-table tr {
      background-color: #02356f;
      color: white;
      border-bottom: 1px solid #3e74a3;
      border-right: 1px solid #3e74a3;
      text-align: center;
    }
    // 左侧服务概况
    .boxLeft {
      width: 50%;
      height: 430px;
      border: 1px solid #3e74a3;
      background-color: #193153;
      box-sizing: border-box;
      margin-top: 10px;
      float: left;
      border-radius: 5px;

      .leftPei {
        width: 20%;
        height: 100%;
        box-sizing: border-box;
        float: left;
        #topPei {
          height: 200px;
          z-index: 999;
        }
        #bottomPei {
          height: 200px;
          z-index: 999;
        }
      }
      .rightTab {
        width: 80%;
        height: 100%;
        box-sizing: border-box;
        float: left;

        .topTab {
          text-align: center;
          .m10 {
            margin: 10px 0;
            color: white;
            font-size: 20px;
          }
        }
        .bottomTab {
          .m10 {
            margin: 10px 0;
            color: white;
            font-size: 20px;
          }
          ::v-deep .el-table th,
          .el-table tr {
            background-color: #02356f;
            color: white;
            border-bottom: 1px solid #3e74a3;
            border-right: 1px solid #3e74a3;
            text-align: center;
          }
          text-align: center;
          margin-top: 10px;
          .el-table__header tr,
          .el-table__header th {
            padding: 0;
            height: 40px;
          }
          ::v-deep .el-table__body tr,
          .el-table__body td {
            padding: 0;
            height: 24px;
          }
          /* 最为关键得两个样式代码，可以设置全局滚动条样式，也可以按需设置 */
          ::-webkit-scrollbar {
            /* 设置竖向滚动条的宽度 */
            width: 4px;
            /* 设置横向滚动条的高度 */
            height: 5px;
          }
          ::-webkit-scrollbar-thumb {
            /*滚动条的背景色*/
            background-color: rgba(144, 147, 153, 0.3);
            border-radius: 5px;
            /* 设置竖向滚动条的宽度 */
            width: 4px;
            /* 设置横向滚动条的高度 */
            height: 5px;
          }
        }
      }
    }
    // 右侧指标概况
    .boxRight {
      width: 50%;
      height: 430px;
      background-color: #193153;
      box-sizing: border-box;
      margin-top: 10px;
      float: left;
      border-radius: 5px;
      .leftPei {
        width: 20%;
        height: 100%;
        background-color: #02356f;
        box-sizing: border-box;
        float: left;
        #topPei {
          height: 200px;
          z-index: 999;
        }
        #bottomPei {
          height: 200px;
          z-index: 999;
        }
      }
      .rightTab {
        width: 80%;
        height: 100%;
        box-sizing: border-box;
        float: left;
        .topTab {
          text-align: center;
          .m10 {
            margin: 10px 0;
            color: white;
            font-size: 20px;
          }
          .el-table th,
          .el-table tr {
            background-color: #02356f;
            color: white;
            border-bottom: 1px solid #3e74a3;
            border-right: 1px solid #3e74a3;
            text-align: center;
          }
        }
        .bottomTab {
          .m10 {
            margin: 10px 0;
            color: white;
            font-size: 20px;
          }
          .el-table th,
          .el-table tr {
            background-color: #02356f;
            color: white;
            border-bottom: 1px solid #3e74a3;
            border-right: 1px solid #3e74a3;
            text-align: center;
          }
          text-align: center;
          margin-top: 10px;
          .el-table__header tr,
          .el-table__header th {
            padding: 0;
            height: 40px;
          }
          .el-table__body tr,
          .el-table__body td {
            padding: 0;
            height: 24px;
          }
          /* 最为关键得两个样式代码，可以设置全局滚动条样式，也可以按需设置 */
          ::-webkit-scrollbar {
            /* 设置竖向滚动条的宽度 */
            width: 4px;
            /* 设置横向滚动条的高度 */
            height: 5px;
          }
          ::-webkit-scrollbar-thumb {
            /*滚动条的背景色*/
            background-color: rgba(144, 147, 153, 0.3);
            border-radius: 5px;
            /* 设置竖向滚动条的宽度 */
            width: 4px;
            /* 设置横向滚动条的高度 */
            height: 5px;
          }
        }
      }
    }
  }
  .boxSum {
    width: 100%;
    height: 500px;
    border-bottom: 1px solid #344e77;
    border-top: 1px solid #344e77;
    border-radius: 5px;
    overflow-y: auto;
    overflow-x: auto;
    margin: 0 auto;
    white-space: nowrap;
    &::-webkit-scrollbar {
      width: 6px;
    }

    &::-webkit-scrollbar-thumb {
      background: rgba(20, 20, 20, 0.2);
      border-radius: 3px;
    }

    &::-webkit-scrollbar-track {
      background: transparent;
    }
    ::v-deep .el-table__body td {
      background-color: #02356f;
      color: white;
      border-bottom: 1px solid #3e74a3;
      border-right: 1px solid #3e74a3;
      text-align: center;
    }
    ::v-deep .el-table th,
    .el-table tr {
      background-color: #02356f;
      color: white;
      border-bottom: 1px solid #3e74a3;
      border-right: 1px solid #3e74a3;
      text-align: center;
    }
    ::v-deep .el-table tbody tr:hover > td {
      background-color: #02356f !important;
    }
    ::v-deep .el-table .cell {
      padding: 0;
    }
    .tableBox {
      .cellTable .cell,
      .el-table th div {
        padding-left: 0px;
        padding-right: 0px;
      }
      .boxLi {
        width: 100px;
        margin-top: 30px;
        float: left;
        text-align: left;
        // margin-top: 156px;
        margin-bottom: 30px;
        margin-right: -60px;
        float: left;
        /* Rotate div */
        transform: rotate(300deg);
        -ms-transform: rotate(300deg); /* Internet Explorer */
        -moz-transform: rotate(300deg); /* Firefox */
        -webkit-transform: rotate(300deg); /* Safari 和 Chrome */
        -o-transform: rotate(300deg); /* Opera */
      }
      .colorBox {
        width: 40px;
        height: 40px;
        padding-top: 10px;
        border-right: 1px solid #3e74a3;
        float: left;

        .red {
          color: red;
          width: 20px;
          height: 20px;
          background-color: red;
          display: block;
          border-radius: 50%;
          margin: 0 10px;
          float: left;
        }
        .green {
          display: block;
          border-radius: 50%;
          margin: 0 10px;
          float: left;
          color: rgb(8, 255, 41);
          width: 20px;
          height: 20px;
          background-color: rgb(8, 255, 41);
        }
        .yellow {
          display: block;
          border-radius: 50%;
          margin: 0 10px;
          float: left;
          color: rgb(255, 208, 0);
          width: 20px;
          height: 20px;
          background-color: rgb(255, 208, 0);
        }
        .colorNone {
          display: block;
          border-radius: 50%;
          margin: 0 10px;
          float: left;
          color: #02356f;
          width: 20px;
          height: 20px;
          background-color: #02356f;
        }
      }
    }
  }
  /* 最为关键得两个样式代码，可以设置全局滚动条样式，也可以按需设置 */
  .boxSum ::-webkit-scrollbar {
    /* 设置竖向滚动条的宽度 */
    width: 5px;
    /* 设置横向滚动条的高度 */
    height: 5px;
  }
  .boxSum ::-webkit-scrollbar-thumb {
    /*滚动条的背景色*/
    background-color: rgba(144, 147, 153, 0.3);
    border-radius: 35px;
    position: relative;
  }
}
</style>
