import request from '@/utils/request' 

export function getCoin () {
  return request({ 
    url: '/coin/getAllCoin',
    method: 'get',
  })
}

export function getPaperMoney () {
  return request({
    url: '/paperMoney/getAllPaperMoney',
    method: 'get',
  })
}

export function insertCoin (value) {
  return request({
    url: '/coin/insertCoin',
    method: 'post',
    data:{
      value
    }
  })
}

export function insertPaperMoney (value) {
  return request({
    url: '/paperMoney/insertPaperMoney',
    method: 'post',
    data:{
      value
    }
  })
}

export function returnMoney(money) {
  return request({
    url: '/money/returnMoney',
    method: 'post',
    data:{
      money
    }
  })
}

export function setCoin(value, num) {
  return request({
    url: '/coin/setCoin',
    method: 'post',
    data:{
      value,
      num
    }
  })
}

export function setPaperMoney(value, num) {
  return request({
    url: '/paperMoney/setPaperMoney',
    method: 'post',
    data:{
      value,
      num
    }
  })
}