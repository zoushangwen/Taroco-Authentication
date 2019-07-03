import { axios } from '@/utils/request'

/**
 * 用户登录
 *
 * @param {用户名,密码} parameter
 */
export function login (parameter) {
  return axios({
    url: '/login',
    method: 'post',
    data: parameter
  })
}

/**
 * 查询用户信息
 */
export function getInfo () {
  return axios({
    url: '/user',
    method: 'get'
  })
}

/**
 * 退出登录
 */
export function logout () {
  return axios({
    url: '/logout',
    method: 'post'
  })
}
