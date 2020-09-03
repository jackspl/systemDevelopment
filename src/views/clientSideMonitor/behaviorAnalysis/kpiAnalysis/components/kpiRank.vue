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
          <div class="seach fr">
            <el-button type="primary" @click="comSeachBtn">查询</el-button>
          </div>

          <!-- 结束日期 -->
          <div class="endTime fr">
            <el-form-item label="结束日期">
              <el-date-picker
                v-model="comForm.comEndTime"
                type="datetime"
                format="yyyy-MM-dd HH:mm:ss"
                value-format="yyyy-MM-dd HH:mm:ss"
                clearable
              ></el-date-picker>
            </el-form-item>
          </div>
          <!-- 开始日期 -->
          <div class="startTime fr">
            <el-form-item label="开始日期">
              <el-date-picker
                v-model="comForm.comStartTime"
                type="datetime"
                format="yyyy-MM-dd HH:mm:ss"
                value-format="yyyy-MM-dd HH:mm:ss"
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
          <el-col :span="5" class="comLang5">
            <div class="title">公司KPI贡献</div>
            <div class="menu clearfix">
              <div class="dior">
                <el-radio v-model="radio1" label="1" @change="comChange1">净贡献</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio1" label="2" @change="comChange2">加分贡献</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio1" label="3" @change="comChange3">减分贡献</el-radio>
              </div>
            </div>
            <div id="card1">
              <div id="main1" style="width: 100%;"></div>
            </div>
          </el-col>
          <!-- 公司KPI排名 -->
          <el-col :span="6" class="comLang">
            <div class="title">KPI排名</div>
            <div class="menu2 clearfix">
              <div class="dior">
                <el-radio v-model="radio2" label="4" @change="comAvgchange1">总KPI</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio2" label="5" @change="comAvgchange2">平均KPI</el-radio>
              </div>
            </div>
            <el-table
              :data="companyKpiTableList.slice((comKpiNum-1)*pagesSize,comKpiNum*pagesSize)"
              class="tableBox"
              :border="false"
            >
              <el-table-column type="index" label align="center" width="30" :index="table_index"></el-table-column>
              <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
              <el-table-column prop="company" width="100" label="公司" align="center"></el-table-column>
              <el-table-column label="总KPI" prop="KPICal" align="center">
                <!-- 循环遍历显示百分百数值 -->
                <template slot-scope="{ row }">
                  <el-progress
                    :percentage="row.KPI/10"
                    :stroke-width="18"
                    color="#F38946"
                    :show-text="false"
                  ></el-progress>
                  <p class="beiJinNum">{{ row.KPI }}</p>
                </template>
              </el-table-column>
              <el-table-column prop="KPILinkRatio" label="环比" align="center" width="90px">
                <template slot-scope="{ row }">
                  <div v-if="row.KPILinkRatio.slice(0,row.KPILinkRatio.length-1) > 0">
                    <img src="@/assets/up.png" class="ComImg" />
                    <p class="sequentialNum">{{ row.KPILinkRatio}}</p>
                  </div>
                  <div v-else>
                    <img src="@/assets/down.png" class="ComImg" />
                    <p class="sequentialNumRed">{{ row.KPILinkRatio}}</p>
                  </div>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
              @current-change="comKpiChange"
              :current-page.sync="comKpiPage"
              class="pagFen"
              :page-sizes="[25, 50, 100]"
              :page-size="pagesSize"
              :total="companyKpiTableList.length"
              small
              layout="total,prev, pager, next"
            ></el-pagination>
          </el-col>
          <!-- 公司加分排名 -->
          <el-col :span="6" class="comLang">
            <div class="title">加分排名</div>
            <div class="menu2 clearfix">
              <div class="dior">
                <el-radio v-model="radio3" label="6" @change="comSumBonus">总加分</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio3" label="7" @change="comAvgBonus">平均加分</el-radio>
              </div>
            </div>
            <el-table
              :data="companyBonusTableList.slice((comBonusNum-1)*pagesSize,comBonusNum*pagesSize)"
              class="tableBox"
              :border="false"
            >
              <el-table-column type="index" label align="center" width="30" :index="table_index2"></el-table-column>
              <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
              <el-table-column prop="company" width="100" label="公司" align="center"></el-table-column>
              <el-table-column label="总KPI" prop="KPICal" align="center">
                <!-- 循环遍历显示百分百数值 -->
                <template slot-scope="{ row }">
                  <el-progress
                    :percentage="row.KPIjiafen/10"
                    :stroke-width="18"
                    color="#F38946"
                    :show-text="false"
                  ></el-progress>
                  <p class="beiJinNum">{{ row.KPIjiafen }}</p>
                </template>
              </el-table-column>
              <el-table-column prop="KPIjiafen" label="环比" align="center" width="90px">
                <template slot-scope="{ row }">
                  <div v-if="row.KPILinkRatioJIAFEN.slice(0,row.KPILinkRatioJIAFEN.length-1) > 0">
                    <img src="@/assets/up.png" class="sequentialImg" />
                    <p class="sequentialNum">{{ row.KPILinkRatioJIAFEN}}</p>
                  </div>
                  <div v-else>
                    <img src="@/assets/down.png" class="sequentialImg" />
                    <p class="sequentialNumRed">{{ row.KPILinkRatioJIAFEN}}</p>
                  </div>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
              class="pagFen"
              @current-change="comBonusChange"
              :current-page.sync="comBonusPage"
              :page-sizes="[25, 50, 100]"
              :page-size="pagesSize"
              :total="companyBonusTableList.length"
              small
              layout="total,prev, pager, next"
            ></el-pagination>
          </el-col>
          <!-- 公司减分排名 -->
          <el-col :span="6" class="comLang">
            <div class="title">减分排名</div>
            <div class="menu2">
              <div class="dior">
                <el-radio v-model="radio4" label="8" @change="comSumDemerit">总减分</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio4" label="9" @change="comAvgDemerit">平均减分</el-radio>
              </div>
            </div>
            <el-table
              :data="companyPointsTableList.slice((comPointsNum-1)*pagesSize,comPointsNum*pagesSize)"
              class="tableBox"
              :border="false"
            >
              <el-table-column type="index" label align="center" width="30" :index="table_index3"></el-table-column>
              <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
              <el-table-column prop="company" width="100" label="公司" align="center"></el-table-column>
              <el-table-column label="总KPI" prop="KPICal" align="center">
                <!-- 循环遍历显示百分百数值 -->
                <template slot-scope="{ row }">
                  <el-progress
                    :percentage="row.KPIjianfen"
                    :stroke-width="18"
                    color="#F38946"
                    :show-text="false"
                  ></el-progress>
                  <p class="beiJinNum">{{ row.KPIjianfen }}</p>
                </template>
              </el-table-column>
              <el-table-column prop="KPIjianfen" label="环比" align="center" width="90px">
                <template slot-scope="{ row }">
                  <div v-if="row.KPILinkRatioJIANFEN.slice(0,row.KPILinkRatioJIANFEN.length-1) > 0">
                    <img src="@/assets/up.png" class="sequentialImg" />
                    <p class="sequentialNum">{{ row.KPILinkRatioJIANFEN}}</p>
                  </div>
                  <div v-else>
                    <img src="@/assets/down.png" class="sequentialImg" />
                    <p class="sequentialNumRed">{{ row.KPILinkRatioJIANFEN}}</p>
                  </div>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
              class="pagFen"
              @current-change="comPointsChange"
              :current-page.sync="comPointPage"
              :page-sizes="[25, 50, 100]"
              :page-size="pagesSize"
              :total="companyPointsTableList.length"
              small
              layout="total,prev, pager, next"
            ></el-pagination>
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
                  placeholder="所有"
                  clearable
                  type="text"
                  :debounce="0"
                  :fetch-suggestions="queryCompany"
                  @select="handleSelectScen"
                  style="width: 150px; margin-right:10px"
                ></el-autocomplete>
              </el-form-item>
              <el-form-item label="部门">
                <el-select
                  v-model="depForm.depDepartment"
                  multiple
                  filterable
                  allow-create
                  default-first-option
                  placeholder="所有"
                >
                  <el-option v-for="item in options" :key="item.options" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
            </div>
            <!-- 查询 -->
            <div class="seach fr">
              <el-form-item style="float: none;">
                <el-button type="primary" @click="depSeachBtn">查询</el-button>
              </el-form-item>
            </div>

            <!-- 结束日期 -->
            <div class="endTime fr">
              <el-form-item label="结束日期">
                <el-date-picker
                  v-model="depForm.depEndTime"
                  type="datetime"
                  format="yyyy-MM-dd HH:mm:ss"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  clearable
                ></el-date-picker>
              </el-form-item>
            </div>
            <!-- 开始日期 -->
            <div class="startTime fr">
              <el-form-item label="开始日期">
                <el-date-picker
                  v-model="depForm.depStartTime"
                  type="datetime"
                  format="yyyy-MM-dd HH:mm:ss"
                  value-format="yyyy-MM-dd HH:mm:ss"
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
          <el-col :span="5" class="comLang5">
            <div class="title">部门KPI贡献</div>
            <div class="menu">
              <div class="dior">
                <el-radio v-model="radio11" label="11" @change="depChange1">净贡献</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio11" label="22" @change="depChange2">加分贡献</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio11" label="33" @change="depChange3">减分贡献</el-radio>
              </div>
            </div>
            <div id="card2">
              <div id="main2" style="width: 100%;"></div>
            </div>
          </el-col>
          <!-- 部门kpi排名数据  -->
          <el-col :span="6" class="comLang">
            <div class="title">KPI排名</div>
            <div class="menu2">
              <div class="dior">
                <el-radio v-model="radio22" label="44" @change="depAvgchange1">总KPI</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio22" label="55" @change="depAvgchange2">平均KPI</el-radio>
              </div>
            </div>
            <el-table
              :data="departmentKpiTableList.slice((depKpiNum-1)*pagesSize,depKpiNum*pagesSize)"
              class="tableBox"
              :border="false"
            >
              <el-table-column type="index" label align="center" width="30" :index="table_index4"></el-table-column>
              <el-table-column
                prop="employeeGroup"
                :show-overflow-tooltip="true"
                width="80px"
                label="部门"
                align="center"
              ></el-table-column>
              <el-table-column label="总KPI" prop="KPICal" align="center">
                <!-- 循环遍历显示百分百数值 -->
                <template slot-scope="{ row }">
                  <el-progress
                    :percentage="row.KPI/10"
                    :stroke-width="18"
                    color="#FD70B8"
                    :show-text="false"
                  ></el-progress>
                  <p class="beiJinNum">{{ row.KPI }}</p>
                </template>
              </el-table-column>
              <el-table-column prop="KPILinkRatio" label="环比" align="center">
                <template slot-scope="{ row }">
                  <div v-if="row.KPILinkRatio.slice(0,row.KPILinkRatio.length-1) > 0">
                    <img src="@/assets/up.png" class="sequentialImg" />
                    <p class="sequentialNum">{{ row.KPILinkRatio}}</p>
                  </div>
                  <div v-else>
                    <img src="@/assets/down.png" class="sequentialImg" />
                    <p class="sequentialNumRed">{{ row.KPILinkRatio}}</p>
                  </div>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
              class="pagFen"
              @current-change="depKpiChange"
              :current-page.sync="depKpiPage"
              :page-sizes="[25, 50, 100]"
              :page-size="pagesSize"
              :total="departmentKpiTableList.length"
              small
              layout="total,prev, pager, next"
            ></el-pagination>
          </el-col>
          <!-- 部门kpi加分数据  -->
          <el-col :span="6" class="comLang">
            <div class="title">加分排名</div>
            <div class="menu2">
              <div class="dior">
                <el-radio v-model="radio33" label="66" @change="depSumBonus">总加分</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio33" label="77" @change="depAvgBonus">平均加分</el-radio>
              </div>
            </div>
            <el-table
              :data="departmentBonusTableList.slice((depBonusNum-1)*pagesSize,depBonusNum*pagesSize)"
              class="tableBox"
              :border="false"
            >
              <el-table-column type="index" label align="center" width="30" :index="table_index5"></el-table-column>
              <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
              <el-table-column
                prop="employeeGroup"
                :show-overflow-tooltip="true"
                width="80px"
                label="部门"
                align="center"
              ></el-table-column>
              <el-table-column label="总KPI" prop="KPICal" align="center">
                <!-- 循环遍历显示百分百数值 -->
                <template slot-scope="{ row }">
                  <el-progress
                    :percentage="row.KPIjiafen/10"
                    :stroke-width="18"
                    color="#FD70B8"
                    :show-text="false"
                  ></el-progress>
                  <p class="beiJinNum">{{ row.KPIjiafen }}</p>
                </template>
              </el-table-column>
              <el-table-column prop="KPIjiafen" label="环比" align="center">
                <template slot-scope="{ row }">
                  <div v-if="row.KPILinkRatioJIAFEN.slice(0,row.KPILinkRatioJIAFEN.length-1) > 0">
                    <img src="@/assets/up.png" class="sequentialImg" />
                    <p class="sequentialNum">{{ row.KPILinkRatioJIAFEN}}</p>
                  </div>
                  <div v-else>
                    <img src="@/assets/down.png" class="sequentialImg" />
                    <p class="sequentialNumRed">{{ row.KPILinkRatioJIAFEN}}</p>
                  </div>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
              class="pagFen"
              @current-change="depBonusChange"
              :current-page.sync="depBonusPage"
              :page-sizes="[25, 50, 100]"
              :page-size="pagesSize"
              :total="departmentBonusTableList.length"
              small
              layout="total,prev, pager, next"
            ></el-pagination>
          </el-col>
          <!-- 部门kpi减分数据  -->
          <el-col :span="6" class="comLang">
            <div class="title">减分排名</div>
            <div class="menu2">
              <div class="dior">
                <el-radio v-model="radio44" label="88" @change="depSumDemerit">总减分</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio44" label="99" @change="depAvgDemerit">平均减分</el-radio>
              </div>
            </div>
            <el-table
              :data="departmentPointsTableList.slice((depPointsNum-1)*pagesSize,depPointsNum*pagesSize)"
              class="tableBox"
              :border="false"
            >
              <el-table-column type="index" label align="center" width="30" :index="table_index6"></el-table-column>
              <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
              <el-table-column
                prop="employeeGroup"
                :show-overflow-tooltip="true"
                width="80px"
                label="部门"
                align="center"
              ></el-table-column>
              <el-table-column label="总KPI" prop="KPICal" align="center">
                <!-- 循环遍历显示百分百数值 -->

                <template slot-scope="{ row }">
                  <el-progress
                    :percentage="row.KPIjianfen"
                    :stroke-width="18"
                    color="#FD70B8"
                    :show-text="false"
                  ></el-progress>
                  <p class="beiJinNum">{{ row.KPIjianfen }}</p>
                </template>
              </el-table-column>
              <el-table-column prop="KPIjianfen" label="环比" align="center">
                <template slot-scope="{ row }">
                  <div v-if="row.KPILinkRatioJIANFEN.slice(0,row.KPILinkRatioJIANFEN.length-1) > 0">
                    <img src="@/assets/up.png" class="sequentialImg" />
                    <p class="sequentialNum">{{ row.KPILinkRatioJIANFEN}}</p>
                  </div>
                  <div v-else>
                    <img src="@/assets/down.png" class="sequentialImg" />
                    <p class="sequentialNumRed">{{ row.KPILinkRatioJIANFEN}}</p>
                  </div>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
              class="pagFen"
              @current-change="depPointsChange"
              :current-page.sync="depPointsPage"
              :page-sizes="[25, 50, 100]"
              :page-size="pagesSize"
              :total="departmentPointsTableList.length"
              small
              layout="total,prev, pager, next"
            ></el-pagination>
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
                  placeholder="所有"
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
                  placeholder="所有"
                  clearable
                  type="text"
                  :debounce="0"
                  :fetch-suggestions="queryDepartment"
                  @select="handleSelectScen"
                  style="width: 150px; margin-right:10px"
                ></el-autocomplete>
              </el-form-item>
              <el-form-item label="员工">
                <el-select
                  v-model="empForm.empName"
                  multiple
                  filterable
                  allow-create
                  default-first-option
                  placeholder="所有"
                >
                  <el-option v-for="item in empOptions" :key="item.empOptions" :value="item.value"></el-option>
                </el-select>
              </el-form-item>
            </div>
            <!-- 查询 -->
            <div class="seach fr">
              <el-form-item style="float: none;">
                <el-button type="primary" @click="empSeachBtn">查询</el-button>
              </el-form-item>
            </div>

            <!-- 结束日期 -->
            <div class="endTime fr">
              <el-form-item label="结束日期">
                <el-date-picker
                  v-model="empForm.empEndTime"
                  type="datetime"
                  format="yyyy-MM-dd HH:mm:ss"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  clearable
                ></el-date-picker>
              </el-form-item>
            </div>
            <!-- 开始日期 -->
            <div class="startTime fr">
              <el-form-item label="开始日期">
                <el-date-picker
                  v-model="empForm.empStartTime"
                  type="datetime"
                  format="yyyy-MM-dd HH:mm:ss"
                  value-format="yyyy-MM-dd HH:mm:ss"
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
          <el-col :span="5" class="comLang5">
            <div class="title">员工KPI贡献</div>
            <div class="menu">
              <div class="dior">
                <el-radio v-model="radio111" label="111" @change="change1">净贡献</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio111" label="222" @change="change2">加分贡献</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio111" label="333" @change="change3">减分贡献</el-radio>
              </div>
            </div>
            <div id="main3" style="width: 100%; height:340px"></div>
          </el-col>
          <!-- 员工kpi排名数据 -->
          <el-col :span="6" class="comLang">
            <div class="title">KPI排名</div>
            <div class="menu2">
              <div class="dior">
                <el-radio v-model="radio222" label="444" @change="empAvgchange1">总KPI</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio222" label="555" @change="empAvgchange2">平均KPI</el-radio>
              </div>
            </div>
            <el-table
              :data="employeesKpiTableList.slice((empKpiNum-1)*empPagesSize,empKpiNum*empPagesSize)"
              class="tableBox"
              :border="false"
            >
              <el-table-column type="index" label align="center" width="40" :index="table_index7"></el-table-column>
              <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
              <el-table-column prop="employeeName" width="80px" label="员工" align="center"></el-table-column>
              <el-table-column label="总KPI" prop="KPICal" align="center">
                <!-- 循环遍历显示百分百数值 -->
                <template slot-scope="{ row }">
                  <el-progress
                    :percentage="row.KPI/10"
                    :stroke-width="18"
                    color="#7BA5F4"
                    :show-text="false"
                  ></el-progress>
                  <p class="beiJinNum">{{ row.KPI }}</p>
                </template>
              </el-table-column>
              <el-table-column prop="KPILinkRatio" label="环比" align="center">
                <template slot-scope="{ row }">
                  <div v-if="row.KPILinkRatio.slice(0,row.KPILinkRatio.length-1) > 0">
                    <img src="@/assets/up.png" class="sequentialImg" />
                    <p class="sequentialNum">{{ row.KPILinkRatio }}</p>
                  </div>
                  <div v-else>
                    <img src="@/assets/down.png" class="sequentialImg" />
                    <p class="sequentialNumRed">{{ row.KPILinkRatio }}</p>
                  </div>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
              class="pagFen"
              @current-change="empKpiChange"
              :current-page.sync="empKpiNumPage"
              :page-sizes="[25, 50, 100]"
              :page-size="empPagesSize"
              :total="employeesKpiTableList.length"
              small
              layout="total,prev, pager, next"
            ></el-pagination>
          </el-col>
          <!-- 员工kpi加分数据 -->
          <el-col :span="6" class="comLang">
            <div class="title">加分排名</div>
            <div class="menu2">
              <div class="dior">
                <el-radio v-model="radio333" label="666" @change="empSumBonus">总加分</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio333" label="777" @change="empAvgBonus">平均加分</el-radio>
              </div>
            </div>
            <el-table
              :data="employeesBonusTableList.slice((empBonusNum-1)*empPagesSize,empBonusNum*empPagesSize)"
              class="tableBox"
              :border="false"
            >
              <el-table-column type="index" label align="center" width="30" :index="table_index8"></el-table-column>
              <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
              <el-table-column prop="employeeName" width="80px" label="员工" align="center"></el-table-column>
              <el-table-column label="总KPI" prop="KPICal" align="center">
                <!-- 循环遍历显示百分百数值 -->
                <template slot-scope="{ row }">
                  <el-progress
                    :percentage="row.KPIjiafen/10"
                    :stroke-width="18"
                    color="#7BA5F4"
                    :show-text="false"
                  ></el-progress>
                  <p class="beiJinNum">{{ row.KPIjiafen }}</p>
                </template>
              </el-table-column>
              <el-table-column prop="KPIjiafen" label="环比" align="center">
                <template slot-scope="{ row }">
                  <div v-if="row.KPILinkRatioJIAFEN.slice(0,row.KPILinkRatioJIAFEN.length-1) > 0">
                    <img src="@/assets/up.png" class="sequentialImg" />
                    <p class="sequentialNum">{{ row.KPILinkRatioJIAFEN }}</p>
                  </div>
                  <div v-else>
                    <img src="@/assets/down.png" class="sequentialImg" />
                    <p class="sequentialNumRed">{{ row.KPILinkRatioJIAFEN }}</p>
                  </div>
                </template>
              </el-table-column>
            </el-table>
            <el-pagination
              class="pagFen"
              @current-change="empBonusChange"
              :current-page.sync="empBonusNumPage"
              :page-sizes="[25, 50, 100]"
              :page-size="empPagesSize"
              :total="employeesBonusTableList.length"
              small
              layout="total,prev, pager, next"
            ></el-pagination>
          </el-col>
          <!-- 员工kpi减分数据 -->
          <el-col :span="6" class="comLang">
            <div class="title">减分排名</div>
            <div class="menu2">
              <div class="dior">
                <el-radio v-model="radio444" label="888" @change="empSumDemerit">总减分</el-radio>
              </div>
              <div class="dior">
                <el-radio v-model="radio444" label="999" @change="empAvgDemerit">平均减分</el-radio>
              </div>
            </div>
            <el-table
              :data="employeesPointsTableList.slice((empPointsNum-1)*empPagesSize,empPointsNum*empPagesSize)"
              class="tableBox"
              :border="false"
            >
              <el-table-column type="index" label align="center" width="30" :index="table_index9"></el-table-column>
              <!-- <el-table-column prop="serial" width="30px"></el-table-column> -->
              <el-table-column prop="employeeName" width="80px" label="员工" align="center"></el-table-column>
              <el-table-column label="总KPI" prop="KPICal" align="center">
                <!-- 循环遍历显示百分百数值 -->
                <template slot-scope="{ row }">
                  <el-progress
                    :percentage="row.KPIjianfen"
                    :stroke-width="18"
                    color="#7BA5F4"
                    :show-text="false"
                  ></el-progress>
                  <p class="beiJinNum">{{ row.KPIjianfen }}</p>
                </template>
              </el-table-column>
              <el-table-column prop="KPIjianfen" label="环比" align="center">
                <template slot-scope="{ row }">
                  <div v-if="row.KPILinkRatioJIANFEN.slice(0,row.KPILinkRatioJIANFEN.length-1) > 0">
                    <img src="@/assets/up.png" class="sequentialImg" />
                    <p class="sequentialNum">{{ row.KPILinkRatioJIANFEN }}</p>
                  </div>
                  <div v-else>
                    <img src="@/assets/down.png" class="sequentialImg" />
                    <p class="sequentialNumRed">{{ row.KPILinkRatioJIANFEN }}</p>
                  </div>
                </template>
              </el-table-column>
            </el-table>

            <el-pagination
              class="pagFen"
              @current-change="empPointsChange"
              :current-page.sync="empPointNumPage"
              :page-sizes="[25, 50, 100]"
              :page-size="empPagesSize"
              :total="employeesPointsTableList.length"
              small
              layout="total,prev, pager, next"
            ></el-pagination>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
