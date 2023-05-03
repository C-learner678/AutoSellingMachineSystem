import Vue from 'vue' // 引入 Vue
import Vuex from 'vuex' // 引入 Vuex
import buy from './modules/buy'
import money from './modules/money'
import login from './modules/login'
import goods from './modules/goods'
import shelf from './modules/shelf'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    buy,
    money,
    login,
    goods,
    shelf
  }
})

export default store
