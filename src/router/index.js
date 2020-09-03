import Vue from 'vue'
import VueRouter from 'vue-router'
// 引入公共样式
import '../css/base/base.css'
Vue.use(VueRouter)
// 解决用户重复点击一个路由，产生的bug
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}
// 路由跳转
const routes = [{
    // 跳转到登录页
    path: '/',
    component: () => import('../views/login/Login.vue')
  },
  {
    //  登录后显示的主页
    path: '/users',
    component: () => import('../views/index/Index.vue'),
    children: [{
        // 行为分析——总览
        path: 'overview',
        component: () => import('../views/clientSideMonitor/behaviorAnalysis/overview/overview.vue')
      },
      {
        // 行为分析——KPI分析
        path: 'kplAnalysis',
        name: 'KPISum',
        component: () => import('../views/clientSideMonitor/behaviorAnalysis/kpiAnalysis/kpiAnalysis.vue')
      },
      {
        // 行为分析——信息安全
        path: 'informationSecurity',
        component: () => import('../views/clientSideMonitor/behaviorAnalysis/informationSecurity/informationSecurity.vue')
      },
      {
        // 场景监控——场景频次监控
        path: 'usersFrequency',
        redirect: '/users/usersFrequency/specificSum',
        component: () => import('../views/clientSideMonitor/sceneMonitor/sceneFrequencyMonitor/frequency.vue'),
        children: [{
            // 基于频次
            path: 'specificSum',
            component: () => import('../views/clientSideMonitor/sceneMonitor/sceneFrequencyMonitor/components/specificSum.vue')
          },
          {
            // 基于用户
            path: 'specificUser',
            component: () => import('../views/clientSideMonitor/sceneMonitor/sceneFrequencyMonitor/components/specificUser.vue')
          },
          {
            // 基于客户端
            path: 'specificClient',
            component: () => import('../views/clientSideMonitor/sceneMonitor/sceneFrequencyMonitor/components/specificClient.vue')
          },
          {
            // 基于特定场景（用户）
            path: 'specificContextUser',
            component: () => import('../views/clientSideMonitor/sceneMonitor/sceneFrequencyMonitor/components/specificContextUser.vue')
          },
          {
            // 基于特定场景（客户端）
            path: 'specificContextClient',
            component: () => import('../views/clientSideMonitor/sceneMonitor/sceneFrequencyMonitor/components/specificContextClient.vue')
          }
        ]
      },
      {
        // 场景监控——场景性能监控
        path: 'scenarioFrequency',
        component: () => import('../views/clientSideMonitor/sceneMonitor/scenePerformanceMonitor/scen.vue')
      },
      {
        // 场景监控——操作详情检索
        path: 'infoRetr',
        component: () => import('../views/clientSideMonitor/sceneMonitor/operationDetailsRetrieval/infoRetr.vue')
      },
      {
        // 管理控制台——TC连接信息
        path: 'connectDB',
        component: () => import('../views/administrativeConsole/TCLinkInfo/connectDB.vue')
      },
      {
        // 管理控制台——关于
        path: 'about',
        name: 'about',
        component: () => import('../views/administrativeConsole/about/about.vue')
      },
      {
        // 服务器监控——服务——概况
        path: 'survey',
        name: 'surveySum',
        component: () => import('../views/serverMonitor/1-1serviceFile/survey.vue')
      },
      {
        // 服务器监控——服务——趋势分析
        path: 'trendAnalysisUnit',
        name: 'trendAnalysisUnit',
        component: () => import('../views/serverMonitor/1-2serviceFile/survey.vue')
      },
      {
        // 服务器监控——服务——详情
        path: 'detAnalysisUnit',
        name: 'detAnalysisUnit',
        component: () => import('../views/serverMonitor/1-3serviceFile/survey.vue')
      },
      {
        // 主机监控——主机——概况
        path: 'hostIndex',
        name: 'hostIndex',
        component: () => import('../views/serverMonitor/2-1hostFile/hostIndex.vue')
      },
      {
        // 主机监控——主机——趋势分析
        path: 'hostTrend',
        name: 'hostTrend',
        component: () => import('../views/serverMonitor/2-2hostFile/hostIndex.vue')
      },
      {
        // 主机监控——主机——详情
        path: 'hostDet',
        name: 'hostDet',
        component: () => import('../views/serverMonitor/2-3hostFile/hostIndex.vue')
      },
      {
        // 管理——用户
        path: 'manage',
        name: 'manage',
        component: () => import('../views/serverMonitor/3-1manageFile/manage.vue')
      },
      {
        // 管理——通知
        path: 'notice',
        name: 'notice',
        component: () => import('../views/serverMonitor/3-2manageFile/manage.vue')
      },
      {
        // 管理——系统
        path: 'system',
        name: 'system',
        component: () => import('../views/serverMonitor/3-3manageFile/manage.vue')
      },
      {
        // 事件——概况
        path: 'eventOver',
        name: 'eventOver',
        component: () => import('../views/serverMonitor/4-1eventFile/eventOver.vue')
      },
      {
        // 事件——监控事件
        path: 'MonitEvent',
        name: 'MonitEvent',
        component: () => import('../views/serverMonitor/4-2eventFile/eventOver.vue')
      },
      {
        // 事件——用户操作事件
        path: 'UserActionEvent',
        name: 'UserActionEvent',
        component: () => import('../views/serverMonitor/4-3eventFile/eventOver.vue')
      },
      {
        // 事件——问题处理
        path: 'problemSolve',
        name: 'problemSolve',
        component: () => import('../views/serverMonitor/4-4eventFile/eventOver.vue')
      }
    ]
  },
  {
    // 行为分析——产品数据分析
    path: '/productDataAnalysis',
    name: 'productDataAnalysis',
    component: () => import('../views/clientSideMonitor/behaviorAnalysis/productDataAnalysis/productDataAnalysis.vue')
  },
  {
    path: '/Nagios_Monitor',
    redirect: '/standard/a',
    component: () => import('../views/serverMonitor/monitorProces/status.vue')
  },
  {
    path: '/standard',
    name: 'standard',
    component: resolve => require(['../views/serverMonitor/monitorProces/status.vue'], resolve),
    children: [{
        path: 'a',
        component: resolve => require(['../components/clientSideMonitor/monitorProces/a.vue'], resolve),
      },
      {
        path: 'b',
        component: resolve => require(['../components/clientSideMonitor/monitorProces/b.vue'], resolve),
      },
      {
        path: 'c',
        component: resolve => require(['../components/clientSideMonitor/monitorProces/c.vue'], resolve),
      },
      {
        path: 'all',
        component: resolve => require(['../components/clientSideMonitor/monitorProces/all.vue'], resolve),
      }
    ]
  }
]
const router = new VueRouter({
  routes
})
export default router
