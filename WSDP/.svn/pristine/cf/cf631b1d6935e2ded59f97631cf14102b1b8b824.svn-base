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
            <p class="usNum">{{ onlinePeopleCount }}</p>
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
                    <div id="main1" style="width: 100%; height: 240px; "></div>
                  </el-tab-pane>

                  <el-tab-pane label="本周" name="second1">
                    <span slot="label" @click="login2">本周</span>
                    <div id="main2" style="width: 100%; height: 240px; "></div>
                  </el-tab-pane>

                  <el-tab-pane label="本月" name="third1">
                    <span slot="label" @click="login3">本月</span>
                    <div id="main3" style="width: 100%; height: 240px; "></div>
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
                    <div id="main4" style="width: 100%; height: 240px; "></div>
                  </el-tab-pane>

                  <el-tab-pane label="本周" name="second2">
                    <span slot="label" @click="concurrent2">本周</span>
                    <div id="main5" style="width: 100%; height: 240px; "></div>
                  </el-tab-pane>

                  <el-tab-pane label="本月" name="third2">
                    <span slot="label" @click="concurrent3">本月</span>
                    <div id="main6" style="width: 100%; height: 240px; "></div>
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
            <p class="eventNum1 fl">{{safetyCounts}}</p>
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
                <div class="dayName">服务器当日操作频次</div>
                <div class="dayNum clearfix">
                  <p class="dayNum-1">1421</p>
                  <p class="dayNum-2">日环比：-2%</p>
                </div>
              </li>

              <li>
                <div class="dayName">客户端当日操作频次</div>
                <div class="dayNum clearfix">
                  <p class="dayNum-1">{{ clientTodayAllCounts }}</p>
                  <p class="dayNum-2">日环比：{{AllRatio}}</p>
                </div>
              </li>
              <li>
                <div class="dayName">异常操作频次</div>
                <div class="dayNum clearfix">
                  <p class="dayNum-1">{{ clientTodayAbnormalCounts }}</p>
                  <p class="dayNnmColor">日环比：{{AbnormalRatio}}</p>
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
            <!-- 广州公司轮播内容 -->
            <el-carousel-item>
              <!-- 头部公司名称 -->
              <div class="figure-title clearfix">
                <div class="point">
                  <div class="points"></div>
                </div>
                <div class="point-title" id="gzTextContent">{{gzName}}</div>
              </div>
              <!-- 公司下面的圆形图和折线图 -->
              <div class="comBox clearfix">
                <div class="com-right">
                  <div class="info-right" id="card9">
                    <ul class="clearfix">
                      <li>
                        <div id="sexrate">
                          <el-tabs v-model="gzactiveName4" type="card" @tab-click="gzComTab">
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
                    <div class="info-fl up-day-num">&nbsp;{{ gzOperaCount }}</div>
                    <div class="info-fl up-abnormal">异常事件</div>
                    <div class="info-fl up-abnormal-num">{{ gzAbnormalCount }}</div>
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
            <!-- 北京公司轮播内容 -->
            <el-carousel-item>
              <!-- 头部公司名称 -->
              <div class="figure-title clearfix">
                <div class="point">
                  <div class="points"></div>
                </div>
                <div class="point-title" id="bjTextContent">{{bjName}}</div>
              </div>
              <!-- 公司下面的圆形图和折线图 -->
              <div class="comBox clearfix">
                <div class="com-right">
                  <div class="info-right" id="card9">
                    <ul class="clearfix">
                      <li>
                        <div id="sexrate">
                          <el-tabs v-model="bjactiveName4" type="card" @tab-click="bjComTab">
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
                    <div class="info-fl up-day-num">&nbsp;{{ bjOperaCount }}</div>
                    <div class="info-fl up-abnormal">异常事件</div>
                    <div class="info-fl up-abnormal-num">{{ bjAbnormalCount }}</div>
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
            <!-- 上海公司轮播内容 -->
            <el-carousel-item>
              <!-- 头部公司名称 -->
              <div class="figure-title clearfix">
                <div class="point">
                  <div class="points"></div>
                </div>
                <div class="point-title" id="shTextContent">{{shName}}</div>
              </div>
              <!-- 公司下面的圆形图和折线图 -->
              <div class="comBox clearfix">
                <div class="com-right">
                  <div class="info-right" id="card9">
                    <ul class="clearfix">
                      <li>
                        <div id="sexrate">
                          <el-tabs v-model="shactiveName4" type="card" @tab-click="shComTab">
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
                    <div class="info-fl up-day-num">&nbsp;{{ shOperaCount }}</div>
                    <div class="info-fl up-abnormal">异常事件</div>
                    <div class="info-fl up-abnormal-num">{{ shAbnormalCount }}</div>
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
            <!-- 成都公司轮播内容 -->
            <el-carousel-item>
              <!-- 头部公司名称 -->
              <div class="figure-title clearfix">
                <div class="point">
                  <div class="points"></div>
                </div>
                <div class="point-title" id="cdTextContent">{{cdName}}</div>
              </div>
              <!-- 公司下面的圆形图和折线图 -->
              <div class="comBox clearfix">
                <div class="com-right">
                  <div class="info-right" id="card9">
                    <ul class="clearfix">
                      <li>
                        <div id="sexrate">
                          <el-tabs v-model="cdactiveName4" type="card" @tab-click="cdComTab">
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
                    <div class="info-fl up-day-num">&nbsp;{{ cdOperaCount }}</div>
                    <div class="info-fl up-abnormal">异常事件</div>
                    <div class="info-fl up-abnormal-num">{{ cdAbnormalCount }}</div>
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
                    <el-table
                      :data="tableData.slice((pagesNum - 1) * pageSize,pagesNum * pageSize)"
                      :show-header="false"
                      style="width: 100%"
                    >
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
                          <div v-else-if="row.recentInfo === '跨部门登录'" class="state"></div>
                          <div v-else-if="row.recentInfo === '跨部门登录,'" class="state"></div>
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
                      <el-table-column
                        :show-overflow-tooltip="true"
                        prop="recentInfo"
                        width="100px"
                      ></el-table-column>
                      <!-- 公司  -->
                      <el-table-column prop="company" width="125px"></el-table-column>
                      <!-- 用户名 -->
                      <el-table-column prop="employeeName" width="70px"></el-table-column>
                      <!-- 查看时间 -->
                      <el-table-column prop="actionBeginTime" width="200px"></el-table-column>
                    </el-table>
                  </el-tab-pane>

                  <el-tab-pane label="异常" name="second3">
                    <el-table
                      :data="tableData.slice((pagesNum - 1) * pageSize,pagesNum * pageSize)"
                      :show-header="false"
                      style="width: 100%"
                    >
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
                          <div v-else class="state"></div>
                        </template>
                      </el-table-column>
                      <!-- 文件查看 -->
                      <el-table-column
                        :show-overflow-tooltip="true"
                        prop="recentInfo"
                        width="100px"
                      ></el-table-column>
                      <!-- 公司  -->
                      <el-table-column prop="company" width="125px"></el-table-column>
                      <!-- 用户名 -->
                      <el-table-column prop="employeeName" width="70px"></el-table-column>
                      <!-- 查看时间 -->
                      <el-table-column prop="actionBeginTime" width="200px"></el-table-column>
                    </el-table>
                  </el-tab-pane>

                  <el-tab-pane label="正常" name="third3">
                    <el-table
                      :data="tableData.slice((pagesNum - 1) * pageSize,pagesNum * pageSize)"
                      :show-header="false"
                      style="width: 100%"
                    >
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
                      <el-table-column
                        :show-overflow-tooltip="true"
                        prop="recentInfo"
                        width="100px"
                      ></el-table-column>
                      <!-- 公司  -->
                      <el-table-column prop="company" width="125px"></el-table-column>
                      <!-- 用户名 -->
                      <el-table-column prop="employeeName" width="70px"></el-table-column>
                      <!-- 查看时间 -->
                      <el-table-column prop="actionBeginTime" width="200px"></el-table-column>
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
                      (pageDepNum - 1) * pageDepSize,
                      pageDepNum * pageDepSize
                    )
                  "
                  :show-header="false"
                  style="width: 100%"
                >
                  <el-table-column prop="operaCount" width="70" text-align="center">
                    <!-- 循环遍历 -->
                    <template slot-scope="{ row }">
                      <div
                        v-if="row.operaCount > 7"
                        style="background-color:#ed405d;width:40px;height:20px;color:white;border-radius:5px;line-height:20px;text-align:center"
                      >{{ row.operaCount }}</div>
                      <div
                        v-else-if="row.operaCount > 6"
                        style="background-color:#f78c44;width:40px;height:20px;color:white;border-radius:5px;line-height:20px;text-align:center"
                      >{{ row.operaCount }}</div>
                      <div
                        v-else
                        style="background-color:#878787;width:40px;height:20px;color:white;border-radius:5px;line-height:20px;text-align:center"
                      >{{ row.operaCount }}</div>
                    </template>
                  </el-table-column>
                  <el-table-column prop="employeeGroup"></el-table-column>
                  <el-table-column>
                    <!-- 循环遍历显示百分百数值 -->
                    <template slot-scope="{ row }">
                      <!-- 必填项percentage -->
                      <el-progress :percentage="row.recentInfo/1"></el-progress>
                    </template>
                  </el-table-column>
                </el-table>
              </div>
            </li>
          </ul>
          <el-pagination
            class="paging"
            @size-change="handleDepChange"
            @current-change="handleDepChange"
            :current-page="pageDepNum"
            :page-sizes="[25, 50, 100]"
            :page-size="pageDepSize"
            :total="userList.length"
            small
            layout="total,prev, pager, next"
          ></el-pagination>
        </div>
      </div>
    </div>
    <!--尾部-->
    <!-- item：循环
      index：tcList数组的下标
      :key 必填项值下标
      :class 动态添加类名
          'expression3'： 类名
          true ： 条件
    -->
    <vue-seamless-scroll :data="tcList" :class-option="optionLeft" class="seamless-warp2">
      <ul class="item">
        <li
          v-for="(item,id) in tcList"
          :key="id"
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
        </li>
      </ul>
    </vue-seamless-scroll>
  </div>