import {
  // 1、员工KPI排名总查询接口
  empSeachAll,
  // 2、员工KPI贡献接口
  employeKpiPie,
  // 3、员工kpi排名
  employeKpiRank,
  // 4、员工kpi加分排名
  empRankSum,
  // 5、员工kpi减分排名
  empMinusRankSum,

  // 6、部门KPI排名总查询接口
  depSeachAll,
  // 7、部门KPI贡献接口
  deployeKpiPie,
  // 8、部门kpi排名
  deployeKpiRank,
  // 9、部门kpi加分排名
  depRankSum,
  // 10、部门kpi减分排名
  depMinusRankSum,

  // 11、公司KPI排名总查询接口
  comSeachAll,
  // 12、公司KPI贡献接口
  comloyeKpiPie,
  // 13、公司kpi排名
  comloyeKpiRank,
  // 14、公司kpi加分、减分排名
  comRankSum,
  // 15、公司kpi加分、减分排名
  comMinusRankSum,

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
      options: [],
      empOptions: [],
      value: [],
      empVlue: [],
      empListVlaue: [],
      // 饼图所有数据数组
      // 公司默认值
      // 部门默认值
      depDefault: "",
      // 员工默认值
      infoDefault: "",
      // 分页器数据
      // pagesNum: 1, // 公司KPI排名
      comKpiNum: 1, // 公司KPI排名
      comKpiPage: 1, // 分页默认第一页高亮
      comBonusNum: 1, // 公司加分排名
      comBonusPage: 1, // 分页默认第一页高亮
      comPointsNum: 1, // 公司减分排名
      comPointPage: 1, // 分页默认第一页高亮

      depKpiNum: 1, // 部门KPI排名
      depKpiPage: 1, // 分页默认第一页高亮
      depBonusNum: 1, // 部门加分排名
      depBonusPage: 1, // 分页默认第一页高亮
      depPointsNum: 1, // 部门减分排名
      depPointsPage: 1, // 分页默认第一页高亮

      empKpiNum: 1, // 员工KPI排名
      empKpiNumPage: 1, // 分页默认第一页高亮
      empBonusNum: 1, // 员工加分排名
      empBonusNumPage: 1, // 分页默认第一页高亮
      empPointsNum: 1, // 员工减分排名
      empPointNumPage: 1, // 分页默认第一页高亮
      kpiContributionFlag: "",
      avgKPIFlag: "",

      pagesSize: 5, // 每页条数
      empPagesSize: 6,
      total: 10, // 总页数
      // 公司KPI排名表单数据
      comForm: {
        // 公司KPI排名数据
        comCompany: "",
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
      // 公司刷新数据
      comSumData: [],

      // 部门KPI排名
      departmentKpiTableList: [],
      // 部门加分排名
      departmentBonusTableList: [],
      // 部门减分排名
      departmentPointsTableList: [],
      // 部门刷新数据
      depSumData: [],

      // 员工KPI排名
      employeesKpiTableList: [],
      // 员工加分排名
      employeesBonusTableList: [],
      // 员工减分排名
      employeesPointsTableList: [],
      // 员工刷新数据
      empSumData: []
    };
  },
  async created() {
    // 默认一个月的日期填充
    this.getSumTimes();
    this.Data();
    this.queryDepartment("");
    this.queryName("");
    // 公司饼图
    this.get_comSumData();
    // 公司kpi排名
    this.getComKpi();
    // 公司加分排名
    this.getComSerial();
    // 公司减分排名
    this.getMinusComSerial();

    // 部门饼图
    this.get_depSumData();
    // 部门kpi排名
    this.getDepKpi();
    // 部门加分排名
    this.getDepSerial();
    // 部门减分排名
    this.getDepMinusSerial();

    // 员工饼图
    this.get_empSumData();
    // 员工kpi排名
    this.getEmployeKpiRank();
    // 员工加分排名
    this.getEpmSerial();
    // 员工减分排名
    this.getEpmMinusSerial();

    // await this.empButtonSeach()
    // await this.depButtonSeach()
    // await this.comAloneData()
    // await this.empAloneData()
  },
  methods: {
    // 公司KPI贡献——净贡献
    comChange1() {
      this.get_comSumData(1);
    },
    // 公司KPI贡献——加分贡献
    comChange2() {
      this.get_comSumData(2);
    },
    // 公司KPI贡献——减分贡献
    comChange3() {
      this.get_comDemeritPie(3);
    },
    // 公司KPI排名(kpi排名)——总KPI
    comAvgchange1() {
      this.getComKpi(0);

      this.comKpiNum = 1; // 排序后返回第一页
      this.comKpiPage = 1;
    },
    // 公司KPI排名(kpi排名)——平均KPI
    comAvgchange2() {
      this.getComKpi(1);

      this.comKpiNum = 1; // 排序后返回第一页
      this.comKpiPage = 1;
    },
    // 公司KPI排名(加分排名)——总加分
    comSumBonus() {
      this.getComSerial(0);

      this.comBonusNum = 1; // 排序后返回第一页
      this.comBonusPage = 1;
    },
    // 公司KPI排名(加分排名)——平均加分
    comAvgBonus() {
      this.getComSerial(1);

      this.comBonusNum = 1; // 排序后返回第一页
      this.comBonusPage = 1;
    },
    // 公司KPI排名(减分排名)——总减分
    comSumDemerit() {
      this.getMinusComSerial(0);

      this.comPointsNum = 1; // 排序后返回第一页
      this.comPointPage = 1;
    },
    // 公司KPI排名(减分排名)——平均减分
    comAvgDemerit() {
      this.getMinusComSerial(1);

      this.comPointsNum = 1; // 排序后返回第一页
      this.comPointPage = 1;
    },

    // 部门KPI贡献——净贡献
    depChange1() {
      this.get_depScoreData(1);
    },
    // 部门KPI贡献——加分贡献
    depChange2() {
      this.get_depBonusData(2);
    },
    // 部门KPI贡献——减分贡献
    depChange3() {
      this.get_depMinusData(3);
    },
    // 部门KPI排名——总KPI
    depAvgchange1() {
      this.getDepKpi(0);
      this.depKpiNum = 1; // 排序后返回第一页
      this.depKpiPage = 1;
    },
    // 部门KPI排名——平均KPI
    depAvgchange2() {
      this.getDepKpi(1);
      this.depKpiNum = 1; // 排序后返回第一页
      this.depKpiPage = 1;
    },
    // 部门KPI排名(加分排名)——总加分
    depSumBonus() {
      this.getDepSerial(0);

      this.depBonusNum = 1; // 排序后返回第一页
      this.depBonusPage = 1;
    },
    // 部门KPI排名(加分排名)——平均加分
    depAvgBonus() {
      this.getDepSerial(1);

      this.depBonusNum = 1; // 排序后返回第一页
      this.depBonusPage = 1;
    },
    // 部门KPI排名(减分排名)——总减分
    depSumDemerit() {
      this.getDepMinusSerial(0);

      this.depPointsNum = 1; // 排序后返回第一页
      this.depPointsPage = 1;
    },
    // 部门KPI排名(减分排名)——平均减分
    depAvgDemerit() {
      this.getDepMinusSerial(1);

      this.depPointsNum = 1; // 排序后返回第一页
      this.depPointsPage = 1;
    },

    // 员工KPI贡献——净贡献
    change1() {
      this.get_empScorePie(1);
    },
    // 员工KPI贡献——加分贡献
    change2() {
      this.get_empjiaFenPie(2);
    },
    // 员工KPI贡献——减分贡献
    change3() {
      this.get_empDemeritPie(3);
    },
    // 员工KPI排名——总KPI
    empAvgchange1() {
      this.getEmployeSumKpiRank(0);
      this.empKpiNum = 1; // 排序后返回第一页
      this.empKpiNumPage = 1;
    },
    // 员工KPI排名——平均KPI
    empAvgchange2() {
      this.getEmployeAvgKpiRank(1);
      this.empKpiNum = 1; // 排序后返回第一页
      this.empKpiNumPage = 1;
    },
    // 员工KPI排名(加分排名)——总加分
    empSumBonus() {
      this.getEpmSumSerial(0);

      this.empBonusNum = 1; // 排序后返回第一页
      this.empBonusNumPage = 1;
    },
    // 员工KPI排名(加分排名)——平均加分
    empAvgBonus() {
      this.getEpmAvgSerial(1);
      this.empBonusNum = 1; // 排序后返回第一页
      this.empBonusNumPage = 1;
    },
    // 员工KPI排名(减分排名)——总加分
    empSumDemerit() {
      this.getEpmSumMinusSerial(0);
      this.empPointsNum = 1; // 排序后返回第一页
      this.empPointNumPage = 1;
    },
    // 员工KPI排名(减分排名)——平均减分
    empAvgDemerit() {
      this.getEpmAvgMinusSerial(1);
      this.empPointsNum = 1; // 排序后返回第一页
      this.empPointNumPage = 1;
    },
    // 随机颜色
    randColor(item, index) {
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
        "blue"
      ];
      // const color = Math.floor(Math.random() * Arr.length + 1)-1;
      const color = Arr[index];
      return color;
    },
    // 表格分页序号
    table_index(index) {
      return (this.comKpiNum - 1) * this.pagesSize + index + 1;
    },
    table_index2(index) {
      return (this.comBonusNum - 1) * this.pagesSize + index + 1;
    },
    table_index3(index) {
      return (this.comPointsNum - 1) * this.pagesSize + index + 1;
    },
    table_index4(index) {
      return (this.depKpiNum - 1) * this.pagesSize + index + 1;
    },
    table_index5(index) {
      return (this.depBonusNum - 1) * this.pagesSize + index + 1;
    },
    table_index6(index) {
      return (this.depPointsNum - 1) * this.pagesSize + index + 1;
    },
    table_index7(index) {
      return (this.empKpiNum - 1) * this.empPagesSize + index + 1;
    },
    table_index8(index) {
      return (this.empBonusNum - 1) * this.empPagesSize + index + 1;
    },
    table_index9(index) {
      return (this.empPointsNum - 1) * this.empPagesSize + index + 1;
    },

    // 公司KPI排名切换页
    comKpiChange(val) {
      this.comKpiNum = val;
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
        this.options = list;
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
        this.empOptions = list;
        callback(list);
      });
    },
    handleSelectScen(item) {},
    // ---------------------------------------------

    // ---------------------------------------------
    // 提取公司减分饼图函数
    async get_comDemeritPie(index = 1) {
      const value = {
        actionBeginTime: this.comForm.comStartTime,
        actionEndTime: this.comForm.comEndTime,
        company: this.comForm.comCompany,
        kpiContributionFlag: index
      };
      const { data } = await comloyeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.map((item, index) => {
        const comRandColor = this.randColor(item, index);
        return {
          value: -item.KPI,
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
              color: comRandColor
            }
          }
        };
      });
      this.companyPieFunction(newArr, allKPI);
    },
    // 提取公司公共饼图函数
    async get_comSumData(index = 1) {
      const value = {
        actionBeginTime: this.comForm.comStartTime,
        actionEndTime: this.comForm.comEndTime,
        company: this.comForm.comCompany,
        kpiContributionFlag: index
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
              color: comRandColor
            }
          }
        };
      });
      this.companyPieFunction(newArr, allKPI);
    },
    // 公司饼图函数
    companyPieFunction(arr, allKPI) {
      let myChart1 = this.$echarts.init(document.getElementById("main1"));
      this.initHeight1 = document.getElementById("card1").offsetHeight;
      this.initWidth1 = document.getElementById("card1").offsetWidth;
      const option1 = {
        animation: false,
        title: {
          text: "公司KPI",
          subtext: allKPI,
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
          // orient: "horizontal",
          x: "center",
          top: "195",
          itemWidth: 12,
          itemHeight: 12,
          itemGap: 20,
          left: "4%",
          height: 80,
          // 富文本形式设置宽度
          formatter: "{a|{name}}",
          textStyle: {
            rich: {
              a: {
                color: "#ccc",
                fontSize: 12,
                width: 100,
                height: 10
                // padding: [0, 0, 0, 1]
              }
            }
          }
        },
        // 鼠标放上去有提示
        tooltip: {
          show: true,
          trigger: "item",
          formatter: "{b}：{c}"
          // formatter: '{b}：{c}个<br/>占比：({d}%)'
        },
        series: [
          {
            type: "pie",
            stillShowZeroSum: true,
            radius: ["60%", "41%"],
            center: ["50%", "35%"],
            // 饼图轮廓阴影
            itemStyle: {
              normal: {
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
            data: arr
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
    // 公司kpi排名
    async getComKpi(index = 0) {
      const value = {
        actionBeginTime: this.comForm.comStartTime,
        actionEndTime: this.comForm.comEndTime,
        company: this.comForm.comCompany,
        avgKPIFlag: index
      };
      try {
        const { data } = await comloyeKpiRank(value);
        const { kpiRankingList } = data;
        this.companyKpiTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 公司加分排名
    async getComSerial(index = 0) {
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.comForm.comStartTime,
        actionEndTime: this.comForm.comEndTime,
        company: this.comForm.comCompany,
        avgKPIFlag: index
      };
      try {
        const { data } = await comRankSum(value);
        // 解构
        const { kpiRankingList } = data;
        this.companyBonusTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 公司减分排名
    async getMinusComSerial(index = 0) {
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.comForm.comStartTime,
        actionEndTime: this.comForm.comEndTime,
        company: this.comForm.comCompany,
        avgKPIFlag: index
      };
      try {
        const { data } = await comMinusRankSum(value);
        // 解构
        const { kpiRankingList } = data;
        this.companyPointsTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 公司查询刷新数据
    async comButtonSeach() {
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.comForm.comStartTime,
        actionEndTime: this.comForm.comEndTime,
        company: this.comForm.comCompany,
        employeeGroup: this.comForm.comDepartment,
        pagesNum: this.comKpiNum
      };
      try {
        const { data } = await comSeachAll(value);
        const { allKPI, contributionList, kpiRankingList } = data;
        this.companyKpiTableList = kpiRankingList;
        this.companyBonusTableList = kpiRankingList;
        this.companyPointsTableList = kpiRankingList;
        this.comSumData = contributionList;
        const newArr = contributionList.slice(0, 6).map((item, index) => {
          const comColor = this.randColor(item, index);
          return {
            value: item.KPI,
            name: item.company + "(" + item.KPILinkRatio + ")",
            radius: [0, "50%"],
            hoverAnimation: false,
            textStyle: {
              color: comColor
            },
            itemStyle: {
              normal: {
                color: comColor
              }
            }
          };
        });
        this.companyPieFunction(newArr, allKPI);
      } catch (error) {
        console.log(error);
      }
    },
    // 公司kpi查询按钮
    async comSeachBtn() {
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
      // await this.$refs.form.validate(isValid => {
      //   if (isValid) {
      this.radio1 = "1";
      this.radio2 = "4";
      this.radio3 = "6";
      this.radio4 = "8";
      this.comKpiNum = 1; // 排序后返回第一页
      this.comKpiPage = 1;
      this.comBonusNum = 1; // 排序后返回第一页
      this.comBonusPage = 1;
      this.comPointsNum = 1; // 排序后返回第一页
      this.comPointPage = 1;
      this.comButtonSeach(this.comForm);
      //   } else {
      //   }
      // });
    },
    // -----------------------------------------
    // 提取部门公共饼图函数
    async get_depSumData(index = 1) {
      const value = {
        actionBeginTime: this.depForm.depStartTime,
        actionEndTime: this.depForm.depEndTime,
        employeeGroup: this.depForm.depDepartment,
        company: this.depForm.depCompany,
        kpiContributionFlag: index
      };
      const { data } = await deployeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.slice(0, 6).map((item, index) => {
        const depRandColor = this.randColor(item, index);
        return {
          value: item.KPI,
          name:
            item.employeeGroup +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          // textStyle: {
          //   color: depRandColor
          // },
          itemStyle: {
            normal: {
              color: depRandColor
            }
          }
        };
      });
      this.departmentPieFunction(newArr, allKPI);
    },
    // 提取部门公共饼图函数
    async get_depScoreData(index = 1) {
      const value = {
        actionBeginTime: this.depForm.depStartTime,
        actionEndTime: this.depForm.depEndTime,
        selectField: this.depForm.depDepartment,
        company: this.depForm.depCompany,
        kpiContributionFlag: index
      };
      const { data } = await deployeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.slice(0, 6).map((item, index) => {
        const depRandColor = this.randColor(item, index);
        return {
          value: item.KPI,
          name:
            item.employeeGroup +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          // textStyle: {
          //   color: depRandColor
          // },
          itemStyle: {
            normal: {
              color: depRandColor
            }
          }
        };
      });
      this.departmentPieFunction(newArr, allKPI);
    },
    // 提取部门公共饼图函数
    async get_depBonusData(index = 1) {
      const value = {
        actionBeginTime: this.depForm.depStartTime,
        actionEndTime: this.depForm.depEndTime,
        selectField: this.depForm.depDepartment,
        company: this.depForm.depCompany,
        kpiContributionFlag: index
      };
      const { data } = await deployeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.slice(0, 6).map((item, index) => {
        const depRandColor = this.randColor(item, index);
        return {
          value: item.KPI,
          name:
            item.employeeGroup +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          // textStyle: {
          //   color: depRandColor
          // },
          itemStyle: {
            normal: {
              color: depRandColor
            }
          }
        };
      });
      this.departmentPieFunction(newArr, allKPI);
    },
    // 提取部门减分饼图函数
    async get_depMinusData(index = 1) {
      const value = {
        actionBeginTime: this.depForm.depStartTime,
        actionEndTime: this.depForm.depEndTime,
        selectField: this.depForm.depDepartment,
        company: this.depForm.depCompany,
        kpiContributionFlag: index
      };
      const { data } = await deployeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.slice(0, 6).map((item, index) => {
        const depRandColor = this.randColor(item, index);
        return {
          value: item.KPI,
          name:
            item.employeeGroup +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          // textStyle: {
          //   color: depRandColor
          // },
          itemStyle: {
            normal: {
              color: depRandColor
            }
          }
        };
      });
      this.departmentPieFunction(newArr, allKPI);
    },

    // 提取部门减分饼图函数
    async get_depDemeritPie(index = 1) {
      const value = {
        actionBeginTime: this.depForm.depStartTime,
        actionEndTime: this.depForm.depEndTime,
        employeeGroup: this.depForm.depDepartment,
        company: this.depForm.depCompany,
        kpiContributionFlag: index
      };
      const { data } = await deployeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.slice(0, 6).map((item, index) => {
        const depRandColor = this.randColor(item, index);
        return {
          value: -item.KPI,
          name:
            item.employeeGroup +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          // textStyle: {
          //   color: depRandColor
          // },
          itemStyle: {
            normal: {
              color: depRandColor
            }
          }
        };
      });
      this.departmentPieFunction(newArr, allKPI);
    },
    // 部门饼图函数
    departmentPieFunction(arr, allKPI) {
      let myChart2 = this.$echarts.init(document.getElementById("main2"));
      this.initHeight1 = document.getElementById("card1").offsetHeight;
      this.initWidth1 = document.getElementById("card1").offsetWidth;
      const option2 = {
        animation: false,
        title: {
          text: "部门KPI",
          subtext: allKPI,
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
          // orient: "horizontal",
          x: "center",
          top: "205",
          // 富文本形式设置宽度
          formatter: "{a|{name}}",
          textStyle: {
            rich: {
              a: {
                color: "#ccc",
                fontSize: 12,
                height: 10,
                width: 100,
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
          formatter: "{b}：{c}"
        },
        series: [
          {
            type: "pie",
            stillShowZeroSum: true,
            // 饼图大小
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
            data: arr
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
    // 部门kpi排名
    async getDepKpi(index = 0) {
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        pagesNum: this.depKpiNum,
        avgKPIFlag: index
      };
      try {
        const { data } = await deployeKpiRank(value);
        const { kpiRankingList } = data;
        this.departmentKpiTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 部门加分排名
    async getDepSerial(index = 0) {
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        pagesNum: this.depBonusNum,
        avgKPIFlag: index
      };
      try {
        const { data } = await depRankSum(value);
        // 解构
        const { kpiRankingList } = data;
        this.departmentBonusTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 部门减分排名
    async getDepMinusSerial(index = 0) {
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        pagesNum: this.depPointsNum,
        avgKPIFlag: index
      };
      try {
        const { data } = await depMinusRankSum(value);
        // 解构
        const { kpiRankingList } = data;
        this.departmentPointsTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 部门查询刷新数据
    async depButtonSeach() {
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.depForm.depStartTime,
        actionEndTime: this.depForm.depEndTime,
        company: this.depForm.depCompany,
        selectField: this.depForm.depDepartment,
        pagesNum: this.pagesNum
      };
      try {
        const { data } = await depSeachAll(value);
        const { allKPI, contributionList, kpiRankingList } = data;
        this.departmentKpiTableList = kpiRankingList;
        this.departmentBonusTableList = kpiRankingList;
        this.departmentPointsTableList = kpiRankingList;
        this.depSumData = contributionList;
        const newArr = contributionList.slice(0, 6).map((item, index) => {
          const depColor = this.randColor(item, index);
          return {
            value: item.KPI,
            name: item.employeeGroup + "(" + item.KPILinkRatio + ")",
            radius: [0, "50%"],
            hoverAnimation: false,
            textStyle: {
              color: depColor
            },
            itemStyle: {
              normal: {
                color: depColor
              }
            }
          };
        });
        this.departmentPieFunction(newArr, allKPI);
      } catch (error) {
        console.log(error);
      }
    },
    // 部门kpi查询按钮
    async depSeachBtn() {
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
      // await this.$refs.form.validate(isValid => {
      //   if (isValid) {
      this.radio11 = "11";
      this.radio22 = "44";
      this.radio33 = "66";
      this.radio44 = "88";
      this.depKpiNum = 1; // 排序后返回第一页
      this.depKpiPage = 1;
      this.depBonusNum = 1; // 排序后返回第一页
      this.depBonusPage = 1;
      this.depPointsNum = 1; // 排序后返回第一页
      this.depPointsPage = 1;
      this.depButtonSeach();
      // } else {
      // }
      // });
    },
    // -----------------------------------------
    // 提取员工净分饼图函数
    async get_empScorePie(index = 1) {
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        selectField: this.empForm.empName,

        kpiContributionFlag: index
      };
      const { data } = await employeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.slice(0, 6).map((item, index) => {
        const shuiji = this.randColor(item, index);
        return {
          value: -item.KPI,
          name:
            item.employeeName +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          textStyle: {
            color: shuiji
          },
          itemStyle: {
            normal: {
              color: shuiji
            }
          }
        };
      });
      this.employeesPieFunction(newArr, allKPI);
    },
    // 提取员工减分饼图函数
    async get_empDemeritPie(index = 1) {
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        selectField: this.empForm.empName,

        kpiContributionFlag: index
      };
      const { data } = await employeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.slice(0, 6).map((item, index) => {
        const shuiji = this.randColor(item, index);
        return {
          value: -item.KPI,
          name:
            item.employeeName +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          textStyle: {
            color: shuiji
          },
          itemStyle: {
            normal: {
              color: shuiji
            }
          }
        };
      });
      this.employeesPieFunction(newArr, allKPI);
    },
    // 提取员工加分饼图函数
    async get_empjiaFenPie(index = 2) {
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        selectField: this.empForm.empName,

        kpiContributionFlag: index
      };
      const { data } = await employeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.slice(0, 6).map((item, index) => {
        const shuiji = this.randColor(item, index);
        return {
          value: -item.KPI,
          name:
            item.employeeName +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          textStyle: {
            color: shuiji
          },
          itemStyle: {
            normal: {
              color: shuiji
            }
          }
        };
      });
      this.employeesPieFunction(newArr, allKPI);
    },

    // 提取员工公共饼图函数
    async get_empSumData(index = 1) {
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        employeeName: this.empForm.empName,

        kpiContributionFlag: index
      };
      const { data } = await employeKpiPie(value);
      const { contributionList, allKPI } = data;
      const newArr = contributionList.slice(0, 6).map((item, index) => {
        const shuiji = this.randColor(item, index);
        return {
          value: item.KPI,
          name:
            item.employeeName +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          textStyle: {
            color: shuiji
          },
          itemStyle: {
            normal: {
              color: shuiji
            }
          }
        };
      });
      this.employeesPieFunction(newArr, allKPI);
    },
    // 员工饼图函数
    employeesPieFunction(arr, allKPI) {
      let myChart3 = this.$echarts.init(document.getElementById("main3"));
      this.initHeight1 = document.getElementById("card1").offsetHeight;
      this.initWidth1 = document.getElementById("card1").offsetWidth;
      const option3 = {
        animation: false,
        title: {
          text: "员工KPI",
          subtext: allKPI,
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
          left: "15%",
          align: "left",
          height: 80
        },
        // 鼠标放上去有提示
        tooltip: {
          show: true,
          trigger: "item",
          formatter: "{b}：{c}"
        },
        series: [
          {
            type: "pie",
            stillShowZeroSum: true,
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
            data: arr
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
    // 员工kpi排名
    async getEmployeKpiRank(index = 0) {
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        selectField: this.empForm.empName.split(","),
        pagesNum: this.empKpiNum,
        avgKPIFlag: index
      };
      try {
        const { data } = await employeKpiRank(value);
        const { kpiRankingList } = data;
        this.employeesKpiTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 员工总排名
    async getEmployeSumKpiRank(index = 0) {
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        selectField: this.empForm.empName,
        pagesNum: this.empKpiNum,
        avgKPIFlag: index
      };
      try {
        const { data } = await employeKpiRank(value);
        const { kpiRankingList } = data;
        this.employeesKpiTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 员工平均排名
    async getEmployeAvgKpiRank(index = 0) {
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        selectField: this.empForm.empName,
        pagesNum: this.empKpiNum,
        avgKPIFlag: index
      };
      try {
        const { data } = await employeKpiRank(value);
        const { kpiRankingList } = data;
        this.employeesKpiTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 员工加分排名
    async getEpmSerial(index = 0) {
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        selectField: this.empForm.empName.split(","),
        pagesNum: this.empBonusNum,
        avgKPIFlag: index
      };
      try {
        const { data } = await empRankSum(value);
        const { kpiRankingList } = data;
        this.employeesBonusTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 员工总加分排名
    async getEpmSumSerial(index = 0) {
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        selectField: this.empForm.empName,
        pagesNum: this.empBonusNum,
        avgKPIFlag: index
      };
      try {
        const { data } = await empRankSum(value);
        const { kpiRankingList } = data;
        this.employeesBonusTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 员工平均加分排名
    async getEpmAvgSerial(index = 0) {
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        selectField: this.empForm.empName,
        pagesNum: this.empBonusNum,
        avgKPIFlag: index
      };
      try {
        const { data } = await empRankSum(value);
        const { kpiRankingList } = data;
        this.employeesBonusTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 员工减分排名
    async getEpmMinusSerial(index = 0) {
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        selectField: this.empForm.empName.split(","),
        pagesNum: this.empPointsNum,
        avgKPIFlag: index
      };
      try {
        const { data } = await empMinusRankSum(value);
        const { kpiRankingList } = data;
        this.employeesPointsTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 员工总减分排名
    async getEpmSumMinusSerial(index = 0) {
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        selectField: this.empForm.empName,
        pagesNum: this.empPointsNum,
        avgKPIFlag: index
      };
      try {
        const { data } = await empMinusRankSum(value);
        const { kpiRankingList } = data;
        this.employeesPointsTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 员工平均减分排名
    async getEpmAvgMinusSerial(index = 0) {
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        emploselectFieldyeeName: this.empForm.empName,
        pagesNum: this.empPointsNum,
        avgKPIFlag: index
      };
      try {
        const { data } = await empMinusRankSum(value);
        const { kpiRankingList } = data;
        this.employeesPointsTableList = kpiRankingList;
      } catch (error) {
        console.log(error);
      }
    },
    // 员工查询刷新数据
    async empButtonSeach() {
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company: this.empForm.empCompany,
        employeeGroup: this.empForm.empDepartment,
        selectField: this.empForm.empName,
        // kpiContributionFlag: this.kpiContributionFlag,
        // avgKPIFlag:this.avgKPIFlag,
        pagesNum: this.pagesNum
      };
      try {
        const { data } = await empSeachAll(value);
        const { allKPI, contributionList, kpiRankingList } = data;

        this.employeesKpiTableList = kpiRankingList;
        this.employeesBonusTableList = kpiRankingList;
        this.employeesPointsTableList = kpiRankingList;
        this.empSumData = contributionList;
        const newArr = contributionList.slice(0, 6).map((item, index) => {
          const shijieColor = this.randColor(item, index);
          return {
            value: item.KPI,
            name: item.employeeName + "(" + item.KPILinkRatio + ")",
            radius: [0, "50%"],
            hoverAnimation: false,
            textStyle: {
              color: shijieColor
            },
            itemStyle: {
              normal: {
                color: shijieColor
              }
            }
          };
        });
        this.employeesPieFunction(newArr, allKPI);
      } catch (error) {
        console.log(error);
      }
    },
    // 员工kpi查询按钮
    async empSeachBtn() {
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
      this.radio111 = "111";
      this.radio222 = "444";
      this.radio333 = "666";
      this.radio444 = "888";
      this.empKpiNum = 1; // 排序后返回第一页
      this.empKpiNumPage = 1;
      this.empBonusNum = 1; // 排序后返回第一页
      this.empBonusNumPage = 1;
      this.empPointsNum = 1;
      this.empPointNumPage = 1;
      this.empButtonSeach();
    },

    // 单独发公司请求接口
    async comAloneData() {
      const { company } = this.companyKpiTableList[1];
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        company,
        pagesNum: this.pagesNum
      };
      const { data } = await empSeachAll(value);
      const { allKPI, contributionList, kpiRankingList } = data;
      this.companyKpiTableList = kpiRankingList;
      this.companyBonusTableList = kpiRankingList;
      this.companyPointsTableList = kpiRankingList;
      const newArr = contributionList.map(item => {
        const comRandColor = this.randColor();
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
              color: comRandColor
            }
          }
        };
      });
      this.companyPieFunction(newArr, allKPI);
    },
    // ------------------------------------------------------------
    // 单独发部门请求接口
    async empAloneData() {
      const { employeeGroup } = this.departmentKpiTableList[1];
      console.log("测试", employeeGroup);
      // 传默认时间到后台
      const value = {
        actionBeginTime: this.empForm.empStartTime,
        actionEndTime: this.empForm.empEndTime,
        employeeGroup,
        pagesNum: this.pagesNum
      };
      const { data } = await depSeachAll(value);
      const { allKPI, contributionList, kpiRankingList } = data;
      this.departmentKpiTableList = kpiRankingList;
      this.departmentBonusTableList = kpiRankingList;
      this.departmentPointsTableList = kpiRankingList;
      const newArr = contributionList.map(item => {
        const comRandColor = this.randColor();
        return {
          value: item.KPI,
          name:
            item.employeeGroup +
            "(" +
            ((item.KPI / allKPI) * 100).toFixed(1) +
            "%" +
            ")",
          radius: [0, "50%"],
          hoverAnimation: false,
          itemStyle: {
            normal: {
              color: comRandColor
            }
          }
        };
      });
      this.departmentPieFunction(newArr, allKPI);
    },
    // 获取当前日期
    getSumTimes() {
      // 获取当前的日期， 变量名为tiems
      this.nowData = new Date();
      const d = this.nowData;
      let month =
        d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
      let day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
      let hours = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
      let min = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
      let sec = d.getSeconds() < 10 ? "0" + d.getSeconds() : d.getSeconds();
      let times =
        d.getFullYear() +
        "-" +
        month +
        "-" +
        day +
        " " +
        hours +
        ":" +
        min +
        ":" +
        sec;
      this.times = times;

      // 获取当前的日期往前推一个月， 变量名为Dtimes
      let dTimes = new Date(new Date(this.nowData).getTime() - 86400000 * 30);
      let Dmonth =
        dTimes.getMonth() + 1 < 10
          ? "0" + (dTimes.getMonth() + 1)
          : dTimes.getMonth() + 1;
      let Dday =
        dTimes.getDate() < 10 ? "0" + dTimes.getDate() : dTimes.getDate();
      let Dhours =
        dTimes.getHours() < 10 ? "0" + dTimes.getHours() : dTimes.getHours();
      let Dmin =
        dTimes.getMinutes() < 10
          ? "0" + dTimes.getMinutes()
          : dTimes.getMinutes();
      let Dsec =
        dTimes.getSeconds() < 10
          ? "0" + dTimes.getSeconds()
          : dTimes.getSeconds();
      let Dtimes =
        dTimes.getFullYear() +
        "-" +
        Dmonth +
        "-" +
        Dday +
        " " +
        Dhours +
        ":" +
        Dmin +
        ":" +
        Dsec;
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
      let hours = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
      let min = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
      let sec = d.getSeconds() < 10 ? "0" + d.getSeconds() : d.getSeconds();

      let times = d.getFullYear() + "-" + month + "-" + day;
      this.nowData = times;
      this.zheXianDate = times;
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
  mounted() {}
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
.fl {
  float: left;
}
.fr {
  float: right;
}
// kpi排名
.kpiRank {
  // 公司
  .company {
    width: 100%;
    height: 470px;
    border: 1px solid #2f69a0;
    border-radius: 5px;
    margin-bottom: 30px;
    margin-top: 10px;
    padding: 0 10px;
    box-sizing: border-box;
    .one {
      color: #fff;
      font-size: 28px;
      text-align: center;
      margin-top: 10px;
    }
    .two {
      color: #fff;
      font-size: 12px;
      text-align: center;
    }
    .three {
      height: 40px;
      line-height: 40px;
      margin-bottom: 10px;
      .seach {
        margin-left: 10px;
        ::v-deep .el-button--primary {
          height: 30px;
          line-height: 0;
        }
      }
      .endTime {
        margin-left: 10px;
        letter-spacing: 3px;
        ::v-deep .el-form-item__label {
          color: white;
        }
        ::v-deep .el-input__inner {
          height: 30px;
          line-height: 30px;
          box-sizing: border-box;
          background-color: #083760;
          border: 1px solid #00639e;
          color: #fff;
        }
        ::v-deep .el-form-item {
          margin-bottom: 0px;
        }
      }
      .startTime {
        margin-left: 10px;
        letter-spacing: 3px;
        ::v-deep .el-form-item__label {
          color: white;
        }
        ::v-deep .el-input__inner {
          height: 30px;
          line-height: 30px;
          box-sizing: border-box;
          background-color: #083760;
          border: 1px solid #00639e;
          color: #fff;
        }
        ::v-deep .el-form-item {
          margin-bottom: 0px;
        }
      }
    }
    .four {
      width: 100%;
      padding-left: 20px;
      box-sizing: border-box;
      .comLang5 {
        border-radius: 3px;
        border: 1px solid #2f69a0;
        height: 340px;
        position: relative;
        background: #1d4b7f;
        overflow: hidden;
        width: 310px;
        margin-right: 10px;
        .title {
          height: 30px;
          line-height: 30px;
          color: #fff;
          font-size: 20px;
          text-align: center;
        }
        .menu {
          width: 100%;
          .dior {
            float: left;
            width: 33.33%;
            text-align: center;
            ::v-deep .el-radio {
              color: white;
            }
          }
        }
        #card1 {
          height: 290px;
          top: -40px !important;
          border: 0px;
          background-color: #1d4b7f;
        }
      }
      .comLang {
        border-radius: 3px;
        border: 1px solid #2f69a0;
        height: 340px;
        position: relative;
        background: #1d4b7f;
        overflow: hidden;
        margin: 0 10px;
        width: 413px;
        ::v-deep .el-table tbody tr:hover > td {
          background-color: red;
        }
        .title {
          height: 30px;
          line-height: 30px;
          color: #fff;
          font-size: 20px;
          text-align: center;
        }
        .menu2 {
          width: 45%;
          text-align: center;
          background-color: #345d8c;
          height: 30px;
          line-height: 27px;
          margin: 10px;
          margin-left: 28%;
          .dior {
            float: left;
            width: 50%;
            text-align: center;
            ::v-deep .el-radio {
              color: white;
            }
          }
        }
        ::v-deep .el-table th {
          background-color: #1d4b7f;
        }
        ::v-deep .el-table tr {
          background-color: #1d4b7f;
        }
        ::v-deep .el-table th.is-leaf {
          border-bottom: 0px;
        }
        ::v-deep .el-table td {
          border-bottom: 0px;
        }
        ::v-deep .el-table {
          color: #ccc;
        }
        ::v-deep .el-table thead {
          color: #ccc;
        }
        ::v-deep .el-progress-bar__outer {
          background-color: #1d4b7f;
          border-radius: 0px;
          width: 140px;
        }
        ::v-deep .el-progress-bar__inner {
          border-radius: 0px;
        }
        ::v-deep .el-progress-bar__outer {
          background-color: #1d4b7f;
          border-radius: 0;
          width: 140px;
        }
        ::v-deep .el-table .cell {
          box-sizing: border-box;
          white-space: normal;
          word-break: break-all;
          line-height: 23px;
          padding-left: 0px;
        }
        ::v-deep .el-table::before {
          height: 0px;
        }
        ::v-deep .el-table__row:hover > td {
          background-color: #1d4b7f !important;
          color: white;
        }

        ::v-deep .el-table__row--striped:hover > td {
          background-color: #1d4b7f !important;
          color: white;
        }
        .beiJinNum {
          position: absolute;
          top: 8px;
          left: 120px;
          right: -1px;
        }
        .sequentialNumRed {
          position: absolute;
          top: 8px;
          left: 30px;
          font-size: 12px;
        }
        .sequentialNum {
          position: absolute;
          top: 8px;
          left: 30px;
          font-size: 12px;
        }
        .ComImg {
          margin-top: 5px;
          margin-left: -50px;
        }
        .sequentialImg {
          margin-top: 5px;
          margin-left: -50px;
        }
        .pagFen {
          float: right;
          position: absolute;
          right: 0;
          bottom: 0px;
          z-index: 2;
        }
        ::v-deep .el-pagination button:disabled {
          background-color: #1d4b7f;
          margin-top: 10px;
        }
        ::v-deep .el-pager {
          margin-top: 8px;
        }
        ::v-deep .el-pager li {
          background-color: #1d4b7f;
        }
        ::v-deep .el-pagination .btn-prev {
          margin-top: 10px;
          background-color: #1d4b7f;
        }
      }
    }
  }
  // 部门
  .department {
    width: 100%;
    height: 470px;
    border: 1px solid #2f69a0;
    border-radius: 5px;
    margin-bottom: 30px;
    margin-top: 10px;
    padding: 0 10px;
    box-sizing: border-box;
    .one {
      color: #fff;
      font-size: 28px;
      text-align: center;
      margin-top: 10px;
    }
    .two {
      color: #fff;
      font-size: 12px;
      text-align: center;
    }
    .three {
      height: 40px;
      line-height: 40px;
      margin-bottom: 10px;
      .dropDownSeach {
        float: right;
        height: 40px;
        .dropDownCompany {
          left: 15px;
          position: absolute;
          ::v-deep .el-form-item__label {
            color: white;
          }
          ::v-deep .el-input__inner {
            background-color: #083760;
            color: #fff;
            height: 30px;
            line-height: 30px;
            box-sizing: border-box;
            border: 1px solid #00639e;
          }
        }
        .seach {
          margin-left: 10px;
          ::v-deep .el-button--primary {
            height: 30px;
            line-height: 0;
          }
        }
        .endTime {
          margin-left: 10px;
          letter-spacing: 3px;
          ::v-deep .el-form-item__label {
            color: white;
          }
          ::v-deep .el-input__inner {
            height: 30px;
            line-height: 30px;
            box-sizing: border-box;
            background-color: #083760;
            border: 1px solid #00639e;
            color: #fff;
          }
          ::v-deep .el-form-item {
            margin-bottom: 0px;
          }
        }
        .startTime {
          margin-left: 10px;
          letter-spacing: 3px;
          ::v-deep .el-form-item__label {
            color: white;
          }
          ::v-deep .el-input__inner {
            height: 30px;
            line-height: 30px;
            box-sizing: border-box;
            background-color: #083760;
            border: 1px solid #00639e;
            color: #fff;
          }
          ::v-deep .el-form-item {
            margin-bottom: 0px;
          }
        }
      }
    }
    .four {
      width: 100%;
      padding-left: 20px;
      box-sizing: border-box;
      .comLang5 {
        border-radius: 3px;
        border: 1px solid #2f69a0;
        height: 340px;
        position: relative;
        background: #1d4b7f;
        overflow: hidden;
        width: 310px;
        margin-right: 10px;
        .title {
          height: 30px;
          line-height: 30px;
          color: #fff;
          font-size: 20px;
          text-align: center;
        }
        .menu {
          width: 100%;
          .dior {
            float: left;
            width: 33.33%;
            text-align: center;
            ::v-deep .el-radio {
              color: white;
            }
          }
        }
        #card1 {
          height: 290px;
          top: -40px !important;
          border: 0px;
          background-color: #1d4b7f;
        }
      }
      .comLang {
        border-radius: 3px;
        border: 1px solid #2f69a0;
        height: 340px;
        position: relative;
        background: #1d4b7f;
        overflow: hidden;
        margin: 0 10px;
        width: 413px;
        ::v-deep .el-table tbody tr:hover > td {
          background-color: red;
        }
        .title {
          height: 30px;
          line-height: 30px;
          color: #fff;
          font-size: 20px;
          text-align: center;
        }
        .menu2 {
          width: 45%;
          text-align: center;
          background-color: #345d8c;
          height: 30px;
          line-height: 27px;
          margin: 10px;
          margin-left: 28%;
          .dior {
            float: left;
            width: 50%;
            text-align: center;
            ::v-deep .el-radio {
              color: white;
            }
          }
        }
        ::v-deep .el-table th {
          background-color: #1d4b7f;
        }
        ::v-deep .el-table tr {
          background-color: #1d4b7f;
        }
        ::v-deep .el-table th.is-leaf {
          border-bottom: 0px;
        }
        ::v-deep .el-table td {
          border-bottom: 0px;
        }
        ::v-deep .el-table {
          color: #ccc;
        }
        ::v-deep .el-table thead {
          color: #ccc;
        }
        ::v-deep .el-progress-bar__outer {
          background-color: #1d4b7f;
          border-radius: 0px;
          width: 140px;
        }
        ::v-deep .el-progress-bar__inner {
          border-radius: 0px;
        }
        ::v-deep .el-progress-bar__outer {
          background-color: #1d4b7f;
          border-radius: 0;
          width: 140px;
        }
        ::v-deep .el-table .cell {
          box-sizing: border-box;
          white-space: normal;
          word-break: break-all;
          line-height: 23px;
          padding-left: 0px;
        }
        ::v-deep .el-table::before {
          height: 0px;
        }
        ::v-deep .el-table__row:hover > td {
          background-color: #1d4b7f !important;
          color: white;
        }

        ::v-deep .el-table__row--striped:hover > td {
          background-color: #1d4b7f !important;
          color: white;
        }
        .beiJinNum {
          position: absolute;
          top: 8px;
          right: -54px;
          z-index: 9;
        }
        .sequentialNumRed {
          position: absolute;
          top: 8px;
          left: 80px;
          font-size: 12px;
          z-index: 9;
        }
        .sequentialNum {
          position: absolute;
          top: 8px;
          font-size: 12px;
          z-index: 9;
          left: 78px;
        }
        .ComImg {
          margin-top: 5px;
          margin-left: 5px;
        }
        .sequentialImg {
          margin-top: 5px;
          margin-left: 5px;
        }
        .pagFen {
          float: right;
          position: absolute;
          right: 0;
          bottom: 0px;
          z-index: 2;
        }
        ::v-deep .el-pagination button:disabled {
          background-color: #1d4b7f;
          margin-top: 10px;
        }
        ::v-deep .el-pager {
          margin-top: 8px;
        }
        ::v-deep .el-pager li {
          background-color: #1d4b7f;
        }
        ::v-deep .el-pagination .btn-prev {
          margin-top: 10px;
          background-color: #1d4b7f;
        }
      }
    }
  }
  // 员工
  .employees {
    width: 100%;
    height: 530px;
    border: 1px solid #2f69a0;
    border-radius: 5px;
    margin-bottom: 30px;
    margin-top: 10px;
    padding: 0 10px;
    box-sizing: border-box;
    .one {
      color: #fff;
      font-size: 28px;
      text-align: center;
      margin-top: 10px;
    }
    .two {
      color: #fff;
      font-size: 12px;
      text-align: center;
    }
    .three {
      height: 40px;
      line-height: 40px;
      margin-bottom: 10px;
      .dropDownSeach {
        float: right;
        height: 40px;
        .dropDownCompany {
          left: 15px;
          position: absolute;
          ::v-deep .el-form-item__label {
            color: white;
          }
          ::v-deep .el-input__inner {
            background-color: #083760;
            color: #fff;
            height: 30px;
            line-height: 30px;
            box-sizing: border-box;
            border: 1px solid #00639e;
          }
        }
        .seach {
          margin-left: 10px;
          ::v-deep .el-button--primary {
            height: 30px;
            line-height: 0;
          }
        }
        .endTime {
          margin-left: 10px;
          letter-spacing: 3px;
          ::v-deep .el-form-item__label {
            color: white;
          }
          ::v-deep .el-input__inner {
            height: 30px;
            line-height: 30px;
            box-sizing: border-box;
            background-color: #083760;
            border: 1px solid #00639e;
            color: #fff;
          }
          ::v-deep .el-form-item {
            margin-bottom: 0px;
          }
        }
        .startTime {
          margin-left: 10px;
          letter-spacing: 3px;
          ::v-deep .el-form-item__label {
            color: white;
          }
          ::v-deep .el-input__inner {
            height: 30px;
            line-height: 30px;
            box-sizing: border-box;
            background-color: #083760;
            border: 1px solid #00639e;
            color: #fff;
          }
          ::v-deep .el-form-item {
            margin-bottom: 0px;
          }
        }
      }
    }
    .four {
      width: 100%;
      padding-left: 20px;
      box-sizing: border-box;
      .comLang5 {
        border-radius: 3px;
        border: 1px solid #2f69a0;
        height: 380px;
        position: relative;
        background: #1d4b7f;
        overflow: hidden;
        width: 310px;
        margin-right: 10px;
        .title {
          height: 30px;
          line-height: 30px;
          color: #fff;
          font-size: 20px;
          text-align: center;
        }
        .menu {
          width: 100%;
          .dior {
            float: left;
            width: 33.33%;
            text-align: center;
            ::v-deep .el-radio {
              color: white;
            }
          }
        }
        #card1 {
          height: 290px;
          top: -40px !important;
          border: 0px;
          background-color: #1d4b7f;
        }
      }
      .comLang {
        border-radius: 3px;
        border: 1px solid #2f69a0;
        height: 380px;
        position: relative;
        background: #1d4b7f;
        overflow: hidden;
        margin: 0 10px;
        width: 413px;
        ::v-deep .el-table tbody tr:hover > td {
          background-color: red;
        }
        .title {
          height: 30px;
          line-height: 30px;
          color: #fff;
          font-size: 20px;
          text-align: center;
        }
        .menu2 {
          width: 45%;
          text-align: center;
          background-color: #345d8c;
          height: 30px;
          line-height: 27px;
          margin: 10px;
          margin-left: 28%;
          .dior {
            float: left;
            width: 50%;
            text-align: center;
            ::v-deep .el-radio {
              color: white;
            }
          }
        }
        ::v-deep .el-table th {
          background-color: #1d4b7f;
        }
        ::v-deep .el-table tr {
          background-color: #1d4b7f;
        }
        ::v-deep .el-table th.is-leaf {
          border-bottom: 0px;
        }
        ::v-deep .el-table td {
          border-bottom: 0px;
        }
        ::v-deep .el-table {
          color: #ccc;
        }
        ::v-deep .el-table thead {
          color: #ccc;
        }
        ::v-deep .el-progress-bar__outer {
          background-color: #1d4b7f;
          border-radius: 0px;
          width: 140px;
        }
        ::v-deep .el-progress-bar__inner {
          border-radius: 0px;
        }
        ::v-deep .el-progress-bar__outer {
          background-color: #1d4b7f;
          border-radius: 0;
          width: 140px;
        }
        ::v-deep .el-table .cell {
          box-sizing: border-box;
          white-space: normal;
          word-break: break-all;
          line-height: 23px;
          padding-left: 0px;
        }
        ::v-deep .el-table::before {
          height: 0px;
        }
        ::v-deep .el-table__row:hover > td {
          background-color: #1d4b7f !important;
          color: white;
        }

        ::v-deep .el-table__row--striped:hover > td {
          background-color: #1d4b7f !important;
          color: white;
        }
        .beiJinNum {
          position: absolute;
          top: 8px;
          right: -54px;
          z-index: 9;
        }
        .sequentialNumRed {
          position: absolute;
          top: 8px;
          left: 80px;
          font-size: 12px;
          z-index: 9;
        }
        .sequentialNum {
          position: absolute;
          top: 8px;
          left: 78px;
          font-size: 12px;
          z-index: 9;
        }
        .ComImg {
          margin-top: 5px;
          margin-left: 5px;
        }
        .sequentialImg {
          margin-top: 5px;
          margin-left: 5px;
        }
        .pagFen {
          float: right;
          position: absolute;
          right: 0;
          bottom: 0px;
          z-index: 2;
        }
        ::v-deep .el-pagination button:disabled {
          background-color: #1d4b7f;
          margin-top: 10px;
        }
        ::v-deep .el-pager {
          margin-top: 8px;
        }
        ::v-deep .el-pager li {
          background-color: #1d4b7f;
        }
        ::v-deep .el-pagination .btn-next {
          margin-top: 10px;
          background-color: #1d4b7f;
        }
        ::v-deep .el-pagination .btn-prev {
          margin-top: 10px;
          background-color: #1d4b7f;
        }
        ::v-deep .el-pagination span:not([class*="suffix"]) {
          height: 28px;
        }
      }
    }
  }
}
</style>
