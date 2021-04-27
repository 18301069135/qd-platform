import request from '@/utils/request'


export function login(data) {
  return request({
    url: 'http://192.168.1.102:8082/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/vue-admin-template/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: 'http://192.168.1.102:8082/logout',
    method: 'post'
  })
}
