package com.ruoyi.classroom.mapper;

import java.util.List;
import com.ruoyi.classroom.domain.CourseUser;
import org.apache.ibatis.annotations.Param;

/**
 * 课程用户关系Mapper接口
 *
 * @author Yuan
 * @date 2023-09-08
 */
public interface CourseUserMapper
{
    /**
     * 查询课程用户关系
     *
     * @param id 课程用户关系主键
     * @return 课程用户关系
     */
    public CourseUser selectCourseUserById(Long id);

    /**
     * 查询课程用户关系列表
     *
     * @param courseUser 课程用户关系
     * @return 课程用户关系集合
     */
    public List<CourseUser> selectCourseUserList(CourseUser courseUser);

    /**
     * 新增课程用户关系
     *
     * @param courseUser 课程用户关系
     * @return 结果
     */
    public int insertCourseUser(CourseUser courseUser);

    /**
     * 修改课程用户关系
     *
     * @param courseUser 课程用户关系
     * @return 结果
     */
    public int updateCourseUser(CourseUser courseUser);

    /**
     * 删除课程用户关系
     *
     * @param id 课程用户关系主键
     * @return 结果
     */
    public int deleteCourseUserById(Long id);

    /**
     * 批量删除课程用户关系
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseUserByIds(Long[] ids);

    public List<CourseUser> findUserCountByCourse(Long courseId);

    /**
     * 根据用户id查询用户教的和学的课程
     * @param
     * @return
     */
    List<CourseUser> selectByUserId(@Param("user_id") Long userId, @Param("type") String type);

    /**
     * 根据用户id查询课程
     * @param userId
     * @return
     */
    List<CourseUser> selectCourseUserByUserId(Long userId);
    List<Long> selectUserIdByCourseId(Long courseId);

    /**
     * 退课
     * @param courseId
     * @return
     */
    int exitCourse(@Param("courseId") Long courseId, @Param("userId") Long userId);
}
