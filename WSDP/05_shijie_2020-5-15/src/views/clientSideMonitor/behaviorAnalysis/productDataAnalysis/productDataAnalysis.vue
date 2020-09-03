<template>
  <div class="dash">
    <!--头部-->
    <div class="header">
      WiseDeep 用户行为监控系统
      <a class="a-access" @click="black">
        <button class="button type1">返回</button>
      </a>
    </div>
    <!--主体-->
    <div class="main clearfix">
      <!-- 左侧内容 -->
      <div class="main-left clearfix">
        <div class="border-top-left">
          <div class="fl img">
            <!-- 一 -->
            <!-- <img src="../assets/dbUser.png" /> -->
            <img src="@/assets/dbUser.png" />
          </div>
          <div class="fl">
            <p class="usName">在线用户</p>
            <p class="usNum">{{ allClientEventCount }}</p>
          </div>
          <div class="fl">
            <p class="fl usEvent">客户端事件：</p>
            <p class="eventNum fl">127834</p>
          </div>
        </div>

        <div class="border-container">
          <div class="name-title">登录用户数量</div>
          <!-- <div id="graduateyear"></div> -->
          <ul class="three-pie clearfix">
            <li>
              <div id="sexrate">
                <el-tabs
                  v-model="activeName1"
                  type="card"
                  @tab-click="handleClick1"
                  id="card1"
                >
                  <el-tab-pane label="今日" name="first1">
                    <span slot="label" @click="login1">今日</span>
                    <div
                      id="main1"
                      style="width: 100%; height: 240px; top:-14px"
                    ></div>
                  </el-tab-pane>

                  <el-tab-pane label="本周" name="second1">
                    <span slot="label" @click="login2">本周</span>
                    <div
                      id="main2"
                      style="width: 100%; height: 240px; top:-14px"
                    ></div>
                  </el-tab-pane>

                  <el-tab-pane label="本月" name="third1">
                    <span slot="label" @click="login3">本月</span>
                    <div
                      id="main3"
                      style="width: 100%; height: 240px; top:-14px"
                    ></div>
                  </el-tab-pane>
                </el-tabs>
              </div>
            </li>
          </ul>
        </div>
        <div class="border-container">
          <div class="name-title">账户并发数量</div>
          <ul class="three-pie clearfix">
            <li>
              <div id="sexrate">
                <el-tabs
                  v-model="activeName2"
                  type="card"
                  @tab-click="handleClick2"
                  id="card2"
                >
                  <el-tab-pane label="今日" name="first2">
                    <span slot="label" @click="concurrent1">今日</span>
                    <div
                      id="main4"
                      style="width: 100%; height: 240px; top:-14px"
                    ></div>
                  </el-tab-pane>

                  <el-tab-pane label="本周" name="second2">
                    <span slot="label" @click="concurrent2">本周</span>
                    <div
                      id="main5"
                      style="width: 100%; height: 240px; top:-14px"
                    ></div>
                  </el-tab-pane>

                  <el-tab-pane label="本月" name="third2">
                    <span slot="label" @click="concurrent3">本月</span>
                    <div
                      id="main6"
                      style="width: 100%; height: 240px; top:-14px"
                    ></div>
                  </el-tab-pane>
                </el-tabs>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <!-- 中间内容 -->
      <div class="main-middle">
        <div class="border-top-left"></div>
        <div class="border-container-middle">
          <ul class="clearfix">
            <li>
              <div id="card3">
                <div
                  id="main7"
                  style="width: 100%; height:634px; left:60px; top:-30px"
                ></div>
                <div class="card3-bot">
                  <div class="bot-info">
                    <ul>
                      <li>
                        <div class="dayName">本日事件次数</div>
                        <div class="dayNum clearfix">
                          <p class="dayNum-1">1421</p>
                          <p class="dayNum-2">日环比：-2%</p>
                        </div>
                      </li>
                      <li>
                        <div class="dayName">异常操作人员</div>
                        <div class="dayNum clearfix">
                          <p class="dayNum-1">1421</p>
                          <p class="dayNnmColor">日环比：-2%</p>
                          <p class="dayNum-2-2">日环比：-2%</p>
                        </div>
                      </li>
                      <li>
                        <div class="dayName">本日事件次数</div>
                        <div class="dayNum clearfix">
                          <p class="dayNum-1">1421</p>
                          <p class="dayNum-2">日环比：-2%</p>
                        </div>
                      </li>
                    </ul>
                  </div>
                  <div class="bot-figure">
                    <div class="figure-title clearfix">
                      <div class="point">
                        <div class="points"></div>
                      </div>
                      <div class="point-title">深圳公司</div>
                    </div>
                    <div class="figure-info">
                      <div class="info-left" id="card8">
                        <div class="info-left-title">异常事件占比</div>
                        <div class="info-left-figure">
                          <div
                            id="main8"
                            style="width: 100%; height:64px; top:0px"
                          ></div>
                        </div>
                      </div>
                      <div class="info-right" id="card9">
                        <ul class="clearfix">
                          <li>
                            <div id="sexrate">
                              <el-tabs
                                v-model="activeName4"
                                type="card"
                                @tab-click="3"
                              >
                                <el-tab-pane label="今日" name="first4">
                                  <div
                                    id="main9"
                                    style="width: 100%; height: 230px;"
                                  ></div>
                                </el-tab-pane>

                                <el-tab-pane label="本周" name="second4">
                                  <div
                                    id="main10"
                                    style="width: 100%; height: 230px;"
                                  ></div>
                                </el-tab-pane>

                                <el-tab-pane label="本月" name="third4">
                                  <div
                                    id="main11"
                                    style="width: 100%; height: 230px;"
                                  ></div>
                                </el-tab-pane>
                              </el-tabs>
                            </div>
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                  <div class="info-day">
                    <div class="info-up clearfix">
                      <div class="up-point info-fl"></div>
                      <div class="info-fl up-day">今日 客户端事件</div>
                      <div class="info-fl up-day-num">
                        &nbsp;{{ allClientEventCount }}
                      </div>
                      <div class="info-fl up-abnormal">异常事件</div>
                      <div class="info-fl up-abnormal-num">
                        {{ abnormalClientEventCount }}
                      </div>
                    </div>
                    <div class="info-up clearfix">
                      <div class="up-point info-fl"></div>
                      <div class="info-fl up-day">今日 服务器事件</div>
                      <div class="info-fl up-day-num">&nbsp;1234</div>
                      <div class="info-fl up-abnormal">异常事件</div>
                      <div class="info-fl up-abnormal-num">234</div>
                    </div>
                  </div>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <!-- 右侧内容 -->
      <div class="main-right">
        <div class="border-top-right">
          <div class="newTime clearfix">
            <p class="fr updateTime">{{ date }}</p>
            <p class="fr">更新时间 :</p>
          </div>
          <div class="newEvent clearfix">
            <p class="fr newEventColor">239745</p>
            <p class="fr">服务器端事件：</p>
          </div>
        </div>
        <div class="border-container">
          <div class="name-title">最近事件</div>

          <!-- <div id="graduateyear"></div> -->
          <ul class="three-pie clearfix">
            <li>
              <div id="recentEvents">
                <el-tabs
                  v-model="activeName3"
                  type="card"
                  @tab-click="handleClick3"
                  id="3"
                >
                  <el-tab-pane label="全部" name="first3">
                    <el-table
                      :data="tableData"
                      :show-header="false"
                      style="width: 100%"
                    >
                      <el-table-column prop="state" width="30">
                        <template slot-scope="{ row }">
                          <div
                            v-if="row.type === '超量下载文件'"
                            class="state"
                          ></div>
                          <div
                            v-else-if="row.type === '跨部门登录'"
                            class="state"
                          ></div>
                          <div v-else class="stateGreen"></div>
                        </template>
                      </el-table-column>
                      <el-table-column prop="type"></el-table-column>
                      <el-table-column prop="address"></el-table-column>
                      <el-table-column prop="date"></el-table-column>
                    </el-table>
                  </el-tab-pane>

                  <el-tab-pane label="异常" name="second3">
                    <el-table
                      :data="tableData"
                      :show-header="false"
                      style="width: 100%"
                    >
                      <el-table-column prop="state" width="30">
                        <template slot-scope="{ row }">
                          <div
                            v-if="row.type === '超量下载文件'"
                            class="state"
                          ></div>
                          <div
                            v-else-if="row.type === '跨部门登录'"
                            class="state"
                          ></div>
                          <div v-else class="stateGreen"></div>
                        </template>
                      </el-table-column>
                      <el-table-column prop="type"></el-table-column>
                      <el-table-column prop="address"></el-table-column>
                      <el-table-column prop="date"></el-table-column>
                    </el-table>
                  </el-tab-pane>

                  <el-tab-pane label="正常" name="third3">
                    <el-table
                      :data="tableData"
                      :show-header="false"
                      style="width: 100%"
                    >
                      <el-table-column prop="state" width="30">
                        <template slot-scope="{ row }">
                          <div
                            v-if="row.type === '超量下载文件'"
                            class="state"
                          ></div>
                          <div
                            v-else-if="row.type === '跨部门登录'"
                            class="state"
                          ></div>
                          <div v-else class="stateGreen"></div>
                        </template>
                      </el-table-column>
                      <el-table-column prop="type"></el-table-column>
                      <el-table-column prop="address"></el-table-column>
                      <el-table-column prop="date"></el-table-column>
                    </el-table>
                  </el-tab-pane>
                </el-tabs>
              </div>
            </li>
          </ul>
        </div>
        <div class="border-container departNum">
          <div class="name-title">部门事件数量</div>

          <ul class="three-pie clearfix">
            <div class="departmentTitle clearfix">
              <ul>
                <li class="departmentTitle1">数量</li>
                <li class="departmentTitle2">部门</li>
                <li class="departmentTitle3">异常事件占比</li>
              </ul>
            </div>
            <li>
              <div id="departEvents">
                <el-table
                  :data="
                    userList.slice(
                      (pagesNum - 1) * pageSize,
                      pagesNum * pageSize
                    )
                  "
                  :show-header="false"
                  style="width: 100%"
                >
                  <el-table-column prop="id" width="70" text-align="center">
                    <!-- 循环遍历 -->
                    <template slot-scope="{ row }">
                      <div
                        v-if="row.id > 20"
                        style="background-color:#ed405d;width:40px;height:20px;color:white;border-radius:5px;line-height:20px;text-align:center"
                      >
                        {{ row.id }}
                      </div>
                      <div
                        v-else-if="row.id > 10"
                        style="background-color:#f78c44;width:40px;height:20px;color:white;border-radius:5px;line-height:20px;text-align:center"
                      >
                        {{ row.id }}
                      </div>
                      <div
                        v-else
                        style="background-color:#878787;width:40px;height:20px;color:white;border-radius:5px;line-height:20px;text-align:center"
                      >
                        {{ row.id }}
                      </div>
                    </template>
                  </el-table-column>
                  <el-table-column prop="hostName"></el-table-column>
                  <el-table-column prop="clientServerTTL">
                    <!-- 循环遍历显示百分百数值 -->
                    <template slot-scope="{ row }">
                      <!-- 必填项percentage -->
                      <el-progress :percentage="row.id"></el-progress>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </li>
          </ul>
          <el-pagination
            class="paging"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pagesNum"
            :page-sizes="[25, 50, 100]"
            :page-size="pageSize"
            :total="userList.length"
            small
            layout="total,prev, pager, next"
          ></el-pagination>
        </div>
      </div>
    </div>
    <!--尾部-->
    <div class="footer clearfix">
      <!-- item：循环
      index：tcList数组的下标
      :key 必填项值下标
      :class 动态添加类名
          'expression3'： 类名
           true ： 条件
      -->
      <div
        v-for="(item, index) in tcList"
        :key="index"
        class="footer-border justity-center center imgBox"
        :class="{
          expression3: true,
          expression2:
            item.network >= 80 || item.memory >= 80 || item.CPU >= 80,
          expression1: item.network >= 90 || item.memory >= 90 || item.CPU >= 90
        }"
      >
        <!-- tc标题 -->
        <div class="footer-top">{{ item.name }}</div>
        <div class="footer-bot">
          <!-- tc状态图片 -->
          <div class="footer-bot-left">
            <!-- 二 -->
            <!-- <img
              v-if="item.network >= 90||item.memory >= 90||item.CPU >= 90"
              src="@/assets/fup1.png"
            /> -->
            <img
              v-if="item.network >= 90 || item.memory >= 90 || item.CPU >= 90"
              src="@/assets/fup1.png"
            />
            <img
              v-else-if="
                item.network >= 80 || item.memory >= 80 || item.CPU >= 80
              "
              src="@/assets/fuq2.png"
            />
            <img v-else src="@/assets/fuq3.png" />
          </div>
          <div class="footer-bot-right">
            <!-- tc网络信息 -->
            <div class="footer-right1">
              <div class="clearfix">
                <div class="fl network">网络</div>
                <div class="fr" :class="{ expRed: item.network >= 90 }">
                  {{ item.network + "%" }}
                </div>
              </div>
              <div>
                <div class="progress">
                  <div
                    class="progress-bar"
                    role="progressbar"
                    aria-valuenow="60"
                    aria-valuemin="0"
                    aria-valuemax="100"
                    :style="{ width: item.network + '%' }"
                  ></div>
                </div>
              </div>
            </div>
            <!-- tc内存信息 -->
            <div class="footer-right2">
              <div class="clearfix">
                <div class="fl network">内存</div>
                <div class="fr" :class="{ expRed: item.memory >= 90 }">
                  {{ item.memory + "%" }}
                </div>
              </div>
              <div>
                <div class="progress">
                  <div
                    class="progress-bar"
                    role="progressbar"
                    aria-valuenow="60"
                    aria-valuemin="0"
                    aria-valuemax="100"
                    :style="{ width: item.memory + '%' }"
                  ></div>
                </div>
              </div>
            </div>
            <!-- tc的CPU信息 -->
            <div class="footer-right3">
              <div class="clearfix">
                <div class="fl network">CPU</div>
                <div class="fr" :class="{ expRed: item.CPU >= 90 }">
                  {{ item.CPU + "%" }}
                </div>
              </div>
              <div>
                <div class="progress">
                  <div
                    class="progress-bar"
                    role="progressbar"
                    aria-valuenow="60"
                    aria-valuemin="0"
                    aria-valuemax="100"
                    :style="{ width: item.CPU + '%' }"
                  ></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// 引入文档查看、文档下载接口
