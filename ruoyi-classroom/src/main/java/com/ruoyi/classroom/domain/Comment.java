package com.ruoyi.classroom.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 评论管理对象 tb_comment
 * 
 * @author Yuan
 * @date 2023-09-08
 */
public class Comment extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 评论id，主键 */
    private Long commentId;

    /** 被评论表的id */
    @Excel(name = "被评论表的id")
    private Long typeId;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    /** 所回复评论的userId */
    @Excel(name = "所回复评论的userId")
    private Long toCommentUserId;

    /** 回复评论的id */
    @Excel(name = "回复评论的id")
    private Long toCommentId;

    /** 评论点赞数 */
    @Excel(name = "评论点赞数")
    private Long likesNumber;

    /** 状态，0禁用，1激活 */
    @Excel(name = "状态，0禁用，1激活")
    private String status;

    public void setCommentId(Long commentId) 
    {
        this.commentId = commentId;
    }

    public Long getCommentId() 
    {
        return commentId;
    }
    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setToCommentUserId(Long toCommentUserId) 
    {
        this.toCommentUserId = toCommentUserId;
    }

    public Long getToCommentUserId() 
    {
        return toCommentUserId;
    }
    public void setToCommentId(Long toCommentId) 
    {
        this.toCommentId = toCommentId;
    }

    public Long getToCommentId() 
    {
        return toCommentId;
    }
    public void setLikesNumber(Long likesNumber) 
    {
        this.likesNumber = likesNumber;
    }

    public Long getLikesNumber() 
    {
        return likesNumber;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("commentId", getCommentId())
            .append("parentId", getParentId())
            .append("typeId", getTypeId())
            .append("content", getContent())
            .append("toCommentUserId", getToCommentUserId())
            .append("toCommentId", getToCommentId())
            .append("likesNumber", getLikesNumber())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
