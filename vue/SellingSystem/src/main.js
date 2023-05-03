import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import store from './store' // 引入 Vuex 状态管理

Vue.config.productionTip = false

Vue.use(ElementUI);

new Vue({
  render: h => h(App),
  ElementUI,
  router, // 使用路由配置
  store
}).$mount('#app')
