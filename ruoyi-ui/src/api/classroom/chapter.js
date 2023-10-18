import request from '@/utils/request'

// 查询目录列表
export function listChapter(query) {
  return request({
    url: '/classroom/chapter/list',
    method: 'get',
    params: query
  })
}

// 查询目录详细
export function getChapter(chapterId) {
  return request({
    url: '/classroom/chapter/' + chapterId,
    method: 'get'
  })
}

// 新增目录
export function addChapter(data) {
  return request({
    url: '/classroom/chapter',
    method: 'post',
    data: data
  })
}

// 修改目录
export function updateChapter(data) {
  return request({
    url: '/classroom/chapter',
    method: 'put',
    data: data
  })
}

// 删除目录
export function delChapter(chapterId) {
  return request({
    url: '/classroom/chapter/' + chapterId,
    method: 'delete'
  })
}

// 查询课程
export function getCourseInfo() {
  return request({
    url: '/classroom/chapter/course',
    method: 'get'
  })
}
