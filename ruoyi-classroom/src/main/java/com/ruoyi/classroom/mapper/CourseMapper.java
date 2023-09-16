package com.ruoyi.classroom.mapper;

import java.util.List;
import com.ruoyi.classroom.domain.Course;
import com.ruoyi.classroom.domain.vo.CourseSmallVo;

/**
 * 课程管理Mapper接口
 * 
 * @author Yuan
 * @date 2023-09-08
 */
public interface CourseMapper 
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
     * 删除课程管理
     * 
     * @param courseId 课程管理主键
     * @return 结果
     */
    public int deleteCourseByCourseId(Long courseId);

    /**
     * 批量删除课程管理
     * 
     * @param courseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseByCourseIds(Long[] courseIds);

    /**
     * 在chapter中查询课程
     * @return
     */
    List<CourseSmallVo> selectCourseInChapter();

    /**
     * 根据课程id查询课程学时
     * @param courseId
     * @return
     */
    Integer selectCreditHoursByCourseId(Long courseId);
}
