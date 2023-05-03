import request from '@/utils/request' // 引入封装好的 axios 请求

export function getShelf () { // 获取货架
  return request({ // 使用封装好的 axios 进行网络请求
    url: '/shelf/getAllShelfItem',
    method: 'get',
  })
}

export function buyShelfItem(position) {
  return request({
    url: '/shelf/buyShelfItem',
    method: 'post',
    data:{
      position
    }
  })
}

export function getImage(id){
  return request({
    url: '/image/getImage/'+id,
    method: 'get',
    responseType: "arraybuffer"
  })
}