</template>

<script>
// 引入文档查看、文档下载接口
// 模拟接口,shijie
import {
  // shijie,
  // 客户端当日操作频次+异常操作频次
  getUserDaySums,
  // 公司轮播图
  getSumComs,
  // 登录用户数量
  loginUserNums,
  // 账号并发量
  queryTable,
  // 部门事件数量接口
  getDepNums,
  // 当天客户端总事件
  getDayClienCount,
  // Map地图跨公司访问
  mapAcrossCompany,
  // 安全事件+总工时+总KPI
  sumWorking,
  // 最近事件接口
  recentlyInterface,
  // tc在线人数接口
  getCountNums
} from "@/api/table.js";
// 引入时间格式
// import moment from 'moment'

export default {
  data() {
    return {
      // 最近事件异常flag
      abnormalFlag: 1,
      // 最近事件正常flag
      normalFlag: 2,
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
      // 轮播图数据
      gzOperaCount: "", //广州总事件
      gzAbnormalCount: "", //广州异常事件
      bjOperaCount: "", //北京总事件
      bjAbnormalCount: "", //北京异常事件
      shOperaCount: "", //上海总事件
      shAbnormalCount: "", //上海异常事件
      cdOperaCount: "", //成都总事件
      cdAbnormalCount: "", //成都异常事件
      zheXianFlag1: "1",
      zheXianFlag2: "2",
      zheXianFlag3: "3",
      // 传给后台的公司名字
      companyName: "",
      // 安全事件
      safetyCounts: "",
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

      // 客户端当天操作总频次
      clientTodayAllCounts: "",
      // 客户端当日操作频次日环比
      AllRatio: "",
      // 客户端当天异常操作频次
      clientTodayAbnormalCounts: "",
      // 客户端异常操作频次日环比
      AbnormalRatio: "",
      // 在线用户人数
      onlinePeopleCount: "",
      // 分页器数据
      pagesNum: 1, // 当前页
      pageSize: 4, // 每页条数
      pageDepNum: 1, // 当前页
      pageDepSize: 8, // 每页条数
      total: 5, // 总页数
      // 公司今日flag
      comSumsFlag: 2,
      // 模拟登录用户数量
      zheXianFlag: 3,
      zheXianDates: "",
      // 模拟账号并发数量
      zheXianFlagDown: 2,
      zheXianDateDown: "",
      actives1: false,
      isError: false,
      date: "",
      // 登录用户数量默认项
      activeName1: "third1",
      // 账号并发数量默认项
      activeName2: "third2",
      // 最近事件默认项
      activeName3: "first3",
      // 深圳公司事件数量
      gzactiveName4: "second4",
      bjactiveName4: "second4",
      shactiveName4: "second4",
      cdactiveName4: "second4",
      // 四个公司的名字
      shName: "",
      bjName: "",
      gzName: "",
      cdName: "",
      // 部门事件数量
      stateNum: 10,
      // 异常事件占比
      // 最近事件data数据

      tableData: [],
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
    this.recentlyList();
    // 客户端当日操作频次+异常操作频次
    this.getDayAllFre();
    // 公司轮播图
    // this.getComSums();
    this.shRotation();
    this.bjRotation();
    this.gzRotation();
    this.cdRotation();

    this.getOnlineNumes();
  },

  methods: {
    async getOnlineNumes() {
      const value = {
        url: "this.pagesNum",
        serviceName: "2020-01-30 13:41:07",
        port: "2020-03-05 13:19:08",
        userName: "2020-03-05 13:19:08",
        passWord: "2020-03-05 13:19:08",
        selectDB: "2020-03-05 13:19:08"
      };
      try {
        const { data } = await getCountNums(value);
        console.log(data);
        const { onlinePeopleCount } = data;
        this.onlinePeopleCount = onlinePeopleCount;
        console.log("11", onlinePeopleCount);
      } catch (e) {
        console.log(e);
      }
    },
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
      const value = {
        zheXianDate: this.zheXianDates
      };
      try {
        const { data } = await mapAcrossCompany(value);
        const { mapCrossCompanyList } = data;
        for (var i = 0; i < mapCrossCompanyList.length; i++) {
          if (
            mapCrossCompanyList[i].company === "成都分公司" &&
            mapCrossCompanyList[i].ownerCompany === "上海分公司"
          ) {
            this.travel.push({ start_city: "成都", end_city: "上海" });
          }
          if (
            mapCrossCompanyList[i].company === "成都分公司" &&
            mapCrossCompanyList[i].ownerCompany === "北京分公司"
          ) {
            this.travel.push({ start_city: "成都", end_city: "北京" });
          }
          if (
            mapCrossCompanyList[i].company === "成都分公司" &&
            mapCrossCompanyList[i].ownerCompany === "广州分公司"
          ) {
            this.travel.push({ start_city: "成都", end_city: "广州" });
          }
          if (
            mapCrossCompanyList[i].company === "北京分公司" &&
            mapCrossCompanyList[i].ownerCompany === "上海分公司"
          ) {
            this.travel.push({ start_city: "北京", end_city: "上海" });
          }
          if (
            mapCrossCompanyList[i].company === "北京分公司" &&
            mapCrossCompanyList[i].ownerCompany === "广州分公司"
          ) {
            this.travel.push({ start_city: "北京", end_city: "广州" });
          }
          if (
            mapCrossCompanyList[i].company === "北京分公司" &&
            mapCrossCompanyList[i].ownerCompany === "成都分公司"
          ) {
            this.travel.push({ start_city: "北京", end_city: "成都" });
          }
          if (
            mapCrossCompanyList[i].company === "广州分公司" &&
            mapCrossCompanyList[i].ownerCompany === "上海分公司"
          ) {
            this.travel.push({ start_city: "广州", end_city: "上海" });
          }
          if (
            mapCrossCompanyList[i].company === "广州分公司" &&
            mapCrossCompanyList[i].ownerCompany === "成都分公司"
          ) {
            this.travel.push({ start_city: "广州", end_city: "成都" });
          }
          if (
            mapCrossCompanyList[i].company === "广州分公司" &&
            mapCrossCompanyList[i].ownerCompany === "北京分公司"
          ) {
            this.travel.push({ start_city: "广州", end_city: "北京" });
          }
          if (
            mapCrossCompanyList[i].company === "上海分公司" &&
            mapCrossCompanyList[i].ownerCompany === "广州分公司"
          ) {
            this.travel.push({ start_city: "上海", end_city: "广州" });
          }
          if (
            mapCrossCompanyList[i].company === "上海分公司" &&
            mapCrossCompanyList[i].ownerCompany === "成都分公司"
          ) {
            this.travel.push({ start_city: "上海", end_city: "成都" });
          }
          if (
            mapCrossCompanyList[i].company === "上海分公司" &&
            mapCrossCompanyList[i].ownerCompany === "北京分公司"
          ) {
            this.travel.push({ start_city: "上海", end_city: "北京" });
          } else {
            this.travel.push({ start_city: "", end_city: "" });
          }
        }
        var travel = this.travel;
      } catch (e) {
        console.log(e);
      }

      // var planePath = "image://../../../../../assets/map.png";
      // var planePath = "image://../../../../../../assets/map.png";
      var planePath = "image://@/assets/map.png";
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
              color: "#fff",
              symbolSize: 5
            },
            lineStyle: {
              normal: {
                color: color[i],
                width: 0,
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
                width: 0
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
    //     this.onlinePeopleCount = arr1[0].age
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
          safetyCounts,
          allWorkingHoursToday,
          differenceHours,
          todayKPI,
          yesterdayKPI
        } = data;
        console.log("zons", safetyCounts);
        this.safetyCounts = safetyCounts;
        this.allWorkingHoursToday = allWorkingHoursToday;
        this.differenceHours = differenceHours;
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

    // 1.	当天客户端总事件
    async getDayUserAllNums() {
      try {
        const { data } = await getDayClienCount();
        const { allClientEventCount } = data;
        this.allClientEventCount = allClientEventCount;
      } catch (e) {
        console.log(e);
      }
    },
    // 客户端当日操作频次+异常操作频次
    async getDayAllFre() {
      try {
        const { data } = await getUserDaySums();
        const {
          clientTodayAllCounts,
          AllRatio,
          clientTodayAbnormalCounts,
          AbnormalRatio
        } = data;
        this.clientTodayAllCounts = clientTodayAllCounts;
        this.AllRatio = AllRatio;
        this.clientTodayAbnormalCounts = clientTodayAbnormalCounts;
        this.AbnormalRatio = AbnormalRatio;
      } catch (e) {
        console.log(e);
      }
    },

    // 分页功能
    // 更改每页个数
    handleSizeChange(val) {
      this.pageSize = val;
      this.pagesNum = 1;
      this.recentlyList();
    },
    // 切换页
    handleCurrentChange(val) {
      this.pagesNum = val;
      this.recentlyList();
    },
    // 部门事件更改每页个数
    handleDepChange(val) {
      this.pageDepSize = val;
      this.pageDepNum = 1;
      this.eventList();
    },
    // 切换页
    handleDepChange(val) {
      this.pageDepNum = val;
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
        this.tableData = recentEventsList;
      } catch (e) {
        console.log(e);
      }
    },
    // 部门事件列表渲染
    async eventList() {
      const value = {
        // pagesNum: this.pagesNum,
        // actionBeginTime: "2020-01-30 13:41:07",
        // actionEndTime: "2020-03-05 13:19:08"
      };
      try {
        const { data } = await getDepNums(value);
        const { allGroupList } = data;
        this.userList = allGroupList;
        this.total = data.allGroupList.length;
      } catch (e) {
        console.log(e);
      }
    },
    // 切换公司的tab栏
    gzComTab(tab, event) {
      if (tab.label === "今日") {
        var textSumData = document.getElementById("gzTextContent");
        this.companyName = textSumData.innerHTML;
        this.switcHgzRotation(
          { zheXianFlag: (this.comSumsFlag = 1) },
          { company: this.companyName }
        );
      }
      if (tab.label === "本周") {
        var textSumData = document.getElementById("gzTextContent");
        this.companyName = textSumData.innerHTML;
        this.switcHgzRotation(
          { zheXianFlag: (this.comSumsFlag = 2) },
          { company: this.companyName }
        );
      }
      if (tab.label === "本月") {
        var textSumData = document.getElementById("gzTextContent");
        this.companyName = textSumData.innerHTML;
        this.switcHgzRotation(
          { zheXianFlag: (this.comSumsFlag = 3) },
          { company: this.companyName }
        );
      }
    },
    shComTab(tab, event) {
      if (tab.label === "今日") {
        var textSumData = document.getElementById("shTextContent");
        this.companyName = textSumData.innerHTML;
        this.switcHshRotation(
          { zheXianFlag: (this.comSumsFlag = 1) },
          { company: this.companyName }
        );
      }
      if (tab.label === "本周") {
        var textSumData = document.getElementById("shTextContent");
        this.companyName = textSumData.innerHTML;
        this.switcHshRotation(
          { zheXianFlag: (this.comSumsFlag = 2) },
          { company: this.companyName }
        );
      }
      if (tab.label === "本月") {
        var textSumData = document.getElementById("shTextContent");
        this.companyName = textSumData.innerHTML;
        this.switcHshRotation(
          { zheXianFlag: (this.comSumsFlag = 3) },
          { company: this.companyName }
        );
      }
    },
    bjComTab(tab, event) {
      if (tab.label === "今日") {
        var textSumData = document.getElementById("bjTextContent");
        this.companyName = textSumData.innerHTML;
        this.switcHbjRotation(
          { zheXianFlag: (this.comSumsFlag = 1) },
          { company: this.companyName }
        );
      }
      if (tab.label === "本周") {
        var textSumData = document.getElementById("bjTextContent");
        this.companyName = textSumData.innerHTML;
        this.switcHbjRotation(
          { zheXianFlag: (this.comSumsFlag = 2) },
          { company: this.companyName }
        );
      }
      if (tab.label === "本月") {
        var textSumData = document.getElementById("bjTextContent");
        this.companyName = textSumData.innerHTML;
        this.switcHbjRotation(
          { zheXianFlag: (this.comSumsFlag = 3) },
          { company: this.companyName }
        );
      }
    },
    cdComTab(tab, event) {
      if (tab.label === "今日") {
        var textSumData = document.getElementById("cdTextContent");
        this.companyName = textSumData.innerHTML;
        this.switcHcdRotation(
          { zheXianFlag: (this.comSumsFlag = 1) },
          { company: this.companyName }
        );
      }
      if (tab.label === "本周") {
        var textSumData = document.getElementById("cdTextContent");
        this.companyName = textSumData.innerHTML;
        this.switcHcdRotation(
          { zheXianFlag: (this.comSumsFlag = 2) },
          { company: this.companyName }
        );
      }
      if (tab.label === "本月") {
        var textSumData = document.getElementById("cdTextContent");
        this.companyName = textSumData.innerHTML;
        this.switcHcdRotation(
          { zheXianFlag: (this.comSumsFlag = 3) },
          { company: this.companyName }
        );
      }
    },
    // 初始化的轮播图
    // 上海轮播图
    async shRotation() {
      const value = {
        zheXianFlag: this.comSumsFlag,
        zheXianDate: this.zheXianDates,
        company: this.companyName
      };
      try {
        const { data } = await getSumComs(value);
        const { companyZheXianList, companyEventsList } = data;
        // 公司名字
        this.shName = companyZheXianList[0].companyName;
        // 总事件个数
        this.shOperaCount = companyEventsList[0].operaCount;
        // 异常事件个数
        this.shAbnormalCount = companyEventsList[0].abnormalCount;

        // 事件总个数
        const shTotalEvents = this.shOperaCount;
        // 异常事件个数
        const shAbnormalEvents = this.shAbnormalCount;
        // 异常事件占比
        const shProportionEvents = companyEventsList[0].recentInfo;

        // 公司总数据
        const allComData = companyZheXianList.map((item, index) => {
          return item.children;
        });
        // 上海分公司数据
        const shX = allComData[0].map((item, index) => {
          return item.zheXianX;
        });
        const shY = allComData[0].map((item, index) => {
          return item.zheXianY;
        });
        this.shItemCom(shX, shY);
        this.shPieCom(shTotalEvents, shAbnormalEvents, shProportionEvents);
      } catch (e) {
        console.log(e);
      }
    },
    // 北京轮播图
    async bjRotation() {
      const value = {
        zheXianFlag: this.comSumsFlag,
        zheXianDate: this.zheXianDates,
        company: this.companyName
      };
      try {
        const { data } = await getSumComs(value);
        const { companyZheXianList, companyEventsList } = data;
        this.bjOperaCount = companyEventsList[1].operaCount;
        this.bjAbnormalCount = companyEventsList[1].abnormalCount;
        this.bjName = companyZheXianList[1].companyName;
        const bjTotalEvents = this.bjOperaCount;
        const bjAbnormalEvents = this.bjAbnormalCount;
        const bjProportionEvents = companyEventsList[1].recentInfo;

        // 公司总数据
        const allComData = companyZheXianList.map((item, index) => {
          return item.children;
        });
        // 北京分公司数据
        const bjX = allComData[1].map(item => {
          return item.zheXianX;
        });
        const bjY = allComData[1].map(item => {
          return item.zheXianY;
        });
        this.bjItemCom(bjX, bjY);
        this.bjPieCom(bjTotalEvents, bjAbnormalEvents, bjProportionEvents);
      } catch (e) {
        console.log(e);
      }
    },
    // 广州轮播图
    async gzRotation() {
      const value = {
        zheXianFlag: this.comSumsFlag,
        zheXianDate: this.zheXianDates,
        company: this.companyName
      };
      try {
        const { data } = await getSumComs(value);
        const { companyZheXianList, companyEventsList } = data;
        this.gzOperaCount = companyEventsList[2].operaCount;
        this.gzAbnormalCount = companyEventsList[2].abnormalCount;
        this.gzName = companyZheXianList[2].companyName;
        const gzTotalEvents = this.gzOperaCount;
        const gzAbnormalEvents = this.gzAbnormalCount;
        const gzProportion = companyEventsList[2].recentInfo;
        // 公司总数据
        const allComData = companyZheXianList.map((item, index) => {
          return item.children;
        });
        // 广州分公司数据
        const gzX = allComData[2].map(item => {
          return item.zheXianX;
        });
        const gzY = allComData[2].map(item => {
          return item.zheXianY;
        });
        this.gzItemCom(gzX, gzY);
        this.gzPieCom(gzTotalEvents, gzAbnormalEvents, gzProportion);
      } catch (e) {
        console.log(e);
      }
    },
    // 成都轮播图
    async cdRotation() {
      const value = {
        zheXianFlag: this.comSumsFlag,
        zheXianDate: this.zheXianDates,
        company: this.companyName
      };
      try {
        const { data } = await getSumComs(value);
        const { companyZheXianList, companyEventsList } = data;
        this.cdOperaCount = companyEventsList[3].operaCount;
        this.cdAbnormalCount = companyEventsList[3].abnormalCount;
        this.cdName = companyZheXianList[3].companyName;
        const cdTotalEvents = this.cdOperaCount;
        const cdAbnormalEvents = this.cdAbnormalCount;
        const cdProportion = companyEventsList[3].recentInfo;
        // 公司总数据
        const allComData = companyZheXianList.map((item, index) => {
          return item.children;
        });
        // 成都分公司数据
        const cdX = allComData[3].map(item => {
          return item.zheXianX;
        });
        const cdY = allComData[3].map(item => {
          return item.zheXianY;
        });
        this.cdItemCom(cdX, cdY);
        this.cdPieCom(cdTotalEvents, cdAbnormalEvents, cdProportion);
      } catch (e) {
        console.log(e);
      }
    },
    // ----------------------------------------------------
    // 切换后的轮播图
    // 上海轮播图  Rotation
    async switcHshRotation() {
      const value = {
        zheXianFlag: this.comSumsFlag,
        zheXianDate: this.zheXianDates,
        company: this.companyName
      };
      try {
        const { data } = await getSumComs(value);
        const { companyZheXianList, companyEventsList } = data;
        this.shOperaCount = companyEventsList[0].operaCount;
        this.shAbnormalCount = companyEventsList[0].abnormalCount;
        const shTotalEvents = this.shOperaCount;
        const shAbnormalEvents = this.shAbnormalCount;
        const shProportionEvents = companyEventsList[0].recentInfo;
        // 公司总数据
        const allComData = companyZheXianList.map((item, index) => {
          return item.children;
        });
        // 上海分公司数据
        const shX = allComData[0].map((item, index) => {
          return item.zheXianX;
        });
        const shY = allComData[0].map((item, index) => {
          return item.zheXianY;
        });
        this.shItemCom(shX, shY);
        this.shPieCom(shTotalEvents, shAbnormalEvents, shProportionEvents);
      } catch (e) {
        console.log(e);
      }
    },
    // 北京轮播图  Rotation
    async switcHbjRotation() {
      const value = {
        zheXianFlag: this.comSumsFlag,
        zheXianDate: this.zheXianDates,
        company: this.companyName
      };
      try {
        const { data } = await getSumComs(value);
        const { companyZheXianList, companyEventsList } = data;
        this.bjOperaCount = companyEventsList[0].operaCount;
        this.bjAbnormalCount = companyEventsList[0].abnormalCount;
        const bjTotalEvents = this.bjOperaCount;
        const bjAbnormalEvents = this.bjAbnormalCount;
        const bjProportion = companyEventsList[0].recentInfo;
        // 公司总数据
        const allComData = companyZheXianList.map((item, index) => {
          return item.children;
        });
        // 北京分公司数据
        const bjX = allComData[0].map(item => {
          return item.zheXianX;
        });
        const bjY = allComData[0].map(item => {
          return item.zheXianY;
        });
        this.bjItemCom(bjX, bjY);
        this.bjPieCom(bjTotalEvents, bjAbnormalEvents, bjProportion);
      } catch (e) {
        console.log(e);
      }
    },
    // 广州轮播图  Rotation
    async switcHgzRotation() {
      const value = {
        zheXianFlag: this.comSumsFlag,
        zheXianDate: this.zheXianDates,
        company: this.companyName
      };
      try {
        const { data } = await getSumComs(value);
        const { companyZheXianList, companyEventsList } = data;
        this.gzOperaCount = companyEventsList[0].operaCount;
        this.gzAbnormalCount = companyEventsList[0].abnormalCount;
        const gzTotalEvents = this.gzOperaCount;
        const gzAbnormalEvents = this.gzAbnormalCount;
        const gzProportion = companyEventsList[0].recentInfo;
        // 公司总数据
        const allComData = companyZheXianList.map((item, index) => {
          return item.children;
        });
        // 广州分公司数据
        const gzX = allComData[0].map(item => {
          return item.zheXianX;
        });
        const gzY = allComData[0].map(item => {
          return item.zheXianY;
        });
        this.gzItemCom(gzX, gzY);
        this.gzPieCom(gzTotalEvents, gzAbnormalEvents, gzProportion);
      } catch (e) {
        console.log(e);
      }
    },
    // 成都轮播图  Rotation
    async switcHcdRotation() {
      const value = {
        zheXianFlag: this.comSumsFlag,
        zheXianDate: this.zheXianDates,
        company: this.companyName
      };
      try {
        const { data } = await getSumComs(value);
        const { companyZheXianList, companyEventsList } = data;
        this.cdOperaCount = companyEventsList[0].operaCount;
        this.cdAbnormalCount = companyEventsList[0].abnormalCount;
        const cdTotalEvents = this.cdOperaCount;
        const cdAbnormalEvents = this.cdAbnormalCount;
        const cdProportion = companyEventsList[0].recentInfo;
        // 公司总数据
        const allComData = companyZheXianList.map((item, index) => {
          return item.children;
        });
        // 成都分公司数据
        const cdX = allComData[0].map(item => {
          return item.zheXianX;
        });
        const cdY = allComData[0].map(item => {
          return item.zheXianY;
        });
        this.cdItemCom(cdX, cdY);
        this.cdPieCom(cdTotalEvents, cdAbnormalEvents, cdProportion);
      } catch (e) {
        console.log(e);
      }
    },
    // -----------------------------------------------------------
    // 登录用户数量柱状图数据
    async getInfoListDay() {
      const value = {
        zheXianFlag: this.zheXianFlag,
        zheXianDate: this.zheXianDates
      };
      try {
        const { data } = await loginUserNums(value);
        // const {
        //   zheXianListAll,
        //   zheXianListCrossDept,
        //   zheXianListSecretFile
        // } = data;
        const { companyZheXianList } = data;
        // 所有数据
        const allSumName = companyZheXianList.map((item, index) => {
          return item.companyName;
        });
        const allSum22 = companyZheXianList.map((item, index) => {
          return item.children;
        });
        // 上海分公司数据
        const AllXTimes = allSum22[0].map((item, index) => {
          return item.zheXianX;
        });
        const AllYNums = allSum22[0].map((item, index) => {
          return item.zheXianX;
        });
        // 北京分公司数据
        const CrosXTimes = allSum22[1].map(item => {
          return item.zheXianX;
        });
        const CrosYNums = allSum22[1].map(item => {
          return item.zheXianY;
        });
        // 广州分公司数据
        const SecrXTimes = allSum22[2].map(item => {
          return item.zheXianX;
        });
        const SecrYNums = allSum22[2].map(item => {
          return item.zheXianY;
        });
        // 成都分公司数据
        const cdX = allSum22[3].map(item => {
          return item.zheXianX;
        });
        const cdY = allSum22[3].map(item => {
          return item.zheXianY;
        });
        this.initItem(
          allSumName,
          AllXTimes,
          AllYNums,
          CrosXTimes,
          CrosYNums,
          SecrXTimes,
          SecrYNums,
          cdX,
          cdY
        );
      } catch (e) {
        console.log(e);
      }
    },
    // 账户并发数量折线图数据
    async getInfoListDay2() {
      const value = {
        zheXianFlag: this.zheXianFlag,
        zheXianDate: this.zheXianDates
      };
      try {
        const { data } = await queryTable(value);
        const { tcOnlineInfoList } = data;
        // 所有数据
        const downAllXTimes = tcOnlineInfoList.map(item => {
          return item.zheXianX;
        });
        const downAllYNums = tcOnlineInfoList.map(item => {
          return item.zheXianY;
        });
        // 跨部门数据
        const downCrosXTimes = tcOnlineInfoList.map(item => {
          return item.zheXianX;
        });
        const downCrosYNums = tcOnlineInfoList.map(item => {
          return item.zheXianY;
        });
        // 机密文件下载数据
        const downSecrXTimes = tcOnlineInfoList.map(item => {
          return item.zheXianX;
        });
        const downSecrYNums = tcOnlineInfoList.map(item => {
          return item.zheXianY;
        });
        this.initItem2(
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
      this.getInfoListDay();
    },
    login2() {
      this.zheXianFlag = 2;
      this.zheXianDate = this.zheXianDates;
      this.getInfoListDay();
    },
    login3() {
      this.zheXianFlag = 3;
      this.zheXianDate = this.zheXianDates;
      this.getInfoListDay();
    },
    // 账户并发三个按钮
    concurrent1() {
      this.zheXianFlag = 1;
      this.zheXianDate = this.zheXianDateDown;
      this.getInfoListDay2();
    },
    concurrent2() {
      this.zheXianFlag = 2;
      this.zheXianDate = this.zheXianDateDown;
      this.getInfoListDay2();
    },
    concurrent3() {
      this.zheXianFlag = 3;
      this.zheXianDate = this.zheXianDateDown;
      this.getInfoListDay2();
    },
    handleClick1(tab, event) {},
    handleClick2(tab, event) {},
    handleClick3(tab, event) {
      if (tab.label === "异常") {
        this.pagesNum = 1;
        this.recentlyList({ exceptionLoginFlag: this.abnormalFlag });
      }
      if (tab.label === "正常") {
        this.pagesNum = 1;
        this.recentlyList({ exceptionLoginFlag: this.normalFlag });
      }
      if (tab.label === "全部") {
        this.pagesNum = 1;
        this.recentlyList();
      }
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
    // 四个饼图占比单独分开
    shPieCom(shTotalEvents, shAbnormalEvents, shProportionEvents) {
      let myChart8_1 = this.$echarts.init(document.getElementById("main8_1"));
      const option8_1 = {
        animation: false,
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
                value: shAbnormalEvents,
                name: shProportionEvents,
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
                value: shTotalEvents,
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
      myChart8_1.setOption(option8_1);
    },
    bjPieCom(bjTotalEvents, bjAbnormalEvents, bjProportionEvents) {
      let myChart8_2 = this.$echarts.init(document.getElementById("main8_2"));
      const option8_2 = {
        animation: false,
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
                value: bjAbnormalEvents,
                name: bjProportionEvents,
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
                value: bjTotalEvents,
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
      myChart8_2.setOption(option8_2);
    },
    gzPieCom(gzTotalEvents, gzAbnormalEvents, gzProportion) {
      let myChart8_3 = this.$echarts.init(document.getElementById("main8_3"));
      const option8_3 = {
        animation: false,
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
                value: gzAbnormalEvents,
                name: gzProportion,
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
                value: gzTotalEvents,
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
      myChart8_3.setOption(option8_3);
    },
    cdPieCom(cdTotalEvents, cdAbnormalEvents, cdProportion) {
      let myChart8_4 = this.$echarts.init(document.getElementById("main8_4"));
      const option8_4 = {
        animation: false,
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
                value: cdAbnormalEvents,
                name: cdProportion,
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
                value: cdTotalEvents,
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
      myChart8_4.setOption(option8_4);
    },
    // 四个子公司单独分开
    // 上海公司
    shItemCom(shX, shY) {
      let myChart9 = this.$echarts.init(document.getElementById("main9"));
      let myChart10 = this.$echarts.init(document.getElementById("main10"));
      let myChart11 = this.$echarts.init(document.getElementById("main11"));
      var fontColor = "#30eee9";
      // 上海
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
            data: []
          }
        ],
        yAxis: [
          {
            type: "value",
            minInterval: 1,
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
            data: shY
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
            data: []
          }
        ],
        yAxis: [
          {
            type: "value",
            minInterval: 1,
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
            data: shY
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
            data: []
          }
        ],
        yAxis: [
          {
            type: "value",
            minInterval: 1,
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
            data: shY
          }
        ]
      };
      myChart9.setOption(option9);
      myChart10.setOption(option10);
      myChart11.setOption(option11);
    },
    // 北京公司
    bjItemCom(bjX, bjY) {
      let myChart9_3 = this.$echarts.init(document.getElementById("main9_3"));
      let myChart10_3 = this.$echarts.init(document.getElementById("main10_3"));
      let myChart11_3 = this.$echarts.init(document.getElementById("main11_3"));
      var fontColor = "#30eee9";
      // 北京
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
            data: []
          }
        ],
        yAxis: [
          {
            type: "value",
            minInterval: 1,
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
            data: bjY
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
            data: []
          }
        ],
        yAxis: [
          {
            type: "value",
            minInterval: 1,
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
            data: bjY
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
            data: []
          }
        ],
        yAxis: [
          {
            type: "value",
            minInterval: 1,
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
            data: bjY
          }
        ]
      };
      myChart9_3.setOption(option9_3);
      myChart10_3.setOption(option10_3);
      myChart11_3.setOption(option11_3);
    },
    // 广州公司
    gzItemCom(gzX, gzY) {
      let myChart9_2 = this.$echarts.init(document.getElementById("main9_2"));
      let myChart10_2 = this.$echarts.init(document.getElementById("main10_2"));
      let myChart11_2 = this.$echarts.init(document.getElementById("main11_2"));
      var fontColor = "#30eee9";
      // 广州
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
            data: []
          }
        ],
        yAxis: [
          {
            type: "value",
            minInterval: 1,
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
            data: gzY
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
            data: []
          }
        ],
        yAxis: [
          {
            type: "value",
            minInterval: 1,
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
            data: gzY
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
            data: []
          }
        ],
        yAxis: [
          {
            type: "value",
            minInterval: 1,
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
            data: gzY
          }
        ]
      };
      myChart9_2.setOption(option9_2);
      myChart10_2.setOption(option10_2);
      myChart11_2.setOption(option11_2);
    },
    // 成都公司
    cdItemCom(cdX, cdY) {
      let myChart9_4 = this.$echarts.init(document.getElementById("main9_4"));
      let myChart10_4 = this.$echarts.init(document.getElementById("main10_4"));
      let myChart11_4 = this.$echarts.init(document.getElementById("main11_4"));
      var fontColor = "#30eee9";
      // 成都
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
            data: []
          }
        ],
        yAxis: [
          {
            type: "value",
            minInterval: 1,
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
            data: cdY
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
            data: []
          }
        ],
        yAxis: [
          {
            type: "value",
            minInterval: 1,
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
            data: cdY
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
            data: []
          }
        ],
        yAxis: [
          {
            type: "value",
            minInterval: 1,
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
            data: cdY
          }
        ]
      };
      myChart9_4.setOption(option9_4);
      myChart10_4.setOption(option10_4);
      myChart11_4.setOption(option11_4);
    },
    // card1登录用户数量柱状图
    initItem(
      allSumName,
      AllXTimes,
      AllYNums,
      CrosXTimes,
      CrosYNums,
      SecrXTimes,
      SecrYNums,
      cdX,
      cdY
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
          data: allSumName,
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
          minInterval: 1,
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
        series: [
          {
            name: allSumName[0],
            type: "bar",
            barWidth: "20%",
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
            name: allSumName[1],
            type: "bar",
            barWidth: "20%",
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
            name: allSumName[2],
            type: "bar",
            barWidth: "20%",
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
            data: SecrYNums
          },
          {
            name: allSumName[0],
            type: "bar",
            barWidth: "20%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#a024f7"
                  },
                  {
                    offset: 1,
                    color: "#8f53b9"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: cdY
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
          data: allSumName,
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
          minInterval: 1,
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

        series: [
          {
            name: allSumName[0],
            type: "bar",
            barWidth: "20%",
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
            name: allSumName[1],
            type: "bar",
            barWidth: "20%",
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
            name: allSumName[2],
            type: "bar",
            barWidth: "20%",
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
            data: SecrYNums
          },
          {
            name: allSumName[3],
            type: "bar",
            barWidth: "20%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#a024f7"
                  },
                  {
                    offset: 1,
                    color: "#8f53b9"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: cdY
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
          data: allSumName,
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
          minInterval: 1,
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

        series: [
          {
            name: allSumName[0],
            type: "bar",
            barWidth: "20%",
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
            name: allSumName[1],
            type: "bar",
            barWidth: "20%",
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
            name: allSumName[2],
            type: "bar",
            barWidth: "20%",
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
            data: SecrYNums
          },
          {
            name: allSumName[3],
            type: "bar",
            barWidth: "20%",
            itemStyle: {
              normal: {
                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
                  {
                    offset: 0,
                    color: "#a024f7"
                  },
                  {
                    offset: 1,
                    color: "#8f53b9"
                  }
                ]),
                barBorderRadius: 11
              }
            },
            data: cdY
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
          data: ["北京123", "深圳123", "上海123"],
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
          minInterval: 1,
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

        series: [
          {
            name: "北京",
            type: "line",
            barWidth: "20%",
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
            data: downCrosYNums
          },
          {
            name: "深圳",
            type: "line",
            barWidth: "20%",
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
            barWidth: "20%",
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
          data: ["北京123", "深圳123", "上海123"],
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
          minInterval: 1,
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
          data: ["北京123", "深圳123", "上海123"],
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
          minInterval: 1,
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

        series: [
          {
            name: "北京",
            type: "line",
            barWidth: "20%",
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
    this.getDayUserAllNums();
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
    this.getInfoListDay();
    // 账户并发数量折线图回调函数
    this.getInfoListDay2();
    // setInterval(() => {
    //   this.getShi()
    // }, 600)
    // 基于准备好的dom，初始化echarts实例

    this.initHeight7 = document.getElementById("card7").offsetHeight;
    this.initWidth7 = document.getElementById("card7").offsetWidth;

    // 指定图表的配置项和数据
    // 地图显示数据
    this.myMapChart();

    var fontColor = "#30eee9";

    // 使用刚指定的配置项和数据显示图表。
  },
  // 计算属性
  computed: {
    optionLeft() {
      return {
        // direction: 2,
        // limitMoveNum: 2
        step: 1, // 数值越大速度滚动越快
        limitMoveNum: this.tcList.length, // 开始无缝滚动的数据量 this.dataList.length
        hoverStop: true, // 是否开启鼠标悬停stop
        direction: 2, // 0向下 1向上 2向左 3向右
        openWatch: true, // 开启数据实时监控刷新dom
        singleHeight: 0, // 单步运动停止的高度(默认值0是无缝不停止的滚动) direction => 0/1
        singleWidth: 0, // 单步运动停止的宽度(默认值0是无缝不停止的滚动) direction => 2/3
        waitTime: 1000 // 单步运动停止的时间(默认值1000ms)
      };
    }
  }
};
</script>

<style
  src="@/css/clientSideMonitor/behaviorAnalysis/productDataAnalysis/bootstrap.css"
></style>
<style
  src="@/css/clientSideMonitor/behaviorAnalysis/productDataAnalysis/dash5.css"
></style>
<style lang="scss">
.seamless-warp2 {
  overflow: hidden;
  height: 190px;
  width: 1920px;
  ul.item {
    width: 1920px;
    li {
      float: left;
      // margin-right: 10px;
    }
  }
}
.el-tooltip__popper {
  max-width: 400px;
}
</style>
