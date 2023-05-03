import request from '@/utils/request' 

export function getAllGoods() {
  return request({
    url: '/goods/getAllGoods',
    method: 'get',
  })
}

export function insertGoods(name, price){
  return request({
    url: '/goods/insertGoods',
    method: 'post',
    data:{
      name,
      price
    }
  })
}

export function modifyGoods(id, name, price){
  return request({
    url: '/goods/modifyGoods',
    method: 'post',
    data:{
      id,
      name,
      price
    }
  })
}

export function deleteGoods(id){
  return request({
    url: '/goods/deleteGoods',
    method: 'post',
    data:{
      id
    }
  })
}

export function setImageId(id){
  return request({
    url: '/image/setId',
    method: 'post',
    data:{
      id
    }
  })  
}