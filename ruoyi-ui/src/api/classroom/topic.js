import request from '@/utils/request'

// 查询话题管理列表
export function listTopic(query) {
  return request({
    url: '/classroom/topic/list',
    method: 'get',
    params: query
  })
}

// 查询话题管理详细
export function getTopic(topicId) {
  return request({
    url: '/classroom/topic/' + topicId,
    method: 'get'
  })
}

// 新增话题管理
export function addTopic(data) {
  return request({
    url: '/classroom/topic',
    method: 'post',
    data: data
  })
}

// 修改话题管理
export function updateTopic(data) {
  return request({
    url: '/classroom/topic',
    method: 'put',
    data: data
  })
}

// 删除话题管理
export function delTopic(topicId) {
  return request({
    url: '/classroom/topic/' + topicId,
    method: 'delete'
  })
}
