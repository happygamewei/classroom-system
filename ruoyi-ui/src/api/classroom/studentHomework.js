import request from '@/utils/request'

// 查询学生作业列表
export function listStudentHomework(query) {
  return request({
    url: '/classroom/studentHomework/list',
    method: 'get',
    params: query
  })
}

// 查询学生作业详细
export function getStudentHomework(shId) {
  return request({
    url: '/classroom/studentHomework/' + shId,
    method: 'get'
  })
}

// 新增学生作业
export function addStudentHomework(data) {
  return request({
    url: '/classroom/studentHomework',
    method: 'post',
    data: data
  })
}

// 修改学生作业
export function updateStudentHomework(data) {
  return request({
    url: '/classroom/studentHomework',
    method: 'put',
    data: data
  })
}

// 删除学生作业
export function delStudentHomework(shId) {
  return request({
    url: '/classroom/studentHomework/' + shId,
    method: 'delete'
  })
}
