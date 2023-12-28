package com.ruoyi.classroom.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测试管理对象 tb_test
 *
 * @author Yuan
 * @date 2023-09-08
 */
public class Test extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 测试id */
    private Long testId;

    /** 测试标题 */
    @Excel(name = "测试标题")
    private String title;

    /** 测试内容 */
    @Excel(name = "测试内容")
    private String content;

    /** 创建者id */
    @Excel(name = "创建者id")
    private Long userId;

    /** 测试标签，1普通测试，2考试 */
    @Excel(name = "测试标签，1普通测试，2考试")
    private String testLabel;

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
    @Excel(name = "发布时间", width = 30, dateFormat = "YYYY-MM-DD HH:mm:ss")
    private Date publishDate;

    /** 截至时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截至时间", width = 30, dateFormat = "YYYY-MM-DD HH:mm:ss")
    private Date deadline;

    /** 总分 */
    @Excel(name = "总分")
    private Long totalScore;

    /** 已批人数 */
    @Excel(name = "已批人数")
    private Long corrrcted;

    /** 未交人数 */
    @Excel(name = "未交人数")
    private Long unpaid;

    /** int	状态，0禁用，1激活 */
    @Excel(name = "int	状态，0禁用，1激活")
    private Long status;

    @Excel(name = "课程id")
    private Long course_id;

    public Long getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Long course_id) {
        this.course_id = course_id;
    }

    public void setTestId(Long testId)
    {
        this.testId = testId;
    }

    public Long getTestId()
    {
        return testId;
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
    public void setTestLabel(String testLabel)
    {
        this.testLabel = testLabel;
    }

    public String getTestLabel()
    {
        return testLabel;
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
    public void setCorrrcted(Long corrrcted)
    {
        this.corrrcted = corrrcted;
    }

    public Long getCorrrcted()
    {
        return corrrcted;
    }
    public void setUnpaid(Long unpaid)
    {
        this.unpaid = unpaid;
    }

    public Long getUnpaid()
    {
        return unpaid;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("testId", getTestId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("userId", getUserId())
            .append("testLabel", getTestLabel())
            .append("typeLabel", getTypeLabel())
            .append("shareProtocol", getShareProtocol())
            .append("process", getProcess())
            .append("chapterId", getChapterId())
            .append("publishDate", getPublishDate())
            .append("deadline", getDeadline())
            .append("totalScore", getTotalScore())
            .append("corrrcted", getCorrrcted())
            .append("unpaid", getUnpaid())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
