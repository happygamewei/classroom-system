package com.ruoyi.classroom.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.classroom.domain.Course;
import com.ruoyi.classroom.domain.vo.CourseContentVo;
import com.ruoyi.classroom.domain.vo.CourseVo;

/**
 * 课程管理Service接口
 * 
 * @author Yuan
 * @date 2023-09-08
 */
public interface ICourseService 
{
    /**
     * 查询课程管理
     * 
     * @param courseId 课程管理主键
     * @return 课程管理
     */
    public Course selectCourseByCourseId(Long courseId);

    /**
     * 查询课程管理列表
     * 
     * @param course 课程管理
     * @return 课程管理集合
     */
    public List<Course> selectCourseList(Course course);

    /**
     * 新增课程管理
     * 
     * @param course 课程管理
     * @return 结果
     */
    public int insertCourse(Course course);

    /**
     * 修改课程管理
     * 
     * @param course 课程管理
     * @return 结果
     */
    public int updateCourse(Course course);

    /**
     * 批量删除课程管理
     * 
     * @param courseIds 需要删除的课程管理主键集合
     * @return 结果
     */
    public int deleteCourseByCourseIds(Long[] courseIds);

    /**
     * 删除课程管理信息
     * 
     * @param courseId 课程管理主键
     * @return 结果
     */
    public int deleteCourseByCourseId(Long courseId);

    /**
     * 得到课程的相关信息
     * @param courseId
     * @return
     */
    CourseContentVo getCourseContentInfo(Long courseId);

    /**
     * 得到用户教的
     * @param userId
     * @return
     */
    Map<String, List<Course>> getUserTeachCourse(Long userId, String type);

    /**
     * 加入课程
     * @param code
     * @return
     */
    int joinCourse(String code);

    /**
     * 根据用户查询课程
     * @param userId
     * @return
     */
    List<CourseVo> listByUserId(Long userId);

    /**
     * 重置课程码
     * @param courseId
     * @return
     */
    int renewCourseCode(Long courseId);

    /**
     * 结课
     * @param courseId
     * @return
     */
    int overCourse(Long courseId);

    /**
     * 取消结课
     * @param courseId
     * @return
     */
    int openCourse(Long courseId);

    /**
     * 置顶课程或取消置顶
     * @param courseId
     * @return
     */
    int cancelTopCourse(Long courseId, String isTop);

    /**
     * 退课
     * @param courseId
     * @return
     */
    int exitCourse(Long courseId);
}
