package com.ruoyi.classroom.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 章节内容管理对象 tb_chapter_content
 * 
 * @author Yuan
 * @date 2023-09-08
 */
public class ChapterContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 章节id */
    @Excel(name = "章节id")
    private Long chapterId;

    /** 内容id */
    @Excel(name = "内容id")
    private Long contentId;

    /** 类型，1作业，2测试，3资料，4公告，5话题 */
    @Excel(name = "类型，1作业，2测试，3资料，4公告，5话题")
    private String contentType;

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
    public void setContentId(Long contentId) 
    {
        this.contentId = contentId;
    }

    public Long getContentId() 
    {
        return contentId;
    }
    public void setContentType(String contentType) 
    {
        this.contentType = contentType;
    }

    public String getContentType() 
    {
        return contentType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("chapterId", getChapterId())
            .append("contentId", getContentId())
            .append("contentType", getContentType())
            .toString();
    }
}
