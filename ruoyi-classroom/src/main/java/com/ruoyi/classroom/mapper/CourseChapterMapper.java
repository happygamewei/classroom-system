package com.ruoyi.classroom.mapper;

import java.util.List;
import com.ruoyi.classroom.domain.CourseChapter;

/**
 * 课程目录关系Mapper接口
 * 
 * @author Yuan
 * @date 2023-09-08
 */
public interface CourseChapterMapper 
{
    /**
     * 查询课程目录关系
     * 
     * @param id 课程目录关系主键
     * @return 课程目录关系
     */
    public CourseChapter selectCourseChapterById(Long id);

    /**
     * 查询课程目录关系列表
     * 
     * @param courseChapter 课程目录关系
     * @return 课程目录关系集合
     */
    public List<CourseChapter> selectCourseChapterList(CourseChapter courseChapter);

    /**
     * 新增课程目录关系
     * 
     * @param courseChapter 课程目录关系
     * @return 结果
     */
    public int insertCourseChapter(CourseChapter courseChapter);

    /**
     * 修改课程目录关系
     * 
     * @param courseChapter 课程目录关系
     * @return 结果
     */
    public int updateCourseChapter(CourseChapter courseChapter);

    /**
     * 删除课程目录关系
     * 
     * @param id 课程目录关系主键
     * @return 结果
     */
    public int deleteCourseChapterById(Long id);

    /**
     * 批量删除课程目录关系
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCourseChapterByIds(Long[] ids);

    /**
     * 根据chapterId删除
     * @param chapterId
     */
    void deleteByChapterId(Long chapterId);

    /**
     * 根据课程id查询
     * @param courseId
     * @return
     */
    List<CourseChapter> selectChaptersByCourserId(Long courseId);
}
