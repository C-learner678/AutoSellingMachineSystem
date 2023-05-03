import Vue from 'vue'
import Router from 'vue-router'
import Selling from '@/views/selling'
import Login from '@/views/login'
import Admin from '@/views/admin'

Vue.use(Router)

const router = new Router({
  routes:[
    {
      path: '/',
      component: Selling
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/admin',
      component: Admin,
      meta: {
        requireAuth: true
      }
    }
  ]
})

export default router

router.beforeEach((to, from, next) => {
  if (to.meta.requireAuth) { // 判断该路由是否需要登录权限
    if (sessionStorage.getItem("token") == 'true') { // 判断本地是否存在token
      next()
    } else {
      // 未登录,跳转到登陆页面
      next({
        path: '/login'
      })
    }
  } else {
    next();
  }
});