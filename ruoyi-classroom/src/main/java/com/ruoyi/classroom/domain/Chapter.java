package com.ruoyi.classroom.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 章节对象 tb_chapter
 * 
 * @author Yuan
 * @date 2023-09-08
 */
public class Chapter extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 目录id */
    private Long chapterId;

    /** 创建者id */
    private Long userId;

    /** 章节/小节名称 */
    @Excel(name = "章节/小节名称")
    private String name;

    /** 授课模式 */
    @Excel(name = "授课模式")
    private String teachMode;

    /** 学时数 */
    @Excel(name = "学时数")
    private Long creditHours;

    /** 目录类型 */
    @Excel(name = "目录类型")
    private String type;

    /** 顺序 */
    @Excel(name = "顺序")
    private Long sort;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setChapterId(Long chapterId) 
    {
        this.chapterId = chapterId;
    }

    public Long getChapterId() 
    {
        return chapterId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setTeachMode(String teachMode) 
    {
        this.teachMode = teachMode;
    }

    public String getTeachMode() 
    {
        return teachMode;
    }
    public void setCreditHours(Long creditHours) 
    {
        this.creditHours = creditHours;
    }

    public Long getCreditHours() 
    {
        return creditHours;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
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
            .append("chapterId", getChapterId())
            .append("parentId", getParentId())
            .append("userId", getUserId())
            .append("name", getName())
            .append("teachMode", getTeachMode())
            .append("creditHours", getCreditHours())
            .append("type", getType())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
