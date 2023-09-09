import request from '@/utils/request'

// 查询评论管理列表
export function listComment(query) {
  return request({
    url: '/classroom/comment/list',
    method: 'get',
    params: query
  })
}

// 查询评论管理详细
export function getComment(commentId) {
  return request({
    url: '/classroom/comment/' + commentId,
    method: 'get'
  })
}

// 新增评论管理
export function addComment(data) {
  return request({
    url: '/classroom/comment',
    method: 'post',
    data: data
  })
}

// 修改评论管理
export function updateComment(data) {
  return request({
    url: '/classroom/comment',
    method: 'put',
    data: data
  })
}

// 删除评论管理
export function delComment(commentId) {
  return request({
    url: '/classroom/comment/' + commentId,
    method: 'delete'
  })
}
