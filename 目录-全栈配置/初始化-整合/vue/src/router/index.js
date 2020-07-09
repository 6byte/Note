import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [{
    path: '/',
    redirect: {
      name: 'home'
    }
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('@/module/index/home.vue')
  },
  {
    path: '/error',
    component: () => import('@/module/common/error.vue')
  },
  {
    path: '*',
    redirect: '/error'
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
