package com.ruoyi.classroom.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 *  评论内容关系对象 tb_comment_content
 * 
 * @author Yuan
 * @date 2023-09-08
 */
public class CommentContent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论内容关系id，主键 */
    private Long id;

    /** 评论id */
    @Excel(name = "评论id")
    private Long commentId;

    /** 内容id */
    @Excel(name = "内容id")
    private Long contentId;

    /** 内容类型，1是作业，2是测试，3是公告，4是话题 */
    @Excel(name = "内容类型，1是作业，2是测试，3是公告，4是话题")
    private String type;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCommentId(Long commentId) 
    {
        this.commentId = commentId;
    }

    public Long getCommentId() 
    {
        return commentId;
    }
    public void setContentId(Long contentId) 
    {
        this.contentId = contentId;
    }

    public Long getContentId() 
    {
        return contentId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("commentId", getCommentId())
            .append("contentId", getContentId())
            .append("type", getType())
            .toString();
    }
}
