import { setShelfItem } from "@/api/shelf"

const buy = {
  actions: {
    SetShelfItem ({ commit },data) { 
      return new Promise((resolve, reject) => { 
        setShelfItem(data.position, data.goodsid, data.num).then(response => { 
          resolve(response) 
        }).catch(error => {
          reject(error)
        })
      })
    },
  }
}
export default buy
