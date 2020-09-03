<template>
  <!-- 管理——用户 -->
  <div class="manageUser">
    <!-- 当前用户登录  -->
    <div class="currentUser clearfix">
      <div class="cnUser fl">当前登录用户</div>
      <div class="egUser fl">admin</div>
      <div class="updateSet fr">
        <el-button type="primary">更新设置</el-button>
      </div>
    </div>
    <!-- 用户表格  -->
    <div class="userTable">
      <div class="wrap-flex">
        <div class="wrap-flex-item">
          <!-- 联系方式  -->
          <div class="contactInfor">
            <el-form :model="contactForm" ref="form">
              <div class="infor">联系方式</div>
              <div class="phone">
                移动电话
                <el-autocomplete
                  placeholder="+86 13298766789"
                  v-model="contactForm.phoneNumber"
                  clearable
                  type="text"
                  :debounce="0"
                  :fetch-suggestions="queryDepartment"
                  @select="handleSelectScen"
                  style="width: 230px; margin-right: 10px;"
                ></el-autocomplete>
                <el-button type="primary" @click="seachBtn">验证</el-button>
              </div>
              <div class="phoneVer">
                <i class="el-icon-success"></i> 2019-12-13 10:23:45 已验证
              </div>
              <div class="emal">
                电子邮件
                <el-autocomplete
                  placeholder="+86 13298766789"
                  v-model="contactForm.emalNumber"
                  clearable
                  type="text"
                  :debounce="0"
                  :fetch-suggestions="queryDepartment"
                  @select="handleSelectScen"
                  style="width: 230px; margin-right: 10px;"
                ></el-autocomplete>
                <el-button type="primary" @click="seachBtn">验证</el-button>
              </div>
              <div class="emalVer">
                <i class="el-icon-success"></i> 2019-12-13 10:23:45 已验证
              </div>
            </el-form>
          </div>
        </div>
        <div class="wrap-flex-item">
          <!-- 修改密码  -->
          <div class="changePassword">
            <el-form :model="passwordForm" ref="form">
              <div class="infor">修改密码</div>
              <div class="currentPass">
                <span class="w150">当前密码</span>
                <span>
                  <el-input
                    placeholder="*********"
                    v-model="passwordForm.currentPassWord"
                    clearable
                    type="password"
                    :debounce="0"
                    :fetch-suggestions="queryDepartment"
                    @select="handleSelectScen"
                    style="width: 230px;"
                    class="fl"
                  ></el-input>
                </span>
              </div>
              <div class="newPass">
                <span class="w150">新密码</span>
                <span>
                  <el-input
                    placeholder="*********"
                    v-model="passwordForm.newPassWord"
                    clearable
                    type="password"
                    :debounce="0"
                    :fetch-suggestions="queryDepartment"
                    @select="handleSelectScen"
                    style="width: 230px;"
                    class="fl"
                  ></el-input>
                </span>
              </div>
              <div class="repeatPass">
                <span class="w150">重复新密码</span>
                <span>
                  <el-input
                    placeholder="*********"
                    v-model="passwordForm.repeatPassWord"
                    clearable
                    type="password"
                    :debounce="0"
                    :fetch-suggestions="queryDepartment"
                    @select="handleSelectScen"
                    style="width: 230px;"
                    class="fl"
                  ></el-input>
                </span>
              </div>
            </el-form>
          </div>
        </div>
        <div class="wrap-flex-item">
          <!-- 语言与时区  -->
          <div class="language">
            <el-form :model="languageForm" ref="form">
              <div class="infor">语言与时区</div>
              <div class="langBox clearfix">
                <div class="lang fl">语言</div>
                <div class="zone fl">
                  <el-select v-model="languageForm.langRegion" placeholder="简体中文">
                    <el-option label="简体中文" value="中文"></el-option>
                    <el-option label="English" value="英文"></el-option>
                  </el-select>
                </div>
              </div>
              <div class="timeBox clearfix">
                <div class="lang fl">时区</div>
                <div class="zone fl">
                  <el-select v-model="languageForm.timeZoneRegion" placeholder="GMT+8  （北京时间）">
                    <el-option label="GMT+8  （北京时间）" value="bjTime"></el-option>
                    <el-option label="GMT+10  （美国时间）" value="mgTime"></el-option>
                  </el-select>
                </div>
              </div>
            </el-form>
          </div>
        </div>
      </div>
    </div>
    <!-- 用户组管理  -->
    <div class="groupManage clearfix">
      <div class="cnUser fl">用户组管理</div>
      <div class="updateSet fr">
        <el-button type="primary" size="small" @click="addRow(users)">添加用户组</el-button>
      </div>
    </div>
    <!-- 用户组管理表格 -->
    <div class="groupTable">
      <el-table
        border
        :data="users"
        highlight-current-row
        v-loading="listLoading"
        style="width: 100%;"
      >
        <el-table-column prop="name" label="用户组名" width="170"></el-table-column>
        <el-table-column prop="price" label="显示名称" width="510"></el-table-column>
        <el-table-column label="启用" width="300">
          <template slot-scope="scope">
            <el-checkbox v-model="scope.row.checked1"></el-checkbox>
          </template>
        </el-table-column>
        <el-table-column prop="data" label="上次登录" width="420"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.$index, users)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 新增弹框 -->
    <!--用户组管理——新增/编辑界面-->
    <el-dialog
      :title="titleMap[dialogStatus]"
      :visible.sync="FormVisible"
      :close-on-click-modal="false"
      class="edit-form"
      :before-close="handleClose"
    >
      <el-form :model="Form" label-width="100px" :rules="editFormRules" ref="Form">
        <el-form-item label="用户组名" prop="name">
          <el-input v-model="Form.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="显示名称">
          <el-input v-model="Form.price"></el-input>
        </el-form-item>
        <el-form-item label="上次登录" :picker-options="pickerOptions">
          <div>
            <el-date-picker
              v-model="Form.data"
              type="date"
              placeholder="选择日期"
              format="yyyy 年 MM 月 dd 日"
              value-format="yyyy-MM-dd"
            ></el-date-picker>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="handleCancel('Form')">取消</el-button>
        <el-button v-if="addBtnshow" type="primary" @click.native="confirmAdd('Form')">确定</el-button>
        <el-button v-if="editBtnshow" type="primary" @click.native="confirmEdit('Form')">确定</el-button>
      </div>
    </el-dialog>
    <!-- ------------------------------------------------------------------- -->
    <!-- 用户管理  -->
    <div class="userManage clearfix">
      <div class="cnUser fl">用户管理</div>
      <div class="updateSet fr">
        <el-button type="primary" size="small" @click="crmAddRow(crmUsers)">添加用户</el-button>
      </div>
    </div>
    <!-- 用户管理表格 -->
    <div class="userTables">
      <el-table
        border
        :data="crmUsers"
        highlight-current-row
        v-loading="crmListLoading"
        style="width: 100%;"
      >
        <el-table-column prop="name" label="用户名" width="160"></el-table-column>
        <el-table-column prop="showName" label="显示名称" width="220"></el-table-column>
        <el-table-column prop="emali" label="电子邮件" width="320"></el-table-column>
        <el-table-column prop="phone" label="移动电话" width="200"></el-table-column>
        <el-table-column prop="level" label="权限级别" width="188"></el-table-column>
        <el-table-column prop="crmData" label="上次登录" width="330"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="small"
              @click="crmHandleEdit(scope.$index, scope.row)"
            >编辑</el-button>
            <el-button size="small" type="danger" @click="crmHandleDelete(scope.$index, users)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--用户管理——新增/编辑界面-->
    <el-dialog
      :title="crmTitleMap[crmDialogStatus]"
      :visible.sync="crmFormVisible"
      :close-on-click-modal="false"
      class="edit-form"
      :before-close="crmHandleClose"
    >
      <el-form :model="crmForm" label-width="100px" :rules="crmEditFormRules" ref="crmForm">
        <el-form-item label="用户组名" prop="name">
          <el-input v-model="crmForm.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="显示名称">
          <el-input v-model="crmForm.showName"></el-input>
        </el-form-item>
        <el-form-item label="电子邮件">
          <el-input v-model="crmForm.emali"></el-input>
        </el-form-item>
        <el-form-item label="移动电话">
          <el-input v-model="crmForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="权限级别">
          <el-input v-model="crmForm.level"></el-input>
        </el-form-item>
        <el-form-item label="上次登录" :picker-options="crmPickerOptions">
          <div>
            <el-date-picker
              v-model="crmForm.crmData"
              type="date"
              placeholder="选择日期"
              format="yyyy 年 MM 月 dd 日"
              value-format="yyyy-MM-dd"
            ></el-date-picker>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="crmHandleCancel('crmForm')">取消</el-button>
        <el-button v-if="crmAddBtnshow" type="primary" @click.native="crmConfirmAdd('crmForm')">确定</el-button>
        <el-button v-if="crmEditBtnshow" type="primary" @click.native="crmConfirmEdit('crmForm')">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {
  // 主机组模糊查询
  queryDownListGroup
} from "@/api/table.js";
var _index;
export default {
  data() {
    return {
      checked1: null, // 如果使用单选框,定义一个model值
      contactForm: {
        phoneNumber: "",
        emalNumber: ""
      },
      passwordForm: {
        currentPassWord: "",
        newPassWord: "",
        repeatPassWord: ""
      },
      languageForm: {
        langRegion: "",
        timeZoneRegion: ""
      },
      // 用户组管理表格
      tableData: [
        {
          date: "2016-05-02",
          name: "admin",
          showName: "管理员组",
          address: "上海市普陀区金沙江路 1518 弄"
        },
        {
          date: "2016-05-04",
          name: "routine",
          showName: "日常维护",
          address: "上海市普陀区金沙江路 1517 弄"
        },
        {
          date: "2016-05-01",
          name: "all_member",
          showName: "所有成员",
          address: "上海市普陀区金沙江路 1519 弄"
        }
      ],

      // ---------------------------
      // 用户管理数据

      crmUsers: [
        { name: "admin", showName: "13", emali:"admin@qq.com", phone:"123123123123", level: "管理员", crmData: "2020-08-27" },
        { name: "routine", showName: "13", emali:"admin@qq.com", phone:"123123123123", level: "普通用户", crmData: "2020-08-27" },
        {
          name: "all_member",
          showName: "33",
          emali:"admin@qq.com", phone:"123123123123", level: "普通用户", crmData: "2020-08-27"
        },
        {
          name: "无印良品水乳",
          showName: "22",
          emali:"admin@qq.com", phone:"123123123123", level: "普通用户", crmData: "2020-08-27"
        },
        { name: "悦风诗吟", showName: "113", emali:"admin@qq.com", phone:"123123123123", level: "普通用户", crmData: "2020-08-27" }
      ],

      crmListLoading: "",
      crmFormVisible: false,
      crmDialogStatus: "",
      crmForm: {
        id: 0,
        name: "",
        showName: "",
        emali: "",
        phone: "",
        level: "",
        crmData: ""
      },
      crmTitleMap: {
        addEquipment: "新增",
        editEquipment: "编辑"
      },
      crmEditFormRules: {
        name: [{ required: true, message: "请输入用户组名", trigger: "blur" }]
      },
      crmAddBtnshow: false,
      crmEditBtnshow: false,
      crmPickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        }
      },
      // ---------------------------------------
      // 用户组管理数据
      users: [
        { name: "admin", price: "13", data: "2020-08-27" },
        { name: "routine", price: "13", data: "2020-08-27" },
        {
          name: "all_member",
          price: "33",
          data: "2020-08-27"
        },
        {
          name: "无印良品水乳",
          price: "22",
          data: "2020-08-27"
        },
        { name: "悦风诗吟", price: "113", data: "2020-08-27" }
      ],
      listLoading: "",
      // 弹框的显示隐藏
      FormVisible: false,
      //新增和编辑弹框标题
      dialogStatus: "",
      Form: {
        id: 0,
        name: "",
        price: "",
        data: ""
      },
      titleMap: {
        addEquipment: "新增",
        editEquipment: "编辑"
      },
      // 新增弹框属性
      editFormRules: {
        name: [{ required: true, message: "请输入用户组名", trigger: "blur" }]
      },
      addBtnshow: false,
      editBtnshow: false,
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        }
      }
      // ---------------------------
      // 用户组管理数据开始
    };
  },
  created() {},
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
    seachBtn() {},
    // 用户管理数据开始
    crmHandleEdit: function(index, row) {
      this.crmFormVisible = true;
      this.crmForm = Object.assign({}, row); //这句是关键！！！
      _index = index;
      this.crmDialogStatus = "editEquipment";
      this.crmAddBtnshow = false;
      this.crmEditBtnshow = true;
    },
    crmHandleDelete(index, row) {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$message({
            // delete:row.splice(index, 1),
            type: "success",
            message: "删除成功!",
            delete: row.splice(index, 1) //splice 删除对象是数unfuntion组   如果是对象会出现错误  row.solice not is

            // url: this.$router.push('/')
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    crmHandleCancel(formName) {
      this.crmFormVisible = false;
    },
    crmHandleClose(done) {
      this.crmFormVisible = false;
    },
    crmAddRow(users, event) {
      this.crmFormVisible = true;
      this.crmForm = {
        id: 0,
        name: "",
        showName: "",
        emali: "",
        phone: "",
        level: "",
        crmData: ""
      };
      this.crmDialogStatus = "addEquipment";
      this.crmAddBtnshow = true;
      this.crmEditBtnshow = false;
    },
    crmConfirmAdd() {
      this.crmUsers.push({
        name: this.crmForm.name,
        showName: this.crmForm.showName,
        emali: this.crmForm.emali,
        phone: this.crmForm.phone,
        level: this.crmForm.level,
        crmData: this.crmForm.crmData
      });
      this.crmFormVisible = false;
    },
    crmConfirmEdit() {
      var editdata = _index;
      this.crmUsers[editdata].name = this.crmForm.name;
      this.crmUsers[editdata].showName = this.crmForm.showName;
      this.crmUsers[editdata].emali = this.crmForm.emali;
      this.crmUsers[editdata].phone = this.crmForm.phone;
      this.crmUsers[editdata].level = this.crmForm.level;
      this.crmUsers[editdata].crmData = this.crmForm.crmData;
      this.crmFormVisible = false;
    },
    // 用户组管理数据结束
    // ----------------------------------
    //点击编辑
    handleEdit: function(index, row) {
      this.FormVisible = true;
      this.Form = Object.assign({}, row); //这句是关键！！！

      _index = index;
      console.log(index);
      console.log(_index);
      console.log(this.Form);

      this.dialogStatus = "editEquipment";
      this.addBtnshow = false;
      this.editBtnshow = true;
    },
    // 删除
    handleDelete(index, row) {
      console.log(index, row);
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$message({
            // delete:row.splice(index, 1),
            type: "success",
            message: "删除成功!",
            delete: row.splice(index, 1) //splice 删除对象是数unfuntion组   如果是对象会出现错误  row.solice not is

            // url: this.$router.push('/')
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    //点击取消
    handleCancel(formName) {
      this.FormVisible = false;
    },
    //点击关闭弹框
    handleClose(done) {
      this.FormVisible = false;
    },
    // 点击新增
    addRow(users, event) {
      this.FormVisible = true;
      this.Form = {
        id: 0,
        name: "",
        price: "",
        data: ""
      };
      this.dialogStatus = "addEquipment";
      this.addBtnshow = true;
      this.editBtnshow = false;
    },
    // 点击确定（新增）
    confirmAdd() {
      // this.users = this.users || []
      this.users.push({
        name: this.Form.name,
        price: this.Form.price,
        data: this.Form.data
      });
      // storage.set('users', this.users);
      this.FormVisible = false;
    },
    // 点击确定（编辑）
    confirmEdit() {
      var editdata = _index;
      console.log(editdata);
      this.users[editdata].name = this.Form.name;
      this.users[editdata].price = this.Form.price;
      this.users[editdata].data = this.Form.data;
      this.FormVisible = false;
    }
  },
  mounted() {}
};
</script>
<style lang="scss">
@import "../../../../css/manageFile/manageUser.css";
.manageUser {
  // 当前用户登录
  .currentUser {
    width: 100%;
    color: white;
    margin-top: 20px;
    .cnUser {
      font-size: 24px;
      margin-right: 20px;
    }
    .egUser {
      font-size: 16px;
      width: 100px;
      padding-top: 10px;
    }
  }
  // 用户表格
  .userTable {
    width: 100%;
    margin: 30px auto;
    .wrap-flex {
      display: flex;
      .wrap-flex-item {
        flex: 1;
      }

      .wrap-flex-item:nth-of-type(1) {
        padding-right: 40px;
        // 联系方式
        .contactInfor {
          background: #16457b;
          border: 1px solid #3878b1;
          box-sizing: border-box;
          height: 236px;
          color: white;
          .infor {
            font-size: 22px;
            padding: 5px 0 0 10px;
            font-weight: 100;
            margin-bottom: 40px;
          }
          .phone {
            padding: 0 0 0 50px;
            font-size: 18px;
          }
          .phoneVer {
            padding: 0 0 0 150px;
            margin-top: 5px;
            margin-bottom: 36px;
          }
          .emal {
            padding: 0 0 0 50px;
            font-size: 18px;
          }
          .emalVer {
            padding: 0 0 0 150px;
            margin-top: 5px;
          }
        }
      }

      .wrap-flex-item:nth-of-type(2) {
        padding-left: 25px;
        padding-right: 25px;
        // 修改密码
        .changePassword {
          background: #16457b;
          border: 1px solid #3878b1;
          box-sizing: border-box;
          height: 236px;
          color: white;
          // 修改密码
          .infor {
            font-size: 22px;
            padding: 5px 0 0 10px;
            font-weight: 100;
            margin-bottom: 40px;
          }
          .w150 {
            display: block;
            width: 100px;
            float: left;
          }
          // 当前密码
          .currentPass {
            padding-left: 70px;
            margin: 10px 0;
            font-size: 18px;
            font-weight: 100;
            height: 30px;
            .w150 {
              width: 100px;
            }
          }
          // 新密码
          .newPass {
            padding-left: 70px;
            margin: 24px 0;
            font-size: 18px;
            font-weight: 100;
            height: 30px;
          }
          // 重复新密码
          .repeatPass {
            padding-left: 70px;
            margin: 10px 0;
            font-size: 18px;
            font-weight: 100;
            height: 30px;
            .w150 {
              width: 100px;
            }
          }
        }
      }

      .wrap-flex-item:nth-of-type(3) {
        padding-left: 40px;
        .language {
          background: #16457b;
          border: 1px solid #3878b1;
          box-sizing: border-box;
          height: 236px;
          color: white;
          // 语言与时区
          .infor {
            font-size: 22px;
            padding: 5px 0 0 10px;
            font-weight: 100;
            margin-bottom: 40px;
          }
          .langBox {
            height: 30px;
            margin-left: 70px;
            margin-bottom: 30px;
            .lang {
              margin-right: 10px;
              padding-top: 5px;
            }
          }
          .timeBox {
            height: 30px;
            margin-left: 70px;
            .lang {
              margin-right: 10px;
              padding-top: 5px;
            }
          }
        }
      }
    }
  }
  // 用户组管理
  .groupManage {
    width: 100%;
    color: white;
    margin-top: 20px;
    .cnUser {
      font-size: 24px;
      margin-right: 20px;
      margin-bottom: 20px;
    }
  }
  // 用户管理
  .userManage {
    width: 100%;
    color: white;
    margin-top: 40px;
    .cnUser {
      font-size: 24px;
      margin-right: 20px;
      margin-bottom: 20px;
    }
  }
}

</style>
