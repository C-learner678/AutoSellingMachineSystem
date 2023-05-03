import { getCoin } from '@/api/money'
import { getPaperMoney } from '@/api/money'
import { insertCoin } from '@/api/money'
import { insertPaperMoney } from '@/api/money'
import { returnMoney } from '@/api/money'
import { setCoin } from '@/api/money'
import { setPaperMoney } from '@/api/money'

const money = {
  actions: {
    GetCoin ({ commit }) {
      return new Promise((resolve, reject) => { 
        getCoin().then(response => {
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    GetPaperMoney ({ commit }) {
      return new Promise((resolve, reject) => { 
        getPaperMoney().then(response => { 
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    InsertCoin({ commit }, coinValue) {
      return new Promise((resolve, reject) => {
        insertCoin(coinValue).then(response => { 
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    InsertPaperMoney({ commit }, paperMoneyValue) {
      return new Promise((resolve, reject) => {
        insertPaperMoney(paperMoneyValue).then(response => { 
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    ReturnMoney({ commit }, money) {
      return new Promise((resolve, reject) => {
        returnMoney(money).then(response => { 
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    SetCoin({ commit }, data) {
      return new Promise((resolve, reject) => {
        setCoin(data.value, data.num).then(response => { 
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
    SetPaperMoney({ commit }, data) {
      return new Promise((resolve, reject) => {
        setPaperMoney(data.value, data.num).then(response => { 
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },
  }
}
export default money
