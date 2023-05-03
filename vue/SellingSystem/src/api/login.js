import request from '@/utils/request' 

export function loginIntoSystem(psw) {
  return request({
    url: '/login/loginIntoSystem',
    method: 'post',
    data:{
      psw
    }
  })
}

export function changePassword(oldpsw,newpsw) {
  return request({
    url: '/login/changePassword',
    method: 'post',
    data:{
      oldpsw,
      newpsw
    }
  })
}