package com.ruoyi.classroom.service.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import com.ruoyi.classroom.domain.CourseChapter;
import com.ruoyi.classroom.domain.vo.CourseContentVo;
import com.ruoyi.classroom.mapper.ChapterMapper;
import com.ruoyi.classroom.mapper.CourseChapterMapper;
import com.ruoyi.classroom.utils.RandomStringGenerator;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.classroom.mapper.CourseMapper;
import com.ruoyi.classroom.domain.Course;
import com.ruoyi.classroom.service.ICourseService;

/**
 * 课程管理Service业务层处理
 * 
 * @author Yuan
 * @date 2023-09-08
 */
@Service
public class CourseServiceImpl implements ICourseService 
{
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseChapterMapper courseChapterMapper;

    @Autowired
    private ChapterMapper chapterMapper;

    /**
     * 查询课程管理
     * 
     * @param courseId 课程管理主键
     * @return 课程管理
     */
    @Override
    public Course selectCourseByCourseId(Long courseId)
    {
        return courseMapper.selectCourseByCourseId(courseId);
    }

    /**
     * 查询课程管理列表
     * 
     * @param course 课程管理
     * @return 课程管理
     */
    @Override
    public List<Course> selectCourseList(Course course)
    {
        return courseMapper.selectCourseList(course);
    }

    /**
     * 新增课程管理
     * 
     * @param course 课程管理
     * @return 结果
     */
    @Override
    public int insertCourse(Course course)
    {
        //设置课程码
        course.setCode(RandomStringGenerator.generateRandomString(6));
        //设置创建者
        course.setCreateBy(SecurityUtils.getUsername());
        course.setCreateTime(DateUtils.getNowDate());
        //设置状态
        course.setStatus("0");
        return courseMapper.insertCourse(course);
    }

    /**
     * 修改课程管理
     * 
     * @param course 课程管理
     * @return 结果
     */
    @Override
    public int updateCourse(Course course)
    {
        course.setUpdateTime(DateUtils.getNowDate());
        return courseMapper.updateCourse(course);
    }

    /**
     * 批量删除课程管理
     * 
     * @param courseIds 需要删除的课程管理主键
     * @return 结果
     */
    @Override
    public int deleteCourseByCourseIds(Long[] courseIds)
    {
        return courseMapper.deleteCourseByCourseIds(courseIds);
    }

    /**
     * 删除课程管理信息
     * 
     * @param courseId 课程管理主键
     * @return 结果
     */
    @Override
    public int deleteCourseByCourseId(Long courseId)
    {
        return courseMapper.deleteCourseByCourseId(courseId);
    }

    /**
     * 得到课程相关信息
     * @param courseId
     * @return
     */
    @Override
    public CourseContentVo getCourseContentInfo(Long courseId) {
        // 查询课程和章节的关系
        List<CourseChapter> courseChapters = courseChapterMapper.selectChaptersByCourserId(courseId);
        CourseContentVo courseContentVo = new CourseContentVo();
        courseContentVo.setChapterNumber(courseChapters.size());

        // 获得课程总学时数
        Integer courseHour = courseMapper.selectCreditHoursByCourseId(courseId);
        courseContentVo.setCreditHours(courseHour);

        // 获取已经分配的学时数
        AtomicInteger allocated = new AtomicInteger(0);
        courseChapters.stream().forEach(courseChapter -> {
            Long chapterId = courseChapter.getChapterId();
            Integer chapterHour = chapterMapper.selectCreditHoursByCourseId(chapterId);
            allocated.addAndGet(chapterHour);
        });
        courseContentVo.setAllocatedHours(allocated.get());

        // 获取课程中的活动数
        courseContentVo.setActiveNumber(3);
        return courseContentVo;
    }
}
