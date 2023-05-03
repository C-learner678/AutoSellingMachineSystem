import { getShelf } from '@/api/buy'
import { buyShelfItem } from '@/api/buy' 
import { getImage } from '@/api/buy'

const buy = {
  actions: {
    GetShelf ({ commit }) { //在组件中使用 this.$store.dispatch("GetSehlf") 调用
      return new Promise((resolve, reject) => { 
        getShelf().then(response => { 
          resolve(response) // 将结果封装进 Promise
        }).catch(error => {
          reject(error)
        })
      })
    },
    Buy({commit}, position){
      return new Promise((resolve, reject) => { 
        buyShelfItem(position).then(response => { 
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })      
    },
    GetImage({commit}, id){
      return new Promise((resolve, reject) => { 
        getImage(id).then(response => { 
          resolve(response) 
        }).catch(error => {
          reject(error)
        })
      })            
    }
  }
}
export default buy
