package com.ruoyi.classroom.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公告对象 tb_notice
 *
 * @author Qiao
 * @date 2023-09-08
 */
public class Notice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公告id */
    private Long noticeId;

    /** 公告标题 */
    @Excel(name = "公告标题")
    private String title;

    /** 公告内容 */
    @Excel(name = "公告内容")
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

    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;

    /** 已读数量 */
    @Excel(name = "已读数量")
    private Long readNumber;

    /** 是否置顶，0否，1是 */
    @Excel(name = "是否置顶，0否，1是")
    private String isTop;

    /** 状态，0禁用，1激活 */
    @Excel(name = "状态，0禁用，1激活")
    private String status;

    /** 文件附件 */
    @Excel(name = "文件附件")
    private String documentLocation;

    /** 图片附件 */
    @Excel(name = "图片附件")
    private String pictureLocation;

    public void setNoticeId(Long noticeId)
    {
        this.noticeId = noticeId;
    }

    public Long getNoticeId()
    {
        return noticeId;
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
    public void setReadNumber(Long readNumber)
    {
        this.readNumber = readNumber;
    }

    public Long getReadNumber()
    {
        return readNumber;
    }
    public void setIsTop(String isTop)
    {
        this.isTop = isTop;
    }

    public String getIsTop()
    {
        return isTop;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getStatus()
    {
        return status;
    }
    public void setDocumentLocation(String documentLocation)
    {
        this.documentLocation = documentLocation;
    }

    public String getDocumentLocation()
    {
        return documentLocation;
    }
    public void setPictureLocation(String pictureLocation)
    {
        this.pictureLocation = pictureLocation;
    }

    public String getPictureLocation()
    {
        return pictureLocation;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("noticeId", getNoticeId())
            .append("title", getTitle())
            .append("content", getContent())
            .append("typeLabel", getTypeLabel())
            .append("shareProtocol", getShareProtocol())
            .append("process", getProcess())
            .append("chapterId", getChapterId())
            .append("publishDate", getPublishDate())
            .append("readNumber", getReadNumber())
            .append("isTop", getIsTop())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("documentLocation", getDocumentLocation())
            .append("pictureLocation", getPictureLocation())
            .toString();
    }
}
