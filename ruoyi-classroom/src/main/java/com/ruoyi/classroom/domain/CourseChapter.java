package com.ruoyi.classroom.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 课程目录关系对象 tb_course_chapter
 * 
 * @author Yuan
 * @date 2023-09-08
 */
public class CourseChapter extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id，主键 */
    private Long id;

    /** 章节id */
    @Excel(name = "章节id")
    private Long chapterId;

    /** 课程id */
    @Excel(name = "课程id")
    private Long courseId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setChapterId(Long chapterId) 
    {
        this.chapterId = chapterId;
    }

    public Long getChapterId() 
    {
        return chapterId;
    }
    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("chapterId", getChapterId())
            .append("courseId", getCourseId())
            .toString();
    }
}
