package com.ruoyi.classroom.mapper;

import java.util.List;
import com.ruoyi.classroom.domain.Course;
import com.ruoyi.classroom.domain.vo.CourseSmallVo;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据课程码查询课程id
     * @param code
     * @return
     */
    Long selectCourseIdByCode(String code);

    /**
     * 更新人数
     * @param courseId
     */
    void updateJoinNumber(Long courseId);

    /**
     * 重置课程码
     * @param courseId
     * @param newCode
     * @return
     */
    int renewCourseCode(@Param("courseId") Long courseId,@Param("newCode") String newCode);

    /**
     * 根据课程码查询课程
     * @param code
     * @return
     */
    Course selectCourseByCode(String code);

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
     * 查询课程是否置顶
     * @param courseId
     * @return
     */
    String selectTopByCourseId(Long courseId);

    /**
     * 置顶或不置顶
     * @param courseId
     * @param isTop
     */
    void updateCourseTop(@Param("courseId") long courseId, @Param("isTop") String isTop);

    /**
     * 查询置顶课程
     * @param courseId
     * @return
     */
    Course selectCourseByCourseIdTop(Long courseId);


}
