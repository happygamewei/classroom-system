package com.ruoyi.classroom.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 点赞记录对象 tb_likes
 * 
 * @author Yuan
 * @date 2023-09-08
 */
public class Likes extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 点赞记录id */
    private Long id;

    /** 评论id */
    @Excel(name = "评论id")
    private Long commentId;

    /** 点赞人id */
    @Excel(name = "点赞人id")
    private Long userId;

    /** 内容类型 */
    @Excel(name = "内容类型")
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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
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
            .append("userId", getUserId())
            .append("type", getType())
            .toString();
    }
}
