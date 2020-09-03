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
            <img src="@/assets/dbUser.png" />
          </div>
          <div class="fl">
            <p class="usName">在线用户</p>
            <p class="usNum">{{ allClientEventCount }}</p>
          </div>
          <div class="fl">
            <p class="fl usEvent">客户端事件：</p>
            <p class="eventNum fl">{{ allClientEventCount }}</p>
          </div>

          <!--
          <div class="fl">
            <p class="fl usEvent">客户端事件：</p>
            <p class="eventNum fl">127834</p>
          </div>-->
        </div>
        <!-- card1登录用户数量 -->
        <div class="border-container">
          <div class="name-title">登录用户数量</div>
          <ul class="three-pie clearfix">
            <li>
              <div id="sexrate">
                <el-tabs v-model="activeName1" type="card" @tab-click="handleClick1" id="card1">
                  <el-tab-pane label="今日" name="first1">
                    <span slot="label" @click="login1">今日</span>
                    <div id="main1" style="width: 100%; height: 240px; top:-14px"></div>
                  </el-tab-pane>

                  <el-tab-pane label="本周" name="second1">
                    <span slot="label" @click="login2">本周</span>
                    <div id="main2" style="width: 100%; height: 240px; top:-14px"></div>
                  </el-tab-pane>

                  <el-tab-pane label="本月" name="third1">
                    <span slot="label" @click="login3">本月</span>
                    <div id="main3" style="width: 100%; height: 240px; top:-14px"></div>
                  </el-tab-pane>
                </el-tabs>
              </div>
            </li>
          </ul>
        </div>
        <!-- card2账户并发数量 -->
        <div class="border-container">
          <div class="name-title">账户并发数量</div>
          <ul class="three-pie clearfix">
            <li>
              <div id="sexrate">
                <el-tabs v-model="activeName2" type="card" @tab-click="handleClick2" id="card2">
                  <el-tab-pane label="今日" name="first2">
                    <span slot="label" @click="concurrent1">今日</span>
                    <div id="main4" style="width: 100%; height: 240px; top:-14px"></div>
                  </el-tab-pane>

                  <el-tab-pane label="本周" name="second2">
                    <span slot="label" @click="concurrent2">本周</span>
                    <div id="main5" style="width: 100%; height: 240px; top:-14px"></div>
                  </el-tab-pane>

                  <el-tab-pane label="本月" name="third2">
                    <span slot="label" @click="concurrent3">本月</span>
                    <div id="main6" style="width: 100%; height: 240px; top:-14px"></div>
                  </el-tab-pane>
                </el-tabs>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <!-- 中间内容 -->
      <div class="main-middle">
        <div class="border-top-left top_three">
          <div class="fl w30">
            <p class="fl usEvent">安全事件：</p>
            <p class="eventNum1 fl">239</p>
          </div>
          <div class="fl w30">
            <p class="fl usEvent">工时：</p>
            <p class="eventNum2 fl">{{allWorkingHoursToday}}</p>
            <i class="iconfont fl workUp" v-show="workUpShow">&#xe687;</i>
            <i class="iconfont fl workUp" v-show="workOnShow">&#xe64d;</i>
            <p class="workNum fl">{{differenceHours}}</p>
          </div>
          <div class="fl w30">
            <p class="fl usEvent">KPI：</p>
            <p class="eventNum3 fl">{{todayKPI}}</p>
            <i class="iconfont fl KPIUp" v-show="KPIUpUpShow">&#xe687;</i>
            <i class="iconfont fl KPIUp" v-show="KPIUpOnShow">&#xe64d;</i>
            <p class="KPINum fl">{{yesterdayKPI}}</p>
          </div>
        </div>
        <div class="border-main">
          <!-- 中国地图 -->
          <div class="chineMap" id="card7">
            <div id="main7" style="width: 100%; height:634px;"></div>
          </div>
          <!-- 头部三个服务器事件次数 -->
          <div class="chineMap-top bot-info">
            <ul>
              <li>
                <div class="dayName">服务器本日事件次数</div>
                <div class="dayNum clearfix">
                  <p class="dayNum-1">1421</p>
                  <p class="dayNum-2">日环比：-2%</p>
                </div>
              </li>

              <li>
                <div class="dayName">客户端本日事件次数</div>
                <div class="dayNum clearfix">
                  <p class="dayNum-1">1421</p>
                  <p class="dayNum-2">日环比：-2%</p>
                </div>
              </li>
              <li>
                <div class="dayName">异常操作次数</div>
                <div class="dayNum clearfix">
                  <p class="dayNum-1">1421</p>
                  <p class="dayNnmColor">日环比：-2%</p>
                  <!-- <p class="dayNum-2-2">日环比：-2%</p> -->
                </div>
              </li>
            </ul>
          </div>
          <!-- 大的轮播盒子 -->
          <el-carousel
            :interval="4000"
            arrow="never"
            height="270px"
            class="shufflingBox"
            indicator-position="outside"
          >
            <!-- 深圳公司轮播内容 -->
            <el-carousel-item>
              <!-- 头部公司名称 -->
              <div class="figure-title clearfix">
                <div class="point">
                  <div class="points"></div>
                </div>
                <div class="point-title">深圳公司</div>
              </div>
              <!-- 公司下面的圆形图和折线图 -->
              <div class="comBox clearfix">
                <div class="com-right">
                  <div class="info-right" id="card9">
                    <ul class="clearfix">
                      <li>
                        <div id="sexrate">
                          <el-tabs v-model="activeName4" type="card" @tab-click="3">
                            <el-tab-pane label="今日" name="first4">
                              <div id="main9_2" style="width: 250px; height: 170px;"></div>
                            </el-tab-pane>

                            <el-tab-pane label="本周" name="second4">
                              <div id="main10_2" style="width: 250px; height: 170px;"></div>
                            </el-tab-pane>

                            <el-tab-pane label="本月" name="third4">
                              <div id="main11_2" style="width: 250px; height: 170px;"></div>
                            </el-tab-pane>
                          </el-tabs>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
                <div class="com-left">
                  <div class="info-left">异常事件占比</div>
                  <div id="main8_1" style="width: 90px;height:70px;padding-left: 20px;"></div>
                </div>

                <!-- 今日事件  -->
                <div class="info-day">
                  <div class="info-up clearfix">
                    <div class="up-point info-fl"></div>
                    <div class="info-fl up-day">今日 客户端事件</div>
                    <div class="info-fl up-day-num">&nbsp;{{ allClientEventCount }}</div>
                    <div class="info-fl up-abnormal">异常事件</div>
                    <div class="info-fl up-abnormal-num">{{ abnormalClientEventCount }}</div>
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
            </el-carousel-item>

            <el-carousel-item>
              <!-- 头部公司名称 -->
              <div class="figure-title clearfix">
                <div class="point">
                  <div class="points"></div>
                </div>
                <div class="point-title">北京公司</div>
              </div>
              <!-- 公司下面的圆形图和折线图 -->
              <div class="comBox clearfix">
                <div class="com-right">
                  <div class="info-right" id="card9">
                    <ul class="clearfix">
                      <li>
                        <div id="sexrate">
                          <el-tabs v-model="activeName4" type="card" @tab-click="3">
                            <el-tab-pane label="今日" name="first4">
                              <div id="main9_3" style="width: 250px; height: 170px;"></div>
                            </el-tab-pane>

                            <el-tab-pane label="本周" name="second4">
                              <div id="main10_3" style="width: 250px; height: 170px;"></div>
                            </el-tab-pane>

                            <el-tab-pane label="本月" name="third4">
                              <div id="main11_3" style="width: 250px; height: 170px;"></div>
                            </el-tab-pane>
                          </el-tabs>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
                <div class="com-left">
                  <div class="info-left">异常事件占比</div>
                  <div id="main8_2" style="width: 90px;height:70px;padding-left: 20px;"></div>
                </div>

                <!-- 今日事件  -->
                <div class="info-day">
                  <div class="info-up clearfix">
                    <div class="up-point info-fl"></div>
                    <div class="info-fl up-day">今日 客户端事件</div>
                    <div class="info-fl up-day-num">&nbsp;{{ allClientEventCount }}</div>
                    <div class="info-fl up-abnormal">异常事件</div>
                    <div class="info-fl up-abnormal-num">{{ abnormalClientEventCount }}</div>
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
            </el-carousel-item>

            <el-carousel-item>
              <!-- 头部公司名称 -->
              <div class="figure-title clearfix">
                <div class="point">
                  <div class="points"></div>
                </div>
                <div class="point-title">上海公司</div>
              </div>
              <!-- 公司下面的圆形图和折线图 -->
              <div class="comBox clearfix">
                <div class="com-right">
                  <div class="info-right" id="card9">
                    <ul class="clearfix">
                      <li>
                        <div id="sexrate">
                          <el-tabs v-model="activeName4" type="card" @tab-click="3">
                            <el-tab-pane label="今日" name="first4">
                              <div id="main9" style="width: 250px; height: 170px;"></div>
                            </el-tab-pane>

                            <el-tab-pane label="本周" name="second4">
                              <div id="main10" style="width: 250px; height: 170px;"></div>
                            </el-tab-pane>

                            <el-tab-pane label="本月" name="third4">
                              <div id="main11" style="width: 250px; height: 170px;"></div>
                            </el-tab-pane>
                          </el-tabs>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
                <div class="com-left">
                  <div class="info-left">异常事件占比</div>
                  <div id="main8_3" style="width: 90px;height:70px;padding-left: 20px;"></div>
                </div>

                <!-- 今日事件  -->
                <div class="info-day">
                  <div class="info-up clearfix">
                    <div class="up-point info-fl"></div>
                    <div class="info-fl up-day">今日 客户端事件</div>
                    <div class="info-fl up-day-num">&nbsp;{{ allClientEventCount }}</div>
                    <div class="info-fl up-abnormal">异常事件</div>
                    <div class="info-fl up-abnormal-num">{{ abnormalClientEventCount }}</div>
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
            </el-carousel-item>

            <el-carousel-item>
              <!-- 头部公司名称 -->
              <div class="figure-title clearfix">
                <div class="point">
                  <div class="points"></div>
                </div>
                <div class="point-title">成都公司</div>
              </div>
              <!-- 公司下面的圆形图和折线图 -->
              <div class="comBox clearfix">
                <div class="com-right">
                  <div class="info-right" id="card9">
                    <ul class="clearfix">
                      <li>
                        <div id="sexrate">
                          <el-tabs v-model="activeName4" type="card" @tab-click="3">
                            <el-tab-pane label="今日" name="first4">
                              <div id="main9_4" style="width: 250px; height: 170px;"></div>
                            </el-tab-pane>

                            <el-tab-pane label="本周" name="second4">
                              <div id="main10_4" style="width: 250px; height: 170px;"></div>
                            </el-tab-pane>

                            <el-tab-pane label="本月" name="third4">
                              <div id="main11_4" style="width: 250px; height: 170px;"></div>
                            </el-tab-pane>
                          </el-tabs>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
                <div class="com-left">
                  <div class="info-left">异常事件占比</div>
                  <div id="main8_4" style="width: 90px;height:70px;padding-left: 20px;"></div>
                </div>

                <!-- 今日事件  -->
                <div class="info-day">
                  <div class="info-up clearfix">
                    <div class="up-point info-fl"></div>
                    <div class="info-fl up-day">今日 客户端事件</div>
                    <div class="info-fl up-day-num">&nbsp;{{ allClientEventCount }}</div>
                    <div class="info-fl up-abnormal">异常事件</div>
                    <div class="info-fl up-abnormal-num">{{ abnormalClientEventCount }}</div>
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
            </el-carousel-item>
          </el-carousel>
        </div>
      </div>
      <!-- 右侧内容 -->
      <div class="main-right">
        <div class="border-top-right">
          <div class="newTime clearfix">
            <p class="fr">更新</p>
            <p class="fr updateTime">{{ date }}</p>
          </div>
          <div class="newEvent clearfix">
            <p class="fr newEventColor">239745</p>
            <p class="fr">服务器端事件：</p>
          </div>
        </div>
        <div class="border-container">
          <div class="name-title">最近事件</div>
          <ul class="three-pie clearfix">
            <li>
              <div id="recentEvents">
                <el-tabs v-model="activeName3" type="card" @tab-click="handleClick3" id="3">
                  <el-tab-pane label="全部" name="first3">
                    <el-table :data="tableData.slice((pagesNum - 1) * pageSize,pagesNum * pageSize)" :show-header="false" style="width: 100%">
                      <el-table-column prop="state" width="30">
                        <template slot-scope="{ row }">
                          <div v-if="row.recentInfo === '机密查看'" class="state"></div>
                          <div v-else-if="row.recentInfo === '机密下载'" class="state"></div>
                          <div v-else-if="row.recentInfo === '超量下载,'" class="state"></div>
                          <div v-else-if="row.recentInfo === '超量下载,机密下载'" class="state"></div>
                          <div v-else-if="row.recentInfo === '跨部门下载,'" class="state"></div>
                          <div v-else-if="row.recentInfo === '跨部门下载'" class="state"></div>
                          <div v-else-if="row.recentInfo === '跨公司查看'" class="state"></div>
                          <div v-else-if="row.recentInfo === '跨公司查看,'" class="state"></div>
                          <div v-else-if="row.recentInfo === '跨公司查看,跨部门查看'" class="state"></div>
                          <div v-else-if="row.recentInfo === '跨公司查看,'" class="state"></div>
                          <div v-else-if="row.recentInfo === '跨部门下载,机密下载'" class="state"></div>
                          <div v-else-if="row.recentInfo === '超量下载,跨部门下载,机密下载'" class="state"></div>
                          <div v-else-if="row.recentInfo === '跨部门查看,机密查看'" class="state"></div>
                          <div v-else-if="row.recentInfo === '超量下载,跨部门下载,'" class="state"></div>
                          <div v-else class="stateGreen"></div>
                        </template>
                      </el-table-column>
                      <!-- 文件查看 -->
                      <el-table-column prop="recentInfo" width="100px"></el-table-column>
                      <!-- 公司  -->
                      <el-table-column prop="company" width="125px"></el-table-column>
                      <!-- 用户名 -->
                      <el-table-column prop="employeeName" width="70px"></el-table-column>
                      <!-- 查看时间 -->
                      <el-table-column prop="actionBeginTime" width="200px"></el-table-column>
                    </el-table>
                  </el-tab-pane>

                  <el-tab-pane label="异常" name="second3">
                    <el-table :data="tableData" :show-header="false" style="width: 100%">
                      <el-table-column prop="state" width="30">
                        <template slot-scope="{ row }">
                          <div v-if="row.type === '超量下载文件'" class="state"></div>
                          <div v-else-if="row.type === '跨部门登录'" class="state"></div>
                          <div v-else class="stateGreen"></div>
                        </template>
                      </el-table-column>
                      <el-table-column prop="type"></el-table-column>
                      <el-table-column prop="address"></el-table-column>
                      <el-table-column prop="date"></el-table-column>
                    </el-table>
                  </el-tab-pane>

                  <el-tab-pane label="正常" name="third3">
                    <el-table :data="tableData" :show-header="false" style="width: 100%">
                      <el-table-column prop="state" width="30">
                        <template slot-scope="{ row }">
                          <div v-if="row.type === '超量下载文件'" class="state"></div>
                          <div v-else-if="row.type === '跨部门登录'" class="state"></div>
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
          <el-pagination
            class="paging"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pagesNum"
            :page-sizes="[25, 50, 100]"
            :page-size="pageSize"
            :total="tableData.length"
            small
            layout="total,prev, pager, next"
          ></el-pagination>
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
                      >{{ row.id }}</div>
                      <div
                        v-else-if="row.id > 10"
                        style="background-color:#f78c44;width:40px;height:20px;color:white;border-radius:5px;line-height:20px;text-align:center"
                      >{{ row.id }}</div>
                      <div
                        v-else
                        style="background-color:#878787;width:40px;height:20px;color:white;border-radius:5px;line-height:20px;text-align:center"
                      >{{ row.id }}</div>
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
            />-->
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
                <div class="fr" :class="{ expRed: item.network >= 90 }">{{ item.network + "%" }}</div>
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
                <div class="fr" :class="{ expRed: item.memory >= 90 }">{{ item.memory + "%" }}</div>
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
                <div class="fr" :class="{ expRed: item.CPU >= 90 }">{{ item.CPU + "%" }}</div>
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
  // 部门事件数量接口
  infoRefr,
  // 当天客户端总事件和异常事件
  getDayClienCount,
  // Map地图跨公司访问
  mapAcrossCompany,
  // 总工时+总KPI
  sumWorking,
  // 最近事件接口
  recentlyInterface
} from "@/api/table.js";
// 引入时间格式
// import moment from 'moment'

