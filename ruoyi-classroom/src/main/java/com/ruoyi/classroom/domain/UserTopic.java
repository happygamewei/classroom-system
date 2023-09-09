package com.ruoyi.classroom.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户话题关系管理对象 tb_user_topic
 * 
 * @author Yuan
 * @date 2023-09-07
 */
public class UserTopic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户话题id */
    private Long userTopicId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 话题id */
    @Excel(name = "话题id")
    private Long topicId;

    /** 是否已参与 0未参与，1已参与 */
    @Excel(name = "是否已参与 0未参与，1已参与")
    private String isReadLabel;

    /** 是否已点赞 0未点赞，1已点赞 */
    @Excel(name = "是否已点赞 0未点赞，1已点赞")
    private String isLike;

    /** 状态，0禁用，1激活 */
    @Excel(name = "状态，0禁用，1激活")
    private String status;

    public void setUserTopicId(Long userTopicId) 
    {
        this.userTopicId = userTopicId;
    }

    public Long getUserTopicId() 
    {
        return userTopicId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setTopicId(Long topicId) 
    {
        this.topicId = topicId;
    }

    public Long getTopicId() 
    {
        return topicId;
    }
    public void setIsReadLabel(String isReadLabel) 
    {
        this.isReadLabel = isReadLabel;
    }

    public String getIsReadLabel() 
    {
        return isReadLabel;
    }
    public void setIsLike(String isLike) 
    {
        this.isLike = isLike;
    }

    public String getIsLike() 
    {
        return isLike;
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
            .append("userTopicId", getUserTopicId())
            .append("userId", getUserId())
            .append("topicId", getTopicId())
            .append("isReadLabel", getIsReadLabel())
            .append("isLike", getIsLike())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
