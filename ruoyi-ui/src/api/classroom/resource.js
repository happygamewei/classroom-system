import request from '@/utils/request'

// 查询资料管理列表
export function listResource(query) {
  return request({
    url: '/classroom/resource/list',
    method: 'get',
    params: query
  })
}

// 查询资料管理详细
export function getResource(resourceId) {
  return request({
    url: '/classroom/resource/' + resourceId,
    method: 'get'
  })
}

// 新增资料管理
export function addResource(data) {
  return request({
    url: '/classroom/resource',
    method: 'post',
    data: data
  })
}

// 修改资料管理
export function updateResource(data) {
  return request({
    url: '/classroom/resource',
    method: 'put',
    data: data
  })
}

// 删除资料管理
export function delResource(resourceId) {
  return request({
    url: '/classroom/resource/' + resourceId,
    method: 'delete'
  })
}