// 模拟接口,shijie
import {
  // shijie,
  getViewZheXian,
  getDownZheXian,
  infoRefr,
  getDayClienCount
} from '@/api/table.js'
// 引入时间格式
// import moment from 'moment'

export default {
  data () {
    return {
      // 今日客户端事件总数
      allClientEventCount: '',
      // 今日客户端异常事件总数
      abnormalClientEventCount: '',
      // 在线用户人数
      ageNum: '',
      // 分页器数据
      pagesNum: 1, // 当前页
      pageSize: 7, // 每页条数
      total: 5, // 总页数
      // 模拟登录用户数量
      zheXianFlag: 2,
      zheXianDates: '',
      // 模拟账号并发数量
      zheXianFlagDown: 2,
      zheXianDateDown: '',
      actives1: false,
      isError: false,
      date: '',
      // 登录用户数量默认项
      activeName1: 'second1',
      // 账号并发数量默认项
      activeName2: 'second2',
      // 最近事件默认项
      activeName3: 'first3',
      // 深圳公司事件数量
      activeName4: 'first4',
      // 部门事件数量
      stateNum: 10,
      // 异常事件占比
      // 最近事件data数据

      tableData: [
        {
          date: '2016-05-04 09:25:45',
          type: '超量下载文件',
          address: '深圳研发部 蒋义城'
        },
        {
          date: '2016-05-01 09:25:45',
          type: '跨部门登录',
          address: '北京生产部 韩建辉'
        },
        {
          date: '2016-05-03 09:25:45',
          type: '打开文档',
          address: '总部销售处 李春光'
        },
        {
          date: '2016-05-01 09:25:45',
          type: '跨部门登录',
          address: '北京生产部 韩建辉'
        },
        {
          date: '2016-05-03 09:25:45',
          type: '打开文档',
          address: '总部销售处 李春光'
        }
      ],
      // 部门事件data数据
      userList: [],
      // tc四组服务器数据结构
      tcList: [
        {
          name: 'TC1服务器',
          network: 70,
          memory: 70,
          CPU: 50
        },
        {
          name: 'TC2服务器',
          network: 80,
          memory: 70,
          CPU: 80
        },
        {
          name: 'TC3服务器',
          network: 70,
          memory: 70,
          CPU: 90
        },
        {
          name: 'TC4服务器',
          network: 80,
          memory: 90,
          CPU: 80
        }
      ]
    }
  },
  created () {
    // 现在时间，随时刷新
    this.nowTTimes()
    // 初始化带上时间
    this.Data()
    // this.getShiTable()
    this.eventList()
    // this.getShi()
  },

  methods: {
    // 模拟接口
    // async getShi () {
    //   try {
    //     const { data } = await shijie()
    //     const { arr1 } = data.data
    //     this.ageNum = arr1[0].age
    //     console.log(data)
    //   } catch (e) {
    //     console.log(e)
    //   }
    // },
    // 今日客户端事件总数
    async getDayClienCount () {
      try {
        const { data } = await getDayClienCount()
        const { abnormalClientEventCount, allClientEventCount } = data
        this.abnormalClientEventCount = abnormalClientEventCount
        this.allClientEventCount = allClientEventCount
      } catch (e) {
        console.log(e)
      }
    },

    // 分页功能
    // 更改每页个数
    handleSizeChange (val) {
      this.pageSize = val
      this.pagesNum = 1
      this.eventList()
    },
    // 切换页
    handleCurrentChange (val) {
      this.pagesNum = val
      this.eventList()
    },

    // 部门事件列表渲染
    async eventList () {
      const value = {
        pagesNum: this.pagesNum,
        actionBeginTime: '2017-01-30 13:41:07',
        actionEndTime: '2020-03-05 13:19:08'
      }
      try {
        const { data } = await infoRefr(value)
        const { tableList } = data
        this.userList = tableList
        this.total = data.tableTotalList[0]
      } catch (e) {
        console.log(e)
      }
    },
    // 登录用户数量柱状图数据
    async getInfoListDay (callback) {
      const value = {
        zheXianFlag: this.zheXianFlag,
        zheXianDate: this.zheXianDates
      }
      try {
        const { data } = await getViewZheXian(value)
        console.log(data)
        const {
          zheXianListAll,
          zheXianListCrossDept,
          zheXianListSecretFile
        } = data
        // 所有数据
        const AllXTimes = zheXianListAll.map(item => {
          return item.zheXianX
        })
        const AllYNums = zheXianListAll.map(item => {
          return item.zheXianY
        })
        // 跨部门数据
        const CrosXTimes = zheXianListCrossDept.map(item => {
          return item.zheXianX
        })
        const CrosYNums = zheXianListCrossDept.map(item => {
          return item.zheXianY
        })
        // 机密文件下载数据
        const SecrXTimes = zheXianListSecretFile.map(item => {
          return item.zheXianX
        })
        const SecrYNums = zheXianListSecretFile.map(item => {
          return item.zheXianY
        })
        callback(
          AllXTimes,
          AllYNums,
          CrosXTimes,
          CrosYNums,
          SecrXTimes,
          SecrYNums
        )
      } catch (e) {
        console.log(e)
      }
    },
    // 账户并发数量折线图数据
    async getInfoListDay2 (callback) {
      const value = {
        zheXianFlag: this.zheXianFlag,
        zheXianDate: this.zheXianDates
      }
      try {
        const { data } = await getDownZheXian(value)
        const {
          zheXianListAll,
          zheXianListOverload,
          zheXianListCrossDept
        } = data
        // 所有数据
        const downAllXTimes = zheXianListAll.map(item => {
          return item.zheXianX
        })
        const downAllYNums = zheXianListAll.map(item => {
          return item.zheXianY
        })
        // 跨部门数据
        const downCrosXTimes = zheXianListCrossDept.map(item => {
          return item.zheXianX
        })
        const downCrosYNums = zheXianListCrossDept.map(item => {
          return item.zheXianY
        })
        // 机密文件下载数据
        const downSecrXTimes = zheXianListOverload.map(item => {
          return item.zheXianX
        })
        const downSecrYNums = zheXianListOverload.map(item => {
          return item.zheXianY
        })
        callback(
          downAllXTimes,
          downAllYNums,
          downCrosXTimes,
          downCrosYNums,
          downSecrXTimes,
          downSecrYNums
        )
      } catch (e) {
        console.log(e)
      }
    },
    //  登录用户三个按钮
    login1 () {
      this.zheXianFlag = 1
      this.zheXianDate = this.zheXianDates
      this.getInfoListDay(this.initItem)
    },
    login2 () {
      this.zheXianFlag = 2
      this.zheXianDate = this.zheXianDates
      this.getInfoListDay(this.initItem)
    },
    login3 () {
      this.zheXianFlag = 3
      this.zheXianDate = this.zheXianDates
      this.getInfoListDay(this.initItem)
    },
    // 账户并发三个按钮
    concurrent1 () {
      this.zheXianFlag = 1
      this.zheXianDate = this.zheXianDateDown
      this.getInfoListDay2(this.initItem2)
    },
    concurrent2 () {
      this.zheXianFlag = 2
      this.zheXianDate = this.zheXianDateDown
      this.getInfoListDay2(this.initItem2)
    },
    concurrent3 () {
      this.zheXianFlag = 3
      this.zheXianDate = this.zheXianDateDown
      this.getInfoListDay2(this.initItem2)
    },
    handleClick1 (tab, event) {},
    handleClick2 (tab, event) {},
    handleClick3 (tab, event) {},
    // 用户提示时间
    Data () {
      this.nowData = new Date()
      const d = this.nowData
      let month =
        d.getMonth() + 1 < 10 ? '0' + (d.getMonth() + 1) : d.getMonth() + 1
      let day = d.getDate() < 10 ? '0' + d.getDate() : d.getDate()
      let times = d.getFullYear() + '-' + month + '-' + day
      this.zheXianDate = times
      this.zheXianDates = times
      this.zheXianDateDown = times
    },
    // 返回按钮
    black () {
      this.$router.push('/users/overview')
    },
    // 当前时间格式化
    timeFormate (timeStamp) {
      let year = new Date(timeStamp).getFullYear()
      let month =
        new Date(timeStamp).getMonth() + 1 < 10
          ? '0' + (new Date(timeStamp).getMonth() + 1)
          : new Date(timeStamp).getMonth() + 1
      let date =
        new Date(timeStamp).getDate() < 10
          ? '0' + new Date(timeStamp).getDate()
          : new Date(timeStamp).getDate()
      let hh =
        new Date(timeStamp).getHours() < 10
          ? '0' + new Date(timeStamp).getHours()
          : new Date(timeStamp).getHours()
      let mm =
        new Date(timeStamp).getMinutes() < 10
          ? '0' + new Date(timeStamp).getMinutes()
          : new Date(timeStamp).getMinutes()
      let ss =
        new Date(timeStamp).getSeconds() < 10
          ? '0' + new Date(timeStamp).getSeconds()
          : new Date(timeStamp).getSeconds()
      // return year + "年" + month + "月" + date +"日"+" "+hh+":"+mm ;
      this.date =
        year +
        '-' +
        month +
        '-' +
        date +
        // '-' +
        ' ' +
        hh +
        ':' +
        mm +
        ':' +
        ss
    },
    // 定时器函数
    nowTTimes () {
      this.timeFormate(new Date())
      this.timer = setTimeout(this.nowTTimes, 1 * 1000)
    },
    // 销毁定时器
    beforeDestroy: function () {
      if (this.timer) {
        clearTimeout(this.timer) // 在Vue实例销毁前，清除我们的定时器
      }
    },
    // 登录用户数量柱状图
    initItem (
      AllXTimes,
      AllYNums,
      CrosXTimes,
      CrosYNums,
      SecrXTimes,
      SecrYNums
    ) {
      let myChart1 = this.$echarts.init(document.getElementById('main1'))
      let myChart2 = this.$echarts.init(document.getElementById('main2'))
      let myChart3 = this.$echarts.init(document.getElementById('main3'))

      this.initHeight = document.getElementById('card1').offsetHeight
      this.initWidth = document.getElementById('card1').offsetWidth

      this.initHeight3 = document.getElementById('card3').offsetHeight
      this.initWidth3 = document.getElementById('card3').offsetWidth

      const option1 = {
        backgroundColor: '#040f3c',
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: '2%',
          right: '4%',
          bottom: '14%',
          top: '16%',
          containLabel: true
        },
        legend: {
          data: ['北京公司', '深圳公司', '上海公司'],
          right: 10,
          top: 12,
          textStyle: {
            color: '#fff'
          },
          itemWidth: 12,
          itemHeight: 10
          // itemGap: 35
        },
        xAxis: {
          type: 'category',
          data: CrosXTimes,
          axisLine: {
            lineStyle: {
              color: 'white'
            }
          },
          axisLabel: {
            // interval: 0,
            // rotate: 40,
            textStyle: {
              fontFamily: 'Microsoft YaHei'
            }
          }
        },

        yAxis: {
          type: 'value',
          max: '10',
          axisLine: {
            show: false,
            lineStyle: {
              color: 'white'
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: 'rgba(255,255,255,0.3)'
            }
          },
          axisLabel: {}
        },
        dataZoom: [
          {
            show: true,
            height: 12,
            xAxisIndex: [0],
            bottom: '8%',
            start: 10,
            end: 90,
            handleIcon:
              'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
            handleSize: '110%',
            handleStyle: {
              color: '#d3dee5'
            },
            textStyle: {
              color: '#fff'
            },
            borderColor: '#90979c'
          },
          {
            type: 'inside',
            show: true,
            height: 15,
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: '北京公司',
            type: 'bar',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#fccb05'
                  },
                  {
                    offset: 1,
                    color: '#f5804d'
                  }
                ]),
                barBorderRadius: 12
              }
            },
            data: CrosYNums
          },
          {
            name: '深圳公司',
            type: 'bar',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#8bd46e'
                  },
                  {
                    offset: 1,
                    color: '#09bcb7'
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: SecrYNums
          },
          {
            name: '上海公司',
            type: 'bar',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#248ff7'
                  },
                  {
                    offset: 1,
                    color: '#6851f1'
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: AllYNums
          }
        ]
      }
      const option2 = {
        backgroundColor: '#040f3c',
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: '2%',
          right: '4%',
          bottom: '14%',
          top: '16%',
          containLabel: true
        },
        legend: {
          data: ['北京公司', '深圳公司', '上海公司'],
          right: 10,
          top: 12,
          textStyle: {
            color: '#fff'
          },
          itemWidth: 12,
          itemHeight: 10
          // itemGap: 35
        },
        xAxis: {
          type: 'category',
          data: CrosXTimes,
          axisLine: {
            lineStyle: {
              color: 'white'
            }
          },
          axisLabel: {
            // interval: 0,
            // rotate: 40,
            textStyle: {
              fontFamily: 'Microsoft YaHei'
            }
          }
        },

        yAxis: {
          type: 'value',
          max: '10',
          axisLine: {
            show: false,
            lineStyle: {
              color: 'white'
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: 'rgba(255,255,255,0.3)'
            }
          },
          axisLabel: {}
        },
        dataZoom: [
          {
            show: true,
            height: 12,
            xAxisIndex: [0],
            bottom: '8%',
            start: 10,
            end: 90,
            handleIcon:
              'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
            handleSize: '110%',
            handleStyle: {
              color: '#d3dee5'
            },
            textStyle: {
              color: '#fff'
            },
            borderColor: '#90979c'
          },
          {
            type: 'inside',
            show: true,
            height: 15,
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: '北京公司',
            type: 'bar',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#fccb05'
                  },
                  {
                    offset: 1,
                    color: '#f5804d'
                  }
                ]),
                barBorderRadius: 12
              }
            },
            data: CrosYNums
          },
          {
            name: '深圳公司',
            type: 'bar',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#8bd46e'
                  },
                  {
                    offset: 1,
                    color: '#09bcb7'
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: SecrYNums
          },
          {
            name: '上海公司',
            type: 'bar',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#248ff7'
                  },
                  {
                    offset: 1,
                    color: '#6851f1'
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: AllYNums
          }
        ]
      }
      const option3 = {
        backgroundColor: '#040f3c',
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: '2%',
          right: '4%',
          bottom: '14%',
          top: '16%',
          containLabel: true
        },
        legend: {
          data: ['北京公司', '深圳公司', '上海公司'],
          right: 10,
          top: 12,
          textStyle: {
            color: '#fff'
          },
          itemWidth: 12,
          itemHeight: 10
          // itemGap: 35
        },
        xAxis: {
          type: 'category',
          data: CrosXTimes,
          axisLine: {
            lineStyle: {
              color: 'white'
            }
          },
          axisLabel: {
            // interval: 0,
            // rotate: 40,
            textStyle: {
              fontFamily: 'Microsoft YaHei'
            }
          }
        },

        yAxis: {
          type: 'value',
          max: '10',
          axisLine: {
            show: false,
            lineStyle: {
              color: 'white'
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: 'rgba(255,255,255,0.3)'
            }
          },
          axisLabel: {}
        },
        dataZoom: [
          {
            show: true,
            height: 12,
            xAxisIndex: [0],
            bottom: '8%',
            start: 10,
            end: 90,
            handleIcon:
              'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
            handleSize: '110%',
            handleStyle: {
              color: '#d3dee5'
            },
            textStyle: {
              color: '#fff'
            },
            borderColor: '#90979c'
          },
          {
            type: 'inside',
            show: true,
            height: 15,
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: '北京公司',
            type: 'bar',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#fccb05'
                  },
                  {
                    offset: 1,
                    color: '#f5804d'
                  }
                ]),
                barBorderRadius: 12
              }
            },
            data: CrosYNums
          },
          {
            name: '深圳公司',
            type: 'bar',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#8bd46e'
                  },
                  {
                    offset: 1,
                    color: '#09bcb7'
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: SecrYNums
          },
          {
            name: '上海公司',
            type: 'bar',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#248ff7'
                  },
                  {
                    offset: 1,
                    color: '#6851f1'
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: AllYNums
          }
        ]
      }
      myChart1.setOption(option1)
      myChart2.setOption(option2)
      myChart3.setOption(option3)
      let resize = {
        height: this.initHeight,
        width: this.initWidth
      }

      myChart1.resize(resize)
      myChart2.resize(resize)
      myChart3.resize(resize)
    },
    // 账户并发数量折线图
    initItem2 (
      downAllXTimes,
      downAllYNums,
      downCrosXTimes,
      downCrosYNums,
      downSecrXTimes,
      downSecrYNums
    ) {
      let myChart4 = this.$echarts.init(document.getElementById('main4'))
      let myChart5 = this.$echarts.init(document.getElementById('main5'))
      let myChart6 = this.$echarts.init(document.getElementById('main6'))
      this.initHeight = document.getElementById('card1').offsetHeight
      this.initWidth = document.getElementById('card1').offsetWidth
      const option4 = {
        backgroundColor: '#040f3c',
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: '2%',
          right: '4%',
          bottom: '14%',
          top: '16%',
          containLabel: true
        },
        legend: {
          data: ['北京', '深圳', '上海'],
          right: 10,
          top: 12,
          textStyle: {
            color: '#fff'
          },
          itemWidth: 12,
          itemHeight: 10
          // itemGap: 35
        },
        xAxis: {
          type: 'category',
          data: downCrosXTimes,
          axisLine: {
            lineStyle: {
              color: 'white'
            }
          },
          axisLabel: {
            // interval: 0,
            // rotate: 40,
            textStyle: {
              fontFamily: 'Microsoft YaHei'
            }
          }
        },

        yAxis: {
          type: 'value',
          max: '10',
          axisLine: {
            show: false,
            lineStyle: {
              color: 'white'
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: 'rgba(255,255,255,0.3)'
            }
          },
          axisLabel: {}
        },
        dataZoom: [
          {
            show: true,
            height: 12,
            xAxisIndex: [0],
            bottom: '8%',
            start: 10,
            end: 90,
            handleIcon:
              'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
            handleSize: '110%',
            handleStyle: {
              color: '#d3dee5'
            },
            textStyle: {
              color: '#fff'
            },
            borderColor: '#90979c'
          },
          {
            type: 'inside',
            show: true,
            height: 15,
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: '北京',
            type: 'line',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#fccb05'
                  },
                  {
                    offset: 1,
                    color: '#f5804d'
                  }
                ]),
                barBorderRadius: 12
              }
            },
            areaStyle: {},
            data: downCrosYNums
          },
          {
            name: '深圳',
            type: 'line',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#8bd46e'
                  },
                  {
                    offset: 1,
                    color: '#09bcb7'
                  }
                ]),
                barBorderRadius: 11
              }
            },
            areaStyle: {},
            data: downSecrYNums
          },
          {
            name: '上海',
            type: 'line',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#248ff7'
                  },
                  {
                    offset: 1,
                    color: '#6851f1'
                  }
                ]),
                barBorderRadius: 11
              }
            },
            areaStyle: {},
            data: downAllYNums
          }
        ]
      }
      const option5 = {
        backgroundColor: '#040f3c',
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: '2%',
          right: '4%',
          bottom: '14%',
          top: '16%',
          containLabel: true
        },
        legend: {
          data: ['北京', '深圳', '上海'],
          right: 10,
          top: 12,
          textStyle: {
            color: '#fff'
          },
          itemWidth: 12,
          itemHeight: 10
          // itemGap: 35
        },
        xAxis: {
          type: 'category',
          data: downCrosXTimes,
          axisLine: {
            lineStyle: {
              color: 'white'
            }
          },
          axisLabel: {
            // interval: 0,
            // rotate: 40,
            textStyle: {
              fontFamily: 'Microsoft YaHei'
            }
          }
        },

        yAxis: {
          type: 'value',
          max: '10',
          axisLine: {
            show: false,
            lineStyle: {
              color: 'white'
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: 'rgba(255,255,255,0.3)'
            }
          },
          axisLabel: {}
        },
        dataZoom: [
          {
            show: true,
            height: 12,
            xAxisIndex: [0],
            bottom: '8%',
            start: 10,
            end: 90,
            handleIcon:
              'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
            handleSize: '110%',
            handleStyle: {
              color: '#d3dee5'
            },
            textStyle: {
              color: '#fff'
            },
            borderColor: '#90979c'
          },
          {
            type: 'inside',
            show: true,
            height: 15,
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: '北京',
            type: 'line',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#fccb05'
                  },
                  {
                    offset: 1,
                    color: '#f5804d'
                  }
                ]),
                barBorderRadius: 12
              }
            },
            areaStyle: {},
            data: downCrosYNums
          },
          {
            name: '深圳',
            type: 'line',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#8bd46e'
                  },
                  {
                    offset: 1,
                    color: '#09bcb7'
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: downSecrYNums
          },
          {
            name: '上海',
            type: 'line',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#248ff7'
                  },
                  {
                    offset: 1,
                    color: '#6851f1'
                  }
                ]),
                barBorderRadius: 11
              }
            },
            areaStyle: {},
            data: downAllYNums
          }
        ]
      }
      const option6 = {
        backgroundColor: '#040f3c',
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: '2%',
          right: '4%',
          bottom: '14%',
          top: '16%',
          containLabel: true
        },
        legend: {
          data: ['北京', '深圳', '上海'],
          right: 10,
          top: 12,
          textStyle: {
            color: '#fff'
          },
          itemWidth: 12,
          itemHeight: 10
          // itemGap: 35
        },
        xAxis: {
          type: 'category',
          data: downCrosXTimes,
          axisLine: {
            lineStyle: {
              color: 'white'
            }
          },
          axisLabel: {
            // interval: 0,
            // rotate: 40,
            textStyle: {
              fontFamily: 'Microsoft YaHei'
            }
          }
        },

        yAxis: {
          type: 'value',
          max: '10',
          axisLine: {
            show: false,
            lineStyle: {
              color: 'white'
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: 'rgba(255,255,255,0.3)'
            }
          },
          axisLabel: {}
        },
        dataZoom: [
          {
            show: true,
            height: 12,
            xAxisIndex: [0],
            bottom: '8%',
            start: 10,
            end: 90,
            handleIcon:
              'path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z',
            handleSize: '110%',
            handleStyle: {
              color: '#d3dee5'
            },
            textStyle: {
              color: '#fff'
            }
          },
          {
            type: 'inside',
            show: true,
            height: 15,
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: '北京',
            type: 'line',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#fccb05'
                  },
                  {
                    offset: 1,
                    color: '#f5804d'
                  }
                ]),
                barBorderRadius: 12
              }
            },
            areaStyle: {},
            data: downCrosYNums
          },
          {
            name: '深圳',
            type: 'line',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#8bd46e'
                  },
                  {
                    offset: 1,
                    color: '#09bcb7'
                  }
                ]),
                barBorderRadius: 11
              }
            },
            areaStyle: {},
            data: downSecrYNums
          },
          {
            name: '上海',
            type: 'line',
            barWidth: '10%',
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: '#248ff7'
                  },
                  {
                    offset: 1,
                    color: '#6851f1'
                  }
                ]),
                barBorderRadius: 11
              }
            },
            areaStyle: {},
            data: downAllYNums
          }
        ]
      }
      myChart4.setOption(option4)
      myChart5.setOption(option5)
      myChart6.setOption(option6)
      let resize = {
        height: this.initHeight,
        width: this.initWidth
      }

      myChart4.resize(resize)

      myChart5.resize(resize)

      myChart6.resize(resize)
    }
  },
  components: {
    // 'dashboard-one': loginUserNum
    // 'dashboard-two': loginUserNumDay
  },
  mounted () {
    this.getDayClienCount()
    // TC服务器样式动态生成
    if (this.tc1_1 >= 90 && this.tc1_2 >= 90) {
      this.expression1 = true
      this.expression2 = false
      this.expression3 = false
    } else if (this.tc1_1 >= 70 && this.tc1_2 >= 70) {
      this.expression1 = false
      this.expression2 = true
      this.expression3 = false
    } else {
      this.expression1 = false
      this.expression2 = false
      this.expression3 = true
    }

    // 登录用户数量柱状图回调函数
    this.getInfoListDay(this.initItem)
    // 账户并发数量折线图回调函数
    this.getInfoListDay2(this.initItem2)
    // setInterval(() => {
    //   this.getShi()
    // }, 600)
    // 基于准备好的dom，初始化echarts实例

    let myChart7 = this.$echarts.init(document.getElementById('main7'))
    let myChart8 = this.$echarts.init(document.getElementById('main8'))
    let myChart9 = this.$echarts.init(document.getElementById('main9'))
    let myChart10 = this.$echarts.init(document.getElementById('main10'))
    let myChart11 = this.$echarts.init(document.getElementById('main11'))

    this.initHeight8 = document.getElementById('card8').offsetHeight
    this.initWidth8 = document.getElementById('card8').offsetWidth

    this.initHeight9 = document.getElementById('card9').offsetHeight
    this.initWidth9 = document.getElementById('card9').offsetWidth

    // 指定图表的配置项和数据

    var geoCoordMap = {
      上海: [119.1803, 31.2891],
      福建: [119.4543, 25.9222],
      重庆: [108.384366, 30.439702],
      北京: [116.4551, 40.2539],
      辽宁: [123.1238, 42.1216],
      河北: [114.4995, 38.1006],
      天津: [117.4219, 39.4189],
      山西: [112.3352, 37.9413],
      陕西: [109.1162, 34.2004],
      甘肃: [103.5901, 36.3043],
      宁夏: [106.3586, 38.1775],
      青海: [101.4038, 36.8207],
      新疆: [87.9236, 43.5883],
      西藏: [91.11, 29.97],
      四川: [103.9526, 30.7617],
      吉林: [125.8154, 44.2584],
      山东: [117.1582, 36.8701],
      河南: [113.4668, 34.6234],
      江苏: [118.8062, 31.9208],
      安徽: [117.29, 32.0581],
      湖北: [114.3896, 30.6628],
      浙江: [119.5313, 29.8773],
      内蒙古: [110.3467, 41.4899],
      江西: [116.0046, 28.6633],
      湖南: [113.0823, 28.2568],
      贵州: [106.6992, 26.7682],
      云南: [102.9199, 25.4663],
      深圳: [113.12244, 23.009505],
      广西: [108.479, 23.1152],
      海南: [110.3893, 19.8516],
      黑龙江: [127.9688, 45.368],
      台湾: [121.4648, 25.563]
    }
    var chinaDatas = [
      {
        name: '北京',
        value: 86
      },
      {
        name: '上海',
        value: 65
      },
      {
        name: '深圳',
        value: 53
      },
      {
        name: '福建',
        value: 48
      },
      {
        name: '河北',
        value: 2
      },
      {
        name: '天津',
        value: 6
      },
      {
        name: '山西',
        value: 12
      },
      {
        name: '陕西',
        value: 2
      },
      {
        name: '甘肃',
        value: 4
      },
      {
        name: '宁夏',
        value: 5
      },
      {
        name: '青海',
        value: 3
      },
      {
        name: '新疆',
        value: 0.4
      },
      {
        name: '西藏',
        value: 0.3
      },
      {
        name: '四川',
        value: 22
      },
      {
        name: '重庆',
        value: 12
      },
      {
        name: '山东',
        value: 9
      },
      {
        name: '河南',
        value: 0.9
      },
      {
        name: '江苏',
        value: 24
      },
      {
        name: '安徽',
        value: 15
      },
      {
        name: '湖北',
        value: 6
      },
      {
        name: '浙江',
        value: 15
      },
      {
        name: '内蒙古',
        value: 0.3
      },
      {
        name: '江西',
        value: 34
      },
      {
        name: '湖南',
        value: 12
      },
      {
        name: '贵州',
        value: 0.8
      },
      {
        name: '广西',
        value: 16
      },
      {
        name: '海南',
        value: 37
      },
      {
        name: '辽宁',
        value: 0.2
      },
      {
        name: '吉林',
        value: 0.4
      },
      {
        name: '云南',
        value: 34
      },
      {
        name: '黑龙江',
        value: 5
      },
      {
        name: '台湾',
        value: 43
      }
    ]
    var convertData = function (data) {
      var res = []
      for (var i = 0; i < data.length; i++) {
        var geoCoord = geoCoordMap[data[i].name]
        if (geoCoord) {
          res.push({
            name: data[i].name,
            value: geoCoord.concat(data[i].value)
          })
        }
      }
      return res
    }
    const option7 = {
      backgroundColor: '#040f3c',
      geo: {
        show: true,
        map: 'china',
        zoom: 1,
        label: {
          normal: {
            show: false
          },
          emphasis: {
            show: false
          }
        },
        roam: false,
        itemStyle: {
          normal: {
            areaColor: '#022a5b',
            borderWidth: 3,
            borderColor: '#00FEFF',
            shadowColor: 'rgba(3,221,255,0.8)',
            shadowBlur: 30
          }
        }
      },
      series: [
        {
          type: 'map',
          map: 'china',
          zoom: 1,
          roam: false, // 是否开启平游或缩放

          geoIndex: 1,
          aspectScale: 0.75, // 长宽比
          showLegendSymbol: false, // 存在legend时显示
          label: {
            normal: {
              show: false
            },
            emphasis: {
              show: false,
              textStyle: {
                color: '#fff'
              }
            }
          },
          // roam: false,
          itemStyle: {
            normal: {
              areaColor: '#022a5b',
              borderColor: '#0073DA',
              borderWidth: 1
            },
            emphasis: {
              areaColor: '#022a5b'
            }
          }
        },
        {
          type: 'effectScatter',
          coordinateSystem: 'geo',
          z: 10,
          data: convertData(
            chinaDatas
              .sort(function (a, b) {
                return b.value - a.value
              })
              .slice(2, 3)
          ),
          symbolSize: 20,
          showEffectOn: 'render',
          rippleEffect: {
            brushType: 'stroke'
          },
          label: {
            normal: {
              show: true,
              formatter: function (params) {
                return (
                  '{fline|' +
                  ' ' +
                  params.data.name +
                  ' ' +
                  '下载机密文件' +
                  '}'
                )
              },
              position: 'top',
              distance: 20,
              backgroundColor: 'rgba(3,219,255,.8)',
              padding: [0, 0],
              borderRadius: 3,
              lineHeight: 24,
              verticalAlign: 'middle',
              color: '#fff',
              z: 11,
              rich: {
                fline: {
                  padding: [0, 10],
                  color: '#ffffff'
                }
              }
            }
          },
          itemStyle: {
            normal: {
              color: 'rgba(3,219,255,1)',
              shadowBlur: 8
            }
          }
        },
        {
          type: 'effectScatter',
          coordinateSystem: 'geo',
          z: 10,
          data: convertData(
            chinaDatas
              .sort(function (a, b) {
                return b.value - a.value
              })
              .slice(1, 2)
          ),
          symbolSize: 20,
          showEffectOn: 'render',
          rippleEffect: {
            brushType: 'stroke'
          },
          label: {
            normal: {
              show: true,
              formatter: function (params) {
                return (
                  '{fline|' + ' ' + params.data.name + ' ' + '跨部门登录' + '}'
                )
              },
              position: 'top',
              distance: 20,
              backgroundColor: 'rgba(248,231,28,.7)',
              padding: [0, 0],
              borderRadius: 3,
              lineHeight: 24,
              verticalAlign: 'middle',
              color: '#fff',
              rich: {
                fline: {
                  padding: [0, 10],
                  color: '#ffffff'
                }
              }
            }
          },
          itemStyle: {
            normal: {
              color: 'rgba(248,231,28,0.9)',
              shadowBlur: 8
            }
          }
        },
        {
          type: 'effectScatter',
          coordinateSystem: 'geo',
          z: 10,
          data: convertData(
            chinaDatas
              .sort(function (a, b) {
                return b.value - a.value
              })
              .slice(0, 1)
          ),
          symbolSize: 20,
          showEffectOn: 'render',
          rippleEffect: {
            brushType: 'stroke'
          },
          label: {
            normal: {
              show: true,
              formatter: function (params) {
                return (
                  '{fline|' + ' ' + params.data.name + ' ' + '跨部门登录' + '}'
                )
              },
              position: 'top',
              distance: 20,
              backgroundColor: 'rgba(219,90,39,.8)',
              padding: [0, 0],
              borderRadius: 3,
              lineHeight: 24,
              verticalAlign: 'middle',
              color: '#fff',
              rich: {
                fline: {
                  padding: [0, 10],
                  color: '#ffffff'
                }
              }
            }
          },
          itemStyle: {
            normal: {
              color: 'rgba(219,90,39,0.9)',
              shadowBlur: 8
            }
          }
        }
      ]
    }
    const option8 = {
      series: [
        {
          color: ['#f78c44', '#2e507a'],

          type: 'pie',
          radius: ['60%', '70%'],
          avoidLabelOverlap: false,
          label: {
            normal: {
              show: true,
              position: 'center'
            },
            emphasis: {
              show: true,
              textStyle: {
                fontSize: '12',
                fontWeight: 'bold'
              }
            }
          },
          labelLine: {
            normal: {
              show: false
            }
          },
          data: [
            {
              value: 25,
              name: '25%',
              label: {
                normal: {
                  textStyle: {
                    fontSize: '14',
                    fontWeight: 'bold'
                  }
                }
              }
            },
            {
              value: 75,

              label: {
                normal: {
                  textStyle: {
                    fontSize: '12',
                    color: '#999',
                    fontWeight: 'bold'
                  },
                  padding: [150, 0, 0, 0]
                }
              }
            }
          ]
        }
      ]
    }
    var fontColor = '#30eee9'
    const option9 = {
      // backgroundColor: '#11183c',
      grid: {
        left: '5%',
        right: '10%',
        top: '20%',
        bottom: '15%',
        containLabel: true
      },

      legend: {
        show: true,
        x: 'center',
        y: '35',
        top: '10',
        icon: 'stack',
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: '#1bb4f6'
        },
        data: ['已采纳', '已发布', '事件数量']
      },
      xAxis: [
        {
          type: 'category',
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: '#397cbc'
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#195384'
            }
          },
          data: ['', '', '', '', '', '', '', '', '', '', '', '']
        }
      ],
      yAxis: [
        {
          type: 'value',

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: '{value}',
            textStyle: {
              color: '#2ad1d2'
            }
          },
          axisLine: {
            lineStyle: {
              color: '#27b4c2'
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#11366e'
            }
          }
        }
      ],
      series: [
        {
          name: '事件数量',
          type: 'line',
          stack: '总量',
          symbol: 'circle',
          symbolSize: 8,
          color: '#aecb56',
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    }
    const option10 = {
      // backgroundColor: '#11183c',
      grid: {
        left: '5%',
        right: '10%',
        top: '20%',
        bottom: '15%',
        containLabel: true
      },

      legend: {
        show: true,
        x: 'center',
        y: '35',
        top: '10',
        icon: 'stack',
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: '#1bb4f6'
        },
        data: ['已采纳', '已发布', '事件数量']
      },
      xAxis: [
        {
          type: 'category',
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: '#397cbc'
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#195384'
            }
          },
          data: ['', '', '', '', '', '', '', '', '', '', '', '']
        }
      ],
      yAxis: [
        {
          type: 'value',

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: '{value}',
            textStyle: {
              color: '#2ad1d2'
            }
          },
          axisLine: {
            lineStyle: {
              color: '#27b4c2'
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#11366e'
            }
          }
        }
      ],
      series: [
        {
          name: '事件数量',
          type: 'line',
          stack: '总量',
          symbol: 'circle',
          symbolSize: 8,
          color: '#aecb56',
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    }
    const option11 = {
      // backgroundColor: '#11183c',
      grid: {
        left: '5%',
        right: '10%',
        top: '20%',
        bottom: '15%',
        containLabel: true
      },

      legend: {
        show: true,
        x: 'center',
        y: '35',
        top: '10',
        icon: 'stack',
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: '#1bb4f6'
        },
        data: ['已采纳', '已发布', '事件数量']
      },
      xAxis: [
        {
          type: 'category',
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: '#397cbc'
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#195384'
            }
          },
          data: ['', '', '', '', '', '', '', '', '', '', '', '']
        }
      ],
      yAxis: [
        {
          type: 'value',

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: '{value}',
            textStyle: {
              color: '#2ad1d2'
            }
          },
          axisLine: {
            lineStyle: {
              color: '#27b4c2'
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: '#11366e'
            }
          }
        }
      ],
      series: [
        {
          name: '事件数量',
          type: 'line',
          stack: '总量',
          symbol: 'circle',
          symbolSize: 8,
          color: '#aecb56',
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    }
    // 使用刚指定的配置项和数据显示图表。

    myChart7.setOption(option7)
    myChart8.setOption(option8)
    myChart9.setOption(option9)
    myChart10.setOption(option10)
    myChart11.setOption(option11)
    let resize3 = {
      height: this.initHeight3,
      width: this.initWidth3
    }
    let resize8 = {
      height: this.initHeight8,
      width: this.initWidth8
    }
    let resize9 = {
      height: this.initHeight9,
      width: this.initWidth9
    }

    myChart7.resize(resize3)
    myChart8.resize(resize8)
    myChart9.resize(resize9)
    myChart10.resize(resize9)
    myChart11.resize(resize9)
  },
  // 计算属性
  computed: {}
  //   <style src="../css/bootstrap.css"></style>
  // <style src="../css/dash5.css"></style>
}
</script>

<style
  src="@/css/clientSideMonitor/behaviorAnalysis/productDataAnalysis/bootstrap.css"
></style>
<style
  src="@/css/clientSideMonitor/behaviorAnalysis/productDataAnalysis/dash5.css"
></style>
