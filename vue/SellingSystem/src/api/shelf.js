import request from '@/utils/request'

export function setShelfItem(position, goodsid, num) { // 获取货架
    return request({ // 使用封装好的 axios 进行网络请求
      url: 'shelf/setShelfItem',
      method: 'post',
      data:{
          position,
          goodsid,
          num
      }
    })
  }