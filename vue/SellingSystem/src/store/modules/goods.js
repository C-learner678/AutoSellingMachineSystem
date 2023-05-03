import { getAllGoods } from "@/api/goods"
import { insertGoods } from "@/api/goods"
import { modifyGoods } from "@/api/goods"
import { deleteGoods } from "@/api/goods"
import { setImageId } from "@/api/goods"

const goods = {
    actions: {
      GetAllGoods({commit}){
        return new Promise((resolve, reject) => { 
          getAllGoods().then(response => { 
            resolve(response)
          }).catch(error => {
            reject(error)
          })
        })      
      },
      InsertGoods({commit},data){
        return new Promise((resolve, reject) => { 
          insertGoods(data.name,data.price).then(response => { 
            resolve(response)
          }).catch(error => {
            reject(error)
          })
        })      
      },
      ModifyGoods({commit},data){
        return new Promise((resolve, reject) => { 
          modifyGoods(data.id,data.name,data.price).then(response => { 
            resolve(response)
          }).catch(error => {
            reject(error)
          })
        })      
      },
      DeleteGoods({commit},id){
        return new Promise((resolve, reject) => { 
          deleteGoods(id).then(response => { 
            resolve(response)
          }).catch(error => {
            reject(error)
          })
        })      
      },
      SetImageId({commit},id){
        return new Promise((resolve, reject) => { 
          setImageId(id).then(response => { 
            resolve(response)
          }).catch(error => {
            reject(error)
          })
        })      
      },
    }
  }
  export default goods
  