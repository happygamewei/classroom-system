package com.ruoyi.classroom.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资料管理对象 tb_resource
 * 
 * @author Yuan
 * @date 2023-09-09
 */
public class Resource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 资料id，主键 */
    private Long resourceId;

    /** 资料标题 */
    @Excel(name = "资料标题")
    private String title;

    /** 资料地址 */
    @Excel(name = "资料地址")
    private String path;

    /** 资料大小 */
    @Excel(name = "资料大小")
    private String size;

    /** 创建者id */
    private Long userId;

    /** 是否允许下载，0否，1是 */
    @Excel(name = "是否允许下载，0否，1是")
    private String isDownload;

    /** 类型 */
    @Excel(name = "类型")
    private Long type;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date publishDate;

    /** 截至时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @Excel(name = "截至时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm")
    private Date deadline;

    /** 总分 */
    @Excel(name = "总分")
    private Long totalScore;

    /** 已完成人数 */
    @Excel(name = "已完成人数")
    private Long completed;

    /** 学习中人数 */
    @Excel(name = "学习中人数")
    private Long studying;

    /** 未学习人数 */
    @Excel(name = "未学习人数")
    private Long nostudying;

    /** 状态，0禁用，1激活 */
    @Excel(name = "状态，0禁用，1激活")
    private String status;

    public void setResourceId(Long resourceId) 
    {
        this.resourceId = resourceId;
    }

    public Long getResourceId() 
    {
        return resourceId;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }
    public void setSize(String size) 
    {
        this.size = size;
    }

    public String getSize() 
    {
        return size;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setIsDownload(String isDownload) 
    {
        this.isDownload = isDownload;
    }

    public String getIsDownload() 
    {
        return isDownload;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
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
    public void setCompleted(Long completed) 
    {
        this.completed = completed;
    }

    public Long getCompleted() 
    {
        return completed;
    }
    public void setStudying(Long studying) 
    {
        this.studying = studying;
    }

    public Long getStudying() 
    {
        return studying;
    }
    public void setNostudying(Long nostudying) 
    {
        this.nostudying = nostudying;
    }

    public Long getNostudying() 
    {
        return nostudying;
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
            .append("resourceId", getResourceId())
            .append("title", getTitle())
            .append("path", getPath())
            .append("size", getSize())
            .append("userId", getUserId())
            .append("isDownload", getIsDownload())
            .append("type", getType())
            .append("typeLabel", getTypeLabel())
            .append("shareProtocol", getShareProtocol())
            .append("process", getProcess())
            .append("chapterId", getChapterId())
            .append("publishDate", getPublishDate())
            .append("deadline", getDeadline())
            .append("totalScore", getTotalScore())
            .append("completed", getCompleted())
            .append("studying", getStudying())
            .append("nostudying", getNostudying())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
