<template>
<!-- kpi排名  -->
  <div class="kpiRank">
    <!-- 公司KPI-------------------- -->
    <div class="company">
      <div class="one">公司KPI排名</div>
      <div class="two">{{ oldDay }}~{{ newDay }}</div>
      <div class="three">
        <!-- 搜索区域 -->
        <el-form :inline="true" :model="comForm" ref="form">
          <!-- 查询 -->
          <div class="seach">
            <el-button type="primary" @click="comSeachBtn">查询</el-button>
          </div>

          <!-- 结束日期 -->
          <div class="endTime">
            <el-form-item label="结束日期">
              <el-date-picker
                v-model="comForm.comEndTime"
                type="datetime"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
          <!-- 开始日期 -->
          <div class="startTime">
            <el-form-item label="开始日期">
              <el-date-picker
                v-model="comForm.comStartTime"
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
      <div class="four clearfix">
        <el-row :gutter="20">
          <!-- 饼图  -->
          <el-col :span="5">
            <div class="grid-content bg-purple">
              <div class="title">公司KPI贡献</div>
              <div class="menu">
                <div class="dior" @click="radioOne">
                  <el-radio v-model="radio1" label="1">净贡献</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio1" label="2">加分贡献</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio1" label="3">减分贡献</el-radio>
                </div>
              </div>
              <div id="card1">
                <div id="main1" style="width: 100%; top: -90px !important"></div>
              </div>
            </div>
          </el-col>
          <!-- 公司KPI排名 -->
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <div class="title">KPI排名</div>
              <div class="menu2">
                <div class="dior">
                  <el-radio v-model="radio2" label="4">净贡献</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio2" label="5">加分贡献</el-radio>
                </div>
              </div>
              <el-table
                :data="companyKpiTableList.slice((pagesNum-1)*pagesSize,pagesNum*pagesSize)"
                class="tableBox"
                :border="false"
              >
              <el-table-column
                type="index"
                label=""
                align="center"
                width="30"
                :index="table_index"
              ></el-table-column>
                <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
                <el-table-column prop="name" width="80px" label="公司" align="center"></el-table-column>
                <el-table-column label="总KPI" width="220px" prop="progressBar" align="center">
                  <!-- 循环遍历显示百分百数值 -->

                  <template slot-scope="{ row }">
                    <div v-if="row.name === '北京公司'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#7172db"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '上海公司'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#e78b57"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '广州公司'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#dede66"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else>
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#84c9c9"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="sequential" label="环比" align="center">
                  <template slot-scope="{ row }">
                    <div v-if="row.sequential > 0">
                      <img src="@/assets/up.png" class="sequentialImg" />
                      <p class="sequentialNum">{{ row.sequential + "%" }}</p>
                    </div>
                    <div v-else>
                      <img src="@/assets/down.png" class="sequentialImg" />
                      <p class="sequentialNumRed">{{ row.sequential + "%" }}</p>
                    </div>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination
                @current-change="comKpiChange"
                class="pagFen"
                :current-page="pagesNum"
                :page-sizes="[25, 50, 100]"
                :page-size="pagesSize"
                :total="companyKpiTableList.length"
                small
                layout="total,prev, pager, next"
              ></el-pagination>
            </div>
          </el-col>
          <!-- 公司加分排名 -->
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <div class="title">加分排名</div>
              <div class="menu2">
                <div class="dior">
                  <el-radio v-model="radio3" label="6">总加分</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio3" label="7">平均加分</el-radio>
                </div>
              </div>
              <el-table
                :data="companyBonusTableList.slice((comBonusNum-1)*pagesSize,comBonusNum*pagesSize)"
                class="tableBox"
                :border="false"
                te
              >
              <el-table-column
                type="index"
                label=""
                align="center"
                width="30"
                :index="table_index2"
              ></el-table-column>
                <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
                <el-table-column prop="name" width="80px" label="公司" align="center"></el-table-column>
                <el-table-column label="总KPI" width="220px" prop="progressBar" align="center">
                  <!-- 循环遍历显示百分百数值 -->

                  <template slot-scope="{ row }">
                    <div v-if="row.name === '北京公司'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#7172db"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '上海公司'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#e78b57"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '广州公司'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#dede66"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else>
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#84c9c9"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="sequential" label="环比" align="center">
                  <template slot-scope="{ row }">
                    <div v-if="row.sequential > 0">
                      <img src="@/assets/up.png" class="sequentialImg" />
                      <p class="sequentialNum">{{ row.sequential + "%" }}</p>
                    </div>
                    <div v-else>
                      <img src="@/assets/down.png" class="sequentialImg" />
                      <p class="sequentialNumRed">{{ row.sequential + "%" }}</p>
                    </div>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination
                class="pagFen"
                @current-change="comBonusChange"
                :current-page="comBonusNum"
                :page-sizes="[25, 50, 100]"
                :page-size="pagesSize"
                :total="companyBonusTableList.length"
                small
                layout="total,prev, pager, next"
              ></el-pagination>
            </div>
          </el-col>
          <!-- 公司减分排名 -->
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <div class="title">减分排名</div>
              <div class="menu2">
                <div class="dior">
                  <el-radio v-model="radio4" label="8">总减分</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio4" label="9">平均减分</el-radio>
                </div>
              </div>
              <el-table
                :data="companyPointsTableList.slice((comPointsNum-1)*pagesSize,comPointsNum*pagesSize)"
                class="tableBox"
                :border="false"
                te
              >
              <el-table-column
                type="index"
                label=""
                align="center"
                width="30"
                :index="table_index3"
              ></el-table-column>
                <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
                <el-table-column prop="name" width="80px" label="公司" align="center"></el-table-column>
                <el-table-column label="总KPI" width="220px" prop="progressBar" align="center">
                  <!-- 循环遍历显示百分百数值 -->

                  <template slot-scope="{ row }">
                    <div v-if="row.name === '北京公司'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#7172db"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '上海公司'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#e78b57"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '广州公司'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#dede66"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else>
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#84c9c9"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="sequential" label="环比" align="center">
                  <template slot-scope="{ row }">
                    <div v-if="row.sequential > 0">
                      <img src="@/assets/up.png" class="sequentialImg" />
                      <p class="sequentialNum">{{ row.sequential + "%" }}</p>
                    </div>
                    <div v-else>
                      <img src="@/assets/down.png" class="sequentialImg" />
                      <p class="sequentialNumRed">{{ row.sequential + "%" }}</p>
                    </div>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination
                class="pagFen"
                @current-change="comPointsChange"
                :page-sizes="[25, 50, 100]"
                :page-size="pagesSize"
                :total="companyPointsTableList.length"
                small
                layout="total,prev, pager, next"
              ></el-pagination>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
    <!-- 部门KPI-------------------- -->
    <div class="department">
      <div class="one">部门KPI排名</div>
      <div class="two">{{ oldDay }}~{{ newDay }}</div>
      <div class="three clearfix">
        <div class="dropDownSeach">
          <!-- 搜索区域 -->
          <el-form :inline="true" :model="depForm" ref="form">
            <div class="dropDownCompany">
              <el-form-item label="公司">
                <el-autocomplete
                  v-model="depForm.depCompany"
                  placeholder="北京公司"
                  clearable
                  type="text"
                  :debounce="0"
                  :fetch-suggestions="queryCompany"
                  @select="handleSelectScen"
                  style="width: 150px; margin-right:10px"
                ></el-autocomplete>
              </el-form-item>
              <el-form-item label="部门">
                <el-autocomplete
                  v-model="depForm.depDepartment"
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
            <!-- 查询 -->
            <div class="seach">
              <el-form-item style="float: none;">
                <el-button type="primary" @click="depSeachBtn">查询</el-button>
              </el-form-item>
            </div>

            <!-- 结束日期 -->
            <div class="endTime">
              <el-form-item label="结束日期">
                <el-date-picker
                  v-model="depForm.depEndTime"
                  type="datetime"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  clearable
                ></el-date-picker>
              </el-form-item>
            </div>
            <!-- 开始日期 -->
            <div class="startTime">
              <el-form-item label="开始日期">
                <el-date-picker
                  v-model="depForm.depStartTime"
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
      </div>
      <div class="four clearfix">
        <el-row :gutter="20">
          <!-- 部门KPI饼图数据 -->
          <el-col :span="5">
            <div class="grid-content bg-purple">
              <div class="title">部门KPI贡献</div>
              <div class="menu">
                <div class="dior">
                  <el-radio v-model="radio11" label="11">净贡献</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio11" label="22">加分贡献</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio11" label="33">减分贡献</el-radio>
                </div>
              </div>
              <div id="card2">
                <div id="main2" style="width: 100%; top: -90px !important"></div>
              </div>
            </div>
          </el-col>
          <!-- 部门kpi排名数据  -->
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <div class="title">KPI排名</div>
              <div class="menu2">
                <div class="dior">
                  <el-radio v-model="radio22" label="44">总KPI</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio22" label="55">平均KPI</el-radio>
                </div>
              </div>
              <el-table
                :data="departmentKpiTableList.slice((depKpiNum-1)*pagesSize,depKpiNum*pagesSize)"
                class="tableBox"
                :border="false"
                te
              >
              <el-table-column
                type="index"
                label=""
                align="center"
                width="30"
                :index="table_index4"
              ></el-table-column>
                <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
                <el-table-column prop="name" width="80px" label="部门" align="center"></el-table-column>
                <el-table-column label="总KPI" width="220px" prop="progressBar" align="center">
                  <!-- 循环遍历显示百分百数值 -->

                  <template slot-scope="{ row }">
                    <div v-if="row.name === '研发三部'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#7172db"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '研发一部'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#e78b57"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '研发二部'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#dede66"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else>
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#84c9c9"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="sequential" label="环比" align="center">
                  <template slot-scope="{ row }">
                    <div v-if="row.sequential > 0">
                      <img src="@/assets/up.png" class="sequentialImg" />
                      <p class="sequentialNum">{{ row.sequential + "%" }}</p>
                    </div>
                    <div v-else>
                      <img src="@/assets/down.png" class="sequentialImg" />
                      <p class="sequentialNumRed">{{ row.sequential + "%" }}</p>
                    </div>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination
                class="pagFen"
                @current-change="depKpiChange"
                :page-sizes="[25, 50, 100]"
                :page-size="pagesSize"
                :total="departmentKpiTableList.length"
                small
                layout="total,prev, pager, next"
              ></el-pagination>
            </div>
          </el-col>
          <!-- 部门kpi加分数据  -->
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <div class="title">加分排名</div>
              <div class="menu2">
                <div class="dior">
                  <el-radio v-model="radio33" label="66">总加分</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio33" label="77">平均加分</el-radio>
                </div>
              </div>
              <el-table
                :data="departmentBonusTableList.slice((depBonusNum-1)*pagesSize,depBonusNum*pagesSize)"
                class="tableBox"
                :border="false"
                te
              >
              <el-table-column
                type="index"
                label=""
                align="center"
                width="30"
                :index="table_index5"
              ></el-table-column>
                <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
                <el-table-column prop="name" width="80px" label="部门" align="center"></el-table-column>
                <el-table-column label="总KPI" width="220px" prop="progressBar" align="center">
                  <!-- 循环遍历显示百分百数值 -->

                  <template slot-scope="{ row }">
                    <div v-if="row.name === '研发三部'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#7172db"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '研发一部'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#e78b57"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '研发二部'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#dede66"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else>
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#84c9c9"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="sequential" label="环比" align="center">
                  <template slot-scope="{ row }">
                    <div v-if="row.sequential > 0">
                      <img src="@/assets/up.png" class="sequentialImg" />
                      <p class="sequentialNum">{{ row.sequential + "%" }}</p>
                    </div>
                    <div v-else>
                      <img src="@/assets/down.png" class="sequentialImg" />
                      <p class="sequentialNumRed">{{ row.sequential + "%" }}</p>
                    </div>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination
                class="pagFen"
                @current-change="depBonusChange"
                :page-sizes="[25, 50, 100]"
                :page-size="pagesSize"
                :total="departmentBonusTableList.length"
                small
                layout="total,prev, pager, next"
              ></el-pagination>
            </div>
          </el-col>
          <!-- 部门kpi减分数据  -->
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <div class="title">减分排名</div>
              <div class="menu2">
                <div class="dior">
                  <el-radio v-model="radio44" label="88">总减分</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio44" label="99">平均减分</el-radio>
                </div>
              </div>
              <el-table
                :data="departmentPointsTableList.slice((depPointsNum-1)*pagesSize,depPointsNum*pagesSize)"
                class="tableBox"
                :border="false"
                te
              >
              <el-table-column
                type="index"
                label=""
                align="center"
                width="30"
                :index="table_index6"
              ></el-table-column>
                <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
                <el-table-column prop="name" width="80px" label="部门" align="center"></el-table-column>
                <el-table-column label="总KPI" width="220px" prop="progressBar" align="center">
                  <!-- 循环遍历显示百分百数值 -->

                  <template slot-scope="{ row }">
                    <div v-if="row.name === '研发三部'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#7172db"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '研发一部'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#e78b57"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '研发二部'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#dede66"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else>
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#84c9c9"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="sequential" label="环比" align="center">
                  <template slot-scope="{ row }">
                    <div v-if="row.sequential > 0">
                      <img src="@/assets/up.png" class="sequentialImg" />
                      <p class="sequentialNum">{{ row.sequential + "%" }}</p>
                    </div>
                    <div v-else>
                      <img src="@/assets/down.png" class="sequentialImg" />
                      <p class="sequentialNumRed">{{ row.sequential + "%" }}</p>
                    </div>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination
                class="pagFen"
                @current-change="depPointsChange"
                :page-sizes="[25, 50, 100]"
                :page-size="pagesSize"
                :total="departmentPointsTableList.length"
                small
                layout="total,prev, pager, next"
              ></el-pagination>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
    <!-- 员工KPI-------------------- -->
    <div class="employees">
      <div class="one">员工KPI排名</div>
      <div class="two">{{ oldDay }}~{{ newDay }}</div>
      <div class="three clearfix">
        <div class="dropDownSeach">
          <!-- 搜索区域 -->
          <el-form :inline="true" :model="empForm" ref="form">
            <div class="dropDownCompany">
              <el-form-item label="公司">
                <el-autocomplete
                  v-model="empForm.empCompany"
                  placeholder="北京公司"
                  clearable
                  type="text"
                  :debounce="0"
                  :fetch-suggestions="queryCompany"
                  @select="handleSelectScen"
                  style="width: 150px; margin-right:10px"
                ></el-autocomplete>
              </el-form-item>
              <el-form-item label="部门">
                <el-autocomplete
                  v-model="empForm.empDepartment"
                  placeholder="研发部"
                  clearable
                  type="text"
                  :debounce="0"
                  :fetch-suggestions="queryDepartment"
                  @select="handleSelectScen"
                  style="width: 150px; margin-right:10px"
                ></el-autocomplete>
              </el-form-item>
              <el-form-item label="员工">
                <el-autocomplete
                  v-model="empForm.empName"
                  placeholder="江生"
                  clearable
                  type="text"
                  :debounce="0"
                  :fetch-suggestions="queryName"
                  @select="handleSelectScen"
                  style="width: 150px;"
                ></el-autocomplete>
              </el-form-item>
            </div>
            <!-- 查询 -->
            <div class="seach">
              <el-form-item style="float: none;">
                <el-button type="primary" @click="empSeachBtn">查询</el-button>
              </el-form-item>
            </div>

            <!-- 结束日期 -->
            <div class="endTime">
              <el-form-item label="结束日期">
                <el-date-picker
                  v-model="empForm.empEndTime"
                  type="datetime"
                  format="yyyy-MM-dd"
                  value-format="yyyy-MM-dd"
                  clearable
                ></el-date-picker>
              </el-form-item>
            </div>
            <!-- 开始日期 -->
            <div class="startTime">
              <el-form-item label="开始日期">
                <el-date-picker
                  v-model="empForm.empStartTime"
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
      </div>
      <div class="four emp clearfix">
        <el-row :gutter="20">
          <!-- 员工饼图数据 -->
          <el-col :span="5">
            <div class="grid-content bg-purple">
              <div class="title">员工KPI贡献</div>
              <div class="menu">
                <div class="dior">
                  <el-radio v-model="radio111" label="111">净贡献</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio111" label="222">加分贡献</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio111" label="333">减分贡献</el-radio>
                </div>
              </div>
              <!-- <div id="card3"> -->
              <div id="main3" style="width: 100%; height:340px"></div>
              <!-- </div> -->
            </div>
          </el-col>
          <!-- 员工kpi排名数据 -->
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <div class="title">KPI排名</div>
              <div class="menu2">
                <div class="dior">
                  <el-radio v-model="radio222" label="444">总KPI</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio222" label="555">平均KPI</el-radio>
                </div>
              </div>
              <el-table
                :data="employeesKpiTableList.slice((empKpiNum-1)*empPagesSize,empKpiNum*empPagesSize)"
                class="tableBox"
                :border="false"
                te
              >
              <el-table-column
                type="index"
                label=""
                align="center"
                width="30"
                :index="table_index7"
              ></el-table-column>
                <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
                <el-table-column prop="name" width="80px" label="员工" align="center"></el-table-column>
                <el-table-column label="总KPI" width="220px" prop="progressBar" align="center">
                  <!-- 循环遍历显示百分百数值 -->

                  <template slot-scope="{ row }">
                    <div v-if="row.name === '常艳丽'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#ff00ff"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '程岩松'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#0be3f5"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '柴言义'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#dede66"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '陈彦辉'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#f99d70"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '迟雁飞'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#0000ff"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else>
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#3f99fd"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="sequential" label="环比" align="center">
                  <template slot-scope="{ row }">
                    <div v-if="row.sequential > 0">
                      <img src="@/assets/up.png" class="sequentialImg" />
                      <p class="sequentialNum">{{ row.sequential + "%" }}</p>
                    </div>
                    <div v-else>
                      <img src="@/assets/down.png" class="sequentialImg" />
                      <p class="sequentialNumRed">{{ row.sequential + "%" }}</p>
                    </div>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination
                class="pagFen"
                @current-change="empKpiChange"
                :page-sizes="[25, 50, 100]"
                :page-size="empPagesSize"
                :total="employeesKpiTableList.length"
                small
                layout="total,prev, pager, next"
              ></el-pagination>
            </div>
          </el-col>
          <!-- 员工kpi加分数据 -->
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <div class="title">加分排名</div>
              <div class="menu2">
                <div class="dior">
                  <el-radio v-model="radio333" label="666">总加分</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio333" label="777">平均加分</el-radio>
                </div>
              </div>
              <el-table
                :data="employeesPointsTableList.slice((empBonusNum-1)*empPagesSize,empBonusNum*empPagesSize)"
                class="tableBox"
                :border="false"
                te
              >
              <el-table-column
                type="index"
                label=""
                align="center"
                width="30"
                :index="table_index8"
              ></el-table-column>
                <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
                <el-table-column prop="name" width="80px" label="员工" align="center"></el-table-column>
                <el-table-column label="总KPI" width="220px" prop="progressBar" align="center">
                  <!-- 循环遍历显示百分百数值 -->

                  <template slot-scope="{ row }">
                    <div v-if="row.name === '常艳丽'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#ff00ff"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '程岩松'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#0be3f5"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '柴言义'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#dede66"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '陈彦辉'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#f99d70"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '迟雁飞'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#0000ff"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else>
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#3f99fd"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="sequential" label="环比" align="center">
                  <template slot-scope="{ row }">
                    <div v-if="row.sequential > 0">
                      <img src="@/assets/up.png" class="sequentialImg" />
                      <p class="sequentialNum">{{ row.sequential + "%" }}</p>
                    </div>
                    <div v-else>
                      <img src="@/assets/down.png" class="sequentialImg" />
                      <p class="sequentialNumRed">{{ row.sequential + "%" }}</p>
                    </div>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination
                class="pagFen"
                @current-change="empBonusChange"
                :page-sizes="[25, 50, 100]"
                :page-size="empPagesSize"
                :total="employeesBonusTableList.length"
                small
                layout="total,prev, pager, next"
              ></el-pagination>
            </div>
          </el-col>
          <!-- 员工kpi减分数据 -->
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <div class="title">减分排名</div>
              <div class="menu2">
                <div class="dior">
                  <el-radio v-model="radio444" label="888">总减分</el-radio>
                </div>
                <div class="dior">
                  <el-radio v-model="radio444" label="999">平均减分</el-radio>
                </div>
              </div>
              <el-table
                :data="employeesPointsTableList.slice((empPointsNum-1)*empPagesSize,empPointsNum*empPagesSize)"
                class="tableBox"
                :border="false"
                te
              >
              <el-table-column
                type="index"
                label=""
                align="center"
                width="30"
                :index="table_index9"
              ></el-table-column>
                <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
                <el-table-column prop="name" width="80px" label="员工" align="center"></el-table-column>
                <el-table-column label="总KPI" width="220px" prop="progressBar" align="center">
                  <!-- 循环遍历显示百分百数值 -->
                  <template slot-scope="{ row }">
                    <div v-if="row.name === '常艳丽'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#ff00ff"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '程岩松'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#0be3f5"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '柴言义'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#dede66"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '陈彦辉'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#f99d70"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else-if="row.name === '迟雁飞'">
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#0000ff"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                    <div v-else>
                      <el-progress
                        :percentage="row.progressBar"
                        :stroke-width="18"
                        color="#3f99fd"
                        :show-text="false"
                      ></el-progress>
                      <p class="beiJinNum">{{ row.progressBarNum }}</p>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="sequential" label="环比" align="center">
                  <template slot-scope="{ row }">
                    <div v-if="row.sequential > 0">
                      <img src="@/assets/up.png" class="sequentialImg" />
                      <p class="sequentialNum">{{ row.sequential + "%" }}</p>
                    </div>
                    <div v-else>
                      <img src="@/assets/down.png" class="sequentialImg" />
                      <p class="sequentialNumRed">{{ row.sequential + "%" }}</p>
                    </div>
                  </template>
                </el-table-column>
              </el-table>

              <el-pagination
                class="pagFen"
                @current-change="empPointsChange"
                :page-sizes="[25, 50, 100]"
                :page-size="empPagesSize"
                :total="employeesPointsTableList.length"
                small
                layout="total,prev, pager, next"
              ></el-pagination>

            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import {
  // getViewZheXian
  // 模拟饼图接口
  getPieData,
  // 模拟kpi排名-公司排名接口
  // 所有公司模糊查询（借用场景模糊查询）
  querySearchCompany,
  // 姓名模糊查询
  querySearchName,
  // 部门模糊查询
  queryDownListGroup
} from "@/api/table.js";
// import moment from 'moment'
export default {
  data() {
    return {
      // 分页器数据
      pagesNum: 1, // 公司KPI排名
      comBonusNum: 1, // 公司加分排名
      comPointsNum: 1, // 公司减分排名

      depKpiNum: 1, // 公司减分排名
      depBonusNum: 1, // 公司减分排名
      depPointsNum: 1, // 公司减分排名
      empKpiNum: 1, // 员工减分排名
      empBonusNum: 1, // 员工减分排名
      empPointsNum: 1, // 员工减分排名
      pagesSize: 5, // 每页条数
      empPagesSize: 6,
      total: 10, // 总页数
      // 公司KPI排名表单数据
      comForm: {
        // 公司KPI排名数据
        comStartTime: "", // 公司开始时间
        comEndTime: "" // 公司结束时间
      },
      // 部门KPI排名表单数据
      depForm: {
        // 部门KPI排名数据
        depCompany: "", // 部门公司
        depDepartment: "", // 部门部门
        depStartTime: "", // 部门开始时间
        depEndTime: "" // 部门结束时间
      },
      // 员工KPI排名表单数据
      empForm: {
        // 员工KPI排名数据
        empCompany: "", // 员工公司
        empDepartment: "", // 员工部门
        empName: "", // 员工姓名
        empStartTime: "", // 员工开始时间
        empEndTime: "" // 员工结束时间
      },
      // 表单里的数据
      form: {
        // 下拉菜单选择
        region1: "",
        region2: "",
        region3: "",
        region4: "",
        region5: "",
        actionBeginTime1: "", // 开始日期
        actionEndTime1: "", // 结束日期
        actionBeginTime2: "", // 开始日期
        actionEndTime2: "" // 结束日期
      },
      // 动态修用户提示时间
      nowData: "",
      // 折线图数据
      zheXianFlag: 3,
      zheXianDate: "",
      // ----------------------------------------------
      // 图表展示数据， 默认显示项
      activeName2: "second",
      oldDay: "",
      newDay: "",
      // 公司KPI贡献单选按钮
      radio1: "1",
      radio2: "4",
      radio3: "6",
      radio4: "8",
      // 部门KPI贡献单选按钮
      radio11: "11",
      radio22: "44",
      radio33: "66",
      radio44: "88",
      // 员工KPI贡献单选按钮
      radio111: "111",
      radio222: "444",
      radio333: "666",
      radio444: "888",

      // 公司KPI排名
      companyKpiTableList: [],
      // 公司加分排名
      companyBonusTableList: [],
      // 公司减分排名
      companyPointsTableList: [],

      // 部门KPI排名
      departmentKpiTableList: [],
      // 部门加分排名
      departmentBonusTableList: [],
      // 部门减分排名
      departmentPointsTableList: [],

      // 员工KPI排名
      employeesKpiTableList: [],
      // 员工加分排名
      employeesBonusTableList: [],
      // 员工减分排名
      employeesPointsTableList: []
    };
  },
  created() {
    // 默认一个月的日期填充
    this.getSumTimes();
    this.Data();
    // 公司三组数据
    this.getComKpi();
    this.getComSerial();
    this.getComPoints();
    // 部门三组数据
    this.getDepKpi();
    this.getDepSerial();
    this.getDepPoints();
    // 员工三组数据
    this.getEpmKpi();
    this.getEpmSerial();
    this.getEpmPoints();
  },
  methods: {
    // 表格分页序号
    table_index (index) {
      return (this.pagesNum - 1) * this.pagesSize + index + 1
    },
    table_index2 (index) {
      return (this.comBonusNum - 1) * this.pagesSize + index + 1
    },
    table_index3 (index) {
      return (this.comPointsNum - 1) * this.pagesSize + index + 1
    },
    table_index4 (index) {
      return (this.depKpiNum - 1) * this.pagesSize + index + 1
    },
    table_index5 (index) {
      return (this.depBonusNum - 1) * this.pagesSize + index + 1
    },
    table_index6 (index) {
      return (this.depPointsNum - 1) * this.pagesSize + index + 1
    },
    table_index7 (index) {
      return (this.empKpiNum - 1) * this.empPagesSize + index + 1
    },
    table_index8 (index) {
      return (this.empBonusNum - 1) * this.empPagesSize + index + 1
    },
    table_index9 (index) {
      return (this.empPointsNum - 1) * this.empPagesSize + index + 1
    },

    // 公司KPI排名切换页
    comKpiChange(val) {
      this.pagesNum = val;
    },
    // 公司加分排名切换页
    comBonusChange(val) {
      this.comBonusNum = val;
    },
    // 公司减分排名切换页
    comPointsChange(val) {
      this.comPointsNum = val;
    },
    // 部门KPI排名切换页
    depKpiChange(val) {
      this.depKpiNum = val;
    },
    // 部门加分排名切换页
    depBonusChange(val) {
      this.depBonusNum = val;
    },
    // 部门减分排名切换页
    depPointsChange(val) {
      this.depPointsNum = val;
    },
    // 员工KPI排名切换页
    empKpiChange(val) {
      this.empKpiNum = val;
    },
    // 员工加分排名切换页
    empBonusChange(val) {
      this.empBonusNum = val;
    },
    // 员工减分排名切换页
    empPointsChange(val) {
      this.empPointsNum = val;
    },
    // 单选按钮
    radioOne() {
      console.log("123");
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
        callback(list);
      });
    },
    // 员工模糊查询 (第一个参数， 用户输入的值  第二个值回调函数)
    queryName(queryString, callback) {
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
    handleSelectScen(item) {},
    // 公司kpi排名
    async getComKpi() {
      const value = {};
      try {
        const { data } = await getPieData(value);
        const { companyKpiTableList } = data.data;
        this.companyKpiTableList = companyKpiTableList;
        // this.companyBonusTableList = companyBonusTableList;
        // this.companyPointsTableList = companyPointsTableList;
        // this.departmentKpiTableList = departmentKpiTableList;
        // this.departmentBonusTableList = departmentBonusTableList;
        // this.departmentPointsTableList = departmentPointsTableList;
        // this.employeesKpiTableList = employeesKpiTableList;
        // this.employeesBonusTableList = employeesBonusTableList;
        // this.employeesPointsTableList = employeesPointsTableList;
        // -----------------------------------------------------------------------------------
      } catch (error) {
        console.log(error);
      }
    },
    // 公司加分排名
    async getComSerial() {
      // 传默认时间到后台
      const value = {
        pagesNum: this.pagesNum,
        pagesSize: this.pagesSize
      };
      try {
        const { data } = await getPieData(value);
        // 解构
        const { companyBonusTableList } = data.data;
        this.companyBonusTableList = companyBonusTableList;
        // -----------------------------------------------------------------------------------
      } catch (error) {
        console.log(error);
      }
    },
    // 公司减分排名
    async getComPoints() {
      // 传默认时间到后台
      const value = {};
      try {
        const { data } = await getPieData(value);
        const { companyPointsTableList } = data.data;
        this.companyPointsTableList = companyPointsTableList;
        // -----------------------------------------------------------------------------------
      } catch (error) {
        console.log(error);
      }
    },
    // -------------------------------
    // 部门kpi排名
    async getDepKpi() {
      const value = {};
      try {
        const { data } = await getPieData(value);
        const { departmentKpiTableList } = data.data;
        this.departmentKpiTableList = departmentKpiTableList;
      } catch (error) {
        console.log(error);
      }
    },
    // 部门加分排名
    async getDepSerial() {
      // 传默认时间到后台
      const value = {
        pagesNum: this.pagesNum,
        pagesSize: this.pagesSize
      };
      try {
        const { data } = await getPieData(value);
        // 解构
        const { departmentBonusTableList } = data.data;
        this.departmentBonusTableList = departmentBonusTableList;
        // -----------------------------------------------------------------------------------
      } catch (error) {
        console.log(error);
      }
    },
    // 部门减分排名
    async getDepPoints() {
      // 传默认时间到后台
      const value = {};
      try {
        const { data } = await getPieData(value);
        const { departmentPointsTableList } = data.data;
        this.departmentPointsTableList = departmentPointsTableList;
        // -----------------------------------------------------------------------------------
      } catch (error) {
        console.log(error);
      }
    },
    // -------------------------------
    // 员工kpi排名
    async getEpmKpi() {
      const value = {
        pagesNum: this.pagesNum,
      };
      try {
        const { data } = await getPieData(value);
        const { employeesKpiTableList } = data.data;
        this.employeesKpiTableList = employeesKpiTableList;
      } catch (error) {
        console.log(error);
      }
    },
    // 员工加分排名
    async getEpmSerial() {
      // 传默认时间到后台
      const value = {
        pagesNum: this.pagesNum,
        pagesSize: this.empPagesSize
      };
      try {
        const { data } = await getPieData(value);
        // 解构
        const { employeesBonusTableList } = data.data;
        this.employeesBonusTableList = employeesBonusTableList;
        // -----------------------------------------------------------------------------------
      } catch (error) {
        console.log(error);
      }
    },
    // 员工减分排名
    async getEpmPoints() {
      // 传默认时间到后台
      const value = {
                pagesSize: this.empPagesSize
      };
      try {
        const { data } = await getPieData(value);
        const { employeesPointsTableList } = data.data;
        this.employeesPointsTableList = employeesPointsTableList;
        // -----------------------------------------------------------------------------------
      } catch (error) {
        console.log(error);
      }
    },
    // -------------------------------
    // 公司饼图函数（模拟接口）
    async companyPieFunction(callback) {
      try {
        const { data } = await getPieData();
        const { companyPie } = data.data;
        const vlue1 = companyPie[0].value;
        const name1 = companyPie[0].name;
        const num1 = companyPie[0].accounted;

        const vlue2 = companyPie[1].value;
        const name2 = companyPie[1].name;
        const num2 = companyPie[1].accounted;

        const vlue3 = companyPie[2].value;
        const name3 = companyPie[2].name;
        const num3 = companyPie[2].accounted;

        const vlue4 = companyPie[3].value;
        const name4 = companyPie[3].name;
        const num4 = companyPie[3].accounted;
        const companyName = companyPie[4].companyName;
        const companyNum = companyPie[4].companyNum;

        callback(
          vlue1,
          name1,
          num1,
          vlue2,
          name2,
          num2,
          vlue3,
          name3,
          num3,
          vlue4,
          name4,
          num4,
          companyName,
          companyNum
        );
      } catch (error) {
        console.log(error);
      }
    },
    // 部门饼图函数（模拟接口）
    async departmentPieFunction(callback) {
      try {
        const { data } = await getPieData();
        const { departmentPie } = data.data;
        const vlue11 = departmentPie[0].value;
        const name11 = departmentPie[0].name;
        const num11 = departmentPie[0].accounted;

        const vlue22 = departmentPie[1].value;
        const name22 = departmentPie[1].name;
        const num22 = departmentPie[1].accounted;

        const vlue33 = departmentPie[2].value;
        const name33 = departmentPie[2].name;
        const num33 = departmentPie[2].accounted;

        const vlue44 = departmentPie[3].value;
        const name44 = departmentPie[3].name;
        const num44 = departmentPie[3].accounted;
        const departmentName = departmentPie[4].departmentName;
        const departmentNum = departmentPie[4].departmentNum;
        callback(
          vlue11,
          name11,
          num11,
          vlue22,
          name22,
          num22,
          vlue33,
          name33,
          num33,
          vlue44,
          name44,
          num44,
          departmentName,
          departmentNum
        );
      } catch (error) {
        console.log(error);
      }
    },
    // 员工饼图函数（模拟接口）
    async employeesPieFunction(callback) {
      try {
        const { data } = await getPieData();
        const { employeesPie } = data.data;
        const vlue111 = employeesPie[0].value;
        const name111 = employeesPie[0].name;
        const num111 = employeesPie[0].accounted;

        const vlue222 = employeesPie[1].value;
        const name222 = employeesPie[1].name;
        const num222 = employeesPie[1].accounted;

        const vlue333 = employeesPie[2].value;
        const name333 = employeesPie[2].name;
        const num333 = employeesPie[2].accounted;

        const vlue444 = employeesPie[3].value;
        const name444 = employeesPie[3].name;
        const num444 = employeesPie[3].accounted;

        const vlue555 = employeesPie[4].value;
        const name555 = employeesPie[4].name;
        const num555 = employeesPie[4].accounted;

        const vlue666 = employeesPie[5].value;
        const name666 = employeesPie[5].name;
        const num666 = employeesPie[5].accounted;
        const employeesName = employeesPie[6].employeesName;
        const employeesNum = employeesPie[6].employeesNum;
        callback(
          vlue111,
          name111,
          num111,
          vlue222,
          name222,
          num222,
          vlue333,
          name333,
          num333,
          vlue444,
          name444,
          num444,
          vlue555,
          name555,
          num555,
          vlue666,
          name666,
          num666,
          employeesName,
          employeesNum
        );
      } catch (error) {
        console.log(error);
      }
    },
    // 公司饼图
    companyPie(
      vlue1,
      name1,
      num1,
      vlue2,
      name2,
      num2,
      vlue3,
      name3,
      num3,
      vlue4,
      name4,
      num4,
      companyName,
      companyNum
    ) {
      let myChart1 = this.$echarts.init(document.getElementById("main1"));
      this.initHeight1 = document.getElementById("card1").offsetHeight;
      this.initWidth1 = document.getElementById("card1").offsetWidth;
      const option1 = {
        animation: false,
        title: {
          text: companyName,
          subtext: companyNum,
          x: "center",
          y: "28%",
          textStyle: {
            fontWeight: "normal",
            fontSize: 14,
            color: "white"
          },
          subtextStyle: {
            fontSize: 14,
            color: "white"
          }
        },

        legend: {
          orient: "horizontal",
          x: "center",
          top: "210",
          itemWidth: 12,
          itemHeight: 12,
          itemGap: 16,
          // 富文本形式设置宽度
          formatter: "{a|{name}}",
          textStyle: {
            rich: {
              a: {
                color: "#ccc",
                fontSize: 12,
                width: 100,
                height: 10,
                padding: [0, 0, 0, 1]
              }
            }
          }
        },
        // 鼠标放上去有提示
        tooltip: {
          show: true,
          trigger: "item",
          formatter: "{b}：{c}个"
          // formatter: '{b}：{c}个<br/>占比：({d}%)'
        },
        series: [
          {
            // 边框轮廓
            // itemStyle: { // 图形样式
            //   normal: {
            //     borderColor: '#ffffff',
            //     borderWidth: 4
            //   }
            // },
            type: "pie",
            stillShowZeroSum: true,
            // 饼图大小
            // radius: ['40%', '50%'],
            // radius: '70%',
            // center: ['50%', '50%'],
            radius: ["60%", "41%"],
            center: ["50%", "35%"],
            // 饼图轮廓阴影
            itemStyle: {
              normal: {
                // 设置扇形的阴影
                shadowBlur: 30,
                shadowColor: "rgba(255,227,42,0.3)",
                shadowOffsetX: -5,
                shadowOffsetY: 5
              }
            },
            label: {
              normal: {
                position: "inner",
                show: false,
                textStyle: {
                  color: "#fff",
                  fontSize: 12
                }
              }
            },
            labelLine: {
              normal: {
                show: false
              }
            },
            // data: arr
            data: [
              {
                value: vlue1,
                name: name1 + "(" + num1 + ")",
                radius: [0, "50%"],
                hoverAnimation: false,
                textStyle: {
                  color: "#333"
                },
                itemStyle: {
                  normal: {
                    color: "#fd9d73"
                  }
                }
              },
              {
                value: vlue2,
                name: name2 + "(" + num2 + ")",
                radius: [0, "50%"],
                hoverAnimation: false,
                itemStyle: {
                  normal: {
                    color: "#0be3f5"
                  }
                }
              },
              {
                value: vlue3,
                name: name3 + "(" + num3 + ")",
                radius: [0, "50%"],
                hoverAnimation: false,
                itemStyle: {
                  normal: {
                    color: "#fffd10"
                  }
                }
              },
              {
                value: vlue4,
                name: name4 + "(" + num4 + ")",
                radius: [0, "50%"],
                hoverAnimation: false,
                itemStyle: {
                  normal: {
                    color: "#3f98fd"
                  }
                }
              }
            ]
          }
        ]
      };
      myChart1.setOption(option1);
      let resize1 = {
        height: this.initHeight1,
        width: this.initWidth1
      };
      myChart1.resize(resize1);
    },
    // 部门饼图
    departmentPie(
      vlue11,
      name11,
      num11,
      vlue22,
      name22,
      num22,
      vlue33,
      name33,
      num33,
      vlue44,
      name44,
      num44,
      departmentName,
      departmentNum
    ) {
      let myChart2 = this.$echarts.init(document.getElementById("main2"));
      this.initHeight1 = document.getElementById("card1").offsetHeight;
      this.initWidth1 = document.getElementById("card1").offsetWidth;
      const option2 = {
        animation: false,
        title: {
          text: departmentName,
          subtext: departmentNum,
          x: "center",
          y: "28%",
          textStyle: {
            fontWeight: "normal",
            fontSize: 14,
            color: "white"
          },
          subtextStyle: {
            fontSize: 14,
            color: "white"
          }
        },

        legend: {
          orient: "horizontal",
          x: "center",
          top: "210",
          // 富文本形式设置宽度
          formatter: "{a|{name}}",
          textStyle: {
            rich: {
              a: {
                color: "#ccc",
                fontSize: 12,
                width: 100,
                height: 10,
                padding: [0, 0, 0, 1]
              }
            }
          },
          itemWidth: 12,
          itemHeight: 12,
          itemGap: 16
        },
        // 鼠标放上去有提示
        tooltip: {
          show: true,
          trigger: "item",
          formatter: "{b}：{c}个"
          // formatter: '{b}：{c}个<br/>占比：({d}%)'
        },
        series: [
          {
            // 边框轮廓
            // itemStyle: { // 图形样式
            //   normal: {
            //     borderColor: '#ffffff',
            //     borderWidth: 4
            //   }
            // },
            type: "pie",

            stillShowZeroSum: true,
            // 饼图大小
            // radius: ['40%', '50%'],
            // radius: '70%',
            // center: ['50%', '50%'],
            radius: ["60%", "41%"],
            center: ["50%", "35%"],
            // 饼图轮廓阴影
            itemStyle: {
              normal: {
                // 设置扇形的阴影
                shadowBlur: 30,
                shadowColor: "rgba(255,227,42,0.3)",
                shadowOffsetX: -5,
                shadowOffsetY: 5
              }
            },
            label: {
              normal: {
                position: "inner",
                show: false,
                textStyle: {
                  color: "#fff",
                  fontSize: 12
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
                value: vlue11,
                name: name11 + "(" + num11 + ")",
                radius: [0, "50%"],
                hoverAnimation: false,
                textStyle: {
                  color: "#333"
                },
                itemStyle: {
                  normal: {
                    color: "#fd9d73"
                  }
                }
              },
              {
                value: vlue22,
                name: name22 + "(" + num22 + ")",
                radius: [0, "50%"],
                hoverAnimation: false,
                itemStyle: {
                  normal: {
                    color: "#0be3f5"
                  }
                }
              },
              {
                value: vlue33,
                name: name33 + "(" + num33 + ")",
                radius: [0, "50%"],
                hoverAnimation: false,
                itemStyle: {
                  normal: {
                    color: "#fffd10"
                  }
                }
              },
              {
                value: vlue44,
                name: name44 + "(" + num44 + ")",
                radius: [0, "50%"],
                hoverAnimation: false,
                itemStyle: {
                  normal: {
                    color: "#3f98fd"
                  }
                }
              }
            ]
          }
        ]
      };
      myChart2.setOption(option2);
      let resize1 = {
        height: this.initHeight1,
        width: this.initWidth1
      };
      myChart2.resize(resize1);
    },
    // 员工饼图
    employeesPie(
      vlue111,
      name111,
      num111,
      vlue222,
      name222,
      num222,
      vlue333,
      name333,
      num333,
      vlue444,
      name444,
      num444,
      vlue555,
      name555,
      num555,
      vlue666,
      name666,
      num666,
      employeesName,
      employeesNum
    ) {
      let myChart3 = this.$echarts.init(document.getElementById("main3"));
      this.initHeight1 = document.getElementById("card1").offsetHeight;
      this.initWidth1 = document.getElementById("card1").offsetWidth;
      const option3 = {
        animation: false,
        title: {
          text: employeesName,
          subtext: employeesNum,
          x: "center",
          y: "28%",
          textStyle: {
            fontWeight: "normal",
            fontSize: 14,
            color: "white"
          },
          subtextStyle: {
            fontSize: 14,
            color: "white"
          }
        },
        legend: {
          x: "center",
          top: "210",
          // 富文本形式设置宽度
          formatter: "{a|{name}}",
          textStyle: {
            rich: {
              a: {
                color: "#ccc",
                fontSize: 12,
                width: 100,
                height: 10,
                padding: [0, 0, 0, 1]
              }
            }
          },
          itemWidth: 12,
          itemHeight: 12,
          itemGap: 16,
          orient: "vertical",
          left: "20%",
          align: "left",
          height: 80
        },
        // 鼠标放上去有提示
        tooltip: {
          show: true,
          trigger: "item",
          formatter: "{b}：{c}个"
          // formatter: '{b}：{c}个<br/>占比：({d}%)'
        },
        series: [
          {
            // 边框轮廓
            // itemStyle: { // 图形样式
            //   normal: {
            //     borderColor: '#ffffff',
            //     borderWidth: 4
            //   }
            // },
            type: "pie",

            stillShowZeroSum: true,
            // 饼图大小
            // radius: ['40%', '50%'],
            // radius: '70%',
            // center: ['50%', '50%'],
            radius: ["60%", "41%"],
            center: ["50%", "35%"],
            // 饼图轮廓阴影
            itemStyle: {
              normal: {
                // 设置扇形的阴影
                shadowBlur: 30,
                shadowColor: "rgba(255,227,42,0.3)",
                shadowOffsetX: -5,
                shadowOffsetY: 5
              }
            },
            label: {
              normal: {
                position: "inner",
                show: false,
                textStyle: {
                  color: "#fff",
                  fontSize: 12
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
                value: vlue111,
                name: name111 + "(" + num111 + ")",
                radius: [0, "50%"],
                hoverAnimation: false,
                textStyle: {
                  color: "#333"
                },
                itemStyle: {
                  normal: {
                    color: "#ff00ff"
                  }
                }
              },
              {
                value: vlue222,
                name: name222 + "(" + num222 + ")",
                radius: [0, "50%"],
                hoverAnimation: false,
                itemStyle: {
                  normal: {
                    color: "#0be3f5"
                  }
                }
              },
              {
                value: vlue333,
                name: name333 + "(" + num333 + ")",
                radius: [0, "50%"],
                hoverAnimation: false,
                itemStyle: {
                  normal: {
                    color: "#dede66"
                  }
                }
              },
              {
                value: vlue444,
                name: name444 + "(" + num444 + ")",
                radius: [0, "50%"],
                hoverAnimation: false,
                itemStyle: {
                  normal: {
                    color: "#f99d70"
                  }
                }
              },
              {
                value: vlue555,
                name: name555 + "(" + num555 + ")",
                radius: [0, "50%"],
                hoverAnimation: false,
                itemStyle: {
                  normal: {
                    color: "#0000ff"
                  }
                }
              },
              {
                value: vlue666,
                name: name666 + "(" + num666 + ")",
                radius: [0, "50%"],
                hoverAnimation: false,
                itemStyle: {
                  normal: {
                    color: "#3f99fd"
                  }
                }
              }
            ]
          }
        ]
      };
      myChart3.setOption(option3);
      let resize1 = {
        height: this.initHeight1,
        width: this.initWidth1
      };
      myChart3.resize(resize1);
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
      // Dtimes:一个月之前的日期
      // times:现在的日期

      // 公司KPI排名时间
      this.comForm.comStartTime = this.Dtimes;
      this.comForm.comEndTime = this.times;

      // 部门KPI排名时间
      this.depForm.depStartTime = this.Dtimes;
      this.depForm.depEndTime = this.times;

      // 员工KPI排名时间
      this.empForm.empStartTime = this.Dtimes;
      this.empForm.empEndTime = this.times;
      // 表头下面的默认时间
      this.oldDay = this.Dtimes;
      this.newDay = this.times;
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
    // 公司kpi查询按钮
    async comSeachBtn() {
      // this.isShowCellTable = false;
      const ttime1 = this.comForm.comStartTime;
      const ttime2 = this.comForm.comEndTime;
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("请合理选择时间");
        return;
      }
      if (timeStr2 < timeStr1 + 2592000000) {
        this.$message.error("时间不低于一个月");
        return;
      }
      await this.$refs.form.validate(isValid => {
        if (isValid) {
          this.getComKpi(this.comForm);
        } else {
        }
      });
    },
    // 部门kpi查询按钮
    async depSeachBtn() {
      // this.isShowCellTable = false;
      const ttime1 = this.depForm.depStartTime;
      const ttime2 = this.depForm.depEndTime;
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("请合理选择时间");
        return;
      }
      if (timeStr2 < timeStr1 + 2592000000) {
        this.$message.error("时间不低于一个月");
        return;
      }
      await this.$refs.form.validate(isValid => {
        if (isValid) {
          this.getComKpi(this.depForm);
        } else {
        }
      });
    },
    // 员工kpi查询按钮
    async empSeachBtn() {
      // this.isShowCellTable = false;
      const ttime1 = this.empForm.empStartTime;
      const ttime2 = this.empForm.empEndTime;
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("请合理选择时间");
        return;
      }
      if (timeStr2 < timeStr1 + 2592000000) {
        this.$message.error("时间不低于一个月");
        return;
      }
      await this.$refs.form.validate(isValid => {
        if (isValid) {
          this.getComKpi(this.empForm);
        } else {
        }
      });
    },
    // 查询按钮
    seachBtn() {
      // 首先验证，在执行回车事件
      this.$refs.form.validate();
      const ttime1 = this.form.actionBeginTime;
      const ttime2 = this.form.actionEndTime;
      const timeStr1 = new Date(ttime1).getTime();
      const timeStr2 = new Date(ttime2).getTime();
      if (timeStr1 > timeStr2) {
        this.$message.error("请合理选择日期");
        // return
      }
      // this.getTableList(this.form)
    }
  },
  destroyed() {},
  mounted() {
    this.companyPieFunction(this.companyPie);
    this.departmentPieFunction(this.departmentPie);
    this.employeesPieFunction(this.employeesPie);
  }
};
</script>

<style lang="scss" scoped>
@import "../../../../../css/clientSideMonitor/behaviorAnalysis/kpiAnalysis/components/kpiRankBase.css";
@import "../../../../../css/clientSideMonitor/behaviorAnalysis/kpiAnalysis/components/kpiRank.css";
</style>
