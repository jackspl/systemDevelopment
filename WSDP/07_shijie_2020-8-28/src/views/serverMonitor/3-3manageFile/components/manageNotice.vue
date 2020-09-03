<template>
  <!-- 通知 -->
  <div class="manageNotice">
    <!-- 头部启用通知 -->
    <div class="manTop clearfix">
      <div class="t-left">
        <el-checkbox @change="enable" v-model="checked">启用通知</el-checkbox>
      </div>
      <div class="t-right">
        <el-button type="primary">主要按钮</el-button>
      </div>
    </div>
    <!-- 主体一 -->
    <div class="manMain clearfix">
      <div class="m-left">
        <div class="hostNotice">主机事件通知</div>
        <div class="hostTable">
          <el-table :data="hostTable" border height="320px">
            <el-table-column prop="1" label width="220" style="text-align: left;"></el-table-column>
            <el-table-column prop="2" label="邮件">
              <template slot-scope="scope">
                <el-checkbox v-model="scope.row.hostChecked1"></el-checkbox>
              </template>
            </el-table-column>
            <el-table-column prop="3" label="短信">
              <template slot-scope="scope">
                <el-checkbox v-model="scope.row.hostChecked2"></el-checkbox>
              </template>
            </el-table-column>
            <el-table-column prop="4" label="电话">
              <template slot-scope="scope">
                <el-checkbox v-model="scope.row.hostChecked3"></el-checkbox>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
      <div class="m-right">
        <div class="serverNotice">服务监控通知</div>
        <div class="serverTable">
          <el-table :data="serverTable" border height="320px">
            <el-table-column prop="1" label width="220" style="text-align: left;"></el-table-column>
            <el-table-column prop="2" label="邮件">
              <template slot-scope="scope">
                <el-checkbox v-model="scope.row.serverChecked1"></el-checkbox>
              </template>
            </el-table-column>
            <el-table-column prop="3" label="短信">
              <template slot-scope="scope">
                <el-checkbox v-model="scope.row.serverChecked2"></el-checkbox>
              </template>
            </el-table-column>
            <el-table-column prop="4" label="电话">
              <template slot-scope="scope">
                <el-checkbox v-model="scope.row.serverChecked3"></el-checkbox>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </div>
    <!-- 主体二 -->
    <div class="receiveMain clearfix">
      <div class="rece-left">
        <div class="hostNotice">接收通知的时间</div>
        <div class="re-time clearfix">
          <el-form ref="form" :model="form">
            <ul>
              <li class="fl fl33 oneColor">sd</li>
              <li class="fl fl33">起始时间</li>
              <li class="fl fl33">截止时间</li>
              <li class="fl fl33 pl20">星期一</li>
              <li class="fl fl33">
                <el-time-picker placeholder="9:00" v-model="form.date1" format="HH:mm"></el-time-picker>
              </li>
              <li class="fl fl33">
                <el-time-picker placeholder="17:00" v-model="form.date2" format="HH:mm"></el-time-picker>
              </li>
              <li class="fl fl33 pl20">星期二</li>
              <li class="fl fl33">
                <el-time-picker placeholder="9:00" v-model="form.date3" format="HH:mm"></el-time-picker>
              </li>
              <li class="fl fl33">
                <el-time-picker placeholder="17:00" v-model="form.date4" format="HH:mm"></el-time-picker>
              </li>
              <li class="fl fl33 pl20">星期三</li>
              <li class="fl fl33">
                <el-time-picker placeholder="9:00" v-model="form.date5" format="HH:mm"></el-time-picker>
              </li>
              <li class="fl fl33">
                <el-time-picker placeholder="17:00" v-model="form.date6" format="HH:mm"></el-time-picker>
              </li>
              <li class="fl fl33 pl20">星期四</li>
              <li class="fl fl33">
                <el-time-picker placeholder="9:00" v-model="form.date7" format="HH:mm"></el-time-picker>
              </li>
              <li class="fl fl33">
                <el-time-picker placeholder="17:00" v-model="form.date8" format="HH:mm"></el-time-picker>
              </li>
              <li class="fl fl33 pl20">星期五</li>
              <li class="fl fl33">
                <el-time-picker placeholder="9:00" v-model="form.date9" format="HH:mm"></el-time-picker>
              </li>
              <li class="fl fl33">
                <el-time-picker placeholder="17:00" v-model="form.date10" format="HH:mm"></el-time-picker>
              </li>
              <li class="fl fl33 pl20">星期六</li>
              <li class="fl fl33">
                <el-time-picker placeholder="9:00" v-model="form.date11" format="HH:mm"></el-time-picker>
              </li>
              <li class="fl fl33">
                <el-time-picker placeholder="17:00" v-model="form.date12" format="HH:mm"></el-time-picker>
              </li>
              <li class="fl fl33 pl20">星期日</li>
              <li class="fl fl33">
                <el-time-picker placeholder="9:00" v-model="form.date13" format="HH:mm"></el-time-picker>
              </li>
              <li class="fl fl33">
                <el-time-picker placeholder="17:00" v-model="form.date14" format="HH:mm"></el-time-picker>
              </li>
            </ul>
          </el-form>
        </div>
      </div>
      <div class="rece-right">
        <div class="serverNotice">接收通知的账户</div>
        <div class="accountTable">
          <el-table :data="accountTable" border height="320px">
            <el-table-column prop="1" label width="220" style="text-align: left;"></el-table-column>
            <el-table-column prop="2" label="邮件">
              <template slot-scope="scope">
                <el-checkbox v-model="scope.row.accountChecked1"></el-checkbox>
              </template>
            </el-table-column>
            <el-table-column prop="3" label="短信">
              <template slot-scope="scope">
                <el-checkbox v-model="scope.row.accountChecked2"></el-checkbox>
              </template>
            </el-table-column>
            <el-table-column prop="4" label="电话">
              <template slot-scope="scope">
                <el-checkbox v-model="scope.row.accountChecked3"></el-checkbox>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      checked: false,
      hostChecked1: null,
      hostChecked2: null,
      hostChecked3: null,
      serverChecked1: null,
      serverChecked2: null,
      serverChecked3: null,
      accountChecked1: null,
      accountChecked2: null,
      accountChecked3: null,
      form: {
        date1: "",
        date2: "",
        date3: "",
        date4: "",
        date5: "",
        date6: "",
        date7: "",
        date8: "",
        date9: "",
        date10: "",
        date11: "",
        date12: "",
        date13: "",
        date14: "",
      },

      hostTable: [
        {
          1: "Host Acknowledgment",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "Host Recovery",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "Host Down",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "Host Unreachable",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "Host Flapping",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "Host Flapping",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "Host Downtime",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "Host Recovery",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760"
        }
      ],
      serverTable: [
        {
          1: "Host Acknowledgment",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "Host Recovery",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "Host Down",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "Host Unreachable",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "Host Flapping",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "Host Flapping",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "Host Downtime",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "Host Recovery",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "TC_server1",
          2: "760",
          3: "760",
          4: "760"
        }
      ],
      accountTable: [
        {
          1: "李强",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "刘志伟",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "张倩倩",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "赵莹莹",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "何立伟",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "马亮",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "周强",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "刘伟",
          2: "760",
          3: "760",
          4: "760"
        },
        {
          1: "王强",
          2: "760",
          3: "760",
          4: "760"
        }
      ]
    };
  },
  created() {},
  methods: {
    enable() {
      console.log("启用通知");
    }
  },
  mounted() {}
};
</script>
<style lang="scss" scoped>
@import "../../../../css/manageFile/manageNotice.css";
.manageNotice {
  // 头部启用通知
  .manTop {
    height: 30px;
    margin-top: 10px;
    .t-left {
      float: left;
      margin-top: 3px;
      .el-checkbox__inner {
        background-color: #023570;
        border: 1px solid #999b9e;
      }
      .el-checkbox {
        color: white;
      }
      .el-checkbox__input.is-checked + .el-checkbox__label {
        color: white;
      }
    }
    .t-right {
      float: right;
      .el-button--primary {
        height: 30px;
        line-height: 0;
      }
    }
  }
  // 主体一
  .manMain {
    height: 430px;
    margin-top: 40px;
    // 主机事件通知
    .m-left {
      width: 640px;
      height: 430px;
      float: left;
      border: 1px solid #3878b1;
      background-color: #1f4271;
      margin-right: 70px;
      .hostNotice {
        width: 100%;
        height: 70px;
        padding: 24px 0 0 34px;
        color: white;
        box-sizing: border-box;
      }
      .hostTable {
        width: 100%;
        height: 320px;
        padding-left: 60px;
        padding-right: 40px;
        box-sizing: border-box;
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
    // 服务监控通知
    .m-right {
      width: 640px;
      height: 430px;
      float: left;
      border: 1px solid #3878b1;
      background-color: #1f4271;
      .serverNotice {
        width: 100%;
        height: 70px;
        padding: 24px 0 0 34px;
        color: white;
        box-sizing: border-box;
      }
      .serverTable {
        width: 100%;
        height: 320px;
        padding-left: 60px;
        padding-right: 40px;
        box-sizing: border-box;
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
  // 主体一
  .receiveMain {
    height: 430px;
    margin-top: 40px;
    // 主机事件通知
    .rece-left {
      width: 640px;
      height: 430px;
      float: left;
      border: 1px solid #3878b1;
      background-color: #1f4271;
      margin-right: 70px;
      .hostNotice {
        width: 100%;
        height: 70px;
        padding: 24px 0 0 34px;
        color: white;
        box-sizing: border-box;
      }
      .re-time {
        width: 430px;
        height: 330px;
        color: white;
        padding-left: 40px;
        box-sizing: border-box;
      }
      .re-time > ul > li {
        float: left;
        width: 33.333% !important;
        height: 30px;
      }
      .pl20 {
        padding-left: 20px;
        box-sizing: border-box;
      }
      .fl33 {
        width: 33.333% !important;
        height: 40px;
        line-height: 40px;
      }
      .oneColor {
        color: #1f4271 !important;
      }
    }
    // 服务监控通知
    .rece-right {
      width: 640px;
      height: 430px;
      float: left;
      border: 1px solid #3878b1;
      background-color: #1f4271;
      .serverNotice {
        width: 100%;
        height: 70px;
        padding: 24px 0 0 34px;
        color: white;
        box-sizing: border-box;
      }
      .accountTable {
        width: 100%;
        height: 320px;
        padding-left: 60px;
        padding-right: 40px;
        box-sizing: border-box;
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
</style>
