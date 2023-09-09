package com.ruoyi.classroom.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 发布作业对象 tb_homework
 * 
 * @author rain
 * @date 2023-09-08
 */
public class Homework extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 作业id */
    private Long homeworkId;

    /** 作业标题 */
    @Excel(name = "作业标题")
    private String title;

    /** 作业内容 */
    @Excel(name = "作业内容")
    private String content;

    /** 创建者id */
    @Excel(name = "创建者id")
    private Long userId;

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

    /** 发布状态，未发布为0，发布为1 */
    @Excel(name = "发布状态，未发布为0，发布为1")
    private String ifPublish;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishDate;

    /** 截至时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截至时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deadline;

    /** 总分 */
    @Excel(name = "总分")
    private Long totalScore;

    /** 已批完 */
    @Excel(name = "已批完")
    private Long approved;

    /** 未交 */
    @Excel(name = "未交")
    private Long unpaid;

    /** 是否查重，0否，1是 */
    @Excel(name = "是否查重，0否，1是")
    private String isCheck;

    /** 查重警戒值 */
    @Excel(name = "查重警戒值")
    private Long checkNumber;

    /** 是否自动打回，0否1是 */
    @Excel(name = "是否自动打回，0否1是")
    private String ifBack;

    /** 状态，0禁用，1激活 */
    @Excel(name = "状态，0禁用，1激活")
    private String status;

    public void setHomeworkId(Long homeworkId) 
    {
        this.homeworkId = homeworkId;
    }

    public Long getHomeworkId() 
    {
        return homeworkId;
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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
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
    public void setIfPublish(String ifPublish) 
    {
        this.ifPublish = ifPublish;
    }

    public String getIfPublish() 
    {
        return ifPublish;
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
    public void setApproved(Long approved) 
    {
        this.approved = approved;
    }

    public Long getApproved() 
    {
        return approved;
    }
    public void setUnpaid(Long unpaid) 
    {
        this.unpaid = unpaid;
    }

    public Long getUnpaid() 
    {
        return unpaid;
    }
    public void setIsCheck(String isCheck) 
    {
        this.isCheck = isCheck;
    }

    public String getIsCheck() 
    {
        return isCheck;
    }
    public void setCheckNumber(Long checkNumber) 
    {
        this.checkNumber = checkNumber;
    }

    public Long getCheckNumber() 
    {
        return checkNumber;
    }
    public void setIfBack(String ifBack) 
    {
        this.ifBack = ifBack;
    }

    public String getIfBack() 
    {
        return ifBack;
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
            .append("homeworkId", getHomeworkId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("userId", getUserId())
            .append("typeLabel", getTypeLabel())
            .append("shareProtocol", getShareProtocol())
            .append("process", getProcess())
            .append("chapterId", getChapterId())
            .append("ifPublish", getIfPublish())
            .append("publishDate", getPublishDate())
            .append("deadline", getDeadline())
            .append("totalScore", getTotalScore())
            .append("approved", getApproved())
            .append("unpaid", getUnpaid())
            .append("isCheck", getIsCheck())
            .append("checkNumber", getCheckNumber())
            .append("ifBack", getIfBack())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