export default {
  data() {
    return {
      vvFlag: 1,
      travel: [],
      Nums1: "",
      Nums2: "",
      Nums3: "",
      Nums4: "",
      Nums5: "",
      workUpShow: false,
      workOnShow: true,
      KPIUpUpShow: false,
      KPIUpOnShow: true,
      // 总工时
      allWorkingHoursToday: "",
      // 相差的工时
      differenceHours: "",
      // 总KPI
      todayKPI: "",
      // 相差的KPI
      yesterdayKPI: "",
      // 今日客户端事件总数
      allClientEventCount: "",
      // 今日客户端异常事件总数
      abnormalClientEventCount: "",
      // 在线用户人数
      ageNum: "",
      // 分页器数据
      pagesNum: 1, // 当前页
      pageSize: 4, // 每页条数
      total: 5, // 总页数
      // 模拟登录用户数量
      zheXianFlag: 2,
      zheXianDates: "",
      // 模拟账号并发数量
      zheXianFlagDown: 2,
      zheXianDateDown: "",
      actives1: false,
      isError: false,
      date: "",
      // 登录用户数量默认项
      activeName1: "second1",
      // 账号并发数量默认项
      activeName2: "second2",
      // 最近事件默认项
      activeName3: "first3",
      // 深圳公司事件数量
      activeName4: "first4",
      // 部门事件数量
      stateNum: 10,
      // 异常事件占比
      // 最近事件data数据

      tableData: [

      ],
      // 部门事件data数据
      userList: [],
      // tc四组服务器数据结构
      tcList: [
        {
          name: "TC1服务器",
          network: 70,
          memory: 70,
          CPU: 50
        },
        {
          name: "TC2服务器",
          network: 80,
          memory: 70,
          CPU: 80
        },
        {
          name: "TC3服务器",
          network: 70,
          memory: 70,
          CPU: 90
        },
        {
          name: "TC4服务器",
          network: 80,
          memory: 90,
          CPU: 80
        }
      ]
    };
  },
  created() {
    // 现在时间，随时刷新
    this.nowTTimes();
    // 初始化带上时间
    this.Data();
    // this.getShiTable()
    this.eventList();
    // this.getShi()
    this.sumAllWorking();
    // 最近事件函数
    this.recentlyList()
  },

  methods: {
    // 在线用户人数
    // async getTCInfo () {
    //   const { data } = await onlineUser()
    //   const { resultInfo } = data
    // },
    // Map地图跨公司访问
    async myMapChart() {
      var myChart = this.$echarts.init(document.getElementById("main7"));
      var geoCoordMap = {
        上海: [121.4648, 31.2891],
        东莞: [113.8953, 22.901],
        东营: [118.7073, 37.5513],
        中山: [113.4229, 22.478],
        临汾: [111.4783, 36.1615],
        临沂: [118.3118, 35.2936],
        丹东: [124.541, 40.4242],
        丽水: [119.5642, 28.1854],
        乌鲁木齐: [87.9236, 43.5883],
        佛山: [112.8955, 23.1097],
        保定: [115.0488, 39.0948],
        兰州: [103.5901, 36.3043],
        包头: [110.3467, 41.4899],
        北京: [116.4551, 40.2539],
        北海: [109.314, 21.6211],
        南京: [118.8062, 31.9208],
        南宁: [108.479, 23.1152],
        南昌: [116.0046, 28.6633],
        南通: [121.1023, 32.1625],
        厦门: [118.1689, 24.6478],
        台州: [121.1353, 28.6688],
        合肥: [117.29, 32.0581],
        呼和浩特: [111.4124, 40.4901],
        咸阳: [108.4131, 34.8706],
        哈尔滨: [127.9688, 45.368],
        唐山: [118.4766, 39.6826],
        嘉兴: [120.9155, 30.6354],
        大同: [113.7854, 39.8035],
        大连: [122.2229, 39.4409],
        天津: [117.4219, 39.4189],
        太原: [112.3352, 37.9413],
        威海: [121.9482, 37.1393],
        宁波: [121.5967, 29.6466],
        宝鸡: [107.1826, 34.3433],
        宿迁: [118.5535, 33.7775],
        常州: [119.4543, 31.5582],
        广州: [113.5107, 23.2196],
        廊坊: [116.521, 39.0509],
        延安: [109.1052, 36.4252],
        张家口: [115.1477, 40.8527],
        徐州: [117.5208, 34.3268],
        德州: [116.6858, 37.2107],
        惠州: [114.6204, 23.1647],
        成都: [103.9526, 30.7617],
        扬州: [119.4653, 32.8162],
        承德: [117.5757, 41.4075],
        拉萨: [91.1865, 30.1465],
        无锡: [120.3442, 31.5527],
        日照: [119.2786, 35.5023],
        昆明: [102.9199, 25.4663],
        杭州: [119.5313, 29.8773],
        枣庄: [117.323, 34.8926],
        柳州: [109.3799, 24.9774],
        株洲: [113.5327, 27.0319],
        武汉: [114.3896, 30.6628],
        汕头: [117.1692, 23.3405],
        江门: [112.6318, 22.1484],
        沈阳: [123.1238, 42.1216],
        沧州: [116.8286, 38.2104],
        河源: [114.917, 23.9722],
        泉州: [118.3228, 25.1147],
        泰安: [117.0264, 36.0516],
        泰州: [120.0586, 32.5525],
        济南: [117.1582, 36.8701],
        济宁: [116.8286, 35.3375],
        海口: [110.3893, 19.8516],
        淄博: [118.0371, 36.6064],
        淮安: [118.927, 33.4039],
        深圳: [114.5435, 22.5439],
        清远: [112.9175, 24.3292],
        温州: [120.498, 27.8119],
        渭南: [109.7864, 35.0299],
        湖州: [119.8608, 30.7782],
        湘潭: [112.5439, 27.7075],
        滨州: [117.8174, 37.4963],
        潍坊: [119.0918, 36.524],
        烟台: [120.7397, 37.5128],
        玉溪: [101.9312, 23.8898],
        珠海: [113.7305, 22.1155],
        盐城: [120.2234, 33.5577],
        盘锦: [121.9482, 41.0449],
        石家庄: [114.4995, 38.1006],
        福州: [119.4543, 25.9222],
        秦皇岛: [119.2126, 40.0232],
        绍兴: [120.564, 29.7565],
        聊城: [115.9167, 36.4032],
        肇庆: [112.1265, 23.5822],
        舟山: [122.2559, 30.2234],
        苏州: [120.6519, 31.3989],
        莱芜: [117.6526, 36.2714],
        菏泽: [115.6201, 35.2057],
        营口: [122.4316, 40.4297],
        葫芦岛: [120.1575, 40.578],
        衡水: [115.8838, 37.7161],
        衢州: [118.6853, 28.8666],
        西宁: [101.4038, 36.8207],
        西安: [109.1162, 34.2004],
        贵阳: [106.6992, 26.7682],
        连云港: [119.1248, 34.552],
        邢台: [114.8071, 37.2821],
        邯郸: [114.4775, 36.535],
        郑州: [113.4668, 34.6234],
        鄂尔多斯: [108.9734, 39.2487],
        重庆: [107.7539, 30.1904],
        金华: [120.0037, 29.1028],
        铜川: [109.0393, 35.1947],
        银川: [106.3586, 38.1775],
        镇江: [119.4763, 31.9702],
        长春: [125.8154, 44.2584],
        长沙: [113.0823, 28.2568],
        长治: [112.8625, 36.4746],
        阳泉: [113.4778, 38.0951],
        青岛: [120.4651, 36.3373],
        韶关: [113.7964, 24.7028]
      };
      try {
        const { data } = await mapAcrossCompany();
        const { mapCrossCompanyList } = data;
        for (var i = 0; i < mapCrossCompanyList.length; i++) {
          if (mapCrossCompanyList[i].company === "成都分公司" && mapCrossCompanyList[i].ownerCompany === "上海分公司") {
            this.travel.push({ start_city: "成都", end_city: "上海" })
          }
          if (mapCrossCompanyList[i].company === "成都分公司" && mapCrossCompanyList[i].ownerCompany === "北京分公司") {
            this.travel.push({ start_city: "成都", end_city: "北京" })
          }
          if (mapCrossCompanyList[i].company === "成都分公司" && mapCrossCompanyList[i].ownerCompany === "深圳分公司") {
            this.travel.push({ start_city: "成都", end_city: "深圳" })
          }
          if (mapCrossCompanyList[i].company === "北京分公司" && mapCrossCompanyList[i].ownerCompany === "上海分公司") {
            this.travel.push({ start_city: "北京", end_city: "上海" })
          }
          if (mapCrossCompanyList[i].company === "北京分公司" && mapCrossCompanyList[i].ownerCompany === "深圳分公司") {
            this.travel.push({ start_city: "北京", end_city: "深圳" })
          }
          if (mapCrossCompanyList[i].company === "北京分公司" && mapCrossCompanyList[i].ownerCompany === "成都分公司") {
            this.travel.push({ start_city: "北京", end_city: "成都" })
          }
          if (mapCrossCompanyList[i].company === "深圳分公司" && mapCrossCompanyList[i].ownerCompany === "上海分公司") {
            this.travel.push({ start_city: "深圳", end_city: "上海" })
          }
          if (mapCrossCompanyList[i].company === "深圳分公司" && mapCrossCompanyList[i].ownerCompany === "成都分公司") {
            this.travel.push({ start_city: "深圳", end_city: "成都" })
          }
          if (mapCrossCompanyList[i].company === "深圳分公司" && mapCrossCompanyList[i].ownerCompany === "北京分公司") {
            this.travel.push({ start_city: "深圳", end_city: "北京" })
          }
          if (mapCrossCompanyList[i].company === "上海分公司" && mapCrossCompanyList[i].ownerCompany === "深圳分公司") {
            this.travel.push({ start_city: "上海", end_city: "深圳" })
          }
          if (mapCrossCompanyList[i].company === "上海分公司" && mapCrossCompanyList[i].ownerCompany === "成都分公司") {
            this.travel.push({ start_city: "上海", end_city: "成都" })
          }
          if (mapCrossCompanyList[i].company === "上海分公司" && mapCrossCompanyList[i].ownerCompany === "北京分公司") {
            this.travel.push({ start_city: "上海", end_city: "北京" })
          } else {
            this.travel.push({ start_city: "", end_city: "" })
          }
        }
        var travel = this.travel
      } catch (e) {
        console.log(e);
      }

      // var planePath = "image://../../../../../assets/map.png";
      var planePath = "image://../../../../../../assets/map.png";
      var convertData = function(data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
          var dataItem = data[i];
          var fromCoord = geoCoordMap[dataItem[0].name];
          var toCoord = geoCoordMap[dataItem[1].name];
          if (fromCoord && toCoord) {
            res.push({
              fromName: dataItem[0].name,
              toName: dataItem[1].name,
              coords: [fromCoord, toCoord]
            });
          }
        }
        return res;
      };
      var handleData = function(data) {
        let obj = {};
        let arr = [];
        for (let i = 0, len1 = data.length; i < len1; i++) {
          if (arr.indexOf(data[i].start_city) === -1) {
            arr.push(data[i].start_city);
          }
          for (let j = 0, len2 = arr.length; j < len2; j++) {
            obj[arr[j]] = [];
          }
        }
        for (let i = 0, len = data.length; i < len; i++) {
          obj[data[i].start_city].push([
            { name: data[i].start_city },
            { name: data[i].end_city }
          ]);
        }
        return obj;
      };
      var color = [
        "#a6c84c",
        "#ffa022",
        "#46bee9",
        "#f569ef5",
        "#f7545f",
        "#ffa500",
        "#97ffff"
      ];

      var series = [];
      var data = handleData(travel);
      let tempArr = [];
      for (let k in data) {
        tempArr.push([k, data[k]]);
      }
      tempArr.forEach(function(item, i) {
        series.push(
          {
            name: item[0],
            type: "lines",
            zlevel: 1,
            effect: {
              show: true,
              period: 6,
              trailLength: 0.7,
              color: "#fff",
              symbolSize: 3
            },
            lineStyle: {
              normal: {
                color: color[i],
                width: 1,
                curveness: 0.2,
                type: "solid"
              }
            },
            data: convertData(item[1])
          },
          {
            name: item[0],
            type: "lines",
            zlevel: 2,
            symbol: ["none", "arrow"],
            symbolSize: 6,
            effect: {
              show: true,
              period: 6,
              trailLength: 0,
              symbol: planePath,
              symbolSize: 15
            },
            lineStyle: {
              normal: {
                color: color[i],
                width: 1,
                opacity: 0.6,
                curveness: 0.2
              }
            },
            data: convertData(item[1])
          },
          {
            name: item[0],
            type: "effectScatter",
            coordinateSystem: "geo",
            zlevel: 2,
            rippleEffect: {
              brushType: "fill"
            },
            label: {
              normal: {
                show: true,
                position: "top",
                formatter: "{b}",
                color: color[i],
                fontSize: 14
              }
            },
            symbolSize: 4,
            itemStyle: {
              normal: {
                color: color[i]
              }
            },
            data: item[1].map(function(dataItem) {
              return {
                name: dataItem[1].name,
                value: geoCoordMap[dataItem[1].name]
              };
            })
          },
          {
            name: item[0],
            type: "effectScatter",
            coordinateSystem: "geo",
            zlevel: 2,
            rippleEffect: {
              brushType: "fill"
            },
            label: {
              normal: {
                show: true,
                color: color[i],
                position: "top",
                formatter: "{b}",
                fontSize: 14
              }
            },
            symbolSize: 8,
            itemStyle: {
              normal: {
                color: color[i]
              }
            },
            data: [
              {
                name: item[0],
                value: geoCoordMap[item[0]]
              }
            ]
          }
        );
      });
      var option = {
        backgroundColor: "#040f3c",
        geo: {
          map: "china",
          label: {
            emphasis: {
              show: false,
              color: "#fff"
            }
          },
          //是否允许缩放
          roam: false,
          zoom: 1.2,
          itemStyle: {
            normal: {
              color: "rgba(51, 69, 89, .5)",
              backgroundColor: "#516a89",
              borderWidth: 1,
              borderColor: "#404a59"
            },
            emphasis: {
              color: "rgba(37, 43, 61, .5)",
              areaColor: "#323c48"
            }
          }
        },
        series: series
      };
      myChart.setOption(option);
    },
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
    // 总工时+总KPI
    async sumAllWorking() {
      try {
        const { data } = await sumWorking();
        const {
          allWorkingHoursToday,
          differenceHours,
          todayKPI,
          yesterdayKPI
        } = data;
        this.allWorkingHoursToday = allWorkingHoursToday;
        this.differenceHours = differenceHours;
        this.todayKPI = todayKPI;
        this.yesterdayKPI = yesterdayKPI;
        if (differenceHours >= 0) {
          this.workUpShow = true;
          this.workOnShow = false;
        }
        if (yesterdayKPI >= 0) {
          this.KPIUpUpShow = true;
          this.KPIUpOnShow = false;
        }
      } catch (e) {
        console.log(e);
      }
    },

    // 今日客户端事件总数
    async getDayClienCount() {
      try {
        const { data } = await getDayClienCount();
        const { allClientEventCount, abnormalClientEventCount } = data;
        this.allClientEventCount = allClientEventCount;
        this.abnormalClientEventCount = abnormalClientEventCount;
      } catch (e) {
        console.log(e);
      }
    },

    // 分页功能
    // 更改每页个数
    handleSizeChange(val) {
      this.pageSize = val;
      this.pagesNum = 1;
      this.eventList();
    },
    // 切换页
    handleCurrentChange(val) {
      this.pagesNum = val;
      this.eventList();
    },
    // 最近事件列表渲染
    async recentlyList(Flag) {
      const value = {
        ...Flag
      };
      try {
        const { data } = await recentlyInterface(value);
        const { recentEventsList } = data;
        this.tableData = recentEventsList
        console.log(data,recentEventsList)
      } catch (e) {
        console.log(e);
      }
    },
    // 部门事件列表渲染
    async eventList() {
      const value = {
        pagesNum: this.pagesNum,
        actionBeginTime: "2020-01-30 13:41:07",
        actionEndTime: "2020-03-05 13:19:08"
      };
      try {
        const { data } = await infoRefr(value);
        const { tableList } = data;
        this.userList = tableList;
        this.total = data.tableTotalList[0];
      } catch (e) {
        console.log(e);
      }
    },
    // 登录用户数量柱状图数据
    async getInfoListDay(callback) {
      const value = {
        zheXianFlag: this.zheXianFlag,
        zheXianDate: this.zheXianDates
      };
      try {
        const { data } = await getViewZheXian(value);
        const {
          zheXianListAll,
          zheXianListCrossDept,
          zheXianListSecretFile
        } = data;
        // 所有数据
        const AllXTimes = zheXianListAll.map(item => {
          return item.zheXianX;
        });
        const AllYNums = zheXianListAll.map(item => {
          return item.zheXianY;
        });
        // 跨部门数据
        const CrosXTimes = zheXianListCrossDept.map(item => {
          return item.zheXianX;
        });
        const CrosYNums = zheXianListCrossDept.map(item => {
          return item.zheXianY;
        });
        // 机密文件下载数据
        const SecrXTimes = zheXianListSecretFile.map(item => {
          return item.zheXianX;
        });
        const SecrYNums = zheXianListSecretFile.map(item => {
          return item.zheXianY;
        });
        callback(
          AllXTimes,
          AllYNums,
          CrosXTimes,
          CrosYNums,
          SecrXTimes,
          SecrYNums
        );
      } catch (e) {
        console.log(e);
      }
    },
    // 账户并发数量折线图数据
    async getInfoListDay2(callback) {
      const value = {
        zheXianFlag: this.zheXianFlag,
        zheXianDate: this.zheXianDates
      };
      try {
        const { data } = await getDownZheXian(value);
        const {
          zheXianListAll,
          zheXianListOverload,
          zheXianListCrossDept
        } = data;
        // 所有数据
        const downAllXTimes = zheXianListAll.map(item => {
          return item.zheXianX;
        });
        const downAllYNums = zheXianListAll.map(item => {
          return item.zheXianY;
        });
        // 跨部门数据
        const downCrosXTimes = zheXianListCrossDept.map(item => {
          return item.zheXianX;
        });
        const downCrosYNums = zheXianListCrossDept.map(item => {
          return item.zheXianY;
        });
        // 机密文件下载数据
        const downSecrXTimes = zheXianListOverload.map(item => {
          return item.zheXianX;
        });
        const downSecrYNums = zheXianListOverload.map(item => {
          return item.zheXianY;
        });
        callback(
          downAllXTimes,
          downAllYNums,
          downCrosXTimes,
          downCrosYNums,
          downSecrXTimes,
          downSecrYNums
        );
      } catch (e) {
        console.log(e);
      }
    },
    //  登录用户三个按钮
    login1() {
      this.zheXianFlag = 1;
      this.zheXianDate = this.zheXianDates;
      this.getInfoListDay(this.initItem);
    },
    login2() {
      this.zheXianFlag = 2;
      this.zheXianDate = this.zheXianDates;
      this.getInfoListDay(this.initItem);
    },
    login3() {
      this.zheXianFlag = 3;
      this.zheXianDate = this.zheXianDates;
      this.getInfoListDay(this.initItem);
    },
    // 账户并发三个按钮
    concurrent1() {
      this.zheXianFlag = 1;
      this.zheXianDate = this.zheXianDateDown;
      this.getInfoListDay2(this.initItem2);
    },
    concurrent2() {
      this.zheXianFlag = 2;
      this.zheXianDate = this.zheXianDateDown;
      this.getInfoListDay2(this.initItem2);
    },
    concurrent3() {
      this.zheXianFlag = 3;
      this.zheXianDate = this.zheXianDateDown;
      this.getInfoListDay2(this.initItem2);
    },
    handleClick1(tab, event) {},
    handleClick2(tab, event) {},
    handleClick3(tab, event) {
      if (tab.label === "异常") {
        console.log("异常")
      }
      if (tab.label === "正常") {
        console.log("正常")
        this.recentlyList({vvFlag:this.vvFlag})
      }
      if (tab.label === "全部") {
        console.log("全部")
      }
      console.log(tab, event)
    },
    // 用户提示时间
    Data() {
      this.nowData = new Date();
      const d = this.nowData;
      let month =
        d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
      let day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
      let times = d.getFullYear() + "-" + month + "-" + day;
      this.zheXianDate = times;
      this.zheXianDates = times;
      this.zheXianDateDown = times;
    },
    // 返回按钮
    black() {
      this.$router.push("/users/overview");
    },
    // 当前时间格式化
    timeFormate(timeStamp) {
      let year = new Date(timeStamp).getFullYear();
      let month =
        new Date(timeStamp).getMonth() + 1 < 10
          ? "0" + (new Date(timeStamp).getMonth() + 1)
          : new Date(timeStamp).getMonth() + 1;
      let date =
        new Date(timeStamp).getDate() < 10
          ? "0" + new Date(timeStamp).getDate()
          : new Date(timeStamp).getDate();
      let hh =
        new Date(timeStamp).getHours() < 10
          ? "0" + new Date(timeStamp).getHours()
          : new Date(timeStamp).getHours();
      let mm =
        new Date(timeStamp).getMinutes() < 10
          ? "0" + new Date(timeStamp).getMinutes()
          : new Date(timeStamp).getMinutes();
      let ss =
        new Date(timeStamp).getSeconds() < 10
          ? "0" + new Date(timeStamp).getSeconds()
          : new Date(timeStamp).getSeconds();
      // return year + "年" + month + "月" + date +"日"+" "+hh+":"+mm ;
      this.date =
        year +
        "-" +
        month +
        "-" +
        date +
        // '-' +
        " " +
        hh +
        ":" +
        mm +
        ":" +
        ss;
    },
    // 定时器函数
    nowTTimes() {
      this.timeFormate(new Date());
      this.timer = setTimeout(this.nowTTimes, 1 * 1000);
    },
    // 销毁定时器
    beforeDestroy: function() {
      if (this.timer) {
        clearTimeout(this.timer); // 在Vue实例销毁前，清除我们的定时器
      }
    },
    // card1登录用户数量柱状图
    initItem(
      AllXTimes,
      AllYNums,
      CrosXTimes,
      CrosYNums,
      SecrXTimes,
      SecrYNums
    ) {
      let myChart1 = this.$echarts.init(document.getElementById("main1"));
      let myChart2 = this.$echarts.init(document.getElementById("main2"));
      let myChart3 = this.$echarts.init(document.getElementById("main3"));

      this.initHeight1 = document.getElementById("card1").offsetHeight;
      this.initWidth1 = document.getElementById("card1").offsetWidth;

      const option1 = {
        backgroundColor: "#040f3c",
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: "2%",
          right: "4%",
          bottom: "14%",
          top: "16%",
          containLabel: true
        },
        legend: {
          data: ["北京公司", "深圳公司", "上海公司"],
          right: 10,
          top: 12,
          textStyle: {
            color: "#fff"
          },
          itemWidth: 12,
          itemHeight: 10
          // itemGap: 35
        },
        xAxis: {
          type: "category",
          data: CrosXTimes,
          axisLine: {
            lineStyle: {
              color: "white"
            }
          },
          axisLabel: {
            // interval: 0,
            // rotate: 40,
            textStyle: {
              fontFamily: "Microsoft YaHei"
            }
          }
        },

        yAxis: {
          type: "value",
          axisLine: {
            show: false,
            lineStyle: {
              color: "white"
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "rgba(255,255,255,0.3)"
            }
          },
          axisLabel: {}
        },
        dataZoom: [
          {
            show: true,
            height: 12,
            xAxisIndex: [0],
            bottom: "8%",
            start: 10,
            end: 90,
            handleIcon:
              "path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z",
            handleSize: "110%",
            handleStyle: {
              color: "#d3dee5"
            },
            textStyle: {
              color: "#fff"
            },
            borderColor: "#90979c"
          },
          {
            type: "inside",
            show: true,
            height: 15,
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: "北京公司",
            type: "bar",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#fccb05"
                  },
                  {
                    offset: 1,
                    color: "#f5804d"
                  }
                ]),
                barBorderRadius: 12
              }
            },
            data: CrosYNums
          },
          {
            name: "深圳公司",
            type: "bar",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#8bd46e"
                  },
                  {
                    offset: 1,
                    color: "#09bcb7"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: SecrYNums
          },
          {
            name: "上海公司",
            type: "bar",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#248ff7"
                  },
                  {
                    offset: 1,
                    color: "#6851f1"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: AllYNums
          }
        ]
      };
      const option2 = {
        backgroundColor: "#040f3c",
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: "2%",
          right: "4%",
          bottom: "14%",
          top: "16%",
          containLabel: true
        },
        legend: {
          data: ["北京公司", "深圳公司", "上海公司"],
          right: 10,
          top: 12,
          textStyle: {
            color: "#fff"
          },
          itemWidth: 12,
          itemHeight: 10
          // itemGap: 35
        },
        xAxis: {
          type: "category",
          data: CrosXTimes,
          axisLine: {
            lineStyle: {
              color: "white"
            }
          },
          axisLabel: {
            // interval: 0,
            // rotate: 40,
            textStyle: {
              fontFamily: "Microsoft YaHei"
            }
          }
        },

        yAxis: {
          type: "value",
          axisLine: {
            show: false,
            lineStyle: {
              color: "white"
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "rgba(255,255,255,0.3)"
            }
          },
          axisLabel: {}
        },
        dataZoom: [
          {
            show: true,
            height: 12,
            xAxisIndex: [0],
            bottom: "8%",
            start: 10,
            end: 90,
            handleIcon:
              "path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z",
            handleSize: "110%",
            handleStyle: {
              color: "#d3dee5"
            },
            textStyle: {
              color: "#fff"
            },
            borderColor: "#90979c"
          },
          {
            type: "inside",
            show: true,
            height: 15,
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: "北京公司",
            type: "bar",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#fccb05"
                  },
                  {
                    offset: 1,
                    color: "#f5804d"
                  }
                ]),
                barBorderRadius: 12
              }
            },
            data: CrosYNums
          },
          {
            name: "深圳公司",
            type: "bar",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#8bd46e"
                  },
                  {
                    offset: 1,
                    color: "#09bcb7"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: SecrYNums
          },
          {
            name: "上海公司",
            type: "bar",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#248ff7"
                  },
                  {
                    offset: 1,
                    color: "#6851f1"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: AllYNums
          }
        ]
      };
      const option3 = {
        backgroundColor: "#040f3c",
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: "2%",
          right: "4%",
          bottom: "14%",
          top: "16%",
          containLabel: true
        },
        legend: {
          data: ["北京公司", "深圳公司", "上海公司"],
          right: 10,
          top: 12,
          textStyle: {
            color: "#fff"
          },
          itemWidth: 12,
          itemHeight: 10
          // itemGap: 35
        },
        xAxis: {
          type: "category",
          data: CrosXTimes,
          axisLine: {
            lineStyle: {
              color: "white"
            }
          },
          axisLabel: {
            // interval: 0,
            // rotate: 40,
            textStyle: {
              fontFamily: "Microsoft YaHei"
            }
          }
        },

        yAxis: {
          type: "value",
          axisLine: {
            show: false,
            lineStyle: {
              color: "white"
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "rgba(255,255,255,0.3)"
            }
          },
          axisLabel: {}
        },
        dataZoom: [
          {
            show: true,
            height: 12,
            xAxisIndex: [0],
            bottom: "8%",
            start: 10,
            end: 90,
            handleIcon:
              "path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z",
            handleSize: "110%",
            handleStyle: {
              color: "#d3dee5"
            },
            textStyle: {
              color: "#fff"
            },
            borderColor: "#90979c"
          },
          {
            type: "inside",
            show: true,
            height: 15,
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: "北京公司",
            type: "bar",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#fccb05"
                  },
                  {
                    offset: 1,
                    color: "#f5804d"
                  }
                ]),
                barBorderRadius: 12
              }
            },
            data: CrosYNums
          },
          {
            name: "深圳公司",
            type: "bar",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#8bd46e"
                  },
                  {
                    offset: 1,
                    color: "#09bcb7"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: SecrYNums
          },
          {
            name: "上海公司",
            type: "bar",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#248ff7"
                  },
                  {
                    offset: 1,
                    color: "#6851f1"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: AllYNums
          }
        ]
      };
      myChart1.setOption(option1);
      myChart2.setOption(option2);
      myChart3.setOption(option3);
      let resize1 = {
        height: this.initHeight1,
        width: this.initWidth1
      };

      myChart1.resize(resize1);
      myChart2.resize(resize1);
      myChart3.resize(resize1);
    },
    // card2账户并发数量折线图
    initItem2(
      downAllXTimes,
      downAllYNums,
      downCrosXTimes,
      downCrosYNums,
      downSecrXTimes,
      downSecrYNums
    ) {
      let myChart4 = this.$echarts.init(document.getElementById("main4"));
      let myChart5 = this.$echarts.init(document.getElementById("main5"));
      let myChart6 = this.$echarts.init(document.getElementById("main6"));
      this.initHeight2 = document.getElementById("card2").offsetHeight;
      this.initWidth2 = document.getElementById("card2").offsetWidth;
      const option4 = {
        backgroundColor: "#040f3c",
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: "2%",
          right: "4%",
          bottom: "14%",
          top: "16%",
          containLabel: true
        },
        legend: {
          data: ["北京", "深圳", "上海"],
          right: 10,
          top: 12,
          textStyle: {
            color: "#fff"
          },
          itemWidth: 12,
          itemHeight: 10
          // itemGap: 35
        },
        xAxis: {
          type: "category",
          data: downCrosXTimes,
          axisLine: {
            lineStyle: {
              color: "white"
            }
          },
          axisLabel: {
            // interval: 0,
            // rotate: 40,
            textStyle: {
              fontFamily: "Microsoft YaHei"
            }
          }
        },

        yAxis: {
          type: "value",
          // max: "10",
          axisLine: {
            show: false,
            lineStyle: {
              color: "white"
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "rgba(255,255,255,0.3)"
            }
          },
          axisLabel: {}
        },
        dataZoom: [
          {
            show: true,
            height: 12,
            xAxisIndex: [0],
            bottom: "8%",
            start: 10,
            end: 90,
            handleIcon:
              "path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z",
            handleSize: "110%",
            handleStyle: {
              color: "#d3dee5"
            },
            textStyle: {
              color: "#fff"
            },
            borderColor: "#90979c"
          },
          {
            type: "inside",
            show: true,
            height: 15,
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: "北京",
            type: "line",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#fccb05"
                  },
                  {
                    offset: 1,
                    color: "#f5804d"
                  }
                ]),
                barBorderRadius: 12
              }
            },
            areaStyle: {},
            data: downCrosYNums
          },
          {
            name: "深圳",
            type: "line",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#8bd46e"
                  },
                  {
                    offset: 1,
                    color: "#09bcb7"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            areaStyle: {},
            data: downSecrYNums
          },
          {
            name: "上海",
            type: "line",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#248ff7"
                  },
                  {
                    offset: 1,
                    color: "#6851f1"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            areaStyle: {},
            data: downAllYNums
          }
        ]
      };
      const option5 = {
        backgroundColor: "#040f3c",
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: "2%",
          right: "4%",
          bottom: "14%",
          top: "16%",
          containLabel: true
        },
        legend: {
          data: ["北京", "深圳", "上海"],
          right: 10,
          top: 12,
          textStyle: {
            color: "#fff"
          },
          itemWidth: 12,
          itemHeight: 10
          // itemGap: 35
        },
        xAxis: {
          type: "category",
          data: downCrosXTimes,
          axisLine: {
            lineStyle: {
              color: "white"
            }
          },
          axisLabel: {
            // interval: 0,
            // rotate: 40,
            textStyle: {
              fontFamily: "Microsoft YaHei"
            }
          }
        },

        yAxis: {
          type: "value",
          axisLine: {
            show: false,
            lineStyle: {
              color: "white"
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "rgba(255,255,255,0.3)"
            }
          },
          axisLabel: {}
        },
        dataZoom: [
          {
            show: true,
            height: 12,
            xAxisIndex: [0],
            bottom: "8%",
            start: 10,
            end: 90,
            handleIcon:
              "path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z",
            handleSize: "110%",
            handleStyle: {
              color: "#d3dee5"
            },
            textStyle: {
              color: "#fff"
            },
            borderColor: "#90979c"
          },
          {
            type: "inside",
            show: true,
            height: 15,
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: "北京",
            type: "line",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#fccb05"
                  },
                  {
                    offset: 1,
                    color: "#f5804d"
                  }
                ]),
                barBorderRadius: 12
              }
            },
            areaStyle: {},
            data: downCrosYNums
          },
          {
            name: "深圳",
            type: "line",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#8bd46e"
                  },
                  {
                    offset: 1,
                    color: "#09bcb7"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: downSecrYNums
          },
          {
            name: "上海",
            type: "line",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#248ff7"
                  },
                  {
                    offset: 1,
                    color: "#6851f1"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            areaStyle: {},
            data: downAllYNums
          }
        ]
      };
      const option6 = {
        backgroundColor: "#040f3c",
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
          }
        },
        grid: {
          left: "2%",
          right: "4%",
          bottom: "14%",
          top: "16%",
          containLabel: true
        },
        legend: {
          data: ["北京", "深圳", "上海"],
          right: 10,
          top: 12,
          textStyle: {
            color: "#fff"
          },
          itemWidth: 12,
          itemHeight: 10
          // itemGap: 35
        },
        xAxis: {
          type: "category",
          data: downCrosXTimes,
          axisLine: {
            lineStyle: {
              color: "white"
            }
          },
          axisLabel: {
            // interval: 0,
            // rotate: 40,
            textStyle: {
              fontFamily: "Microsoft YaHei"
            }
          }
        },

        yAxis: {
          type: "value",
          axisLine: {
            show: false,
            lineStyle: {
              color: "white"
            }
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "rgba(255,255,255,0.3)"
            }
          },
          axisLabel: {}
        },
        dataZoom: [
          {
            show: true,
            height: 12,
            xAxisIndex: [0],
            bottom: "8%",
            start: 10,
            end: 90,
            handleIcon:
              "path://M306.1,413c0,2.2-1.8,4-4,4h-59.8c-2.2,0-4-1.8-4-4V200.8c0-2.2,1.8-4,4-4h59.8c2.2,0,4,1.8,4,4V413z",
            handleSize: "110%",
            handleStyle: {
              color: "#d3dee5"
            },
            textStyle: {
              color: "#fff"
            }
          },
          {
            type: "inside",
            show: true,
            height: 15,
            start: 1,
            end: 35
          }
        ],
        series: [
          {
            name: "北京",
            type: "line",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#fccb05"
                  },
                  {
                    offset: 1,
                    color: "#f5804d"
                  }
                ]),
                barBorderRadius: 12
              }
            },
            areaStyle: {},
            data: downCrosYNums
          },
          {
            name: "深圳",
            type: "line",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#8bd46e"
                  },
                  {
                    offset: 1,
                    color: "#09bcb7"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            areaStyle: {},
            data: downSecrYNums
          },
          {
            name: "上海",
            type: "line",
            barWidth: "10%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#248ff7"
                  },
                  {
                    offset: 1,
                    color: "#6851f1"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            areaStyle: {},
            data: downAllYNums
          }
        ]
      };
      myChart4.setOption(option4);
      myChart5.setOption(option5);
      myChart6.setOption(option6);
      let resize2 = {
        height: this.initHeight2,
        width: this.initWidth2
      };
      myChart4.resize(resize2);
      myChart5.resize(resize2);
      myChart6.resize(resize2);
    }
  },
  components: {
    // 'dashboard-one': loginUserNum
    // 'dashboard-two': loginUserNumDay
  },
  mounted() {
    this.getDayClienCount();
    // TC服务器样式动态生成
    if (this.tc1_1 >= 90 && this.tc1_2 >= 90) {
      this.expression1 = true;
      this.expression2 = false;
      this.expression3 = false;
    } else if (this.tc1_1 >= 70 && this.tc1_2 >= 70) {
      this.expression1 = false;
      this.expression2 = true;
      this.expression3 = false;
    } else {
      this.expression1 = false;
      this.expression2 = false;
      this.expression3 = true;
    }
    // 登录用户数量柱状图回调函数
    this.getInfoListDay(this.initItem);
    // 账户并发数量折线图回调函数
    this.getInfoListDay2(this.initItem2);
    // setInterval(() => {
    //   this.getShi()
    // }, 600)
    // 基于准备好的dom，初始化echarts实例
    let myChart8_1 = this.$echarts.init(document.getElementById("main8_1"));
    let myChart8_2 = this.$echarts.init(document.getElementById("main8_2"));
    let myChart8_3 = this.$echarts.init(document.getElementById("main8_3"));
    let myChart8_4 = this.$echarts.init(document.getElementById("main8_4"));

    let myChart9 = this.$echarts.init(document.getElementById("main9"));
    let myChart10 = this.$echarts.init(document.getElementById("main10"));
    let myChart11 = this.$echarts.init(document.getElementById("main11"));

    let myChart9_2 = this.$echarts.init(document.getElementById("main9_2"));
    let myChart10_2 = this.$echarts.init(document.getElementById("main10_2"));
    let myChart11_2 = this.$echarts.init(document.getElementById("main11_2"));

    let myChart9_3 = this.$echarts.init(document.getElementById("main9_3"));
    let myChart10_3 = this.$echarts.init(document.getElementById("main10_3"));
    let myChart11_3 = this.$echarts.init(document.getElementById("main11_3"));

    let myChart9_4 = this.$echarts.init(document.getElementById("main9_4"));
    let myChart10_4 = this.$echarts.init(document.getElementById("main10_4"));
    let myChart11_4 = this.$echarts.init(document.getElementById("main11_4"));

    this.initHeight7 = document.getElementById("card7").offsetHeight;
    this.initWidth7 = document.getElementById("card7").offsetWidth;

    // 指定图表的配置项和数据
    // 地图显示数据
    this.myMapChart();
    const option8_1 = {
      series: [
        {
          hoverAnimation: false,
          animation: false,
          color: ["#f78c44", "#2e507a"],

          type: "pie",
          radius: ["80%", "100%"],
          tooltip: {
            show: false
          },
          label: {
            normal: {
              show: true,
              position: "center"
            },
            emphasis: {
              show: true,
              textStyle: {
                fontSize: "12",
                fontWeight: "bold"
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
              name: "25%",
              label: {
                normal: {
                  textStyle: {
                    fontSize: "14",
                    fontWeight: "bold"
                  }
                }
              }
            },
            {
              value: 75,

              label: {
                normal: {
                  textStyle: {
                    fontSize: "12",
                    color: "#999",
                    fontWeight: "bold"
                  },
                  padding: [150, 0, 0, 0]
                }
              }
            }
          ]
        }
      ]
    };
    const option8_2 = {
      series: [
        {
          color: ["#f78c44", "#2e507a"],

          type: "pie",
          radius: ["80%", "100%"],
          avoidLabelOverlap: false,
          label: {
            normal: {
              show: true,
              position: "center"
            },
            emphasis: {
              show: true,
              textStyle: {
                fontSize: "12",
                fontWeight: "bold"
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
              name: "25%",
              label: {
                normal: {
                  textStyle: {
                    fontSize: "14",
                    fontWeight: "bold"
                  }
                }
              }
            },
            {
              value: 75,

              label: {
                normal: {
                  textStyle: {
                    fontSize: "12",
                    color: "#999",
                    fontWeight: "bold"
                  },
                  padding: [150, 0, 0, 0]
                }
              }
            }
          ]
        }
      ]
    };
    const option8_3 = {
      series: [
        {
          color: ["#f78c44", "#2e507a"],

          type: "pie",
          radius: ["80%", "100%"],
          avoidLabelOverlap: false,
          label: {
            normal: {
              show: true,
              position: "center"
            },
            emphasis: {
              show: true,
              textStyle: {
                fontSize: "12",
                fontWeight: "bold"
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
              name: "25%",
              label: {
                normal: {
                  textStyle: {
                    fontSize: "14",
                    fontWeight: "bold"
                  }
                }
              }
            },
            {
              value: 75,

              label: {
                normal: {
                  textStyle: {
                    fontSize: "12",
                    color: "#999",
                    fontWeight: "bold"
                  },
                  padding: [150, 0, 0, 0]
                }
              }
            }
          ]
        }
      ]
    };
    const option8_4 = {
      series: [
        {
          color: ["#f78c44", "#2e507a"],

          type: "pie",
          radius: ["80%", "100%"],
          avoidLabelOverlap: false,
          label: {
            normal: {
              show: true,
              position: "center"
            },
            emphasis: {
              show: true,
              textStyle: {
                fontSize: "12",
                fontWeight: "bold"
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
              name: "25%",
              label: {
                normal: {
                  textStyle: {
                    fontSize: "14",
                    fontWeight: "bold"
                  }
                }
              }
            },
            {
              value: 75,

              label: {
                normal: {
                  textStyle: {
                    fontSize: "12",
                    color: "#999",
                    fontWeight: "bold"
                  },
                  padding: [150, 0, 0, 0]
                }
              }
            }
          ]
        }
      ]
    };
    var fontColor = "#30eee9";
    const option9 = {
      // backgroundColor: '#11183c',
      grid: {
        left: "5%",
        right: "10%",
        top: "20%",
        bottom: "15%",
        containLabel: true
      },

      legend: {
        show: true,
        x: "center",
        y: "35",
        top: "10",
        icon: "stack",
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: "#1bb4f6"
        },
        data: ["已采纳", "已发布", "今日事件数量"]
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#397cbc"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#195384"
            }
          },
          data: ["", "", "", "", "", "", "", "", "", "", "", ""]
        }
      ],
      yAxis: [
        {
          type: "value",

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: "{value}",
            textStyle: {
              color: "#2ad1d2"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#27b4c2"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#11366e"
            }
          }
        }
      ],
      series: [
        {
          name: "今日事件数量",
          type: "line",
          stack: "总量",
          symbol: "circle",
          symbolSize: 8,
          color: "#aecb56",
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    };
    const option9_2 = {
      // backgroundColor: '#11183c',
      grid: {
        left: "5%",
        right: "10%",
        top: "20%",
        bottom: "15%",
        containLabel: true
      },

      legend: {
        show: true,
        x: "center",
        y: "35",
        top: "10",
        icon: "stack",
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: "#1bb4f6"
        },
        data: ["已采纳", "已发布", "今日事件数量"]
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#397cbc"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#195384"
            }
          },
          data: ["", "", "", "", "", "", "", "", "", "", "", ""]
        }
      ],
      yAxis: [
        {
          type: "value",

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: "{value}",
            textStyle: {
              color: "#2ad1d2"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#27b4c2"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#11366e"
            }
          }
        }
      ],
      series: [
        {
          name: "今日事件数量",
          type: "line",
          stack: "总量",
          symbol: "circle",
          symbolSize: 8,
          color: "#aecb56",
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    };
    const option9_3 = {
      // backgroundColor: '#11183c',
      grid: {
        left: "5%",
        right: "10%",
        top: "20%",
        bottom: "15%",
        containLabel: true
      },

      legend: {
        show: true,
        x: "center",
        y: "35",
        top: "10",
        icon: "stack",
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: "#1bb4f6"
        },
        data: ["已采纳", "已发布", "今日事件数量"]
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#397cbc"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#195384"
            }
          },
          data: ["", "", "", "", "", "", "", "", "", "", "", ""]
        }
      ],
      yAxis: [
        {
          type: "value",

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: "{value}",
            textStyle: {
              color: "#2ad1d2"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#27b4c2"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#11366e"
            }
          }
        }
      ],
      series: [
        {
          name: "今日事件数量",
          type: "line",
          stack: "总量",
          symbol: "circle",
          symbolSize: 8,
          color: "#aecb56",
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    };
    const option9_4 = {
      // backgroundColor: '#11183c',
      grid: {
        left: "5%",
        right: "10%",
        top: "20%",
        bottom: "15%",
        containLabel: true
      },

      legend: {
        show: true,
        x: "center",
        y: "35",
        top: "10",
        icon: "stack",
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: "#1bb4f6"
        },
        data: ["已采纳", "已发布", "今日事件数量"]
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#397cbc"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#195384"
            }
          },
          data: ["", "", "", "", "", "", "", "", "", "", "", ""]
        }
      ],
      yAxis: [
        {
          type: "value",

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: "{value}",
            textStyle: {
              color: "#2ad1d2"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#27b4c2"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#11366e"
            }
          }
        }
      ],
      series: [
        {
          name: "今日事件数量",
          type: "line",
          stack: "总量",
          symbol: "circle",
          symbolSize: 8,
          color: "#aecb56",
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    };
    const option10 = {
      // backgroundColor: '#11183c',
      grid: {
        left: "5%",
        right: "10%",
        top: "20%",
        bottom: "15%",
        containLabel: true
      },

      legend: {
        show: true,
        x: "center",
        y: "35",
        top: "10",
        icon: "stack",
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: "#1bb4f6"
        },
        data: ["已采纳", "已发布", "本周事件数量"]
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#397cbc"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#195384"
            }
          },
          data: ["", "", "", "", "", "", "", "", "", "", "", ""]
        }
      ],
      yAxis: [
        {
          type: "value",

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: "{value}",
            textStyle: {
              color: "#2ad1d2"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#27b4c2"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#11366e"
            }
          }
        }
      ],
      series: [
        {
          name: "本周事件数量",
          type: "line",
          stack: "总量",
          symbol: "circle",
          symbolSize: 8,
          color: "#aecb56",
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    };
    const option10_2 = {
      // backgroundColor: '#11183c',
      grid: {
        left: "5%",
        right: "10%",
        top: "20%",
        bottom: "15%",
        containLabel: true
      },

      legend: {
        show: true,
        x: "center",
        y: "35",
        top: "10",
        icon: "stack",
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: "#1bb4f6"
        },
        data: ["已采纳", "已发布", "本周事件数量"]
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#397cbc"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#195384"
            }
          },
          data: ["", "", "", "", "", "", "", "", "", "", "", ""]
        }
      ],
      yAxis: [
        {
          type: "value",

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: "{value}",
            textStyle: {
              color: "#2ad1d2"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#27b4c2"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#11366e"
            }
          }
        }
      ],
      series: [
        {
          name: "本周事件数量",
          type: "line",
          stack: "总量",
          symbol: "circle",
          symbolSize: 8,
          color: "#aecb56",
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    };
    const option10_3 = {
      // backgroundColor: '#11183c',
      grid: {
        left: "5%",
        right: "10%",
        top: "20%",
        bottom: "15%",
        containLabel: true
      },

      legend: {
        show: true,
        x: "center",
        y: "35",
        top: "10",
        icon: "stack",
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: "#1bb4f6"
        },
        data: ["已采纳", "已发布", "本周事件数量"]
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#397cbc"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#195384"
            }
          },
          data: ["", "", "", "", "", "", "", "", "", "", "", ""]
        }
      ],
      yAxis: [
        {
          type: "value",

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: "{value}",
            textStyle: {
              color: "#2ad1d2"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#27b4c2"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#11366e"
            }
          }
        }
      ],
      series: [
        {
          name: "本周事件数量",
          type: "line",
          stack: "总量",
          symbol: "circle",
          symbolSize: 8,
          color: "#aecb56",
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    };
    const option10_4 = {
      // backgroundColor: '#11183c',
      grid: {
        left: "5%",
        right: "10%",
        top: "20%",
        bottom: "15%",
        containLabel: true
      },

      legend: {
        show: true,
        x: "center",
        y: "35",
        top: "10",
        icon: "stack",
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: "#1bb4f6"
        },
        data: ["已采纳", "已发布", "本周事件数量"]
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#397cbc"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#195384"
            }
          },
          data: ["", "", "", "", "", "", "", "", "", "", "", ""]
        }
      ],
      yAxis: [
        {
          type: "value",

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: "{value}",
            textStyle: {
              color: "#2ad1d2"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#27b4c2"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#11366e"
            }
          }
        }
      ],
      series: [
        {
          name: "本周事件数量",
          type: "line",
          stack: "总量",
          symbol: "circle",
          symbolSize: 8,
          color: "#aecb56",
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    };
    const option11 = {
      // backgroundColor: '#11183c',
      grid: {
        left: "5%",
        right: "10%",
        top: "20%",
        bottom: "15%",
        containLabel: true
      },

      legend: {
        show: true,
        x: "center",
        y: "35",
        top: "10",
        icon: "stack",
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: "#1bb4f6"
        },
        data: ["已采纳", "已发布", "本月事件数量"]
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#397cbc"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#195384"
            }
          },
          data: ["", "", "", "", "", "", "", "", "", "", "", ""]
        }
      ],
      yAxis: [
        {
          type: "value",

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: "{value}",
            textStyle: {
              color: "#2ad1d2"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#27b4c2"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#11366e"
            }
          }
        }
      ],
      series: [
        {
          name: "本月事件数量",
          type: "line",
          stack: "总量",
          symbol: "circle",
          symbolSize: 8,
          color: "#aecb56",
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    };
    const option11_2 = {
      // backgroundColor: '#11183c',
      grid: {
        left: "5%",
        right: "10%",
        top: "20%",
        bottom: "15%",
        containLabel: true
      },

      legend: {
        show: true,
        x: "center",
        y: "35",
        top: "10",
        icon: "stack",
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: "#1bb4f6"
        },
        data: ["已采纳", "已发布", "本月事件数量"]
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#397cbc"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#195384"
            }
          },
          data: ["", "", "", "", "", "", "", "", "", "", "", ""]
        }
      ],
      yAxis: [
        {
          type: "value",

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: "{value}",
            textStyle: {
              color: "#2ad1d2"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#27b4c2"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#11366e"
            }
          }
        }
      ],
      series: [
        {
          name: "本月事件数量",
          type: "line",
          stack: "总量",
          symbol: "circle",
          symbolSize: 8,
          color: "#aecb56",
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    };
    const option11_3 = {
      // backgroundColor: '#11183c',
      grid: {
        left: "5%",
        right: "10%",
        top: "20%",
        bottom: "15%",
        containLabel: true
      },

      legend: {
        show: true,
        x: "center",
        y: "35",
        top: "10",
        icon: "stack",
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: "#1bb4f6"
        },
        data: ["已采纳", "已发布", "本月事件数量"]
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#397cbc"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#195384"
            }
          },
          data: ["", "", "", "", "", "", "", "", "", "", "", ""]
        }
      ],
      yAxis: [
        {
          type: "value",

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: "{value}",
            textStyle: {
              color: "#2ad1d2"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#27b4c2"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#11366e"
            }
          }
        }
      ],
      series: [
        {
          name: "本月事件数量",
          type: "line",
          stack: "总量",
          symbol: "circle",
          symbolSize: 8,
          color: "#aecb56",
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    };
    const option11_4 = {
      // backgroundColor: '#11183c',
      grid: {
        left: "5%",
        right: "10%",
        top: "20%",
        bottom: "15%",
        containLabel: true
      },

      legend: {
        show: true,
        x: "center",
        y: "35",
        top: "10",
        icon: "stack",
        itemWidth: 10,
        itemHeight: 10,
        textStyle: {
          color: "#1bb4f6"
        },
        data: ["已采纳", "已发布", "本月事件数量"]
      },
      xAxis: [
        {
          type: "category",
          boundaryGap: false,
          axisLabel: {
            color: fontColor
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: "#397cbc"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#195384"
            }
          },
          data: ["", "", "", "", "", "", "", "", "", "", "", ""]
        }
      ],
      yAxis: [
        {
          type: "value",

          min: 0,
          max: 1000,
          axisLabel: {
            formatter: "{value}",
            textStyle: {
              color: "#2ad1d2"
            }
          },
          axisLine: {
            lineStyle: {
              color: "#27b4c2"
            }
          },
          axisTick: {
            show: false
          },
          splitLine: {
            show: true,
            lineStyle: {
              color: "#11366e"
            }
          }
        }
      ],
      series: [
        {
          name: "本月事件数量",
          type: "line",
          stack: "总量",
          symbol: "circle",
          symbolSize: 8,
          color: "#aecb56",
          bottom: 20,
          data: [150, 232, 201, 154, 190, 330, 410, 150, 232, 201, 154, 190]
        }
      ]
    };
    // 使用刚指定的配置项和数据显示图表。

    myChart8_1.setOption(option8_1);
    myChart8_2.setOption(option8_2);
    myChart8_3.setOption(option8_3);
    myChart8_4.setOption(option8_4);

    myChart9.setOption(option9);
    myChart10.setOption(option10);
    myChart11.setOption(option11);

    myChart9_2.setOption(option9_2);
    myChart10_2.setOption(option10_2);
    myChart11_2.setOption(option11_2);

    myChart9_3.setOption(option9_3);
    myChart10_3.setOption(option10_3);
    myChart11_3.setOption(option11_3);

    myChart9_4.setOption(option9_4);
    myChart10_4.setOption(option10_4);
    myChart11_4.setOption(option11_4);
  },
  // 计算属性
  computed: {}
};
</script>

<style
  src="@/css/clientSideMonitor/behaviorAnalysis/productDataAnalysis/bootstrap.css"
></style>
<style
  src="@/css/clientSideMonitor/behaviorAnalysis/productDataAnalysis/dash5.css"
></style>
