package com.ruoyi.classroom.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 话题对象 tb_topic
 *
 * @author Yuan
 * @date 2023-09-07
 */
public class Topic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 话题id */
    private Long topicId;

    /** 话题标题 */
    @Excel(name = "话题标题")
    private String title;

    /** 话题内容 */
    @Excel(name = "话题内容")
    private String content;

    /** 活动类型标签 */
    @Excel(name = "活动类型标签")
    private String typeLabel;

    /** 共享协议 */
    @Excel(name = "共享协议")
    private String shareProtocol;

    /** 应用环节 */
    @Excel(name = "应用环节")
    private String process;

    /** 所属章节 */
    @Excel(name = "所属章节")
    private Long chapterId;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishDate;

    /** 截至时间 */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "截至时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadline;

    /** 总分 */
    @Excel(name = "总分")
    private Long totalScore;

    /** 至少回复次数 */
    @Excel(name = "至少回复次数")
    private Long leastReplyNumber;

    /** 已参与数量 */
    @Excel(name = "已参与数量")
    private Long joinNumber;

    /** 点赞次数 */
    @Excel(name = "点赞次数")
    private Long likeCount;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    public void setTopicId(Long topicId)
    {
        this.topicId = topicId;
    }

    public Long getTopicId()
    {
        return topicId;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setTypeLabel(String typeLabel)
    {
        this.typeLabel = typeLabel;
    }

    public String getTypeLabel()
    {
        return typeLabel;
    }
    public void setShareProtocol(String shareProtocol)
    {
        this.shareProtocol = shareProtocol;
    }

    public String getShareProtocol()
    {
        return shareProtocol;
    }
    public void setProcess(String process)
    {
        this.process = process;
    }

    public String getProcess()
    {
        return process;
    }
    public void setChapterId(Long chapterId)
    {
        this.chapterId = chapterId;
    }

    public Long getChapterId()
    {
        return chapterId;
    }
    public void setPublishDate(Date publishDate)
    {
        this.publishDate = publishDate;
    }

    public Date getPublishDate()
    {
        return publishDate;
    }
    public void setDeadline(Date deadline)
    {
        this.deadline = deadline;
    }

    public Date getDeadline()
    {
        return deadline;
    }
    public void setTotalScore(Long totalScore)
    {
        this.totalScore = totalScore;
    }

    public Long getTotalScore()
    {
        return totalScore;
    }
    public void setLeastReplyNumber(Long leastReplyNumber)
    {
        this.leastReplyNumber = leastReplyNumber;
    }

    public Long getLeastReplyNumber()
    {
        return leastReplyNumber;
    }
    public void setJoinNumber(Long joinNumber)
    {
        this.joinNumber = joinNumber;
    }

    public Long getJoinNumber()
    {
        return joinNumber;
    }
    public void setLikeCount(Long likeCount)
    {
        this.likeCount = likeCount;
    }

    public Long getLikeCount()
    {
        return likeCount;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getUserId()
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("topicId", getTopicId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("typeLabel", getTypeLabel())
            .append("shareProtocol", getShareProtocol())
            .append("process", getProcess())
            .append("chapterId", getChapterId())
            .append("publishDate", getPublishDate())
            .append("deadline", getDeadline())
            .append("totalScore", getTotalScore())
            .append("leastReplyNumber", getLeastReplyNumber())
            .append("joinNumber", getJoinNumber())
            .append("likeCount", getLikeCount())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("userId", getUserId())
            .toString();
    }
}
