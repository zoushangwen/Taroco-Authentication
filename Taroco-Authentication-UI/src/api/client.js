import { axios } from '@/utils/request'

/**
 * 查询单个客户端
 */
export function getClient (clientId) {
  return axios({
    url: `/clients/${clientId}`,
    method: 'get'
  })
}

/**
 * 查询客户端分页
 */
export function getList (params) {
  return axios({
    url: `/clients`,
    method: 'get',
    params
  })
}

/**
 * 修改单个客户端
 */
export function updateClient (data) {
  return axios({
    url: `/clients`,
    method: 'put',
    data
  })
}

/**
 * 新增单个客户端
 */
export function addClient (data) {
  return axios({
    url: `/clients`,
    method: 'post',
    data
  })
}

/**
 * 删除单个客户端
 */
export function deleteClient (clientId) {
  return axios({
    url: `/clients/${clientId}`,
    method: 'delete'
  })
}
