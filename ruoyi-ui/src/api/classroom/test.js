import request from '@/utils/request'

// 查询测试管理列表
export function listTest(query) {
  return request({
    url: '/classroom/test/list',
    method: 'get',
    params: query
  })
}

// 查询测试管理详细
export function getTest(testId) {
  return request({
    url: '/classroom/test/' + testId,
    method: 'get'
  })
}

// 新增测试管理
export function addTest(data) {
  return request({
    url: '/classroom/test',
    method: 'post',
    data: data
  })
}

// 修改测试管理
export function updateTest(data) {
  return request({
    url: '/classroom/test',
    method: 'put',
    data: data
  })
}

// 删除测试管理
export function delTest(testId) {
  return request({
    url: '/classroom/test/' + testId,
    method: 'delete'
  })
}
